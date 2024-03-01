package bankChatBot;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankOperations implements IBankAccountOperation {

	// Maintain a balance attribute
	private float balance = 0f;

	// Create and initialize set of all the keywords which might show up
	@SuppressWarnings("serial")
	private final Set<String> showKeywords = new HashSet<String>() {
		{
			add("see");
			add("show");
		}
	};

	@SuppressWarnings("serial")
	private final Set<String> depositKeywords = new HashSet<String>() {
		{
			add("deposit");
			add("put");
			add("invest");
			add("transfer");
		}
	};

	@SuppressWarnings("serial")
	private final Set<String> withdrawKeywords = new HashSet<String>() {
		{
			add("withdraw");
			add("pull");
		}
	};

	public void processOperation(String req) {

		// Check if any keyword exists in the string
		boolean showKeywordFound = showKeywords.stream().anyMatch(req.toLowerCase()::contains);
		if (showKeywordFound) {

			// Show the balance
			showBalance();
			return;
		}

		// Check if any keyword exists in the string
		boolean depositKeywordFound = depositKeywords.stream().anyMatch(req.toLowerCase()::contains);
		if (depositKeywordFound) {

			// Get the amount from the string
			float amount = amountExtractor(req);

			// Deposit the amount
			deposit(amount);
			return;
		}

		// Check if any keyword exists in the string
		boolean withdrawKeywordFound = withdrawKeywords.stream().anyMatch(req.toLowerCase()::contains);
		if (withdrawKeywordFound) {

			// Get the amount
			float amount = amountExtractor(req);

			// Withdraw the amount
			withdraw(amount);
			return;
		}

	}

	@Override
	public void showBalance() {

		System.out.println(balance);

	}

	@Override
	public void deposit(float amnt) {

		// Add the balance
		balance += amnt;
		showBalance();

	}

	@Override
	public void withdraw(float amnt) {

		// Check for negative balance
		if ((balance - amnt) < 0) {
			System.out.println("Insufficient balance for the request.");
			showBalance();
			return;
		}

		// Deduct the balance
		balance -= amnt;
		showBalance();

	}

	public float amountExtractor(String req) {

		// Use regex to find decimals in the string
		// Make use of Java pattern and matcher
		Pattern pattern = Pattern.compile("\\b\\d+(\\.\\d+)?\\b");
		Matcher matcher = pattern.matcher(req);

		while (matcher.find()) {

			// Extract the amount from the string
			String amountStr = matcher.group();
			
			// String to float type casting
			float amount = Float.parseFloat(amountStr);
			return amount;
		}

		return 0.0f;

	}

}
