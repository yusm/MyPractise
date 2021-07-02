package com.example.yusm.mypractise.dagger;

import dagger.Component;

/*
 *
 * Date: 2019/7/23
 * Desc：
 */
@Component(modules = WatchModule.class)
public interface ActivityComponent {
    void inject(DaggerTestActivity daggerTestActivity);
}
