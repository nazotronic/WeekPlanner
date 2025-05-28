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
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicDistribution {
	/* --- constructors --- */
	public BasicDistribution(ArrayList<ScheduleDayManager> days,
	                         ArrayList<ScheduleWorkerInfo> workersInfo) {
		this.days = days;
		this.workersInfo = workersInfo;

		workerDaysCounter = new int[workersInfo.size()];
		workerDaysPartStatus = new int[workersInfo.size()];

		makeBasicDistribution();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	private void makeBasicDistribution() {
		for (ScheduleDayManager daySchedule : days) {
			if (!daySchedule.getDaySettings().isActiveFlag()) {
				continue;
			}

			int startDayHour = daySchedule.getDaySettings().getStartDayTime().getHour();
			int endDayHour = daySchedule.getDaySettings().getEndDayTime().getHour();

			int minWorkersCount = daySchedule.getDaySettings().getMinWorkersCount();
			int maxWorkersCount = daySchedule.getDaySettings().getMaxWorkersCount();
			int workersMiddleCount = minWorkersCount + Math.round((float) (maxWorkersCount - minWorkersCount) / 2);
			boolean privilegedDay = daySchedule.getDaySettings().isPrivilegedDay();
			boolean hardWorkRequired = daySchedule.getDaySettings().isHardWorkReq();

			int startPartHour = startDayHour;
			int fillingStep = Math.ceilDiv(endDayHour - startDayHour, BASIC_DISTRIBUTION_PARTS);

			for (ScheduleWorkerInfo worker : workersInfo) {
				worker.startDay();
				Arrays.fill(workerDaysPartStatus, -1);
			}

			for (int i = 0; i < BASIC_DISTRIBUTION_PARTS; i++) {
				int endPartHour = Math.min(startPartHour + fillingStep, endDayHour);
				int workersCount = 0;
				boolean hardWorkerFlag = false;

				if (i == 0 || i == BASIC_DISTRIBUTION_PARTS - 1) {
					workersCount = (!privilegedDay) ? minWorkersCount : workersMiddleCount;
				}
				else {
					workersCount = (!privilegedDay) ? workersMiddleCount : maxWorkersCount;
					hardWorkerFlag = hardWorkRequired;
				}

				for (int j = 0; j < workersCount; j++) {
					int hourPointer = startPartHour;
					boolean partStartFlag = true;

					while (hourPointer != endPartHour) {
						int requiredHours = endPartHour - hourPointer;
						AtomicInteger availableHours = new AtomicInteger();
						int workerIndex = -1;

						if (hardWorkerFlag) {
							workerIndex = findHardWorkerIndex(requiredHours, availableHours);

							if (requiredHours == availableHours.get()) {
								hardWorkerFlag = false;
							}

							if (workerIndex == -1) {
								hardWorkerFlag = false;
								continue;
							}
						}

						else {
							if (partStartFlag) {
								workerIndex = findFreeWorkerIndex(requiredHours, availableHours, i - 1);
								partStartFlag = false;
							} else {
								workerIndex = findFreeWorkerIndex(requiredHours, availableHours);
							}

							if (workerIndex == -1) {
								workerIndex = findFreeWorkerIndex(requiredHours, availableHours, days.indexOf(daySchedule), hourPointer);

								if (workerIndex == -1) {
									break;
								}
							}
						}

						daySchedule.getWorkerShift(workerIndex).setShift(hourPointer, hourPointer + availableHours.get());
						workerDaysPartStatus[workerIndex] = i;

						hourPointer += availableHours.get();
					}
				}

				startPartHour = endPartHour;
			}

			for (int i = 0; i < workersInfo.size(); i++) {
				if (workerDaysPartStatus[i] != -1) {
					workerDaysCounter[i]++;
				}
			}
		}
	}

	private int findHardWorkerIndex(int requiredHours, AtomicInteger availableHours) {
		int workerIndex = -1;

		for (int i = 0; i < workersInfo.size(); i++) {
			ScheduleWorkerInfo worker = workersInfo.get(i);

			if (!worker.isHardWork()) {
				continue;
			}

			if (workerDaysCounter[i] >= 5) {
				continue;
			}

			if (worker.getDayFreeWorkHours() == 0 || workerDaysPartStatus[i] != -1) {
				continue;
			}

			if (worker.getDayFreeWorkHours() == requiredHours) {
				workerIndex = i;
				break;
			}

			if (workerIndex == -1) {
				workerIndex = i;
				continue;
			}

			if (worker.getDayFreeWorkHours() > workersInfo.get(workerIndex).getDayFreeWorkHours()) {
				workerIndex = i;
			}
		}

		if (workerIndex != -1) {
			availableHours.set(workersInfo.get(workerIndex).takeDayHours(requiredHours));
		}

		return workerIndex;
	}

	private int findFreeWorkerIndex(int requiredHours, AtomicInteger availableHours) {
		int workerIndex = -1;

		for (int i = 0;i < workersInfo.size();i++) {
			ScheduleWorkerInfo worker = workersInfo.get(i);

			if (workerDaysCounter[i] >= 5) {
				continue;
			}

			if (worker.getDayFreeWorkHours() == 0 || workerDaysPartStatus[i] != -1) {
				continue;
			}


			if (worker.getDayFreeWorkHours() == requiredHours) {
				workerIndex = i;
				break;
			}


			if (workerIndex == -1) {
				workerIndex = i;
				continue;
			}

			if (worker.getDayFreeWorkHours() > workersInfo.get(workerIndex).getDayFreeWorkHours()) {
				workerIndex = i;
			}
		}

		if (workerIndex != -1) {
			availableHours.set(workersInfo.get(workerIndex).takeDayHours(requiredHours));
		}

		return workerIndex;
	}

	public int findFreeWorkerIndex(int requiredHours, AtomicInteger availableHours, int withStatus) {
		int workerIndex = -1;

		for (int i = 0;i < workersInfo.size();i++) {
			ScheduleWorkerInfo worker = workersInfo.get(i);

			if (workerDaysCounter[i] >= 5) {
				continue;
			}

			if (worker.getDayFreeWorkHours() == 0 || workerDaysPartStatus[i] != withStatus) {
				continue;
			}


			if (worker.getDayFreeWorkHours() == requiredHours) {
				workerIndex = i;
				break;
			}


			if (workerIndex == -1) {
				workerIndex = i;
				continue;
			}

			if (worker.getDayFreeWorkHours() > workersInfo.get(workerIndex).getDayFreeWorkHours()) {
				workerIndex = i;
			}
		}

		if (workerIndex != -1) {
			availableHours.set(workersInfo.get(workerIndex).takeDayHours(requiredHours));
		}
		else {
			return findFreeWorkerIndex(requiredHours, availableHours);
		}

		return workerIndex;
	}

	public int findFreeWorkerIndex(int requiredHours, AtomicInteger availableHours, int dayIndex, int fromHour) {
		int workerIndex = -1;
		ScheduleDayManager daySchedule = days.get(dayIndex);

		for (int i = 0;i < workersInfo.size();i++) {
			ScheduleWorkerInfo worker = workersInfo.get(i);

			if (workerDaysCounter[i] >= 5) {
				continue;
			}

			if (worker.getRemainingTime().getHour() == 0) {
				continue;
			}

			if (daySchedule.getWorkerShift(i).getEndTime().getHour() == fromHour) {
				workerIndex = i;
				break;
			}
		}

		if (workerIndex != -1) {
			availableHours.set(workersInfo.get(workerIndex).takeHours(requiredHours));
		}

		return workerIndex;
	}
	/* --- main logic --- */

	/* --- private --- */
	private ArrayList<ScheduleDayManager> days;
	private ArrayList<ScheduleWorkerInfo> workersInfo;

	private final int[] workerDaysCounter;
	private final int[] workerDaysPartStatus;

	private final int BASIC_DISTRIBUTION_PARTS = 3;
	/* --- private --- */
}