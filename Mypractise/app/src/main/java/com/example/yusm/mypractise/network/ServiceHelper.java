package com.example.yusm.mypractise.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServiceHelper {

    private static ServiceHelper sHelper = new ServiceHelper();
    private OkHttpClient okHttpClient;
    private Handler uiHandler;

    public static ServiceHelper getHelper() {
        return sHelper;
    }

    public interface ResponseHandlerT<T> {
        void onResponse(boolean success, T t);
    }

    public interface StringResponseListener {
        void onResponse(boolean success, String result);
    }

    private ServiceHelper() {
        uiHandler = new Handler(Looper.getMainLooper());
        initHttpClient();
    }

    private void initHttpClient() {

        okHttpClient = new OkHttpClient.Builder()
                .build();
    }

    public String syncGet(final String url, final RequestParams params) {
        Request.Builder builder = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null && params.getUrlParams() != null && params.getUrlParams().size() > 0) {
            for (ConcurrentHashMap.Entry<String, String> entry : params.getUrlParams().entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        builder.url(urlBuilder.build());
        Request request = builder.build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            Log.i("ServiceHelper",Thread.currentThread().getName());
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void get(final String url, final RequestParams params, final StringResponseListener listener) {
        Request.Builder builder = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null && params.getUrlParams() != null && params.getUrlParams().size() > 0) {
            for (ConcurrentHashMap.Entry<String, String> entry : params.getUrlParams().entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        builder.url(urlBuilder.build());
        Request request = builder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener == null) {
                    return;
                }
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResponse(false, null);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (listener == null) {
                    return;
                }
                final String result = response.body().string();
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResponse(true, result);
                    }
                });
            }
        });
    }

    public <T> void get(final String url, final RequestParams params, final Class<T> clazz,
                        final ResponseHandlerT<T> responseHandlerT) {
        Request.Builder builder = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null && params.getUrlParams() != null && params.getUrlParams().size() > 0) {
            for (ConcurrentHashMap.Entry<String, String> entry : params.getUrlParams().entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        builder.url(urlBuilder.build());
        Request request = builder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (responseHandlerT == null) {
                    return;
                }
//                uiHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        responseHandlerT.onResponse(false, null);
//                    }
//                });
                postResult(false,null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (responseHandlerT == null) {
                    return;
                }
                final T result;
                try {
                    result = new Gson().fromJson(response.body().string(), clazz);
                    if (result != null) {
//                        uiHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                responseHandlerT.onResponse(true, result);
//                            }
//                        });
                        postResult(true, result);
                    } else {
//                        uiHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                responseHandlerT.onResponse(false, null);
//                            }
//                        });
                        postResult(false,null);
                    }
                }catch (Exception e){
                }
            }

            private void postResult(final boolean success,final T t){
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (responseHandlerT == null) {
                            return;
                        }
                        responseHandlerT.onResponse(success, t);
                    }
                });
            }
        });
    }

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=UTF-8");

    public <T> void post(final String url, final String params, final Class<T> clazz,
                         final ResponseHandlerT<T> responseHandlerT) {
        Request.Builder builder = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        builder.url(urlBuilder.build());
        builder.addHeader("Content-Type", "application/json;charset=UTF-8");
        builder.addHeader("Accept", "application/json");
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
        builder.post(body);
        Request request = builder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (responseHandlerT == null) {
                    return;
                }
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        responseHandlerT.onResponse(false, null);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (responseHandlerT == null) {
                    return;
                }
                final T result;
                result = new Gson().fromJson(response.body().string(), clazz);
                if (result != null) {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseHandlerT.onResponse(true, result);
                        }
                    });

                } else {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseHandlerT.onResponse(false, null);
                        }
                    });
                }
            }
        });
    }

}
