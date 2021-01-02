package logic.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseConnection {
	/**
	 * Classe singleton contenente la connessione al realtime database di firebase
	 */
	
	//String databaseId = "peakland-54c42-default-rtdb";
	FirebaseDatabase firebaseDb;
	DatabaseReference databaseReference;
	
	private static DatabaseConnection instance = null;
	
	private DatabaseConnection() {
		initializeConnection();
		this.firebaseDb = FirebaseDatabase.getInstance();
		this.databaseReference = firebaseDb.getReference();
	}
	
	public static DatabaseConnection getInstance() {
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}
	
	public DatabaseReference getDatabaseReference() {
		return databaseReference;
	}
	
	public void initializeConnection() {
		try (FileInputStream serviceAccount = new FileInputStream(new java.io.File( "." ).getCanonicalPath() + "\\peakland-54c42-firebase-adminsdk-cihqn-dace282633.json")) {
			FirebaseOptions options = FirebaseOptions.builder()
				    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				    .setDatabaseUrl("https://peakland-54c42-default-rtdb.europe-west1.firebasedatabase.app/")
				    .build();
			FirebaseApp.initializeApp(options);
			
			String current = new java.io.File( "." ).getCanonicalPath() + "\\src\\logic\\model\\peakland-54c42-firebase-adminsdk-cihqn-dace282633.json" ;
	        System.out.println("Current dir:"+current);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Errore file");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Errore generico");
		}
	}
	
}
