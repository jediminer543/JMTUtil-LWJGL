import static org.lwjgl.system.glfw.GLFW.glfwInit;
import static org.lwjgl.system.glfw.GLFW.glfwSetErrorCallback;

import org.lwjgl.opengl.GL11;
import org.lwjgl.system.glfw.ErrorCallback;


public class Test {

	public static void main(String[] args) {
		glfwSetErrorCallback(ErrorCallback.Util.getDefault());
		 
        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
	}

}
