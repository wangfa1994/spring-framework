package com.wf.model.jdbc.jdbc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

	public static void main(String[] args) {
		int[] array = new int[100];
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}

		ForkJoinPool pool = new ForkJoinPool();
		SumTask task = new SumTask(array, 0, array.length);

		long sum = pool.invoke(task);
		System.out.println("Sum: " + sum);
	}
}

 class SumTask extends RecursiveTask<Long> {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 10; // 任务分解的阈值
	private int[] array;
	private int start;
	private int end;

	public SumTask(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if (end - start <= THRESHOLD) { // 如果任务足够小，直接计算
			long sum = 0;
			for (int i = start; i < end; i++) {
				sum += array[i];
			}
			return sum;
		} else { // 否则分解为子任务
			int mid = (start + end) / 2;
			SumTask leftTask = new SumTask(array, start, mid);
			SumTask rightTask = new SumTask(array, mid, end);

			leftTask.fork(); // 提交左子任务
			Long rightResult = rightTask.compute(); // 同步执行右子任务
			Long leftResult = leftTask.join(); // 等待左子任务完成

			return leftResult + rightResult;
		}
	}

}
