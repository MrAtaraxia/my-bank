package theBank.DAO;

import java.util.ArrayList;
import java.util.List;
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
				"Transaction", new Transaction(), Transaction.lookup,"id = ?", myListV, myListT)) {
			return (Transaction) part;
		}
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() throws Exception {
		List<Transaction> toReturn = null;
		for(var part:getAllIt(
				"Transaction", new Transaction(), Transaction.lookup).entrySet()) {
			if(toReturn == null) { toReturn = new ArrayList<>(); }
			toReturn.add((Transaction) part.getValue());
		}
		return toReturn;
	}

	@Override
	public  List<Transaction> getTransactionsByPersonID(Integer PersonID) throws Exception {
		List<Transaction> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("PersonID");
		myListV.add(PersonID);
		for(var part:getAllItWherePS(
				"Transaction", new Transaction(), Transaction.lookup,"PersonID=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Transaction) part);
		}
		return toReturn;
	}


	@Override
	public  List<Transaction> getTransactionsByAccountID(Integer AccountID) throws Exception {
		List<Transaction> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("AccountID");
		myListV.add(AccountID);
		for(var part:getAllItWherePS(
				"Transaction", new Transaction(), Transaction.lookup,"AccountID=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Transaction) part);
		}
		return toReturn;
	}

	@Override
	public  List<Transaction> getTransactionsByTState(TState tstate) throws Exception {
		List<Transaction> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("TState");
		myListV.add(tstate);
		for(var part:getAllItWherePS(
				"Transaction", new Transaction(), Transaction.lookup,"TState=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Transaction) part);
		}
		return toReturn;
	}

	@Override
	public  List<Transaction> getTransactionsByTType(TType ttype) throws Exception {
		List<Transaction> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("TType");
		myListV.add(ttype);
		for(var part:getAllItWherePS(
				"Transaction", new Transaction(), Transaction.lookup,"TType=?", myListV, myListT)) {
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
	    return insertIt("Transaction",input,Transaction.lookup);
	}

	@Override
	public boolean updateTransaction(Transaction input) throws Exception {
		return updateIt("Transaction", input, Transaction.lookup);
	}

	@Override
	public boolean deleteTransaction(int id) throws Exception {
		return deleteIt("Transaction", id);
	}

}
