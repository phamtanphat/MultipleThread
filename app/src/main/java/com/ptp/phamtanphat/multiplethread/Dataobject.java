package com.ptp.phamtanphat.multiplethread;

/**
 * Created by KhoaPhamPC on 24/10/2017.
 */

public class Dataobject {
    private int A;
    private int B;
    private int laco;

    public int getLaco() {
        return laco;
    }

    public void setLaco(int laco) {
        this.laco = laco;
    }

    public Dataobject() {

    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }
    public int Tinhtong(){
       return this.A + this.B;
    }
}
