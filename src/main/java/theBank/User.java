package theBank;

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
import org.apache.logging.log4j.LogManager;

/**
 * User Class
 * @author w3
 */

public class User implements Serializable {
	private static final Logger logger = LogManager.getLogger(User.class);

	protected static Integer nextId = 20;
	private static final long serialVersionUID = -2698235604427808725L;
	private Integer id;
    private String fname;
    private String lname;
    private String username;
    private String pass;
    //private String encryptedPass;
    private String email;
    private UserType type;
    private double withdrawn;
    
    

    public User(Integer id, String fname, String lname, 
    		String username, String pass, String email, UserType type) {
    	//logger.info("User param constructor.");
    	this.id = id;
    	this.fname = fname;
    	this.lname = lname;
    	this.username = username;
    	this.pass = pass;
    	this.email = email;
    	this.type = type;
    	this.withdrawn=0.0d;
    }

    public User() {
    	//logger.info("User default constructor.");
    	this.id = nextId;
    	nextId++;
    	this.withdrawn=0.0d;
    }


    public Integer getId() {
        return id;
    }

    public boolean setId(Integer id) {
    	if(id<=0) {
    		return false;
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

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


    public String getPass() {
//    	String algorithm = "AES/CBC/PKCS5Padding";
//    	SecretKey myKey = Encryption.generateKey(100);
//    	IvParameterSpec ivParameterSpec = Encryption.generateIv();
//    	if(this.encryptedPass!=null) {
//	    	String unEncrypted = null;
//	        try {
//				 unEncrypted = Encryption.decrypt(algorithm, this.encryptedPass, myKey, ivParameterSpec);
//			} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
//					| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
//				e.printStackTrace();
//			}
//	        	System.out.print("Unencrypted:" + unEncrypted + ":Pass:" + pass + "\n");
//	        	logger.info("Encrypted:"+this.encryptedPass+"-Unencrypted:" + unEncrypted + "-Pass:" + pass + "\n");
//    	}
        	return pass;
    }

    public void setPass(String pass) {
//    	String algorithm = "AES/CBC/PKCS5Padding";
//    	SecretKey myKey = Encryption.generateKey(100);
//    	IvParameterSpec ivParameterSpec = Encryption.generateIv();
        this.pass = pass;
//		try {
//			this.encryptedPass = Encryption.encrypt(algorithm, pass, myKey, ivParameterSpec);
//			logger.info("Encrypted:"+this.encryptedPass+ "-Pass:" + pass + "\n");
//		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
//				| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
//			e.printStackTrace();
//		}
		
    }

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public UserType getType() {
		//logger.trace("User getType.");
		return type;
	}

	public void setType(UserType type) {
		//logger.trace("User setType.");
		this.type = type;
	}

	public double getWithdrawn() {
		return withdrawn;
	}

	public void setWithdrawn(double withdrawn) {
		this.withdrawn = withdrawn;
	}

}