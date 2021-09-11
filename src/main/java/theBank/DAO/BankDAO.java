package theBank.DAO;

import java.util.Map;

import theBank.general.Bank;

/**
 * Bank DAO
 * @author w3
 */

public interface BankDAO {
    public Bank getBank(int id) throws Exception;
    Map<Integer, Bank> getAllBanks() throws Exception;
    Map<Integer, Bank> getAllBanksByState(String state) throws Exception;
    boolean insertBank(Bank addr) throws Exception;
    boolean updateBank(Bank addr) throws Exception;
    boolean deleteBank(int id) throws Exception;
}