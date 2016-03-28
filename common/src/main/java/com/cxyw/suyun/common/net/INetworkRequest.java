package com.cxyw.suyun.common.net;


import com.cxyw.suyun.common.net.callBack.IResponseCallBack;
import com.cxyw.suyun.common.net.model.HttpMethod;

import java.util.Map;

/**
 * Created by liub on 16/2/24.
 */
//APIProvider
public interface INetworkRequest {
    /**
     * 请求数据
     *
     * @param url 请求地址
     * @param method 请求方法
     * @param params 请求参数
     * @param callBack 回调对象
     * @param otherParams
     */
    void requestData(String url, HttpMethod method, RequestParams params, IResponseCallBack callBack,Map<String,Object> otherParams);

    /**
     * 移除请求
     *
     * @param tag
     */
    void removeRequest(String tag);
}
