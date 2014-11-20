package com.jediminer543.util.event.bus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import com.jediminer543.util.event.InputEvent;
import com.jediminer543.util.event.annotation.Input;

public class InputBus extends EventBus {
	
	public static void register(Class<?> thing) {
		ArrayList<Method> definedMethods = new ArrayList<Method>(Arrays.asList(thing.getDeclaredMethods()));
		for (Method m: definedMethods) {
			if (m.isAnnotationPresent(Input.class) && Modifier.isStatic(m.getModifiers())) {
				m.setAccessible(true);
				methods.put(null, m);
			}
		}
		
	}
	
	public static void register(Object thing) {
		ArrayList<Method> definedMethods = new ArrayList<Method>(Arrays.asList(thing.getClass().getDeclaredMethods()));
		for (Method m: definedMethods) {
			if (m.isAnnotationPresent(Input.class) && !Modifier.isStatic(m.getModifiers())) {
				m.setAccessible(true);
				methods.put(thing, m);
			}
		}
		
	}
	
	public static void invoke(InputEvent ie) {
		Iterator<Entry<Object, Method>> it = methods.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Method> pair = it.next();
				try {
					pair.getValue().invoke(pair.getKey(), ie);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.err.println("Someone mis-registered an init method; Stack Trace aproching");
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		}
	}
}
