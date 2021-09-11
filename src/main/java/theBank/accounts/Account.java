package theBank.accounts;

import java.io.Serializable;

import theBank.People.UType;

public class Account implements Serializable{
	
	public static Integer nextId = 1;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6579968071499579017L;
	private Integer id;
	private Double balance;
	private Integer[] ownerIDs;
	private Boolean enabled;
	private Boolean approved;
	private AType type;
	
	public Account(){
		this.id = nextId;
		this.balance = 0.0d;
		this.enabled = true;
		this.approved = false;
		this.type = AType.INDIVIDUAL;
		nextId++;
	}
	
	Account(Integer id, Integer[] owners, AType type){
		this.id = id;
		this.ownerIDs = owners;
		this.balance = 0.0d;
		this.enabled = true;
		this.approved = false;
		this.type = type;
	}
	
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

	public Double getBalance() {
		return balance;
	}

	public boolean setBalance(Double balance) {
		if(balance < 0) {
			return false;
		}
		this.balance = balance;
		return true;
	}

	public Integer[] getOwners() {
		return ownerIDs;
	}

	public void setOwners(Integer[] owners) {
		this.ownerIDs = owners;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved, UType utype) {
		if(utype == UType.EMPLOYEE || utype == UType.ADMIN) {
			this.approved = approved;
		}
	}

	public AType getType() {
		return type;
	}

	public void setType(AType type) {
		this.type = type;
	}

}
