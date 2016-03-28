package com.lyc.schedulebox.logic.impl;

import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IRequestCallBack;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.bean.UserBean;
import com.lyc.schedulebox.common.AppConstants;
import com.lyc.schedulebox.logic.IUserLoginInteractor;
import com.lyc.schedulebox.logic.listener.GetUserInfoListener;
import com.lyc.schedulebox.logic.model.UserInfoModel;
import com.lyc.schedulebox.utils.NetworkHelper;
import com.lyc.schedulebox.utils.logutils.LogUtils;

/**
 * Created by lianyuchen on 16/3/25.
 */
public class UserLoginInteractorImpl implements IUserLoginInteractor {
    @Override
    public void getUserInfo(String username, String password, final GetUserInfoListener listener) {
        RequestParams params = new RequestParams();
        params.setRequestString("userName",username);
        params.setRequestString("userPwd",password);
        NetworkHelper.getInstance().post(AppConstants.URL_GET_USER_INFO, params, new IRequestCallBack<UserInfoModel>() {
            @Override
            public void onSuccess(UserInfoModel result) {
                LogUtils.i("UserLoginInteractorImpl",result);
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
}
