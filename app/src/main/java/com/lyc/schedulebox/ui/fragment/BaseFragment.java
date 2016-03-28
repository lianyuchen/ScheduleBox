package com.lyc.schedulebox.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by lianyuchen on 15/7/23.
 */
public class BaseFragment extends Fragment {

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
