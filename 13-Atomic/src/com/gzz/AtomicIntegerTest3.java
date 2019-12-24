package com.gzz;

import java.util.concurrent.atomic.AtomicInteger;
//用了AtomicInteger类后会变成什么样子呢？
//把上面的代码改造成AtomicInteger原子类型，先看看效果
//结果每次都输出20000，程序输出了正确的结果，这都归功于AtomicInteger.incrementAndGet()方法的原子性。
public class AtomicIntegerTest3 {
	public static AtomicInteger count = new AtomicInteger(0);

	public static void increase() {
		count.incrementAndGet();
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
