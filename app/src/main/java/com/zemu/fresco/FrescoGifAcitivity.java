package com.zemu.fresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoGifAcitivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView sdv_fresco_gif;
    private Button bt_fresco_askImg;
    private Button bt_fresco_stopAnim;
    private Button bt_fresco_startAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_gif_acitivity);
        initView();
    }

    private void initView() {
        sdv_fresco_gif =  findViewById(R.id.sdv_fresco_gif);
        bt_fresco_askImg =  findViewById(R.id.bt_fresco_askImg);
        bt_fresco_stopAnim =  findViewById(R.id.bt_fresco_stopAnim);
        bt_fresco_startAnim = findViewById(R.id.bt_fresco_startAnim);

        bt_fresco_askImg.setOnClickListener(this);
        bt_fresco_stopAnim.setOnClickListener(this);
        bt_fresco_startAnim.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fresco_askImg:
                Uri uri = Uri.parse("https://img01.sogoucdn.com/net/a/04/link?url=https%3A%2F%2Fi01picsos.sogoucdn.com%2Fefdc50758ef1dcee&appid=122");

                AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(false)
                        .setOldController(sdv_fresco_gif.getController())//内存优化
                        .build();

                sdv_fresco_gif.setController(controller);

                break;
            case R.id.bt_fresco_stopAnim:

                break;
            case R.id.bt_fresco_startAnim:
                if (sdv_fresco_gif.getController() != null) {
                    Animatable animatable = sdv_fresco_gif.getController().getAnimatable();

                    if (animatable != null && !animatable.isRunning()) {
                        animatable.start();
                    }
                }


                break;
        }
    }
}
