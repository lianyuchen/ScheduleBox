package com.lyc.schedulebox.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.utils.logutils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lianyuchen on 15/12/30.
 */
public class UserFragment extends BaseFragment {
    @Bind(R.id.btn_logout)
    Button btnLogout;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_user_gender)
    TextView tvUserGender;
    private View mViews = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mViews == null) {
            mViews = inflater.inflate(R.layout.fragment_user, null);
            ButterKnife.bind(this, mViews);
            init();
        }
        ViewGroup parent = (ViewGroup) mViews.getParent();
        if (parent != null) {
            parent.removeView(mViews);
        }
        ButterKnife.bind(this, mViews);
        return mViews;
    }

    private void init() {
        if (null != SharedPreferenceUtils.getSharedPreferences(getActivity(),"login_info")) {
            if (SharedPreferenceUtils.getValue(getActivity(),"login_info","haveUserInfo",false)) {
                tvUsername.setText(SharedPreferenceUtils.getValue(getActivity(),"login_info","username",""));
                tvUserGender.setText(SharedPreferenceUtils.getValue(getActivity(),"login_info","phone",""));
            } else {

            }

        }else {
            LogUtils.i("no SharedPreferenceUtils instance");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_logout)
    public void logout() {
//        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.i("UserFragment","onResume()");
        init();
    }
}
