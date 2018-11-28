package com.zemu.fresco;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.File;

public class FrescoMultiActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView sdv_fresco_multi;
    private Button bt_fresco_multiImg;
    private Button bt_fresco_thumbnailImg;
    private Button bt_fresco_multiplexImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_multi);
        initView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fresco_multiImg:
                Uri lowUri = Uri.parse("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=91883135,2320948421&fm=111&gp=0.jpg");
                Uri highUri = Uri.parse("https://img5.duitang.com/uploads/item/201312/03/20131203153823_Y4y8F.jpeg");
                AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setLowResImageRequest(ImageRequest.fromUri(lowUri))
                        .setImageRequest(ImageRequest.fromUri(highUri))
                        .build();
                sdv_fresco_multi.setController(controller);

                break;
            case R.id.bt_fresco_thumbnailImg:
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "big.jpg"));

                break;
            case R.id.bt_fresco_multiplexImg:

                break;
        }
    }




    private void initView() {
        sdv_fresco_multi = findViewById(R.id.sdv_fresco_multi);
        bt_fresco_multiImg =findViewById(R.id.bt_fresco_multiImg);
        bt_fresco_thumbnailImg =  findViewById(R.id.bt_fresco_thumbnailImg);
        bt_fresco_multiplexImg = findViewById(R.id.bt_fresco_multiplexImg);

        bt_fresco_multiImg.setOnClickListener(this);
        bt_fresco_thumbnailImg.setOnClickListener(this);
        bt_fresco_multiplexImg.setOnClickListener(this);
    }
}
