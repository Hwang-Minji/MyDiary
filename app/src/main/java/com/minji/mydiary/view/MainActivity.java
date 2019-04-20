package com.minji.mydiary.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.minji.mydiary.R;
import com.minji.mydiary.presenter.MainPresenter;
import com.minji.mydiary.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainView {
    private Button uploadButton;
    private MainPresenter presenter = new MainPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.requestOpenUploadActivity(getApplicationContext());
            }
        });
    }
}
