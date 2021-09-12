package theBank.DAO;

import java.util.HashMap;
import java.util.Map;

import theBank.general.Bank;

public class BankDaoImpl extends BaseDAOImpl implements BankDAO {
	
	@Override
	public Bank getBank(int id) throws Exception {
		for(var part:getAllItWhere(
				"Bank", new Bank(), Bank.lookup,"id="+id).entrySet()) {
			return (Bank) part.getValue();
		}
		return null;
	}

	@Override
	public Bank getBankByRouting(String routing) throws Exception {
		for(var part:getAllItWhere(
				"Bank", new Bank(), Bank.lookup,"routing="+routing).entrySet()) {
			return (Bank) part.getValue();
		}
		return null;
	}
	
	@Override
	public Map<Integer, Bank> getAllBanks() throws Exception {
		Map<Integer, Bank> toReturn = null;
		for(var part:getAllIt(
				"Bank", new Bank(), Bank.lookup).entrySet()) {
			if(toReturn == null) { toReturn = new HashMap<>(); }
			toReturn.put(part.getKey(), (Bank) part.getValue());
		}
		return toReturn;
	}

	@Override
	public Map<Integer, Bank> getAllActiveBanks() throws Exception {
		Map<Integer, Bank> toReturn = null;
		for(var part:getAllItWhere(
				"Bank", new Bank(), Bank.lookup,"active=true"
				).entrySet()) {
			if(toReturn == null) { toReturn = new HashMap<>(); }
			toReturn.put(part.getKey(), (Bank) part.getValue());
		}
		return toReturn;
	}

	@Override
	public Map<Integer, Bank> getAllBanksByAddressID(int addressID) throws Exception {
		Map<Integer, Bank> toReturn = null;
		//System.out.println("By AddressID");
		for(var part:getAllItWhere(
				"Bank", new Bank(), Bank.lookup,"active=true AND addressID=" + addressID
				).entrySet()) {
			if(toReturn == null) { toReturn = new HashMap<>(); }
			toReturn.put(part.getKey(), (Bank) part.getValue());
		}
		return toReturn;
	}

	@Override
	public boolean insertBank(Bank input) throws Exception {
	    Map<Integer, Bank> aMap = getAllBanks();
	    for(Bank entity:aMap.values()) {
	    	if(entity.getAddressID().equals(input.getAddressID()))
	    		if(entity.getName().equals(input.getName()))
	    			if(entity.getRouting().equals(input.getRouting()))
	    				{ System.out.println("Duplicate"); return false; }
	    }
	    return insertIt("bank",input,Bank.lookup);
	}

	@Override
	public boolean updateBank(Bank input) throws Exception {
		return updateIt("bank", input, Bank.lookup);
	}

	@Override
	public boolean activateBank(int id) throws Exception {
		return activateIt("bank", new Bank(), Bank.lookup, id);
	}

	@Override
	public boolean deactivateBank(int id) throws Exception {
		return deactivateIt("bank", new Bank(), Bank.lookup, id);
	}

	@Override
	public boolean deleteBank(int id) throws Exception {
		return deleteIt("bank", id);
	}
	
	public static void main(String[] args) throws Exception {
		BankDaoImpl bDao = new BankDaoImpl();
		try {
		Bank myBank = new Bank();
//		Class aclass = Bank.class;
//		Method[] methos = aclass.getMethods();
//		Field[] theFields = aclass.getFields();
//		for(Field aField:theFields) {
//			System.out.println(aField.getName() +" " + aField.getType());
//		}
		//bDao.getIt("bank",5);
		
		myBank.setName("BABank");
		myBank.setAddressID(1);
		myBank.setRouting("232288899");
		myBank.setTotalFunds(0.0d);
		System.out.println("HERE?");
		myBank.setId(5);
		
		System.out.println("HERE?ABC" + myBank.getAddressID());
		System.out.println("AGAIN!");
		Map<Integer, Bank> theBanks = bDao.getAllBanksByAddressID(1);
		for(var eBank:theBanks.entrySet()) {
			myBank = eBank.getValue();
			System.out.println(eBank.getValue().getName() + " " + eBank.getValue().getId());
			break;
		}
		myBank.setName("NEW NAME");
		bDao.activateBank(myBank.getId());
		bDao.deactivateBank(myBank.getId());
		//bDao.insertBank(myBank);
		//bDao.activateIt(1,myBank, Bank.lookup);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
