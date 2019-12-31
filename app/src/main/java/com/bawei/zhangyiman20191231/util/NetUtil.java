package com.bawei.zhangyiman20191231.util;

import android.os.Handler;
import android.widget.ImageView;

import com.bawei.zhangyiman20191231.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 *@auther:张奕漫
 *@Date: 2019/12/31
 *@Time:8:56
 *@Description:
 * */
public class NetUtil {
    private static NetUtil netUtil;
    private final OkHttpClient okHttpClient;
    private final Handler handler;

    private NetUtil() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static NetUtil getInstance() {
        if (netUtil==null){
            synchronized (NetUtil.class){
                if (netUtil==null){
                    netUtil = new NetUtil();
                }
            }
        }
        return netUtil;
    }

    public void getUrlGet(String HttpUrl,MyCallBack myCallBack){
        Request builder = new Request.Builder()
                .get()
                .url(HttpUrl)
                .build();

        Call call = okHttpClient.newCall(builder);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!(response ==null) &&response.isSuccessful()){
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getJson(string);
                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Exception("请求失败"));
                        }
                    });
                }
            }
        });
    }

    public void getPhoto(String PhotoUrl, ImageView imageView){
        Glide.with(imageView)
                .load(PhotoUrl)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }
    public interface MyCallBack{
        void getJson(String json);
        void onError(Throwable throwable);
    }
}
