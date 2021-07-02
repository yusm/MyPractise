package com.example.yusm.mypractise;
/*
 *
 * Date: 2019/7/2
 * Desc：
 */

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.yusm.mypractise.bean.Person;
import com.example.yusm.mypractise.databinding.ActivityAidlClientBinding;
import com.example.yusm.mypractise.service.MyAidlService;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class AidlClientActivity extends Activity{
//    @BindView(R.id.content) TextView content;

//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_aidl_client;
//    }

//    @Override
//    protected void initUI() {
//        super.initUI();
//        bindService();
//    }

    ActivityAidlClientBinding activityAidlClientBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAidlClientBinding = DataBindingUtil.setContentView(this, R.layout.activity_aidl_client);
//        activityAidlClientBinding.setAdd("add person");
        activityAidlClientBinding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AidlClientActivity.this.onClick();
            }
        });
        activityAidlClientBinding.setImageUrl("https://pics3.baidu.com/feed/00e93901213fb80ebde814aa7d51022bb9389413.jpeg?token=4525ae7b1f10c52288c04f072a364439&s=33E5D6A6A65337D2601F083D0300301A");
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent(getApplicationContext(), MyAidlService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }

    private IMyAidl iMyAidl;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidl = null;
        }
    };

//    @OnClick({R.id.add})
    public void onClick(){
        Random random = new Random();
        Person person = new Person("yusm "+random.nextInt(10));

        try {
            iMyAidl.addPerson(person);
            List<Person> mPersonList = iMyAidl.getPersonList();
            activityAidlClientBinding.setContent(mPersonList.toString());

//            content.setText(mPersonList.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    protected static int sum(int a, int b){
        return a+b;
    }
}
