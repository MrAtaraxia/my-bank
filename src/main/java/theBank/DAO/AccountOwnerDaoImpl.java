package theBank.DAO;

import java.util.ArrayList;
import java.util.List;

import theBank.People.Username;
import theBank.accounts.AccountOwner;

public class AccountOwnerDaoImpl extends BaseDAOImpl implements AccountOwnerDao {

	@Override
	public List<AccountOwner> getAOs(int id) throws Exception {
		List<AccountOwner> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("id");
		myListV.add(id);
		for(var part:getAllItWherePS(
				"AccountOwner", new AccountOwner(), AccountOwner.lookup,"id=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((AccountOwner) part);
		}
		return toReturn;
	}

	@Override
	public List<AccountOwner> getAllAOs() throws Exception {
		List<AccountOwner> toReturn = null;
		for(var part:getAllIt(
				"AccountOwner", new AccountOwner(), AccountOwner.lookup).entrySet()) {
			if(toReturn == null) { toReturn = new ArrayList<>(); }
			toReturn.add((AccountOwner) part.getValue());
		}
		return toReturn;
	}

	@Override
	public List<AccountOwner> getAllActiveAOs() throws Exception {
		List<AccountOwner> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		for(var part:getAllItWherePS(
				"AccountOwner", new AccountOwner(), AccountOwner.lookup,"active=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((AccountOwner) part);
		}
		return toReturn;
	}


	@Override
	public List<AccountOwner> getAllAOsByUserID(Integer UserID) throws Exception {
		List<AccountOwner> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("UserID");
		myListV.add(UserID);
		for(var part:getAllItWherePS(
				"AccountOwner", new AccountOwner(), AccountOwner.lookup,"UserID=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((AccountOwner) part);
		}
		return toReturn;
	}

	@Override
	public List<AccountOwner> getAllActiveAOsByUserID(Integer UserID) throws Exception {
		List<AccountOwner> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		myListT.add("UserID");
		myListV.add(UserID);
		for(var part:getAllItWherePS(
				"AccountOwner", new AccountOwner(), AccountOwner.lookup,"active=? AND UserID=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((AccountOwner) part);
		}
		return toReturn;
	}
	
	@Override
	public List<AccountOwner> getAllAOsByAccountID(Integer AccountID) throws Exception {
		List<AccountOwner> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("AccountID");
		myListV.add(AccountID);
		for(var part:getAllItWherePS(
				"AccountOwner", new AccountOwner(), AccountOwner.lookup,"active=? AND UserID=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((AccountOwner) part);
		}
		return toReturn;
	}

	@Override
	public List<AccountOwner> getAllActiveAOsByAccountID(Integer AccountID) throws Exception {
		List<AccountOwner> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		myListT.add("AccountID");
		myListV.add(AccountID);
		for(var part:getAllItWherePS(
				"AccountOwner", new AccountOwner(), AccountOwner.lookup,"active=? AND UserID=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((AccountOwner) part);
		}
		return toReturn;
	}
	
	@Override
	public List<Integer> getAllAccountIDsByAOs(List<AccountOwner> accMap) throws Exception {
		List<Integer> toReturn = null;
		for(AccountOwner ao: accMap) {
			if(toReturn == null) {
				toReturn = new ArrayList<>();
			}
			toReturn.add(ao.getAccountID());
		}
		return toReturn;
	}

	@Override
	public List<Integer> getAllPersonIDsByAOs(List<AccountOwner> accMap) throws Exception {
		List<Integer> toReturn = null;
		for(AccountOwner ao: accMap) {
			if(toReturn == null) {
				toReturn = new ArrayList<>();
			}
			toReturn.add(ao.getPersonID());
		}
		return toReturn;
	}

	@Override
	public boolean insertAO(AccountOwner ao) throws Exception {
		List<AccountOwner> aMap = getAllAOs();
	    for(AccountOwner entity:aMap) {
	    	if(entity.getAccountID().equals(ao.getAccountID()))
	    		if(entity.getPersonID().equals(ao.getPersonID()))
	    			{ System.out.println("Duplicate"); return false; }
	    }
	    return insertIt("AccountOwner",ao,AccountOwner.lookup);
	}

	@Override
	public boolean updateAO(AccountOwner input) throws Exception {
		return updateIt("AccountOwner", input, AccountOwner.lookup);
	}

	@Override
	public boolean activateAO(int id) throws Exception {
		return activateIt("AccountOwner", new AccountOwner(), AccountOwner.lookup, id);
	}


	@Override
	public boolean deactivateAO(int id) throws Exception {
		return deactivateIt("AccountOwner", new AccountOwner(), AccountOwner.lookup, id);
	}

	@Override
	public boolean deleteAO(int id) throws Exception {
		return deleteIt("AccountOwner", id);
	}


}
