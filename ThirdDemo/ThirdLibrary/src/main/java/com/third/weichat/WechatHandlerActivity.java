package com.third.weichat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

/**
 * Created by echo on 5/19/15.
 */
public abstract class WechatHandlerActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI mIWXAPI;


    /**
     * BaseResp的getType函数获得的返回值，1:第三方授权， 2:分享
     */
    private static final int TYPE_LOGIN = 1;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = WechatHandlerActivity.this;
        mIWXAPI = WeiChatLoginManager.getIWXAPI();
        if (mIWXAPI != null) {
            mIWXAPI.handleIntent(getIntent(), this);
        }
        finish();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mIWXAPI != null) {
            mIWXAPI.handleIntent(getIntent(), this);
        }
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
        finish();
    }


    @Override
    public void onResp(BaseResp resp) {


        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:

                if (resp.getType() == TYPE_LOGIN) {
                    final String code = ((SendAuth.Resp) resp).code;
                    getWeiChatLoginHandler().onReceiveAuthCode(code);
                    finish();
                } else {
                    onShareSuccess();
                    finish();
                }

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:

                if (resp.getType() == TYPE_LOGIN) {
                    onLoginCancel();
                } else {
                    onShareCanecl();
                }
                finish();
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                if (resp.getType() == TYPE_LOGIN) {
                    onLoginError();
                } else {
                    onShareError();
                }
                finish();
                break;
        }
    }

    protected abstract WeiChatLoginHandler getWeiChatLoginHandler();


    protected abstract void onShareSuccess();

    protected abstract void onShareError();

    protected abstract void onLoginError();

    protected abstract void onShareCanecl();

    protected abstract void onLoginCancel();


}
