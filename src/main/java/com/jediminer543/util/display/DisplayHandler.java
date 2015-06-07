package com.jediminer543.util.display;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;

import com.jediminer543.util.input.Keyboard;
import com.jediminer543.util.input.Mouse;

public class DisplayHandler {
	
	static long activeDisplayPos = 0;
	
	private static Map<Long, Display> displays = new HashMap<Long, Display>();
	
	private static boolean initialised = false;
	
	public static void init() {
		
		GLFW.glfwSetErrorCallback(Callbacks.errorCallbackPrint(System.err));
		
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
		registerDisplay(display);
		return pos;
	}
	
	static void registerDisplay(Display display) {
		long pos = display.getWindowID();
		displays.put(pos, display);
	}
	
	public static void setActive(long windowID) {
		activeDisplayPos = windowID;
		GLFW.glfwMakeContextCurrent(activeDisplayPos);
	}
	
	public static long getActive() {
		return activeDisplayPos;
	}
	
	public static void setMouseGrabbed(long windowID, boolean grabbed) {
		displays.get(windowID).setMouseGrabbed(grabbed);
	}
	
	public static Keyboard getDisplayKeyboard(long windowID) {
		return displays.get(windowID).getKeyboard();
	}
	
	public static Mouse getDisplayMouse(long windowID) {
		return displays.get(windowID).getMouse();
	}

}
