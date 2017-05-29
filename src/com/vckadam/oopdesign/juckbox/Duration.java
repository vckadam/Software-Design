package com.vckadam.oopdesign.juckbox;

public class Duration {
	private int hour, min, sec;
	
	public Duration(final int hh, final int mm, final int ss) {
		this.hour = hh;
		this.min = mm;
		this.sec = ss;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}
	
}
