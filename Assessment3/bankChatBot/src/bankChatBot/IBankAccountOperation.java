package bankChatBot;

public interface IBankAccountOperation {

	// Abstract methods for bank operations
	public void showBalance();

	public void deposit(float amount);

	public void withdraw(float amount);

	public void processOperation(String req);
}
