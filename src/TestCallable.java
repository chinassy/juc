import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * @author sunshuyang
 *
 *
 *一、创建执行线程方式三：实现callable接口。相较于实现runnable接口的方式，方法可以有返回值，并且可以抛出异常。
 *二、执行callable方式，需要FutureTask实现类的支持，用于接收运算结果。FutureTask是Future接口的实现类。
 */
public class TestCallable {
	
	public static void main(String[] args) {
		
		ThreadDemo1 td = new ThreadDemo1();
		//1、执行callable方式，需要futureTask实现类的支持。用于接收运算结果。
		FutureTask<Integer> futureTask = new  FutureTask<>(td);
		
		
		new Thread(futureTask).start();
		
		try {
			Integer sum = futureTask.get(); // FutureTask可以用于闭锁。
			System.out.println(sum);
			System.out.println("------------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}

class ThreadDemo1 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i = 0 ; i < 100 ; i++){
			sum += i;
		}
		
		return sum;
	}
	
}
