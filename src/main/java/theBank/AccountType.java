package theBank;

public enum AccountType {
	JOINT(0),
	INDIVIDUAL(1),
	SAVINGS(2),
	CHECKING(3);
	private final int value;
	private AccountType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
