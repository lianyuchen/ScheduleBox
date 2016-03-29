package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.ClearUserInfoListener;

/**
 * Created by lianyuchen on 16/3/29.
 */
public interface IUserLogoutInteractor {
    void clearUserInfo(ClearUserInfoListener listener);
}
