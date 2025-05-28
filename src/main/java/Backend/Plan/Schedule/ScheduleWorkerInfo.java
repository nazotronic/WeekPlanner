/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Plan.Schedule;

import Backend.Elements.Time;
import Backend.Staff.WorkerSettings;

import java.util.ArrayList;

public class ScheduleWorkerInfo {
	/* --- constructors --- */
	public ScheduleWorkerInfo(WorkerSettings worker) {
		int monthWorkHours = worker.getMonthWorkHours();

		fullName = worker.getFullName();
		hardWork = worker.isHardWork();
		dayWorkHours = Math.round((float) monthWorkHours / 4 / 5);
		remainingTime = new Time(worker.getRemainingTime());
		totalTime = new Time();

		remarks = new ArrayList<>();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void startDay() {
		dayFreeWorkHours = Math.min(dayWorkHours, remainingTime.getHour());
	}

	public int takeDayHours(int hours) {
		int availableHours = takeHours(Math.min(hours, dayFreeWorkHours));
		dayFreeWorkHours -= availableHours;

		return availableHours;
	}

	public int takeHours(int hours) {
		int availableHours = Math.min(hours, remainingTime.getHour());

		remainingTime.minusHour(availableHours);
		totalTime.plusHour(hours);

		if (getDayFreeWorkHours() < availableHours) {
			remarks.add("Понаднормова к-ксть годин: " + (availableHours - getDayFreeWorkHours()) );
		}

		return availableHours;
	}

	public void addHours(int hours) {
		remainingTime.plusHour(hours);
		dayFreeWorkHours += hours;

		totalTime.minusHour(hours);
	}

	public void addRemark(String remark) {
		remarks.add(remark);
	}
	/* --- main logic --- */

	/* --- getters --- */
	public String getFullName() {
		return fullName;
	}

	public boolean isHardWork() {
		return hardWork;
	}

	public Time getRemainingTime() {
		return remainingTime;
	}

	public Time getTotalTime() {
		return totalTime;
	}

	public int getDayFreeWorkHours() {
		return dayFreeWorkHours;
	}

	public ArrayList<String> getRemarks() {
		return remarks;
	}
	/* --- getters --- */

	/* --- private --- */
	private final String fullName;
	private final boolean hardWork;
	private final int dayWorkHours;

	private final Time remainingTime;
	private final Time totalTime;
	private int dayFreeWorkHours;

	ArrayList<String> remarks;
	/* --- private --- */
}
