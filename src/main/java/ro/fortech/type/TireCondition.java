package ro.fortech.type;

public enum TireCondition {
	USED("used"), NEW("new"), SLIGHTY_USED("slighty used");
	
	private String tireCondition;
	
	private TireCondition(String tireCondition) {
		this.tireCondition = tireCondition;
	}

	public String getTireCondition() {
		return tireCondition;
	}

	public void setTireCondition(String tireCondition) {
		this.tireCondition = tireCondition;
	}
	
	public static TireCondition getEnum(String s){
		
		if(USED.name().equalsIgnoreCase(s)){
			return USED;
		}
		else if(NEW.name().equalsIgnoreCase(s)){
			return NEW;
		}
		else if(SLIGHTY_USED.name().equalsIgnoreCase(s)){
			return SLIGHTY_USED;
		}
		throw new IllegalArgumentException("No Enum specified for this string");
	}
}
