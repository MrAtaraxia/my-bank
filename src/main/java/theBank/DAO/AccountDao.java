package theBank.DAO;

import java.util.List;
import theBank.accounts.AState;
import theBank.accounts.AType;
import theBank.accounts.Account;

/**
 * Account DAO
 * @author w3
 */

public interface AccountDao {
    public Account getAccount(int id) throws Exception;
    List<Account> getAllAccounts() throws Exception;
    List<Account> getAllActiveAccounts() throws Exception;
    List<Account> getAllNeedApprovalAccounts() throws Exception;
    List<Account> getAccountsByState(AState astate) throws Exception;
    List<Account> getActiveAccountsByState(AState astate) throws Exception;
    List<Account> getAccountsByType(AType atype) throws Exception;
    List<Account> getActiveAccountsByType(AType atype) throws Exception;
    List<Account> getAccountsByAccountIDs(List<Integer> accountIDs) throws Exception;
    Account insertAccount(Account account) throws Exception;
    boolean updateAccount(Account account) throws Exception;
    boolean activateAccount(int id) throws Exception;
    boolean deactivateAccount(int id) throws Exception;
    boolean deleteAccount(int id) throws Exception;
	boolean CancelAccount(int id) throws Exception;
}
