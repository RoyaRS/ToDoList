package com.example.roya.todolist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.roya.todolist.Database.DatabaseAssets;
import com.example.roya.todolist.Database.TaskDBAdapter;
import com.example.roya.todolist.Fragment.TasksFragment;
import com.example.roya.todolist.Model.Tasks;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    DatabaseAssets mydatabase;
    Tasks tasks = new Tasks();
    TaskDBAdapter taskDBAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydatabase = new DatabaseAssets(getApplicationContext());
        try {
            mydatabase.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mydatabase.openDataBase();


        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().add(R.id.container, new TasksFragment()).commit();


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_task:
                final EditText editText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(editText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(editText.getText());
                                tasks = new Tasks();
                                tasks.setTitle(task);
                                taskDBAdapter = new TaskDBAdapter(getApplication());
                                taskDBAdapter.insert(tasks);
                                Log.d(TAG, "Task to add: " + task);
                                getSupportFragmentManager().beginTransaction().add(R.id.container, new TasksFragment()).commit();

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
