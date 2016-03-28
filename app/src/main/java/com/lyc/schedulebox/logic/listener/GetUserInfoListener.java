package com.lyc.schedulebox.logic.listener;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.model.UserInfoModel;

/**
 * Created by lianyuchen on 16/3/25.
 */
public interface GetUserInfoListener {

    void getUserInfoSuccess(UserInfoModel bean);

    void getUserInfoError(ErrorObj obj);

    void getUserInfoFailed(ErrorObj obj);
}
