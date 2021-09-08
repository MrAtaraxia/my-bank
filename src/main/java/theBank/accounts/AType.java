package theBank.accounts;

public enum AType {
	JOINT("JOINT"),
	INDIVIDUAL("INDIVIDUAL"),
	SAVINGS("SAVINGS"),
	CHECKING("CHECKING"),
	BUSINESS_SAVINGS("BUSINESS SAVINGS"),
	BUSINESS_CHECKING("BUSINESS CHECKING"),
	MERCHANTS_ACCOUNT("MERCHANTS ACCOUNT");
	private final String value;
	private AType(String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
}
