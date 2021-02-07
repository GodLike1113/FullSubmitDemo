package com.transsnet.myapplication;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Author:  zengfeng
 * Time  :  2021/2/1 16:29
 * Des   :
 */
public class MyThread extends Thread {
    Handler mHandler;

    @Override
    public void run() {
        super.run();

        synchronized (MyThread.class) {
            Looper.prepare();
            mHandler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.w("vivi", "handleMessage::Thread id---" + getId());
                }

            };
            Looper.loop();
            notifyAll();
        }
    }
}
