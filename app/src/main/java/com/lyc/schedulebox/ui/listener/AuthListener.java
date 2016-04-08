package com.lyc.schedulebox.ui.listener;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.utils.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * Created by lianyuchen on 16/4/8.
 */
public class AuthListener implements WeiboAuthListener {

    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;
    private Context context;

    public AuthListener(Context context) {
        this.context = context;
    }


    @Override
    public void onComplete(Bundle bundle) {
        // 从 Bundle 中解析 Token
        mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
        if (mAccessToken.isSessionValid()) {
            // 显示 Token
            //updateTokenView(false);

            // 保存 Token 到 SharedPreferences
            AccessTokenKeeper.writeAccessToken(context, mAccessToken);
            Toast.makeText(context,
                    R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
        } else {
            // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
            String code = bundle.getString("code");
            String message = context.getString(R.string.weibosdk_demo_toast_auth_failed);
            if (!TextUtils.isEmpty(code)) {
                message = message + "\nObtained the code: " + code;
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onWeiboException(WeiboException e) {
        Toast.makeText(context,
                "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCancel() {
        Toast.makeText(context,
                R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
    }
}
