package com.ywk.retrofits;

import androidx.annotation.NonNull;


import com.safframework.http.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * http日志拦截器
 *
 * @author : WKJ
 * @date : 2019/6/26/0026 10:40:22
 */
public class HttpLoggingInterceptorUtil {

    @NonNull
    public static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.writeTimeout(10 * 1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(10 * 1000, TimeUnit.MILLISECONDS);
        builder.connectTimeout(10 * 1000, TimeUnit.MILLISECONDS);

        LoggingInterceptor loggingInterceptor = new LoggingInterceptor.Builder()
                .loggable((BuildConfig.DEBUG))
                .request()
                .requestTag("sss")
                .response()
                .responseTag("sss")
                // 隐藏竖线边框
                .hideVerticalLine()
                .build();

        // 设置拦截器
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }
}
