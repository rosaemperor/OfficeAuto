package com.shiyidaoAndroidApp.http;


import com.shiyidaoAndroidApp.config.AppCongif;
import com.shiyidaoAndroidApp.config.ServerPath;

/**
 * Created by Adminidtrator on 2016/11/10.
 */

public class NETAPI {
    /**
     * 登录
     *
     *
     */
    public static void Login(String loginName, String password, OkHttpHelper.DefaultCallBack callBack, String tag) {
        OkHttpClientManager.Param[] params =
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("loginName", loginName),
                        new OkHttpClientManager.Param("password", password),
                };
        OkHttpHelper.post(AppCongif.SERVER_IP + ServerPath.READER_LOGIN, params,
                callBack, tag);
    }

}
