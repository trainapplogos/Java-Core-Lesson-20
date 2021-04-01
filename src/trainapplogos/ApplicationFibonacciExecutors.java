package trainapplogos;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApplicationFibonacciExecutors {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executable = Executors.newFixedThreadPool(1);
		Future<?> task = executable.submit(new MyThreadEx());
		
		while(!task.isDone())
		{
		 Thread.sleep(1);
		}
		
		task.get();
		executable.shutdown();
		
		ExecutorService executable2 = Executors.newSingleThreadExecutor();
		executable2.execute(new RunnableThreadEx());
		executable2.shutdown();
	}
	
	static int[] calculateFibonacci(int count) { 
		int cur = 0;
		int prev1= 1;
		int prev2= 1;
		
		int [] fiboArr = new int[count];
		
		for (int i = 0; i <= count - 1; i++) {
			if (i > 1) { cur =  prev1 + prev2; } else { cur = 1; };
			fiboArr[i] = cur;
			prev2 = prev1;
			prev1 = cur;
		}
		return fiboArr;
	}
}

class MyThreadEx extends Thread {
	@Override
	public synchronized void run() {
		System.out.println("> [MyThread]: Enter number of Fibonachi's numbers:");
		Scanner sc = new Scanner(System.in);
		int arr[] = ApplicationFibonacciExecutors.calculateFibonacci(sc.nextInt());
		
		System.out.print("Thread \"Thread\": ");
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}	
	}
}

class RunnableThreadEx implements Runnable {
	@Override
	public synchronized void run() {
		System.out.println("\n> [RunnableThread]: Enter number of Fibonachi's numbers:");
		Scanner sc = new Scanner(System.in);
		int arr[] = ApplicationFibonacciExecutors.calculateFibonacci(sc.nextInt());
		
		System.out.print("Thread \"Runnable\": ");
		for (int i = arr.length - 1; i >= 0 ; i--) {
			System.out.print(arr[i] + " ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	
	}
}