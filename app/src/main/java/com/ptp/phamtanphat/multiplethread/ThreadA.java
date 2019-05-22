package com.ptp.phamtanphat.multiplethread;

import android.util.Log;

public class ThreadA extends Thread {
    @Override
    public void run() {
        super.run();
        for(int i = 0 ; i<100 ; i++){
            try {
                Thread.sleep(20);
                Log.d("BBB","a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
