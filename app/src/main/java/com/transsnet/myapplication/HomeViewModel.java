package com.transsnet.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Author:  zengfeng
 * Time  :  2021/2/7 13:53
 * Des   :
 */
public class HomeViewModel extends ViewModel {
    private MutableLiveData<StateInfoBean> stateInfoLiveData;

    public void setStateInfoLiveData(StateInfoBean stateInfo) {
        if (stateInfoLiveData == null) {
            stateInfoLiveData = new MutableLiveData<>();
        }
        stateInfoLiveData.setValue(stateInfo);
    }

    public MutableLiveData<StateInfoBean> getStateInfoLiveData() {
        if (stateInfoLiveData == null) {
            stateInfoLiveData = new MutableLiveData<>();
        }
        return stateInfoLiveData;
    }
} 
