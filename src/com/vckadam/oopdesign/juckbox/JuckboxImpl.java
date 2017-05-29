package com.vckadam.oopdesign.juckbox;

import java.util.LinkedList;
import java.util.Queue;

public class JuckboxImpl implements Juckbox {

	private Library lib;
	PickType pickType;
	Queue<Song> queue;
	private boolean stop;
	
	public JuckboxImpl() {
		lib = Library.getInstance();
		pickType = PickType.QUEUE;
		this.queue = new LinkedList<Song>();
		this.stop = false;
	}
	@Override
	public void palyLibrary() {
		this.stop = false;
		for(Song song : lib.getAllSongs()) {
			if(this.stop) break;
			System.out.println(song.getSongTitle());
		}
	}

	@Override
	public void playSingleSong(Song song) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addToQueue(Song song) {
		// TODO Auto-generated method stub

	}

	@Override
	public void palyFromQueue() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playPlaylist(PlayList playList, PickType pType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stopJuckbox() {
		this.stop = true;		
	}

}
