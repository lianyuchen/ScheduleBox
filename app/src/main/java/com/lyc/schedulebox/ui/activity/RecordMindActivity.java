package com.lyc.schedulebox.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lyc.schedulebox.MyApplication;
import com.lyc.schedulebox.R;
import com.lyc.schedulebox.presenter.IMindPresenter;
import com.lyc.schedulebox.presenter.ISharePresenter;
import com.lyc.schedulebox.presenter.impl.MindPresenterImpl;
import com.lyc.schedulebox.presenter.impl.SharePresenterImpl;
import com.lyc.schedulebox.ui.listener.ShareQQUiListener;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.view.IRecordMindView;
import com.lyc.schedulebox.view.IShareView;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.stat.StatService;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordMindActivity extends BaseActivity implements IRecordMindView, IShareView {

    @Bind(R.id.et_mind_content)
    EditText etMindContent;
    @Bind(R.id.ib_share_weixin)
    ImageButton ibShareWeixin;
    @Bind(R.id.ib_share_weixin_timeline)
    ImageButton ibShareWeixinTimeline;
    @Bind(R.id.ib_share_qq)
    ImageButton ibShareQq;
    @Bind(R.id.ib_share_weibo)
    ImageButton ibShareWeibo;
    @Bind(R.id.ib_share_qzone)
    ImageButton ibShareQzone;
    @Bind(R.id.btn_pub_mind)
    Button btnPubMind;

    private IMindPresenter mindPresenter;
    private ISharePresenter sharePresenter;
    private IUiListener shareQQUiListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_mind);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitleBar();
        setTitleBarText("发表心情");
        shareQQUiListener = new ShareQQUiListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    @OnClick({R.id.ib_share_weixin, R.id.ib_share_weixin_timeline, R.id.ib_share_qq, R.id.ib_share_weibo, R.id.ib_share_qzone, R.id.btn_pub_mind})
    public void onClick(View view) {
        sharePresenter = new SharePresenterImpl(this);
        switch (view.getId()) {
            case R.id.ib_share_weixin:
                sharePresenter.shareWXSession();
                break;
            case R.id.ib_share_weixin_timeline:
                sharePresenter.shareWXTimeLine();
                break;
            case R.id.ib_share_qq:
                sharePresenter.shareQQ();
                break;
            case R.id.ib_share_weibo:
                sharePresenter.shareWeibo();
                break;
            case R.id.ib_share_qzone:
                sharePresenter.shareQzone();
                break;
            case R.id.btn_pub_mind:
                mindPresenter = new MindPresenterImpl(this);
                mindPresenter.pubMind();
                break;
        }
    }

    @Override
    public void completePubMind() {
        Toast.makeText(this, "发表成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public String getMindContent() {
        return etMindContent.getText().toString().trim();
    }

    @Override
    public int getUserId() {
        return SharedPreferenceUtils.getValue(this, "login_info", "userId", -1);
    }

    @Override
    public void shareWXTimeLine() {
        WXTextObject object = new WXTextObject();
        object.text = getShareText();

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = object;
        msg.description = getShareText();

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        MyApplication.api.sendReq(req);
    }

    @Override
    public void shareWXSession() {
        WXTextObject object = new WXTextObject();
        object.text = getShareText();

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = object;
        msg.description = getShareText();

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        MyApplication.api.sendReq(req);
    }

    @Override
    public void shareQQ() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://www.qq.com/news/1.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "测试应用222222");
        MyApplication.mTencent.shareToQQ(this, params, shareQQUiListener);
    }

    @Override
    public void shareQzone() {
        final Bundle params = new Bundle();
        //分享类型
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzonePublish.PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD);
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, getShareText());
        MyApplication.mTencent.publishToQzone(this, params, shareQQUiListener);

    }

    @Override
    public void shareWeibo() {

    }

    @Override
    public String getShareText() {
        return getMindContent();
    }

    @Override
    public void showToast(Object o) {
        if (o instanceof UiError) {
            Toast.makeText(this, ((UiError) o).errorMessage, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode,resultCode,data, shareQQUiListener);
    }
}
