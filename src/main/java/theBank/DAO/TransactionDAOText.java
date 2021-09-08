package theBank.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import theBank.accounts.Account;
import theBank.transactions.Transaction;

import java.util.Set;

public class TransactionDAOText implements TransactionDAO {

	
	public String filePath = "./data/transactions.txt";	
	public String aFilePath = "./data/accounts.txt";
	public String uFilePath = "./data/users.txt";
	
	public AccountDao aDao = new AccountDaoText(aFilePath);
	
	public TransactionDAOText(){
	}	
	
	public TransactionDAOText(String filePath){
		this.filePath = filePath;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	private Map<Integer, Transaction> readTransactions() throws Exception {
		 Map<Integer, Transaction> returnThis = new  HashMap<Integer, Transaction>();

        FileInputStream fis = new FileInputStream(filePath);
        try {
		ObjectInputStream in = new ObjectInputStream(fis);
        returnThis = (Map<Integer, Transaction>) in.readObject();
        }
        catch(Exception e) {
        }
        return returnThis;
    }

    protected Boolean writeAccounts(Map<Integer, Transaction> input) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(input);
            out.flush();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
	@Override
	public Transaction getTransaction(int id) throws Exception {
		Map<Integer, Transaction> allTransactions = readTransactions();
		for(int anId:allTransactions.keySet()) {
			if(anId==id) {
				return allTransactions.get(anId);
			}
		}
		return null;
	}

	@Override
	public Map<Integer, Transaction> getAllTransactions() throws Exception {
		return readTransactions();
	}

	@Override
	public Map<Integer, Transaction> getTransactionsByUser(Integer UserID) throws Exception {
		Map<Integer, Transaction> toReturn = null;
		Map<Integer, Transaction> allTs = readTransactions();
		Set<Account> allAs = aDao.getAccountsByUser(UserID);
		if(allAs==null) {
			return null;
		}
		for(Account anA:allAs) {
			for(Entry<Integer, Transaction> aTransaction:allTs.entrySet()) {
				if(aTransaction.getValue().getAccountID()==anA.getId()) {
					if(toReturn==null) {
						new HashMap<Integer, Transaction>();
					}
					toReturn.put(aTransaction.getKey(), aTransaction.getValue());
				}
			}
		}
		return toReturn;
	}

	@Override
	public Map<Integer, Transaction> getTransactionsByAccount(Integer AccountID) throws Exception {
		Map<Integer, Transaction> toReturn = null;
		Map<Integer, Transaction> allTs = readTransactions();

		for(Entry<Integer, Transaction> aTransaction:allTs.entrySet()) {
			if(toReturn==null) {
				toReturn = new HashMap<Integer, Transaction>();
			}
			toReturn.put(aTransaction.getKey(), aTransaction.getValue());
		}
		
		return toReturn;
	}


	@Override
	public boolean insertTransaction(Transaction transaction) throws Exception {
//		Map<Integer, Transaction> allTs = null;
//		try{
//			allTs = readTransactions();
//		}
//		catch(Exception e) {
//			allTs = new HashMap<Integer, Transaction>();
//		}
//		
//		if(transaction.getId().equals(null) || account.getOwners().equals(null)) {
//			return false;
//		}
		
//		for(Account a :allAccounts) {
//			if(a.getId().equals(account.getId())) {
//				return false;
//			}
//		}
//		allAccounts.add(account);
//		if(writeAccounts(allAccounts)) {
//			return true;
//		}
		return false;
	}

	@Override
	public boolean updateTransaction(Transaction account) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTransaction(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
