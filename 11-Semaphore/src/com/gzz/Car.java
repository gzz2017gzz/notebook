package com.gzz;

public class Car extends Thread {
	private Driver driver;

	public Car(Driver driver) {
		this.driver = driver;
	}

	public void run() {
		driver.driveCar();
	}
}
