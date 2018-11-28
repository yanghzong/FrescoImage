package com.zemu.fresco.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 择木 on 2018/11/27.
 */

public class MyApplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
