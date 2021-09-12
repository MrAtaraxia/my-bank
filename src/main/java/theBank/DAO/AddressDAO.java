package theBank.DAO;

import java.util.Map;

import theBank.general.Address;
import theBank.general.State;

public interface AddressDAO {
    public Address getAddress(int id) throws Exception;
    Map<Integer, Address> getAllAddresses() throws Exception;
    Map<Integer, Address> getAllActiveAddresses() throws Exception;
    Map<Integer, Address> getAllAddressesByState(State state) throws Exception;
    Address getAddressByPerson(int personID) throws Exception;
    boolean insertAddress(Address addr) throws Exception;
    boolean updateAddress(Address addr) throws Exception;
    boolean activateAddress(int id) throws Exception;
    boolean deactivateAddress(int id) throws Exception;
    boolean deleteAddress(int id) throws Exception;
}