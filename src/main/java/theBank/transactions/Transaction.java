package theBank.transactions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import theBank.general.Extractor;

public class Transaction {
	public static Integer nextId = 1;
	private Integer id;
	private Integer AccountLocalID;
	private String RoutingLocalNum;
	private Integer PersonID;
//	private Integer AccountExID;
//	private String RoutingExNum;
	private String Description;
	private Double Addition;
	private Double Subtraction;
	private Double Balance;
	private TState tstate;
	private TType ttype;
	private Boolean taxable=true;
	private LocalDateTime created;
	private LocalDateTime modified;
	
	public Transaction(){
		this(nextId);
	}
	
	Transaction(Integer id){
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}
	
	public boolean setId(Integer id) {
		if(id <=0) {
			return false;
		}
		if(id >= nextId) {
			nextId = id+1;
		}
		this.id = id;
		return true;
	}
	
	public Integer getAccountLocalID() {
		return AccountLocalID;
	}

	public void setAccountLocalID(Integer accountLocalID) {
		AccountLocalID = accountLocalID;
	}

	public String getRoutingLocalNum() {
		return RoutingLocalNum;
	}

	public void setRoutingLocalNum(String routingLocalID) {
		RoutingLocalNum = routingLocalID;
	}

	public Integer getPersonID() {
		return PersonID;
	}

	public void setPersonID(Integer personID) {
		PersonID = personID;
	}
//
//	public Integer getAccountExID() {
//		return AccountExID;
//	}
//
//	public void setAccountExID(Integer accountExID) {
//		AccountExID = accountExID;
//	}

//	public String getRoutingExNum() {
//		return RoutingExNum;
//	}
//
//	public void setRoutingExNum(String routingExID) {
//		RoutingExNum = routingExID;
//	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Double getAddition() {
		return Addition;
	}

	public void setAddition(Double addition) {
		Addition = addition;
	}

	public Double getSubtraction() {
		return Subtraction;
	}

	public void setSubtraction(Double subtraction) {
		Subtraction = subtraction;
	}

	public Double getBalance() {
		return Balance;
	}

	public void setBalance(Double balance) {
		Balance = balance;
	}

	public TState getTState() {
		return tstate;
	}

	public void setTState(TState cashWithdrawl) {
		this.tstate = cashWithdrawl;
	}

	public TType getTType() {
		return ttype;
	}

	public void setTType(TType ttype) {
		this.ttype = ttype;
	}

	public Boolean getTaxable() {
		return taxable;
	}

	public void setTaxable(Boolean taxable) {
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


	public static Map<String, Extractor> lookup = new HashMap<>();
	private static List<String[]> mod_names = new ArrayList<>();
	private static List<Method> methods = new ArrayList<>();
	private static List<String[]> getters = new ArrayList<>();
	private static List<String[]> setters = new ArrayList<>();
	private static List<String> f_getters = new ArrayList<>();
	private static List<String> f_setters = new ArrayList<>();
	
	@SuppressWarnings("rawtypes")
	private static Class aclass = Transaction.class;		
	//System.out.println(aclass.getDeclaredFields());

	static {
		List<String[]> names = new ArrayList<>();
		
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
//		for(String[] afield : mod_names)
//		{
//			if(!afield[1].equalsIgnoreCase("static")) {
//				for(int i = 0; i < afield.length;i++) {
//					System.out.print(afield[i] +" " +i + " ");
//				}
//				System.out.println();
//			}	
//		}		
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


//	static
//    {
//        for(Field afield : aclass.getFields())
//        {
//            names.add(afield.toString());
//            System.out.println(afield);
//        }
//        
//    }

}