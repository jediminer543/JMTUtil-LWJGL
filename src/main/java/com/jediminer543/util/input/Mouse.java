package com.jediminer543.util.input;

public class Mouse {
	
	/**
	 * Creates a mouse with a predefined windowID
	 * @param windowID Window to link to
	 */
	public Mouse(long windowID) {
		this.windowID = windowID;
	}
	
	/**
	 * Window id mouse is linked to
	 */
	private long windowID;
	
	public long getWindowID() {
		return windowID;
	}

	public void setWindowID(long windowID) {
		this.windowID = windowID;
	}
	
	/** Delta X */
	private int				dx;

	/** Delta Y */
	private int				dy;

	/** Delta Scroll x */
	private int				dwheelx;
	
	/** Delta Scroll y */
	private int				dwheely;
	
	/**
	 * Polls the mouse for its current state. Access the polled values using the
	 * get<value> methods.
	 * By using this method, it is possible to "miss" mouse click events if you don't
	 * poll fast enough.
	 *
	 * @see org.lwjgl.input.Mouse#getDX()
	 * @see org.lwjgl.input.Mouse#getDY()
	 * @see org.lwjgl.input.Mouse#getDWheel()
	 */
	public void Poll() {
		
	}
	
	/**
	 * @return Movement on the x axis since last time getDX() was called.
	 */
	public int getDX() {
			int result = dx;
			dx = 0;
			return result;
	}

	/**
	 * @return Movement on the y axis since last time getDY() was called.
	 */
	public int getDY() {
			int result = dy;
			dy = 0;
			return result;
	}

	/**
	 * @return Movement of the wheel on x axis since last time getDWheelX() was called
	 */
	public int getDWheelX() {
			int result = dwheelx;
			dwheelx = 0;
			return result;
	}
	
	/**
	 * @return Movement of the wheel on y axis since last time getDWheelY() was called
	 */
	public int getDWheelY() {
			int result = dwheely;
			dwheely = 0;
			return result;
	}
	
	/**
	 * Sets whether or not the mouse has grabbed the cursor
	 * (and thus hidden). If grab is false, the getX() and getY()
	 * will return delta movement in pixels clamped to the display
	 * dimensions, from the center of the display.
	 *
	 * @param grab whether the mouse should be grabbed
	 */
	public void setGrabbed(boolean grab) {
		
	}
}
