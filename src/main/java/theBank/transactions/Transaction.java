package theBank.transactions;

import java.util.Calendar;
//import java.util.Date;

public class Transaction {
	public static int next_id = 1;
	private int id;
	private int AccountID;
	private int month;
	private int day;
	private String description;
	private double addition;
	private double subtraction;
	private double balance;
	private TState state;
	
	
	
	Transaction(){
		this(next_id);
		
	}
	
	Transaction(int id){
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id >= next_id) {
			next_id = id+1;
		}
		this.id = id;
	}

	public int getAccountID() {
		return AccountID;
	}

	public void setAccountID(int accountID) {
		AccountID = accountID;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth() {
		Calendar myCalendar = Calendar.getInstance();
		this.setMonth(myCalendar.get(Calendar.MONTH));
	}
	
	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay() {
		Calendar myCalendar = Calendar.getInstance();
		this.setDay(myCalendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public void setDay(int day) {
		this.day = day;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAddition() {
		return addition;
	}

	public void setAddition(double addition) {
		this.addition = addition;
	}

	public double getSubtraction() {
		return subtraction;
	}

	public void setSubtraction(double subtraction) {
		this.subtraction = subtraction;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public TState getState() {
		return state;
	}

	public void setState(TState state) {
		this.state = state;
	}
}
