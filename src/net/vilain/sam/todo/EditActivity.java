package net.vilain.sam.todo;

import android.app.Activity;
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
    }

    public void saveItem(View v) {
        String newval = etItem.getText().toString();
        Toast.makeText(this, "You said " + newval, Toast.LENGTH_SHORT).show();
    }
}
