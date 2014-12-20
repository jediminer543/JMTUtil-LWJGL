package com.jediminer543.util.event;

public class MouseButtonEvent extends MouseEvent{

	private int button;
	private int action;
	private int mods;
	
	public MouseButtonEvent(long windowID, int button, int action, int mods) {
		super(windowID);
		this.action = action;
		this.button = button;
		this.mods = mods;
	}

	public int getButton() {
		return button;
	}

	public int getAction() {
		return action;
	}

	public int getMods() {
		return mods;
	}
	
}
