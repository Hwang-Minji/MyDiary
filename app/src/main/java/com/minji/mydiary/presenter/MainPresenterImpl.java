package com.minji.mydiary.presenter;

import android.content.Context;
import android.content.Intent;

import com.minji.mydiary.model.db.LocalDatabaseHelper;
import com.minji.mydiary.view.UploadActivity;

public class MainPresenterImpl implements MainPresenter {
    @Override
    public void requestAllDatas() {
        LocalDatabaseHelper.getInstance().getAllDatas();
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
