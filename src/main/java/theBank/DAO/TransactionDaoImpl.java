package theBank.DAO;

import java.sql.Connection;
import java.util.Map;

import theBank.transactions.Transaction;

public class TransactionDaoImpl implements TransactionDAO {

	@Override
	public Transaction getTransaction(int id) throws Exception {
	    Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Transaction> getAllTransactions() throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Transaction> getTransactionsByUser(Integer UserID) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Transaction> getTransactionsByAccount(Integer AccountID) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertTransaction(Transaction transaction) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTransaction(Transaction transaction) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTransaction(int id) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return false;
	}

}
