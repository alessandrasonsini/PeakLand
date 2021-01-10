package logic.model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public abstract class Dao implements OnGetDataListener {
	
	private static final Logger LOGGER = Logger.getLogger(Dao.class.getName());
	
	private DatabaseConnection dbConnection;
	
	static final Object lockObject = new Object(); 
	static boolean condition = false;
	
	
	protected Dao() {
		this.dbConnection = DatabaseConnection.getInstance();
	}
	
	protected DatabaseReference getSpecificReference() {
		return this.dbConnection.getDatabaseReference().child(this.getChild());
	}
	
	// Effettua la query sul db e sulla risposta asincrona richiama il metodo onSuccess definito 
	// nelle classi dao figlie

	public void readData(Query query) {
		
		query.addListenerForSingleValueEvent(new ValueEventListener() {
	        @Override
	        public void onDataChange(DataSnapshot dataSnapshot) {
	        	onSuccess(dataSnapshot);
	            synchronized (lockObject) {
	            	lockObject.notifyAll();
	            	condition = true;
				}
	        }

	        @Override
	        public void onCancelled(DatabaseError firebaseError) {
	            LOGGER.log(Level.FINE, "Firebase error");
	        }
	    });
		synchronized (lockObject) {
			try {
				while(!condition) {
					lockObject.wait();
				}
				
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.log(Level.SEVERE,e.toString(),e);
			}
		}

	}
	
	@Override
	public abstract void onSuccess(DataSnapshot dataSnapshot);
	
	protected abstract String getChild(); 
}
