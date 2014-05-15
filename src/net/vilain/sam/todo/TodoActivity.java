package net.vilain.sam.todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;

import net.vilain.sam.todo.EditActivity;

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

        readItems();

        lvTodoItems = (ListView) findViewById(R.id.lvTodoItems);
        itemsAdapter = new ArrayAdapter<String>
            (this, android.R.layout.simple_list_item_1, items);
        lvTodoItems.setAdapter(itemsAdapter);
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
        saveItems();
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
    }

    // boy, I thought 'let' in BASIC was a lot of guff.
    // Java is really taking the cake here
    private final int REQUEST_EDIT = 0;

    private void setupListViewListener() {
        lvTodoItems.setOnItemLongClickListener
            (new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> aView,
                                               View item, int pos,
                                               long id) {
                    items.remove(pos);
                    itemsAdapter.notifyDataSetInvalidated();
                    saveItems();
                    return true;
                }
            });
        lvTodoItems.setOnItemClickListener
            (new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> aView,
                                        View item, int position,
                                        long id) {
                    Intent i = new Intent(TodoActivity.this, EditActivity.class);
                    int index = (int) id;
                    i.putExtra("item_id", index);
                    i.putExtra("item_text", items.get(index));
                    startActivityForResult(i, REQUEST_EDIT);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_EDIT) {
            String new_value = data.getExtras().getString("item_text");
            int id = data.getExtras().getInt("item_id");
            items.set(id, new_value);
            itemsAdapter.notifyDataSetInvalidated();
            saveItems();
        }
    } 
}
