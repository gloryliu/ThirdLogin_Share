package com.third.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-26 12:57      <br/>
 * Description：
 */
public  class ShareContent {


    private String content;
    private String title;
    private String URL;
    private String targetUrl;
    private String imageUrl;
    private ArrayList<String> mImageUrls;
    private String mMusicUrl;

    private Bitmap mShareImageBitmap;


    public Bitmap getShareImageBitmap() {
        return mShareImageBitmap;
    }

    public void setShareImageBitmap(Bitmap shareImageBitmap) {
        mShareImageBitmap = shareImageBitmap;
    }

    public String getMusicUrl() {
        return mMusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        mMusicUrl = musicUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public ArrayList<String> getImageUrls() {
        return mImageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        mImageUrls = imageUrls;
    }
}
