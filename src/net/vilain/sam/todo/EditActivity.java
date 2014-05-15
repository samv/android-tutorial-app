package net.vilain.sam.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity
{
    EditText etItem;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etItem = (EditText) findViewById(R.id.etItem);
        String todoText = getIntent().getStringExtra("item_text");
        etItem.setText(todoText);
    }

    public void saveItem(View v) {
        String newval = etItem.getText().toString();
        Intent edited = new Intent();
        edited.putExtra("item_text", newval);
        edited.putExtra("item_id", getIntent().getIntExtra("item_id", 0));
        setResult(RESULT_OK, edited);
        this.finish();
    }
}
