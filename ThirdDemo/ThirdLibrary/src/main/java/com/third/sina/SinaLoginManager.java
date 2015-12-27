package com.third.sina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.third.data.ThirdDataProvieder;
import com.third.interfaces.SinaLoginCallBack;

/**
 * Created by echo on 5/19/15.
 */
public class SinaLoginManager {

    private static final String SCOPE =
            "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog";

    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";


    private Context mContext;

    private static String mSinaAppKey;

    private AuthInfo mAuthInfo = null;

    private UsersAPI userAPI;

    private SinaLoginCallBack mSinaLoginCallBack;


    private String mRedirectUrl;


    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private static SsoHandler mSsoHandler;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
    public SinaLoginManager(Context context) {

        mContext = context;
        mSinaAppKey = ThirdDataProvieder.getWeiboAppId();
        mRedirectUrl = TextUtils.isEmpty(ThirdDataProvieder.getRedriectUrl()) ? REDIRECT_URL : ThirdDataProvieder.getRedriectUrl();

    }


    public static SsoHandler getSsoHandler() {
        return mSsoHandler;
    }

    public void login(SinaLoginCallBack sinaLoginCallBack) {
        mSinaLoginCallBack = sinaLoginCallBack;
        AccessTokenKeeper.clear(mContext);
        mAuthInfo = new AuthInfo(mContext, mSinaAppKey, mRedirectUrl, SCOPE);
        mSsoHandler = new SsoHandler((Activity) mContext, mAuthInfo);
        mSsoHandler.authorize(new AuthListener());

    }

    /**
     * * 1. SSO 授权时，需要在 onActivityResult 中调用 {@link SsoHandler#authorizeCallBack} 后，
     * 该回调才会被执行。
     * 2. 非SSO 授权时，当授权结束后，该回调就会被执行
     */
    private class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            final Oauth2AccessToken accessToken = Oauth2AccessToken.parseAccessToken(values);
            if (accessToken != null && accessToken.isSessionValid()) {
                AccessTokenKeeper.writeAccessToken(mContext, accessToken);
                userAPI = new UsersAPI(mContext, mSinaAppKey, accessToken);
                userAPI.show(Long.parseLong(accessToken.getUid()), mListener);
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            if (mSinaLoginCallBack != null) {
                mSinaLoginCallBack.onError();
            }
        }

        @Override
        public void onCancel() {
            if (mSinaLoginCallBack != null) {
                mSinaLoginCallBack.onCancel();
            }
        }
    }

    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {

                // 调用 User#parse 将JSON串解析成User对象
                User user = User.parse(response);


                if (mSinaLoginCallBack != null) {
                    mSinaLoginCallBack.onComplete(user);
                }
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            if (mSinaLoginCallBack != null) {
                mSinaLoginCallBack.onError();
            }
        }
    };





}
