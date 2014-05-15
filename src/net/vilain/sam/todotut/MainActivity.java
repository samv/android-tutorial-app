package net.vilain.sam.todotut;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    public EditText etWords;
    public TextView tvLabel;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etWords = (EditText)findViewById(R.id.etWords);
        tvLabel = (TextView)findViewById(R.id.tvLabel);
    }

    public void onSubmit(View v) {
        String fieldValue = etWords.getText().toString();
        // do something when the button is clickedEditText
        tvLabel.setText(fieldValue);
        Toast.makeText(this, fieldValue, Toast.LENGTH_SHORT).show();
    }
}
