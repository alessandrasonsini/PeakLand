package logic.model.dao;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseConnection {
	/**
	 * Classe singleton contenente la connessione al realtime database di firebase
	 */
	
	private FirebaseDatabase firebaseDb;
	private DatabaseReference databaseReference;
	private Bucket bucketReference;
	private Storage storageReference;
	
	private static DatabaseConnection instance = null;
	
	private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
	
	private DatabaseConnection() {
		initializeConnection();
		this.firebaseDb = FirebaseDatabase.getInstance();
		this.databaseReference = firebaseDb.getReference();
		this.bucketReference = StorageClient.getInstance().bucket();
		this.storageReference = this.bucketReference.getStorage();
		
	}
	
	public static DatabaseConnection getInstance() {
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}
	
	public DatabaseReference getDatabaseReference() {
		return this.databaseReference;
	}
	
	public Bucket getBucketReference() {
		return this.bucketReference;
	}
	
	public Storage getStorageReference() {
		return this.storageReference;
	}
	
	private void initializeConnection() {
		
		try (InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("peakland-54c42-firebase-adminsdk-cihqn-dace282633.json")) {
			FirebaseOptions options = FirebaseOptions.builder()
				    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				    .setDatabaseUrl("https://peakland-54c42-default-rtdb.europe-west1.firebasedatabase.app/")
				    .setStorageBucket("peakland-54c42.appspot.com")
				    .build();
			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
	
}
