package com.minji.mydiary.presenter;

import android.app.Activity;
import android.content.Intent;

import java.io.File;

public interface UploadPresenter {
    void requestOpenGallery(Activity activity);
    void setPhoto(File file);
    void requestUploadNewPost();
}
