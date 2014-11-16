package com.jediminer543.util.display;

import org.lwjgl.system.glfw.GLFW;

import com.jediminer543.util.handlers.time.Tickable;
import com.jediminer543.util.input.Keyboard;
import com.jediminer543.util.input.Mouse;

public class Display implements Tickable
{
	private long windowID;
	
	private Keyboard keyboard;
	private Mouse mouse;
	
	public long getWindowID() {
		return windowID;
	}

	public int height;
	public int width;
	
	public String title;
	
	public long monitor, share;
	
	public Display(String title) {
		this(title, 800, 600);
	}
	
	@SuppressWarnings("null")
	public Display(String title, int width, int height) {
		this(title, 800, 600, (Long) null);
	}
	
	@SuppressWarnings("null")
	public Display(String title, int width, int height, long monitor) {
		this(title, 800, 600, monitor, (Long) null);
	}
	
	public Display(String title, int width, int height, long monitor, long share) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.monitor = monitor;
		this.share = share;
	}
	
	public void init() {
		windowID = GLFW.glfwCreateWindow(width, height, title, monitor, share);
		keyboard = new Keyboard(windowID);
	}
	
	public void makeActive() {
		GLFW.glfwMakeContextCurrent(windowID);
	}

	public void tick() {
		this.keyboard.Poll();
		this.mouse.Poll();
	}
	
	
}
