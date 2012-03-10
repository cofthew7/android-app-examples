package hk.edu.uic.mad.hw.model;

import java.io.Serializable;

public class Song implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String singer;
	private String duration;
	private String alubm;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getAlubm() {
		return alubm;
	}

	public void setAlubm(String alubm) {
		this.alubm = alubm;
	}

	public boolean equals(Object obj) {
		Song song = (Song)obj;
		if(song.getId() == this.id)
			return true;
		return false;
		
	}
}
