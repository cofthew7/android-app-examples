package hk.edu.uic.mad.hw;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import hk.edu.hk.mad.hw.R;
import hk.edu.uic.mad.hw.model.Song;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSongActivity extends Activity {
	private List<Song> songs;
	private EditText songName;
	private EditText singer;
	private EditText duration;
	private EditText image;
	private Button cancle;
	private Button save;
	private Intent addSongIntent;
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_song);
		
		songName = (EditText)findViewById(R.id.put_song_name_edittext);
		singer = (EditText)findViewById(R.id.put_singer_edittext);
		duration = (EditText)findViewById(R.id.put_duration_edittext);
		image = (EditText)findViewById(R.id.put_image_edittext);
		
		cancle = (Button)findViewById(R.id.cancle_add_song);
		save = (Button)findViewById(R.id.save_add_song);
		
		cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(100, addSongIntent);
				finish();
			}
		});
		
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkForm()) {
					Song newSong = new Song();
					newSong.setId(songs.size() + 1);
					newSong.setTitle(songName.getText().toString());
					newSong.setSinger(singer.getText().toString());
					newSong.setDuration(duration.getText().toString());
					newSong.setAlubm(Environment.getExternalStorageDirectory() + "/sample3/" + image.getText().toString());
					songs.add(newSong);
					addSongIntent.putExtra("songs", (Serializable)songs);
					setResult(Activity.RESULT_OK, addSongIntent);
					finish();
					Log.e("INTENT", "Add " + songs.size());
				} else {
					Toast.makeText(getApplicationContext(), "Input incorrect!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		addSongIntent = getIntent();
		songs = (List<Song>)addSongIntent.getSerializableExtra("songs");
		
	}
	
	public boolean checkForm() {
		
		if (!songName.getText().toString().equals("") && !singer.getText().toString().equals("") &&
				!duration.getText().toString().equals("") && !image.getText().toString().equals("")) {
			return true;
		}
		return false;
	}
}
