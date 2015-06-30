package com.shouwei.csdn.util;

public class MySqliteHelper {

	private final static byte[] _writeLock = new byte[0];
	private static int _syncInt = 0;

	public void Method1() {
		synchronized (_writeLock) {
			StringBuffer sb = new StringBuffer();
			sb.append(Thread.currentThread().getName());
			sb.append(" Method1 ");
			sb.append(_syncInt);
			_syncInt++;
			sb.append(" --> ");
			sb.append(_syncInt);
			System.out.println(sb.toString());
		}
	}

	public void Method2() {
		synchronized (_writeLock) {
			StringBuffer sb = new StringBuffer();
			sb.append(Thread.currentThread().getName());
			sb.append(" Method2 ");
			sb.append(_syncInt);
			_syncInt++;
			sb.append(" --> ");
			sb.append(_syncInt);
			System.out.println(sb.toString());
		}
	}

	public void Method3() {
		synchronized (_writeLock) {
			this.Method1();
			this.Method2();
		}
	}
}

class MainTest {

	public static void main(String[] args) {

		Thread t2 = new Thread1();
		Thread t1 = new Thread2();
		Thread t3 = new Thread3();
		t1.start();
		t2.start();
		t3.start();
	}

	private static class Thread2 extends Thread {
		public void run() {
			MySqliteHelper tmp = new MySqliteHelper();
			while (true) {

				try {
					Thread.sleep(30000);
					tmp.Method1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println(y);
			}
		}
	}

	private static class Thread1 extends Thread {
		public void run() {
			MySqliteHelper tmp = new MySqliteHelper();
			while (true) {

				try {
					Thread.sleep(100);
					tmp.Method2();
					Thread.sleep(100);
					tmp.Method1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println(y);
			}
		}
	}

	private static class Thread3 extends Thread {
		public void run() {
			MySqliteHelper tmp = new MySqliteHelper();
			while (true) {

				try {
					Thread.sleep(100);
					tmp.Method3();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println(y);
			}
		}
	}
}