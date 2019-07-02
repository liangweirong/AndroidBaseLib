package com.example.androidlib.api;


import com.example.baselib.net.BaseNetBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppApi {
    /**
     * 主页 -首页的接口
     *
     * @return
     */
    @GET("query")
    Observable<BaseNetBean> query(@Query("type") String type, @Query("postid") String postid);
}
