package net.vilain.sam.todotut;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button submitButton = (Button)findViewById(R.id.btnSubmit);
        button.setOnClickListener(this);
    }

    public void onSubmit(View v) {
      // do something when the button is clicked
    }
}
