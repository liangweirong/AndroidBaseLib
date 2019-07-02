package com.example.baselib.context;

import android.content.Context;

public class Utils {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }
    /**
     * 获取上下文对象
     */
    public static Context getContext() {
        if (mContext == null) {
            throw new NullPointerException("u should init first");
        }
        return mContext;
    }
}
