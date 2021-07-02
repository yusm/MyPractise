package com.example.yusm.mypractise.leaktest

import com.example.yusm.mypractise.R
import com.example.yusm.mypractise.base.BaseActivity

/*
 *
 * Date: 2021/6/22 
 * Desc：
 */

class LeakActivity: BaseActivity() {

    override fun getLayoutId(): Int  = R.layout.activity_custom_view

    override fun initUI() {
        super.initUI()
        LeakThread().start()
    }

    internal class LeakThread:Thread(){
        override fun run() {
            try {
                Thread.sleep(60*60*1000)
            }catch (e : InterruptedException){
                e.printStackTrace()
            }

        }
    }
}