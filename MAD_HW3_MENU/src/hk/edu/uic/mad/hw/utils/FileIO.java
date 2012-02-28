package hk.edu.uic.mad.hw.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class FileIO {
	
	private String fileContent;
	
	public FileIO() {
		
	}
	
	public FileIO(String fileContent) {
		super();
		this.fileContent = fileContent;
	}

	public String readFileFromSDCard(String fileName) {
		File directory = Environment.getExternalStorageDirectory();
		
		// Assumes that a file article.rss is available on the SD card
		File file = new File(directory + "/" + fileName);
		if (!file.exists()) {
			// throw new RuntimeException("File not found");
			Log.d("FILEIO", "FILE NOT FOUND");
			this.writeFileToSDCard(fileName, "0");
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			fileContent = builder.toString();
			Log.d("FILEIO", "READ " + fileContent);
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
		return fileContent;
	}

	public boolean writeFileToSDCard(String fileName, String fileContent) {
		File directory = Environment.getExternalStorageDirectory();
		
		// Assumes that a file article.rss is available on the SD card
		File file = new File(directory + "/" + fileName);
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(fileContent);
			writer.flush();
			writer.close();
			Log.d("FILEIO", "Write to SD Card");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
