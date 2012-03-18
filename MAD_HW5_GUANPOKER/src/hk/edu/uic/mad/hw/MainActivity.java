package hk.edu.uic.mad.hw;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */

	private ListView lvOpponentPlayCards;
	private ListView lvMyPlayCards;
	private ListView lvMyCards;
	
	private ArrayList<HashMap<String, Object>> listMyCards;
	
	private CardGroup cgOpponentPlayCards;
	private CardGroup cgMyPlayCards;
	private CardGroup cgMyCards;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		lvMyCards = new ListView(this);
		listMyCards = new ArrayList<HashMap<String, Object>>();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_round:
			
			return true;
		case R.id.exit:
			// showToast(this.getResources().getText(R.string.menu_save).toString());
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
}