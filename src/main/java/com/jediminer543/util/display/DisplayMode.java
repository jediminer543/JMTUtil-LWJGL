package com.jediminer543.util.display;


public class DisplayMode
{
	/**
	 * Width of the display being described
	 */
	int width;
	
	/**
	 * Height of the display being described
	 */
	int height;
	
	/**
	 * Monitor to fullscreen on
	 */
	long monitor; 
	
	/**
	 * Display to share context with
	 */
	long share;
	
	/**
	 * Enable vsync
	 */
	boolean vsync;
	
	public DisplayMode(int width, int height) {
		this(width, height, 0, 0);
	}
	
	public DisplayMode(int width, int height, long monitor, long share) {
		this(width, height, monitor, share, false);
	}
	
	public DisplayMode(int width, int height, long monitor, long share, boolean vsync) {
		this.width = width;
		this.height = height;
		this.monitor = monitor;
		this.share = share;
		this.vsync = vsync;
	}
}
