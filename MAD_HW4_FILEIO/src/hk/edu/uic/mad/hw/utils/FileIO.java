package hk.edu.uic.mad.hw.utils;

import hk.edu.uic.mad.hw.model.Song;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.os.Environment;
import android.util.Log;

public class FileIO {
	
	private List<Song> songList; 
	
	public FileIO() {
		songList = new LinkedList<Song>();
	}

	public boolean isExist(String path) {
		File file = new File(path);
		
		if(file.exists()) {
			return true;
		}
		return false;
	}
	public List<Song> readFileFromSDCard(String fileName) {
		File directory = Environment.getExternalStorageDirectory();
		
		File file = new File(directory + "/sample3/" + fileName);
		if (!file.exists()) {
			Log.d("FILEIO", "FILE NOT FOUND");
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String line;
			while ((line = reader.readLine()) != null) {
				Song song = new Song();
				song.setId(Integer.parseInt(line));
				song.setTitle(reader.readLine());
				song.setSinger(reader.readLine());
				song.setDuration(reader.readLine());
				song.setAlubm(directory + "/sample3/" + reader.readLine());
				Log.d("FILEIO", song.getAlubm());
				songList.add(song);
			}
			Log.d("FILEIO", "Read Finished!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return songList;
	}

	public boolean writeFileToSDCard(String fileName, List<Song> songs) {
		File directory = Environment.getExternalStorageDirectory();
		
		File file = new File(directory + "/sample3/" + fileName);
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < songs.size(); i++) {
				writer.write(songs.get(i).getId() + "\n");
				writer.write(songs.get(i).getTitle() + "\n");
				writer.write(songs.get(i).getSinger() + "\n");
				writer.write(songs.get(i).getDuration() + "\n");
				writer.write(songs.get(i).getAlubm().substring(20) + "\n");
			}
			writer.flush();
			writer.close();
			Log.d("FILEIO", "Write Finished!");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
