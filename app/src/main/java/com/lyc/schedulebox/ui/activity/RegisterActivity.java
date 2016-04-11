package com.lyc.schedulebox.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.presenter.IUserRegisterPresenter;
import com.lyc.schedulebox.presenter.impl.UserPresenterImpl;
import com.lyc.schedulebox.view.IRegisterView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterView{

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_register)
    Button btnRegister;
    private IUserRegisterPresenter mUserRegisterPresenter;
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
        mUserRegisterPresenter = new UserPresenterImpl(this);
        mUserRegisterPresenter.doUserRegister();
    }

    @Override
    public String getUserName() {
        if (!TextUtils.isEmpty(etUsername.getText().toString().trim())){
            return etUsername.getText().toString().trim();
        }
        return null;
    }

    @Override
    public String getUserPwd() {
        if (!TextUtils.isEmpty(etUsername.getText().toString().trim())){
            return etUsername.getText().toString().trim();
        }
        return null;
    }

    @Override
    public void completeRegister() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
