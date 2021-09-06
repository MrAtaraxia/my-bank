package theBank;

public enum UserType {
	NONE(0),
	CUSTOMER(1),
	EMPLOYEE(2),
	ADMIN(3);
	private final int value;
	private UserType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
