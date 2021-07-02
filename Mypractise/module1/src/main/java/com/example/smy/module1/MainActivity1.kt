package com.example.smy.module1

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.smy.commonlibrary.ArouterConstants

@Route(path = ArouterConstants.MODULE1_ACTIVITY2)
class MainActivity1 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
    }
}
