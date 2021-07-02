package com.example.yusm.mypractise;

import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.smy.commonlibrary.ArouterConstants;
import com.example.yusm.mypractise.base.BaseActivity;
import com.example.yusm.mypractise.test.PolyToPolyActivity;
import com.example.yusm.mypractise.test.SearchViewActivity;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.OnClick;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

@Route(path = ArouterConstants.APP_CUSTOMACTIVITY)
public class CustomViewActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @OnClick({R.id.tv_search,R.id.tv_polytopoly})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_search:
                Intent searchIntent = new Intent(CustomViewActivity.this,SearchViewActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.tv_polytopoly:
                Intent polyIntent = new Intent(CustomViewActivity.this, PolyToPolyActivity.class);
                startActivity(polyIntent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Observable.create(new OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//
//            }
//        }).map(new Func1<Integer, String>() {
//            @Override
//            public String call(Integer integer) {
//                return null;
//            }
//        }).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
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
//        });
        test();

        testClassLoader();
    }

    private void testClassLoader() {

        ClassLoader classLoader = getClassLoader();
//        while (classLoader!=null){
//            Log.i(TAG," classLoader  "+classLoader.toString());
//            classLoader = classLoader.getParent();
//        }

        File file = new File(Environment.getExternalStorageDirectory(),"module1-debug.apk");
        if(!file.exists()){
            Log.i(TAG,"file is not exist:" +file.getAbsolutePath());
            return;
        }
        Log.i(TAG,"find it: " +file.getAbsolutePath());
        String optimizeDir = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"opt_dex";
        File fileOpt = new File(optimizeDir);
        if(!fileOpt.exists()){
            fileOpt.mkdirs();
        }
        PathClassLoader pathClassLoader = new PathClassLoader(file.getAbsolutePath(),classLoader);
        DexClassLoader dexClassLoader = new DexClassLoader(file.getAbsolutePath(),null,null,classLoader);
        classLoader = pathClassLoader;
        try {
            Class moduleActivity = classLoader.loadClass("com.example.smy.module1.TextClassLoader");
//            Class moduleActivity = classLoader.loadClass("com.example.yusm.mypractise.MainActivity1");
            if(moduleActivity!=null){
                Object object = moduleActivity.newInstance();
//                Method method = moduleActivity.getDeclaredMethod("test",null);
//                method.setAccessible(true);
//                method.invoke(object,null);
            }else {
                Log.i(TAG,"moduleActivity load fail");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
        }
    }

    private void test() {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));

//        View view ;
//        view.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        })

    }

}
