package theBank.transactions;

import java.util.HashMap;
import java.util.Map;


public enum TState {
	APPROVED("APPROVED"),
	PENDING_CONFIRMATION("PENDING CONFIRMATION"),
	PENDING_APPROVAL("PENDING APPROVAL"),
	REJECTED("REJECTED"),
	CLOSED("CLOSED");
	private final String value;
	private TState(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
    private static final Map<String, TState> lookup = new HashMap<>();
    
    static
    {
        for(TState sta : TState.values())
        {
            lookup.put(sta.toString(), sta);
        }
    }

    public static TState get(String url) 
    {
        return lookup.get(url);
    }
}
