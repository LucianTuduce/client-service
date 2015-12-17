package ro.fortech.type;

public enum SuspensionType {
	SWING_AXLE("swing axle"), TRAILING_LINK("trailing link"), MAC_PHERSON("MacPherson");
	
	private String suspensionType;
	
	private SuspensionType(String suspensionType) {
		this.setSuspensionType(suspensionType);
	}

	public String getSuspensionType() {
		return suspensionType;
	}

	public void setSuspensionType(String suspensionType) {
		this.suspensionType = suspensionType;
	}

	public static SuspensionType getEnum(String s){
		
		if(SWING_AXLE.name().equalsIgnoreCase(s)){
			return SWING_AXLE;
		}
		else if(TRAILING_LINK.name().equalsIgnoreCase(s)){
			return TRAILING_LINK;
		}
		else if(MAC_PHERSON.name().equalsIgnoreCase(s)){
			return MAC_PHERSON;
		}
		throw new IllegalArgumentException("No Enum specified for this string");
	}
}
