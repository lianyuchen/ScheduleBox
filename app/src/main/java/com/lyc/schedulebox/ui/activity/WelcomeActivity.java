package com.lyc.schedulebox.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.common.AppConstants;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

public class WelcomeActivity extends BaseActivity {

    private Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatConfig.setDebugEnable(true);
        try {
            StatService.startStatService(this, AppConstants.QQ_APP_ID, com.tencent.stat.common.StatConstants.VERSION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StatService.trackCustomEvent(this, "onCreate", "");
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != SharedPreferenceUtils.getSharedPreferences(WelcomeActivity.this,"login_info")) {
                    if (SharedPreferenceUtils.getValue(WelcomeActivity.this,"login_info","isLogin",false)) {
                        intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        WelcomeActivity.this.finish();
                    } else {
                        intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        WelcomeActivity.this.finish();
                    }

                }else {
                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                }
            }
        }, 2000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }
}
