package com.third.interfaces;

import com.third.model.QQLoginInfo;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-26 23:10      <br/>
 * Description：
 */
public interface QQLoginCallBack extends PlatformActionListener {

    /**
     * 登录成功
     *
     * @param loginInfo
     */
   void onComplete(QQLoginInfo loginInfo);


}
