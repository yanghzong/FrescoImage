package com.zemu.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

//加载一张网络的图片,在加载的时候有进度条的显示,加载完毕后进度条消失

public class FrescoSpimgActivity extends AppCompatActivity {
    private String urlImg="https://img02.sogoucdn.com/app/a/100520024/f9bb87da65e9b04cbd5b439b64697ad7";
    private SimpleDraweeView sdvFrescoSpimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_spimg);
        //初始化控件
        initView();
        //要加载的图片
        Uri uri=Uri.parse("urlImg");

        //创建Build对象
        GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder=new GenericDraweeHierarchyBuilder(getResources());

        //通过对象设置样式
        GenericDraweeHierarchy build=genericDraweeHierarchyBuilder.setProgressBarImage(new ProgressBarDrawable()).build();


        //将样式设置给图片的控件
        sdvFrescoSpimg.setHierarchy(build);

        //加载图片成功
        sdvFrescoSpimg.setImageURI(uri);

    }

    private void initView() {
        sdvFrescoSpimg = findViewById(R.id.sdv_fresco_spimg);
    }
}
