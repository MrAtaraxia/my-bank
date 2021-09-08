package theBank.DAO;

import java.util.Map;

import theBank.transactions.Transaction;

/**
 * Account DAO
 * @author w3
 */

public interface TransactionDAO {
	public Transaction getTransaction(int id) throws Exception;
    Map<Integer, Transaction> getAllTransactions() throws Exception;
    Map<Integer, Transaction> getTransactionsByUser(Integer UserID) throws Exception;
    Map<Integer, Transaction> getTransactionsByAccount(Integer AccountID) throws Exception;
    boolean insertTransaction(Transaction transaction) throws Exception;
    boolean updateTransaction(Transaction transaction) throws Exception;
    boolean deleteTransaction(int id) throws Exception;
}
