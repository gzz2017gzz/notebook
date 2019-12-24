package com.gzz.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerRun {

//	public static void main(String[] args) throws ParseException {
//		Date date = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse("2019-01-22 15:56:00");
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println("系统正在运行……");
//			}
//		}, date);
//	}
	/*
	 * 在指定的日期运行一次定时任务 如果date日期在今天之前 则启动定时器后，立即运行一次定时任务run方法
	 * 如果date日期在今天之后，则启动定时器后，会在指定的将来日期运行一次任务run方法
	 */

//	public static void main(String[] args) {
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println("系统正在运行……");
//			}
//		}, 5000); // 指定启动定时器5s之后运行定时器任务run方法，并且只运行一次
//	}
//	public static void main(String[] args) throws ParseException {
//		String sdate = "2018-02-10";
//		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd");
//		Date date = sf.parse(sdate);
//
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				System.out.println("系统正在运行……");
//			}
//		}, date, 2000);
//		/* 如果指定的date时间是当天或者今天之前，启动定时器后会立即每隔2s运行一次定时器任务 */
//		/* 如果指定的date时间是未来的某天，启动定时器后会在未来的那天开始，每隔2s执行一次定时器任务 */
//	}
//	  public static void main(String[] args) {
//	        Timer timer = new Timer();
//	        timer.schedule(new TimerTask() {
//
//	            @Override
//	            public void run() {
//	                System.out.println("系统正在运行……");
//	            }
//	        }, 5000, 2000);
//	        /*当启动定时器后，5s之后开始每隔2s执行一次定时器任务*/
//	    }
//	public static void main(String[] args) throws InterruptedException {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("系统正在运行……");
////              timer.cancel(); //可以在任何时刻调用cancel方法终止timer线程
//            }
//        }, 5000, 2000);
//
//        /*如果主线程不休眠一段时间，就执行了cancel方法，那么定时器还没来得及执行就会被关闭*/
//        Thread.sleep(6000);
//        timer.cancel();
//    }

//	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//		Date date = sf.parse("2018-02-12 18:33:00");
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println(new Date() + "系统正在运行……");
//			}
//		}, date, 10 * 1000);
//	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date date = sf.parse("2018-02-12 18:39:00");

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				System.out.println(new Date() + "系统正在运行……");
			}
		}, date, 10 * 1000);
	}

}
