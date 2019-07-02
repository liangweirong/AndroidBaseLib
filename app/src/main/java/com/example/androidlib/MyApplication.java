package com.example.androidlib;

import android.app.Application;

import com.example.baselib.context.Utils;
import com.example.baselib.net.RetrofitClient;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        RetrofitClient.initRetrofitClient("http://www.kuaidi100.com/", null);

    }
}
