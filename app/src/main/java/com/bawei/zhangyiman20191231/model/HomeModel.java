package com.bawei.zhangyiman20191231.model;

import com.bawei.zhangyiman20191231.contract.IHomeContract;
import com.bawei.zhangyiman20191231.model.bean.Bean;
import com.bawei.zhangyiman20191231.util.NetUtil;
import com.google.gson.Gson;

/*
 *@auther:张奕漫
 *@Date: 2019/12/31
 *@Time:8:56
 *@Description:
 * */
public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(MyModelCallBack myModelCallBack) {
        String Http = "http://blog.zhaoliang5156.cn/api/news/ranking.json";
        NetUtil.getInstance().getUrlGet(Http, new NetUtil.MyCallBack() {
            @Override
            public void getJson(String json) {
                Bean bean = new Gson().fromJson(json, Bean.class);
                myModelCallBack.onSuccess(bean);
            }

            @Override
            public void onError(Throwable throwable) {
                myModelCallBack.onError(throwable);
            }
        });
    }
}
