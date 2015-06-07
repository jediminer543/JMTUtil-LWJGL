package com.jediminer543.util.test.display;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jediminer543.util.display.*;
import com.jediminer543.util.natives.NativeUtil;

public class DisplayTest
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
	public void testCreate() {
		assertNotEquals("Test Display Creation", 0, display.getWindowID());
	}
	
	@Test
	public void testRegister() {
		assertEquals("Test Display Handler Registration", display.getWindowID(), DisplayHandler.getActive());
	}
	
}
