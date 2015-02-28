package com.jediminer543.util.display;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.glfw.GLFWCharModsCallback;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMonitorCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowIconifyCallback;
import org.lwjgl.glfw.GLFWWindowPosCallback;
import org.lwjgl.glfw.GLFWWindowRefreshCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.glfw.Callbacks;

import com.jediminer543.util.event.InputEvent;
import com.jediminer543.util.event.KeyEvent;
import com.jediminer543.util.event.MouseButtonEvent;
import com.jediminer543.util.event.MouseMoveEvent;
import com.jediminer543.util.event.annotation.Input;
import com.jediminer543.util.event.bus.InputBus;
import com.jediminer543.util.handlers.time.Tickable;
import com.jediminer543.util.input.Keyboard;
import com.jediminer543.util.input.Mouse;

public class Display implements Tickable {
	private long windowID;

	private Keyboard keyboard;
	private Mouse mouse;

	private boolean mouseGrabbed = true;

	//private DisplayCharCallback charCallback = new DisplayCharCallback();
	//private DisplayCharModsCallback charModsCallback = new DisplayCharModsCallback();
	//private DisplayCursorEnterCallback cursorEnterCallback = new DisplayCursorEnterCallback();
	private DisplayCursorPosCallback cursorPosCallback = new DisplayCursorPosCallback();
	//private DisplayDropCallback dropCallback = new DisplayDropCallback();
	//private DisplayFramebufferSizeCallback framebufferSizeCallback = new DisplayFramebufferSizeCallback();
	private DisplayKeyCallback keyCallback = new DisplayKeyCallback();
	private DisplayMouseButtonCallback mouseButtonCallback = new DisplayMouseButtonCallback();
	//private DisplayScrollCallback scrollCallback = new DisplayScrollCallback();
	//private DisplayWindowCloseCallback windowCloseCallback = new DisplayWindowCloseCallback();
	//private DisplayWindowFocusCallback windowFocusCallback = new DisplayWindowFocusCallback();
	//private DisplayWindowIconifyCallback windowIconifyCallback = new DisplayWindowIconifyCallback();
	//private DisplayWindowPosCallback windowPosCallback = new DisplayWindowPosCallback();
	//private DisplayWindowRefreshCallback windowRefreshCallback = new DisplayWindowRefreshCallback();
	private DisplayWindowSizeCallback windowSizeCallback = new DisplayWindowSizeCallback();

	public long getWindowID() {
		return windowID;
	}

	public int height;
	public int width;

	public String title;

	public long monitor, share = 0L;

	@Input
	public void onInput(InputEvent ie) {
		if (ie instanceof KeyEvent) {
			KeyEvent ke = (KeyEvent) ie;
			if (ke.getWindowID() == this.getWindowID()
					&& ke.getKey() == Keyboard.KEY_ESCAPE) {
				glfwSetWindowShouldClose(windowID, GL_TRUE);
			}
		}
	}

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
		InputBus.register(this);
	}

	public void init() {
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

		windowID = glfwCreateWindow(width, height, CharBuffer.wrap(title),
				monitor, share);

		// Callbacks.(windowID, callback);
		registerCallbacks();

		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(windowID, (GLFWvidmode.width(vidmode) - width) / 2,
				(GLFWvidmode.height(vidmode) - height) / 2);

		glfwMakeContextCurrent(windowID);

		glfwSwapInterval(1);
		glfwShowWindow(windowID);
		keyboard = new Keyboard(windowID);
		mouse = new Mouse(windowID);
	}
	
	public void registerCallbacks() {
		Callbacks.glfwSetCallback(windowID, cursorPosCallback);
		Callbacks.glfwSetCallback(windowID, keyCallback);
		Callbacks.glfwSetCallback(windowID, mouseButtonCallback);
		Callbacks.glfwSetCallback(windowID, windowSizeCallback);
	}

	public void tick() {
		
	}

	/*
	 * public class DisplayCallback extends Callbacks {
	 * 
	 * 
	 * 
	 * @Override public void charMods(long arg0, int arg1, int arg2) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void character(long arg0, int arg1) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void cursorEnter(long arg0, int arg1) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void cursorPos(long window, double xpos, double ypos) {
	 * 
	 * 
	 * }
	 * 
	 * @Override public void drop(long arg0, int arg1, long arg2) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void framebufferSize(long arg0, int arg1, int arg2) { //
	 * TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void key(long window, int key, int scancode, int action,
	 * int mods) { InputBus.invoke(new KeyEvent(window, key, scancode, action,
	 * mods));
	 * 
	 * 
	 * }
	 * 
	 * @Override public void mouseButton(long window, int button, int action,
	 * int mods) { InputBus.invoke(new MouseButtonEvent(window, button, action,
	 * mods));
	 * 
	 * }
	 * 
	 * @Override public void scroll(long window, double wheelx, double wheely) {
	 * //TODO:mouseEventUpdate(); }
	 * 
	 * @Override public void windowClose(long arg0) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void windowFocus(long arg0, int arg1) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void windowIconify(long arg0, int arg1) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void windowPos(long arg0, int arg1, int arg2) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void windowRefresh(long arg0) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void windowSize(long arg0, int arg1, int arg2) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * }
	 */


		/**
		 * Required for mouse centralisation
		 */
		double centerX = width / 2;
		double centerY = height / 2;

		class DisplayCharCallback extends GLFWCharCallback {

			@Override
			public void invoke(long window, int codepoint) {

			}

		}

		class DisplayCharModsCallback extends GLFWCharModsCallback {

			@Override
			public void invoke(long window, int codepoint, int mods) {

			}
		}

		class DisplayCursorEnterCallback extends GLFWCursorEnterCallback {

			@Override
			public void invoke(long window, int entered) {

			}

		}

		class DisplayCursorPosCallback extends GLFWCursorPosCallback {

			@Override
			public void invoke(long window, double xpos, double ypos) {

				if (mouseGrabbed) {
					InputBus.invoke(new MouseMoveEvent(window, xpos - centerX,
							ypos - centerY));
					glfwSetCursorPos(windowID, centerX, centerY);
				}

			}
		}

		class DisplayDropCallback extends GLFWDropCallback {

			@Override
			public void invoke(long window, int count, long names) {

			}

		}

		class DisplayFramebufferSizeCallback extends
				GLFWFramebufferSizeCallback {

			@Override
			public void invoke(long window, int width, int height) {

			}
		}

		class DisplayKeyCallback extends GLFWKeyCallback {

			@Override
			public void invoke(long window, int key, int scancode, int action,
					int mods) {
				InputBus.invoke(new KeyEvent(window, key, scancode, action,
						mods));
			}
		}

		class DisplayMouseButtonCallback extends GLFWMouseButtonCallback {

			@Override
			public void invoke(long window, int button, int action, int mods) {
				InputBus.invoke(new MouseButtonEvent(window, button, action, mods));

			}
		}
		
		class DisplayScrollCallback extends GLFWScrollCallback {

			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				
			}

		}
		
		class DisplayWindowCloseCallback extends GLFWWindowCloseCallback {

			@Override
			public void invoke(long window) {
				// TODO Auto-generated method stub
				
			}

		}
		
		class DisplayWindowFocusCallback extends GLFWWindowFocusCallback {

			@Override
			public void invoke(long window, int focused) {
				// TODO Auto-generated method stub
				
			}

		}
		
		class DisplayWindowIconifyCallback extends GLFWWindowIconifyCallback {

			@Override
			public void invoke(long window, int iconified) {
				// TODO Auto-generated method stub
				
			}

		}
		
		class DisplayWindowPosCallback extends GLFWWindowPosCallback {

			@Override
			public void invoke(long window, int xpos, int ypos) {
				// TODO Auto-generated method stub
				
			}

		}
		
		class DisplayWindowRefreshCallback extends GLFWWindowRefreshCallback {

			@Override
			public void invoke(long window) {
				// TODO Auto-generated method stub
				
			}

		}
		
		class DisplayWindowSizeCallback extends GLFWWindowSizeCallback {

			@Override
			public void invoke(long window, int mwidth, int mheight) {
				width = mwidth;
				height = mheight;
			}

		}
		
	
}
