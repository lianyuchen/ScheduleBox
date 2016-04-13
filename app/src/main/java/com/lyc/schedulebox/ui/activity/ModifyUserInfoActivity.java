package com.lyc.schedulebox.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lyc.schedulebox.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyUserInfoActivity extends BaseActivity {

    @Bind(R.id.et_user_phone)
    EditText etUserPhone;
    @Bind(R.id.et_user_mail)
    EditText etUserMail;
    @Bind(R.id.btn_commit)
    Button btnCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitleBar();
        setTitleBarText("修改资料");
    }

    @OnClick(R.id.btn_commit)
    public void onClick() {
    }
}
