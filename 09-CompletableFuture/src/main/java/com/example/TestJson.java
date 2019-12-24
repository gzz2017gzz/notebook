package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestJson {
	private static Log logger = LogFactory.getLog(TestJson.class);// 定义日志工具

//	@Test
	public void test1() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		new Thread(() -> {
			// 模拟执行耗时任务
			logger.info("task doing...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 告诉completableFuture任务已经完成
			completableFuture.complete("ok");
		}).start();
		// 获取任务结果，如果没有完成会一直阻塞等待
		String result = completableFuture.get();
		logger.info("计算结果:" + result);
	}

//	@Test
	public void test2() throws ExecutionException, InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		new Thread(() -> {
			// 模拟执行耗时任务
			System.out.println("task doing...");
			try {
				Thread.sleep(3000);
				@SuppressWarnings("unused")
				int i = 1 / 0;
			} catch (Exception e) {
				// 告诉completableFuture任务发生异常了
				completableFuture.completeExceptionally(e);
			}
			// 告诉completableFuture任务已经完成
			completableFuture.complete("ok");
		}).start();
		// 获取任务结果，如果没有完成会一直阻塞等待
		String result = completableFuture.get();
		System.out.println("计算结果:" + result);
	}

//	@Test
	public void testMethod() {
		String[] orders = { "1", "2", "3", "4", "5", "6" };
		List<CompletableFuture<Boolean>> futures = new ArrayList<>();
		Arrays.stream(orders).forEach(id -> {
			try {
				futures.add(submitAsync(id));
			} catch (Exception ex) {
				System.out.println(ex);
			}
		});
		futures.stream().forEach(f -> {
			try {
				System.out.println(f.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private static Boolean submit(String order) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("抛一个异常" + order);
	}

	private static CompletableFuture<Boolean> submitAsync(String order) {
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		new Thread(() -> {
			try {
				Boolean result = submit(order);
				future.complete(result);
			} catch (Exception ex) {
				future.completeExceptionally(ex);
			}
		}).start();
		return future;
	}

//	@Test
	public void thenAccept() {
		CompletableFuture.supplyAsync(() -> "hello").thenAccept(s -> System.out.println(s + " world"));
	}

//	@Test
	public void thenRun() {
		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		}).thenRun(() -> System.out.println("hello world"));
		while (true) {
		}
	}

	@Test
	public void thenCombine() {
		long start = System.currentTimeMillis();
		String result = CompletableFuture.supplyAsync(() -> {
			this.speep(000);
			return "hello";
		}).thenCombine(CompletableFuture.supplyAsync(() -> {
			this.speep(000);
			return "world";
		}), (s1, s2) -> s1 + " " + s2).join();
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(result);
	}

	public void speep(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void method() {

		// 第一个任务。
		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
			this.speep(200);
			return "zhang";
		});

		// 第二个任务把第一个任务联合起来。
		CompletableFuture<String> f2 = f1.thenCombine(CompletableFuture.supplyAsync(() -> {
			this.speep(500);
			return "phil";
		}), (s1, s2) -> s1 + " " + s2 );

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
