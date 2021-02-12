package logic.bean;

/**
 * Classe che rappresenta la generica bean
 */
public abstract class ItemBean {
	
	// Converte l'array di stringhe in un'unica stringa
	public String convertToText(String[] array) {
		String text = "";
		
		for (int i = 0; i < array.length; i++) {
			if (!array[i].isEmpty())
				text = text.concat(array[i] + " ");
		}
		
		if (text.isEmpty())
			return "Not available";
		return text;
	}
	
	// Converte l'oggetto generico in stringa, gestendo correttamente il valore null
	public String convertToText(Object genericObject) {
		if(genericObject == null) {
			return "Not available";
		}
		else {
			return genericObject.toString()
					.replace("[", "")
					.replace("]", "")
					.replace("true", "yes")
					.replace("false", "no");
		}
	}

}
