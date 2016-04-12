package com.cxyw.suyun.common.net.impl;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IResponseCallBack;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.cxyw.suyun.common.net.model.HttpMethod;
import com.cxyw.suyun.common.utils.CommonApplication;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    public void sendMultipartRequest (String url, String filePathName, File file, final RequestParams params, final IResponseCallBack callBack) {
        MultipartRequest multipartRequest = new MultipartRequest(url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(new ErrorObj(-1, error.getMessage()));
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        },filePathName, file, params.getRquestParams());
        //设置tag
        multipartRequest.setTag(TextUtils.isEmpty(params.getRequestTag()) ? tag : params.getRequestTag());
        //加入队列
        addToRequestQueue(multipartRequest);

    }

    public void sendMultipartRequest (String url, String filePathName, List<File> files, final RequestParams params, final IResponseCallBack callBack) {
        MultipartRequest multipartRequest = new MultipartRequest(url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(new ErrorObj(-1, error.getMessage()));
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        },filePathName, files, params.getRquestParams());
        //设置tag
        multipartRequest.setTag(TextUtils.isEmpty(params.getRequestTag()) ? tag : params.getRequestTag());
        //加入队列
        addToRequestQueue(multipartRequest);

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
    class MultipartRequest extends Request<String> {

        private MultipartEntity entity = new MultipartEntity();

        private final Response.Listener<String> mListener;

        private List<File> mFileParts;
        private String mFilePartName;
        private Map<String, String> mParams;
        /**
         * 单个文件
         * @param url
         * @param errorListener
         * @param listener
         * @param filePartName
         * @param file
         * @param params
         */
        public MultipartRequest(String url, Response.ErrorListener errorListener,
                                Response.Listener<String> listener, String filePartName, File file,
                                Map<String, String> params) {
            super(Method.POST, url, errorListener);

            mFileParts = new ArrayList<File>();
            if (file != null) {
                mFileParts.add(file);
            }
            mFilePartName = filePartName;
            mListener = listener;
            mParams = params;
            buildMultipartEntity();
        }
        /**
         * 多个文件，对应一个key
         * @param url
         * @param errorListener
         * @param listener
         * @param filePartName
         * @param files
         * @param params
         */
        public MultipartRequest(String url, Response.ErrorListener errorListener,
                                Response.Listener<String> listener, String filePartName,
                                List<File> files, Map<String, String> params) {
            super(Method.POST, url, errorListener);
            mFilePartName = filePartName;
            mListener = listener;
            mFileParts = files;
            mParams = params;
            buildMultipartEntity();
        }

        private void buildMultipartEntity() {
            if (mFileParts != null && mFileParts.size() > 0) {
                for (File file : mFileParts) {
                    entity.addPart(mFilePartName, new FileBody(file));
                }
                long l = entity.getContentLength();
//                CLog.log(mFileParts.size()+"个，长度："+l);
            }

            try {
                if (mParams != null && mParams.size() > 0) {
                    for (Map.Entry<String, String> entry : mParams.entrySet()) {
                        entity.addPart(
                                entry.getKey(),
                                new StringBody(entry.getValue(), Charset
                                        .forName("UTF-8")));
                    }
                }
            } catch (UnsupportedEncodingException e) {
                VolleyLog.e("UnsupportedEncodingException");
            }
        }

        @Override
        public String getBodyContentType() {
            return entity.getContentType().getValue();
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                entity.writeTo(bos);
            } catch (IOException e) {
                VolleyLog.e("IOException writing to ByteArrayOutputStream");
            }
            return bos.toByteArray();
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
//            CLog.log("parseNetworkResponse");
            if (VolleyLog.DEBUG) {
                if (response.headers != null) {
                    for (Map.Entry<String, String> entry : response.headers
                            .entrySet()) {
                        VolleyLog.d(entry.getKey() + "=" + entry.getValue());
                    }
                }
            }

            String parsed;
            try {
                parsed = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed,
                    HttpHeaderParser.parseCacheHeaders(response));
        }


        /*
         * (non-Javadoc)
         *
         * @see com.android.volley.Request#getHeaders()
         */
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            VolleyLog.d("getHeaders");
            Map<String, String> headers = super.getHeaders();

            if (headers == null || headers.equals(Collections.emptyMap())) {
                headers = new HashMap<String, String>();
            }


            return headers;
        }

        @Override
        protected void deliverResponse(String response) {
            mListener.onResponse(response);
        }
    }


}
