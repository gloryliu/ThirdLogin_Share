package com.kangmei.KmMall.http;

import android.util.Log;

import com.kangmei.KmMall.AppContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-20 20:25      <br/>
 * Description：
 */
public class HttpUtil {


    public static void get(final String sUrl, final OnResultListener onResultListener) {

        new Thread() {
            @Override
            public void run() {
                URL url = null;
                OutputStream out = null;
                InputStream inputStream = null;
                try {

                    url = new URL(sUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    conn.setConnectTimeout(5 * 1000);
                    conn.setReadTimeout(5 * 1000);


                    if (conn.getResponseCode() == 200) {//200 OKcon

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                        inputStream = conn.getInputStream();
                        byte[] buff = new byte[1024];
                        int offset = -1;
                        while ((offset = inputStream.read(buff)) != -1) {
                            byteArrayOutputStream.write(buff, 0, offset);
                        }
                        final String s = new String(byteArrayOutputStream.toByteArray(), "UTF-8");

                        if (onResultListener != null) {
                            AppContext.post(new Runnable() {
                                @Override
                                public void run() {
                                    onResultListener.onResult(s);
                                }
                            });
                        }

                        Log.d("HttpUtil", s);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {

                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }


}
