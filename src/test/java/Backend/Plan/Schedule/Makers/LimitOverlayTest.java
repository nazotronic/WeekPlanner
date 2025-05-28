/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Plan.Schedule.Makers;

import Backend.Calendar.DaySettings;
import Backend.Database.DBManager;
import Backend.Elements.Time;
import Backend.IManager;
import Backend.Limitations.Limits.LimitVacation;
import Backend.Plan.Schedule.ScheduleDayManager;
import Backend.Plan.Schedule.ScheduleWorkerInfo;
import Backend.Plan.Schedule.WorkerShift;
import Backend.Staff.StaffManager;
import Backend.Staff.WorkerSettings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LimitOverlayTest {

	@Test
	void testMakeLimitVacationOverlay_appliesVacationAndRestoresHours() {
		WorkerSettings workerSettings = new WorkerSettings((IManager) null);
		workerSettings.setName("John");
		workerSettings.setSurname("Doe");
		workerSettings.setMiddleName("A");
		workerSettings.setMonthWorkHours(160);
		workerSettings.setRemainingTime(new Time(160));

		ScheduleWorkerInfo workerInfo = new ScheduleWorkerInfo(workerSettings);
		workerInfo.startDay();

		WorkerShift shift = new WorkerShift();
		shift.setShift(8, 16); // 8 hours shift

		ScheduleDayManager dayManager = new ScheduleDayManager(new DaySettings(null), new StaffManager((DBManager) null)) {
			@Override
			public WorkerShift getWorkerShift(int index) {
				return shift;
			}
		};

		ArrayList<ScheduleDayManager> days = new ArrayList<>();
		days.add(dayManager);

		ArrayList<ScheduleWorkerInfo> workers = new ArrayList<>();
		workers.add(workerInfo);

		LimitVacation limit = new LimitVacation(); // 6 hour vacation

		new LimitOverlay(days, workers, limit);

		assertEquals(160, workerInfo.getRemainingTime().getHour());
	}
}
