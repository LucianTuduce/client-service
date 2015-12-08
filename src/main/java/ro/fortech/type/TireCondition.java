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
}
