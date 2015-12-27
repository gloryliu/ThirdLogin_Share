package com.kangmei.KmMall;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-18 23:33      <br/>
 * Description：
 */
public class AppContext extends Application {


    private static Handler mGlobalHanlder;

    private static AppContext mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;

        mGlobalHanlder = new Handler(Looper.getMainLooper());
    }

    public static Handler getGlobalHanlder() {
        return mGlobalHanlder;
    }

    public static void post(Runnable runnable) {
        mGlobalHanlder.post(runnable);
    }

    public static AppContext getInstance() {
        return mAppContext;
    }
}
