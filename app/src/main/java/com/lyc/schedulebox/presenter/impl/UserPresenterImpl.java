package com.lyc.schedulebox.presenter.impl;

import android.content.Context;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.IUserLoginInteractor;
import com.lyc.schedulebox.logic.IUserLogoutInteractor;
import com.lyc.schedulebox.logic.IUserRegisterInteractor;
import com.lyc.schedulebox.logic.impl.UserInteractorImpl;
import com.lyc.schedulebox.logic.listener.ClearUserInfoListener;
import com.lyc.schedulebox.logic.listener.GetUserInfoListener;
import com.lyc.schedulebox.logic.listener.RegisterUserListener;
import com.lyc.schedulebox.logic.model.UserInfoModel;
import com.lyc.schedulebox.presenter.IUserLoginPresenter;
import com.lyc.schedulebox.presenter.IUserLogoutPresenter;
import com.lyc.schedulebox.presenter.IUserRegisterPresenter;
import com.lyc.schedulebox.view.ILoginView;
import com.lyc.schedulebox.view.ILogoutView;
import com.lyc.schedulebox.view.IRegisterView;

/**
 * Created by lianyuchen on 16/3/25.
 */
public class UserPresenterImpl implements IUserRegisterPresenter, RegisterUserListener, IUserLoginPresenter,GetUserInfoListener, IUserLogoutPresenter, ClearUserInfoListener {
    private ILoginView mLoginView;
    private ILogoutView mLogoutView;
    private IRegisterView mRegisterView;
    private IUserLoginInteractor mUserLoginInteractor;
    private IUserLogoutInteractor mUserLogoutInteractor;
    private IUserRegisterInteractor mUserRegisterInteractor;

    public UserPresenterImpl(ILoginView mLoginView) {
        this.mLoginView = mLoginView;
    }

    public UserPresenterImpl(ILogoutView mLogoutView) {
        this.mLogoutView = mLogoutView;
    }

    public UserPresenterImpl(IRegisterView mRegisterView) {
        this.mRegisterView = mRegisterView;
    }

    @Override
    public void doLogin() {
        String username = mLoginView.getUserName();
        String password = mLoginView.getUserPassword();
        mUserLoginInteractor = new UserInteractorImpl((Context) mLoginView);
        mUserLoginInteractor.getUserInfo(username, password, this);
    }

    @Override
    public void getUserInfoSuccess(UserInfoModel bean) {
        mLoginView.startOrtherActivity();
    }

    @Override
    public void getUserInfoError(ErrorObj obj) {
        mLoginView.showNetWorkError(obj);
    }

    @Override
    public void getUserInfoFailed(ErrorObj obj) {
        mLoginView.showLogicFailed(obj);
    }

    @Override
    public void doLogout() {
        mUserLogoutInteractor = new UserInteractorImpl(mLogoutView.getActivityContext());
        mUserLogoutInteractor.clearUserInfo(this);
    }

    @Override
    public void clearUserInfoSuccess() {
        mLogoutView.jump2Login();
    }

    @Override
    public void clearUserInfoFailed() {
        mLogoutView.showLogicFailed();
    }

    @Override
    public void doUserRegister() {
        String username = mRegisterView.getUserName();
        String password = mRegisterView.getUserPwd();
        mUserRegisterInteractor = new UserInteractorImpl();
        mUserRegisterInteractor.doUserRegister(username,password,this);
    }

    @Override
    public void registerSuccess() {
        mRegisterView.completeRegister();
    }

    @Override
    public void registerFailed(ErrorObj obj) {
        mRegisterView.showLogicFailed(obj);
    }

    @Override
    public void registerError(ErrorObj obj) {
        mRegisterView.showNetWorkError(obj);
    }
}
