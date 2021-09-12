package theBank.general;

public class Address {
	private static int nextId = 1;
	private int id;
	private boolean active=true;
	private String street;
	private String zipcode;
	private State state;
	private String city;


	public int getId() {
		return id;
	}

	public boolean setId() {
		return setId(nextId);
	}
	
	public boolean setId(int id) {
		if(id <=0) {
			return false;
		}
		if(id >= nextId) {
			nextId = id+1;
		}
		this.id = id;
		return true;
	}
	
	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(int zipcode) {
		this.zipcode = String.format("%05d" , zipcode);;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
