package logic.model.bean;

/**
 * Classe che rappresenta la generica bean
 */
public abstract class ItemBean {
	
	// Converte l'oggetto generico in stringa, gestendo correttamente il valore null
	public String convertToText(Object genericObject) {
		if(genericObject == null) {
			return "Not available";
		}
		else {
			return genericObject.toString();
		}
	}

}
