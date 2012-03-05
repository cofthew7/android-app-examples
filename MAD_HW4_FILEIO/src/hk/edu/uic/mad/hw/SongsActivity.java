package hk.edu.uic.mad.hw;

import hk.edu.hk.mad.hw.R;
import hk.edu.uic.mad.hw.utils.FileIO;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SongsActivity extends ListActivity {
	
	private static String SETTINGS_FILE = "settings.txt";
	
	private FileIO fileIO;
	private ListView lv;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] songs = getResources().getStringArray(R.array.songs_array);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.songs, songs));

		fileIO = new FileIO();
		lv = getListView();
		lv.setDividerHeight(Integer.parseInt(fileIO.readFileFromSDCard(SETTINGS_FILE)));
		this.registerForContextMenu(lv);
	}
	
	@Override
	public void onResume () {
		super.onResume();
		lv.setDividerHeight(Integer.parseInt(fileIO.readFileFromSDCard(SETTINGS_FILE)));
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.context_menu, menu);
	}
	
	@Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
        case R.id.pix2:
            fileIO.writeFileToSDCard(SETTINGS_FILE, "2");
            lv.setDividerHeight(2);
            return true;
        case R.id.pix8:
	        fileIO.writeFileToSDCard(SETTINGS_FILE, "8");
	        lv.setDividerHeight(8);
        	return true;
        case R.id.pix16:
        	fileIO.writeFileToSDCard(SETTINGS_FILE, "16");
	        lv.setDividerHeight(16);
        default:
            return super.onContextItemSelected(item);
        }
    }
}