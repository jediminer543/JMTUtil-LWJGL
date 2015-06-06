package com.jediminer543.util;

import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.glfw.GLFW;

import com.jediminer543.util.display.DisplayHandler;
import com.jediminer543.util.gl.Project;
import com.jediminer543.util.render.camera.DebugCamera;
import com.jediminer543.util.render.model.Model;
import com.jediminer543.util.render.model.ObjectLoader;

import javax.vecmath.Vector3f;

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
		model.mtl.materials.get(0).texture.setFilter(GL11.GL_NEAREST);
		model.mtl.materials.get(0).texture.setWrap(GL12.GL_CLAMP_TO_EDGE);
		sphere.init();
	}
	
	public static void initDisplay() {
		DisplayHandler.init();
		mainDisplayID = DisplayHandler.makeDisplay(title, width, height, 0L, 0L);
		DisplayHandler.setActive(mainDisplayID);
		DisplayHandler.setMouseGrabbed(mainDisplayID, true);
	}
	
	public static void initGl() {
		//FloatBuffer projection = BufferUtils.createFloatBuffer(16);
		GLContext.createFromCurrent();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        float near = 0.01f;
        float far = 100f;
        float aspect = width / height;
        float fov = 70f;
        
        //double left = -1*near*Math.tan((aspect*fov)/2);
        //double right = far*Math.tan((aspect*fov)/2);
        //double bottom = -1*near*Math.tan(fov/2);
        //double top = far*Math.tan(fov/2);
        Project.gluPerspective(fov, aspect, near, far);
        //protoPerspective(70, aspect, near, far);
        //glFrustum(-1.0f, 1.0f, -1.0f, 1.0f, 1f, 20.0f);
        //glGetFloat(GL_PROJECTION_MATRIX, projection);
        //while (projection.remaining() > 0) {
        //	System.out.print(projection.get() + ",");
        //}
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
		sphere.pos.add(new Vector3f(-10f,0f,-10f));
		model.rot.add(new Vector3f(-45f,0f,-45f));
		while (GLFW.glfwWindowShouldClose(DisplayHandler.getActive()) == GL11.GL_FALSE) {
			GLFW.glfwPollEvents();
			GLFW.glfwSwapBuffers(DisplayHandler.getActive());
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			camera.tick();
			model.render();
			sphere.render();
			
		}
		GLFW.glfwDestroyWindow(mainDisplayID);
	}
}
