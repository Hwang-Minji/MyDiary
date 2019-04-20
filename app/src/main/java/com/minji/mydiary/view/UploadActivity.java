package com.minji.mydiary.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.minji.mydiary.R;
import com.minji.mydiary.presenter.UploadPresenter;
import com.minji.mydiary.presenter.UploadPresenterImpl;

import java.io.IOException;

public class UploadActivity extends AppCompatActivity implements UploadView {

    private static final String TAG = "UploadActivity";
    private final int GALLERY_CODE = 1112;
    private UploadPresenter presenter = new UploadPresenterImpl(this);
    private ImageView uploadedImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        presenter.requestOpenGallery(this);

        uploadedImgView = findViewById(R.id.uploaded_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult()");

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    presenter.setPhoto(data);
                    break;

            }
        }
    }

    @Override
    public void setPhoto(Intent intent) {
        Log.d(TAG, "setPhoto()");
        Uri imgUri = intent.getData();

        String imagePath = getRealPathFromURI(imgUri);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        uploadedImgView.setImageBitmap(bitmap);
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private String getRealPathFromURI(Uri contentUri) {
        int colIndex = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);

        if(cursor.moveToFirst()) {
            colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(colIndex);
    }
}
