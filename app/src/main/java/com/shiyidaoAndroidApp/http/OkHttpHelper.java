package com.shiyidaoAndroidApp.http;

import android.content.Context;
import android.util.Log;


import com.shiyidaoAndroidApp.uitle.developHelp.LogUitl;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;



/**
 * okhttp网络访问头
 * 所有网络访问都应该通过这里
 * Created by alan on 16/9/14.
 *
 */
public final class OkHttpHelper {
    public static String fileurl="/OfficeAotomation/FileBox";

    private  static Context context;
    /**
     * 网络管理者
     */
    private static OkHttpClientManager okHttpClientManager;

    /**
     * 初始化方法
     *
     * @param a
     */
    public synchronized static void init(Context a) {
        context = a;

        if (okHttpClientManager == null) {
            okHttpClientManager = OkHttpClientManager.getInstance();
        }
    }

    /**
     * 获取当前的网络管理者
     *
     * @return
     */
    public OkHttpClientManager getOkhttpManager() {
        return okHttpClientManager;
    }


    /**
     * get 方法(自定义回调)
     *
     * @param url
     * @param parmars
     * @param callBack
     * @param tag
     */
    public static void get(String url, Map<String, Object> parmars, OkHttpClientManager.ResultCallback callBack, String tag) {
        okHttpClientManager.getGetDelegate().getAsyn(url, callBack, tag);
    }

    /**
     *
     *
     * @param url
     * @param callBack
     * @param tag
     */
    public static void get2(String url, final DefaultCallBack callBack, final String tag) {
        okHttpClientManager.getGetDelegate().getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callBack.start();
            }

            @Override
            public void onError(Request request, Exception e) {
                LogUitl.E(getClass().getName(), "curr" + tag + "net onErro" + e.toString());
                if(!e.toString().contains("IOException"))
                callBack.onErro("网络无法连接", e);

            }

            @Override
            public void onResponse(String response) {
                LogUitl.E(getClass().getName(), "Request :" + response);
                callBack.onSuccess(response);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                callBack.complite();
            }

        }, tag);
    }
    public static void get(String url, final DefaultCallBack callBack, final String tag) {
        okHttpClientManager.getGetDelegate().getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callBack.start();
            }

            @Override
            public void onError(Request request, Exception e) {
                LogUitl.E(getClass().getName(), "curr" + tag + "net onErro" + e.toString());
               // callBack.onErro(request.toString(), e);
                if(!e.toString().contains("IOException"))
                callBack.onErro("网络无法连接",e);

            }

            @Override
            public void onResponse(String response) {
                LogUitl.E(getClass().getName(), "Request :" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("code")==1) {
                        callBack.onSuccess(jsonObject.getString("result"));
                    } else {
                        callBack.onErro(jsonObject.getString("msg"), null);
                    }
                } catch (JSONException e) {
                    callBack.onErro("Json Erro", e);//
                }
            }

            @Override
            public void onAfter() {
                super.onAfter();
                callBack.complite();
            }

        }, tag);
    }

    /**
     * 带参数的Post方法
     *
     * @param url
     * @param params
     * @param callback
     * @param tag
     */
    public static void post(String url, OkHttpClientManager.Param[] params, OkHttpClientManager.ResultCallback callback, String tag) {
        okHttpClientManager.getPostDelegate().postAsyn(url, params, callback, tag);
    }

    /**
     * 默认回调Post方法
     *
     * @param url
     * @param params
     * @param callBack
     * @param tag
     */
    public static void post(String url, OkHttpClientManager.Param[] params, final DefaultCallBack callBack, String tag) {
        okHttpClientManager.getPostDelegate().postAsyn(url, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callBack.start();
            }

            @Override
            public void onError(Request request, Exception e) {
                LogUitl.E(getClass().getName(), request + "Exception:" + e.toString());
                if(!e.toString().contains("IOException"))
                    callBack.onErro("网络无法连接",e);
            }

            @Override
            public void onResponse(String response) {
                LogUitl.E(getClass().getName(),"AA"+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getInt("code")==1){
                        if(!jsonObject.has("result")||jsonObject.getString("result")==null||jsonObject.getString("result")==""){

                            callBack.onSuccess(jsonObject.getString("msg"));
                        }else {
                            callBack.onSuccess(jsonObject.getString("result"));

                        }
                    }else {
                        callBack.onErro(jsonObject.getString("msg"), null);
                    }
                } catch (JSONException e) {
                    Log.d("AAA","3"+e.toString());
                    e.printStackTrace();
                }
            }

            @Override
                public void onAfter() {
                super.onAfter();
                callBack.complite();
            }
        }, tag);
    }
    /**
     * msg回调post方法
     */
    public static void post(String url, OkHttpClientManager.Param[] params, final MsgCallBack callBack, String tag) {
        okHttpClientManager.getPostDelegate().postAsyn(url, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callBack.start();
            }

            @Override
            public void onError(Request request, Exception e) {
                LogUitl.E(getClass().getName(), request + "Exception:" + e.toString());
                if(!e.toString().contains("IOException"))
                callBack.onErro("网络无法连接", e);
            }

            @Override
            public void onResponse(String response) {
                LogUitl.E(getClass().getName(), "response :" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("code")==1){
                        if(jsonObject.getString("result")==null||jsonObject.getString("result")==""){
                            callBack.onErro("No data request", null);
                        }else {
                            callBack.onSuccess(jsonObject.getString("result"));
                        }
                    }else {
                        callBack.onErro(jsonObject.getString("msg"), null);
                    }
                } catch (JSONException e) {
                    callBack.onErro("Json Erro", e);
                }
            }
            @Override
            public void onAfter() {
                super.onAfter();
                callBack.complite();
            }
        }, tag);
    }
    /**
     * fileUrl是app本地存储的文件夹
     * 文件下载
     */
    public static void downFile(String url, String fileurl, final DefaultCallBack callback, Object tag){

        OkHttpClientManager.getDownloadDelegate().downloadAsyn(url,fileurl, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
              callback.onErro(request.toString(),e);
            }

            @Override
            public void onResponse(String response) {
            callback.onSuccess(response);
            }
        }, tag);

    }
    /**
 * 单张图片上传
 */
    public static void post(String url, String fileKey, File file, final DefaultCallBack callback, Object tag){
        try {
            okHttpClientManager.getUploadDelegate().postAsyn(url, fileKey, file, new OkHttpClientManager.ResultCallback<String>() {
                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                    callback.start();
                }

                @Override
                public void onError(Request request, Exception e) {
                    callback.onErro(request.toString(),e);
                }

                @Override
                public void onResponse(String response) {
                    LogUitl.E(getClass().getName(), "response :" + response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("code")==1) {
                            callback.onSuccess(jsonObject.getString("result"));
                        } else {
                            callback.onErro(jsonObject.getString("msg"), null);
                        }
                    } catch (JSONException e) {
                        callback.onErro("Json Erro", e);
                    }
                }

                @Override
                public void onAfter() {
                    super.onAfter();
                    callback.complite();
                }
            }, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 取消网络进程
     *
     * @param tag
     */
    public static synchronized void cancleTag(String tag) {
        okHttpClientManager.client().cancel(tag);


    }


    /**
     * 默认回调
     */
    public interface DefaultCallBack {
        /**
         * 开始
         */
        void start();

        /**
         * 成功
         *
         * @param result
         */
        void onSuccess(String result);

        /**
         * 异常/链接失败
         *
         * @param request 异常信息
         * @param e       本地异常信息
         */
        void onErro(String request, Exception e);

        /**
         * 完成
         */
        void complite();
    }

    /**
     * 得到msg信息的回调
     */
    public interface MsgCallBack {
        /**
         * 开始
         */
        void start();

        /**
         * 成功
         *
         * @param result
         */
        void onSuccess(String result);

        /**
         * 异常/链接失败
         *
         * @param request 异常信息
         * @param e       本地异常信息
         */
        void onErro(String request, Exception e);

        /**
         * 完成
         */
        void complite();
    }



}
