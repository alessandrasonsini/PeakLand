package logic.bean;

/**
 * Classe che rappresenta la generica bean
 */
public class ItemBean {
	
	private static final String DEFAULT = "Not available"; 
	
	protected ItemBean() {
		
	}
	
	// Converte l'array di stringhe in un'unica stringa
	protected String convertToText(String[] array) {
		String text = "";
		for (int i = 0; i < array.length; i++) {
			if (!array[i].isEmpty())
				text = text.concat(array[i] + " ");
		}
		
		if (text.isEmpty())
			text = DEFAULT;
		
		return text;
	}
	
	// Converte l'oggetto generico in stringa, gestendo correttamente il valore null
	protected String convertToText(Object genericObject) {
		String text;
		if(genericObject == null) {
			text = DEFAULT;
		}
		else {
			text = genericObject.toString().replace("true", "yes").replace("false", "no");
		}
		return text;
	}
	
	protected String convertToTextWithUnitOfMeasure(Object genObject, String unitOfMeasure) {
		String text = convertToText(genObject);
		if(!text.equals(DEFAULT))
			text = text + " " + unitOfMeasure;
		return text;
	}
}
