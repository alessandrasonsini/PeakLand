package logic.model.exception;

public class CurrentUserNotFindException extends Exception{

	/**
	 * Eccezione che viene lanciata quando non viene trovata l'istanza dello user corrente
	 * e non viene passato l'argomento per inizializzarla
	 */
	private static final long serialVersionUID = 1L;
	
	public CurrentUserNotFindException(String message) {
		super(message);
	}

}
