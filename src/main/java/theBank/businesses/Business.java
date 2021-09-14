package theBank.businesses;

import theBank.People.Person;
import theBank.general.Address;

public class Business {
	private static int nextId = 1;
	private int id;
	private boolean active = true;
	Person owner;
	String name;
	int EIN;
	BType btype;
	Address address;
	
	public Business() {
		setId(nextId);
	}
	
	public int getId() {
		return id;
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
}
