package com.fssa.charitytrust.logger;

public class Logger { 
	private static Logger getLogger() {
		return new Logger();
	}
public static void info(Object obj) {
	System.out.println(obj);
}
}
