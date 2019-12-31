package com.bawei.zhangyiman20191231.contract;

import com.bawei.zhangyiman20191231.model.bean.Bean;

import org.greenrobot.eventbus.ThreadMode;

/*
 *@auther:张奕漫
 *@Date: 2019/12/31
 *@Time:8:56
 *@Description:
 * */
public interface IHomeContract {

    interface IView{
        void onSuccess(Bean bean);
        void onError(Throwable throwable);
    }

    interface IPresenter{
        void getHomeData();
    }

    interface IModel{
        void getHomeData(MyModelCallBack myModelCallBack);
        interface MyModelCallBack{
            void onSuccess(Bean bean);
            void onError(Throwable throwable);
        }
    }

}
