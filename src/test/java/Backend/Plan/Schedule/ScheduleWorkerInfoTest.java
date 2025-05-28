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
import Backend.IManager;
import Backend.Staff.WorkerSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleWorkerInfoTest {

	private ScheduleWorkerInfo info;
	private WorkerSettings worker;

	@BeforeEach
	public void setUp() {
		worker = new WorkerSettings((IManager) null);
		worker.setMonthWorkHours(160);  // => dayWorkHours = 8
		info = new ScheduleWorkerInfo(worker);
	}

	@Test
	public void testConstructorInitializesCorrectly() {
		assertEquals(worker.getFullName(), info.getFullName());
		assertEquals(worker.isHardWork(), info.isHardWork());
		assertEquals(new Time(worker.getRemainingTime()), info.getRemainingTime());
		assertEquals(0, info.getTotalTime().getHour());
		assertTrue(info.getRemarks().isEmpty());
	}

	@Test
	public void testStartDayInitializesDayFreeWorkHours() {
		info.startDay();
		assertEquals(8, info.getDayFreeWorkHours());
	}

	@Test
	public void testTakeDayHoursLimitsToDayFreeWorkHours() {
		info.startDay(); // dayFreeWorkHours = 8
		int taken = info.takeDayHours(6);
		assertEquals(6, taken);
		assertEquals(2, info.getDayFreeWorkHours());
	}

	@Test
	public void testTakeDayHoursCapsAtRemainingTime() {
		worker.setMonthWorkHours(16); // dayWorkHours = 0
		ScheduleWorkerInfo smallInfo = new ScheduleWorkerInfo(worker);
		smallInfo.startDay();
		int taken = smallInfo.takeDayHours(5);
		assertEquals(0, taken);
	}

	@Test
	public void testTakeHoursReducesRemainingTimeAndAddsToTotal() {
		info.startDay();
		int remainingBefore = info.getRemainingTime().getHour();
		int taken = info.takeHours(5);
		assertEquals(5, taken);
		assertEquals(remainingBefore - 5, info.getRemainingTime().getHour());
		assertEquals(5, info.getTotalTime().getHour());
	}

	@Test
	public void testTakeHoursGeneratesRemarkIfOverLimit() {
		info.startDay();
		info.takeDayHours(10); // request more than allowed
		ArrayList<String> remarks = info.getRemarks();
		assertFalse(remarks.isEmpty());
		assertTrue(remarks.get(0).contains("Понаднормова"));
	}

	@Test
	public void testAddHoursUpdatesStateCorrectly() {
		info.startDay();
		info.takeDayHours(6);
		info.addHours(2);
		assertEquals(4, info.getDayFreeWorkHours()); // originally 8 - 6 + 2
		assertEquals(2, info.getRemainingTime().getHour() % 160); // total change should reflect
		assertEquals(4, info.getTotalTime().getHour()); // was 6, minus 2
	}

	@Test
	public void testAddRemark() {
		info.addRemark("Test remark");
		assertEquals(1, info.getRemarks().size());
		assertEquals("Test remark", info.getRemarks().get(0));
	}
}
