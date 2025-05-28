/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Calendar;

import Backend.Database.DBManager;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerTest {

	@Test
	void constructorWithDBInitializesEmpty() {
		CalendarManager manager = new CalendarManager((DBManager) null);
		assertEquals(0, manager.getDaysCount());
	}

	@Test
	void copyConstructorCopiesAllDays() {
		CalendarManager original = new CalendarManager((DBManager) null);
		original.syncFromDB(); // Will create default days

		CalendarManager copy = new CalendarManager(original);
		assertEquals(original.getDaysCount(), copy.getDaysCount());
	}

	@Test
	void syncFromDBWithNullDBDoesNothing() {
		CalendarManager manager = new CalendarManager((DBManager) null);
		manager.syncFromDB();
		assertEquals(0, manager.getDaysCount());
	}

	@Test
	void iteratorReturnsValidIterator() {
		CalendarManager manager = new CalendarManager((DBManager) null);
		Iterator<DaySettings> iterator = manager.iterator();
		assertNotNull(iterator);
		assertFalse(iterator.hasNext());
	}

	@Test
	void getDayReturnsNullForInvalidIndex() {
		CalendarManager manager = new CalendarManager((DBManager) null);
		assertNull(manager.getDay(0));
		assertNull(manager.getDay(1));
	}

	@Test
	void DBUpdateWithNullDBDoesNothing() {
		CalendarManager manager = new CalendarManager((DBManager) null);
		DaySettings day = new DaySettings(manager);
		manager.DBUpdate(day, "name", "Test"); // Should not throw exception
	}
}