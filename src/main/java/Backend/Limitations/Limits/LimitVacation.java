/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Limitations.Limits;

import Backend.Elements.Time;

public class LimitVacation implements ILimit {
	/* --- setters --- */
	public void setStartTime(int startTime) {
		this.startTime.setHour(startTime);
	}

	public void setEndTime(int endTime) {
		this.endTime.setHour(endTime);
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
	}
	/* --- setters --- */

	/* --- overrides & getters --- */
	@Override
	public Time getStart() {
		return startTime;
	}

	@Override
	public Time getEnd() {
		return endTime;
	}

	@Override
	public int getWorkerId() {
		return workerId;
	}

	@Override
	public int getDayId() {
		return dayId;
	}
	/* --- overrides & getters --- */

	/* --- private --- */
	private final Time startTime = new Time();
	private final Time endTime = new Time();
	private int workerId;
	private int dayId;
	/* --- private --- */
}
