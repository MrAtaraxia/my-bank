package theBank.DAO;

import java.util.Set;

import theBank.accounts.Account;

/**
 * Account DAO
 * @author w3
 */

public interface AccountDao {
    public Account getAccount(int id) throws Exception;
    Set<Account> getAllAccounts() throws Exception;
    Set<Account> getAllActiveAccounts() throws Exception;
    Set<Account> getAllNeedApprovalAccounts() throws Exception;
    Set<Account> getActiveAccountsByUser(Integer UserID) throws Exception;
    Set<Account> getAccountsByUser(Integer UserID) throws Exception;
    boolean insertAccount(Account account) throws Exception;
    boolean updateAccount(Account account) throws Exception;
    boolean deleteAccount(int id) throws Exception;
}
