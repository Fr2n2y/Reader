package com.example.zhangyang.Reader.Bean;

/**
 * Created by zhangyang on 16/9/20.
 */
public class Update {

    /**
     * versionCode : 1
     * versionName : 1.0.0
     * downloadURL : www.download.url
     */

    private int versionCode;
    private String versionName;
    private String downloadURL;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
