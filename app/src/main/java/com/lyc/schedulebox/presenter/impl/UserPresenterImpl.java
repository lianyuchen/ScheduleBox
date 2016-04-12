package com.lyc.schedulebox.presenter.impl;

import android.content.Context;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.IUploadPhotoInteractor;
import com.lyc.schedulebox.logic.IUserLoginInteractor;
import com.lyc.schedulebox.logic.IUserLogoutInteractor;
import com.lyc.schedulebox.logic.IUserRegisterInteractor;
import com.lyc.schedulebox.logic.impl.UserInteractorImpl;
import com.lyc.schedulebox.logic.listener.ClearUserInfoListener;
import com.lyc.schedulebox.logic.listener.GetUserInfoListener;
import com.lyc.schedulebox.logic.listener.RegisterUserListener;
import com.lyc.schedulebox.logic.listener.UploadPhotoListener;
import com.lyc.schedulebox.logic.model.UserInfoModel;
import com.lyc.schedulebox.presenter.IUploadPhotoPresenter;
import com.lyc.schedulebox.presenter.IUserLoginPresenter;
import com.lyc.schedulebox.presenter.IUserLogoutPresenter;
import com.lyc.schedulebox.presenter.IUserRegisterPresenter;
import com.lyc.schedulebox.view.ILoginView;
import com.lyc.schedulebox.view.IRegisterView;
import com.lyc.schedulebox.view.IUserFragView;


/**
 * Created by lianyuchen on 16/3/25.
 */
public class UserPresenterImpl implements IUploadPhotoPresenter, IUserRegisterPresenter, RegisterUserListener, IUserLoginPresenter, GetUserInfoListener, IUserLogoutPresenter, ClearUserInfoListener, UploadPhotoListener {
    private ILoginView mLoginView;
    private IRegisterView mRegisterView;
    private IUserFragView mUserFragView;

    private IUserLoginInteractor mUserLoginInteractor;
    private IUserLogoutInteractor mUserLogoutInteractor;
    private IUserRegisterInteractor mUserRegisterInteractor;
    private IUploadPhotoInteractor mUploadPhotoInteractor;

    public UserPresenterImpl(ILoginView mLoginView) {
        this.mLoginView = mLoginView;
    }

    public UserPresenterImpl(IRegisterView mRegisterView) {
        this.mRegisterView = mRegisterView;
    }

    public UserPresenterImpl(IUserFragView mUserFragView) {
        this.mUserFragView = mUserFragView;
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
        mUserLogoutInteractor = new UserInteractorImpl(mUserFragView.getActivityContext());
        mUserLogoutInteractor.clearUserInfo(this);
    }

    @Override
    public void clearUserInfoSuccess() {
        mUserFragView.jump2Login();
    }

    @Override
    public void clearUserInfoFailed() {
        mUserFragView.showLogicFailed();
    }

    @Override
    public void doUserRegister() {
        String username = mRegisterView.getUserName();
        String password = mRegisterView.getUserPwd();
        mUserRegisterInteractor = new UserInteractorImpl();
        mUserRegisterInteractor.doUserRegister(username, password, this);
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

    @Override
    public void uploadUserPhoto() {
        mUploadPhotoInteractor = new UserInteractorImpl();
        mUploadPhotoInteractor.uploadUserPhoto(mUserFragView.getUserUUID(), mUserFragView.getPhotoUri(), this);

    }

    @Override
    public void uploadPhotoSuccess() {
        mUserFragView.showUploadPhotoSuccess();
    }

    @Override
    public void uploadPhotoFailed(ErrorObj obj) {
        mUserFragView.showLogicFailed(obj);
    }

    @Override
    public void uploadPhotoError(ErrorObj obj) {
        mUserFragView.showNetWorkError(obj);
    }
}
