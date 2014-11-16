package com.jediminer543.util.display;

import org.lwjgl.system.glfw.GLFW;
import org.lwjgl.system.glfw.WindowCallback;

import com.jediminer543.util.handlers.time.Tickable;
import com.jediminer543.util.input.InputStore;
import com.jediminer543.util.input.Keyboard;
import com.jediminer543.util.input.Mouse;
import com.jediminer543.util.input.MouseEvent;

public class Display implements Tickable
{
	private long windowID;
	
	private InputStore is = new InputStore();
	
	private Keyboard keyboard;
	private Mouse mouse;
	
	private WindowCallback callback = new WindowCallback() {

		public MouseEvent me = new MouseEvent();
		
		public boolean newME = false;
		
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
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseButton(long window, int button, int action, int mods) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void scroll(long window, double arg1, double arg2) {
			// TODO Auto-generated method stub
			
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
		
	};
	
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
		WindowCallback.set(windowID, callback);
		keyboard = new Keyboard(windowID);
	}
	
	public void makeActive() {
		GLFW.glfwMakeContextCurrent(windowID);
	}

	public void tick() {
		this.callback.newME = true;
		this.keyboard.Poll();
		this.mouse.Poll();
	}
	
	
}
