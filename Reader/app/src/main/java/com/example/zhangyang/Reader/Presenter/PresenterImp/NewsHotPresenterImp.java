package com.example.zhangyang.Reader.Presenter.PresenterImp;

import com.example.zhangyang.Reader.Bean.News;
import com.example.zhangyang.Reader.Model.NewsHotModelImp;
import com.example.zhangyang.Reader.Presenter.INewsListCallBack;
import com.example.zhangyang.Reader.View.NewsListView;

import java.util.List;

/**
 * Created by zhangyang on 16/9/17.
 */
public class NewsHotPresenterImp extends BasePresenterImp<NewsListView, List<News.DataBean.ArticlesBean>> implements INewsListCallBack {
    private NewsListView view;
    private String since_date, nt;
    private NewsHotModelImp modelImp;

    public NewsHotPresenterImp(NewsListView view) {
        this.view = view;
        modelImp=new NewsHotModelImp();
    }

    @Override
    public void unscribe() {
        super.unscribe();
        modelImp.unsubscribe();
    }

    public void getNewsList(){
        modelImp.loadNewsHot(this);
    }

    public void getMoreNewsList(){
        modelImp.loadMoreNewsHot(since_date,nt,this);
    }

    @Override
    public void showNewsList(List<News.DataBean.ArticlesBean> beanList) {
        view.initNewsList(beanList);
    }

    @Override
    public void loadMoreNews(List<News.DataBean.ArticlesBean> beanList) {
        view.addNewsList(beanList);
    }

    @Override
    public void setNexturl(String nexturl) {
        since_date = nexturl.substring(nexturl.indexOf("since_date=") + 11, nexturl.indexOf("&nt="));
        nt = nexturl.substring(nexturl.indexOf("&nt") + 4, nexturl.indexOf("&nt") + 5);
    }
}
