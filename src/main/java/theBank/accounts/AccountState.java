package theBank.accounts;

public enum AccountState {
	ENABLED(0),
	PENDING_CONFIRMATION(1),
	PENDING_APPROVAL(2),
	REJECTED(2),
	CLOSED(3);
	private final int value;
	private AccountState(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
