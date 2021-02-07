package com.transsnet.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TextView homeTabTv;
    private TextView infoTabTv;
    private HomeViewModel mHomeViewModel;
    private Observer<StateInfoBean> mStateInfoObserver;
    private TextView mineTabTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();


//        initObserver();
        mHomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class); //创建ViewModel对象

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
                compressPic();
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

    public void compressPic(){
        Log.d("vivi","获得读写SD卡权限");
        String srcPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/src.jpg";
        String desPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/des.jpg";
//        String[] list = Environment.getExternalStorageDirectory().list();
        String src1Path = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
        if(!getFilesDir().exists()){
            getFilesDir().mkdirs();
        }


        Log.d("vivi","src1 ="+src1Path);
        Log.d("vivi","des ="+desPath);
        Luban.with(this)
                .load(srcPath)
                .ignoreBy(100)
                .setTargetDir(desPath)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                        Log.d("vivi","onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        Log.d("vivi","onSuccess");
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                        Log.d("vivi","onError:"+e.getMessage());
                    }
                }).launch();


        threadTest();
    }

    public void threadTest(){
        final MyThread mLooperThread = new MyThread();
        mLooperThread.start();
        new Thread() {

            @Override
            public void run() {
                while (mLooperThread.mHandler == null) {
                    try {
                       Thread.currentThread().sleep(100);
//                        wait();//防止在发送消息时Handler还没建立
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mLooperThread.mHandler.sendEmptyMessage(0);
                Log.w("vivi", "Send Message::Thread id ---" + getId());
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StateInfoBean bean = new StateInfoBean();
        bean.setLogin(false);
        bean.setToken("abcd");
        mHomeViewModel.setStateInfoLiveData(bean);
        Log.i("vivi", "发送LiveData数据");
    }
}
