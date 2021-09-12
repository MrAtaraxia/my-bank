package theBank.DAO;

import java.util.Map;

import theBank.general.Bank;
import theBank.general.State;

/**
 * Bank DAO
 * @author w3
 */

public interface BankDAO {
    public Bank getBank(int id) throws Exception;
    public Bank getBankByRouting(String routingNumber) throws Exception;
    Map<Integer, Bank> getAllBanks() throws Exception;
    Map<Integer, Bank> getAllActiveBanks() throws Exception;
    Map<Integer, Bank> getAllBanksByAddressID(int addressid) throws Exception;
    boolean insertBank(Bank abank) throws Exception;
    boolean updateBank(Bank abank) throws Exception;
    boolean activateBank(int id) throws Exception;
    boolean deactivateBank(int id) throws Exception;
    boolean deleteBank(int id) throws Exception;
}
