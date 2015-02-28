package com.jediminer543.util.render;

import javax.vecmath.Vector3f;

/**
 * Created by Jediminer543 on 12/08/2014.
 */
public abstract class Renderable
{
	public Vector3f pos = new Vector3f();
	public Vector3f rot = new Vector3f();

	public abstract void render();
}
