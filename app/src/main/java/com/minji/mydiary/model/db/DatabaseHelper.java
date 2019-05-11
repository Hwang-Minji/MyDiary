package com.minji.mydiary.model.db;

import android.graphics.Bitmap;

import com.minji.mydiary.model.db.dao.PostDAO;
import java.util.List;

public interface DatabaseHelper {
    PostDAO readPost(String date);
    List<PostDAO> readPostList();
    void writeNewPost(String imagePath, String text);

}
