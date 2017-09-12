package com.example.roya.todolist.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.roya.todolist.Database.TaskDBAdapter;
import com.example.roya.todolist.Model.Tasks;
import com.example.roya.todolist.R;
import com.example.roya.todolist.TaskListAdapter;

import java.util.ArrayList;

/**
 * Created by Roya on 6/23/2017.
 */

public class TasksFragment extends Fragment {

    ListView lst_data;
    TaskDBAdapter taskDBAdapter;
    Tasks tasks = new Tasks();

    public TasksFragment() {
        // Required empty public constructor
    }


    private View RootView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RootView = inflater.inflate(R.layout.fragment_tasks, container, false);

        lst_data = (ListView) RootView.findViewById(R.id.lst_data);


        taskDBAdapter = new TaskDBAdapter(getActivity());

        FillData();


        return RootView;


    }



  /*  @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Context thisContext = view.getContext();
        lst_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button button = (Button) RootView.findViewById(R.id.task_delete);
                button.setTag(position);
                Log.d("Error cause by: ", String.valueOf(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView = (TextView) RootView.findViewById(R.id.task_title);
                        String tasktxt = String.valueOf(textView.getText());
                        Log.d("Error cause by: ", tasktxt);
                        taskDBAdapter.DeleteTask(tasktxt);
                    }
                });
            }
        });


    }*/

    private void FillData() {
        ArrayList<Tasks> tasksArrayList = taskDBAdapter.getTasks();

        TaskListAdapter taskListAdapter = new TaskListAdapter(getActivity(), R.layout.item_todo, tasksArrayList);


        lst_data.setAdapter(taskListAdapter);
    }
  /*  public void DeleteTask(View view){
        View parent = (View) view.getParent();
        TextView textView = (TextView) parent.findViewById(R.id.task_delete);
        String tasktxt = String.valueOf(textView.getText());
        Log.d("Error cause by: ", tasktxt);
        taskDBAdapter.DeleteTask(tasktxt);

    }*/


}
