package com.third.model;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-26 23:10      <br/>
 * Description：
 */
public class QQLoginInfo {

    private String mNickName;
    private String mOpenID;
    private String mGender;
    private String nHeadIcon;

    @Override
    public String toString() {
        return "QQLoginInfo{" +
                "mNickName='" + mNickName + '\'' +
                ", mOpenID='" + mOpenID + '\'' +
                ", mGender='" + mGender + '\'' +
                ", nHeadIcon='" + nHeadIcon + '\'' +
                '}';
    }

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String nickName) {
        mNickName = nickName;
    }

    public String getOpenID() {
        return mOpenID;
    }

    public void setOpenID(String openID) {
        mOpenID = openID;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getnHeadIcon() {
        return nHeadIcon;
    }

    public void setnHeadIcon(String nHeadIcon) {
        this.nHeadIcon = nHeadIcon;
    }
}
