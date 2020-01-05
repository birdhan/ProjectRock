package com.cloud.base.test.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Concurrent4ThreadPool {     
	private ExecutorService exe = null;    
	private static final int POOL_SIZE = 4;    
	public Concurrent4ThreadPool() {
        exe = Executors.newFixedThreadPool(POOL_SIZE);        
        System.out.println("the server is ready...");
    }
    public void server() {
        int i = 0;
        while (i < 100) {
            exe.execute(new Worker(i));
        }
    }
    public static void main(String[] args) {
        new Concurrent4ThreadPool().server();
    }
    
    class Worker implements Runnable {
        int id;
        Worker(int id) {
            this.id = id;
        }
        public void run() {
            System.out.println("task " + id + ":start");        
        }
    }
}
