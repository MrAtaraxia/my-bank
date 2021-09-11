package theBank.transactions;

import java.util.HashMap;
import java.util.Map;


public enum TType {
	ACH_CREDIT("ACH CREDIT"),
	ACH_WITHDRAWL("ACH WITHDRAWL"),
	ACH_COLLECTIONS ("ACH COLLECTIONS"),
	ACH_PAYMENTS ("ACH PAYMENTS"),
	CHECK ("CHECK"),
	DEBIT_CARD_PURCHASE("DEBIT CARD PURCHASE"),
	CASH_WITHDRAWL("CASH WITHDRAWL"),
	CASH_DEPOSIT("CASH DEPOSIT"),
	INTEREST("INTEREST"),
	INVALID("INVALID"),
	OVERDRAFT("OVERDRAFT"),
	POS_PURCHASE("POS PURCHASE"),
	POS_CREDIT("POS CREDIT/ADJ POS DEPOSIT"),
	NETWORK_POS_PURCHASE("NETWORK POS PURCHASE"),
	CHARGE_BACK("CHARGE BACK"),
	REFUND("REFUND"),
	TRANSFER("TRANSFER"),
	MERCHANT_CAPTURE_DEPOSIT("MERCHANT CAPTURE DEPOSIT");
	private final String value;
	private TType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
    private static final Map<String, TType> lookup = new HashMap<>();
    
    static
    {
        for(TType sta : TType.values())
        {
            lookup.put(sta.toString(), sta);
        }
    }

    public static TType get(String url) 
    {
        return lookup.get(url);
    }
}
