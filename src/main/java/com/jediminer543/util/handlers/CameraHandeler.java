package com.jediminer543.util.handlers;

import com.jediminer543.util.handlers.time.Tickable;
import com.jediminer543.util.render.camera.Camera;

public class CameraHandeler implements Tickable
{
	Camera activeCamera;

	public void tick() {
		activeCamera.tick();
	}
	
	//TODO implement many cameras. ArrayList<Camera> cameras
	
	
}
