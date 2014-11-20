package com.jediminer543.util;
import com.jediminer543.util.event.annotation.Init;
import com.jediminer543.util.event.bus.InitBus;

public class EventTest {

	public static void main(String[] args) {
		
		InitBus.register(Test.class);
		InitBus.invoke();

	}
	
	static class Test {
		
		@Init
		public static void Initialise() {
			System.out.println("Events!!");
		}
		
		static {
			
		}
	}

}
