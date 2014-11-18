package com.jediminer543.util.display;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.system.glfw.GLFW;

import com.jediminer543.util.input.Keyboard;

public class DisplayHandler {
	
	private static long activeDisplayPos = 0;
	
	private static Map<Long, Display> displays = new HashMap<Long, Display>();
	
	private static boolean initialised = false;
	
	public static void init() {
		//GLFW.glfwSetErrorCallback(Util.getDefault());
		
		if ( GLFW.glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
		
		initialised = true;
	}
	
	public static long makeDisplay(String title, int width, int height, long monitor, long share) {
		if (!initialised) {
			throw new IllegalArgumentException("Display handler not initialised");
		}
		Display display = new Display(title, width, height, monitor, share);
		if (display.getWindowID() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Max display cap reached");
		}
		display.init();
		long pos = display.getWindowID();
		displays.put(pos, display);
		return pos;
	}
	
	public static void setActive(long windowID) {
		activeDisplayPos = windowID;
		//GLFW.glfwMakeContextCurrent(activeDisplayPos);
	}
	
	
	
	public static Keyboard getDisplayKeyboard(long windowID) {
		
		return null;
	}

	public static abstract class DisplaySettings {
		
		public int width;
		public int height;
		
		public String title;
		
		public long monitor, share = -1L;
		
	}

}
