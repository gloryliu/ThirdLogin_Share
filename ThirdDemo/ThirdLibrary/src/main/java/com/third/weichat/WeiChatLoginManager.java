package com.third.weichat;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.third.R;
import com.third.data.ThirdDataProvieder;

/**
 * Created by echo on 5/19/15.
 */
public class WeiChatLoginManager {

    private static final String SCOPE = "snsapi_userinfo";

    private static final String STATE = "lls_engzo_wechat_login";


    private String mWeChatAppId;

    private static IWXAPI mIWXAPI;


    public WeiChatLoginManager(Context context) {
        mWeChatAppId = ThirdDataProvieder.getWechatAppId();
        if (!TextUtils.isEmpty(mWeChatAppId)) {
            mIWXAPI = WXAPIFactory.createWXAPI(context, mWeChatAppId, true);
            if (!mIWXAPI.isWXAppInstalled()) {
                Toast.makeText(context, context.getString(R.string.share_install_wechat_tips), Toast.LENGTH_SHORT).show();
                return;
            } else {
                mIWXAPI.registerApp(mWeChatAppId);
            }
        }
    }


    public static IWXAPI getIWXAPI() {
        return mIWXAPI;
    }


    public void login() {
        if (mIWXAPI != null) {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = SCOPE;
            req.state = STATE;
            mIWXAPI.sendReq(req);
        }
    }
}
