package com.lyc.schedulebox.utils;

import android.os.Build;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import com.cxyw.suyun.common.net.NetworkProxy;
import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IRequestCallBack;
import com.cxyw.suyun.common.net.callBack.IResponseCallBack;
import com.cxyw.suyun.common.net.callBack.InterceptResultCallBack;
import com.cxyw.suyun.common.net.model.DataSources;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.cxyw.suyun.common.net.model.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by liub on 16/2/24.
 */
public class NetworkHelper {
    private static NetworkHelper instance = null;

    String manufacturer = "";//手机生产厂商
    String mobile_version = "";
    String mobile_modle = "";//手机型号
    private String responseErrorCode = "";//错误编码
    private String responseErrorMsg = "";//错误消息
    //private SharedPreferenceUtil sharedPreferenceUtil = null;

    public NetworkHelper() {
        try {
            mobile_version = android.os.Build.VERSION.RELEASE;
            mobile_modle = android.os.Build.MODEL;
            manufacturer = Build.MANUFACTURER;
            //sharedPreferenceUtil = SharedPreferenceUtil.getInstance(MyApplication.getScheduleBoxApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NetworkHelper getInstance() {
        if (null == instance) {
            synchronized (NetworkHelper.class) {
                if (null == instance) {
                    instance = new NetworkHelper();
                }
            }
        }
        //NetworkProxy.getInstance().setNetworkRequest(new VollyNetworkRequest());
        return instance;
    }

    public <T> T post(String url, RequestParams params, final IRequestCallBack<T> callBack) {
        return post(url, params, callBack, null, null);
    }

    /**
     * post请求数据
     *
     * @param url      请求URL
     * @param params   请求参数
     * @param callBack 请求回调
     * @param model    解析模型，支持Model对象和String类
     * @param <T>
     * @return
     */
    public <T> T post(String url, RequestParams params, final IRequestCallBack<T> callBack, final Class<T> model) {
        return post(url, params, callBack, model, null);
    }

    public <T> T post(String url, RequestParams params, final IRequestCallBack<T> callBack, InterceptResultCallBack interceptCallBack) {
        return post(url, params, callBack, null, interceptCallBack);
    }

    public <T> T post(String url, RequestParams params, final IRequestCallBack<T> callBack, final Class<T> model, InterceptResultCallBack interceptCallBack) {
        return request(url, HttpMethod.POST, params, callBack, model, interceptCallBack);
    }

    public <T> T get(String url, RequestParams params, final IRequestCallBack<T> callBack) {
        return get(url, params, callBack, null, null);
    }

    /**
     * post请求数据
     *
     * @param url      请求URL
     * @param params   请求参数
     * @param callBack 请求回调
     * @param model    解析模型
     * @param <T>
     * @return
     */
    public <T> T get(String url, RequestParams params, final IRequestCallBack<T> callBack, final Class<T> model) {
        return get(url, params, callBack, model, null);
    }

    public <T> T get(String url, RequestParams params, final IRequestCallBack<T> callBack, InterceptResultCallBack interceptCallBack) {
        return get(url, params, callBack, null, interceptCallBack);
    }

    public <T> T get(String url, RequestParams params, final IRequestCallBack<T> callBack, final Class<T> model, InterceptResultCallBack interceptCallBack) {
        return request(url, HttpMethod.GET, params, callBack, model, interceptCallBack);
    }


    /**
     * 数据请求
     *
     * @param url               网络URL
     * @param method            请求方法
     * @param params            请求参数
     * @param callBack          回调方法
     * @param interceptCallBack 拦截回调方法
     */
    public <T> T request(String url, HttpMethod method, RequestParams params, final IRequestCallBack<T> callBack, final Class<T> model, final InterceptResultCallBack interceptCallBack) {
        final RequestParams requestParams = setCommonParams(params);;
        NetworkProxy.getInstance().requestData(url, method, requestParams, new IResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject resultObj = new JSONObject(result);
                    int code = JSONUtils.getInt(resultObj, "code", -1);
                    String codeMsg = JSONUtils.getString(resultObj, "codeMsg", "");
                    switch (code) {
                        //响应成功状态为：200
                        case 200:
                            T parseResult = parseBean(result, model);
                            boolean isCallBack = true;
                            if (interceptCallBack != null) {
                                isCallBack = interceptCallBack.onProcessResponse(code, codeMsg, result);
                            }
                            if (isCallBack) {
                                callBack.onSuccess(parseResult);
                            }
                            break;
                        default:
                            callBack.onFailed(new ErrorObj(code, codeMsg));
                            break;
                    }
                } catch (JSONException e) {
                    callBack.onError(new ErrorObj(-1, e.getMessage()));
                }
            }

            @Override
            public void onError(ErrorObj obj) {
                callBack.onError(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                callBack.onFailed(obj);
            }
        }, null);
        return null;
    }
    public <T> T request(String url, String filePathName, File file, RequestParams params, final IRequestCallBack<T> callBack, final Class<T> model) {
        final RequestParams requestParams = setCommonParams(params);;
        NetworkProxy.getInstance().requestData(url, filePathName, file, requestParams, new IResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject resultObj = new JSONObject(result);
                    int code = JSONUtils.getInt(resultObj, "code", -1);
                    String codeMsg = JSONUtils.getString(resultObj, "codeMsg", "");
                    switch (code) {
                        //响应成功状态为：200
                        case 200:
                            T parseResult = parseBean(result, model);
                            boolean isCallBack = true;

                            if (isCallBack) {
                                callBack.onSuccess(parseResult);
                            }
                            break;
                        default:
                            callBack.onFailed(new ErrorObj(code, codeMsg));
                            break;
                    }
                } catch (JSONException e) {
                    callBack.onError(new ErrorObj(-1, e.getMessage()));
                }
            }

            @Override
            public void onError(ErrorObj obj) {
                callBack.onError(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                callBack.onFailed(obj);
            }
        });
        return null;
    }

    public <T> T request(String url, String filePathName, List<File> files, RequestParams params, final IRequestCallBack<T> callBack, final Class<T> model) {
        final RequestParams requestParams = setCommonParams(params);
        NetworkProxy.getInstance().requestData(url, filePathName, files, requestParams, new IResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject resultObj = new JSONObject(result);
                    int code = JSONUtils.getInt(resultObj, "code", -1);
                    String codeMsg = JSONUtils.getString(resultObj, "codeMsg", "");
                    switch (code) {
                        //响应成功状态为：200
                        case 200:
                            T parseResult = parseBean(result, model);
                            boolean isCallBack = true;

                            if (isCallBack) {
                                callBack.onSuccess(parseResult);
                            }
                            break;
                        default:
                            callBack.onFailed(new ErrorObj(code, codeMsg));
                            break;
                    }
                } catch (JSONException e) {
                    callBack.onError(new ErrorObj(-1, e.getMessage()));
                }
            }

            @Override
            public void onError(ErrorObj obj) {
                callBack.onError(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                callBack.onFailed(obj);
            }
        });
        return null;
    }

    /**
     * 停止网络请求
     *
     * @param tag 标记
     */
    public void stopRequest(String tag) {
        stopRequest(tag, DataSources.NETWORK);
    }

    /**
     * 停止网络请求
     *
     * @param tag 标记
     * @param ds  数据来源
     */
    public void stopRequest(String tag, DataSources ds) {
        NetworkProxy.getInstance().removeRequest(tag);
    }


    /**
     * 设置公共参数（包括header和params）
     * @param params
     * @return
     */
    private RequestParams setCommonParams(RequestParams params) {
        if(null == params){
            params = new RequestParams();
        }
        //params.setHeader("version", AppConstants.appVersion);//app版本号
        //params.setHeader("imei", AppConstants.APP_IMEI);//手机硬件设备号
        params.setHeader("mobile-version", mobile_version);
        params.setHeader("mobile-board", mobile_modle);
        params.setHeader("manufacturer", manufacturer);
        //params.setHeader("i", AppConstants.MD5Version);
        //params.setHeader("c", Md5Util.encryptURL(params.getRquestParams(), AppConstants.MD5KEY));
        //params.setHeader(AppConstants.TOKEN_NUMBER, AppConstants.TOKEN_REQUEST_NUMBER);//请求Number
        params.setRequestString("r", Math.random() + "");
        //所有请求增加UserID
        //params.setRequestString("uid", UserUtils.getUserId());
//        if (islogin()) {
//            // token开关为true时，上传token，否则不传
//            if (AppConfig.IS_TOKEN_SWITCH) {
//                params.setHeader(AppConstants.TOKEN_KEY, sharedPreferenceUtil.getTokenByUserId(UserUtils.getUserId()));//请求增加Token
//            }
//
//        }
        return params;
    }

    /**
     * 解析Bean
     *
     * @param resultStr
     * @param clazz
     * @return
     */
    private <T> T parseBean(String resultStr, Class<T> clazz) {
        T resultObj = null;
        try {
            if(null == clazz){
                return null;
            }
            if (isStringClass((Type) clazz)) {
                return (T) resultStr;
            } else {
                resultObj = JSON.parseObject(resultStr, clazz);
            }
        } catch (Exception e) {
            return resultObj;
        }
        return resultObj;
    }

    private boolean isStringClass(Type type) {
        Type _type = TypeUtils.unwrap(type);
        if (_type == String.class) {
            return true;
        }else{
            return false;
        }
    }

}
