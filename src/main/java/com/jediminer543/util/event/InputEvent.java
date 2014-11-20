package com.jediminer543.util.event;

public class InputEvent extends DisplayEvent {
	protected InputType type;
	
	public InputType getType() {
		return type;
	}

	protected enum InputType {
		Mouse,
		Keyboard,
		Joystick;
	}
}
