package com.third.interfaces;

public interface  PlatformActionListener {



    /**
     * 登录失败
     */
    void onError();

    /**
     * 取消登录
     */
     void onCancel();

}