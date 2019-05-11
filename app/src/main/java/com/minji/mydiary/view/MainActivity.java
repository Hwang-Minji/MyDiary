package com.minji.mydiary.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.minji.mydiary.R;
import com.minji.mydiary.model.db.dao.PostDAO;
import com.minji.mydiary.presenter.MainPresenter;
import com.minji.mydiary.presenter.MainPresenterImpl;
import com.minji.mydiary.utils.ContextHolder;

import org.parceler.Parcels;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final String TAG = "MainActivity";
    private Button uploadButton;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContextHolder.setApplicationContext(getApplicationContext());

        presenter = new MainPresenterImpl(this);

        uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.requestOpenUploadActivity(getApplicationContext());
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        presenter.requestDateList();
    }

    private void checkPermissions() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1052);
        }
    }

    @Override
    public void updateTimeline(List<String> dateList) {
        if (adapter == null) {
            adapter = new MyAdapter(dateList, presenter);
            recyclerView.setAdapter(adapter);

            return;
        }

        adapter.setDateList(dateList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void newOpenPostActivity(PostDAO post) {

        Intent intent = new Intent(MainActivity.this, OpenPostActivity.class);
        intent.putExtra("post", Parcels.wrap(post));

        Log.d(TAG, "tossDate: " + post.getDate());
        startActivity(intent);
    }
}
