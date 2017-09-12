package com.example.roya.todolist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.roya.todolist.Database.TaskDBAdapter;
import com.example.roya.todolist.Fragment.TasksFragment;
import com.example.roya.todolist.Model.Tasks;

import java.util.List;

/**
 * Created by Roya on 6/23/2017.
 */

public class TaskListAdapter extends ArrayAdapter<Tasks> {

    Context mContext;
    TextView txtview;
    Viewholder viewholder = null;
    View view;
    int pos;
    TaskDBAdapter taskDBAdapter;
    Tasks tasks;

    public TaskListAdapter(Context context, int resource, List<Tasks> items) {
        super(context, resource, items);

        this.mContext = context;
        taskDBAdapter = new TaskDBAdapter(context);
        tasks =new Tasks();


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        view = convertView;
        pos = position;
        Tasks rowItem = getItem(pos);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_todo, null);
            viewholder = new Viewholder();
            viewholder.txt_task = (TextView) convertView.findViewById(R.id.task_title);
            viewholder.delete_btn = (Button) convertView.findViewById(R.id.task_delete);

            convertView.setTag(viewholder);

        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        viewholder.txt_task.setText(rowItem.getTitle());

        viewholder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = (View) v.getParent();
                TextView task = (TextView) parent.findViewById(R.id.task_title);
                String txttask = String.valueOf(task.getText());
                taskDBAdapter.DeleteTask(txttask);
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().add(R.id.container, new TasksFragment()).commit();


            }
        });


        return convertView;
    }


    private class Viewholder {

        TextView txt_task;
        Button delete_btn;
        RelativeLayout relative;

    }


}
