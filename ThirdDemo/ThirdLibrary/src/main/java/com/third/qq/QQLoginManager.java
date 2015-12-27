package com.third.qq;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.third.data.ThirdDataProvieder;
import com.third.interfaces.QQLoginCallBack;
import com.third.model.QQLoginInfo;

import org.json.JSONException;
import org.json.JSONObject;


public class QQLoginManager {


    private static final String TAG = QQLoginManager.class.getSimpleName();
    private Context mContext;

    private String mAppId;

    private Tencent mTencent;

    protected QQLoginCallBack mQQLoginCallBack;


    public QQLoginManager(Context context) {
        mContext = context;
        mAppId = ThirdDataProvieder.getQQAppId();
        if (!TextUtils.isEmpty(mAppId)) {
            mTencent = Tencent.createInstance(mAppId, context.getApplicationContext());
        } else {
            throw new IllegalStateException("QQLoginManager：请先初始化 appId");
        }
    }


    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }


    public void login(QQLoginCallBack callBack) {

        if (!mTencent.isSessionValid()) {
            Log.d(TAG, "login");
            mQQLoginCallBack = callBack;
            mTencent.login((Activity) mContext, "all", new IUiListener() {
                @Override
                public void onComplete(Object object) {
                    Log.d(TAG, "object:" + object);
                    JSONObject jsonObject = (JSONObject) object;
                    initOpenidAndToken(jsonObject);
                    UserInfo info = new UserInfo(mContext, mTencent.getQQToken());
                    info.getUserInfo(new IUiListener() {
                        @Override
                        public void onComplete(Object object) {

                            try {
                                JSONObject jsonObject = (JSONObject) object;

                                QQLoginInfo qqLoginInfo = new QQLoginInfo();

                                qqLoginInfo.setNickName(jsonObject.getString("nickname"));
                                qqLoginInfo.setGender(jsonObject.getString("gender"));
                                qqLoginInfo.setnHeadIcon(jsonObject.getString("figureurl_qq_2"));
                                qqLoginInfo.setOpenID(mTencent.getOpenId());


                                if (mQQLoginCallBack != null) {
                                    mQQLoginCallBack
                                            .onComplete(qqLoginInfo);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                if (mQQLoginCallBack != null) {
                                    mQQLoginCallBack
                                            .onError();
                                }
                            }


                        }

                        @Override
                        public void onError(UiError uiError) {
                            Log.d(TAG, "onError:");
                            if (mQQLoginCallBack != null) {
                                mQQLoginCallBack
                                        .onError();
                            }
                        }

                        @Override
                        public void onCancel() {
                            Log.d(TAG, "onCancel");
                            if (mQQLoginCallBack != null) {
                                mQQLoginCallBack
                                        .onCancel();
                            }
                        }
                    });
                }

                @Override
                public void onError(UiError uiError) {
                    if (mQQLoginCallBack != null) {
                        mQQLoginCallBack
                                .onError();
                    }
                }

                @Override
                public void onCancel() {
                    if (mQQLoginCallBack != null) {
                        mQQLoginCallBack
                                .onCancel();
                    }
                }
            });

        } else {
            Log.d(TAG, "Error");
            mTencent.logout(mContext);
        }
    }

}


