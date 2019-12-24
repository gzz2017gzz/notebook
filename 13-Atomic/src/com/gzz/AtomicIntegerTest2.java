package com.gzz;

//要是换成volatile修饰count变量呢？
//顺带说下volatile关键字很重要的两个特性:
//1、保证变量在线程间可见，对volatile变量所有的写操作都能立即反应到其他线程中，换句话说，volatile变量在各个线程中是一致的（得益于java内存模型—"先行发生原则"）；
//2、禁止指令的重排序优化；
//那么换成volatile修饰count变量后，会有什么效果呢？ 试一试:
 
public class AtomicIntegerTest2 {
	public static volatile int count = 0;

	public static void increase() {
		count++;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					increase();
				}
			}).start();
		}
		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(count);
	}
}
 
