package trainapplogos;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationFibonacciExecutors {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executable = Executors.newSingleThreadExecutor();
		executable.execute(new MyThreadEx());
		executable.execute(new RunnableThreadEx());
		executable.shutdown();
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
	public void run() {
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

class  RunnableThreadEx implements Runnable {
	@Override
	public void run() {
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