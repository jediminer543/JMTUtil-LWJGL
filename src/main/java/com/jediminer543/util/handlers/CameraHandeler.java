package com.jediminer543.util.handlers;

import java.util.ArrayList;

import com.jediminer543.util.handlers.time.Tickable;
import com.jediminer543.util.render.camera.Camera;

/**
 * 
 * @author Jediminer543
 *	
 *
 */
@Deprecated
public class CameraHandeler implements Tickable
{
	Camera activeCamera;
	
	ArrayList<Camera> cameras = new ArrayList<Camera>();

	public void tick() {
		activeCamera.tick();
	}
	
	
}
