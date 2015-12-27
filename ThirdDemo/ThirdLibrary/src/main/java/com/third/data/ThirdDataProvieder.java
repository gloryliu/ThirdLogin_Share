package com.third.data;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-26 13:03      <br/>
 * Description：
 */
public class ThirdDataProvieder {

    private static String mWechatAppId = "";

    private static String mWeiboAppId = "";

    private static String mQQAppId = "";

    private static String mWechatSecret = "";


    private static String mRedirectUrl = "https://api.weibo.com/oauth2/default.html";// 应用的回调页

    public static final String SCOPE =                               // 应用申请的高级权限
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";


    /**
     * init wechat config
     */
    public static void initWechat(String wechatAppId, String wechatSecret) {
        mWechatAppId = wechatAppId;
        mWechatSecret = wechatSecret;
    }


    /**
     * init weibo config
     */
    public static void initWeibo(String weiboAppId) {

        mWeiboAppId = weiboAppId;
    }

    /**
     * init QQ config
     */
    public static void initQQ(String qqAppId) {

        mQQAppId = qqAppId;
    }

    public static void initWeiboRedriectUrl(String url) {
        mRedirectUrl = url;
    }


    public static String getWechatAppId() {
        return mWechatAppId;
    }

    public static String getWeiboAppId() {
        return mWeiboAppId;
    }

    public static String getQQAppId() {
        return mQQAppId;
    }

    public static String getWechatSecret() {
        return mWechatSecret;
    }


    public static String getRedriectUrl() {
        return mRedirectUrl;
    }

}
