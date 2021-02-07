package com.transsnet.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * Author:  zengfeng
 * Time  :  2020/9/23 14:13
 * Des   :
 */
public class MineFragment extends Fragment {
    private HomeViewModel mHomeViewModel;
    private Observer<StateInfoBean> mStateInfoObserver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("vivi","MineFragment创建");
        Toast.makeText(getActivity(),"create Mine",Toast.LENGTH_SHORT).show();
        View rootView = inflater.inflate(R.layout.mine_fragment, container, false);


        initObserver();
        //创建ViewModel对象,new ViewModelProvider(getActivity())发送和接收的对象必须一个，为getActivity()
        mHomeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);

        mHomeViewModel.getStateInfoLiveData().observeForever(mStateInfoObserver); //从ViewModel中取出LiveData再添加一个观察者

        return rootView;
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
                Log.d("vivi", "MineFragment接收到token =" + token + ",login =" + login);
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeViewModel.getStateInfoLiveData().removeObserver(mStateInfoObserver);
    }
}
