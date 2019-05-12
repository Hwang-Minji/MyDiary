package com.minji.mydiary.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.minji.mydiary.R;
import com.minji.mydiary.model.db.dao.PostDAO;

import org.parceler.Parcels;

public class OpenPostActivity extends AppCompatActivity implements OpenPostView{
    private static final String TAG = "OpenPostActivityClass";
    private TextView dateTextView;
    private ImageView imageView;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_post);

        Intent intent = getIntent();
        PostDAO post = (PostDAO) Parcels.unwrap(intent.getParcelableExtra("post"));
        Log.d(TAG, "post.getDate: " + post.getDate());

        dateTextView = findViewById(R.id.opened_date);
        imageView = findViewById(R.id.opened_image);
        contentTextView = findViewById(R.id.opened_content);

        setDate(post.getDate());
        setImage(post.getImagePath());
        setContent(post.getText());
    }

    private void setDate(String date) {
        Log.d(TAG, "date: " + date);
        dateTextView.setText(date.substring(0,10));
    }

    private void setImage(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

        imageView.setImageBitmap(bitmap);
    }

    private void setContent(String content) {
        contentTextView.setText(content);
    }
}