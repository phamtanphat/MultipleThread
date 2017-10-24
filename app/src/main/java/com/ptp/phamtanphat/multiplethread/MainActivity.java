package com.ptp.phamtanphat.multiplethread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Dataobject dataobject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataobject = new Dataobject();
        dataobject.setLaco(1);
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject){
                    for (int i = 0; i < 50; ) {
                        if (dataobject.getLaco() == 1) {
                            int a = new Random().nextInt(100);
                            dataobject.setA(a);
                            Log.d("BBB","Vi tri cua i " + i);
                            Log.d("BBB", "Số a : " + dataobject.getA());

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            i++;
                            dataobject.setLaco(2);
                            dataobject.notifyAll();
                        } else {
                            try {
                                dataobject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    dataobject.setLaco(0);
                    dataobject.notifyAll();
                }

            }
        });
        Thread threadb = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject){
                    for (; true;) {
                        if (dataobject.getLaco() == 2 || dataobject.getLaco() == 0) {
                            int b = new Random().nextInt(100);
                            dataobject.setB(b);
                            Log.d("BBB", "Số b : " + dataobject.getB());


                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (dataobject.getLaco() == 0){
                                dataobject.setLaco(4);
                                break;
                            }
                            dataobject.setLaco(3);
                            dataobject.notifyAll();
                        } else {
                            try {
                                dataobject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });
        Thread threadtinhtong = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject) {
                    for (; true; ) {
                        if (dataobject.getLaco() == 3 || dataobject.getLaco() == 4) {
                            Log.d("BBB", "Tổng : " + dataobject.Tinhtong());

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (dataobject.getLaco() == 4){
                                break;
                            }
                            dataobject.setLaco(1);
                            dataobject.notifyAll();
                        } else {
                            try {
                                dataobject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        threadA.start();
        threadb.start();
        threadtinhtong.start();

    }
}
