package hk.edu.uic.mad.hw;

import java.io.Serializable;
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
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
	private ArrayList<HashMap<String, Object>> songList;
	private SimpleAdapter songListAdapter;
	private List<Song> songs;
	private boolean isModified;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		fileIO = new FileIO();
		lv = getListView();
		isModified = false;
		songList = new ArrayList<HashMap<String, Object>>();
		
		
		/* create list view dynamically */
		songs = fileIO.readFileFromSDCard(SONGS_LIST);
		
		for (int i = 0; i < songs.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Song song = songs.get(i);
			
			Bitmap bm;
			if(fileIO.isExist(song.getAlubm())) {
				bm = BitmapFactory.decodeFile(song.getAlubm());
			} else {
				Drawable d = this.getResources().getDrawable(R.drawable.no_image);
				bm = ((BitmapDrawable)d).getBitmap();
			}
			
			map.put("song_id", song.getId());
			map.put("album", bm);
			map.put("song_name", song.getTitle());
			map.put("singer", song.getSinger());
			map.put("duration", song.getDuration());
			songList.add(map);
		}
		
		songListAdapter = new SimpleAdapter(this, songList, R.layout.songs, 
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
			HashMap<String, Object> map;
			int selectedItemId;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				map = (HashMap<String, Object>)lv.getItemAtPosition(arg2);
				selectedItemId = arg2;
				Dialog dialog = new AlertDialog.Builder(SongsActivity.this)
				.setIcon(R.drawable.delete)
				.setTitle(SongsActivity.this.getResources().getText(R.string.dialog_title_song).toString())
				.setMessage(SongsActivity.this.getResources().getText(R.string.dialog_message_song).toString())
				.setPositiveButton(R.string.dialog_positive, 
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								Song song = new Song();
								song.setId(Integer.parseInt(map.get("song_id").toString()));
								Log.d("FILEIO", "" + songs.remove(song));
								songList.remove(selectedItemId);
								songListAdapter.notifyDataSetChanged();
								lv.invalidate();
								isModified = true;
								Toast.makeText(getApplicationContext(), "Delete " + map.get("song_id")+ " " +  map.get("song_name"), Toast.LENGTH_SHORT).show();
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
	        	Log.d("MENU", "add");
	        	Log.d("INTENT", "before add " + songs.size());
	        	Intent addSongIntent = new Intent(SongsActivity.this, AddSongActivity.class);
	        	addSongIntent.putExtra("songs", (Serializable)songs);
	        	startActivityForResult(addSongIntent, 0);
	        	
	            return true;
	        case R.id.delete_all:
	        	Log.d("MENU", "delete all");
	        	songs.clear();
	        	songList.clear();
	        	songListAdapter.notifyDataSetChanged();
				lv.invalidate();
				isModified = true;
	            return true;
	        case R.id.add_all:
	        	Log.d("MENU", "add all");
	        	songs.clear();
	        	songs = fileIO.readFileFromSDCard(SONGS_LIST);
	    		
	    		for (int i = 0; i < songs.size(); i++) {
	    			HashMap<String, Object> map = new HashMap<String, Object>();
	    			Song song = songs.get(i);
	    			Bitmap bm;
	    			if(fileIO.isExist(song.getAlubm())) {
	    				bm = BitmapFactory.decodeFile(song.getAlubm());
	    			} else {
	    				Drawable d = this.getResources().getDrawable(R.drawable.no_image);
	    				bm = ((BitmapDrawable)d).getBitmap();
	    			}	    			
	    			map.put("song_id", song.getId());
	    			map.put("album", bm);
	    			map.put("song_name", song.getTitle());
	    			map.put("singer", song.getSinger());
	    			map.put("duration", song.getDuration());
	    			songList.add(map);
	    		}
	    		songListAdapter.notifyDataSetChanged();
				lv.invalidate();
				isModified = true;
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	public void onPause() {
		
		if (isModified) {
			Log.d("FILEIO", "song size " + songs.size());
			fileIO.writeFileToSDCard(SONGS_LIST, songs);
			isModified = false;
		}
		super.onPause();
	}
	@Override
	public void onResume () {
		super.onResume();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(resultCode) {
		case RESULT_OK:
			isModified = true;
			songs = (List<Song>) data.getSerializableExtra("songs");
			HashMap<String, Object> map = new HashMap<String, Object>();
			Song song = songs.get(songs.size() - 1);
			Bitmap bm;
			if(fileIO.isExist(song.getAlubm())) {
				bm = BitmapFactory.decodeFile(song.getAlubm());
			} else {
				Drawable d = this.getResources().getDrawable(R.drawable.no_image);
				bm = ((BitmapDrawable)d).getBitmap();
			}
			map.put("song_id", song.getId());
			map.put("album", bm);
			map.put("song_name", song.getTitle());
			map.put("singer", song.getSinger());
			map.put("duration", song.getDuration());
			songList.add(map);
			songListAdapter.notifyDataSetChanged();
			lv.invalidate();
			break;
		default:
			break;
		}
	}
}