package theBank.DAO;

import java.util.List;

import theBank.accounts.AccountOwner;

public interface AccountOwnerDao {
	List<AccountOwner> getAOs(int id) throws Exception;
	List<AccountOwner> getAllAOs() throws Exception;
	List<AccountOwner> getAllActiveAOs() throws Exception;
	List<AccountOwner> getAllAOsByPersonID(Integer personID) throws Exception;
	List<AccountOwner> getAllActiveAOsByPersonID(Integer personID) throws Exception;
	List<AccountOwner> getAllAOsByAccountID(Integer accountID) throws Exception;
	List<AccountOwner> getAllActiveAOsByAccountID(Integer accountID) throws Exception;
    List<Integer> getAllAccountIDsByAOs(List<AccountOwner> accMap) throws Exception;
    List<Integer> getAllPersonIDsByAOs(List<AccountOwner> accMap) throws Exception;
    boolean insertAO(AccountOwner ao) throws Exception;
    boolean updateAO(AccountOwner ao) throws Exception;
    boolean activateAO(int id) throws Exception;
    boolean deactivateAO(int id) throws Exception;
    boolean deleteAO(int id) throws Exception;
}
