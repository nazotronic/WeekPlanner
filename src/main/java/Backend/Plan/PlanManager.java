/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Plan;

import Backend.Calendar.CalendarManager;
import Backend.IManager;
import Backend.Limitations.LimitationsManager;
import Backend.Logger;
import Backend.Plan.Schedule.ScheduleManager;
import Backend.Staff.StaffManager;

public class PlanManager implements IManager {
	/* --- constructors --- */
	public PlanManager(CalendarManager globalCalendar, StaffManager globalStaff) {
		calendar = new CalendarManager(globalCalendar);
		staff = new StaffManager(globalStaff);
		limitations = new LimitationsManager();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void startSchedule() {
		if (schedule == null) {
			Logger.logInfo("Початок створення розкладу");
		}

		schedule = (schedule == null) ? new ScheduleManager(getCalendar(), getStaff(), getLimitations()) : schedule;
	}

	public void stopSchedule() {
		Logger.logInfo("Кінець створення розкладу");
		schedule = null;
	}

	public void acceptPlan(String planName, StaffManager staffToProcess) {
		StringBuilder planLog = new StringBuilder("\nЗанесення в план - " + planName + "\n");
		Logger.logInfo("Затвердження розкладу");

		schedule.acceptSchedule(planLog, staffToProcess);
		stopSchedule();
	}
	/* --- constructors --- */

	/* --- getters --- */
	public CalendarManager getCalendar() {
		return calendar;
	}

	public StaffManager getStaff() {
		return staff;
	}

	public LimitationsManager getLimitations() {
		return limitations;
	}

	public ScheduleManager getSchedule() {
		return schedule;
	}
	/* --- getters --- */

	/* --- private --- */
	private final CalendarManager calendar;
	private final StaffManager staff;
	private final LimitationsManager limitations;
	private ScheduleManager schedule;
	/* --- private --- */
}
