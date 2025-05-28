/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Plan.Schedule;

import Backend.Calendar.DaySettings;
import Backend.IManager;
import Backend.Staff.StaffManager;
import Backend.Staff.WorkerSettings;

import java.util.ArrayList;

public class ScheduleDayManager implements IManager {
	/* --- constructors --- */
	public ScheduleDayManager(DaySettings day, StaffManager staff) {
		daySettings = new DaySettings(this, day);
		workers = new ArrayList<>();

		for (WorkerSettings workerSettings : staff) {
			workers.add(new WorkerShift());
		}
	}
	/* --- constructors --- */

	/* --- getters --- */
	public WorkerShift getWorkerShift(int index) {
		if (index >= workers.size()) {
			return null;
		}

		return workers.get(index);
	}

	public DaySettings getDaySettings() {
		return daySettings;
	}
	/* --- getters --- */

	/* --- private --- */
	private final DaySettings daySettings;
	private final ArrayList<WorkerShift> workers;
	/* --- private --- */
}
