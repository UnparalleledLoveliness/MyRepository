package com.example.demo.service;

import com.example.demo.imp.Calculator;
import com.example.demo.imp.CalculatorImp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorProxy {
    Object target;
    public CalculatorProxy (Object target)
    {
        this.target=target;
    }

    public Object getCalculatorProxy()
    {
        Object proxy = null;
        //代理对象由哪个类加载器加载
        ClassLoader loader = target.getClass().getClassLoader();
        //代理对象的类型，即其中有哪些方法
        Class[] interfaces = target.getClass().getInterfaces();
        //处理方法
        InvocationHandler h = new InvocationHandler(){
            /**
             * proxy:正在返回的代理的对象，一般不去使用它
             * method: 被调用的方法
             * args: 被调用方法的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                String methodName = method.getName();
                //日志
                System.out.println("Log:method "+methodName+" begin:"+ Arrays.asList(args));
                //执行方法
                Object result = method.invoke(target, args);
                //日志
                System.out.println("Log:method "+methodName+" end:"+Arrays.asList(args));

                return result;
            }
        };

        //创建代理对象
        proxy =  Proxy.newProxyInstance(loader, interfaces, h);

        return proxy;

    }

    public static void main(String[] args) {
        Calculator target = new CalculatorImp();

        Calculator proxy = (Calculator) new CalculatorProxy(target).getCalculatorProxy();
        int result = proxy.add(1, 2);
        System.out.println("-->"+result);
        result = proxy.sub(2, 1);
        System.out.println("-->"+result);
    }
}
