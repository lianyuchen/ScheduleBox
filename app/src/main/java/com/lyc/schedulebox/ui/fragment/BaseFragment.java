package com.lyc.schedulebox.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.view.IBaseView;

/**
 * Created by lianyuchen on 15/7/23.
 */
public class BaseFragment extends Fragment implements IBaseView{

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
}
