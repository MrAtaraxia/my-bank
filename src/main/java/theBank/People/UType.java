package theBank.People;

import java.util.HashMap;
import java.util.Map;


public enum UType {
	NONE("NONE"),
	CUSTOMER("CUSTOMER"),
	EMPLOYEE("EMPLOYEE"),
	ADMIN("ADMIN");
	private final String value;
	private UType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
    private static final Map<String, UType> lookup = new HashMap<>();
    
    static
    {
        for(UType sta : UType.values())
        {
            lookup.put(sta.toString(), sta);
        }
    }

    public static UType get(String url) 
    {
        return lookup.get(url);
    }
}
