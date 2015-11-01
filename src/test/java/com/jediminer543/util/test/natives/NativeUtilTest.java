package com.jediminer543.util.test.natives;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jediminer543.util.natives.NativeUtil;


public class NativeUtilTest
{
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}
	
	@Test
	public void testSetNativePath() {
		String path = "test";
		NativeUtil.setNativePath(path);
		assertThat("Test Setting Native Path", System.getProperty("java.library.path"), containsString(path));
	}
	
	//@Test
	//public void testAddNativePath() {
	//	String path1 = "test/test";
	//	String path2 = "test5/standingBy";
	//	NativeUtil.setNativePath(path1);
	//	NativeUtil.setNativePath(path2);
		// test that both paths are added
		//assertThat("Test Adding native string pt1", System.getProperty("java.library.path"), containsString(path1));
		//assertThat("Test Adding native string pt1", System.getProperty("java.library.path"), containsString(path2));
	//}
	
	@Test
	public void testAddDefaultNativePath() {
		NativeUtil.addDefaultNativePath();
		assertThat("Test Adding Default Native Path", System.getProperty("java.library.path"), containsString("lib" + File.separator + "natives"));
	}
	
}
