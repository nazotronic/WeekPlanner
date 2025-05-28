/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Plan.Schedule.Makers;

import Backend.Limitations.Limits.ILimit;
import Backend.Plan.Schedule.ScheduleDayManager;
import Backend.Plan.Schedule.ScheduleWorkerInfo;

import java.util.ArrayList;

public class LimitOverlay {
	/* --- constructors --- */
	public LimitOverlay(ArrayList<ScheduleDayManager> days,
	                    ArrayList<ScheduleWorkerInfo> workersInfo,
	                    ILimit limit) {
		this.days = days;
		this.workersInfo = workersInfo;
		this.limit = limit;

		makeLimitVacationOverlay();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void makeLimitVacationOverlay() {
		int dayId = limit.getDayId();
		int workerId = limit.getWorkerId();
		int startHour = limit.getStart().getHour();
		int endHour = limit.getEnd().getHour();

		int plusHours = days.get(dayId).getWorkerShift(workerId).clearPeriodInShift(startHour, endHour);
		workersInfo.get(workerId).addHours(plusHours);
	}
	/* --- main logic --- */

	/* --- private --- */
	private final ArrayList<ScheduleDayManager> days;
	private final ArrayList<ScheduleWorkerInfo> workersInfo;
	private final ILimit limit;
	/* --- private --- */
}
