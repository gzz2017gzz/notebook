package com.gzz;

import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		for (long i = 26086290; i < 100000000; i++) {

			System.out.print("\t" + i);
			TimeUnit.MILLISECONDS.sleep(100);
			if (i % 20 == 0)
				System.out.println();

		}
	}

}
