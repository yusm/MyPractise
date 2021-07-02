package com.example.yusm.mypractise.synchron;
/*
 *
 * Date: 2019/7/16
 * Desc：
 */

public class Thread2 implements Runnable{

    @Override
    public void run() {
        // TODO Auto-generated method stub
//		SynchronizedTest s = SynchronizedTest.getInstance();
//		SynchronizedTest s2 = new SynchronizedTest();
//		s2.method1();
//		s.method2();
//		SynchronizedTest.staticMethod1();
//		SynchronizedTest.staticMethod2();
		SynchronizedTest.staticIn.method2();
//        SynchronizedTest.staticIn.staticMethod1();
    }
}