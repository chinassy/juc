import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author sunshuyang
 * 一、i++ 的原子性问题：i++的操作实际上分为三个步骤：读-改-写
 *   int i = 10;
 *   i = i++; //10
 *   
 *   int temp = i;
 *   i = i + 1;
 *   i = temp;
 *   
 *   二、原子变量， 在jdk1.5以后，java.util.concurrent包提供了常用的原子变量：
 *     1、volatile 保证了内存可见性。
 *     2、CAS(Compare-And-Swap)算法保证数据的原子性。
 *        CAS算法是硬件对于并发操作共享数据的支持。
 *        CAS包含三个操作数：
 *        内存值 V
 *        预估值 A
 *        更新值 B
 *        当且仅当 V==A时，V=B，否则，将不做任何操作。
 */
public class TestAtomicDemo {
	
	public static void main(String[] args) {
		AtomicDemo demo = new AtomicDemo();
		for(int i = 0 ; i < 10 ; i++){
			new Thread(demo).start();
		}
	}

}


class AtomicDemo implements Runnable{
//	private int serialNumber = 0;
	private AtomicInteger serialNumber = new AtomicInteger();

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getSerialNumber());
	}
	
	public int getSerialNumber(){
		return serialNumber.getAndIncrement();
	}
	
}