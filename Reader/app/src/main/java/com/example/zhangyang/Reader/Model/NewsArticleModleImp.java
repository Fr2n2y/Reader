package com.example.zhangyang.Reader.Model;

import com.example.zhangyang.Reader.Bean.NewsArticles;
import com.example.zhangyang.Reader.Interface.ZakerNews;
import com.example.zhangyang.Reader.Presenter.INewsCallBack;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyang on 16/8/26.
 */
public class NewsArticleModleImp extends BaseModel {
    public NewsArticleModleImp() {
        super("http://iphone.myzaker.com/");
    }
    public void loadNewsAricle(int id, String pk,final INewsCallBack iNewsCallBack){
        ZakerNews news = retrofit.create(ZakerNews.class);
        news.getArticles(id,pk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<NewsArticles, String>() {
                    @Override
                    public String call(NewsArticles newsArticles) {
                        return newsArticles.getData().getContent();
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        iNewsCallBack.showArticles(s);
                    }
                });
    }
}
