package net.vilain.sam.todotut;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity
{
    public EditText etWords;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etWords = (EditText)findViewById(R.id.etWords);
    }

    public void onSubmit(View v) {
        String fieldValue = etWords.getText().toString();
      // do something when the button is clicked
        Toast.makeText(this, fieldValue, Toast.LENGTH_SHORT).show();
    }
}
