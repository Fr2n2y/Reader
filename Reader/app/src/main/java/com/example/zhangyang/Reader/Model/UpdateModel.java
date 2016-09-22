package com.example.zhangyang.Reader.Model;

import com.example.zhangyang.Reader.Bean.Update;
import com.example.zhangyang.Reader.Interface.ZakerNews;
import com.example.zhangyang.Reader.Presenter.IUpdateCallBack;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangyang on 16/9/20.
 */
public class UpdateModel extends BaseModel{
    public UpdateModel() {
        super("https://fr2n2y.github.io/");
    }
    public void loadUpdate(final IUpdateCallBack callback){
        ZakerNews zakerNews=retrofit.create(ZakerNews.class);
        Subscription subscription = zakerNews.getUpdate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Update>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Update update) {
                        callback.loadUpdate(update);
                    }
                });
        addSubscription(subscription);
    }
}
