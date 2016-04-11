package com.lyc.schedulebox.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.presenter.IUserLoginPresenter;
import com.lyc.schedulebox.presenter.impl.UserPresenterImpl;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.view.ILoginView;
import com.tencent.stat.StatService;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    private Date backTime;
    private IUserLoginPresenter mUserLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        init();
    }

    private void init() {
        mUserLoginPresenter = new UserPresenterImpl(this);
        if (null != SharedPreferenceUtils.getSharedPreferences(this, "login_info")) {
            SharedPreferenceUtils.getEditor(this, "login_info").clear().commit();
        }
    }

    private void initView() {
        initTitleBar();
        setTitleBarLeftVisible(false);
        setTitleBarText("登陆");
    }

    @OnClick(R.id.btn_login)
    public void login() {
        mUserLoginPresenter.doLogin();
    }


    @Override
    public String getUserName() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getUserPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void startOrtherActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    @OnClick(R.id.tv_register)
    public void jump2Register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    //	再按一次退出
    @Override
    public void onBackPressed() {

        Date currentTime = new Date();
        if(backTime == null || currentTime.getTime() - backTime.getTime() > 3000){
            backTime = currentTime;
            Toast.makeText(getApplicationContext(), "再次按下返回键退出", Toast.LENGTH_SHORT).show();
            return;
        }
        super.onBackPressed();
    }
}
