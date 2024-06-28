package com.example.demo.design.singleton;

/*
 * 单例模式：
 * 优点：
 * 1.在内存中只有一个对象，节省内存空间
 * 2.避免频繁的创建销毁对象，可以提高性能
 * 3.避免对共享资源的多重占用
 * 4.可以全局访问
 * 缺点：
 * 1.没有抽象层，不能扩展
 * 应用场景：
 * 1.需要频繁实例化然后销毁的对象（Spring mvc中的controller）
 * 2.创建对象时耗时过多或者耗资源过多，但又经常用到的对象（线程池）
 */

public class Singleton {
  private static Singleton instance = null;

  private Singleton() {
  }

  public static synchronized Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}
