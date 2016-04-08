package com.lyc.schedulebox.view;

/**
 * Created by lianyuchen on 16/4/7.
 */
public interface IShareView extends IBaseView{

    void shareWXTimeLine();

    void shareWXSession();

    void shareQQ();

    void shareQzone();

    void shareWeibo();

    String getShareText();

    void showToast(Object o);
}
