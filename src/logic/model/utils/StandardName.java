package logic.model.utils;

public class StandardName {
	
	private StandardName() {
		// Costruttore privato per classe di utils con solo metodi statici
	}
	
	public static String standardizeName(String name) {
		if (name != null && name.length() > 0) {
			return name.replaceFirst(name.substring(0,1), name.substring(0,1).toUpperCase());
		}
		else 
			return name;
	}
}
