package com.example.week2practical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DataHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User.DB";
    public static final String TABLE_USERS = "user";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";


    private static DataHandler instance;
    private SQLiteDatabase database;

    private DataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = this.getWritableDatabase();
    }

    public static synchronized DataHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DataHandler(context.getApplicationContext());
        }
        return instance;
    }

    public DataHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory,
                       int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_FOLLOWED + " INTEGER DEFAULT 0" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed() ? 1 : 0);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        //db.close();
    }

    public boolean hasUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public List<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add them to the list
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                int followed = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FOLLOWED));
                boolean isFollowed = followed == 1;

                User user = new User(name, description, id, isFollowed);
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return userList;
    }

    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, user.followed ? 1 : 0);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_USERS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(user.id) });
        db.close();
    }

}
