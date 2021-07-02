package com.example.yusm.mypractise.service;
/*
 *
 * Date: 2019/7/2
 * Desc：
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.example.yusm.mypractise.IMyAidl;
import com.example.yusm.mypractise.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class MyAidlService extends Service{

    private ArrayList<Person> mPersons;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        return mIBinder;
    }

    private IBinder mIBinder = new IMyAidl.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }
    };
}
