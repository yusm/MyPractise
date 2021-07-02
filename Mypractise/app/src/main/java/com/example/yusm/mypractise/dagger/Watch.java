package com.example.yusm.mypractise.dagger;
/*
 *
 * Date: 2019/7/23
 * Desc：
 */

import javax.inject.Inject;

public class Watch {

    @Inject
    public Watch(){

    }

    public String work(){
        return "手表在工作";
    }
}
