package com.example.roya.todolist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.roya.todolist.Model.Tasks;

import java.util.ArrayList;

/**
 * Created by Roya on 6/23/2017.
 */

public class TaskDBAdapter extends DatabaseAssets {

    private static final String KEY_ID = "Id";
    private static final String KEY_TITLE = "task_title";

    public TaskDBAdapter(Context c) {
        super(c);
    }

    public void insert(Tasks tasks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, tasks.getTitle());
        db.insert(TABLE_TASK, null, values);
    }

    public ArrayList<Tasks> getTasks() {
        ArrayList<Tasks> lst = new ArrayList<Tasks>();
        String selectquery = "SELECT * FROM " + TABLE_TASK;
        Tasks tasks = new Tasks();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectquery, null);


        for (; cursor.moveToNext(); ) {

            //read all data
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            tasks = new Tasks(id, title);
            lst.add(tasks);
        }
        return lst;
    }
    public void DeleteTask(String taskTitle) {
        Log.d("Error cause by: ", "open db");

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Error cause by: ", "open db");
        db.delete(TABLE_TASK, KEY_TITLE + "='" + taskTitle + "'", null);
    }


}
