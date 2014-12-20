package com.jediminer543.util.render.model;

import com.jediminer543.util.render.Renderable;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * Created by Jediminer543 on 13/08/2014.
 */
public class Face extends Renderable
{

	ArrayList<Vertex> vertexes = new ArrayList<Vertex>();

	public boolean hasNormal = true;
	public boolean hasTexture = true;

	public String materialName;

	Model parent;

	public MTLMaterial material;

	@Override
	public void render()
	{
		for (Vertex vertex: vertexes)
		{
			if (hasTexture) {
				GL11.glTexCoord2f(vertex.texture.x, vertex.texture.y);
			}
			if (hasNormal) {
				GL11.glNormal3f(vertex.normal.x, vertex.normal.y, vertex.normal.z);
			}
			GL11.glVertex3f(vertex.vertex.x * parent.scale.x, vertex.vertex.y * parent.scale.y, vertex.vertex.z * parent.scale.z);
		}

	}
}
