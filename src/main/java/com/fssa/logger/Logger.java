package com.fssa.logger;

public class Logger {
	private static Logger getLogger() {
		return new Logger();
	}
public static void info(Object obj) {
	System.out.println(obj);
}
}
