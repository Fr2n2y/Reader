package com.example.zhangyang.Reader.Presenter.PresenterImp;

import com.example.zhangyang.Reader.Bean.Update;
import com.example.zhangyang.Reader.Model.UpdateModel;
import com.example.zhangyang.Reader.Presenter.IUpdateCallBack;
import com.example.zhangyang.Reader.View.UpdateView;

/**
 * Created by zhangyang on 16/9/20.
 */
public class UpdatePresenterImp extends BasePresenterImp<UpdateView, Update> implements IUpdateCallBack {
    private UpdateView updateView;
    private UpdateModel model;

    public UpdatePresenterImp(UpdateView updateView) {
        this.updateView = updateView;
        model = new UpdateModel();
    }

    public void getUpdate() {
        model.loadUpdate(this);
    }


    public void unScribe(){
        model.unsubscribe();
    }

    @Override
    public void loadUpdate(Update update) {
        updateView.initUpdate(update);
    }
}
