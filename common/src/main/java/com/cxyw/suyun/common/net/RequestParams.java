package com.cxyw.suyun.common.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liub on 16/2/24.
 */
public class RequestParams {
    private Map<String, String> rqParams = new HashMap<String, String>();

    private Map<String,String> headerParams=new HashMap<String,String>();

    public RequestParams(){
        setOtherDeviceMsg(false);
    }
    //单设备登录标识
    private boolean showOtherDeviceMsg = false;

    //请求TAG
    private  String requestTag = "";

    public void setRequestString(String paramKey, String paramValue) {
        rqParams.put(paramKey, paramValue);
    }


    /**
     * 显示单设备登录提示
     */
    public void setOtherDeviceMsg(boolean boo) {
        this.showOtherDeviceMsg = boo;
    }

    /**
     * 根据key获取请求参数值
     *
     * @return
     */
    public boolean getOtherDeviceMsg() {
        return this.showOtherDeviceMsg;
    }

    /**
     * 获取请求标记
     *
     * @return
     */
    public String getRequestTag() {
        return this.requestTag;
    }

    /**
     * 赋值请求标记
     *
     * @param tagValue
     */
    public void setRequestTag(String tagValue) {
        this.requestTag = tagValue;
    }

    /**
     * 根据key获取请求参数值
     *
     * @param key
     * @return
     */
    public Object getParamsValueByKey(String key) {
        return rqParams.get(key);
    }

    /**
     * 获取请求Map
     *
     */
    public Map getRquestParams(){
        return this.rqParams;
    }

    /**
     * 获取请求参数URL
     *
     * @return url
     */
    public  String getUrlParams() {
        if(this.rqParams == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : rqParams.entrySet()) {
            sb.append("&");
            sb.append(entry.getKey() + "=" + entry.getValue());
        }
        String s = sb.toString();
        if (s.length()>0) {
            s = s.substring(1);
        }
        return s;
    }

    public Map<String, String> getRquestHeader(){
        return this.headerParams;
    }

    public void setHeader(String name, String value) {
        if(null==headerParams){
            headerParams=new HashMap<String,String>();
        }

        headerParams.put(name,value);
    }

    public void setContentType(String contentType) {
        this.setHeader("Content-Type", contentType);
    }

}
