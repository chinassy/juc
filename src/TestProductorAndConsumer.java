
public class TestProductorAndConsumer {
	
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
class Clerk{
	private int product = 0;
	//进货
	public synchronized void get(){
		while(product >= 10){ //为了避免虚假唤醒问题，应该总是使用在循环中。
			System.out.println("产品已满");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		
			System.out.println(Thread.currentThread().getName() + ":" + ++product);
			this.notifyAll();
		
		
	}
	//卖货
	public synchronized void sale(){
		while(product <= 0){
			System.out.println("缺货");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			System.out.println(Thread.currentThread().getName() + ":" + --product);
			this.notifyAll();
		
	}
}


//生产者
class Productor implements Runnable{
	
	private Clerk clerk;
	
	public Productor(Clerk clerk){
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
class Consumer implements Runnable{
	
	private Clerk clerk;
	
	 public Consumer(Clerk clerk) {
		 this.clerk = clerk;
	}

	@Override
	public void run() {
		for(int i = 0 ; i < 20 ; i++){
			clerk.sale();
		}
	}
	
}
