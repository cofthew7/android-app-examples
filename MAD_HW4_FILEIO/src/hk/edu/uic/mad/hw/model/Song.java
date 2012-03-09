package hk.edu.uic.mad.hw.model;

public class Song {
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
		String id = (String)obj;
		if(Integer.parseInt(id) == this.id)
			return true;
		return false;
		
	}
}
