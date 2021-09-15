package theBank.DAO;

import java.util.ArrayList;
import java.util.List;

import theBank.accounts.Account;
import theBank.transactions.TState;
import theBank.transactions.TType;
import theBank.transactions.Transaction;

public class TransactionDaoImpl extends BaseDAOImpl implements TransactionDAO {

	@Override
	public Transaction getTransaction(int id) throws Exception {
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("id");
		myListV.add(id);
		for(var part:getAllItWherePS(
				"Transactions", new Transaction(), Transaction.lookup,"id = ?", myListV, myListT)) {
			return (Transaction) part;
		}
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() throws Exception {
		List<Transaction> toReturn =  new ArrayList<>();
		for(var part:getAllIt(
				"Transactions", new Transaction(), Transaction.lookup).entrySet()) {
			if(toReturn == null) { toReturn = new ArrayList<>(); }
			toReturn.add((Transaction) part.getValue());
		}
		return toReturn;
	}

	@Override
	public  List<Transaction> getTransactionsByPersonID(Integer PersonID) throws Exception {
		List<Transaction> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("personID");
		myListV.add(PersonID);
		for(var part:getAllItWherePS(
				"Transactions", new Transaction(), Transaction.lookup,"PersonID=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Transaction) part);
		}
		return toReturn;
	}


	@Override
	public  List<Transaction> getTransactionsByAccountID(Integer AccountID) throws Exception {
		List<Transaction> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("accountID");
		myListV.add(AccountID);
		for(var part:getAllItWherePS(
				"Transactions", new Transaction(), Transaction.lookup,"AccountID=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Transaction) part);
		}
		return toReturn;
	}

	@Override
	public  List<Transaction> getTransactionsByTState(TState tstate) throws Exception {
		List<Transaction> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("tState");
		myListV.add(tstate);
		for(var part:getAllItWherePS(
				"Transactions", new Transaction(), Transaction.lookup,"TState=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Transaction) part);
		}
		return toReturn;
	}

	@Override
	public  List<Transaction> getTransactionsByTType(TType ttype) throws Exception {
		List<Transaction> toReturn =  new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("tType");
		myListV.add(ttype);
		for(var part:getAllItWherePS(
				"Transactions", new Transaction(), Transaction.lookup,"TType=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Transaction) part);
		}
		return toReturn;
	}

	@Override
	public  List<Transaction> getTransactionsByDay(Integer day) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  List<Transaction> getTransactionsByMonth(Integer month) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean insertTransaction(Transaction input) throws Exception {
	    return insertIt("Transactions",input,Transaction.lookup);
	}

	@Override
	public boolean updateTransaction(Transaction input) throws Exception {
		return updateIt("Transactions", input, Transaction.lookup);
	}

	@Override
	public boolean deleteTransaction(int id) throws Exception {
		return deleteIt("Transactions", id);
	}

	public static void main(String[] args) throws Exception {
		TransactionDaoImpl tDao = new TransactionDaoImpl();
		Account myAcc = new Account();
		myAcc.setBankID(5);
		List<Transaction> pers = tDao.getAllTransactions();
		for(Transaction ap:pers) {
			System.out.println(ap.getId());
		}
	}
	
}
