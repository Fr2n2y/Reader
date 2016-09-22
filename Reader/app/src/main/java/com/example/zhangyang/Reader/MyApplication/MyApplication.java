package com.example.zhangyang.Reader.MyApplication;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.zhangyang.Reader.Utils.NewsInfo;

/**
 * Created by zhangyang on 16/8/23.
 */
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        initNews();
    }

    private void initNews() {
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        NewsInfo.set_bsize(metrics.widthPixels,metrics.heightPixels);
    }
    public static Context getContext(){
        return context;
    }
}
