package com.jediminer543.util.test.input;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.glfw.GLFW;

import com.jediminer543.util.event.KeyEvent;
import com.jediminer543.util.event.bus.InputBus;
import com.jediminer543.util.input.Keyboard;


public class KeyboardTest
{
	static Keyboard k;
	static int window = 0;
	static int key = 1;
	static int action = 1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		k = new Keyboard(0);
		InputBus.invoke(new KeyEvent(window, key, 0, action, 0));
		k.next();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}
	
	@Test
	public void testWindowID() {
		assertEquals("Test Keyboard WindowID", window, k.getWindowID());;
	}
	
	@Test
	public void testEventKey() {
		assertEquals("Test Key Event Actions", key, k.getEventKey());;
	}
	
	@Test
	public void testEventState() {
		boolean state = (action == GLFW.GLFW_PRESS);
		assertEquals("Test Key Event Actions", state, k.getEventKeyState());;
	}
	
	//@Test//TODO
	//public void testEventThing() {
	//	assertEquals("", 1, k.getEventKey());;
	//}
	
}
