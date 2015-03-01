package com.jediminer543.util;

import java.io.File;

public class Natives {
	
	public static void setNativePath() {
		String arch = System.getProperty("os.arch");
		String os = System.getProperty("os.name").split(" ")[0];
		
		StringBuilder path = new StringBuilder("lib" + File.separator +"natives" + File.separator);
		
		switch (os) {
			case "Windows":
				path.append("windows"  + File.separator);
				break;
			case "Mac":
				path.append("macosx"  + File.separator);
				break;
			default:
				path.append("linux" + File.separator);
				break;
		}
		
		if (arch.contains("64")) {
			path.append("x64");
		}
		else {
			path.append("x32");
		}
		
		System.setProperty("java.library.path", path.toString());
		
		//System.out.println(System.getProperty("java.library.path"));
	}

}
