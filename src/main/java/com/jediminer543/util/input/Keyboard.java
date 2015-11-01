package com.jediminer543.util.input;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.lwjgl.glfw.GLFW;

import com.jediminer543.util.event.InputEvent;
import com.jediminer543.util.event.KeyEvent;
import com.jediminer543.util.event.annotation.Input;
import com.jediminer543.util.event.bus.InputBus;

/**
 * 
 * @author Jediminer543
 *	
 *	a replacement for LWJGL input keyboard.
 */
public class Keyboard 
{
	@Input
	public void onInput(InputEvent ie) {
		if (ie.getWindowID() == this.windowID) {
			if (ie instanceof KeyEvent) {
				KeyEvent ke = (KeyEvent) ie;
				Event event = new Event();
				event.key = ke.getKey();
				event.repeat = (ke.getAction() == GLFW.GLFW_REPEAT);
				event.state = (ke.getAction() == GLFW.GLFW_PRESS) || (ke.getAction() == GLFW.GLFW_REPEAT);
				events.push(event);
			}
		}
	}
	
	/**
	 * Creates a keyboard with a predefined windowID
	 * @param windowID Window to link to
	 */
	public Keyboard(long windowID) {
		this.windowID = windowID;
		InputBus.register(this);
	}
	
	private long windowID;
	
	public long getWindowID() {
		return windowID;
	}

	public void setWindowID(long windowID) {
		this.windowID = windowID;
	}
	
	/**
	 * The special character meaning that no
	 * character was translated for the event.
	 */
	public static final int CHAR_NONE          = '\0';
	
	/**
	 * The special keycode meaning that only the
	 * translated character is valid.
	 */
	public static final int KEY_NONE            = GLFW.GLFW_KEY_UNKNOWN;

	public static final int KEY_ESCAPE          = GLFW.GLFW_KEY_ESCAPE;
	public static final int KEY_1               = GLFW.GLFW_KEY_1;
	public static final int KEY_2               = GLFW.GLFW_KEY_2;
	public static final int KEY_3               = GLFW.GLFW_KEY_3;
	public static final int KEY_4               = GLFW.GLFW_KEY_4;
	public static final int KEY_5               = GLFW.GLFW_KEY_5;
	public static final int KEY_6               = GLFW.GLFW_KEY_6;
	public static final int KEY_7               = GLFW.GLFW_KEY_7;
	public static final int KEY_8               = GLFW.GLFW_KEY_8;
	public static final int KEY_9               = GLFW.GLFW_KEY_9;
	public static final int KEY_0               = GLFW.GLFW_KEY_0;
	public static final int KEY_MINUS           = GLFW.GLFW_KEY_MINUS; /* - on main keyboard */
	public static final int KEY_EQUALS          = GLFW.GLFW_KEY_EQUAL;
	public static final int KEY_BACK            = GLFW.GLFW_KEY_BACKSPACE; /* backspace */
	public static final int KEY_TAB             = GLFW.GLFW_KEY_TAB;
	public static final int KEY_Q               = GLFW.GLFW_KEY_Q;
	public static final int KEY_W               = GLFW.GLFW_KEY_W;
	public static final int KEY_E               = GLFW.GLFW_KEY_E;
	public static final int KEY_R               = GLFW.GLFW_KEY_R;
	public static final int KEY_T               = GLFW.GLFW_KEY_T;
	public static final int KEY_Y               = GLFW.GLFW_KEY_Y;
	public static final int KEY_U               = GLFW.GLFW_KEY_U;
	public static final int KEY_I               = GLFW.GLFW_KEY_I;
	public static final int KEY_O               = GLFW.GLFW_KEY_O;
	public static final int KEY_P               = GLFW.GLFW_KEY_P;
	public static final int KEY_LBRACKET        = GLFW.GLFW_KEY_LEFT_BRACKET;
	public static final int KEY_RBRACKET        = GLFW.GLFW_KEY_RIGHT_BRACKET;
	public static final int KEY_RETURN          = GLFW.GLFW_KEY_ENTER; /* Enter on main keyboard */
	public static final int KEY_LCONTROL        = GLFW.GLFW_KEY_LEFT_CONTROL;
	public static final int KEY_A               = GLFW.GLFW_KEY_A;
	public static final int KEY_S               = GLFW.GLFW_KEY_S;
	public static final int KEY_D               = GLFW.GLFW_KEY_D;
	public static final int KEY_F               = GLFW.GLFW_KEY_F;
	public static final int KEY_G               = GLFW.GLFW_KEY_G;
	public static final int KEY_H               = GLFW.GLFW_KEY_H;
	public static final int KEY_J               = GLFW.GLFW_KEY_J;
	public static final int KEY_K               = GLFW.GLFW_KEY_K;
	public static final int KEY_L               = GLFW.GLFW_KEY_L;
	public static final int KEY_SEMICOLON       = GLFW.GLFW_KEY_SEMICOLON;
	public static final int KEY_APOSTROPHE      = GLFW.GLFW_KEY_APOSTROPHE;
	public static final int KEY_GRAVE           = GLFW.GLFW_KEY_GRAVE_ACCENT; /* accent grave */
	public static final int KEY_LSHIFT          = GLFW.GLFW_KEY_LEFT_SHIFT;
	public static final int KEY_BACKSLASH       = GLFW.GLFW_KEY_BACKSLASH;
	public static final int KEY_Z               = GLFW.GLFW_KEY_Z;
	public static final int KEY_X               = GLFW.GLFW_KEY_X;
	public static final int KEY_C               = GLFW.GLFW_KEY_C;
	public static final int KEY_V               = GLFW.GLFW_KEY_V;
	public static final int KEY_B               = GLFW.GLFW_KEY_B;
	public static final int KEY_N               = GLFW.GLFW_KEY_N;
	public static final int KEY_M               = GLFW.GLFW_KEY_M;
	public static final int KEY_COMMA           = GLFW.GLFW_KEY_COMMA;
	public static final int KEY_PERIOD          = GLFW.GLFW_KEY_PERIOD; /* . on main keyboard */
	public static final int KEY_SLASH           = GLFW.GLFW_KEY_SLASH; /* / on main keyboard */
	public static final int KEY_RSHIFT          = GLFW.GLFW_KEY_RIGHT_SHIFT;
	public static final int KEY_MULTIPLY        = GLFW.GLFW_KEY_KP_MULTIPLY; /* * on numeric keypad */
	public static final int KEY_LMENU           = GLFW.GLFW_KEY_LEFT_ALT; /* left Alt */
	public static final int KEY_SPACE           = GLFW.GLFW_KEY_SPACE;
	public static final int KEY_CAPITAL         = GLFW.GLFW_KEY_CAPS_LOCK;
	public static final int KEY_F1              = GLFW.GLFW_KEY_F1;
	public static final int KEY_F2              = GLFW.GLFW_KEY_F2;
	public static final int KEY_F3              = GLFW.GLFW_KEY_F3;
	public static final int KEY_F4              = GLFW.GLFW_KEY_F4;
	public static final int KEY_F5              = GLFW.GLFW_KEY_F5;
	public static final int KEY_F6              = GLFW.GLFW_KEY_F6;
	public static final int KEY_F7              = GLFW.GLFW_KEY_F7;
	public static final int KEY_F8              = GLFW.GLFW_KEY_F8;
	public static final int KEY_F9              = GLFW.GLFW_KEY_F9;
	public static final int KEY_F10             = GLFW.GLFW_KEY_F10;
	public static final int KEY_NUMLOCK         = GLFW.GLFW_KEY_NUM_LOCK;
	public static final int KEY_SCROLL          = GLFW.GLFW_KEY_SCROLL_LOCK; /* Scroll Lock */
	public static final int KEY_NUMPAD7         = GLFW.GLFW_KEY_KP_7;
	public static final int KEY_NUMPAD8         = GLFW.GLFW_KEY_KP_8;
	public static final int KEY_NUMPAD9         = GLFW.GLFW_KEY_KP_9;
	public static final int KEY_SUBTRACT        = GLFW.GLFW_KEY_KP_SUBTRACT; /* - on numeric keypad */
	public static final int KEY_NUMPAD4         = GLFW.GLFW_KEY_KP_4;
	public static final int KEY_NUMPAD5         = GLFW.GLFW_KEY_KP_5;
	public static final int KEY_NUMPAD6         = GLFW.GLFW_KEY_KP_6;
	public static final int KEY_ADD             = GLFW.GLFW_KEY_KP_ADD; /* + on numeric keypad */
	public static final int KEY_NUMPAD1         = GLFW.GLFW_KEY_KP_1;
	public static final int KEY_NUMPAD2         = GLFW.GLFW_KEY_KP_2;
	public static final int KEY_NUMPAD3         = GLFW.GLFW_KEY_KP_3;
	public static final int KEY_NUMPAD0         = GLFW.GLFW_KEY_KP_0;
	public static final int KEY_DECIMAL         = GLFW.GLFW_KEY_KP_DECIMAL; /* . on numeric keypad */
	public static final int KEY_F11             = GLFW.GLFW_KEY_F11;
	public static final int KEY_F12             = GLFW.GLFW_KEY_F12;
	public static final int KEY_F13             = GLFW.GLFW_KEY_F13; /*                     (NEC PC98) */
	public static final int KEY_F14             = GLFW.GLFW_KEY_F14; /*                     (NEC PC98) */
	public static final int KEY_F15             = GLFW.GLFW_KEY_F15; /*                     (NEC PC98) */
	public static final int KEY_F16             = GLFW.GLFW_KEY_F16; /* Extended Function keys - (Mac) */
	public static final int KEY_F17             = GLFW.GLFW_KEY_F17;
	public static final int KEY_F18             = GLFW.GLFW_KEY_F18;
	public static final int KEY_F19             = GLFW.GLFW_KEY_F19; /* Extended Function keys - (Mac) */
	public static final int KEY_NUMPADEQUALS    = GLFW.GLFW_KEY_KP_EQUAL; /* = on numeric keypad (NEC PC98) */
	public static final int KEY_NUMPADENTER     = GLFW.GLFW_KEY_KP_ENTER; /* Enter on numeric keypad */
	public static final int KEY_RCONTROL        = GLFW.GLFW_KEY_RIGHT_CONTROL;
	public static final int KEY_NUMPADCOMMA     = GLFW.GLFW_KEY_COMMA; /* , on numeric keypad (NEC PC98) */
	public static final int KEY_DIVIDE          = GLFW.GLFW_KEY_SLASH; /* / on numeric keypad */
	public static final int KEY_SYSRQ           = GLFW.GLFW_KEY_PRINT_SCREEN;
	public static final int KEY_RMENU           = GLFW.GLFW_KEY_RIGHT_ALT; /* right Alt */
	public static final int KEY_PAUSE           = GLFW.GLFW_KEY_PAUSE; /* Pause */
	public static final int KEY_HOME            = GLFW.GLFW_KEY_HOME; /* Home on arrow keypad */
	public static final int KEY_UP              = GLFW.GLFW_KEY_UP; /* UpArrow on arrow keypad */
	public static final int KEY_PRIOR           = GLFW.GLFW_KEY_PAGE_UP; /* PgUp on arrow keypad */
	public static final int KEY_LEFT            = GLFW.GLFW_KEY_LEFT; /* LeftArrow on arrow keypad */
	public static final int KEY_RIGHT           = GLFW.GLFW_KEY_RIGHT; /* RightArrow on arrow keypad */
	public static final int KEY_END             = GLFW.GLFW_KEY_END; /* End on arrow keypad */
	public static final int KEY_DOWN            = GLFW.GLFW_KEY_DOWN; /* DownArrow on arrow keypad */
	public static final int KEY_NEXT            = GLFW.GLFW_KEY_PAGE_DOWN; /* PgDn on arrow keypad */
	public static final int KEY_INSERT          = GLFW.GLFW_KEY_INSERT; /* Insert on arrow keypad */
	public static final int KEY_DELETE          = GLFW.GLFW_KEY_DELETE; /* Delete on arrow keypad */
	public static final int KEY_LMETA           = GLFW.GLFW_KEY_LEFT_SUPER; /* Left Windows/Option key */
	/**
	 * The left windows key, mapped to KEY_LMETA
	 *
	 * @deprecated Use KEY_LMETA instead
	 */
	public static final int KEY_LWIN            = KEY_LMETA; /* Left Windows key */
	public static final int KEY_RMETA            = GLFW.GLFW_KEY_RIGHT_SUPER; /* Right Windows/Option key */
	/**
	 * The right windows key, mapped to KEY_RMETA
	 *
	 * @deprecated Use KEY_RMETA instead
	 */
	public static final int KEY_RWIN            = KEY_RMETA; /* Right Windows key */
	public static final int KEY_SLEEP           = GLFW.GLFW_KEY_PAUSE;
	
	private static final String[] keyName = new String[256];
	
	private static final Map<String, Integer> keyMap = new HashMap<String, Integer>(253);
	@SuppressWarnings("unused")
	private static int counter;

	static {
		// Use reflection to find out key names
		Field[] fields = Keyboard.class.getFields();
		try {
			for ( Field field : fields ) {
				if ( Modifier.isStatic(field.getModifiers())
				     && Modifier.isPublic(field.getModifiers())
				     && Modifier.isFinal(field.getModifiers())
				     && field.getType().equals(int.class)
				     && field.getName().startsWith("KEY_")
				     && !field.getName().endsWith("WIN") ) { /* Don't use deprecated names */

					int key = field.getInt(null);
					String name = field.getName().substring(4);
					keyName[key] = name;
					keyMap.put(name, key);
					counter++;
				}

			}
		} catch (Exception e) {
		}
	}
	
	public Stack<Event> events = new Stack<Event>();
	
	public Event current_event = new Event();

	
	/**
	 * Checks to see if a key is down.
	 * @param key Keycode to check
	 * @return true if the key is down according to the last poll()
	 */
	public boolean isKeyDown(int key) {
		int state = GLFW.glfwGetKey(getWindowID(), key);
		switch (state) {
		//case GLFW.GLFW_REPEAT:
		case GLFW.GLFW_PRESS:
			return true;
		default:
			break;
		}
		return false;
	}
	
	/**
	 * Gets the next keyboard event. You can query which key caused the event by using
	 * <code>getEventKey</code>. To get the state of that key, for that event, use
	 * <code>getEventKeyState</code> - finally use <code>getEventCharacter</code> to get the
	 * character for that event.
	 *
	 * @see com.jediminer543.util.input.Keyboard#getEventKey()
	 * @see com.jediminer543.util.input.Keyboard#getEventKeyState()
	 * @see com.jediminer543.util.input.Keyboard#getEventCharacter()
	 * @return last event or null if no new events
	 */
	public Event nextEvent() {
		if (events.size() > 0) {
			Event old_event = current_event;
			current_event = events.pop();
			return old_event;
		} else {
			return null;
		}
	}
	
	/**
	 * Compatibility method for lwjgl2 conversions, use nextEvent instead for more data;
	 *
	 * @see com.jediminer543.util.input.Keyboard#getEventKey()
	 * @see com.jediminer543.util.input.Keyboard#getEventKeyState()
	 * @see com.jediminer543.util.input.Keyboard#getEventCharacter()
	 * @return last event or null if no new events
	 */
	public boolean next() {
		return this.nextEvent() != null;
	}
	
	/**
	 * @see com.jediminer543.util.input.Keyboard#enableRepeatEvents(boolean)
	 * @return true if the current event is a repeat event, false if
	 * the current event is not a repeat even or if repeat events are disabled.
	 */
	public boolean isRepeatEvent() {
		return this.current_event.repeat;
	}
	
	/**
	 * Gets the time in nanoseconds of the current event.
	 * Only useful for relative comparisons with other
	 * Keyboard events, as the absolute time has no defined
	 * origin.
	 * @return The time in nanoseconds of the current event
	 */
	public long getEventNanoseconds() {
		return this.current_event.nanos;
	}
	
	/**
	 * Gets the state of the key that generated the
	 * current event
	 *
	 * @return True if key was down, or false if released
	 */
	public boolean getEventKeyState() {
			return this.current_event.state;
	}
	
	/**
	 * Please note that the key code returned is NOT valid against the
	 * current keyboard layout. To get the actual character pressed call
	 * getEventCharacter
	 *
	 * @return The key from the current event
	 */
	public int getEventKey() {
		return this.current_event.key;
	}
	
	/**
	 * @return The character from the current event
	 */
	public char getEventCharacter() {
		if (this.current_event.key <= 255) {
		return (char) this.current_event.key;
		} else {
		return CHAR_NONE;
		}
	}
	
	private static final class Event {
		
		/** The current keyboard event key being examined
		 *  As this is GLFW keys are mapped to ascii char codes
		 */
		private int key;
		
		/** The current state of the key being examined in the event queue */
		private boolean state;
		
		/** The current event time */
		private long nanos;
		
		/** Is the current event a repeated event? */
		private boolean repeat;

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
		
			return "Event [key=" + key + ", state=" + state + ", nanos=" + nanos + ", repeat="
					+ repeat + "]";
		}
		
	}
	
	
}
