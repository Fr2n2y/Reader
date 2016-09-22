package com.example.zhangyang.Reader.Interface;

import com.example.zhangyang.Reader.Bean.News;
import com.example.zhangyang.Reader.Bean.NewsArticles;
import com.example.zhangyang.Reader.Bean.Update;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhangyang on 16/8/20.
 */
public interface ZakerNews {
    //_appid=AndroidPhone&_bsize=1080_1920&_version=6.7
    @GET("/zaker/news.php")
    Observable<News> getNews(@Query("app_id") int app_id);

    @GET("/zaker/daily_hot.php")
    Observable<News> getHotNews();

    @GET("/zaker/blog2news.php?")
    Observable<News> loadmore(@Query("app_id") int app_id
            , @Query("since_date") String since_date
            , @Query("nt") String nt
            , @Query("next_aticle_id") String next_aticle_id
            , @Query("opage") String opage);

    @GET("/zaker/article_mongo.php?&style_v=2")
    Observable<NewsArticles> getArticles(@Query("app_id") int app_id
            , @Query("pk") String pk);

    @GET("/zaker/daily_hot.php")
    Observable<News> getMore(@Query("since_date") String since_date
            , @Query("nt") String nt);
    @GET("/release/update.json")
    Observable<Update> getUpdate();
}
