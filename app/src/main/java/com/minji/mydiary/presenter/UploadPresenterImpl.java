package com.minji.mydiary.presenter;

import android.app.Activity;
import android.content.Intent;

import com.minji.mydiary.model.GalleryHelper;
import com.minji.mydiary.model.GalleryHelperImpl;
import com.minji.mydiary.model.db.LocalDatabaseHelper;
import com.minji.mydiary.view.UploadView;

import java.io.File;

public class UploadPresenterImpl implements UploadPresenter {
    private GalleryHelper galleryHelper = new GalleryHelperImpl();
    private UploadView view;
    private LocalDatabaseHelper localDatabaseHelper;

    public UploadPresenterImpl(UploadView view) {
        this.view = view;
    }

    @Override
    public void requestOpenGallery(Activity activity) {
        galleryHelper.openGallery(activity);
    }

    @Override
    public void setPhoto(File file) {
        view.setPhoto(file);
    }

    @Override
    public void requestUploadNewPost() {

    }
}
