package com.jediminer543.util;

import java.io.File;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.jediminer543.util.display.DisplayHandler;
import com.jediminer543.util.gl.Project;
import com.jediminer543.util.render.model.Model;
import com.jediminer543.util.render.model.ObjectLoader;

public class Game 
{
	public static long mainDisplayID;
	
	public static Model model;
	
	public static int width = 800;
	public static int height = 600;
		
	public static String title = "Test Window";
		
	
	public static void main(String[] args) throws IOException
	{
		model = ObjectLoader.loadModel(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sphere.obj"));
		initDisplay();
		mainLoop();
	}
	
	public static void init() {
		initDisplay();
	}
	
	public static void initDisplay() {
		DisplayHandler.init();
		mainDisplayID = DisplayHandler.makeDisplay(title, width, height, 0L, 0L);
		DisplayHandler.setActive(mainDisplayID);
	}
	
	public static void initGl() {
		Project.gluPerspective(90f, width/height, 0.01f, 100f);
	}
	
	public static void mainLoop() {
		while (true) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			model.render();
		}
	}
}
