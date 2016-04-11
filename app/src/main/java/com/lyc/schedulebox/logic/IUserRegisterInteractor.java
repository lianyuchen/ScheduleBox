package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.RegisterUserListener;

/**
 * Created by lianyuchen on 16/4/11.
 */
public interface IUserRegisterInteractor {

    void doUserRegister(String username, String password, RegisterUserListener listener);
}
