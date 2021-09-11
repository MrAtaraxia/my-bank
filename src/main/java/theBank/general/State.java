package theBank.general;

import java.util.HashMap;
import java.util.Map;

public enum State {
	AL("Alabama"),
	AK("Alaska"),
	AZ("Arizona"),
	AR("Arkansas"),
	CA("California"),
	CO("Colorado"),
	CT("Connecticut"),
	DE("Delaware"),
	FL("Flordia"),
	GA("Georgia"),
	HI("Hawaii"),
	ID("Idaho"),
	IL("Illinois"),
	IN("Indiana"),
	IA("Iowa"),
	KS("Kansas"),
	KY("Kentucky"),
	LA("Louisiana"),
	ME("Maine"),
	MD("Maryland"),
	MA("Massachusetts"),
	MI("Michigan"),
	MN("Minnesota"),
	MS("Mississippi"),
	MO("Missouri"),
	MT("Montana"),
	NE("Nebraska"),
	NV("Navada"),
	NH("New Hampshire"),
	NJ("New Jersey"),
	NM("New Mexico"),
	NY("New York"),
	NC("North Carolina"),
	ND("North Dakota"),
	OH("Ohio"),
	OK("Oklahoma"),
	OR("Oregon"),
	PA("Pennsylvania"),
	RI("Rhode Island"),
	SC("South Carolina"),
	SD("South Dakota"),
	TN("Tennessee"),
	TX("Texas"),
	UT("Utah"),
	VT("Vermont"),
	VA("Virgina"),
	WA("Washington"),
	WV("West Virgina"),
	WI("Wisconsin"),
	WY("Wyoming");
	private String name;
	
	private State(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
    private static final Map<String, State> lookup = new HashMap<>();
  
    static
    {
        for(State sta : State.values())
        {
            lookup.put(sta.toString(), sta);
        }
    }

    public static State get(String url) 
    {
        return lookup.get(url);
    }
}