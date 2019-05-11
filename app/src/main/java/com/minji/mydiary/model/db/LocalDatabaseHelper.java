package com.minji.mydiary.model.db;

import android.graphics.Bitmap;
import android.util.Log;

import com.minji.mydiary.model.db.dao.PostDAO;
import com.minji.mydiary.utils.ContextHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.minji.mydiary.utils.ContextHolder.getApplicationContext;

public class LocalDatabaseHelper implements DatabaseHelper {
    private static final String TAG = "LocalDatabaseHelper";
    private RealmConfiguration realmConfiguration;
    private Realm realm;
    private Lock reentrantLock = new ReentrantLock();

    private LocalDatabaseHelper() {
        Realm.init(getApplicationContext());

        realmConfiguration =
                new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build();

        realm = Realm.getInstance(realmConfiguration);
    }

    private static class LazyHolder {
        private static final LocalDatabaseHelper databaseHelper = new LocalDatabaseHelper();
    }

    public static LocalDatabaseHelper getInstance() {
        return LazyHolder.databaseHelper;
    }

    private PostDAO createPostDAO(String date, String imagePath, String text) {
        PostDAO postDAO = new PostDAO.Builder()
                .setDate(date)
                .setText(text)
                .setImagePath(imagePath)
                .build();

        return postDAO;
    }

    @Override
    public PostDAO readPost(String date) {
        return realm.where(PostDAO.class).equalTo("date", date).findFirst();
    }

    @Override
    public List<PostDAO> readPostList() {
        return realm.where(PostDAO.class).findAll();
    }

    @Override
    public void writeNewPost(String imagePath, String text) {
        reentrantLock.lock();

        realm.beginTransaction();
        realm.copyToRealm(createPostDAO(getStringDate(), imagePath, text));
        realm.commitTransaction();

        Log.d(TAG, "writeNewPost()");
        reentrantLock.unlock();
        Log.d(TAG, "DB num: " + realm.where(PostDAO.class).count());
    }

    private String getStringDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm:sss");

        return simpleDateFormat.format(date);
    }
}
