package com.cxyw.suyun.common.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by limw on 16/3/1.
 */
public class CommonApplication extends Application{

    private static Context context;
    @Override
    public void onCreate() {
        context=getApplicationContext();
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }
}
