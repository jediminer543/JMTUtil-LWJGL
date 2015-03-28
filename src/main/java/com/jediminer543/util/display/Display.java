package com.jediminer543.util.display;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.glfw.*;

import com.jediminer543.util.display.Display.DisplayCallbacks.DisplayCursorPosCallback;
import com.jediminer543.util.display.Display.DisplayCallbacks.DisplayKeyCallback;
import com.jediminer543.util.display.Display.DisplayCallbacks.DisplayMouseButtonCallback;
import com.jediminer543.util.event.InputEvent;
import com.jediminer543.util.event.KeyEvent;
import com.jediminer543.util.event.MouseButtonEvent;
import com.jediminer543.util.event.MouseMoveEvent;
import com.jediminer543.util.event.annotation.Input;
import com.jediminer543.util.event.bus.InputBus;
import com.jediminer543.util.handlers.time.Tickable;
import com.jediminer543.util.input.Keyboard;
import com.jediminer543.util.input.Mouse;

public class Display implements Tickable
{
	private long windowID;
	
	//@SuppressWarnings("unused")
	Keyboard keyboard;
	//@SuppressWarnings("unused")
	Mouse mouse;
	
	private boolean mouseGrabbed = true;
	
	DisplayCallbacks callbacks = new DisplayCallbacks();
	private DisplayCursorPosCallback cpcallback = callbacks.new DisplayCursorPosCallback();
	private DisplayKeyCallback keycallback = callbacks.new DisplayKeyCallback();
	private DisplayMouseButtonCallback mbcallback = callbacks.new DisplayMouseButtonCallback();
	
	public long getWindowID() {
		return windowID;
	}

	public int height;
	public int width;
	
	public String title;
	
	public long monitor, share = 0L;
	
	public boolean useCloseKey = true;
	public int closeKey = Keyboard.KEY_ESCAPE;
	
	 
	
	@Input
	public void onInput(InputEvent ie) {
		if (ie instanceof KeyEvent) {
			KeyEvent ke = (KeyEvent) ie;
			if (ke.getWindowID() == this.getWindowID() && ke.getKey() == closeKey && useCloseKey) {
				glfwSetWindowShouldClose(windowID, GL_TRUE);
			}
		}
	}
	
	public Display(String title, int width, int height, long monitor, long share) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.monitor = monitor;
		this.share = share;
		InputBus.register(this);
	}
	
	public void init() {
		glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        
		windowID = glfwCreateWindow(width, height, CharBuffer.wrap(title), monitor, share);
		
		Callbacks.glfwSetCallback(windowID, cpcallback);
		Callbacks.glfwSetCallback(windowID, keycallback);
		Callbacks.glfwSetCallback(windowID, mbcallback);
		//TODO ALL THE CALLBACKS
		
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
            windowID,
            (GLFWvidmode.width(vidmode) - width) / 2,
            (GLFWvidmode.height(vidmode) - height) / 2
        );
        
        glfwMakeContextCurrent(windowID);
        
		glfwSwapInterval(1);
        glfwShowWindow(windowID);
		keyboard = new Keyboard(windowID);
		mouse = new Mouse(windowID);
	}
	
	public boolean shouldClose() {
		if (glfwWindowShouldClose(getWindowID()) == GL11.GL_FALSE)
			return false;
		else
			return true;
	}
	
	public double getCenterX() {
		return width / 2;
	}
	
	public double getCenterY() {
		return height / 2;
	}
	
	class DisplayCallbacks {
	public class DisplayCharCallback extends GLFWCharCallback {
		@Override
		public void invoke(long window, int character) {
			
		}
	}
	
	public class DisplayCharModsCallback extends GLFWCharModsCallback {
		@Override
		public void invoke(long window, int arg1, int arg2) {
			
		}
	}
	
	public class DisplayCursorEnterCallback extends GLFWCursorEnterCallback {
		@Override
		public void invoke(long window, int arg1) {
			//TODO
		}
	}
	
	public class DisplayCursorPosCallback extends GLFWCursorPosCallback {
		@Override
		public void invoke(long window, double xpos, double ypos) {
			if (mouseGrabbed) {
				InputBus.invoke(new MouseMoveEvent(window, xpos - getCenterX(), ypos - getCenterY()));
				glfwSetCursorPos(windowID, getCenterX(), getCenterY());
			}
			else {
				InputBus.invoke(new MouseMoveEvent(window, xpos, ypos));
			}
		}
	}
	
	public class DisplayDropCallback extends GLFWDropCallback {
		@Override
		public void invoke(long window, int arg1, long arg2) {
			//TODO
		}
	}
	
	public class DisplayFramebufferSizeCallback extends GLFWFramebufferSizeCallback {
		@Override
		public void invoke(long window, int arg1, int arg2) {
			//TODO
		}
	}
	
	public class DisplayKeyCallback extends GLFWKeyCallback {
		@Override
		public void invoke(long window, int key, int scancode, int action, int mods) {
			InputBus.invoke(new KeyEvent(window, key, scancode, action, mods));
		}
	}
	
	public class DisplayMouseButtonCallback extends GLFWMouseButtonCallback {
		@Override
		public void invoke(long window, int button, int action, int mods) {
			InputBus.invoke(new MouseButtonEvent(window, button, action, mods));
		}
	}
	
	public class DisplayScrollCallback extends GLFWScrollCallback {
		@Override
		public void invoke(long window, double wheelx, double wheely) {
			//TODO Mouse Event Xscroll/YScroll
		}
	}

	public class DisplayWindowCloseCallback extends GLFWWindowCloseCallback {
		@Override
		public void invoke(long arg0) {
			//TODO
		}
	}
	
	public class DisplayWindowFocusCallback extends GLFWWindowFocusCallback {
		@Override
		public void invoke(long arg0, int arg1) {
			//TODO
		}
	}

	public class DisplayWindowIconifyCallback extends GLFWWindowIconifyCallback {
		@Override
		public void invoke(long arg0, int arg1) {
			
		}
	}
	
	public class DisplayWindowPosCallback extends GLFWWindowPosCallback {
		@Override
		public void invoke(long arg0, int arg1, int arg2) {
			// TODO
		}
	}
	
	public class DisplayWindowRefreshCallback extends GLFWWindowRefreshCallback {
		@Override
		public void invoke(long arg0) {
			//TODO
		}
	}
	
	public class DisplayWindowSizeCallback extends GLFWWindowSizeCallback {

		@Override
		public void invoke(long arg0, int arg1, int arg2) {
			//TODO
		}
		
	}
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}
	
	
}
