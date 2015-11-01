package com.jediminer543.util.natives;

import java.io.File;
import java.lang.reflect.Field;


public class NativeUtil
{
	public static void setNativePath(String path) {
		System.setProperty( "java.library.path", path);
		 
		Field fieldSysPath;
		try {
			fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
			fieldSysPath.setAccessible( true );
			fieldSysPath.set( null, null );
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addNativePath(String path) {
		setNativePath(((System.getProperty("java.library.path") != null) ? System.getProperty("java.library.path") : "") + File.pathSeparator + path);
	}
	
	public static void addDefaultNativePath() {
		addNativePath("lib" + File.separator + "natives");
	}
}
