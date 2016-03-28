package com.cxyw.suyun.common.net.impl;

import com.cxyw.suyun.common.net.INetworkRequest;
import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IResponseCallBack;
import com.cxyw.suyun.common.net.model.HttpMethod;

import java.util.Map;

/**
 * Created by liub on 16/2/24.
 */
public class VollyNetworkRequest implements INetworkRequest {
    @Override
    public void requestData(String url, HttpMethod method, RequestParams params, IResponseCallBack callBack, Map<String, Object> otherParams) {
        MyVolley.getInstance().sendStringRequest(url, method, params, callBack, otherParams);
    }

    @Override
    public void removeRequest(String tag) {
        MyVolley.getInstance().removeRequestByTag(tag);
    }

}
