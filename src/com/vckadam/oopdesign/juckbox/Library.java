package com.vckadam.oopdesign.juckbox;

import java.util.HashSet;
import java.util.Set;

public class Library {
	private static Library lib = new Library();
	private Set<Song> allSongs;
	private Library() { 
		this.allSongs = new HashSet<Song>();
	}
	public static Library getInstance() {
		return lib;
	}
	public void addSong(final Song song) {
		lib.allSongs.add(song);
	}
	public void removeSong(final Song song) {
		lib.allSongs.remove(song);
	}	
	public Set<Song> getAllSongs() {
		return this.allSongs;
	}
}
