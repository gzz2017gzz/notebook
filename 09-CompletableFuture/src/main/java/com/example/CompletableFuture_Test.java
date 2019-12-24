package com.example;

import java.util.concurrent.CompletableFuture;
import org.junit.Test;

public class CompletableFuture_Test {

	public void speep(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void thenCombine1() {
		long start = System.currentTimeMillis();
		CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() -> {
			this.speep(200);
			return "刘";
		});
		CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> {
			this.speep(300);
			return "德";
		});
		CompletableFuture<String> c3 = CompletableFuture.supplyAsync(() -> {
			this.speep(500);
			return "华";
		});
		String join = c1.thenCombine(c2, (s1, s2) -> s1 + s2).thenCombine(c3, (s1, s2) -> s1 + s2).join();
		System.out.println(System.currentTimeMillis() - start + join);
	}

	public void thenCombine() {
		String result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		}).thenCombine(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "world";
		}), (s1, s2) -> s1 + " " + s2).join();
		System.out.println(result);
	}

}
