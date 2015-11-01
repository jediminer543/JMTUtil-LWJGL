package com.jediminer543.util.event;

public class MouseEvent extends InputEvent {

	protected boolean grabbed;
	
	public MouseEvent(long windowID, boolean grabbed) {
		super(windowID);
		this.grabbed = grabbed;
	}

	public boolean isGrabbed() {
	
		return grabbed;
	}

}
