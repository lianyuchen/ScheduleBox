package com.lyc.schedulebox.ui.activity;

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
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.view.IRecordMindView;
import com.lyc.schedulebox.view.IShareView;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordMindActivity extends BaseActivity implements IRecordMindView, IShareView{

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
    }

    @OnClick({R.id.ib_share_weixin, R.id.ib_share_weixin_timeline, R.id.ib_share_qq, R.id.ib_share_weibo, R.id.ib_share_qzone, R.id.btn_pub_mind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_share_weixin:
                sharePresenter = new SharePresenterImpl(this);
                sharePresenter.shareWXSession();
                break;
            case R.id.ib_share_weixin_timeline:
                sharePresenter = new SharePresenterImpl(this);
                sharePresenter.shareWXTimeLine();
                break;
            case R.id.ib_share_qq:
                break;
            case R.id.ib_share_weibo:
                break;
            case R.id.ib_share_qzone:
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

    }

    @Override
    public void shareQzone() {

    }

    @Override
    public void shareWeibo() {

    }

    @Override
    public String getShareText() {
        return getMindContent();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
