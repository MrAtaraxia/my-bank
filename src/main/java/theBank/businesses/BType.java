package theBank.businesses;

public enum BType {
	SOLE_PROPRIETORSHIP("SOLE PROPRIETORSHIP"),
	SINGLE_MEMBER_LLC("SINGLE-MEMBER LLC"),
	PENDING_APPROVAL("PENDING APPROVAL"),
	REJECTED("REJECTED"),
	CLOSED("CLOSED");
	private final String value;
	private BType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
