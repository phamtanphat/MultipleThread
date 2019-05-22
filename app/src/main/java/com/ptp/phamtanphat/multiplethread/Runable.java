package com.ptp.phamtanphat.multiplethread;

import android.util.Log;

public class Runable implements Runnable {
    @Override
    public void run() {
        for(int i = 0 ; i<100 ; i++){
            try {
                Thread.sleep(20);
                Log.d("BBB","b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
