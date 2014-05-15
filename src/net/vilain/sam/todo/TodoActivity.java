package net.vilain.sam.todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;

public class TodoActivity extends Activity
{
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvTodoItems;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        lvTodoItems = (ListView) findViewById(R.id.lvTodoItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>
            (this, android.R.layout.simple_list_item_1, items);
        lvTodoItems.setAdapter(itemsAdapter);
        items.add("Write an app");
        items.add("?");
        items.add("Profit");
        setupListViewListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    public void addNewTodoItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        itemsAdapter.add(etNewItem.getText().toString());
        etNewItem.setText("");
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
    }

    private void setupListViewListener() {
        lvTodoItems.setOnItemLongClickListener
            (new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> aView,
                                               View item, int pos,
                                               long id) {
                    items.remove(pos);
                    itemsAdapter.notifyDataSetInvalidated();
                    return true;
                }
            });
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        }
        catch (IOException e) {
            items = new ArrayList<String>();
            e.printStackTrace();
        }
    }

    private void saveItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
