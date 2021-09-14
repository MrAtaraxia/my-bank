package theBank.accounts;

import java.util.HashMap;
import java.util.Map;

public enum AState {
		PENDING("PENDING"),
		ACTIVE("ACTIVE"),
		FROZEN("FROZEN"),
		CLOSED("CLOSED"),
		CANCELED("CANCELED"),
		AWAITING_CONFIRMATION("AWAITING CONFIRMATION");
		private final String value;
		private AState(String value) {
			this.value = value;
		}
		
		public String toString() {
			return this.value;
	}
	
    private static final Map<String, AState> lookup = new HashMap<>();
    
    static
    {
        for(AState sta : AState.values())
        {
            lookup.put(sta.toString(), sta);
        }
    }

    public static AState get(String url) 
    {
        return lookup.get(url);
    }

}
