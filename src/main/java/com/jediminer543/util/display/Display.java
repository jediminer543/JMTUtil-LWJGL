package com.jediminer543.util.display;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
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
	
	Keyboard keyboard;
	Mouse mouse;
	
	/**
	 * @return the keyboard of this display
	 * @since 0.1.5
	 */
	public Keyboard getKeyboard() {
	
		return keyboard;
	}
	/**
	 * @return the mouse of this display
	 * @since 0.1.5
	 */
	public Mouse getMouse() {
	
		return mouse;
	}

	/**
	 * The mouse grab state
	 */
	private boolean mouseGrabbed = false;
	
	/**
	 * The callback class
	 */
	DisplayCallbacks callbacks = new DisplayCallbacks();
	/**
	 * Mouse Move callback
	 */
	private DisplayCursorPosCallback cpcallback = callbacks.new DisplayCursorPosCallback();
	/**
	 * Key callback
	 */
	private DisplayKeyCallback keycallback = callbacks.new DisplayKeyCallback();
	/**
	 * Mouse Button Callback
	 */
	private DisplayMouseButtonCallback mbcallback = callbacks.new DisplayMouseButtonCallback();
	
	/**
	 * Gets the native GLFW id
	 * @return The native glfw ID
	 */
	public long getWindowID() {
		return windowID;
	}

	/**
	 * Width of the display
	 * @since 0.1.5
	 */
	public int height;
	/**
	 * Height of the display
	 * @since 0.1.5
	 */
	public int width;
	
	/**
	 * Determines whether to register to the DisplayHandler
	 * 
	 * @see com.jediminer543.util.DisplayHandler
	 * 
	 * @since 0.1.5
	 */
	public boolean useHandler = true;
	
	public String title;
	
	long monitor, share = 0L;
	
	/**
	 * A Config used in testing to set 
	 */
	public boolean useCloseKey = true;
	public int closeKey = Keyboard.KEY_ESCAPE;
	
	private int swapInterval = 0;
	
	/**
	 * Called by event bus; closes widow if configured to use close key
	 * Designed to be used by debugging
	 * @param ie
	 */
	@Input
	public void onInput(InputEvent ie) {
		if (ie instanceof KeyEvent) {
			KeyEvent ke = (KeyEvent) ie;
			if (ke.getWindowID() == this.getWindowID() && ke.getKey() == closeKey && useCloseKey) {
				glfwSetWindowShouldClose(windowID, GL_TRUE);
			}
		}
	}
	
	/**
	 * Creates a display with a specified title and information passed
	 * @param title Title of display
	 * @param width Width of display
	 * @param height Height of display
	 * @param monitor Monitor to fullscreen on
	 * @param share Display to share context with
	 * @since 0.1.5
	 */
	public Display(String title, int width, int height, long monitor, long share) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.monitor = monitor;
		this.share = share;
		InputBus.register(this);
	}
	
	/**
	 * Creates a display with a specified title and info from
	 *  a display mode
	 * @param title Title for display
	 * @param dm Display Mode to set 
	 * @since 0.1.5
	 */
	public Display(String title, DisplayMode dm) {
		this.title = title;
		this.width = dm.width;
		this.height = dm.height;
		this.monitor = dm.monitor;
		this.share = dm.share;
		InputBus.register(this);
	}
	
	/**
	 * Sets as active with display handler
	 * Then creates the OpenGL context for the display
	 * @since 0.1.5
	 */
	public void makeActive() {
		DisplayHandler.activeDisplayPos = windowID;
		glfwMakeContextCurrent(windowID);
		GLContext.createFromCurrent();
	}
	
	/**
	 * Initialises GLFW window
	 * @since 0.1.5
	 */
	public void init() {
		init(null);
	}
	
	/**
	 * Initialises the GLFW window
	 * Subject to change with display mode?
	 * @param mode Display Mode (NYI)
	 * @since 0.1.5
	 */
	public void init(DisplayMode mode) {
		if (GLFW.glfwInit() != GL11.GL_TRUE) {
			
		}
		glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        
		windowID = glfwCreateWindow(width, height, CharBuffer.wrap(title), monitor, share);
		DisplayHandler.registerDisplay(this);
		
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
	
	/**
	 * Sets the mouse grabbed state
	 * 
	 * @param grab The state to set the mouse to (grabbed or not)
	 * @see org.lwjgl.glfw.GLFW.glfwSetInputMode
	 * 
	 */
	public void setMouseGrabbed(boolean grab) {
		this.mouseGrabbed = grab;
		if (grab)
		glfwSetInputMode(getWindowID(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
		else
		glfwSetInputMode(getWindowID(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}
	
	/**
	 * @return The mouses grabbed state
	 * @since 0.1.5
	 */
	public boolean isMouseGrabbed() {
	
		return mouseGrabbed;
	}
	
	/**
	 * Set the display configuration to the specified gamma, brightness and contrast.
	 * The configuration changes will be reset when destroy() is called.
	 *
	 * @param gamma      The gamma value
	 * @param brightness The brightness value between -1.0 and 1.0, inclusive
	 * @param contrast   The contrast, larger than 0.0.
	 * 
	 * NYI
	 */
	public void setDisplayConfiguration(float gamma, float brightness, float contrast) {
		//TODO
	}
	
	/**
	 * Enable or disable vertical monitor synchronization. This call is a best-attempt at changing
	 * the vertical refresh synchronization of the monitor, and is not guaranteed to be successful.
	 *
	 * @param sync true to synchronize; false to ignore synchronization
	 * @since 0.1.5
	 */
	public void setVSyncEnabled(boolean sync) {
		swapInterval = (sync ? 1 : 0);
	}
	
	/**
	 * Calls GLFW per frame operations
	 * Swaps buffers and polls events
	 * @see org.lwjgl.glfw.GLFW.glfwPollEvents
	 * @see org.lwjgl.glfw.GLFW.glfwSwapBuffers
	 * @since 0.1.5
	 */
	@Override
	public void tick() {
		GLFW.glfwSwapInterval(swapInterval);
		GLFW.glfwPollEvents();
		GLFW.glfwSwapBuffers(DisplayHandler.getActive());
	}
	
	/**
	 * Update the window 
	 * @see com.jediminer543.util.display.Display.tick
	 * @since 0.1.5
	 */
	public void update() {
		this.tick();
	}
	
	/**
	 * Checks if the display should close
	 * @return Whether the display should close
	 * @since 0.1.5
	 */
	public boolean shouldClose() {
		if (glfwWindowShouldClose(getWindowID()) == GL11.GL_FALSE)
			return false;
		else
			return true;
	}
	
	/**
	 * LWJGL 2 compatibility method.
	 * @return whether the display should close
	 * @see ShouldClose
	 * @since 0.1.5
	 */
	public boolean isCloseRequested() {
		return shouldClose();
	}
	
	/**
	 * @return the centre of the display on the x axis
	 * @since 0.1.5
	 */
	public double getCenterX() {
		return width / 2;
	}
	
	/**
	 * @return the centre of the display on the x axis
	 * @since 0.1.5
	 */
	public double getCenterY() {
		return height / 2;
	}
	
	/**
	 * Destroy the Display. 
	 * @since 0.1.5
	 */
	public void destroy() {
		glfwDestroyWindow(windowID);
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
				InputBus.invoke(new MouseMoveEvent(window, mouseGrabbed, xpos - getCenterX(), ypos - getCenterY()));
				glfwSetCursorPos(windowID, getCenterX(), getCenterY());
			}
			else {
				InputBus.invoke(new MouseMoveEvent(window, mouseGrabbed, xpos, height-ypos));
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
			InputBus.invoke(new MouseButtonEvent(window, mouseGrabbed, button, action, mods));
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

}
