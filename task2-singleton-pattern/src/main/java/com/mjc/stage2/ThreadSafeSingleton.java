package com.mjc.stage2;


public class ThreadSafeSingleton {
    private ThreadSafeSingleton(){};

    private static class SingletonInstance{
        private static final ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();
    }
    public static ThreadSafeSingleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
