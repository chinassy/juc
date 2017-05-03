import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumerForLock {
	
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		
		Productor p = new Productor(clerk);
		Consumer c = new Consumer(clerk);
		
		new Thread(p,"生产者A").start();
		new Thread(c,"消费者B").start();
		
		new Thread(p,"生产者A").start();
		new Thread(c,"消费者B").start();
	}
	

}
//店员
class Clerk1{
	private int product = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	//进货
	public  void get(){
		lock.lock();
		try{
			while(product >= 10){ //为了避免虚假唤醒问题，应该总是使用在循环中。
				System.out.println("产品已满");
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			
				System.out.println(Thread.currentThread().getName() + ":" + ++product);
				condition.signalAll();
		}finally{
			lock.unlock();
		}
		
	}
	//卖货
	public synchronized void sale(){
		lock.lock();
		try{
		while(product <= 0){
			System.out.println("缺货");
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			System.out.println(Thread.currentThread().getName() + ":" + --product);
			condition.signalAll();
		}finally{
			lock.unlock();
		}
		
		
	}
}


//生产者
class Productor1 implements Runnable{
	
	private Clerk clerk;
	
	public Productor1(Clerk clerk){
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for(int i = 0 ; i < 20 ; i++){
			
			clerk.get();
		}
	}   
}

//消费者
class Consumer1 implements Runnable{
	
	private Clerk clerk;
	
	 public Consumer1(Clerk clerk) {
		 this.clerk = clerk;
	}

	@Override
	public void run() {
		for(int i = 0 ; i < 20 ; i++){
			clerk.sale();
		}
	}
	
}
