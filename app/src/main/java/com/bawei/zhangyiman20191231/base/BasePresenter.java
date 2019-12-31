package com.bawei.zhangyiman20191231.base;

/*
 *@auther:张奕漫
 *@Date: 2019/12/31
 *@Time:8:56
 *@Description:
 * */
public abstract class BasePresenter<V> {
    protected V  view;

    public void attch(V view) {
        this.view = view;
    }
    public void detach() {
        view = null;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
