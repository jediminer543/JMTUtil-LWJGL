package com.jediminer543.util.file;

import java.io.File;

/**
 * 
 * @author Jediminer543
 *
 *	ClasspathFileLoader
 */
public class CFL {
	
	public static File loadFileFromClasspath(String fileName) {
		return new File(CFL.class.getClassLoader().getResource(fileName).getFile());
	}
	
}
