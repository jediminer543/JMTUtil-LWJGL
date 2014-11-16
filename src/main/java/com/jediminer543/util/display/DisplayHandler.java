package com.jediminer543.util.display;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.system.glfw.ErrorCallback;
import org.lwjgl.system.glfw.GLFW;

import com.jediminer543.util.input.Keyboard;

public class DisplayHandler {
	
	@SuppressWarnings("unused")
	private int activeDisplayPos = 0;
	
	private ArrayList<Display> displays = new ArrayList<Display>();
	
	private boolean initialised = false;
	
	public void init() {
		GLFW.glfwSetErrorCallback(ErrorCallback.Util.getDefault());
		
		if ( GLFW.glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
		
		initialised = true;
		
		for (Display display:displays) {
			display.init();
		}
	}
	
	public long makeDisplay(DisplaySettings displaySettings) {
		int pos = displays.size();
		displays.add(pos, new Display(displaySettings.title, displaySettings.width, displaySettings.height, displaySettings.monitor, displaySettings.share));
		if (initialised) {
			displays.get(pos).init();
		}
		return displays.get(pos).getWindowID();
	}
	
	public Keyboard getDisplayKeyboard(long windowID) {
		
		return null;
	}

	
	public abstract class DisplaySettings {
		
		public int width;
		public int height;
		
		public String title;
		
		@SuppressWarnings("null")
		public long monitor, share = (Long) null;
		
	}

}
