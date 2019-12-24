package com.gzz;

//为什么需要AtomicInteger原子操作类？
//对于Java中的运算操作，例如自增或自减，若没有进行额外的同步操作，在多线程环境下就是线程不安全的。num++解析为num=num+1，
//明显，这个操作不具备原子性，多线程并发共享这个变量时必然会出现问题。测试代码如下:
public class AtomicIntegerTest {
	public static int count = 0;

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
//这里运行了20个线程，每个线程对count变量进行1000此自增操作，如果上面这段代码能够正常并发的话，最后的结果应该是20000才对，
//但实际结果却发现每次运行的结果都不相同，都是一个小于20000的数字。这是为什么呢？

