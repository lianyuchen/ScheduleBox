package com.lyc.schedulebox.presenter.impl;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.bean.UserBean;
import com.lyc.schedulebox.logic.IUserLoginInteractor;
import com.lyc.schedulebox.logic.impl.UserLoginInteractorImpl;
import com.lyc.schedulebox.logic.listener.GetUserInfoListener;
import com.lyc.schedulebox.logic.model.UserInfoModel;
import com.lyc.schedulebox.presenter.IUserLoginPresenter;
import com.lyc.schedulebox.view.ILoginView;

/**
 * Created by lianyuchen on 16/3/25.
 */
public class UserLoginPresenterImpl implements IUserLoginPresenter,GetUserInfoListener {
    private ILoginView mLoginView;
    private IUserLoginInteractor mUserLoginInteractor;

    public UserLoginPresenterImpl(ILoginView mLoginView) {
        this.mLoginView = mLoginView;
        mUserLoginInteractor = new UserLoginInteractorImpl();
    }

    @Override
    public void doLogin() {
        String username = mLoginView.getUserName();
        String password = mLoginView.getUserPassword();
        mUserLoginInteractor.getUserInfo(username, password, this);
    }

    @Override
    public void getUserInfoSuccess(UserInfoModel bean) {
        mLoginView.saveUserInfo(bean);
        mLoginView.startOrtherActivity();
    }

    @Override
    public void getUserInfoError(ErrorObj obj) {
        mLoginView.showNetError(obj);
    }

    @Override
    public void getUserInfoFailed(ErrorObj obj) {
        mLoginView.showLoginFailedDialog();
    }
}
