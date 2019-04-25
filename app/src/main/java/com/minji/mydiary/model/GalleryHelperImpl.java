package com.minji.mydiary.model;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

public class GalleryHelperImpl implements GalleryHelper {
    private final int GALLERY_CODE = 1112;

    @Override
    public void openGallery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(intent, GALLERY_CODE);
    }
}
