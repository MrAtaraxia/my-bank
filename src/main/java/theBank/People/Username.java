package theBank.People;

import java.time.LocalDateTime;

public class Username {
	private static int nextId = 1;
	private int id;
	private boolean active;
	private int personID;
	private String username;
	private String pass;
	private UType type;
	private LocalDateTime created;
	private LocalDateTime modified;
	
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
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public UType getType() {
		return type;
	}
	public void setType(UType type) {
		this.type = type;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
}
