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
 * Created by zhangyang on 16/8/26.
 */
public class NewsModelImp extends BaseModel{
    public NewsModelImp() {
        super("http://iphone.myzaker.com/");
    }

    public void loadNewsList(int id, final INewsListCallBack callBack){
        ZakerNews news=retrofit.create(ZakerNews.class);
        Subscription subscription = news.getNews(id)
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
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<News.DataBean.ArticlesBean> articlesBeen) {
                        callBack.showNewsList(articlesBeen);
                    }
                });
        addSubscription(subscription);
    }
    public void loadMore(int id,String since_date,String nt,String next_article_id,String opage,final INewsListCallBack callBack){
        ZakerNews news=retrofit.create(ZakerNews.class);
        Subscription subscription = news.loadmore(id,since_date,nt,next_article_id,opage)
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
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<News.DataBean.ArticlesBean> articlesBeen) {
                        callBack.loadMoreNews(articlesBeen);
                    }
                });
        addSubscription(subscription);
    }
}
