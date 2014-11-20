package com.jediminer543.util.event.bus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import com.jediminer543.util.event.annotation.Init;

public class InitBus extends EventBus {
	
	public static void register(Class<?> thing) {
		ArrayList<Method> definedMethods = new ArrayList<Method>(Arrays.asList(thing.getDeclaredMethods()));
		for (Method m: definedMethods) {
			if (m.isAnnotationPresent(Init.class) && Modifier.isStatic(m.getModifiers())) {
				m.setAccessible(true);
				methods.put(null, m);
			}
		}
		
	}
	
	public static void invoke() {
		Iterator<Entry<Object, Method>> it = methods.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Method> pair = it.next();
				try {
					pair.getValue().invoke(pair.getKey());
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
