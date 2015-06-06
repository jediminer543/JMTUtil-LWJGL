package com.jediminer543.util.input;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWimage;


public class Cursor
{
	private final long cursorID;
	
	/**
	 * Constructs a new Cursor, with the given parameters.
	 *
	 * @param width cursor image width
	 * @param height cursor image height
	 * @param xHotspot the x coordinate of the cursor hotspot
	 * @param yHotspot the y coordinate of the cursor hotspot
	 * @param images A buffer containing the images. The origin is at the lower left corner, like OpenGL.
	 */
	public Cursor(int width, int height, int xHotspot, int yHotspot, ByteBuffer image) {
		//cursorID
		GLFWimage glfwImage = new GLFWimage();
		glfwImage.setPixels(image);
		glfwImage.setWidth(width);
		glfwImage.setHeight(height);
		cursorID = GLFW.glfwCreateCursor(image, xHotspot, yHotspot);
	}
	
	public long getCursorID() {
		return cursorID;
	}
	
	/**
	 * Binds the cursor to the target window. 
	 * 
	 * @param windowID Window to bind cursor to
	 */
	public void bind(long windowID) {
		GLFW.glfwSetCursor(windowID, cursorID);
	}
	
	/**
	 * Destroy the glfw cursor. If the cursor is current,
	 * the current glfw cursor is set to null (the default
	 * OS cursor)
	 */
	public void destroy() {
		GLFW.glfwDestroyCursor(cursorID);
	}
}
