package logic.model;

public class StandardName {
	
	public static String standardizeName(String name) {
		if (name != null && name.length() > 0) {
			return name.replaceFirst(name.substring(0,1), name.substring(0,1).toUpperCase());
		}
		else 
			return name;
	}
}
