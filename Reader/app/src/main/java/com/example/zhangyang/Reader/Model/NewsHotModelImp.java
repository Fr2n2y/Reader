package com.example.zhangyang.Reader.Model;

import com.example.zhangyang.Reader.Bean.News;
import com.example.zhangyang.Reader.Interface.ZakerNews;
import com.example.zhangyang.Reader.Presenter.INewsListCallBack;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyang on 16/9/17.
 */
public class NewsHotModelImp extends BaseModel{
    public NewsHotModelImp() {
        super("http://iphone.myzaker.com/");
    }

    public void loadNewsHot(final INewsListCallBack callBack){
        ZakerNews zakerNews = retrofit.create(ZakerNews.class);
        Subscription subscription=zakerNews.getHotNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<News, List<News.DataBean.ArticlesBean>>() {
                    @Override
                    public List<News.DataBean.ArticlesBean> call(News news) {
                        callBack.setNexturl(news.getData().getInfo().getNext_url());
                        return news.getData().getArticles();
                    }
                }).subscribe(new Subscriber<List<News.DataBean.ArticlesBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<News.DataBean.ArticlesBean> articlesBeen) {
                callBack.showNewsList(articlesBeen);
            }
        });
        addSubscription(subscription);
    }
    public void loadMoreNewsHot(String since_date,String nt,final INewsListCallBack callBack){
        ZakerNews zakerNews=retrofit.create(ZakerNews.class);
        Subscription subscription = zakerNews.getMore(since_date, nt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<News, List<News.DataBean.ArticlesBean>>() {
                    @Override
                    public List<News.DataBean.ArticlesBean> call(News news) {
                        callBack.setNexturl(news.getData().getInfo().getNext_url());
                        return news.getData().getArticles();
                    }
                })
                .subscribe(new Subscriber<List<News.DataBean.ArticlesBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<News.DataBean.ArticlesBean> articlesBeen) {
                        callBack.loadMoreNews(articlesBeen);
                    }
                });
        addSubscription(subscription);
    }
}
