package hk.edu.uic.mad.hw;

import hk.edu.hk.mad.hw.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SongsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        TextView textview = new TextView(this);
        textview.setText("This is the songs tab");
        setContentView(textview);
    }
}