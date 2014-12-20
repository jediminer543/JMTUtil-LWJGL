package com.jediminer543.util.event;

public class KeyEvent extends InputEvent {

	protected int key, scancode, action, mods;
	
	public int getKey() {
		return key;
	}

	public int getScancode() {
		return scancode;
	}

	public int getAction() {
		return action;
	}

	public int getMods() {
		return mods;
	}

	public KeyEvent(long windowID, int key, int scancode, int action, int mods) {
		super(windowID);
		this.type = InputType.Keyboard;
		this.key = key;
		this.scancode = scancode;
		this.action = action;
		this.mods = mods;
	}
}
