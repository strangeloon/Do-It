package com.example.anew;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import android.util.SparseBooleanArray;

public class MainActivity extends AppCompatActivity {

    private EditText taskEditText;
    private Button addButton, removeButton;
    private ListView tasksListView;
    private ArrayList<String> tasksList;
    private ArrayAdapter<String> tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskEditText = findViewById(R.id.taskEditText);
        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.removeButton);
        tasksListView = findViewById(R.id.tasksListView);
        tasksList = new ArrayList<String>();

        tasksAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                tasksList
        );
        tasksListView.setAdapter(tasksAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskEditText.getText().toString();
                if (!task.equals("")) {
                    tasksAdapter.add(task);
                    taskEditText.setText("");
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray selectedItems = tasksListView.getCheckedItemPositions();
                int count = selectedItems.size();
                for (int i = count - 1; i >= 0; i--) {
                    if (selectedItems.valueAt(i)) {
                        int position = selectedItems.keyAt(i);
                        tasksList.remove(position);
                    }
                }
                tasksAdapter.notifyDataSetChanged();
                tasksListView.clearChoices();
                removeButton.setEnabled(false);
            }
        });


        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeButton.setEnabled(true);
            }
        });
    }
}
