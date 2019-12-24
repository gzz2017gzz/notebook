package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import org.junit.Test;

public class TestCoM {
	public void speep(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void method() {
		// 第一个任务。
		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
			this.speep(200);
			return "刘";
		});
		// 第二个任务把第一个任务联合起来。
		CompletableFuture<String> f2 = f1.thenCombine(CompletableFuture.supplyAsync(() -> {
			this.speep(300);
			return "德";
		}), (s1, s2) -> s1 + s2);
		// 第三个任务把第二个任务联合起来。
		CompletableFuture<String> f3 = f2.thenCombine(CompletableFuture.supplyAsync(() -> {
			this.speep(400);
			return "华";
		}), (s1, s2) -> s1 + s2);
		System.out.println("等待联合任务的全部执行完毕...");
		f2.whenCompleteAsync(new BiConsumer<String, Throwable>() {
			@Override
			public void accept(String s, Throwable throwable) {
				System.out.println("联合任务均完成");
				System.out.println(s);
			}
		});
		System.out.println("代码运行至此。");
	}

}
