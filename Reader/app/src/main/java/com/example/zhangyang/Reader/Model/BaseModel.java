package com.example.zhangyang.Reader.Model;

import com.example.zhangyang.Reader.Utils.RetrofitManager;

import retrofit2.Retrofit;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangyang on 16/8/25.
 */
public class BaseModel {
    public Retrofit retrofit;
    private CompositeSubscription mCompositeSubscription;
    protected void addSubscription(Subscription subscription){
        if(mCompositeSubscription==null){
            this.mCompositeSubscription=new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }
    public void unsubscribe(){
        if(mCompositeSubscription!=null){
            this.mCompositeSubscription.unsubscribe();
        }
    }

    public BaseModel(String baseurl) {
        retrofit=RetrofitManager.builder(baseurl);
    }
}
