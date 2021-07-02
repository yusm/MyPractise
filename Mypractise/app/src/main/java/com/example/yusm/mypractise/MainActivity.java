package com.example.yusm.mypractise;

import android.Manifest;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.smy.commonlibrary.ArouterConstants;
import com.example.yusm.mypractise.base.BaseActivity;
import com.example.yusm.mypractise.dagger.DaggerTestActivity;
import com.example.yusm.mypractise.greendao.User;
import com.example.yusm.mypractise.hook.NonRegisterActivcity;
import com.example.yusm.mypractise.leaktest.LeakActivity;
import com.example.yusm.mypractise.service.TestThreadService;
import com.example.yusm.mypractise.synchron.ThreadMain;
import com.example.yusm.mypractise.test.ArouterTestActivity;
import com.example.yusm.mypractise.test.ViewTouchActivity;
import com.example.yusm.mypractise.utils.IntentUtils;
import com.example.yusm.mypractise.widget.NetWorkActivity;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.OnClick;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Route(path = ArouterConstants.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        super.initUI();

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int totalMemory = (int) Runtime.getRuntime().totalMemory();
        Log.i(TAG,"totalMemory: "+totalMemory+"  maxMemory:"+maxMemory);

        printFileDir();

        showDevInfo();

        test();

        Thread.getAllStackTraces();

    }

    private void test() {

        String str1 = "hello";
        final User user = new User(1L,"yusm",30);

        changeStr(str1);
        System.out.println("str1: "+str1);

        changeUser(user);
        System.out.println("user:"+user.toString());
    }

    private void changeStr(String content){
        content = "nnnn";
    }

    private void changeUser(final User user){
        user.setUsername("hahahhaha");
        user.setGrade(20);
    }

    private void printFileDir() {
        Log.i(TAG," in printFileDir ");
        File file = getExternalFilesDir("Caches");
        Log.i(TAG," getExternalFilesDir: "+file.getAbsolutePath());

        file = Environment.getExternalStorageDirectory();
        Log.i(TAG," Environment.getExternalStorageDirectory: "+file.getAbsolutePath());

        file = getCacheDir();
        Log.i(TAG," getCacheDir: "+file.getAbsolutePath());

        file = getFilesDir();
        Log.i(TAG," getFilesDir: "+file.getAbsolutePath());
    }

    private void showDevInfo(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        //获取屏幕像素
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        Log.d(TAG,"the screen size is "+point.toString());

        //屏幕密度，每英寸容纳的点数
        Log.d(TAG,"Density is "+displayMetrics.density+" densityDpi is "+displayMetrics.densityDpi+" height: "+displayMetrics.heightPixels+
                " width: "+displayMetrics.widthPixels);

    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG,"onServiceConnected, "+Thread.currentThread());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"onServiceDisconnected, "+Thread.currentThread());
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.tv_customview,R.id.tv_contacts,R.id.tv_animation,R.id.tv_aidl,R.id.tv_hook,
    R.id.tv_test,R.id.tv_dagger,R.id.tv_viewtouch,R.id.tv_arouter})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_customview:
//                test();
                IntentUtils.startActivity(MainActivity.this,CustomViewActivity.class);
//                IntentUtils.startActivity(MainActivity.this, NetWorkActivity.class);
//                IntentUtils.startActivity(MainActivity.this, LeakActivity.class);
                break;
            case R.id.tv_contacts:
                openPhoneDirectory();
                break;
            case R.id.tv_animation:
                IntentUtils.startActivity(MainActivity.this,AnimationActivity.class);
//                ExecutorService mExecutor = Executors.newFixedThreadPool(5);
                break;
            case R.id.tv_aidl:
                IntentUtils.startActivity(MainActivity.this,AidlClientActivity.class);
                break;
            case R.id.tv_hook:
                IntentUtils.startActivity(MainActivity.this, NonRegisterActivcity.class);
                break;
            case R.id.tv_test:
                ThreadMain.main(new String[]{});
                Intent intentService = new Intent(MainActivity.this, TestThreadService.class);
                bindService(intentService,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.tv_dagger:
                IntentUtils.startActivity(MainActivity.this, DaggerTestActivity.class);
                break;
            case R.id.tv_viewtouch:
                IntentUtils.startActivity(MainActivity.this, ViewTouchActivity.class);
                break;
            case R.id.tv_arouter:
                IntentUtils.startActivity(MainActivity.this, ArouterTestActivity.class);
                break;
        }
    }

    //打开通讯录
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openPhoneDirectory(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED) {
//            Intent contacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//            startActivityForResult(contacts,1);
            Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            startActivityForResult(intent,1);
        } else {
            Toast.makeText(this,"没有访问通讯录权限",Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==2){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent contacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(contacts,1);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intentService = new Intent(MainActivity.this, TestThreadService.class);
        bindService(intentService,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
//        Intent intentService = new Intent(MainActivity1.this, TestThreadService.class);
//        stopService(intentService);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    // ContentProvider展示数据类似一个单个数据库表
                    // ContentResolver实例带的方法可实现找到指定的ContentProvider并获取到ContentProvider的数据
                    ContentResolver reContentResolverol = getContentResolver();
                    // URI,每个ContentProvider定义一个唯一的公开的URI,用于指定到它的数据集
                    Uri contactData = data.getData();
                    Cursor cursor=null;
                    Cursor phone=null;
                    try{
                        CursorLoader cursorLoader = new CursorLoader(this, contactData,
                                null, null, null, null);
                        // 查询就是输入URI等参数,其中URI是必须的,其他是可选的,如果系统能找到URI对应的ContentProvider将返回一个Cursor对象.
                        cursor = cursorLoader.loadInBackground();
                        cursor.moveToFirst();
                        // 获得DATA表中的名字
                        String username = cursor
                                .getString(cursor
                                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // 条件为联系人ID
                        String contactId = cursor.getString(cursor
                                .getColumnIndex(ContactsContract.Contacts._ID));
                        // 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
                        phone = reContentResolverol.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                        + " = " + contactId, null, null);
                        String usernumber="";
                        while (phone.moveToNext()) {
                            usernumber = phone
                                    .getString(phone
                                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        if(cursor!=null)
                            cursor.close();
                        if(phone!=null)
                            phone.close();
                    }
                    break;
            }
        }
    }
}
