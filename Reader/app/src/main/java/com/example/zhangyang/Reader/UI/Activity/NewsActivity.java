package com.example.zhangyang.Reader.UI.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangyang.Reader.R;
import com.example.zhangyang.Reader.UI.Fragment.NewsFragment;
import com.example.zhangyang.Reader.Utils.Rescources;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyang on 16/8/30.
 */
public class NewsActivity extends AppCompatActivity {

    @BindView(R.id.topic_tv)
    TextView topicTv;
    @BindView(R.id.topic_iv)
    ImageView topicIv;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        init();
    }

    private void init() {
        drawer = (DrawerLayout) findViewById(R.id.news_drawer);
        toolbar = (Toolbar) findViewById(R.id.news_toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.news_navigation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        switchFragment(R.id.news_military);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switchFragment(item.getItemId());
                switch (item.getItemId()) {
                    case R.id.news_settings:
                        startActivity(new Intent(NewsActivity.this, SettingsActivity.class));
                        break;
                    default:
                        break;
                }
//                item.setChecked(true); // BUG设置应为不可点击 xml设置无效
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void switchFragment(int itemId) {
        topicIv.setImageDrawable(ContextCompat.getDrawable(this, Rescources.getDrawableId(itemId)));
        topicTv.setText(getString(Rescources.getTitleId(itemId)));
        NewsFragment newsFragment = new NewsFragment();
        Slide slideTransition;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Gravity.START部分机型崩溃java.lang.IllegalArgumentException: Invalid slide direction
            slideTransition = new Slide(Gravity.LEFT);
            slideTransition.setDuration(700);
            newsFragment.setEnterTransition(slideTransition);
            newsFragment.setExitTransition(slideTransition);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("app_id", Rescources.getApp_id(itemId));
        newsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.news_frame, newsFragment).commit();
    }
}
