package com.third.data;

import android.support.annotation.IntDef;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2015-12-27 16:31      <br/>
 * Description：
 */
public class ShareType {
    /**
     * text
     */
    public static final int TEXT = 1;

    /**
     * picture
     */
    public static final int PIC = 2;

    /**
     * webPage
     */
    public static final int WEBPAGE = 3;

    /**
     * music
     */
    public static final int MUSIC = 4;


    @IntDef(value = {TEXT, PIC, WEBPAGE, MUSIC})
    public @interface Share {

    }

}
