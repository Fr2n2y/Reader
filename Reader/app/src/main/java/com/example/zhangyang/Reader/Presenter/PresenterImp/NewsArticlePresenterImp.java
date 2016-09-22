package com.example.zhangyang.Reader.Presenter.PresenterImp;

import com.example.zhangyang.Reader.Model.NewsArticleModleImp;
import com.example.zhangyang.Reader.Presenter.INewsCallBack;
import com.example.zhangyang.Reader.View.NewsView;

/**
 * Created by zhangyang on 16/8/26.
 */
public class NewsArticlePresenterImp extends BasePresenterImp<NewsView, String> implements INewsCallBack {
    private NewsArticleModleImp modleImp;
    private NewsView view;
    private String pk;
    public NewsArticlePresenterImp(NewsView view,String pk) {
        super();
        this.view=view;
        this.pk=pk;
        modleImp = new NewsArticleModleImp();
    }

    public void getArticles(){
        modleImp.loadNewsAricle(3,pk,this);
    }
    @Override
    public void showArticles(String html) {
        view.initWebView(html);
    }
}
