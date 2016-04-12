package com.lyc.schedulebox.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lyc.schedulebox.R;
import com.lyc.schedulebox.presenter.IUploadPhotoPresenter;
import com.lyc.schedulebox.presenter.IUserLogoutPresenter;
import com.lyc.schedulebox.presenter.impl.UserPresenterImpl;
import com.lyc.schedulebox.ui.activity.LoginActivity;
import com.lyc.schedulebox.utils.SharedPreferenceUtils;
import com.lyc.schedulebox.utils.UtilsImageProcess;
import com.lyc.schedulebox.utils.logutils.LogUtils;
import com.lyc.schedulebox.view.IUserFragView;
import com.tencent.stat.StatService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lianyuchen on 15/12/30.
 */
public class UserFragment extends BaseFragment implements IUserFragView {
    @Bind(R.id.btn_logout)
    Button btnLogout;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_user_gender)
    TextView tvUserGender;
    @Bind(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @Bind(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @Bind(R.id.ll_my_friend)
    LinearLayout llMyFriend;
    @Bind(R.id.ll_my_share)
    LinearLayout llMyShare;
    @Bind(R.id.ll_my_mind)
    LinearLayout llMyMind;
    @Bind(R.id.ll_analysis)
    LinearLayout llAnalysis;
    private View mViews = null;
    private IUserLogoutPresenter mUserLogoutPresenter;
    private IUploadPhotoPresenter mUploadPhotoPresenter;

    private String photoUri;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private File tempFile = new File(Environment.getExternalStorageDirectory(),
            getPhotoFileName());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mViews == null) {
            mViews = inflater.inflate(R.layout.fragment_user, null);
            ButterKnife.bind(this, mViews);
            init();
        }
        ViewGroup parent = (ViewGroup) mViews.getParent();
        if (parent != null) {
            parent.removeView(mViews);
        }
        ButterKnife.bind(this, mViews);
        return mViews;
    }

    private void init() {
        mUserLogoutPresenter = new UserPresenterImpl(this);
        if (null != SharedPreferenceUtils.getSharedPreferences(getActivity(), "login_info")) {
            if (SharedPreferenceUtils.getValue(getActivity(), "login_info", "isLogin", false)) {
                tvUsername.setText(SharedPreferenceUtils.getValue(getActivity(), "login_info", "username", ""));
                tvUserGender.setText(SharedPreferenceUtils.getValue(getActivity(), "login_info", "phone", ""));
            } else {

            }

        } else {
            LogUtils.i("no SharedPreferenceUtils instance");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_logout)
    public void logout() {

        mUserLogoutPresenter.doLogout();
    }

    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.i("UserFragment","onResume()");
        init();
        StatService.onResume(getActivity());
    }

    @Override
    public void jump2Login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public Context getActivityContext() {
        return this.getActivity();
    }

    @OnClick({R.id.iv_user_icon, R.id.ll_user_info, R.id.ll_my_friend, R.id.ll_my_share, R.id.ll_my_mind, R.id.ll_analysis})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
//                Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
//                getAlbum.setType("image/*");
//                startActivityForResult(getAlbum, PHOTO_REQUEST_GALLERY);

                Intent cameraintent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                // 指定调用相机拍照后照片的储存路径
                cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(tempFile));
                startActivityForResult(cameraintent,
                        CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.ll_user_info:
                break;
            case R.id.ll_my_friend:
                break;
            case R.id.ll_my_share:
                break;
            case R.id.ll_my_mind:
                break;
            case R.id.ll_analysis:
                break;
        }
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == getActivity().RESULT_OK) {
                    // Image captured and saved to fileUri specified in the Intent
                    /**
                     * 指定了存储位置，不会执行下面的if语句块
                     */
                    if (data != null) { //可能尚未指定intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        //返回有缩略图
                        if (data.hasExtra("data")) {
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            ivUserIcon.setImageBitmap(thumbnail);
                            tempFile.delete();
                        }
                    } else {
                        //由于指定了目标uri，存储在目标uri，intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        // 通过目标uri，找到图片
                        // 对图片的缩放处理
                        // 操作
                        if (tempFile.exists()) {
                            startPhotoZoom(Uri.fromFile(tempFile));
                        }
//                        LogUtils.i(tempFile);

                    }
                } else if (resultCode == getActivity().RESULT_CANCELED) {
                    // User cancelled the image capture
                    Toast.makeText(getActivity(), "取消拍照", Toast.LENGTH_LONG).show();
                }
                break;
            case PHOTO_REQUEST_CUT:
                if (resultCode == getActivity().RESULT_OK) {
                    if (data != null) {
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        photoUri = UtilsImageProcess.getPath(thumbnail);
                        mUploadPhotoPresenter = new UserPresenterImpl(this);
                        mUploadPhotoPresenter.uploadUserPhoto();
                        ivUserIcon.setImageBitmap(thumbnail);
                        tempFile.delete();//设置成功后清除之前的照片文件
                    }
                } else if (resultCode == getActivity().RESULT_CANCELED) {
                    // User cancelled the image capture
                    Toast.makeText(getActivity(), "取消裁剪", Toast.LENGTH_LONG).show();
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
//
//            switch (requestCode) {
//                case PHOTO_REQUEST_TAKEPHOTO:// 当选择拍照时调用
//                    startPhotoZoom(Uri.fromFile(tempFile));
//                    break;
//                case PHOTO_REQUEST_GALLERY:// 当选择从本地获取图片时
//                    // 做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
//                    if (data != null) {
//                        System.out.println("11================");
//                        startPhotoZoom(data.getData());
//                    } else {
//                        System.out.println("================");
//                    }
//                    break;
//                case PHOTO_REQUEST_CUT:// 返回的结果
//                    if (data != null)
//                        // setPicToView(data);
////                    sentPicToNext(data);
//                        break;
//            }
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }

    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        System.out.println("22================");
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    @Override
    public String getUserUUID() {
        return SharedPreferenceUtils.getValue(getActivity(),"login_info","uuid","");
    }

    @Override
    public String getPhotoUri() {
        return photoUri;
    }

    @Override
    public void showUploadPhotoSuccess() {
        Toast.makeText(getActivity(),"上传成功",Toast.LENGTH_SHORT).show();
    }
}
