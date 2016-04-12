package com.lyc.schedulebox.logic.listener;

import com.cxyw.suyun.common.net.model.ErrorObj;

/**
 * Created by lianyuchen on 16/4/12.
 */
public interface UploadPhotoListener {

    void uploadPhotoSuccess();

    void uploadPhotoFailed(ErrorObj obj);

    void uploadPhotoError(ErrorObj obj);
}
