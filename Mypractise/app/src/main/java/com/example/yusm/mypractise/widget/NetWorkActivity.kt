package com.example.yusm.mypractise.widget

import android.util.Log
import androidx.annotation.StringRes
import com.example.yusm.mypractise.R
import com.example.yusm.mypractise.base.BaseActivity
import com.example.yusm.mypractise.bean.dataConvert
import com.example.yusm.mypractise.network.ApiService
import com.example.yusm.mypractise.network.ServiceHelper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.activity_viewtouch.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

/*
 *
 * Date: 2021/5/10 
 * Desc：
 */

class NetWorkActivity: BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_viewtouch

    override fun initUI() {
        super.initUI()
        touch_c.setOnClickListener {
//            test2()
            testData()
        }
    }

    fun testData(){
//        GlobalScope.launch {
//            try{
//                withContext(Dispatchers.Main){
//                    val response = Retrofit.Builder().baseUrl("https://www.wanandroid.com/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).build().create(ApiService::class.java).getBanner()
//                    Log.i("response",response.toString())
//                    Log.i("response",(response.data.size).toString())
//                }
//            }catch (e: Exception){
//                e.printStackTrace()
//            }
//        }

        MainScope().launch {
            try{
                    val response = Retrofit.Builder().baseUrl("https://www.wanandroid.com/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java).getBanner()
                    Log.i("response",response.toString())
                    Log.i("response",(response.data.size).toString())
                    Log.i("response",Thread.currentThread().name)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


    fun test2(){
        val url = "https://www.wanandroid.com/banner/json"
        ServiceHelper.getHelper().get(url,null
        ) { success, result ->
            Log.i("response:",result)
            Log.i("aa",Thread.currentThread().name)
        }
//        val data = ServiceHelper.getHelper().syncGet(url,null)
//        Log.i("aa",Thread.currentThread().name)
//        Log.i("aa",(data==null).toString())
    }
}