package com.lyc.schedulebox.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lyc.schedulebox.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        init();
    }

    private void initView() {
        initTitleBar();
        setTitleBarText("用户注册");
    }

    private void init() {
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
    }
}
