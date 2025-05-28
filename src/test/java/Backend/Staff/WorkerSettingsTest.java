/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Staff;

import Backend.Elements.Time;
import Backend.IManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerSettingsTest {

	private WorkerSettings worker;

	@BeforeEach
	public void setUp() {
		worker = new WorkerSettings((IManager) null);
	}

	@Test
	public void testDefaultValues() {
		assertEquals(160, worker.getMonthWorkHours());
		assertEquals("n", worker.getName());
		assertEquals("surname", worker.getSurname());
		assertEquals("mn", worker.getMiddleName());
		assertFalse(worker.isHardWork());
		assertNotNull(worker.getRemainingTime());
	}

	@Test
	public void testSettersAndGetters() {
		worker.setMonthWorkHours(120);
		assertEquals(120, worker.getMonthWorkHours());

		Time time = new Time(100);
		worker.setRemainingTime(time);
		assertEquals(time, worker.getRemainingTime());

		worker.setName("Ivan");
		worker.setSurname("Petrenko");
		worker.setMiddleName("Ivanovych");
		worker.setHardWork(true);

		assertEquals("Ivan", worker.getName());
		assertEquals("Petrenko", worker.getSurname());
		assertEquals("Ivanovych", worker.getMiddleName());
		assertTrue(worker.isHardWork());
	}

	@Test
	public void testFullName() {
		worker.setName("Olena");
		worker.setSurname("Shevchenko");
		worker.setMiddleName("Mykolayivna");

		assertEquals("Shevchenko O.M.", worker.getFullName());
	}

	@Test
	public void testCopyConstructor() {
		worker.setName("A");
		worker.setSurname("B");
		worker.setMiddleName("C");
		worker.setMonthWorkHours(100);
		worker.setHardWork(true);
		worker.setRemainingTime(new Time(50));

		WorkerSettings copy = new WorkerSettings(worker);

		assertEquals("A", copy.getName());
		assertEquals("B", copy.getSurname());
		assertEquals("C", copy.getMiddleName());
		assertEquals(100, copy.getMonthWorkHours());
		assertTrue(copy.isHardWork());
		assertEquals(50, copy.getRemainingTime().getHour());
	}

	@Test
	public void testRemainingTimeOperations() {
		Time t = new Time(160);
		worker.setRemainingTime(t);

		worker.minusRemainingTime(new Time(10));
		assertEquals(150, worker.getRemainingTime().getHour());

		worker.resetRemainingTime();
		assertEquals(worker.getMonthWorkHours(), worker.getRemainingTime().getHour());
	}

	@Test
	public void testSaveLogAsPDFCreatesFile() throws Exception {
		IManager fakeManager = new IManager() {
			@Override
			public void DBUpdate(Object object, String field, String value) {}
			@Override
			public void DBAdd(Object object, String field, String value) {}
			@Override
			public String DBGet(Object object, String field) {
				return "This is a test log.";
			}
		};

		WorkerSettings localWorker = new WorkerSettings(fakeManager);
		localWorker.setName("Test");

		File tempDir = Files.createTempDirectory("test_pdf").toFile();
		localWorker.saveLogAsPDF(tempDir.getAbsolutePath());

		File pdf = new File(tempDir, "Test_log.pdf");
		assertTrue(pdf.exists());
		assertTrue(pdf.length() > 0);

		pdf.delete();
		tempDir.delete();
	}
}
