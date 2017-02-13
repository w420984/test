package com.wwt.test.modle;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wwt.test.BR;

public class User extends BaseObservable{
    public String mName;
    public int mAge;

    public User(String name, int age){
        mName = name;
        mAge = age;
    }

    @Bindable
    public String getName(){
        return mName;
    }

    @Bindable
    public int getAge(){
        return mAge;
    }

    public void setmName(String name){
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    public void setAge(int age){
        notifyPropertyChanged(BR.age);
    }
}
