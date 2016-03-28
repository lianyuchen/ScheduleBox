package com.cxyw.suyun.common.net.callBack;

import com.cxyw.suyun.common.net.RequestParams;

/**
 * Created by liub on 16/2/26.
 * 拦截结果集，对结果集处理
 */
public interface InterceptResultCallBack {
    /**
     * 拦截输出结果集，对code进行二次判断，比如：统一处理行驶中订单的状态
     *
     * @param code 响应状态
     * @param codeMsg 响应状态消息
     * @param result 响应结果
     *
     * @result true:继续回调； false:不回调
     */
    boolean onProcessResponse(int code,String codeMsg, String result);
}
