package com.example.zhangyang.Reader.Presenter;

import com.example.zhangyang.Reader.Bean.News;

import java.util.List;

/**
 * Created by zhangyang on 16/8/26.
 */
public interface INewsListCallBack {
    void showNewsList(List<News.DataBean.ArticlesBean> beanList);
    void loadMoreNews(List<News.DataBean.ArticlesBean> beanList);
    void setNexturl(String nexturl);
}
