package com.jediminer543.util.event;

public class MouseMoveEvent extends MouseEvent {
	
	protected double xpos, ypos;
	
	public double getXpos() {
		return xpos;
	}

	public double getYpos() {
		return ypos;
	}

	public MouseMoveEvent(long windowID, boolean grabbed, double xpos, double ypos) {
		super(windowID, grabbed);
		this.xpos = xpos;
		this.ypos = ypos;
	}
}
