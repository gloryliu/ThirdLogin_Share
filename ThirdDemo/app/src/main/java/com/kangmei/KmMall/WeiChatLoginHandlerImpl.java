package com.kangmei.KmMall;

import android.util.Log;
import android.widget.Toast;

import com.kangmei.KmMall.http.HttpUtil;
import com.kangmei.KmMall.http.OnResultListener;
import com.third.weichat.WeiChatLoginHandler;

import org.json.JSONException;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-27 1:30      <br/>
 * Description：
 */
public class WeiChatLoginHandlerImpl extends WeiChatLoginHandler {

    private static WeiChatLoginHandlerImpl weiChatLoginHandler = new WeiChatLoginHandlerImpl();

    public static WeiChatLoginHandlerImpl getInstance() {
        return weiChatLoginHandler;
    }


    @Override
    protected void onAuthUrl(String authUrl) {
        Log.d("WeiChatLoginHandlerImpl", authUrl);
        HttpUtil.get(authUrl, new OnResultListener() {
            @Override
            public void onResult(String result) {
                try {
                    requestUserInfo(getAccessUserInfoUrl(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AppContext.getInstance(), "onAuthUrl", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestUserInfo(String accessUserInfoUrl) {
        HttpUtil.get(accessUserInfoUrl, new OnResultListener() {
            @Override
            public void onResult(String result) {
                Log.d("WeiChatLoginHandlerImpl", "parseUserInfo(result):" + parseUserInfo(result));
            }
        });
    }

}
