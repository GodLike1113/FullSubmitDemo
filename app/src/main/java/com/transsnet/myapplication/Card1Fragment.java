package com.transsnet.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Author:  zengfeng
 * Time  :  2020/9/24 10:37
 * Des   :
 */
public class Card1Fragment  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.card1_fragment, null);
        if(view instanceof RelativeLayout){
            System.out.println("View是个相对布局");
        }else if(view instanceof TextView){
            System.out.println("View是个文本");
        }
        return view;
    }
}
