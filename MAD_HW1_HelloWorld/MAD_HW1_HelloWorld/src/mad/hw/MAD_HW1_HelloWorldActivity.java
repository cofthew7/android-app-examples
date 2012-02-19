package mad.hw;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MAD_HW1_HelloWorldActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Hello, World!");
        setContentView(tv);
        
        /*setContentView(R.layout.main);
        
        Button currentTime = (Button) findViewById(R.id.buttonCurrentTime);
        currentTime.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(date);
				
				Toast toast = Toast.makeText(MAD_HW1_HelloWorldActivity.this, currentTime , Toast.LENGTH_LONG);
				toast.show();
			}
        	
        });*/
    }
}