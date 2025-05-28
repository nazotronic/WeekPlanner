/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Plan.Schedule.Makers;

import Backend.Plan.Schedule.ScheduleDayManager;
import Backend.Plan.Schedule.ScheduleWorkerInfo;

import java.util.ArrayList;

public class MeetingOverlay {
	/* --- constructors --- */
	public MeetingOverlay(ArrayList<ScheduleDayManager> days,
	                         ArrayList<ScheduleWorkerInfo> workersInfo) {
		this.days = days;
		this.workersInfo = workersInfo;

		makeMeetingOverlay();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void makeMeetingOverlay() {
		for (ScheduleDayManager daySchedule : days) { // all days
			if (!daySchedule.getDaySettings().isActiveFlag()) {
				continue;
			}

			if (!daySchedule.getDaySettings().isMeetingFlag()) {
				continue;
			}

			int meetingStartHour = daySchedule.getDaySettings().getStartMeetingTime().getHour();
			int meetingDurationHour = daySchedule.getDaySettings().getMeetingDurationTime().getHour();

			for (int i = 0;i < workersInfo.size();i++) {
				ScheduleWorkerInfo worker = workersInfo.get(i);

				if (daySchedule.getWorkerShift(i).isInitialized()) {
					int requiredHours = daySchedule.getWorkerShift(i).isPeriodInShift(meetingStartHour, meetingDurationHour);

					if (requiredHours <= meetingDurationHour) {
						int availableHours = worker.takeHours(requiredHours);

						if (availableHours != requiredHours) {
							continue;
						}

						daySchedule.getWorkerShift(i).setShift(meetingStartHour, meetingStartHour + availableHours);
						workersInfo.get(i).addRemark("Зброри у " + daySchedule.getDaySettings().getName());
					}
					continue;
				}

				int availableHours = worker.takeHours(meetingDurationHour);

				daySchedule.getWorkerShift(i).setShift(meetingStartHour, meetingStartHour + availableHours);
				workersInfo.get(i).addRemark("Зброри у " + daySchedule.getDaySettings().getName());
			}
		}
	}
	/* --- main logic --- */

	/* --- private --- */
	private final ArrayList<ScheduleDayManager> days;
	private final ArrayList<ScheduleWorkerInfo> workersInfo;
	/* --- private --- */
}
