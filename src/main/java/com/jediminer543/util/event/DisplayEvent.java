package com.jediminer543.util.event;

public class DisplayEvent extends Event{
	protected long windowID;

	public long getWindowID() {
		return windowID;
	}
	
	public DisplayEvent(long windowID) {
		this.windowID = windowID;
	}
	
}
