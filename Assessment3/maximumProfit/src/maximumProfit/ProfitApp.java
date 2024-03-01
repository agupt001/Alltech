package maximumProfit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ProfitApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Get the total number of items
		System.out.println("Enter the number of items for sale");
		int num_items = sc.nextInt();

		int[] category = new int[num_items];
		int[] price = new int[num_items];

		System.out.println("");
		System.out.println("Lets fill out the category array");
		System.out.println("");

		// Get the categories
		for (int i = 0; i < num_items; i++) {

			System.out.println("Enter category " + (i + 1));
			int cat = sc.nextInt();
			category[i] = cat;

		}

		System.out.println("");
		System.out.println("Awesome! Lets fill out the price array");
		System.out.println("");

		// Get the price of each item
		for (int i = 0; i < num_items; i++) {

			System.out.println("Enter price " + (i + 1));
			int prc = sc.nextInt();
			price[i] = prc;

		}

		// Calculate maximum profit
		long max_profit = findMaximumProfit(category, price);

		// Print Max profit
		System.out.println("");
		System.out.println(max_profit);

		sc.close();

	}

	/*
	 * Method to calculate maximum profit Input parameters category[], price[]
	 * returns long
	 */
	public static long findMaximumProfit(int[] category, int[] price) {

		/*
		 * Logic Profit will be maximum when price array is sorted Sort the price array
		 * and store the indices Iterate through the indices and calculate profit based
		 * on the number of categories processed Store the categories processed in a set
		 */

		// Create an indices array
		Integer[] indices = new Integer[price.length];

		for (int i = 0; i < price.length; i++) {
			indices[i] = i; // Initialize the array of indices
		}

		// Sort the array and store indices in the order of sorting
		Arrays.sort(indices, (a, b) -> Integer.compare(price[a], price[b]));

		// Create a set to store the category processed
		Set<Integer> diffCategorySold = new HashSet<Integer>();

		// Create max_profit and multiplier attributes
		long max_profit = 0L;
		long multiplier = 1L;

		// Iterate through the indices and calculate profit using hashSet
		for (Integer index : indices) {

			// Store the categories in the set
			diffCategorySold.add(category[index]);

			// Multiplier will be the size of different categories stored
			multiplier = (long) diffCategorySold.size();

			// Calculate profit and add it to the max_profit
			max_profit += (price[index] * multiplier);

		}

		return max_profit;
	}
}
