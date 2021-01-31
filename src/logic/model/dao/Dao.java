package logic.model.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Blob.BlobSourceOption;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import logic.model.exception.SystemException;


public abstract class Dao implements OnGetDataListener {
	
	private static final Logger LOGGER = Logger.getLogger(Dao.class.getName());
	
	private DatabaseConnection dbConnection;
	protected DatabaseReference dbReference;
	private Bucket bucketReference;
	private Storage storageRefence;
	
	private boolean writeCompleted;
	
	private static final Object readLock = new Object(); 
	private static final Object writeLock = new Object();
	
	private boolean readLockCondition = false;
	private boolean writeLockCondition = false;
	
	protected Dao() {
		this.dbConnection = DatabaseConnection.getInstance();
		this.dbReference = this.dbConnection.getDatabaseReference().child(this.getChild());
		this.bucketReference = this.dbConnection.getBucketReference();
		this.storageRefence = this.dbConnection.getStorageReference();
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
	            	readLockCondition = true;
	           	}
	        }

	        @Override
	        public void onCancelled(DatabaseError firebaseError) {
	            LOGGER.log(Level.FINE, "Firebase error");
	        }
	    });
		synchronized (readLock) {
			try {
				while(!readLockCondition) {
					readLock.wait();
				}
				readLockCondition = false;
				
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
					writeLockCondition = true;
				}
			}
		});
		synchronized (writeLock) {
			try {
				while(!writeLockCondition) {
					writeLock.wait();
				}
				writeLockCondition = false;
				
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.log(Level.SEVERE,e.toString(),e);
			}
		}
		return writeCompleted;
	}
	
	public List<ByteArrayInputStream> getImagesStream(String directory) {
		Page<Blob> blobs = this.bucketReference.list(Storage.BlobListOption.prefix(directory));
		List<ByteArrayInputStream> imageStreams = new ArrayList<>();
		for(Blob blob : blobs.iterateAll()) {
			imageStreams.add(new ByteArrayInputStream(blob.getContent(BlobSourceOption.generationMatch())));
		}
		return imageStreams;
	}
	
	public void uploadImage(List<File> images, String directory) throws SystemException {
		int imgNumber = images.size();
		for(int i = 0; i < imgNumber; i++) {
			try {
				this.bucketReference.create(directory + "/" + i + ".jpeg", new FileInputStream(images.get(i)),Bucket.BlobWriteOption.doesNotExist());
			} catch (FileNotFoundException e) {
				throw new SystemException();
			}
		}
	}
	
	public ByteArrayInputStream getImage(String fileName) {
		Blob blob = this.storageRefence.get(this.bucketReference.getName(),fileName);
		ByteArrayInputStream imageReturn = null;
		if(blob != null)
			imageReturn = new ByteArrayInputStream(blob.getContent(BlobSourceOption.generationMatch()));
		return imageReturn;
	}
	
	public void uploadImage(FileInputStream f, String fileName) {
		this.bucketReference.create(fileName,f,Bucket.BlobWriteOption.doesNotExist());
	}
	
	public void deleteImage(String fileToDelete) {
		this.storageRefence.delete(BlobId.of(this.bucketReference.getName(),fileToDelete));
	}
	
	@Override
	public abstract void onReadSuccess(DataSnapshot dataSnapshot);
	
	protected abstract String getChild(); 
	
}
