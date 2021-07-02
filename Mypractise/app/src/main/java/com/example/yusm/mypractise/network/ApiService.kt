package com.example.yusm.mypractise.network

import com.example.yusm.mypractise.bean.BannerData
import com.example.yusm.mypractise.bean.BaseResp
import com.example.yusm.mypractise.bean.Fiction
import retrofit2.Call
import retrofit2.http.GET

/*
 *
 * Date: 2021/5/10 
 * Desc：
 */

interface ApiService {

//    @GET("https://www.apiopen.top/novelApi")
//    suspend fun getFictions(): BaseResp<List<Fiction>>


    @GET("banner/json")
    suspend fun getBanner():BaseResp<List<BannerData>>
}