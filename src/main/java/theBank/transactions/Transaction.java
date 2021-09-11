package theBank.transactions;

import java.time.LocalDateTime;
import java.util.Calendar;
//import java.util.Date;

public class Transaction {
	public static int nextId = 1;
	private int id;
	private int AccountLocalID;
	private int RoutingLocalID;
	private int PersonID;
	private int AccountExID;
	private int RoutingExID;
	private String Description;
	private int Addition;
	private int Subtraction;
	private int Balance;
	private TState state;
	private TType type;
	private boolean taxable;
	private LocalDateTime created;
	private LocalDateTime modified;
	
	Transaction(){
		this(nextId);
	}
	
	Transaction(int id){
		this.setId(id);
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
	
	public int getAccountLocalID() {
		return AccountLocalID;
	}

	public void setAccountLocalID(int accountLocalID) {
		AccountLocalID = accountLocalID;
	}

	public int getRoutingLocalID() {
		return RoutingLocalID;
	}

	public void setRoutingLocalID(int routingLocalID) {
		RoutingLocalID = routingLocalID;
	}

	public int getPersonID() {
		return PersonID;
	}

	public void setPersonID(int personID) {
		PersonID = personID;
	}

	public int getAccountExID() {
		return AccountExID;
	}

	public void setAccountExID(int accountExID) {
		AccountExID = accountExID;
	}

	public int getRoutingExID() {
		return RoutingExID;
	}

	public void setRoutingExID(int routingExID) {
		RoutingExID = routingExID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getAddition() {
		return Addition;
	}

	public void setAddition(int addition) {
		Addition = addition;
	}

	public int getSubtraction() {
		return Subtraction;
	}

	public void setSubtraction(int subtraction) {
		Subtraction = subtraction;
	}

	public int getBalance() {
		return Balance;
	}

	public void setBalance(int balance) {
		Balance = balance;
	}

	public TState getState() {
		return state;
	}

	public void setState(TState state) {
		this.state = state;
	}

	public TType getType() {
		return type;
	}

	public void setType(TType type) {
		this.type = type;
	}

	public boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
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
