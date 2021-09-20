package com.ibm.fp.interfaces.builtin;

public class ThreadImpl {
    public static void main(String[] args) {
        Runnable target = () -> System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(target);
        thread.start();

        Thread thread1 = new Thread(()-> System.out.println(Thread.currentThread().getName()));
        thread1.start();
    }
}
