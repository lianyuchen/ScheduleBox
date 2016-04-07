package com.lyc.schedulebox.logic.listener;

import com.cxyw.suyun.common.net.model.ErrorObj;

/**
 * Created by lianyuchen on 16/4/7.
 */
public interface UploadMindListener {

    void uploadMindSuccess();

    void uploadMindFailed(ErrorObj obj);

    void uploadMindError(ErrorObj obj);
}
