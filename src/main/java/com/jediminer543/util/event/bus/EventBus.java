package com.jediminer543.util.event.bus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class EventBus {
	protected static Map<Object, Method> methods = new HashMap<Object, Method>();
}