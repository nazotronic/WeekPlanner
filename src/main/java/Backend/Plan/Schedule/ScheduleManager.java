/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Plan.Schedule;

import Backend.Calendar.CalendarManager;
import Backend.Calendar.DaySettings;
import Backend.IManager;
import Backend.Limitations.LimitationsManager;
import Backend.Limitations.Limits.LimitVacation;
import Backend.Limitations.Limits.LimitWrapper;
import Backend.Plan.Schedule.Makers.BasicDistribution;
import Backend.Plan.Schedule.Makers.LimitOverlay;
import Backend.Plan.Schedule.Makers.MeetingOverlay;
import Backend.Staff.StaffManager;
import Backend.Staff.WorkerSettings;

import java.util.ArrayList;

public class ScheduleManager implements IManager {
	/* --- constructors --- */
	public ScheduleManager(CalendarManager calendar, StaffManager staff, LimitationsManager limitations) {
		days = new ArrayList<>();
		workersInfo = new ArrayList<>();

		for (DaySettings day : calendar) {
			days.add(new ScheduleDayManager(day, staff));
		}

		for (WorkerSettings worker : staff) {
			workersInfo.add(new ScheduleWorkerInfo(worker));
		}

		new BasicDistribution(days, workersInfo);
		new MeetingOverlay(days, workersInfo);

		for (LimitWrapper limit : limitations) {
			int dayId = limit.getLimit().getDayId();
			int workerId = limit.getLimit().getWorkerId();
			String remark = "Накладання обмеження " + limit.getLimitName() + " у " + calendar.getDay(dayId).getName() +
					": " + ((limit.getLimit() instanceof LimitVacation) ? "Відпуск" : "Лікарняний");

			workersInfo.get(workerId).addRemark(remark);
			new LimitOverlay(days, workersInfo, limit.getLimit());
		}
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void acceptSchedule(StringBuilder log, StaffManager staffToProcess) {
		for (int i = 0;i < workersInfo.size();i++) {
			StringBuilder workerLog = new StringBuilder(log);
			ArrayList<WorkerShift> workerSchedule = makeWorkerSchedule(i);
			ArrayList<String> workerRemarks = workersInfo.get(i).getRemarks();

			for (int j = 0;j < workerSchedule.size();j++) {
				String dayName = days.get(j).getDaySettings().getName();

				workerLog.append("\t" + dayName + ": " + workerSchedule.get(j) + "\n");
			}

			for (String remark : workerRemarks) {
				workerLog.append("\t\t" + remark + "\n");
			}
			workerLog.append("\n");

			staffToProcess.getWorker(i).minusRemainingTime(workersInfo.get(i).getTotalTime());
			staffToProcess.getWorker(i).addLog(workerLog.toString());
		}
	}

	public ArrayList<WorkerShift> makeWorkerSchedule(int index) {
		ArrayList<WorkerShift> workerSchedule = new ArrayList<>();

		if (index >= getWorkersCount()) {
			return null;
		}

		for (ScheduleDayManager daySchedule : days) {
			workerSchedule.add(daySchedule.getWorkerShift(index));
		}

		return workerSchedule;
	}
	/* --- main logic --- */

	/* --- getters --- */
	public int getWorkersCount() {
		if (workersInfo != null) {
			return workersInfo.size();
		}

		return 0;
	}

	public ScheduleWorkerInfo getWorkerInfo(int index) {
		if (index >= getWorkersCount()) {
			return null;
		}

		return workersInfo.get(index);
	}
	/* --- getters --- */

	/* --- private --- */
	private final ArrayList<ScheduleDayManager> days;
	private final ArrayList<ScheduleWorkerInfo> workersInfo;
	/* --- private --- */
}
