package com.jediminer543.util.test.config;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.jediminer543.util.config.Config;

public class ConfigTest {

	@ClassRule
	public static TemporaryFolder tempDir = new TemporaryFolder();
	
	static String configFileName = "testConfig.cfg";

	@Test
	public void configTest() throws IOException
	{
		File configFile = tempDir.newFile(configFileName);
		Config config = new Config(configFile);
		String testStr = "Hello, World!";
		String testStrPath = "testStr";
		config.set(testStrPath, testStr);
		assertTrue("Testing key exists",config.keyExists(testStrPath));
		assertEquals("Testing key read", config.readString(testStrPath), testStr);
		
	}
}
