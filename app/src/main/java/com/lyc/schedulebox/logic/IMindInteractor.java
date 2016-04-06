package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.GetMindListListener;

/**
 * Created by lianyuchen on 16/4/6.
 */
public interface IMindInteractor {

    void getMindList(String userId, GetMindListListener listener);
}
