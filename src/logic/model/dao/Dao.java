package logic.model.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public abstract class Dao implements OnGetDataListener {

	// Effettua la query sul db e sulla risposta asincrona richiama il metodo onSuccess definito 
	// nelle classi dao figlie

	public void readData(Query query) {
		
		Object lockObject = new Object();
		
		query.addListenerForSingleValueEvent(new ValueEventListener() {
	        @Override
	        public void onDataChange(DataSnapshot dataSnapshot) {
	            onSuccess(dataSnapshot);
	            synchronized (lockObject) {
	            	lockObject.notify();
				}
	            System.out.println("Sblocco il thread");
	        }

	        @Override
	        public void onCancelled(DatabaseError firebaseError) {
	            
	        }
	    });
		synchronized (lockObject) {
			try {
				System.out.println("Blocco il thread");
				lockObject.wait();
				System.out.println("Sono sbloccato");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Eccezione");
				//e.printStackTrace();
			}
		}
		
		
		System.out.println("Okay");

	}
	
	@Override
	public abstract void onSuccess(DataSnapshot snapshot);
}
