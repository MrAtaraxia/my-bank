package theBank.DAO;

import java.util.List;
import java.util.Map;

import theBank.transactions.TState;
import theBank.transactions.TType;
import theBank.transactions.Transaction;

/**
 * Account DAO
 * @author w3
 */

public interface TransactionDAO {
	public Transaction getTransaction(int id) throws Exception;
    List<Transaction> getAllTransactions() throws Exception;
    List<Transaction> getTransactionsByPersonID(Integer UserID) throws Exception;
    List<Transaction> getTransactionsByAccountID(Integer AccountID) throws Exception;
    List<Transaction> getTransactionsByTState(TState tstate) throws Exception;
    List<Transaction> getTransactionsByTType(TType ttype) throws Exception;
    List<Transaction> getTransactionsByDay(Integer day) throws Exception;
    List<Transaction> getTransactionsByMonth(Integer month) throws Exception;
    boolean insertTransaction(Transaction transaction) throws Exception;
    boolean updateTransaction(Transaction transaction) throws Exception;
    boolean deleteTransaction(int id) throws Exception;
}
