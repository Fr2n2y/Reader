package com.example.zhangyang.Reader.Utils;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by zhangyang on 16/9/9.
 */
public class RxBus {
    private static volatile RxBus defaultInstance;
    private final Subject<Object, Object> rxbus;

    public RxBus() {
        rxbus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    public void post(Object O) {
        rxbus.onNext(O);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return rxbus.ofType(eventType);
    }

}
