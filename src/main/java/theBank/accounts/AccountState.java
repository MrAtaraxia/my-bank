package theBank.accounts;

public enum AccountState {
	ENABLED("ENABLED"),
	PENDING_CONFIRMATION("PENDING CONFIRMATION"),
	PENDING_APPROVAL("PENDING APPROVAL"),
	REJECTED("REJECTED"),
	CLOSED("CLOSED");
	private final String value;
	private AccountState(String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
}
