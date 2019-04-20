package com.minji.mydiary.presenter;

import android.app.Activity;
import android.content.Intent;

public interface UploadPresenter {
    void requestOpenGallery(Activity activity);
    void setPhoto(Intent intent);
}
