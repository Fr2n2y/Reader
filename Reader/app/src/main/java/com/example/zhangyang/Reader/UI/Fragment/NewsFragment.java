package com.example.zhangyang.Reader.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangyang.Reader.Bean.News;
import com.example.zhangyang.Reader.Interface.OnRecyclerViewItemClickListener;
import com.example.zhangyang.Reader.Presenter.PresenterImp.NewsHotPresenterImp;
import com.example.zhangyang.Reader.Presenter.PresenterImp.NewsListPresenterImp;
import com.example.zhangyang.Reader.R;
import com.example.zhangyang.Reader.UI.Activity.NewsArticleActivity;
import com.example.zhangyang.Reader.UI.Adapter.RecyclerviewAdapter;
import com.example.zhangyang.Reader.View.NewsListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangyang on 16/8/30.
 */
public class NewsFragment extends BaseFragment implements NewsListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.news_swipe)
    SwipeRefreshLayout newsSwipe;
    private NewsListPresenterImp newsListPresenterImp;
    private NewsHotPresenterImp newsHotPresenterImp;
    @BindView(R.id.re)
    RecyclerView re;
    private Unbinder bind;
    private RecyclerviewAdapter adapter;
    private boolean loading = false;
    private int app_id;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyler, container, false);
        bind = ButterKnife.bind(this, view);
        app_id = getArguments().getInt("app_id");
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (newsHotPresenterImp != null) newsHotPresenterImp.unscribe();
        if (newsListPresenterImp != null) newsListPresenterImp.unscribe();
        bind.unbind();
    }

    public void init() {
        if (app_id == 1057) {
            newsHotPresenterImp = new NewsHotPresenterImp(NewsFragment.this);
            newsHotPresenterImp.getNewsList();
        } else {
            newsListPresenterImp = new NewsListPresenterImp(NewsFragment.this, app_id);
            newsListPresenterImp.getNewsList();
        }
        adapter = new RecyclerviewAdapter(getActivity());
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        re.setLayoutManager(manager);
        re.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, String pk) {
                Intent intent = new Intent(getActivity(), NewsArticleActivity.class);
                intent.putExtra("pk", pk);
                startActivity(intent);
            }
        });
        newsSwipe.setOnRefreshListener(this);
        re.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //向下滚动
                {
                    int visibleItemCount = manager.getChildCount();
                    int totalItemCount = manager.getItemCount();
                    int firstVisiblesItems = manager.findFirstVisibleItemPosition();
                    if (!loading && (firstVisiblesItems + visibleItemCount) >= totalItemCount) {
                        View foot = View.inflate(getActivity(), R.layout.item_foot, null);
                        adapter.setFootView(foot);
                        if (app_id == 1057) {
                            newsHotPresenterImp.getMoreNewsList();
                        } else {
                            newsListPresenterImp.getMoreList();
                        }
                        loading = true;
                    }
                }
            }
        });
    }

    @Override
    public void initNewsList(final List<News.DataBean.ArticlesBean> beanList) {//mvp实现newslistview中加载UI
        adapter.setBeanList(beanList);
        newsSwipe.setRefreshing(false);
    }

    @Override
    public void addNewsList(List<News.DataBean.ArticlesBean> beanList) {
        adapter.addBeanList(beanList);
        adapter.deleteFootView();
        loading = false;
    }

    @Override
    public void showNetChange(String s) {
        Snackbar.make(re, s, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        adapter.update();
        adapter.notifyDataSetChanged();
        if (newsHotPresenterImp == null && newsListPresenterImp != null)
            newsListPresenterImp.getNewsList();
        if (newsHotPresenterImp != null && newsListPresenterImp == null)
            newsHotPresenterImp.getNewsList();
    }
}
