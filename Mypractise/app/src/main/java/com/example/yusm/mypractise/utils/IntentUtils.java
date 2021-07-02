package com.example.yusm.mypractise.utils;
/*
 *
 * Created by iPanel@iPanel.cn
 * Date: 2019/5/22
 * Desc：
 */

import android.content.Context;
import android.content.Intent;

public class IntentUtils {

    public static void startActivity(Context context,Class<?> cls){
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
