package com.minji.mydiary.presenter;

import android.content.Context;

public interface MainPresenter {
    void requestDateList();
    void requestPostDAO(String date);
    void requestOpenGallary();

    void requestOpenUploadActivity(Context context);
}
