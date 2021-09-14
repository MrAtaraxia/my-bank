package theBank.accounts;

import java.util.HashMap;
import java.util.Map;

public enum OType {
	NEEDSAPPROVAL("NEEDSAPPROVAL"),
	APPROVED("APPROVED"),
	DENIED("DENIED"),;
	private final String value;
	private OType(String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
	
    private static final Map<String, OType> lookup = new HashMap<>();
    
    static
    {
        for(OType sta : OType.values())
        {
            lookup.put(sta.toString(), sta);
        }
    }

    public static OType get(String url) 
    {
        return lookup.get(url);
    }

}
