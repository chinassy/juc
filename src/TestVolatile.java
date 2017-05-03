
/**
 * volatile关键字：当多个线程操作共享数据时，可以保证内存中的数据可见。
 *               相较于synchronized是一种较为轻量级的同步策略。
 *               
 * 与synchronized不同：
 * 1、volatile不具备“互斥性”。
 * 2、volatile不能保证变量的“原子性”。
 * @author sunshuyang
 *
 */
public class TestVolatile {
	
	public static void main(String[] args) {
		
		ThreadDemo td = new ThreadDemo();
		Thread t = new Thread(td);
		t.start();
		
		while(true){
			if(td.isFlag()){
				System.out.println("---------------");
				break;
			}
		}
		
		
	}

}

class ThreadDemo implements Runnable{
	
	private volatile boolean flag = false;

	@Override
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		flag = true;
		System.out.println("flag=" + isFlag());
				
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
}
