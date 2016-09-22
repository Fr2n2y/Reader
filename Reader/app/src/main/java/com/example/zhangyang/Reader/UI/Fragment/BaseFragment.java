package com.example.zhangyang.Reader.UI.Fragment;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangyang.Reader.Broadcast.NetworkChange;
import com.example.zhangyang.Reader.Interface.NetSate;

/**
 * Created by zhangyang on 16/9/4.
 */
public class BaseFragment extends Fragment implements NetSate{
    private NetworkChange networkChange;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBroadcast();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initBroadcast() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        networkChange = new NetworkChange(this);
        getActivity().registerReceiver(networkChange,filter);
    }

    @Override
    public void showNetChange(String s) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(networkChange);
    }
}
