package com.zemu.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoCircleAndCornerActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleDraweeView sdv_fresco_circleandcorner;
    private Button bt_fresco_circle;
    private Button bt_fresco_corner;
    private Uri mUri;
    private GenericDraweeHierarchyBuilder mBuilder;
    private RoundingParams mParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_circle_and_corner);
        initView();

        mUri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");

        //创建Builder对象
        mBuilder = new GenericDraweeHierarchyBuilder(getResources());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fresco_circle://圆形
                //设置形状对象,形状为圆形
                mParams = RoundingParams.asCircle();
                //把形状设置给参数对象
                GenericDraweeHierarchy rounding = mBuilder.setRoundingParams(mParams).build();
                //设置给图片控件
                sdv_fresco_circleandcorner.setHierarchy(rounding);
                //加载图片控件
                sdv_fresco_circleandcorner.setImageURI(mUri);

                break;
            case R.id.bt_fresco_corner:
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(50f);

                GenericDraweeHierarchy build = mBuilder.setRoundingParams(roundingParams).build();
                sdv_fresco_circleandcorner.setHierarchy(build);
                sdv_fresco_circleandcorner.setImageURI(mUri);

                break;
        }
    }











    private void initView() {
        sdv_fresco_circleandcorner = findViewById(R.id.sdv_fresco_circleandcorner);
        bt_fresco_circle = findViewById(R.id.bt_fresco_circle);
        bt_fresco_corner = findViewById(R.id.bt_fresco_corner);

        bt_fresco_circle.setOnClickListener(this);
        bt_fresco_corner.setOnClickListener(this);
    }


}
