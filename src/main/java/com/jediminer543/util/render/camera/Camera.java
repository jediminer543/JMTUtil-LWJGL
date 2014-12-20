package com.jediminer543.util.render.camera;

import javax.vecmath.Vector3f;

import com.jediminer543.util.handlers.time.Tickable;

/**
 * Created by Jediminer543 on 23/08/2014.
 *
 * Base camera class
 */
public abstract class Camera implements Tickable
{
	public Vector3f pos = new Vector3f();
	public Vector3f rot = new Vector3f();

	public abstract void tick();
}
