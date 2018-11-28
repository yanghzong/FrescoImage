package com.zemu.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoJpegActivity extends AppCompatActivity implements View.OnClickListener {


    private SimpleDraweeView sdv_fresco_jpeg;
    private Button sdv_fresco_askImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_jpeg);
        initView();
    }

    private void initView() {
        sdv_fresco_jpeg = findViewById(R.id.sdv_fresco_jpeg);
        sdv_fresco_askImg = findViewById(R.id.sdv_fresco_askImg);

        sdv_fresco_askImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdv_fresco_askImg:
                Uri uri = Uri.parse("https://img04.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg01.sogoucdn.com%2Fapp%2Fa%2F100520024%2F861ed0bc4193dca6ad3b7ceffcdb82e6&appid=122");
                ProgressiveJpegConfig jpegConfig = new ProgressiveJpegConfig() {
                    @Override
                    public int getNextScanNumberToDecode(int scanNumber) {
                        return scanNumber + 2;
                    }

                    @Override
                    public QualityInfo getQualityInfo(int scanNumber) {
                        boolean isGoodEnough = (scanNumber >= 5);

                        return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
                    }
                };
                ImagePipelineConfig.newBuilder(this).setProgressiveJpegConfig(jpegConfig).build();
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)//渐进模式
                        .build();

                AbstractDraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setTapToRetryEnabled(true)
                        .setOldController(sdv_fresco_jpeg.getController())
                        .build();

                sdv_fresco_jpeg.setController(draweeController);

                break;
        }
    }
}
