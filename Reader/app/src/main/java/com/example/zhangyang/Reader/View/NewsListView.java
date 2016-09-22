package com.example.zhangyang.Reader.View;

import com.example.zhangyang.Reader.Bean.News;

import java.util.List;

/**
 * Created by zhangyang on 16/8/26.
 */
public interface NewsListView extends BaseView {
    void initNewsList(List<News.DataBean.ArticlesBean> beanList);

    void addNewsList(List<News.DataBean.ArticlesBean> beanList);
}
