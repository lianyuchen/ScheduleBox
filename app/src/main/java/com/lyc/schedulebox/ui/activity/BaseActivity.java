package com.lyc.schedulebox.ui.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.R;
import com.lyc.schedulebox.common.AppConstants;
import com.lyc.schedulebox.view.IBaseView;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by lianyuchen on 16/2/23.
 */
public class BaseActivity extends AppCompatActivity implements IBaseView{
    private ImageButton mBack;
    private TextView mTitle;
    private TextView mRight;
    protected Resources resources;

    /** 微博分享的接口实例 */
    protected static IWeiboShareAPI mWeiboShareAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = this.getResources();
        // 创建微博 SDK 接口实例
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, AppConstants.WEIBO_APP_KEY);

        // 注册到新浪微博
        mWeiboShareAPI.registerApp();
    }
    protected void initTitleBar() {
        mTitle = (TextView) findViewById(R.id.tv_title);
        mBack = (ImageButton) findViewById(R.id.ib_back);
        mRight = (TextView) findViewById(R.id.tv_right);
        if (null != mBack) {
            mBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goBack();
                }
            });
        }
    }

    private void goBack() {
        finish();
    }
    protected void setTitleBarText(String title) {
        if (null != mTitle && null != title) {
            mTitle.setText(title);
        }
    }

    protected void setTitleBarText(int resId) {
        if (null != mTitle) {
            mTitle.setText(resId);
        }
    }

    protected void setTitleBarLeftVisible(boolean flag) {
        if (null != mBack) {
            mBack.setVisibility(flag ? View.VISIBLE : View.GONE);
        }
    }
    protected void setTitleBarRightText(String text, View.OnClickListener listener) {
        if (null != mRight) {
            mRight.setVisibility(View.VISIBLE);
            mRight.setText(text);
            if (null != listener) {
                mRight.setOnClickListener(listener);
            }
        }
    }

    protected void setTitleBarRightText(String text){
        setTitleBarRightText(text,null);
    }

    protected void setTitleBarRightText(int resId){
        setTitleBarRightText(resId,null);
    }

    protected void setTitleBarRightText(int resId, View.OnClickListener listener) {
        if (null != mRight) {
            mRight.setVisibility(View.VISIBLE);
            mRight.setText(resId);
            if (null != listener) {
                mRight.setOnClickListener(listener);
            }
        }
    }
    protected void setTitleBarLeftClick(View.OnClickListener listener) {
        if (null != mBack) {
            mBack.setOnClickListener(listener);
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void showNetWorkError(ErrorObj obj) {
        Toast.makeText(this, obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogicFailed(ErrorObj obj) {
        Toast.makeText(this, obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogicFailed() {
        Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show();
    }
}
