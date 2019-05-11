package com.minji.mydiary.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.minji.mydiary.model.GalleryHelper;
import com.minji.mydiary.model.GalleryHelperImpl;
import com.minji.mydiary.model.db.LocalDatabaseHelper;
import com.minji.mydiary.model.db.dao.PostDAO;
import com.minji.mydiary.view.UploadView;

import java.io.File;

public class UploadPresenterImpl implements UploadPresenter {
    private static final String TAG = "UploadPresentImpl";
    private GalleryHelper galleryHelper = new GalleryHelperImpl();
    private UploadView view;
    private LocalDatabaseHelper localDatabaseHelper = LocalDatabaseHelper.getInstance();

    public UploadPresenterImpl(UploadView view) {
        this.view = view;
    }

    @Override
    public void requestOpenGallery(Activity activity) {
        galleryHelper.openGallery(activity);
    }

    @Override
    public void setImage(File file) {
        view.setImage(file);
    }

    @Override
    public void requestUploadNewPost(String imagePath, String text) {
        Log.d(TAG, "requestUploadNewPost()");
        localDatabaseHelper.writeNewPost(imagePath, text);
    }
}
