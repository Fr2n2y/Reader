package com.example.zhangyang.Reader.Utils;

import com.example.zhangyang.Reader.MyApplication.MyApplication;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangyang on 16/8/25.
 */
public class RetrofitManager {
    private static OkHttpClient client;
    private static Cache cache;

    public RetrofitManager() {

    }

    private static void initClient() {
        cache = new Cache(new File(MyApplication.getContext().getCacheDir()
                , "HttpCache"), 1024 * 1024 * 100);
        client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(addQuryInterceptor)
                .addInterceptor(cacheInterceptor)
                .build();
    }

    public static Retrofit builder(String baseurl) {
        initClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    private static Interceptor addQuryInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter(Constants.APPID, NewsInfo.get_appid())
                    .addQueryParameter(Constants.BSIZE, NewsInfo.get_appid())
                    .addQueryParameter(Constants.VERSION, NewsInfo.get_version())
                    .build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }
    };
    private static Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!InternetUtils.isNetConnect(MyApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (InternetUtils.isNetConnect(MyApplication.getContext())) {
                int maxAge = 60 * 60;
                response.newBuilder().removeHeader("Pragma")
                        .header("Cache-Control", "public,max-age" + maxAge)
                        .build();
            } else {
                int maxAge = 60 * 60 * 24 * 7;
                response.newBuilder().removeHeader("Pragma")
                        .header("Cache-Control", "public,only-if-cached,max-age" + maxAge)
                        .build();
            }
            return response;
        }
    };
}
