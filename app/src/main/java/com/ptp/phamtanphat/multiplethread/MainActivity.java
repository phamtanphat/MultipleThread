package com.ptp.phamtanphat.multiplethread;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {


    Dataobject dataobject = new Dataobject();
    Thread threada , threadb , threadc;
    int y,z = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataobject.setLaco(0);

//        Chỉ tạo ra 3 luồng trong hệ thống

        threada = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject){
                    for(int i = 0 ; i< 10 ; ){
                        if (dataobject.getLaco() == 0){
                            int a = new Random().nextInt(10);
                            Log.d("BBB","A " +a + " index " + i);
                            i++;
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            dataobject.setA(a);
                            dataobject.setLaco(1);
                            dataobject.notifyAll();
                        }else{
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


        threadb = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject){
                    for(;true;){
                        if (dataobject.getLaco() == 1 || dataobject.getLaco() == 0){
                            int b = new Random().nextInt(10);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.d("BBB","B " +b + " index " + y++);
                            if (dataobject.getLaco() == 0){
                                dataobject.setLaco(3);
                                dataobject.notifyAll();
                                break;
                            }
                            dataobject.setB(b);
                            dataobject.setLaco(2);
                            dataobject.notifyAll();


                        }else{
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
        threadc = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (dataobject){
                    for( ; true ;){
                        if (dataobject.getLaco() == 2 || dataobject.getLaco() == 3){
                            int c = dataobject.getA() + dataobject.getB();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.d("BBB","Gia tri c" + c + " index " + z++);
                            if (dataobject.getLaco() ==3){
                                dataobject.notifyAll();
                                break;
                            }
                            dataobject.setLaco(0);
                            dataobject.notifyAll();


                        }else{
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


        threada.start();
        threadb.start();
        threadc.start();


    }

}
