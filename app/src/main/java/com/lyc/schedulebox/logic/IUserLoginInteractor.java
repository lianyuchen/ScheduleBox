package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.GetUserInfoListener;

/**
 * Created by lianyuchen on 16/3/25.
 */
public interface IUserLoginInteractor {

    void getUserInfo(String username, String password, GetUserInfoListener listener);
}
