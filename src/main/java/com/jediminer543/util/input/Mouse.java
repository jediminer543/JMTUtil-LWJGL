package com.jediminer543.util.input;

import java.util.Stack;

import org.lwjgl.glfw.GLFW;

import com.jediminer543.util.display.DisplayHandler;
import com.jediminer543.util.event.InputEvent;
import com.jediminer543.util.event.MouseButtonEvent;
import com.jediminer543.util.event.MouseMoveEvent;
import com.jediminer543.util.event.annotation.Input;
import com.jediminer543.util.event.bus.InputBus;

public class Mouse {
	
	@Input
	public void onInput(InputEvent ie) {
		if (ie.getWindowID() == this.windowID) {
			if (ie instanceof MouseMoveEvent) {
				MouseMoveEvent mme = (MouseMoveEvent) ie;
				if (mme.isGrabbed()) {
					this.dx += mme.getXpos();
					this.dy += mme.getYpos();
					this.x += mme.getXpos();
					this.y += mme.getYpos();
				}
				else {
					this.dx = mme.getXpos();
					this.dy = mme.getYpos();
					this.x = mme.getXpos();
					this.y = mme.getYpos();
				}
				this.events.push(addEvent(x, y, null, null, null, null, null));
			}
			if (ie instanceof MouseButtonEvent) {
				MouseButtonEvent mbe = (MouseButtonEvent) ie;
				this.events.push(addEvent(null, null, mbe.getButton(), mbe.getAction(), null, null, null));
			}
		}
	}
	
	private Event addEvent(Double x, Double y, Integer button, Integer buttonState, Double wheelx, Double wheely, Long nanos) {
		Event event = new Event();
		event.event_x = x != null ? x : this.x;
		event.event_y = y != null ? y : this.y;
		if (button != null) {
			event.eventButton = button;
			event.eventState = button == GLFW.GLFW_PRESS || button == GLFW.GLFW_REPEAT;
		} else {
			event.eventButton = -1;
			event.eventState = false;
		}
		
		return event;
	}
	
	/**
	 * Creates a mouse with a predefined windowID
	 * @param windowID Window to link to
	 */
	public Mouse(long windowID) {
		this.windowID = windowID;
		InputBus.register(this);
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
	
	/** Mouse absolute X position in pixels */
	private double				x;

	/** Mouse absolute Y position in pixels */
	private double				y;
	
	/** Delta X */
	private double				dx;

	/** Delta Y */
	private double				dy;

	/** Delta Scroll x */
	private double				dwheelx;
	
	/** Delta Scroll y */
	private double				dwheely;
	
	/** The current mouse event button being examined */
	private int			eventButton;

	/** The current state of the button being examined in the event queue */
	private boolean		eventState;
	
	/** The current delta of the mouse in the event queue */
	private double			event_dx;
	private double			event_dy;
	private double			event_dwheelx;
	private double			event_dwheely;
	
	/** The current absolute position of the mouse in the event queue */
	private double			event_x;
	private double			event_y;
	private long		event_nanos;
	
	private double			last_event_raw_x;
	private double			last_event_raw_y;
	
	private Stack<Event> events = new Stack<Event>();

	private Event current_event;
	
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
	public double getDX() {
			double result = dx;
			dx = 0;
			return result;
	}

	/**
	 * @return Movement on the y axis since last time getDY() was called.
	 */
	public double getDY() {
			double result = dy;
			dy = 0;
			return result;
	}
	
	/**
	 * Retrieves the absolute position. It will be clamped to
	 * 0...height-1.
	 *
	 * @return Absolute y axis position of mouse
	 */
	public double getX() {
			double result = x;
			return result;
	}

	/**
	 * Retrieves the absolute position. It will be clamped to
	 * 0...height-1.
	 *
	 * @return Absolute y axis position of mouse
	 */
	public double getY() {
			double result = y;
			return result;
	}

	/**
	 * @return Movement of the wheel on x axis since last time getDWheelX() was called
	 */
	public double getDWheelX() {
			double result = dwheelx;
			dwheelx = 0;
			return result;
	}
	
	/**
	 * @return Movement of the wheel on y axis since last time getDWheelY() was called
	 */
	public double getDWheelY() {
			double result = dwheely;
			dwheely = 0;
			return result;
	}
	
	/**
	 * Sets whether or not the mouse has grabbed the cursor
	 * (and thus hidden). If grab is false, the getX() and getY()
	 * will return delta movement in pixels clamped to the display
	 * dimensions, from the center of the display.
	 * <strong>NYI</strong>
	 * @param grab whether the mouse should be grabbed
	 */
	public void setGrabbed(boolean grab) {
		DisplayHandler.setMouseGrabbed(windowID, grab);
	}
	
	public Event nextEvent() {
		if (events.size() > 0) {
			Event old_event = current_event;
			current_event = events.pop();
			this.unpackEvent();
			return old_event;
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the next mouse event. You can query which button caused the event by using
	 * <code>getEventButton()</code> (if any). To get the state of that key, for that event, use
	 * <code>getEventButtonState</code>. To get the current mouse delta values use <code>getEventDX()</code>
	 * and <code>getEventDY()</code>.
	 * @see org.lwjgl.input.Mouse#getEventButton()
	 * @see org.lwjgl.input.Mouse#getEventButtonState()
	 * @return true if a mouse event was read, false otherwise
	 */
	public boolean next() {
		return !(this.nextEvent() == null);
	}
	
	private void unpackEvent() {
				event_dx = current_event.event_x - last_event_raw_y;
				event_dy = current_event.event_y - last_event_raw_y;
				event_x += event_dx;
				event_y += event_dy;
				last_event_raw_x = event_x;
				last_event_raw_y = event_y;
				eventButton = current_event.eventButton;
				eventState = current_event.eventState;
				
	}

	/**
	 * @return Current events button. Returns -1 if no button state was changed
	 */
	public int getEventButton() {
		return eventButton;
	}

	/**
	 * Get the current events button state.
	 * @return Current events button state.
	 */
	public boolean getEventButtonState() {
		return eventState;
	}

	/**
	 * @return Current events delta x.
	 */
	public double getEventDX() {
		return event_dx;
	}

	/**
	 * @return Current events delta y.
	 */
	public double getEventDY() {
		return event_dy;
	}

	/**
	 * @return Current events absolute x.
	 */
	public double getEventX() {
		return event_x;
	}

	/**
	 * @return Current events absolute y.
	 */
	public double getEventY() {
		return event_y;
	}

	/**
	 * @return Current events delta z
	 */
	public double getEventDWheelX() {
		return event_dwheelx;
	}

	/**
	 * Gets the time in nanoseconds of the current event.
	 * Only useful for relative comparisons with other
	 * Mouse events, as the absolute time has no defined
	 * origin.
	 *
	 * @return The time in nanoseconds of the current event
	 */
	public long getEventNanoseconds() {
		return event_nanos;
	}
	
	@SuppressWarnings("unused")
	private class Event {
		/** The current mouse event button being examined */
		private int			eventButton;

		/** The current state of the button being examined in the event queue */
		private boolean		eventState;
		
		/** The current absolute position of the mouse in the event queue */
		private double			event_x;
		private double			event_y;
		private long		event_nanos;
	}
	
}
