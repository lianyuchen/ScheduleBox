package com.lyc.schedulebox.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.lyc.schedulebox.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordMindActivity extends BaseActivity {

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
                break;
            case R.id.ib_share_weixin_timeline:
                break;
            case R.id.ib_share_qq:
                break;
            case R.id.ib_share_weibo:
                break;
            case R.id.ib_share_qzone:
                break;
            case R.id.btn_pub_mind:
                break;
        }
    }
}
