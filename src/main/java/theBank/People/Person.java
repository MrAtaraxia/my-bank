package theBank.People;

import java.io.Serializable;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.IvParameterSpec;

import org.apache.logging.log4j.Logger;

import theBank.general.*;

import org.apache.logging.log4j.LogManager;

/**
 * User Class
 * @author w3
 */

public class Person implements Serializable {
	private static final Logger logger = LogManager.getLogger(Person.class);

	public static Integer nextId = 1;
	private static final long serialVersionUID = -2698235604427808725L;
	private Integer id;
    private boolean active;
	private String fname;
    private String lname;
    //private String encryptedPass;
    private String email;
    private double withdrawn;
    private Address address;
    

    public Person(Integer id, String fname, String lname, 
    		String username, String pass, String email, UType type) {
    	//logger.info("User param constructor.");
    	this.setId(id);
    	this.setActive(true);
    	this.setFname(fname);
    	this.setLname(lname);
    	this.email = email;
    	this.withdrawn=0.0d;
    }

    public Person() {
    	//logger.info("User default constructor.");
    	this.id = nextId;
    	nextId++;
    	this.withdrawn=0.0d;
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
    
    public String getFname() {
		return fname;
	}
    
	public void setFname(String fname) {
		this.fname = fname;
	}

	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public double getWithdrawn() {
		return withdrawn;
	}

	public void setWithdrawn(double withdrawn) {
		this.withdrawn = withdrawn;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}