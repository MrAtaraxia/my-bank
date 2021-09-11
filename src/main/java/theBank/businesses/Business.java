package theBank.businesses;

import theBank.People.Person;
import theBank.general.Address;

public class Business {
	private static int nextId = 1;
	private int id;
	Person owner;
	String name;
	int EIN;
	String type;
	Address address;
	
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
}
