package logic.model.dao;

import com.google.firebase.database.DataSnapshot;

public interface OnGetDataListener {

	void onSuccess(DataSnapshot snapshot);
}
