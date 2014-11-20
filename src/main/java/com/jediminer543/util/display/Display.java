package com.jediminer543.util.display;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.glfw.GLFW.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.lwjgl.system.glfw.GLFW;
import org.lwjgl.system.glfw.GLFWvidmode;
import org.lwjgl.system.glfw.WindowCallback;

import com.jediminer543.util.event.InputEvent;
import com.jediminer543.util.event.KeyEvent;
import com.jediminer543.util.event.MouseMoveEvent;
import com.jediminer543.util.event.annotation.Input;
import com.jediminer543.util.event.bus.InputBus;
import com.jediminer543.util.handlers.time.Tickable;
import com.jediminer543.util.input.Keyboard;
import com.jediminer543.util.input.Mouse;

public class Display implements Tickable
{
	private long windowID;
	
	private Keyboard keyboard;
	private Mouse mouse;
	
	private DisplayCallback callback = new DisplayCallback();
	
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
			if (ke.getWindowID() == this.getWindowID() && ke.getKey() == Keyboard.KEY_ESCAPE) {
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
        
		windowID = glfwCreateWindow(width, height, CharBuffer.wrap(title), monitor, share);
		
		WindowCallback.set(windowID, callback);
		
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
	}

	public void tick() {
		//TODO
	}
	
	public class DisplayCallback extends WindowCallback {
		
		@Override
		public void charMods(long arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void character(long arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cursorEnter(long arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cursorPos(long window, double xpos, double ypos) {
			InputBus.invoke(new MouseMoveEvent(window, xpos, ypos));
			
		}

		@Override
		public void drop(long arg0, int arg1, long arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void framebufferSize(long arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void key(long window, int key, int scancode, int action, int mods) {
			InputBus.invoke(new KeyEvent(window, key, scancode, action, mods));
			
		}

		@Override
		public void mouseButton(long window, int button, int action, int mods) {
			//TODO:mouseEventUpdate();
			
		}

		@Override
		public void scroll(long window, double wheelx, double wheely) {
			//TODO:mouseEventUpdate();
		}

		@Override
		public void windowClose(long arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowFocus(long arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconify(long arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowPos(long arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowRefresh(long arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowSize(long arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
