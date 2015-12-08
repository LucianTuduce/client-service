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
}
