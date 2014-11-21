package com.jediminer543.util;

import java.io.File;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.glfw.GLFW;

import com.jediminer543.util.display.DisplayHandler;
import com.jediminer543.util.gl.Project;
import com.jediminer543.util.render.camera.DebugCamera;
import com.jediminer543.util.render.model.Model;
import com.jediminer543.util.render.model.ObjectLoader;
import com.jediminer543.util.vector.Vector3f;

public class Game 
{
	public static long mainDisplayID;
	
	public static Model model, sphere;
	
	public static int width = 800;
	public static int height = 600;
		
	public static String title = "Test Window";
		
	
	public static void main(String[] args) throws IOException
	{
		model = ObjectLoader.loadModel(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\cube.obj"));
		sphere = ObjectLoader.loadModel(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sphere2.obj"));
		init();
		mainLoop();
	}
	
	public static void init() {
		initDisplay();
		initGl();
		model.init();
		sphere.init();
	}
	
	public static void initDisplay() {
		DisplayHandler.init();
		mainDisplayID = DisplayHandler.makeDisplay(title, width, height, 0L, 0L);
		DisplayHandler.setActive(mainDisplayID);
	}
	
	public static void initGl() {
		GLContext.createFromCurrent();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        Project.gluPerspective(70, width / height, 0.01f, 100f);
        //glGetFloat(GL_PROJECTION_MATRIX, GLOBALS.GLUMatrix);
        //glMatrixMode(GL_PROJECTION);
        //glLoadIdentity();
        //glOrtho(0, width, height, 0, 1, -1);
        //glGetFloat(GL_PROJECTION_MATRIX, GLOBALS.HUDMatrix);
        //glLoadMatrix(GLOBALS.GLUMatrix);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();


        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_BLEND);
	}
	
	public static void mainLoop() {
		DebugCamera camera = new DebugCamera();
		GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		model.pos.translate(new Vector3f(-1f,0f,-1f));
		model.rot.translate(new Vector3f(-45f,0f,-45f));
		GLFW.glfwSetInputMode(mainDisplayID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
		while (GLFW.glfwWindowShouldClose(DisplayHandler.getActive()) == GL11.GL_FALSE) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			model.render();
			sphere.render();
			camera.tick();
			GLFW.glfwSwapBuffers(DisplayHandler.getActive());
			GLFW.glfwPollEvents();
		}
		GLFW.glfwDestroyWindow(mainDisplayID);
	}
}
