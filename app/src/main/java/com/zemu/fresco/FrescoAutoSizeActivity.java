package com.zemu.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoAutoSizeActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_fresco;
    private Button bt_fresco_loadsmall;
    private SimpleDraweeView simpleDraweeView;
    private String urlimg="http://ww4.sinaimg.cn/mw690/9844520fjw1f4fqrpw1fvj21911wlb2b.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_auto_size);
        initView();
        simpleDraweeView = new SimpleDraweeView(this);

        simpleDraweeView.setAspectRatio(1f);

    }

    private void initView() {
        bt_fresco_loadsmall = findViewById(R.id.bt_fresco_loadsmall);
        ll_fresco = findViewById(R.id.ll_fresco);
        bt_fresco_loadsmall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_fresco_loadsmall:
                Uri uri=Uri.parse(urlimg);
                ImageRequest request= ImageRequestBuilder.newBuilderWithSource(uri).build();
                AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                        .setOldController(simpleDraweeView.getController())
                        .setImageRequest(request)
                        .build();
                if (build!=null){
                    simpleDraweeView.setController(build);
                    ll_fresco.addView(simpleDraweeView);
                    break;

                }

        }
    }
}
