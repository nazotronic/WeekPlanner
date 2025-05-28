/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Staff;

import Backend.Database.DBManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffManagerTest {
	private StaffManager manager;

	@BeforeEach
	void setUp() {
		manager = new StaffManagerStub(); // Клас-стік замість DBManager
	}

	@Test
	void testConstructorCopy() {
		manager.addWorker();
		WorkerSettings original = manager.getWorker(0);
		original.setName("Іван");
		original.setSurname("Іванов");
		original.setMonthWorkHours(160);
		original.setHardWork(true);

		StaffManager copy = new StaffManager(manager);
		assertEquals(1, copy.getWorkersCount());

		WorkerSettings copied = copy.getWorker(0);
		assertEquals("Іван", copied.getName());
		assertEquals("Іванов", copied.getSurname());
		assertEquals(160, copied.getMonthWorkHours());
		assertTrue(copied.isHardWork());
	}

	@Test
	void testAddWorkerIncreasesCount() {
		int before = manager.getWorkersCount();
		manager.addWorker();
		int after = manager.getWorkersCount();
		assertEquals(before + 1, after);
	}

	@Test
	void testDeleteWorkerDecreasesCount() {
		manager.addWorker();
		WorkerSettings worker = manager.getWorker(0);
		manager.deleteWorker(worker);
		assertEquals(0, manager.getWorkersCount());
	}

	@Test
	void testGetWorkerValidIndex() {
		manager.addWorker();
		WorkerSettings worker = manager.getWorker(0);
		assertNotNull(worker);
	}

	@Test
	void testGetWorkerInvalidIndex() {
		WorkerSettings worker = manager.getWorker(99);
		assertNull(worker);
	}

	@Test
	void testIterableReturnsCorrectCount() {
		manager.addWorker();
		manager.addWorker();
		int count = 0;
		for (WorkerSettings w : manager) {
			count++;
		}
		assertEquals(2, count);
	}

	static class StaffManagerStub extends StaffManager {
		public StaffManagerStub() {
			super((DBManager) null);
		}

		@Override
		public void syncFromDB() {
		}

		@Override
		public void DBUpdate(Object object, String field, String value) {
		}

		@Override
		public void DBAdd(Object object, String field, String value) {
		}

		@Override
		public String DBGet(Object object, String field) {
			return "";
		}
	}

	static class DummyDBManager extends Backend.Database.DBManager {
		public DummyDBManager() {
			super();


		}
	}
}
