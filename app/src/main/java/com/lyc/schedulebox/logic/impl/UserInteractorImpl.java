package com.lyc.schedulebox.logic.impl;

import android.content.Context;

import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IRequestCallBack;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.common.AppConstants;
import com.lyc.schedulebox.logic.IUserLoginInteractor;
import com.lyc.schedulebox.logic.IUserLogoutInteractor;
import com.lyc.schedulebox.logic.listener.ClearUserInfoListener;
import com.lyc.schedulebox.logic.listener.GetUserInfoListener;
import com.lyc.schedulebox.logic.model.UserInfoModel;
import com.lyc.schedulebox.utils.NetworkHelper;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.utils.logutils.LogUtils;

/**
 * Created by lianyuchen on 16/3/25.
 */
public class UserInteractorImpl implements IUserLoginInteractor, IUserLogoutInteractor {
    private Context context;

    public UserInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getUserInfo(String username, String password, final GetUserInfoListener listener) {
        RequestParams params = new RequestParams();
        params.setRequestString("userName",username);
        params.setRequestString("userPwd",password);
        NetworkHelper.getInstance().post(AppConstants.URL_GET_USER_INFO, params, new IRequestCallBack<UserInfoModel>() {
            @Override
            public void onSuccess(UserInfoModel result) {
                LogUtils.i("UserInteractorImpl",result);
                SharedPreferenceUtils.putValue(context,"login_info","username",result.getObj().getUserName());
                SharedPreferenceUtils.putValue(context,"login_info","password",result.getObj().getUserPwd());
                SharedPreferenceUtils.putValue(context,"login_info","uuid",result.getObj().getUserUUID());
                SharedPreferenceUtils.putValue(context,"login_info","userId",result.getObj().getUserId());
                SharedPreferenceUtils.putValue(context,"login_info","isLogin",true);
                listener.getUserInfoSuccess(result);
            }

            @Override
            public void onError(ErrorObj obj) {
                listener.getUserInfoError(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                listener.getUserInfoFailed(obj);
            }
        }, UserInfoModel.class);
    }

    @Override
    public void clearUserInfo(ClearUserInfoListener listener) {

        if (null == SharedPreferenceUtils.getSharedPreferences(context,"login_info")) {
            listener.clearUserInfoFailed();
        }else {
            SharedPreferenceUtils.putValue(context,"login_info","isLogin",false);
            listener.clearUserInfoSuccess();
        }
    }
}
