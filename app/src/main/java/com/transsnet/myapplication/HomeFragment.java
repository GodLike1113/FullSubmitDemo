package com.transsnet.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager vp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_first, container, false);
        System.out.println("rootView 是："+rootView);
        vp = rootView.findViewById(R.id.viewPager);

        initData();
        return rootView;
    }

    private void initData() {
        CardPagerAdapter adapter = new CardPagerAdapter(getActivity().getSupportFragmentManager());
        Card1Fragment card1Fragment = new Card1Fragment();
        Card2Fragment card2Fragment = new Card2Fragment();
        List<Fragment> list = new ArrayList<>();
        list.add(card1Fragment);
        list.add(card2Fragment);
        adapter.setFragments(list);
        vp.setAdapter(adapter);
    }

}
