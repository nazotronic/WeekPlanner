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

public class LimitHospital implements ILimit {
	/* --- setters --- */
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
		return START_TIME;
	}

	@Override
	public Time getEnd() {
		return END_TIME;
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
	private final Time START_TIME = new Time(0);
	private final Time END_TIME = new Time(24);
	private int workerId;
	private int dayId;
	/* --- private --- */
}
