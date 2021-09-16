package theBank.DAO;

import java.util.ArrayList;
import java.util.List;

import theBank.accounts.AState;
import theBank.accounts.AType;
import theBank.accounts.Account;

public class AccountDaoImpl extends BaseDAOImpl implements AccountDao {

	@Override
	public Account getAccount(Integer id) throws Exception {
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("id");
		myListV.add(id);
		for(Object part:getAllItWherePS(
				"Account", new Account(), Account.lookup,"id = ?", myListV, myListT)) {
			return (Account) part;
		}
		return null;
	}

	@Override
	public List<Account> getAllAccounts() throws Exception {
		List<Account> toReturn =  new ArrayList<>();
		for(Object part:getAllIt(
				"Account", new Account(), Account.lookup)) {
			if(toReturn == null) { toReturn = new ArrayList<>(); }
			toReturn.add( (Account) part);
		}
		return toReturn;
	}

	@Override
	public List<Account> getAllActiveAccounts() throws Exception {
		List<Account> toReturn = new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		for(Object part:getAllItWherePS(
				"Account", new Account(), Account.lookup,"active=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((Account) part);
		}
		return toReturn;
	}

	@Override
	public List<Account> getAllNeedApprovalAccounts() throws Exception {
		List<Account> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("astate");
		myListV.add(AState.PENDING);
		for(Object part:getAllItWherePS(
				"Account", new Account(), Account.lookup,"astate=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Account) part);
		}
		return toReturn;
	}

	@Override
	public List<Account> getAccountsByState(AState astate) throws Exception {
		List<Account> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("astate");
		myListV.add(astate);
		for(Object part:getAllItWherePS(
				"Account", new Account(), Account.lookup,"astate=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Account) part);
		}
		return toReturn;
	}

	@Override
	public List<Account> getActiveAccountsByState(AState astate) throws Exception {
		List<Account> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		myListT.add("astate");
		myListV.add(astate);
		for(Object part:getAllItWherePS(
				"Account", new Account(), Account.lookup,"active=? and astate=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Account) part);
		}
		return toReturn;
	}

	@Override
	public List<Account> getAccountsByType(AType atype) throws Exception {
		List<Account> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("atype");
		myListV.add(atype);
		for(Object part:getAllItWherePS(
				"Account", new Account(), Account.lookup,"atype=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Account) part);
		}
		return toReturn;
	}

	@Override
	public List<Account> getActiveAccountsByType(AType atype) throws Exception {
		List<Account> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		myListT.add("atype");
		myListV.add(atype);
		for(Object part:getAllItWherePS(
				"Account", new Account(), Account.lookup,"active=? and atype=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Account) part);
		}
		return toReturn;
	}
	
	@Override
    public List<Account> getAccountsByAccountIDs(List<Integer> accountIDs) throws Exception{
		List<Account> toReturn =  new ArrayList<>();
		for(Integer accNum:accountIDs) {
			Account curAcc = getAccount(accNum);
			if(curAcc!=null) {
				if(toReturn==null) { toReturn = new ArrayList<>(); }
				toReturn.add(curAcc);
			}
		}
		return toReturn;
	}

	@Override
	public Boolean insertAccount(Account input) throws Exception {
	    List<Account> aThing = getAllAccounts();
	    for(Account entity:aThing) {
	    	if(entity.getBankID().equals(input.getBankID()))
	    		if(entity.getAccountNumber().equals(input.getAccountNumber()))
	    			if(entity.getAtype().equals(input.getAtype()))
	    				{ System.out.println("Duplicate"); return null; }
	    }
	    if(insertIt("Account",input,Account.lookup)) {
	    	List<Account>aThing2= getAllAccounts();
	    	for(Account ath:aThing2) {
	    		if(!aThing.contains(ath)) {
	    			return true;
	    		}
	    	}
	    }
	    return false;
	}

	@Override
	public Boolean updateAccount(Account input) throws Exception {
		return updateIt("Account", input, Account.lookup);
	}

	@Override
	public Boolean activateAccount(Integer id) throws Exception {
		return activateIt("Account", new Account(), Account.lookup, id);
	}

	@Override
	public Boolean deactivateAccount(Integer id) throws Exception {
		return deactivateIt("Account", new Account(), Account.lookup, id);
	}

	@Override
	public Boolean deleteAccount(Integer id) throws Exception {
		return deleteIt("Account", id);
	}

	@Override
	public Boolean CancelAccount(Integer id) throws Exception {
		Account curr = getAccount(id);
		if(curr!= null) {
			curr.setAstate(AState.CANCELED);
			curr.setActive(false);
			updateAccount(curr);
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		AccountDaoImpl acDao = new AccountDaoImpl();
		Account myAcc = new Account();
		myAcc.setBankID(5);
		List<Account> pers = acDao.getAllAccounts();
		for(Account ap:pers) {
			System.out.println(ap.getId());
		}
	}


}
