package com.jediminer543.util.render.camera;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import com.jediminer543.util.display.DisplayHandler;
import com.jediminer543.util.event.InputEvent;
import com.jediminer543.util.event.annotation.Input;
import com.jediminer543.util.event.bus.InputBus;
import com.jediminer543.util.input.Keyboard;
import com.jediminer543.util.input.Mouse;


/**
 * Created by Jediminer543 on 23/08/2014.
 * 
 * A basic camera that serves to debug environments.
 */
public class DebugCamera extends Camera
{
	
	Keyboard keyboard = new Keyboard(DisplayHandler.getActive());
	Mouse mouse = new Mouse(DisplayHandler.getActive());
	
	Vector3f lookVelocity = new Vector3f();
	
	protected double DX, DY;

	public DebugCamera() {
		InputBus.register(this);
	}
	
	@Override
	public void tick()
	{
		//rot.x += DX * 0.15;
		//rot.y += DY * 0.1;
		//DX = DY = 0;
		convertLook();
		detectInput();
		moveCamera();
	}

	public void moveCamera()
	{
		GL11.glLoadIdentity();
		//GL11.glRotatef(180, 0, 0, 1);
		GL11.glRotatef(rot.y, 1, 0, 0);
		GL11.glRotatef(rot.x, 0, 1, 0);
		GL11.glTranslatef(pos.x, pos.y, pos.z);
	}

	@Input
	public void detectInput(InputEvent ie) {
//		if (ie instanceof KeyEvent) {
//			KeyEvent ke = (KeyEvent) ie;
//			if (ke.getWindowID() == DisplayHandler.getActive()) {
//				switch (ke.getKey()) {
//				case Keyboard.KEY_W:
//					lookVelocity.x += (float) (10 * 0.01);
//					break;
//				case Keyboard.KEY_S:
//					lookVelocity.x -= (float) (10 * 0.01);
//					break;
//				case Keyboard.KEY_A:
//					lookVelocity.z += (float) (10 * 0.01);
//					break;
//				case Keyboard.KEY_D:
//					lookVelocity.z -= (float) (10 * 0.01);
//					break;
//				case Keyboard.KEY_UP:
//					rot.y -= 1;
//					break;
//				case Keyboard.KEY_DOWN:
//					rot.y += 1;
//					break;
//				case Keyboard.KEY_LEFT:
//					rot.x -= 1;
//					break;
//				case Keyboard.KEY_RIGHT:
//					rot.x += 1;
//					break;
//				case Keyboard.KEY_SPACE:
//					pos.y -= 0.1;
//					break;
//				case Keyboard.KEY_LCONTROL:
//					pos.y += 0.1;
//					break;
//				}
//			}
//		}
//		else 
//		if (ie instanceof MouseEvent) {
//			if (ie instanceof MouseMoveEvent) {
//				MouseMoveEvent mme = (MouseMoveEvent) ie;
//				DX += mme.getXpos();
//				DY += mme.getYpos();
//			}
//		}
		
	}
	
	public void detectInput()
	{
		//if (Mouse.isGrabbed()) {
			int DX = mouse.getDX();
			int DY = mouse.getDY();
			
			rot.x += DX * 0.15;
			rot.y += DY * 0.1;
		//}
		
		if (rot.y > 90)
			rot.y = 90;
		if (rot.y < -90)
			rot.y = -90;
			
		if (keyboard.isKeyDown(Keyboard.KEY_W))
			lookVelocity.x += (float) (10 * 0.01);
		if (keyboard.isKeyDown(Keyboard.KEY_S))
			lookVelocity.x -= (float) (10 * 0.01);
		if (keyboard.isKeyDown(Keyboard.KEY_A))
			lookVelocity.z += (float) (10 * 0.01);
		if (keyboard.isKeyDown(Keyboard.KEY_D))
			lookVelocity.z -= (float) (10 * 0.01);
		if (keyboard.isKeyDown(Keyboard.KEY_UP))
			rot.y -= 1;
		if (keyboard.isKeyDown(Keyboard.KEY_DOWN))
			rot.y += 1;
		if (keyboard.isKeyDown(Keyboard.KEY_LEFT))
			rot.x -= 1;
		if (keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			rot.x += 1;
		if (keyboard.isKeyDown(Keyboard.KEY_SPACE))
			pos.y -= 0.1;
		if (keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
			pos.y += 0.1;
	}
	

	public void convertLook()
	{
		
		float Hypotonuse = lookVelocity.x; //TODO add delta
		float Ajacent =  Hypotonuse * (float) Math.cos(Math.toRadians(rot.x));
		float Oposite = Hypotonuse * (float) Math.sin(Math.toRadians(rot.x));
		pos.z += Ajacent;
		pos.x -= Oposite;
		
		float HypotonuseP = lookVelocity.z; //TODO add delta
		float AjacentP =  HypotonuseP * (float) Math.cos(Math.toRadians(rot.x-90));
		float OpositeP = HypotonuseP * (float) Math.sin(Math.toRadians(rot.x-90));
		pos.z += AjacentP;
		pos.x -= OpositeP;
		
		lookVelocity = new Vector3f();
	}
	
}
