package com.vckadam.oopdesign.juckbox;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
	private final int playListId;
	private List<Song> songs;
	private Duration duration;
	
	public PlayList(final int id) {
		this.playListId = id;
		this.songs = new ArrayList<Song>();
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public int getPlayListId() {
		return playListId;
	}
	
	
}
