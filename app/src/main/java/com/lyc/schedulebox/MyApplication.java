package com.lyc.schedulebox;

import android.content.Context;

import com.cxyw.suyun.common.utils.CommonApplication;
import com.lyc.schedulebox.common.AppConstants;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by lianyuchen on 16/2/23.
 */
public class MyApplication extends CommonApplication {
    private static MyApplication app;
    private static Context mContext;// 全局context

    public static IWXAPI api;

    public static MyApplication getInstance() {
        return app;
    }

    public static Tencent mTencent;

    public static WeiboAuth mWeiboAuth;

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

        regToWX();
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        mTencent = Tencent.createInstance(AppConstants.QQ_APP_ID, getScheduleBoxApplicationContext());
        // 创建微博实例
        mWeiboAuth = new WeiboAuth(this, AppConstants.WEIBO_APP_KEY, AppConstants.REDIRECT_URL, AppConstants.SCOPE);

    }

    private void regToWX() {
        api = WXAPIFactory.createWXAPI(this, AppConstants.WX_APP_ID, false);

        api.registerApp(AppConstants.WX_APP_ID);
    }

    public static Context getScheduleBoxApplicationContext() {
        return mContext;
    }

}
