package com.example.zhangyang.Reader.Utils;

import com.example.zhangyang.Reader.R;

/**
 * Created by zhangyang on 16/9/14.
 */
public class Rescources {

    public static int getApp_id(int itemId) {
        switch (itemId) {
            case R.id.news_military:
                return Constants.NEWS_MILITARY;
            case R.id.news_cars:
                return Constants.NEWS_CAR;
            case R.id.news_sports:
                return Constants.NEWS_SPORTS;
            case R.id.news_technology:
                return Constants.NEWS_TECHNOLOGY;
            case R.id.news_hot:
                return Constants.NEWS_HOT;
        }
        return Constants.NEWS_MILITARY;//默认返回军事新闻
    }

    public static int getDrawableId(int itemId) {
        switch (itemId) {
            case R.id.news_military:
                return R.drawable.icon_military;
            case R.id.news_cars:
                return R.drawable.icon_cars;
            case R.id.news_sports:
                return R.drawable.icon_sports;
            case R.id.news_technology:
                return R.drawable.icon_technology;
            case R.id.news_hot:
                return R.drawable.icon_hot;
        }
        return R.drawable.icon_military;
    }

    public static int getTitleId(int itemId){
        switch (itemId){
            case R.id.news_military:
                return R.string.military;
            case R.id.news_cars:
                return R.string.cars;
            case R.id.news_sports:
                return R.string.sports;
            case R.id.news_technology:
                return R.string.technology;
            case R.id.news_hot:
                return R.string.hot;
        }
        return R.string.military;
    }

}
