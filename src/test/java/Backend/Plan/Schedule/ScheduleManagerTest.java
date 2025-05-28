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
import Backend.Database.DBManager;
import Backend.Limitations.LimitationsManager;
import Backend.Staff.StaffManager;
import Backend.Staff.WorkerSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleManagerTest {
	private ScheduleManager manager;
	private StaffManager staff;
	private CalendarManager calendar;
	private LimitationsManager limitations;

	@BeforeEach
	void setUp() {
		// Створюємо 2-х працівників
		staff = new StaffManager((DBManager) null);
		staff.addWorker();
		staff.addWorker();

		staff.getWorker(0).setName("John");
		staff.getWorker(0).setSurname("Doe");
		staff.getWorker(0).setMiddleName("A");

		staff.getWorker(1).setName("Jane");
		staff.getWorker(1).setSurname("Smith");
		staff.getWorker(1).setMiddleName("B");

		// Створюємо календар з 2-ма днями
		calendar = new CalendarManager((DBManager) null);

		// Порожні обмеження
		limitations = new LimitationsManager();

		manager = new ScheduleManager(calendar, staff, limitations);
	}

	@Test
	void testMakeWorkerSchedule() {
		ArrayList<WorkerShift> schedule = manager.makeWorkerSchedule(0);
		assertNotNull(schedule);
		assertEquals(2, schedule.size());
	}

	@Test
	void testMakeWorkerSchedule_OutOfBounds() {
		assertNull(manager.makeWorkerSchedule(100));
	}

	@Test
	void testGetWorkersCount() {
		assertEquals(2, manager.getWorkersCount());
	}

	@Test
	void testGetWorkerInfo() {
		assertNotNull(manager.getWorkerInfo(0));
		assertEquals("Doe J.A.", manager.getWorkerInfo(0).getFullName());
	}

	@Test
	void testGetWorkerInfo_OutOfBounds() {
		assertNull(manager.getWorkerInfo(5));
	}

	@Test
	void testAcceptSchedule() {
		StringBuilder log = new StringBuilder("LOG:\n");
		manager.acceptSchedule(log, staff);

		// Перевіримо, що зменшився час
		for (int i = 0; i < staff.getWorkersCount(); i++) {
			WorkerSettings worker = staff.getWorker(i);
			assertTrue(worker.getRemainingTime().getHour() < worker.getMonthWorkHours());
		}
	}
}
