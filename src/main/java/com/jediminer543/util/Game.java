package com.jediminer543.util;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.system.glfw.GLFW.glfwSwapBuffers;

import java.io.File;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import com.jediminer543.util.display.DisplayHandler;
import com.jediminer543.util.gl.Project;
import com.jediminer543.util.render.model.Model;
import com.jediminer543.util.render.model.ObjectLoader;
import com.jediminer543.util.vector.Vector3f;

public class Game 
{
	public static long mainDisplayID;
	
	public static Model model;
	
	public static int width = 800;
	public static int height = 600;
		
	public static String title = "Test Window";
		
	
	public static void main(String[] args) throws IOException
	{
		model = ObjectLoader.loadModel(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\cube.obj"));
		init();
		mainLoop();
	}
	
	public static void init() {
		initDisplay();
		initGl();
		model.init();
	}
	
	public static void initDisplay() {
		DisplayHandler.init();
		mainDisplayID = DisplayHandler.makeDisplay(title, width, height, 0L, 0L);
		DisplayHandler.setActive(mainDisplayID);
	}
	
	public static void initGl() {
		GLContext.createFromCurrent();
		Project.gluPerspective(90f, width/height, 0.01f, 100f);
	}
	
	public static void mainLoop() {
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		model.pos.translate(new Vector3f(-0f,0f,0.5f));
		while (true) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			model.render();
			glfwSwapBuffers(DisplayHandler.getActive());
            glfwPollEvents();
		}
	}
}
