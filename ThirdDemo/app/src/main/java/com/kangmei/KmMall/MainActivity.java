package com.kangmei.KmMall;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.third.data.ShareType;
import com.third.data.ThirdDataProvieder;
import com.third.interfaces.QQLoginCallBack;
import com.third.interfaces.SinaLoginCallBack;
import com.third.interfaces.SinaShareCallBack;
import com.third.model.QQLoginInfo;
import com.third.model.ShareContent;
import com.third.qq.QQLoginManager;
import com.third.qq.QQShareManager;
import com.third.sina.User;
import com.third.sina.SinaLoginManager;
import com.third.sina.SinaShareManager;
import com.third.weichat.WeiChatLoginManager;
import com.third.weichat.WeiChatShareManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IUiListener {


    private QQShareManager mQqShareManager;
    private QQLoginManager mQqLoginManager;
    private WeiChatShareManager mWeiChatShareManager;
    private WeiChatLoginManager mWeiChatLoginManager;


    private SinaShareManager mWeiboShareManager;
    private SinaLoginManager mWeiboLoginManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ThirdDataProvieder.initQQ(Const.QQ_APP_ID);
        ThirdDataProvieder.initWechat(Const.WEI_CHAT_APP_ID, Const.WEI_CHAT_APP_SECRET);
        ThirdDataProvieder.initWeibo(Const.SINA_APP_KEY);


        mQqShareManager = new QQShareManager(this);
        mQqLoginManager = new QQLoginManager(this);
        mWeiChatShareManager = new WeiChatShareManager(this);
        mWeiChatLoginManager = new WeiChatLoginManager(this);
        mWeiboShareManager = new SinaShareManager(this);
        mWeiboLoginManager = new SinaLoginManager(this);

        mQqShareManager.setiUiListener(this);


        mWeiboShareManager.setSinaShareCallBack(new SinaShareCallBack() {
            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();

            }
        });

    }

    ///////////////////////////////////////////////////////////////////////////
    // qq
    ///////////////////////////////////////////////////////////////////////////


    public void qqLogin(View view) {
        mQqLoginManager.login(new QQLoginCallBack() {
            @Override
            public void onComplete(QQLoginInfo userInfo) {
                Log.d("MainActivity", "userInfo:" + userInfo);
            }

            @Override
            public void onError() {
                Log.d("MainActivity", "onError:");
            }

            @Override
            public void onCancel() {
                Log.d("MainActivity", "onCancel:");
            }
        });
    }


    public void qqZoneShare(View view) {
        qShare(QQShareManager.QZONE);

    }

    public void qqShare(View view) {

        qShare(QQShareManager.QQ);

    }

    private void qShare(int type) {
        ShareContent shareContent = new ShareContent();
        shareContent.setContent("测试一下 ，第三方集成 ，来几个美女，我擦");
        shareContent.setTitle("湛添友-在测试分享");
        shareContent.setURL("http://www.race604.com/android-nested-scrolling/");
        shareContent.setImageUrl("http://ww3.sinaimg.cn/large/7a8aed7bjw1ezbriom623j20hs0qoadv.jpg");
        //如果是分享到qq空间，使用下面方法添加图片
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("http://ww3.sinaimg.cn/large/7a8aed7bjw1ezbriom623j20hs0qoadv.jpg");
        arrayList.add("http://ww4.sinaimg.cn/large/7a8aed7bjw1ezak8074s3j20qo0k0adz.jpg");
        shareContent.setImageUrls(arrayList);

        mQqShareManager.share(shareContent, type);
    }


    ///////////////////////////////////////////////////////////////////////////
    // 微信
    ///////////////////////////////////////////////////////////////////////////

    public void weiChatLogin(View view) {
        mWeiChatLoginManager.login();
    }

    public void weiChatShare(View view) {
        wxShare(WeiChatShareManager.FRIEND);
    }

    public void weiChatTimeLineShare(View view) {
        wxShare(WeiChatShareManager.TIME_LINE);

    }


    private void wxShare(int type) {
        ShareContent shareContent = new ShareContent();
        shareContent.setContent("测试一下 ，第三方集成 ，来几个美女，我擦" + "美女：http://www.race604.com/android-nested-scrolling/");
        shareContent.setTitle("湛添友-在测试分享");
        shareContent.setURL("http://www.race604.com/android-nested-scrolling/");
        shareContent.setImageUrl("http://ww3.sinaimg.cn/large/7a8aed7bjw1ezbriom623j20hs0qoadv.jpg");
        //分析PIC或者WEBPAGE请添加图片，直接添加bitmap
        shareContent.setShareImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        mWeiChatShareManager.share(shareContent, type, ShareType.WEBPAGE);
    }


    ///////////////////////////////////////////////////////////////////////////
    // 新浪
    ///////////////////////////////////////////////////////////////////////////


    public void sinaLogin(View view) {
        mWeiboLoginManager.login(new SinaLoginCallBack() {
            @Override
            public void onComplete(User user) {
                Toast.makeText(MainActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "user:" + user);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void sinaShare(View view) {
        ShareContent shareContent = new ShareContent();
        shareContent.setContent("测试一下 ，第三方集成 ，来几个美女，我擦" + "美女：http://www.race604.com/android-nested-scrolling/");
        shareContent.setTitle("湛添友-在测试分享");
        shareContent.setURL("http://www.race604.com/android-nested-scrolling/");
        shareContent.setImageUrl("http://ww3.sinaimg.cn/large/7a8aed7bjw1ezbriom623j20hs0qoadv.jpg");
        //分析PIC或者WEBPAGE请添加图片，直接添加bitmap
        shareContent.setShareImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        mWeiChatShareManager.share(shareContent, WeiChatShareManager.FRIEND, ShareType.WEBPAGE);
        mWeiboShareManager.share(shareContent, ShareType.WEBPAGE);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(MainActivity.this, "onNewIntent", Toast.LENGTH_SHORT).show();


        mWeiboShareManager.onNweIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mWeiboLoginManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onComplete(Object o) {
        Toast.makeText(MainActivity.this, "onComplete", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(UiError uiError) {
        Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancel() {
        Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();

    }
}
