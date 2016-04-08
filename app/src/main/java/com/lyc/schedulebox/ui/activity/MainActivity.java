package com.lyc.schedulebox.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.adapter.CustomFragmentPagerAdapter;
import com.tencent.stat.StatService;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.ib_back)
    ImageButton mImageButton;
    private TextView mTitle;
    private FrameLayout mFrameLayout;
    private RadioGroup mRadioGroup;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private int currentRadioButtonId;
    private Date backTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();

    }
    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }


    private void initData() {
        mFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
        mRadioGroup.setOnCheckedChangeListener(this);
        if (null != getIntent()) {
            currentRadioButtonId = getIntent().getIntExtra("radioButtonId",R.id.rb_menu_course);
        } else {
            currentRadioButtonId = R.id.rb_menu_course;
        }
    }

    private void initView() {

        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("日程盒子");
        mImageButton.setVisibility(View.GONE);
        mFrameLayout = (FrameLayout) findViewById(R.id.fl_content);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_menu);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //当回到程序界面时，显示之前保存的页面
        mRadioGroup.check(currentRadioButtonId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当程序处于pause时记录下当前选中了哪个页面
        currentRadioButtonId = mRadioGroup.getCheckedRadioButtonId();
    }

    /**
     * RadioButton的选中切换监听事件
     *
     * @param radioGroup
     * @param checkId    选中的button的id
     */

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        int fragmentIndex = 0;
        switch (checkId) {
            case R.id.rb_menu_school:
                fragmentIndex = 0;
                break;
            case R.id.rb_menu_course:
                fragmentIndex = 1;
                break;
            case R.id.rb_menu_user:
                fragmentIndex = 2;
                break;

        }
        Object object = mFragmentPagerAdapter.instantiateItem(mFrameLayout, fragmentIndex);
        mFragmentPagerAdapter.setPrimaryItem(mFrameLayout, 0, object);
        mFragmentPagerAdapter.finishUpdate(mFrameLayout);


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
