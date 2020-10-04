package model;

public class Trace {
	
	public enum Level{INFO, WAR, ERR}
	
	private static Level tracelevel;
		
	public static void setTraceLevel(Level level) {
		tracelevel = level;
	}
	
	public static void out(Level level, String text) {
		if(level.ordinal()>=tracelevel.ordinal()) {
			System.out.println(text);
		}
	}

}
