package com.shiyidaoAndroidApp.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shiyidaoAndroidApp.BR;


/**
 * Created by Adminidtrator on 2017/3/8.
 */

public class stu extends BaseObservable{
    private String age;
    private String name;
    public stu(String age,String name){
        this.name=name;
        this.age=age;
    }
    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
       notifyPropertyChanged(BR.age);
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
