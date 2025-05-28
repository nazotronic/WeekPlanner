/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend;

import Backend.Elements.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IManagerTest {

	// Create a simple test implementation of IManager
	static class TestManager implements IManager {
		// We'll override methods to track calls
		public String lastField;
		public Object lastObject;
		public String lastStringValue;
		public int lastIntValue;
		public boolean lastBoolValue;
		public Time lastTimeValue;

		@Override
		public void DBUpdate(Object object, String field, String value) {
			lastObject = object;
			lastField = field;
			lastStringValue = value;
		}

		@Override
		public String DBGet(Object object, String field) {
			return "test_value";
		}
	}

	@Test
	void DBUpdateStringCallsImplementation() {
		TestManager manager = new TestManager();
		Object testObj = new Object();

		manager.DBUpdate(testObj, "test_field", "test_value");

		assertEquals(testObj, manager.lastObject);
		assertEquals("test_field", manager.lastField);
		assertEquals("test_value", manager.lastStringValue);
	}

	@Test
	void DBUpdateIntCallsStringVersion() {
		TestManager manager = new TestManager();
		Object testObj = new Object();

		manager.DBUpdate(testObj, "int_field", 42);

		assertEquals(testObj, manager.lastObject);
		assertEquals("int_field", manager.lastField);
		assertEquals("42", manager.lastStringValue);
	}

	@Test
	void DBUpdateBooleanCallsStringVersion() {
		TestManager manager = new TestManager();
		Object testObj = new Object();

		manager.DBUpdate(testObj, "bool_field", true);

		assertEquals(testObj, manager.lastObject);
		assertEquals("bool_field", manager.lastField);
		assertEquals("1", manager.lastStringValue);
	}

	@Test
	void DBUpdateTimeCallsStringVersion() {
		TestManager manager = new TestManager();
		Object testObj = new Object();
		Time testTime = new Time(10, 30);

		manager.DBUpdate(testObj, "time_field", testTime);

		assertEquals(testObj, manager.lastObject);
		assertEquals("time_field", manager.lastField);
		assertEquals("10:30:00", manager.lastStringValue);
	}

	@Test
	void defaultDBAddDoesNothing() {
		IManager manager = new IManager() {};
		// Should not throw exception
		manager.DBAdd(new Object(), "field", "value");
	}

	@Test
	void defaultDBGetReturnsNull() {
		IManager manager = new IManager() {};
		assertNull(manager.DBGet(new Object(), "field"));
	}
}