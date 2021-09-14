package theBank.DAO;

import java.util.List;
import theBank.general.Bank;

/**
 * Bank DAO
 * @author w3
 */

public interface BankDAO {
    public Bank getBank(int id) throws Exception;
    public Bank getBankByRouting(String routingNumber) throws Exception;
    List<Bank> getAllBanks() throws Exception;
    List<Bank> getAllActiveBanks() throws Exception;
    List<Bank> getAllBanksByAddressID(int addressid) throws Exception;
    boolean insertBank(Bank abank) throws Exception;
    boolean updateBank(Bank abank) throws Exception;
    boolean activateBank(int id) throws Exception;
    boolean deactivateBank(int id) throws Exception;
    boolean deleteBank(int id) throws Exception;
}
