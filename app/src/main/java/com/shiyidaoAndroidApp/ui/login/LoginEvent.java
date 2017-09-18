package com.shiyidaoAndroidApp.ui.login;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Adminidtrator on 2017/3/10.
 */

public class LoginEvent {
    private Context mContext;
    public LoginEvent(Context mContext){
        this.mContext=mContext;
    }
    public void onClickGoReginst(View view){
        Toast.makeText(mContext, "onClickGoReginst", Toast.LENGTH_LONG).show();
    }
}
