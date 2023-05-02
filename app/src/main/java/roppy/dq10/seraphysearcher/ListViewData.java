package roppy.dq10.seraphysearcher;

import java.util.List;

public class ListViewData {
	private boolean sound;
	private boolean vibrate;
	private List<TargetListData> targetList;
	private int durationIndex;

	public boolean isSound() {
		return sound;
	}
	public void setSound(boolean sound) {
		this.sound = sound;
	}
	public boolean isVibrate() {
		return vibrate;
	}
	public void setVibrate(boolean vibrate) {
		this.vibrate = vibrate;
	}
	public List<TargetListData> getTargetList() {
		return targetList;
	}
	public void setTargetList(List<TargetListData> targetList) {
		this.targetList = targetList;
	}
	public int getDurationIndex() { return durationIndex; }
	public void setDurationIndex(int durationIndex) { this.durationIndex = durationIndex; }
}
