package com.cxyw.suyun.common.net.callBack;


import com.cxyw.suyun.common.net.model.ErrorObj;

/**
 * Created by liub on 16/2/24.
 * <p/>
 * 输出结果处理类
 */
public interface IRequestCallBack<T> {
    void onSuccess(T result);

    void onError(ErrorObj obj);

    void onFailed(ErrorObj obj);
}
