import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 一、用于解决多线程安全问题的方式：
 * syncronized隐式锁
 * 1、同步代码块
 * 
 * 2、同步方法 
 * 
 * 3、同步锁Lock （jdk1.5后）
 * 注意：这是一个显示锁，需要通过lock（）方法上锁，必须通过unlock（）方法进行释放锁。 unlock()一般都写在finally块中
 * @author sunshuyang
 *
 */
public class TestLock {
	
	public static void main(String[] args) {
		
		Ticket t = new Ticket();
		
		new Thread(t , "一号窗口").start();
		new Thread(t,"二号窗口").start();
		new Thread(t,"三号窗口").start();
		
	}

}

class Ticket implements Runnable{
	
	private int tick = 100;
	
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		lock.lock();
		try {
			
			while(tick > 0){
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --tick);
			}
			
		} finally {
			lock.unlock();//释放锁
		}
		
		
		
		
	}
	
}
