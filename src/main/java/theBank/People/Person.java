package theBank.People;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

import theBank.general.Extractor;

/**
 * Person Class
 * @author w3
 */

public class Person implements Serializable {
	//private static final Logger logger = LogManager.getLogger(Person.class);

	public static Integer nextId = 1;
	private static final long serialVersionUID = -2698235604427808725L;
	private Integer id = nextId;
    private boolean active = true;
	private String fname;
    private String lname;
    //private String encryptedPass;
    private String email;
    private double withdrawn;
    private Integer addressID;
    private LocalDateTime created;
    private LocalDateTime modified;
    

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
    	setId(nextId);
    	this.id = nextId;
    	nextId++;
    	this.withdrawn=0.0d;
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

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getAddressID() {
		return addressID;
	}

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
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

	public static Map<String, Extractor> lookup = new HashMap<>();
	private static List<String[]> mod_names = new ArrayList<>();
	private static List<Method> methods = new ArrayList<>();
	private static List<String[]> getters = new ArrayList<>();
	private static List<String[]> setters = new ArrayList<>();
	private static List<String> f_getters = new ArrayList<>();
	private static List<String> f_setters = new ArrayList<>();
	private static List<String[]> names = new ArrayList<>();
	@SuppressWarnings("rawtypes")
	private static Class aclass = Person.class;		
	//System.out.println(aclass.getDeclaredFields());

	static {
		
		for(Field afield : aclass.getDeclaredFields())
		{
			names.add(afield.toString().split(" "));
		}
		//int i = 0;
		for(String[] afield : names)
		{
			if(!afield[1].equalsIgnoreCase("static")) {
				String curType =null;
				if(afield[1].startsWith("java")) {
					curType = afield[1].substring(afield[1].lastIndexOf(".")+1);
				} else {
					curType = afield[1];
				}
				String curId = afield[2].substring(afield[2].lastIndexOf(".")+1);
				String[] currStr = {afield[0],curType,curId};
				mod_names.add(currStr);
			}
		}
		for(Method meth:aclass.getDeclaredMethods()) {
			methods.add(meth);
			if(meth.getName().startsWith("get")){
				f_getters.add(meth.toString());
			}			
			if(meth.getName().startsWith("set")){
				if(!meth.getName().endsWith("()")){
					f_setters.add(meth.toString());
				}
			}
		}
		
		for(String afield:f_getters) 
		{
			int openIndex = afield.lastIndexOf("(");
			//int closeIndex = afield.lastIndexOf(")");
			int firstSpace = afield.indexOf(" ");
			int secondSpace = afield.indexOf(" ",firstSpace+1);
			int firstp = afield.indexOf(".");
			int secondp = afield.indexOf(".",firstp+1);
			//int pLast = afield.lastIndexOf(".");
			int pBefore = afield.lastIndexOf(".",openIndex);
			String currPart = null;
			if(secondp > secondSpace) {
				currPart = afield.substring(firstSpace+1,secondSpace);
			}else {
				currPart = afield.substring(secondp+1,secondSpace);
			}
			//System.out.println(currPart);
			String[] currStr = {afield.substring(0,firstSpace),
					afield.substring(pBefore+1,openIndex),
					currPart
					};
			getters.add(currStr);
		}
		for(String afield:f_setters) 
		{
			if(!afield.endsWith("()")) {
				int openIndex = afield.lastIndexOf("(");
				int closeIndex = afield.lastIndexOf(")");
				int firstSpace = afield.indexOf(" ");
				int pLast = afield.lastIndexOf(".");
				int pBefore = afield.lastIndexOf(".",openIndex);
				String currPart = null;
				if(pLast != pBefore) {
					currPart = afield.substring(pLast+1,closeIndex);
				}else {
					currPart = afield.substring(openIndex+1,closeIndex);
				}
				//System.out.println(currPart);
				String[] currStr = {afield.substring(0,firstSpace),
						afield.substring(pBefore+1,openIndex),
						currPart
						};
				setters.add(currStr);
			}
		}
		for(String[] nfield : mod_names){
			Extractor extra = new Extractor();
			extra.name = nfield[2];
			extra.nType = nfield[1];
			extra.nAccess = nfield[0];
			
			for(String[] gfield : getters) {
				if(gfield[1].equalsIgnoreCase("get"+extra.name)) {
					extra.getter=gfield[1];
					extra.gType =gfield[2];
					extra.gAccess=gfield[0];
					break;
				}
			}
			for(String[] sfield : setters) {
				if(sfield[1].equalsIgnoreCase("set"+extra.name)) {
					extra.setter=sfield[1];
					extra.sType =sfield[2];
					extra.sAccess=sfield[0];
					break;
				}
			}
			lookup.put(extra.name, extra);
		}
		for(Method meth:methods) {
			//System.out.println(meth.getName());
			for(Entry<String, Extractor> anEnt :lookup.entrySet()) {
				if(meth.getName().equalsIgnoreCase(anEnt.getValue().getter)) {
					anEnt.getValue().gMethod = meth;
					break;
				}
				else if(meth.getName().equalsIgnoreCase(anEnt.getValue().setter)) {
					anEnt.getValue().sMethod = meth;
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("OMG!");
		for(Field afield : aclass.getDeclaredFields())
		{
			System.out.println(afield.toString());
		}

		System.out.println("AFTER OMG!");
		for(String[] afield : names)
		{
			if(!afield[1].equalsIgnoreCase("static")) {
				for(int i = 0; i < afield.length;i++) {
					System.out.print(afield[i] +" " +i + " ");
				}
				System.out.println();
			}
		}
		System.out.println("After NAMES");
//
//		System.out.println("GETTERS:");
//		for(String[] afield : getters)
//		{
//			for(int i = 0; i < afield.length;i++) {
//				System.out.print(afield[i] +" " +i + " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("SETTERS:");
//		for(String[] afield : setters)
//		{
//			for(int i = 0; i < afield.length;i++) {
//				System.out.print(afield[i] +" " +i + " ");
//			}
//			System.out.println();
//		}
		System.out.println("EXTRACTOR:");
		for(Entry<String, Extractor> afield : lookup.entrySet())
		{
			System.out.println(afield.getKey()+" " + afield.getValue().nType+" " + 
		afield.getValue().setter+" " + afield.getValue().sMethod + " " + 
		afield.getValue().getter+" " + afield.getValue().gMethod);
		}
	}

}