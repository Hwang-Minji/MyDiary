package com.minji.mydiary.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.minji.mydiary.R;
import com.minji.mydiary.presenter.UploadPresenter;
import com.minji.mydiary.presenter.UploadPresenterImpl;

import java.io.File;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity implements UploadView {

    private static final String TAG = "UploadActivity";
    private static final int GALLERY_CODE = 1112;
    private UploadPresenter presenter = new UploadPresenterImpl(this);
    private ImageView uploadedImgView;
    private android.support.v7.widget.Toolbar uploadedToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadedToolBar = findViewById(R.id.uploaded_toolbar);
        setSupportActionBar(uploadedToolBar);

        presenter.requestOpenGallery(this);
        uploadedImgView = findViewById(R.id.uploaded_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.uploaded_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.uploaded_menu_item :
                //presenter 호출
                //presenter가 DB에 올림
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == GALLERY_CODE) {
           Log.d(TAG, "onActivityResult");
           Uri photoUri = data.getData();
           Cursor cursor = null;
           File file = null;

           try {
               String[] proj = { MediaStore.Images.Media.DATA };
               cursor = getContentResolver().query(photoUri, proj, null, null, null);

               int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
               cursor.moveToFirst();
               file = new File(cursor.getString(colIndex));

           } finally {
               if(cursor != null) {
                   cursor.close();
               }
           }

           setPhoto(file);
       }
    }

    @Override
    public void setPhoto(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        uploadedImgView.setImageBitmap(originBitmap);
    }
}