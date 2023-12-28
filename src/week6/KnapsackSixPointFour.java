package week6;

// Q) 6.4
public class KnapsackSixPointFour {

	public static int knapsack(int capacity, int[] weights, int[] values, int n) {
		if (n == 0 || capacity == 0) {
			return 0;
		}
		if (weights[n - 1] > capacity) {
			return knapsack(capacity, weights, values, n - 1);
		}
		int includedValue = values[n - 1] + knapsack(capacity - weights[n - 1], weights, values, n - 1);
		int excludedValue = knapsack(capacity, weights, values, n - 1);
		return Math.max(includedValue, excludedValue);
	}

	public static void main(String[] args) {
		int[] weights = {2, 2, 3, 5, 6};
		int[] values = {4, 2, 3, 7, 10};
		int capacity = 10;
		int n = weights.length;
		int result = knapsack(capacity, weights, values, n);
		System.out.println("Obtained maximum value: " + result);
	}

}
