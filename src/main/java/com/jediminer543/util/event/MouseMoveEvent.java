package com.jediminer543.util.event;

public class MouseMoveEvent extends MouseEvent {
	
	double xpos, ypos;
	
	public MouseMoveEvent(long windowID, double xpos, double ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.windowID = windowID;
	}
}
