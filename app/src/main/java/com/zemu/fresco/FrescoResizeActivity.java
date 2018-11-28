package com.zemu.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoResizeActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView sdvFrescoReSize;
    private Button btnZhuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feesco_resize);
        initView();
        Uri uri=Uri.parse("https://img02.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg03.sogoucdn.com%2Fapp%2Fa%2F100520024%2F735eef77c24f820d6d66f54e3e37081d&appid=122");

        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(uri))
                .build();
        sdvFrescoReSize.setController(controller);
    }

    private void initView() {
        btnZhuan = findViewById(R.id.btn_xuanzhuan);
        sdvFrescoReSize = findViewById(R.id.sdv_fresco_ReSize);
        btnZhuan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_xuanzhuan:









                break;
        }
    }
}
