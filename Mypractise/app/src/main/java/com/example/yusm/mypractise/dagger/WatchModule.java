package com.example.yusm.mypractise.dagger;

import dagger.Module;
import dagger.Provides;

/*
 *
 * Date: 2019/7/23
 * Desc：
 */
@Module
public class WatchModule {

    @Provides
    public Watch provideWatch(){
        return new Watch(){
            @Override
            public String work() {
                return super.work()+"  in provide";
            }
        };
    }
}
