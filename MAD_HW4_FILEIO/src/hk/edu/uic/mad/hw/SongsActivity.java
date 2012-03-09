package hk.edu.uic.mad.hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hk.edu.hk.mad.hw.R;
import hk.edu.uic.mad.hw.model.Song;
import hk.edu.uic.mad.hw.utils.FileIO;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;

public class SongsActivity extends ListActivity {
	
	private static String SONGS_LIST = "SongList.txt";
	
	private FileIO fileIO;
	private ListView lv;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		fileIO = new FileIO();
		lv = getListView();
		
		Resources res = getResources();
		ArrayList<HashMap<String, Object>> songList = new ArrayList<HashMap<String, Object>>();
		List<Song> songs = fileIO.readFileFromSDCard(SONGS_LIST);
		
		/* create list view dynamically */
		for (int i = 0; i < songs.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Song song = songs.get(i);
			Bitmap bm = BitmapFactory.decodeFile(song.getAlubm());
			map.put("song_id", song.getId());
			map.put("album", bm);
			map.put("song_name", song.getTitle());
			map.put("singer", song.getSinger());
			map.put("duration", song.getDuration());
			songList.add(map);
		}
		
		SimpleAdapter songListAdapter = new SimpleAdapter(this, songList, R.layout.songs, 
				new String[]{"song_id", "album", "song_name", "singer", "duration"},
				new int[]{R.id.song_id, R.id.album, R.id.song_name, R.id.singer, R.id.duration});
		
		songListAdapter.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Object data, String textRepresentation) {
				if (view instanceof ImageView && data instanceof Bitmap) {
					ImageView iv = (ImageView) view;
					iv.setImageBitmap((Bitmap) data);
					return true;
				} else {
					return false;
				}
			}
		});
		
		/* OnItemClickListener for items in the list view */
		OnItemClickListener listener = new OnItemClickListener (){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//Toast.makeText(getApplicationContext(), arg1.getTag().toString(), Toast.LENGTH_SHORT).show();
				Dialog dialog = new AlertDialog.Builder(SongsActivity.this)
				.setIcon(R.drawable.delete)
				.setTitle(SongsActivity.this.getResources().getText(R.string.dialog_title_song).toString())
				.setMessage(SongsActivity.this.getResources().getText(R.string.dialog_message_song).toString())
				.setPositiveButton(R.string.dialog_positive, 
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Toast.makeText(getApplicationContext(),
										"You clicked on " + getResources().getText(R.string.dialog_positive), Toast.LENGTH_SHORT).show();
							}
						})
				.setNegativeButton(R.string.dialog_negative,
						new DialogInterface.OnClickListener() {
					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getApplicationContext(),
									"You clicked on " + getResources().getText(R.string.dialog_negative), Toast.LENGTH_SHORT).show();
						}
					})
				.create();
				dialog.show();
			}
		};
		
		lv.setAdapter(songListAdapter);
		lv.setOnItemClickListener(listener);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.option_menu_song, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.add:
	            //showToast(this.getResources().getText(R.string.menu_bookmark).toString());
	            return true;
	        case R.id.delete_all:
	        	//showToast(this.getResources().getText(R.string.menu_save).toString());
	            return true;
	        case R.id.add_all:
	        	//showToast(this.getResources().getText(R.string.menu_search).toString());
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onResume () {
		super.onResume();
	}
	
}