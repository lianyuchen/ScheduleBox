package com.cxyw.suyun.common.net;

import com.cxyw.suyun.common.net.callBack.IResponseCallBack;
import com.cxyw.suyun.common.net.impl.VollyNetworkRequest;
import com.cxyw.suyun.common.net.model.HttpMethod;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by liub on 16/2/24.
 */
public class NetworkProxy implements INetworkRequest {
    private static NetworkProxy instance = null;

    private INetworkRequest netRequest = null;

    public NetworkProxy(INetworkRequest netRequest) {
        this.netRequest = netRequest;
    }

    public NetworkProxy() {
        netRequest = new VollyNetworkRequest();
    }

    public static NetworkProxy getInstance() {
        if (null == instance) {
            synchronized (NetworkProxy.class) {
                if (null == instance) {
                    instance = new NetworkProxy();
                }
            }
        }
        return instance;
    }

    public void setNetworkRequest(INetworkRequest netRequest) {
        this.netRequest = netRequest;
    }

    @Override
    public void requestData(String url, HttpMethod method, RequestParams params, IResponseCallBack callBack, Map<String, Object> otherParams) {
        netRequest.requestData(url, method, params, callBack, otherParams);
    }

    @Override
    public void requestData(String url, String filePathName, File file, RequestParams params, IResponseCallBack callBack) {
        netRequest.requestData(url,filePathName,file,params,callBack);
    }

    @Override
    public void requestData(String url, String filePathName, List<File> files, RequestParams params, IResponseCallBack callBack) {
        netRequest.requestData(url,filePathName,files,params,callBack);
    }

    @Override
    public void removeRequest(String tag) {
        netRequest.removeRequest(tag);
    }
}
