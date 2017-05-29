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
		System.out.println(song.getSongTitle());

	}

	@Override
	public void addToQueue(Song song) {
		this.queue.add(song);

	}

	@Override
	public void palyFromQueue() {
		this.stop = false;
		while(!this.stop && !queue.isEmpty()) {
			System.out.println(queue.remove().getSongTitle());
		}
	}

	@Override
	public void playPlaylist(PlayList playList, PickType pType) {
		if(pType == PickType.QUEUE) {
			for(Song song : playList.getSongs()) {
				System.out.println(song.getSongTitle());
			}
		} else if (pType == PickType.RANDOM) {
			// write code for this
		} else {
			for(int i = playList.getSongs().size()-1; i >= 0; i--) {
				System.out.println(playList.getSongs().get(i).getSongTitle());
			}
		}
		
	}
	@Override
	public void stopJuckbox() {
		this.stop = true;		
	}

}
