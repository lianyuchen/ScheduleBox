package com.lyc.schedulebox;

import android.content.Context;

import com.cxyw.suyun.common.utils.CommonApplication;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by lianyuchen on 16/2/23.
 */
public class MyApplication extends CommonApplication {
    private static MyApplication app;
    private static Context mContext;// 全局context
    public static MyApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        app = this;
        mContext = getApplicationContext();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/HYQiH2312F45.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static Context getScheduleBoxApplicationContext() {
        return mContext;
    }

}
