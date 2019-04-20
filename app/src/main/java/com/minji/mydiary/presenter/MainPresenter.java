package com.minji.mydiary.presenter;

import android.content.Context;

public interface MainPresenter {
    void requestAllDatas();
    void requestOpenGallary();

    void requestOpenUploadActivity(Context context);
}
