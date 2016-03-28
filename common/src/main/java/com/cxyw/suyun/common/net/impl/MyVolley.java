package com.cxyw.suyun.common.net.impl;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IResponseCallBack;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.cxyw.suyun.common.net.model.HttpMethod;
import com.cxyw.suyun.common.utils.CommonApplication;

import java.util.Map;

/**
 * Created by limw on 16/3/1.
 */
public class MyVolley {
    private static MyVolley mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private final String tag = "volley";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private MyVolley() {
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized MyVolley getInstance() {
        if (mInstance == null) {
            mInstance = new MyVolley();
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(CommonApplication.getContext());
        }
        return mRequestQueue;
    }

    private <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * 根据设置的tag移除网络请求
     *
     * @param tag 网络请求的tag
     */
    public void removeRequestByTag(Object tag) {
        if (null != tag) {
            getRequestQueue().cancelAll(tag);
        }
    }


    /**
     * StringRequest
     */
    public void sendStringRequest(String url, HttpMethod method, final RequestParams params, final IResponseCallBack callBack, Map<String, Object> otherParams) {
        /**
         * 解析请求方式,get,post
         */
        int requestMethod;
        if (method == HttpMethod.GET) {
            requestMethod = Request.Method.GET;
            StringBuilder queryParams = new StringBuilder();
            if (url.contains("?")) {
                queryParams.append(url).append(params.getUrlParams());
            } else {
                queryParams.append(url).append("?").append(params.getUrlParams());
            }
            url = queryParams.toString();
        } else if (method == HttpMethod.POST) {
            requestMethod = Request.Method.POST;
        } else {
            return;
        }

        MyStringRequest stringRequest = new MyStringRequest(requestMethod, url,
                params.getRquestHeader(), params.getRquestParams(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onError(new ErrorObj(-1, error.getMessage()));
                    }

                });

        //设置tag
        stringRequest.setTag(TextUtils.isEmpty(params.getRequestTag()) ? tag : params.getRequestTag());
        //加入队列
        addToRequestQueue(stringRequest);
    }

    class MyStringRequest extends StringRequest {

        private Map<String, String> headers;
        private Map<String, String> params;

        public MyStringRequest(int method, String url, Map<String, String> headers, Map<String, String> params,
                               Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
            this.headers = headers;
            this.params = params;

        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return headers;
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return params;
        }
    }


}
