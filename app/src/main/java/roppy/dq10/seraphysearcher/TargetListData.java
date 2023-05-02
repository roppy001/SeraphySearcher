package roppy.dq10.seraphysearcher;

public class TargetListData {
	private String key;
	private String name;
	private String timeDescription;
	private boolean checked;
	private String queryString;
	private int colorType;

	public static final int COLOR_NORMAL_TYPE = 0;
	public static final int COLOR_ALERT_TYPE = 1;

	public TargetListData() {
	}

	public TargetListData(String key, String name, String timeDescription,
			boolean checked) {
		this.key = key;
		this.name = name;
		this.timeDescription = timeDescription;
		this.checked = checked;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimeDescription() {
		return timeDescription;
	}
	public void setTimeDescription(String timeDescription) {
		this.timeDescription = timeDescription;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public int getColorType() {
		return colorType;
	}
	public void setColorType(int colorType) {
		this.colorType = colorType;
	}


}
