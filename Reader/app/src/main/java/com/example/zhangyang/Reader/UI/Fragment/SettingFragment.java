package com.example.zhangyang.Reader.UI.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.example.zhangyang.Reader.Bean.Update;
import com.example.zhangyang.Reader.Presenter.PresenterImp.UpdatePresenterImp;
import com.example.zhangyang.Reader.R;
import com.example.zhangyang.Reader.Utils.CacheUtil;
import com.example.zhangyang.Reader.Utils.Constants;
import com.example.zhangyang.Reader.View.UpdateView;

/**
 * Created by zhangyang on 16/9/16.
 */
public class SettingFragment extends PreferenceFragment implements UpdateView {
    private UpdatePresenterImp updatePresenterImp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        updatePresenterImp = new UpdatePresenterImp(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        updatePresenterImp.unScribe();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void init() {
        findPreference("declare").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                new AlertDialog.Builder(getActivity()).setTitle("免责声明")
                        .setMessage("本软件已开源上传至github,请点击版本查看。软件内容源由非法抓包所得," +
                                "仅供交流学习使用,若有侵权请联系作者,下架。请支持正版Zaker新闻," +
                                "感谢您的使用!")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return true;
            }
        });
        findPreference("version").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Fr2n2y/")));
                return true;
            }
        });
        findPreference("author").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://fr2n2y.github.io/")));
                return true;
            }
        });
        Preference preference = findPreference("cache");
        preference.setSummary("缓存大小:" + CacheUtil.getCacheSize(SettingFragment.this.getActivity().getCacheDir()));
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                CacheUtil.deleteDir(SettingFragment.this.getActivity().getCacheDir());
                preference.setSummary("缓存大小:" + CacheUtil.getCacheSize(SettingFragment.this.getActivity().getCacheDir()));
                return true;
            }
        });
        Preference check_update = findPreference("check_update");
        check_update.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                updatePresenterImp.getUpdate();
                return true;
            }
        });
    }

    @Override
    public void initUpdate(final Update update) {
        if (update.getVersionCode() >= Constants.versionCode) {
            new AlertDialog.Builder(getActivity()).setTitle("有更新可用")
                    .setMessage("当前版本v" + Constants.versionName + ",您有一个新的版本更新!"
                            + "v" + update.getVersionName())
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(update.getDownloadURL())));
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
    }
}
