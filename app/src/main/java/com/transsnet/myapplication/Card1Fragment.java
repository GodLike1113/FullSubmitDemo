package com.transsnet.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * Author:  zengfeng
 * Time  :  2020/9/24 10:37
 * Des   :
 */
public class Card1Fragment  extends Fragment {
    private HomeViewModel mHomeViewModel;
    private Observer<StateInfoBean> mStateInfoObserver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.card1_fragment, null);
        if(view instanceof RelativeLayout){
            System.out.println("View是个相对布局");
        }else if(view instanceof TextView){
            System.out.println("View是个文本");
        }

        initObserver();
        mHomeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class); //创建ViewModel对象

        mHomeViewModel.getStateInfoLiveData().observeForever(mStateInfoObserver); //从ViewModel中取出LiveData再添加一个观察者
        return view;
    }



    /**
     * 里面存着一个坑，在不可见时不会进行更新Ui
     */
    private void initObserver() {
        mStateInfoObserver = new Observer<StateInfoBean>() {

            @Override
            public void onChanged(StateInfoBean stateInfoBean) {
                String token = stateInfoBean.getToken();
                boolean login = stateInfoBean.isLogin();
                Log.d("vivi", "Card1Fragment接收到token =" + token + ",login =" + login);
            }
        };
    }
}
