package com.example.yusm.mypractise.bean

/*
 *
 * Date: 2021/5/10 
 * Desc：
 */

data class BaseResp<T>(
    var code: Int = 0,
    var msg: String = "",
    var `data`: T
)