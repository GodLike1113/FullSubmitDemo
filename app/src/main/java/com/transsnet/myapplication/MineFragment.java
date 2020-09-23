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

/**
 * Author:  zengfeng
 * Time  :  2020/9/23 14:13
 * Des   :
 */
public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("vivi","MineFragment创建");
        Toast.makeText(getActivity(),"create Mine",Toast.LENGTH_SHORT).show();
        View rootView = inflater.inflate(R.layout.mine_fragment, container, false);
        return rootView;
    }
}
