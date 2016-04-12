package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.UploadPhotoListener;

/**
 * Created by lianyuchen on 16/4/12.
 */
public interface IUploadPhotoInteractor {

    void uploadUserPhoto(String userUUID, String uri, UploadPhotoListener listener);
}
