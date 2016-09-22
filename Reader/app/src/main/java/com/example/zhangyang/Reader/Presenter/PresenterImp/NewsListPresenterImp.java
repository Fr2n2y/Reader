package com.example.zhangyang.Reader.Presenter.PresenterImp;

import com.example.zhangyang.Reader.Bean.News;
import com.example.zhangyang.Reader.Model.NewsModelImp;
import com.example.zhangyang.Reader.Presenter.INewsListCallBack;
import com.example.zhangyang.Reader.View.NewsListView;

import java.util.List;

/**
 * Created by zhangyang on 16/8/26.
 */
public class NewsListPresenterImp extends BasePresenterImp<NewsListView, List<News.DataBean.ArticlesBean>> implements INewsListCallBack {
    private NewsListView view;
    private String since_date, nt, next_aticle_id, opage;
    private NewsModelImp modelImp;
    private int app_id;

    public NewsListPresenterImp(NewsListView view,int app_id) {
        this.view = view;
        modelImp = new NewsModelImp();
        this.app_id=app_id;
    }

    @Override
    public void unscribe() {
        super.unscribe();
        modelImp.unsubscribe();
    }

    public void getNewsList() {
        modelImp.loadNewsList(app_id, this);
    }

    public void getMoreList() {
        modelImp.loadMore(app_id,since_date,nt,next_aticle_id,opage, this);
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
        nt = nexturl.substring(nexturl.indexOf("&nt") + 4, nexturl.indexOf("&next"));
        next_aticle_id = nexturl.substring(nexturl.indexOf("&next") + 16, nexturl.indexOf("&_appid"));
        opage = nexturl.substring(nexturl.indexOf("&opage") + 7, nexturl.indexOf("&ot"));
    }

}
