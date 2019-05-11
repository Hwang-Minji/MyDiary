package com.minji.mydiary.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.minji.mydiary.model.db.DatabaseHelper;
import com.minji.mydiary.model.db.FakeDatabaseHelper;
import com.minji.mydiary.model.db.LocalDatabaseHelper;
import com.minji.mydiary.model.db.dao.PostDAO;
import com.minji.mydiary.view.MainView;
import com.minji.mydiary.view.OpenPostView;
import com.minji.mydiary.view.UploadActivity;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {
    private String TAG = "MainPresenterImpl()";
    private DatabaseHelper dbHelper;
    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        dbHelper = LocalDatabaseHelper.getInstance();
        this.mainView = mainView;
    }

    @Override
    public void requestDateList() {
        List<String> dateList = new ArrayList<>();

        List<PostDAO> posts = dbHelper.readPostList();
        for(PostDAO post : posts) {
           dateList.add(post.getDate());
        }

        Log.d(TAG, "before updateTimeline");
        mainView.updateTimeline(dateList);
    }

    @Override
    public void requestPostDAO(String date) {
        PostDAO post = dbHelper.readPost(date);
        Log.d(TAG, "receivedDate: " + post.getDate());
        mainView.newOpenPostActivity(post);
    }

    @Override
    public void requestOpenGallary() {

    }

    @Override
    public void requestOpenUploadActivity(Context context) {
        Intent intent = new Intent(context, UploadActivity.class);
        context.startActivity(intent);
    }
}
