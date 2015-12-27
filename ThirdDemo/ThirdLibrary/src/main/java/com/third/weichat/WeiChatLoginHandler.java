package com.third.weichat;

import com.third.data.ThirdDataProvieder;
import com.third.model.WeiChatLoginInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-27 1:14      <br/>
 * Description：
 */
public abstract class WeiChatLoginHandler {


    private static final String GET_WEI_TOEKN_URL =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private static final String GET_WEI_USER_INFO =
            "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";


    /**
     * openid : olv4iuNhJW_Q0-TkP_7YUctav3OI
     * nickname : 慢慢
     * sex : 1
     * language : zh_CN
     * city : Yueyang
     * province : Hunan
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/ibjgQ9hujbGS0KN0bcgn3RmIg2iaiaTZ3Cu7lblHaSicB0nwqIJdcSRwNJtLmpDZPMa7uSYZHFpuqkLPA3snZJffjMEfor9Hpyicn/0
     * privilege : []
     * unionid : oMP3GwU-X2cJswGvhnvVQ7OBt6OM
     */


    protected WeiChatLoginInfo parseUserInfo(String result) {
        WeiChatLoginInfo weiChatLoginInfo = new WeiChatLoginInfo();
        try {
            JSONObject jsonObject = new JSONObject(result);
            weiChatLoginInfo.setOpenid(jsonObject.getString("openid"));
            weiChatLoginInfo.setNickname(jsonObject.getString("nickname"));
            weiChatLoginInfo.setSex(jsonObject.getInt("sex"));
            weiChatLoginInfo.setCity(jsonObject.getString("city"));
            weiChatLoginInfo.setProvince(jsonObject.getString("province"));
            weiChatLoginInfo.setCountry(jsonObject.getString("country"));
            weiChatLoginInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
            weiChatLoginInfo.setUnionid(jsonObject.getString("unionid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weiChatLoginInfo;
    }


    void onReceiveAuthCode(String code) {
        onAuthUrl(String.format(GET_WEI_TOEKN_URL, ThirdDataProvieder.getWechatAppId(), ThirdDataProvieder.getWechatSecret(), code));
    }


    protected abstract void onAuthUrl(String authUrl);


    public static String getAccessUserInfoUrl(String authData) throws JSONException {
        JSONObject jsonObject = new JSONObject(authData);
        String access_token = jsonObject.getString("access_token");
        String refresh_token = jsonObject.getString("refresh_token");
        String openid = jsonObject.getString("openid");
        String url = String.format(GET_WEI_USER_INFO, access_token, openid);
        return url;
    }
}
