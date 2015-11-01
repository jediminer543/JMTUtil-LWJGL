package com.jediminer543.util.test;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.glfw.GLFW;

import com.jediminer543.util.Natives;
import com.jediminer543.util.display.DisplayHandler;
import com.jediminer543.util.file.CFL;
import com.jediminer543.util.gl.Project;
import com.jediminer543.util.render.camera.DebugCamera;
import com.jediminer543.util.render.model.Model;
import com.jediminer543.util.render.model.ObjectLoader;

public class Game 
{
	public static long mainDisplayID;
	
	public static Model model, sphere;
	
	public static int width = 800;
	public static int height = 600;
		
	public static String title = "Test Window";
		
	
	public static void main(String[] args) throws IOException
	{
		Natives.setNativePath();
		model = ObjectLoader.loadModel(CFL.loadFileFromClasspath("cube.obj"));
		sphere = ObjectLoader.loadModel(CFL.loadFileFromClasspath("sphere2.obj"));
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
		//FloatBuffer projection = BufferUtils.createFloatBuffer(16);
		GLContext.createFromCurrent();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        //float right = -width/2;
        //float left = width/2;
        //float top = height/2;
        //float bottom = -height/2;
        //float near = 0f;
        //float far = 20f;
        
        /*
        FloatBuffer orthoMatrix = BufferUtils.createFloatBuffer(16);
        float[] orthoArray = {2/(right-left), 0, 0, -((right+left)/(right-left)),
        					0, 2/(top-bottom), 0, -((top+bottom)/(top-bottom)),
        					0, 0, -2/(far - near), -((far+near)/(far-near)),
        					0, 0, 0, 1};
        System.out.println(Arrays.toString(orthoArray));
        //orthoMatrix = orthoMatrix.put(orthoArray);
        for(float f : orthoArray)
        {
           orthoMatrix.put(f);
        }
        
        glLoadMatrix(orthoMatrix);*/
        
        
        Project.gluPerspective(70f, width/height, 0.1f, 100f);
        //protoPerspective(70, aspect, near, far);
        //glFrustum(-1.0f, 1.0f, -1.0f, 1.0f, 1f, 20.0f);
        //glGetFloat(GL_PROJECTION_MATRIX, projection);
        //while (projection.remaining() > 0) {
        //	System.out.print(projection.get() + ",");
        //}
        //glMatrixMode(GL_PROJECTION);
        //glLoadIdentity();
        //glOrtho(right, left, bottom, top, near, far);
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
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		sphere.pos.add(new Vector3f(-10f,0f,-10f));
		//model.rot.add(new Vector3f(-45f,0f,-45f));
		GLFW.glfwSetInputMode(mainDisplayID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
		while (GLFW.glfwWindowShouldClose(DisplayHandler.getActive()) == GL11.GL_FALSE) {
			GLFW.glfwPollEvents();
			GLFW.glfwSwapBuffers(DisplayHandler.getActive());
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			model.render();
			sphere.render();
			camera.tick();
			
		}
		GLFW.glfwDestroyWindow(mainDisplayID);
	}
}
