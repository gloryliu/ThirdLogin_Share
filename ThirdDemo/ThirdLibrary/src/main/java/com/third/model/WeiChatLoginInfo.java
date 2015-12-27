package com.third.model;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-26 23:10      <br/>
 * Description：
 */
public class WeiChatLoginInfo {

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

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;

    @Override
    public String toString() {
        return "WeiChatLoginInfo{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }



    public String getOpenid() {
        return openid;
    }

    public String getNickname() {
        return nickname;
    }

    public int getSex() {
        return sex;
    }

    public String getLanguage() {
        return language;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }


}
