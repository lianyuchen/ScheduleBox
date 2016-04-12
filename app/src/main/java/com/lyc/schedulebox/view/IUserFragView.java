package com.lyc.schedulebox.view;

import android.content.Context;

/**
 * Created by lianyuchen on 16/4/12.
 */
public interface IUserFragView extends IBaseView{


    void jump2Login();

    Context getActivityContext();

    String getUserUUID();

    String getPhotoUri();

    void showUploadPhotoSuccess();
}
