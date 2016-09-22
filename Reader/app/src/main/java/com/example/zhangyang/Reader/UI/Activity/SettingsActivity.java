package com.example.zhangyang.Reader.UI.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.example.zhangyang.Reader.R;
import com.example.zhangyang.Reader.UI.Fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyang on 16/9/12.
 */
public class SettingsActivity extends BaseActivity {

    @BindView(R.id.settings_toolbar)
    Toolbar settingsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        settingsToolbar.setTitle("设置");
        setSupportActionBar(settingsToolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        settingsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getFragmentManager().beginTransaction().replace(R.id.frame_settings, new SettingFragment(), null).commit();
    }
}
