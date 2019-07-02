package com.example.baselib.net;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import com.example.baselib.BuildConfig;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * retrofit客户端
 */
public class RetrofitClient {
    /**
     * 项目中的 BaseUrl
     */
    private static String baseUrl;
    /**
     * 自定义的 okhttp 拦截器
     */
    private static Interceptor interceptor;
    /**
     * Retrofit
     */
    private  Retrofit retrofit;

    /**
     * 需要在 application 里面初始化
     *
     * @param baseUrl     项目中的 baseUrl
     * @param interceptor 自定义的 okHttp 拦截器，可以为 null
     */
    public static void initRetrofitClient(@NonNull String baseUrl, @Nullable Interceptor interceptor) {
        RetrofitClient.baseUrl = baseUrl;
        RetrofitClient.interceptor = interceptor;
    }



    private RetrofitClient(String baseUrl, Interceptor interceptor) {
//        LogUtil.d("RetrofitClient RetrofitClient init：");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        Log.i("okhttp",BuildConfig.DEBUG+"");
        if (BuildConfig.DEBUG) {
            // 如果是 debug 才开启日志
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    if(message.contains("Content-Type"))
                        return;
                    if(message.contains("Content-Length"))
                        return;
                    if(message.contains("Server"))
                        return;
                    if(message.contains("Date"))
                        return;
                    if(message.contains("Content-Type"))
                        return;
                    if(message.contains("Transfer-Encoding"))
                        return;
                    if(message.contains("Connection"))
                        return;
                    if(message.contains("X-Powered-By"))
                        return;
                    if(message.contains("Set-Cookie"))
                        return;
                    if(message.contains("Expires"))
                        return;
                    if(message.contains("Cache-Control"))
                        return;
                    if(message.contains("Pragma"))
                        return;
                    if(message.contains("END"))
                        return;
                    if(message.contains("Pragma"))
                        return;

                    Log.i("okhttp", message);
                }
            });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(httpLoggingInterceptor);
        }

        if (interceptor != null) {
            builder.addInterceptor(interceptor);
        }

        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient(baseUrl, interceptor);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
