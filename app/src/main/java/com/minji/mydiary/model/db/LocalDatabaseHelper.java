package com.minji.mydiary.model.db;

import android.util.Log;

import com.minji.mydiary.model.db.dao.PostDAO;
import com.minji.mydiary.utils.ContextHolder;

import io.realm.Realm;
import io.realm.RealmResults;

public class LocalDatabaseHelper {
    private static final String TAG = "LocalDatabaseHelper";
    private Realm realm;

    private LocalDatabaseHelper() {
    }

    private static class LazyHolder {
        private static final LocalDatabaseHelper databaseHelper = new LocalDatabaseHelper();
    }

    public static LocalDatabaseHelper getInstance() {
        return LazyHolder.databaseHelper;
    }

    public void getAllDatas() {
        realm = Realm.getInstance(ContextHolder.getApplicationContext());

        RealmResults<PostDAO> postList = realm.where(PostDAO.class).findAll();
        Log.d(TAG, "size : " + postList.size());
    }
}
