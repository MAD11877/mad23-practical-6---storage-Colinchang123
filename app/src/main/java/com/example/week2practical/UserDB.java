package com.example.week2practical;

public class UserDB {

    private String name;
    private String description;
    private int id;
    boolean followed;

    public UserDB() { }
    public UserDB(String Name, String Description, int Id, boolean Followed) {
        this.name = Name;
        this.description = Description;
        this.id = Id;
        this.followed = Followed;
    }


}
