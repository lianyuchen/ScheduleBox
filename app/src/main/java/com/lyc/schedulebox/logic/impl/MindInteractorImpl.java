package com.lyc.schedulebox.logic.impl;

import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IRequestCallBack;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.common.AppConstants;
import com.lyc.schedulebox.logic.IMindInteractor;
import com.lyc.schedulebox.logic.listener.GetMindListListener;
import com.lyc.schedulebox.logic.listener.UploadMindListener;
import com.lyc.schedulebox.logic.model.BaseModel;
import com.lyc.schedulebox.logic.model.MindListModel;
import com.lyc.schedulebox.utils.NetworkHelper;

/**
 * Created by lianyuchen on 16/4/6.
 */
public class MindInteractorImpl implements IMindInteractor {

    @Override
    public void getMindList(String userId, final GetMindListListener listener) {
        RequestParams params = new RequestParams();
        params.setRequestString("userId", userId);
        NetworkHelper.getInstance().post(AppConstants.URL_GET_MIND_LIST, params, new IRequestCallBack<MindListModel>() {
            @Override
            public void onSuccess(MindListModel result) {
                listener.getMindListSuccess(result.getObj().getList());
            }

            @Override
            public void onError(ErrorObj obj) {
                listener.getMindListFailed(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                listener.getError(obj);
            }
        }, MindListModel.class);
    }

    @Override
    public void uploadMind(String userId, String mindContent, final UploadMindListener listener) {
        RequestParams params = new RequestParams();
        params.setRequestString("userId", userId);
        params.setRequestString("mindContent", mindContent);
        NetworkHelper.getInstance().post(AppConstants.URL_PUB_MIND, params, new IRequestCallBack<BaseModel>() {
            @Override
            public void onSuccess(BaseModel result) {
                listener.uploadMindSuccess();
            }

            @Override
            public void onError(ErrorObj obj) {
                listener.uploadMindError(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                listener.uploadMindFailed(obj);
            }
        }, BaseModel.class);
    }
}
