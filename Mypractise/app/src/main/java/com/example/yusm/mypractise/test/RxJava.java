//package com.example.yusm.mypractise.test;
///*
// *
// * Created by iPanel@iPanel.cn
// * Date: 2019/5/30
// * Desc：
// */
//
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import java.io.File;
//import java.util.List;
//
//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Action1;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//
//public class RxJava {
//
//    public void showImagesFromFile(final ImageView imageView, List<File> folder){
//        Observable.from(folder)
//                .flatMap(new Func1<File, Observable<File>>() {
//                    @Override
//                    public Observable<File> call(File file) {
//                        return Observable.from(file.listFiles());
//                    }
//                })
//                .filter(new Func1<File, Boolean>() {
//                    @Override
//                    public Boolean call(File file) {
//                        return file.getName().endsWith(".png");
//                    }
//                })
//                .map(new Func1<File, Bitmap>() {
//                    @Override
//                    public Bitmap call(File file) {
//                        return BitmapFactory.decodeFile(file.getAbsolutePath());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        imageView.setImageBitmap(bitmap);
//                    }
//                });
//    }
//
//    private void init(){
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//        };
//
//        Subscriber<String> subscriber = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//        };
//
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber1) {
//                subscriber1.onNext("hello");
//                subscriber1.onNext("word");
//                subscriber1.onNext("yusm");
//                subscriber1.onCompleted();
//            }
//        });
//
//        Observable observable1 = Observable.just("hello","yusm","hi");
//        Observable observable2 = Observable.from(new String[]{"yusm","1","2"});
//
//        observable1.subscribe(subscriber);
//
//        Observable.just(1,2,3,4)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.i("number",integer+"");
//                    }
//                });
//
//        observable1.lift(new Observable.Operator<String,Integer>() {
//
//            @Override
//            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
//                return new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        subscriber.onCompleted();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        subscriber.onError(e);
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        subscriber.onNext(integer+"");
//                    }
//                };
//            }
//        });
//
//    }
//
//
//    public void onSingleClick(Button button){
//    }
//
//}
