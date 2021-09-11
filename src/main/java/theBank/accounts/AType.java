package theBank.accounts;

import java.util.HashMap;
import java.util.Map;

public enum AType {
	JOINT("JOINT"),
	INDIVIDUAL("INDIVIDUAL"),
	SAVINGS("SAVINGS"),
	CHECKING("CHECKING"),
	BUSINESS_SAVINGS("BUSINESS SAVINGS"),
	BUSINESS_CHECKING("BUSINESS CHECKING"),
	MERCHANTS_ACCOUNT("MERCHANTS ACCOUNT");
	private final String value;
	private AType(String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
	
    private static final Map<String, AType> lookup = new HashMap<>();
    
    static
    {
        for(AType sta : AType.values())
        {
            lookup.put(sta.toString(), sta);
        }
    }

    public static AType get(String url) 
    {
        return lookup.get(url);
    }

}
