package logic.model.dao;

import java.util.Map;
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
	protected DatabaseReference dbReference;
	
	private boolean writeCompleted;
	
	//private final Object lockObject = new Object(); 
	private Boolean readLock = false;
	private Boolean writeLock = false;
	
	protected Dao() {
		this.dbConnection = DatabaseConnection.getInstance();
		this.dbReference = this.dbConnection.getDatabaseReference().child(this.getChild());
	}
	
	protected DatabaseReference getSpecificReference() {
		return this.dbConnection.getDatabaseReference().child(this.getChild());
	}
	
	// Effettua la query sul db e sulla risposta asincrona richiama il metodo onSuccess implementato
	// nelle classi dao figlie

	public void readData(Query query) {
		query.addListenerForSingleValueEvent(new ValueEventListener() {
	        @Override
	        public void onDataChange(DataSnapshot dataSnapshot) {
	        	onReadSuccess(dataSnapshot);
	            synchronized (readLock) {
	            	readLock.notifyAll();
	            	readLock = true;
				}
	        }

	        @Override
	        public void onCancelled(DatabaseError firebaseError) {
	            LOGGER.log(Level.FINE, "Firebase error");
	        }
	    });
		synchronized (readLock) {
			try {
				while(!readLock) {
					readLock.wait();
				}
				readLock = false;
				
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.log(Level.SEVERE,e.toString(),e);
			}
		}

	}
	
	public boolean writeData(Map<String, Object> data) {
		
		this.dbReference.updateChildren(data, new DatabaseReference.CompletionListener() {
			@Override
			public void onComplete(DatabaseError error, DatabaseReference ref) {
				if(error != null) {
					writeCompleted = false;
				}
				else writeCompleted = true;
				synchronized (writeLock) {
					writeLock.notifyAll();
					writeLock = true;
				}
			}
		});
		synchronized (writeLock) {
			try {
				while(!writeLock) {
					writeLock.wait();
				}
				writeLock = false;
				
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.log(Level.SEVERE,e.toString(),e);
			}
		}
		return writeCompleted;
		
	}
	
	
	
	@Override
	public abstract void onReadSuccess(DataSnapshot dataSnapshot);
	
	protected abstract String getChild(); 
}
