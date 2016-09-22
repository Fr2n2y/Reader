package com.example.zhangyang.Reader.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.zhangyang.Reader.Interface.NetSate;
import com.example.zhangyang.Reader.Utils.InternetUtils;

/**
 * Created by zhangyang on 16/9/4.
 */
public class NetworkChange extends BroadcastReceiver {
    private NetSate netSate;

    public NetworkChange(NetSate netSate) {
        this.netSate = netSate;
    }

    @Override

    public void onReceive(Context context, Intent intent) {
        if(!InternetUtils.isNetConnect(context)){
            netSate.showNetChange("请确保网络连接");
        }
    }
}
