package hk.edu.uic.mad.hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hk.edu.hk.mad.hw.R;
import hk.edu.uic.mad.hw.model.Song;
import hk.edu.uic.mad.hw.utils.FileIO;
import android.app.ListActivity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

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
		
		for (int i = 0; i < songs.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Song song = songs.get(i);
			Bitmap bm = BitmapFactory.decodeFile(song.getAlubm());
			map.put("album", bm);
			map.put("song_name", song.getTitle());
			map.put("singer", song.getSinger());
			map.put("duration", song.getDuration());
			songList.add(map);
		}
		
		SimpleAdapter songListAdapter = new SimpleAdapter(this, songList, R.layout.songs, 
				new String[]{"album", "song_name", "singer", "duration"},
				new int[]{R.id.album, R.id.song_name, R.id.singer, R.id.duration});
		
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
		
		lv.setAdapter(songListAdapter);
		
	}
	
	@Override
	public void onResume () {
		super.onResume();
	}
	
}