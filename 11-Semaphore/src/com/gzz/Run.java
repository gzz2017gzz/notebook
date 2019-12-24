package com.gzz;

public class Run {
	public static void main(String[] args) {
		Driver driver = new Driver();
		for (int i = 0; i < 5; i++) {
			(new Car(driver)).start();
		}
	}
}

//从输出可以看出，改输出与单线程是一样的，执行完一个线程，再执行另一个线程。