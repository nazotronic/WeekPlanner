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

public class WorkerShift {
	/* --- constructors --- */
	public WorkerShift() {
		startTime = new Time();
		endTime = new Time();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public int isPeriodInShift(int startHour, int duration) {
		int startDiff = startTime.getHour() - startHour;
		int endDiff = (startHour + duration) - endTime.getHour();

		startDiff = Math.max(startDiff, 0);
		endDiff = Math.max(endDiff, 0);

		return startDiff + endDiff;
	}

	public int clearPeriodInShift(int startHour, int endHour) {
		Time startTimeOld = new Time(getStartTime());
		Time endTimeOld = new Time(getEndTime());

		if (startHour > startTime.getHour() && endHour < endTime.getHour()) {
			int diff = endTime.getHour() - startTime.getHour();

			initialized = false;
			startTime.setHour(0);
			endTime.setHour(0);

			return diff;
		}
		else if (startHour <= startTime.getHour() && endHour >= endTime.getHour()) {
			int diff = endTime.getHour() - startTime.getHour();

			initialized = false;
			startTime.setHour(0);
			endTime.setHour(0);

			return diff;
		}

		if (startHour <= startTime.getHour()) {
			startTime.setHour(Math.max(startTime.getHour(), endHour));
		}
		else {
			endTime.setHour(Math.min(endTime.getHour(), startHour));
		}

		int startDiff = startTime.getHour() - startTimeOld.getHour();
		int endDiff = endTimeOld.getHour() - endTime.getHour();

		startDiff = Math.max(startDiff, 0);
		endDiff = Math.max(endDiff, 0);

		return startDiff + endDiff;
	}
	/* --- main logic --- */

	/* --- overrides --- */
	@Override
	public String toString() {
		return startTime + " - " + endTime;
	}
	/* --- overrides --- */

	/* --- setters --- */
	public void setShift(int startHour, int endHour) {
		if (!initialized) {
			startTime.setHour(startHour);
			endTime.setHour(endHour);

			initialized = true;
			return;
		}

		if (startHour < startTime.getHour()) {
			startTime.setHour(startHour);
		}

		if (endHour > endTime.getHour()) {
			endTime.setHour(endHour);
		}
	}
	/* --- setters --- */

	/* --- getters --- */
	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public boolean isInitialized() {
		return initialized;
	}
	/* --- getters --- */

	/* --- private --- */
	private final Time startTime;
	private final Time endTime;
	private boolean initialized = false;
	/* --- private --- */
}
