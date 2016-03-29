package com.lyc.schedulebox.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;

public class WelcomeActivity extends BaseActivity {

    private Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
