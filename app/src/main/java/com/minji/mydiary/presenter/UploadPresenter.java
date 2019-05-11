package com.minji.mydiary.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import com.minji.mydiary.model.db.dao.PostDAO;

import java.io.File;

public interface UploadPresenter {
    void requestOpenGallery(Activity activity);
    void setImage(File file);
    void requestUploadNewPost(String imagePath, String text);
}
