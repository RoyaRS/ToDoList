package com.example.roya.todolist.Model;

/**
 * Created by Roya on 6/23/2017.
 */

public class Tasks {

    private int Id;
    private String title;

    public Tasks() {

    }

    public Tasks(int id, String title) {

        this.setId(id);
        this.setTitle(title);
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
