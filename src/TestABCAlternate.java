import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启三个线程，这三个线程的ID分别为A/B/C,每个线程将自己的ID在屏幕上打印10遍，要求输出的结果必须按顺序显示。
 * 如：ABCABCABC... 依次递归
 * 
 * @author sunshuyang
 *
 */
public class TestABCAlternate {

	public static void main(String[] args) {

		Alternate alt = new Alternate();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					alt.loopA(i);
				}

			}
		}, "A").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					alt.loopB(i);
				}

			}
		}, "B").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					alt.loopC(i);
					System.out.println("-----------------------------------");
				}

			}
		}, "C").start();

	}

}

class Alternate {

	private int num = 1;
	private Lock lock = new ReentrantLock();
	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();

	public void loopA(int totalLoop) {
		lock.lock();
		try {

			if (num != 1) {
				condition1.await();
			}

			for (int i = 1; i <= 1; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}

			num = 2;
			condition2.signal();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void loopB(int totalLoop) {
		lock.lock();
		try {

			if (num != 2) {
				condition2.await();
			}

			for (int i = 1; i <= 1; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}

			num = 3;
			condition3.signal();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void loopC(int totalLoop) {
		lock.lock();
		try {

			if (num != 3) {
				condition3.await();
			}

			for (int i = 1; i <= 1; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}

			num = 1;
			condition1.signal();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
