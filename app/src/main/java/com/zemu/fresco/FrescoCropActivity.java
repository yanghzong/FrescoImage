package com.zemu.fresco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoCropActivity extends AppCompatActivity {

    private Button btFrescoCrop;
    private SimpleDraweeView sdvFrescoCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_crop);
        sdvFrescoCrop = findViewById(R.id.sdv_fresco_crop);
        btFrescoCrop = findViewById(R.id.bt_fresco_crop);
    }
}
