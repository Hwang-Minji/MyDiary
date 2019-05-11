package com.minji.mydiary.model.db;

import android.util.Log;

import com.minji.mydiary.model.db.dao.PostDAO;
import com.minji.mydiary.utils.ContextHolder;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static com.minji.mydiary.utils.ContextHolder.getApplicationContext;

public class FakeDatabaseHelper implements DatabaseHelper {
    private static final String TAG = "FakeDatabaseHelper";
    private Realm realm;

    public FakeDatabaseHelper() {
        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build();

        realm = Realm.getInstance(realmConfiguration);
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
    public List<PostDAO> readPostList() {
        return realm.where(PostDAO.class).findAll();
    }

    @Override
    public PostDAO readPost(String date) {
        return realm.where(PostDAO.class).equalTo("date", date).findFirst();
    }

    @Override
    public void writeNewPost(String imagePath, String text) {

    }
}
