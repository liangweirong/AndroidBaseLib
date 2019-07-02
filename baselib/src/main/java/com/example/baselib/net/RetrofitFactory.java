package com.example.baselib.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * retrofit 创建的工厂
 *
 * @author JiaoMin
 * @date 2017/12/08
 */
public class RetrofitFactory {

    /**
     * 创建一个 RxJava 2 为回调适配器的 Retrofit 实例
     * @param okHttpClient OkHttpClient
     * @return Retrofit
     */
    public static Retrofit create(OkHttpClient okHttpClient,String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
