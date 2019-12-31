package com.bawei.zhangyiman20191231.presenter;

import com.bawei.zhangyiman20191231.base.BasePresenter;
import com.bawei.zhangyiman20191231.contract.IHomeContract;
import com.bawei.zhangyiman20191231.model.HomeModel;
import com.bawei.zhangyiman20191231.model.bean.Bean;

/*
 *@auther:张奕漫
 *@Date: 2019/12/31
 *@Time:8:56
 *@Description:
 * */
public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {


    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        homeModel.getHomeData(new IHomeContract.IModel.MyModelCallBack() {
            @Override
            public void onSuccess(Bean bean) {
                view.onSuccess(bean);
            }

            @Override
            public void onError(Throwable throwable) {
                view.onError(throwable);
            }
        });
    }
}
