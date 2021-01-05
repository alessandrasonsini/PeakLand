package logic.model.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public abstract class Dao implements OnGetDataListener {
	
	final static Object lockObject = new Object(); 
	
	// Effettua la query sul db e sulla risposta asincrona richiama il metodo onSuccess definito 
	// nelle classi dao figlie

	public void readData(Query query) {
		
		query.addListenerForSingleValueEvent(new ValueEventListener() {
	        @Override
	        public void onDataChange(DataSnapshot dataSnapshot) {
	            onSuccess(dataSnapshot);
	            synchronized (lockObject) {
	            	lockObject.notifyAll();
				}
	        }

	        @Override
	        public void onCancelled(DatabaseError firebaseError) {
	            System.out.println("Read data error");
	        }
	    });
		synchronized (lockObject) {
			try {
				lockObject.wait();
			} catch (InterruptedException e) {
				System.out.println("Eccezione");
				Thread.currentThread().interrupt();
				//e.printStackTrace();
			}
		}

	}
	
	@Override
	public abstract void onSuccess(DataSnapshot snapshot);
}
