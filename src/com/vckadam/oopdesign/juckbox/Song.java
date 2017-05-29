package com.vckadam.oopdesign.juckbox;

public class Song {
	private final int songId;
	private final String songTitle;
	private String songArtist;
	private Duration duration;
	
	public Song(final int id, String title) {
		this.songId = id;
		this.songTitle = title;
	}

	public String getSongArtist() {
		return songArtist;
	}

	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public int getSongId() {
		return songId;
	}

	public String getSongTitle() {
		return songTitle;
	}
	
	
}
