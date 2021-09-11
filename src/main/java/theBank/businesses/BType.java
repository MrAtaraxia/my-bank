package theBank.businesses;

import java.util.HashMap;
import java.util.Map;

public enum BType {
	SOLE_PROPRIETORSHIP("SOLE PROPRIETORSHIP"),
	SINGLE_MEMBER_LLC("SINGLE-MEMBER LLC"),
	PENDING_APPROVAL("PENDING APPROVAL"),
	REJECTED("REJECTED"),
	CLOSED("CLOSED");
	private final String value;
	private BType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
    private static final Map<String, BType> lookup = new HashMap<>();
    
    static
    {
        for(BType sta : BType.values())
        {
            lookup.put(sta.toString(), sta);
        }
    }

    public static BType get(String url) 
    {
        return lookup.get(url);
    }
}
