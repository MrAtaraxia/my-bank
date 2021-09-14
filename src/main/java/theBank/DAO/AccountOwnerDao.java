package theBank.DAO;

import java.util.List;

import theBank.accounts.AccountOwner;

public interface AccountOwnerDao {
	List<AccountOwner> getAOs(int id) throws Exception;
	List<AccountOwner> getAllAOs() throws Exception;
	List<AccountOwner> getAllActiveAOs() throws Exception;
	List<AccountOwner> getAllAOsByUserID(Integer UserID) throws Exception;
	List<AccountOwner> getAllActiveAOsByUserID(Integer UserID) throws Exception;
	List<AccountOwner> getAllAOsByAccountID(Integer AccountID) throws Exception;
	List<AccountOwner> getAllActiveAOsByAccountID(Integer AccountID) throws Exception;
    List<Integer> getAllAccountIDsByAOs(List<AccountOwner> accMap) throws Exception;
    List<Integer> getAllPersonIDsByAOs(List<AccountOwner> accMap) throws Exception;
    boolean insertAO(AccountOwner ao) throws Exception;
    boolean updateAO(AccountOwner ao) throws Exception;
    boolean activateAO(int id) throws Exception;
    boolean deactivateAO(int id) throws Exception;
    boolean deleteAO(int id) throws Exception;
}
