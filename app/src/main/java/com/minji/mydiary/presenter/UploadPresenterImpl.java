package com.minji.mydiary.presenter;

import android.app.Activity;
import android.content.Intent;

import com.minji.mydiary.model.GalleryHelper;
import com.minji.mydiary.model.GalleryHelperImpl;
import com.minji.mydiary.view.UploadView;

public class UploadPresenterImpl implements UploadPresenter {
    private GalleryHelper galleryHelper = new GalleryHelperImpl();
    private UploadView view;

    public UploadPresenterImpl(UploadView view) {
        this.view = view;
    }

    @Override
    public void requestOpenGallery(Activity activity) {
        galleryHelper.openGallery(activity);
    }

    @Override
    public void setPhoto(Intent intent) {
        view.setPhoto(intent);
    }
}
