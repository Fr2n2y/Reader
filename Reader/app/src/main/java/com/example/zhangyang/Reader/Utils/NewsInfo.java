package com.example.zhangyang.Reader.Utils;

/**
 * Created by zhangyang on 16/8/23.
 */
public class NewsInfo {
    private static String _appid = "AndroidPhone";
    private static String _bsize = "_";
    private static String _version = "6.7";

    public static String get_bsize() {
        return _bsize;
    }

    public static String get_appid() {
        return _appid;
    }

    public static String get_version() {
        return _version;
    }

    public static void set_bsize(int width, int height) {
        NewsInfo._bsize = String.valueOf(width) + _bsize + String.valueOf(height);
    }
}
