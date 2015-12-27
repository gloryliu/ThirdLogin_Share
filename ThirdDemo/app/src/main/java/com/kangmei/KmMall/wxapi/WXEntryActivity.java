package com.kangmei.KmMall.wxapi;


import android.widget.Toast;

import com.kangmei.KmMall.WeiChatLoginHandlerImpl;
import com.third.weichat.WechatHandlerActivity;
import com.third.weichat.WeiChatLoginHandler;

public class WXEntryActivity extends WechatHandlerActivity {


    @Override
    protected WeiChatLoginHandler getWeiChatLoginHandler() {
        return WeiChatLoginHandlerImpl.getInstance();
    }

    @Override
    protected void onShareSuccess() {
        Toast.makeText(WXEntryActivity.this, "onShareSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onShareError() {
        Toast.makeText(WXEntryActivity.this, "onShareError", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onLoginError() {
        Toast.makeText(WXEntryActivity.this, "onLoginError", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onShareCanecl() {
        Toast.makeText(WXEntryActivity.this, "onShareCanecl", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onLoginCancel() {
        Toast.makeText(WXEntryActivity.this, "onLoginCancel", Toast.LENGTH_SHORT).show();

    }
}