package com.third.interfaces;

import com.third.sina.User;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-27 17:18      <br/>
 * Description：
 */
public interface SinaLoginCallBack extends PlatformActionListener {

    void onComplete(User user);
}
