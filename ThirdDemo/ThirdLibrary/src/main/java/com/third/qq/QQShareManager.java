package com.third.qq;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.UiThread;
import android.text.TextUtils;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.third.data.ThirdDataProvieder;
import com.third.model.ShareContent;

/**
 * Created by echo on 5/18/15.
 */
public class QQShareManager {

    public static final int QZONE = 1;
    public static final int QQ = 2;


    @IntDef(value = {QQ, QZONE})
    public @interface TentcentShareType {

    }


    private String mAppId;

    private Tencent mTencent;

    private QQShare mQQShare;

    private Context mContext;


    public QQShareManager(Activity context) {

        mAppId = ThirdDataProvieder.getQQAppId();
        mContext = context;
        if (!TextUtils.isEmpty(mAppId)) {
            mTencent = Tencent.createInstance(mAppId, context);
            mQQShare = new QQShare(context, mTencent.getQQToken());
        } else {
            throw new IllegalStateException("QQShareManager：请先初始化 appId");
        }
    }


    private void shareToQQ(Activity activity, ShareContent shareContent) {
        Bundle params = new Bundle();
        //类型
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        //标题
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareContent.getTitle());
        //内容的url
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareContent.getURL());
        //摘要
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareContent.getContent());
        //图片url
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareContent.getImageUrl());
        if (mQQShare != null) {
            mQQShare.shareToQQ(activity, params, iUiListener);
        }
    }


    private  IUiListener iUiListener;

    public void setiUiListener(IUiListener iUiListener) {
        this.iUiListener = iUiListener;
    }

    @UiThread
    public void share(ShareContent shareContent, @TentcentShareType int shareType) {

        if (shareType == QQ) {
            shareToQQ((Activity) mContext, shareContent);
        } else if (shareType == QZONE) {
            shareToQZone((Activity) mContext, shareContent);
        }
    }

    private void shareToQZone(Activity context, ShareContent shareContent) {
        Bundle params = new Bundle();
        //分享类型
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, shareContent.getTitle());//必填 最多200个字符。
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, shareContent.getContent());//选填 最多600字符。
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, shareContent.getURL());//必填
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, shareContent.getImageUrls());//图片*/
        mTencent.shareToQzone(context, params, iUiListener);
    }
}
