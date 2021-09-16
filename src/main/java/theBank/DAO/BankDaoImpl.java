package theBank.DAO;

import java.util.ArrayList;
import java.util.List;
import theBank.general.Bank;

public class BankDaoImpl extends BaseDAOImpl implements BankDAO {
	
	@Override
	public Bank getBank(int id) throws Exception {
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("id");
		myListV.add(id);
		for(Object part:getAllItWherePS(
				"Bank", new Bank(), Bank.lookup,"id = ?", myListV, myListT)) {
			return (Bank) part;
		}
		return null;
	}

	@Override
	public Bank getBankByRouting(String routing) throws Exception {
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("routing");
		myListV.add(routing);
		for(Object part:getAllItWherePS(
				"Bank", new Bank(), Bank.lookup,"routing=?", myListV, myListT)) {
			return (Bank) part;
		}
		return null;
	}
	
	@Override
	public List<Bank> getAllBanks() throws Exception {
		List<Bank> toReturn = new ArrayList<>();
		for(Object part:getAllIt(
				"Bank", new Bank(), Bank.lookup)) {
			if(toReturn == null) { toReturn = new ArrayList<>(); }
			toReturn.add((Bank) part);
		}
		return toReturn;
	}

	@Override
	public List<Bank> getAllActiveBanks() throws Exception {
		List<Bank> toReturn = new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		for(Object part:getAllItWherePS(
				"Bank", new Bank(), Bank.lookup,"active=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((Bank) part);
		}
		return toReturn;
	}

	@Override
	public List<Bank> getAllBanksByAddressID(int addressID) throws Exception {
		List<Bank> toReturn = new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		myListT.add("addressID");
		myListV.add(addressID);
		for(Object part:getAllItWherePS(
				"Bank", new Bank(), Bank.lookup,"active=? AND addressId=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((Bank) part);
		}
		return toReturn;
	}

	@Override
	public boolean insertBank(Bank input) throws Exception {
	    List<Bank> aMap = getAllBanks();
	    for(Bank entity:aMap) {
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
		
			myBank = bDao.getBank(1);
			System.out.println("HERE?"+ myBank.getName());
			List<Bank> newB = bDao.getAllActiveBanks();
			newB.forEach(abc -> System.out.println(abc.getName()));
			newB = bDao.getAllBanksByAddressID(1);
			newB.forEach(abc -> System.out.println(abc.getName()));
			System.out.println("HERE?"+ myBank.getName());

		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
