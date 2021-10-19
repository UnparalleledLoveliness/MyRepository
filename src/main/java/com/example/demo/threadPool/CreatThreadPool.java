package com.example.demo.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CreatThreadPool {
  public static void main(String[] args) {
    ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(3,3,5, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(5));
    for (int i = 0; i < 4; i++) {
      poolExecutor.execute(() -> {
        for (int x = 0; x < 2; x++) {
          System.out.println(Thread.currentThread().getName() + ":" + x);
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }
    poolExecutor.shutdown();
  }
}
