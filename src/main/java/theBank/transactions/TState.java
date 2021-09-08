package theBank.transactions;

public enum TState {
	APPROVED("APPROVED"),
	PENDING_CONFIRMATION("PENDING CONFIRMATION"),
	PENDING_APPROVAL("PENDING APPROVAL"),
	REJECTED("REJECTED"),
	CLOSED("CLOSED");
	private final String value;
	private TState(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
