package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.GetMindListListener;
import com.lyc.schedulebox.logic.listener.UploadMindListener;

/**
 * Created by lianyuchen on 16/4/6.
 */
public interface IMindInteractor {

    void getMindList(String userId, GetMindListListener listener);

    void uploadMind(String userId, String mindContent, UploadMindListener listener);
}
