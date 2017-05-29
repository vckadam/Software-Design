package com.vckadam.oopdesign.juckbox;

public interface Juckbox {
	void palyLibrary();
	void playSingleSong(Song song);
	void stopJuckbox();
	void addToQueue(Song song);
	void palyFromQueue();
	void playPlaylist(PlayList playList, PickType pType);
}
