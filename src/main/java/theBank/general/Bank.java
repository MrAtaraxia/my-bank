package theBank.general;

public class Bank {
	int id;
	private String name;
	private Address address;
	private String routingNumber;
	
	Bank(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getRoutingString() {
		return routingNumber;
	}
	
	public int getRoutingNumber() {
		return Integer.parseInt(routingNumber);
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	
	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = String.format("%09d" , routingNumber);
	}
	
}
