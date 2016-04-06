package com.lyc.schedulebox.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.utils.logutils.LogUtils;
import com.lyc.schedulebox.view.IBaseView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lianyuchen on 15/7/23.
 */
public class BaseFragment extends Fragment implements IBaseView{

    protected int userId = -1;
    protected String last7Day, behind7Day;
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showNetWorkError(ErrorObj obj) {
        Toast.makeText(getActivity(), obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogicFailed(ErrorObj obj) {
        Toast.makeText(getActivity(), obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogicFailed() {
        Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
    }

    protected void getUserInfo() {
        userId = SharedPreferenceUtils.getValue(getActivity(), "login_info", "userId", -1);
        Calendar start = Calendar.getInstance();
        start.add(Calendar.DATE, -7);
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DATE, +7);
        last7Day = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(start.getTime());
        behind7Day = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(end.getTime());
        LogUtils.i(last7Day + "--" + behind7Day);
    }
}
