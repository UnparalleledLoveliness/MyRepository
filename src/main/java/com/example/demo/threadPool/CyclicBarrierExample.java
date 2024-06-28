package com.example.demo.threadPool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
  public static void main(String[] args) {
    // 创建一个CyclicBarrier实例，添加一个所有线程都到达屏障后执行的任务
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () ->
        System.out.println("所有线程都到达屏障，开始执行任务"));

    // 创建并启动3个线程
    for (int i = 0; i < 3; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "到达屏障");
        try {
          // 调用await()方法，表示自己已经到达了屏障
          cyclicBarrier.await();
        } catch (BrokenBarrierException | InterruptedException e) {
          throw new RuntimeException(e);
        }
      }).start();
    }
  }
}
