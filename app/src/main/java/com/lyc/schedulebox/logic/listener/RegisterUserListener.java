package com.lyc.schedulebox.logic.listener;

import com.cxyw.suyun.common.net.model.ErrorObj;

/**
 * Created by lianyuchen on 16/4/11.
 */
public interface RegisterUserListener {

    void registerSuccess();

    void registerFailed(ErrorObj obj);

    void registerError(ErrorObj obj);
}
