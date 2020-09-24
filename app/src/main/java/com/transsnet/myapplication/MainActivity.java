package com.transsnet.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TextView homeTabTv;
    private TextView infoTabTv;
    private TextView mineTabTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        homeTabTv = findViewById(R.id.home_tv);
        infoTabTv = findViewById(R.id.info_tv);
        mineTabTv = findViewById(R.id.mine_tv);
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homeTabTv.setOnClickListener(this);
        infoTabTv.setOnClickListener(this);
        mineTabTv.setOnClickListener(this);
    }

    private void initData() {
        HomeFragment homeFragment = new HomeFragment();
        InfoFragment infoFragment = new InfoFragment();
        MineFragment mineFragment = new MineFragment();
        final List<Fragment> srcList = new ArrayList<>();
        srcList.add(homeFragment);
        srcList.add(infoFragment);
        srcList.add(mineFragment);

        HomePagerAdapter adapter =new HomePagerAdapter(getSupportFragmentManager());
        adapter.setFragments(srcList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }


    public void selectTab(int position){
        if(position == 0){
            homeTabTv.setTextColor(Color.parseColor("#099BFA"));
            infoTabTv.setTextColor(Color.parseColor("#999999"));
            mineTabTv.setTextColor(Color.parseColor("#999999"));
        }else if(position == 1){
            homeTabTv.setTextColor(Color.parseColor("#999999"));
            infoTabTv.setTextColor(Color.parseColor("#099BFA"));
            mineTabTv.setTextColor(Color.parseColor("#999999"));
        }else if(position == 2){
            homeTabTv.setTextColor(Color.parseColor("#999999"));
            infoTabTv.setTextColor(Color.parseColor("#999999"));
            mineTabTv.setTextColor(Color.parseColor("#099BFA"));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_tv:
                viewPager.setCurrentItem(0,false);
                selectTab(0);
                break;
            case R.id.info_tv:
                viewPager.setCurrentItem(1,false);
                selectTab(1);
                break;
            case R.id.mine_tv:
                viewPager.setCurrentItem(2,false);
                selectTab(2);
                break;
        }
    }
}
