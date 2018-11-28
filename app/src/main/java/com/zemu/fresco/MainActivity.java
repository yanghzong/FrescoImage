package com.zemu.fresco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_fresco_spimg;
    private Button bt_fresco_crop;
    private Button bt_fresco_circleAndCorner;
    private Button bt_fresco_jpeg;
    private Button bt_fresco_gif;
    private Button bt_fresco_multi;
    private Button bt_fresco_listener;
    private Button bt_fresco_resize;
    private Button bt_fresco_updateImg;
    private Button bt_fresco_autoSizeImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt_fresco_spimg =  findViewById(R.id.bt_fresco_spimg);
        bt_fresco_crop =  findViewById(R.id.bt_fresco_crop);
        bt_fresco_circleAndCorner =  findViewById(R.id.bt_fresco_circleAndCorner);
        bt_fresco_jpeg =  findViewById(R.id.bt_fresco_jpeg);
        bt_fresco_gif =  findViewById(R.id.bt_fresco_gif);
        bt_fresco_multi =  findViewById(R.id.bt_fresco_multi);
        bt_fresco_listener =  findViewById(R.id.bt_fresco_listener);
        bt_fresco_resize =  findViewById(R.id.bt_fresco_resize);
        bt_fresco_updateImg =  findViewById(R.id.bt_fresco_updateImg);
        bt_fresco_autoSizeImg =  findViewById(R.id.bt_fresco_autoSizeImg);

        bt_fresco_spimg.setOnClickListener(this);
        bt_fresco_crop.setOnClickListener(this);
        bt_fresco_circleAndCorner.setOnClickListener(this);
        bt_fresco_jpeg.setOnClickListener(this);
        bt_fresco_gif.setOnClickListener(this);
        bt_fresco_multi.setOnClickListener(this);
        bt_fresco_listener.setOnClickListener(this);
        bt_fresco_resize.setOnClickListener(this);
        bt_fresco_updateImg.setOnClickListener(this);
        bt_fresco_autoSizeImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {//带进度条的图片
            case R.id.bt_fresco_spimg:
                Intent spimgIntent = new Intent(this, FrescoSpimgActivity.class);
                startActivity(spimgIntent);
                break;
            case R.id.bt_fresco_crop://对图片进行裁剪的效果

                break;
            case R.id.bt_fresco_circleAndCorner://设置圆形和圆角形图片
                Intent circleIntent = new Intent(this, FrescoCircleAndCornerActivity.class);
                startActivity(circleIntent);
                break;
            case R.id.bt_fresco_jpeg: // 渐进式展示图片
                Intent frescoJpegIntent = new Intent(this, FrescoJpegActivity.class);
                startActivity(frescoJpegIntent);
                break;
            case R.id.bt_fresco_gif://GIF动画图片
                Intent frescoGifIntent = new Intent(this, FrescoGifAcitivity.class);
                startActivity(frescoGifIntent);
                break;
            case R.id.bt_fresco_multi://多图请求及图片复用
                Intent frescoMultiIntent = new Intent(this, FrescoMultiActivity.class);
                startActivity(frescoMultiIntent);
                break;
            case R.id.bt_fresco_listener://图片加载监听
                Intent frescoListenerIntent=new Intent(this,FrescoListenerActivity.class);
                startActivity(frescoListenerIntent);

                break;
            case R.id.bt_fresco_resize://图片修改和旋转
                Intent frescoResizeIntent=new Intent(this,FrescoResizeActivity.class);
                startActivity(frescoResizeIntent);

                break;
            case R.id.bt_fresco_updateImg://修改图片
                Intent frescoUpdateIntent=new Intent(this,FrescoUpdateActivity.class);
                startActivity(frescoUpdateIntent);


                break;
            case R.id.bt_fresco_autoSizeImg://动态设置图片
                Intent frescoAutoSizeIntent = new Intent(this, FrescoAutoSizeActivity.class);
                startActivity(frescoAutoSizeIntent);
                break;
        }
    }
}
