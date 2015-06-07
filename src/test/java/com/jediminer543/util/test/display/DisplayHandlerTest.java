package com.jediminer543.util.test.display;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jediminer543.util.display.Display;
import com.jediminer543.util.display.DisplayHandler;
import com.jediminer543.util.natives.NativeUtil;


public class DisplayHandlerTest
{
	
	static Display display;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NativeUtil.addDefaultNativePath();
		display = new Display("Test", 800, 600, 0, 0);
		display.init();
		display.makeActive();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}
	
	@Test
	public void testSetGrabbed() {
		boolean mouseGrabbed = display.isMouseGrabbed();
		DisplayHandler.setMouseGrabbed(display.getWindowID(), !mouseGrabbed);
		assertEquals("Test Mouse Grabbed", !mouseGrabbed, display.isMouseGrabbed());
	}
	
}
