package com.lyc.schedulebox.logic.listener;

import com.cxyw.suyun.common.net.model.ErrorObj;

/**
 * Created by lianyuchen on 16/3/31.
 */
public interface AddScheduleListener {

    void addScheduleSuccess();

    void addFailed(ErrorObj obj);

    void addError(ErrorObj obj);
}
