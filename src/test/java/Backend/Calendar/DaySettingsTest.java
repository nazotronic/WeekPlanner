/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaySettingsTest {

	@Test
	void constructorWithManagerInitializesCorrectly() {
		DaySettings daySettings = new DaySettings(null);
		assertNotNull(daySettings);
		assertEquals(10, daySettings.getStartDayTime().getHour());
		assertEquals(20, daySettings.getEndDayTime().getHour());
		assertTrue(daySettings.isActiveFlag());
		assertFalse(daySettings.isMeetingFlag());
	}

	@Test
	void copyConstructorCopiesAllFields() {
		DaySettings original = new DaySettings(null);
		original.setName("Test Day");
		original.setActiveFlag(false);
		original.setStartDayTime(8);
		original.setEndDayTime(18);
		original.setMeetingFlag(true);
		original.setStartMeetingTime(12);
		original.setMeetingDurationTime(2);
		original.setMaxWorkersCount(5);
		original.setMinWorkersCount(3);
		original.setPrivilegedDay(true);
		original.setHardWorkReq(true);

		DaySettings copy = new DaySettings(null, original);

		assertEquals("Test Day", copy.getName());
		assertFalse(copy.isActiveFlag());
		assertEquals(8, copy.getStartDayTime().getHour());
		assertEquals(18, copy.getEndDayTime().getHour());
		assertTrue(copy.isMeetingFlag());
		assertEquals(12, copy.getStartMeetingTime().getHour());
		assertEquals(2, copy.getMeetingDurationTime().getHour());
		assertEquals(5, copy.getMaxWorkersCount());
		assertEquals(3, copy.getMinWorkersCount());
		assertTrue(copy.isPrivilegedDay());
		assertTrue(copy.isHardWorkReq());
	}

	@Test
	void settersUpdateFieldsCorrectly() {
		DaySettings daySettings = new DaySettings(null);

		daySettings.setName("New Name");
		assertEquals("New Name", daySettings.getName());

		daySettings.setActiveFlag(false);
		assertFalse(daySettings.isActiveFlag());

		daySettings.setStartDayTime(9);
		assertEquals(9, daySettings.getStartDayTime().getHour());

		daySettings.setEndDayTime(19);
		assertEquals(19, daySettings.getEndDayTime().getHour());

		daySettings.setMeetingFlag(true);
		assertTrue(daySettings.isMeetingFlag());

		daySettings.setStartMeetingTime(11);
		assertEquals(11, daySettings.getStartMeetingTime().getHour());

		daySettings.setMeetingDurationTime(3);
		assertEquals(3, daySettings.getMeetingDurationTime().getHour());

		daySettings.setMaxWorkersCount(6);
		assertEquals(6, daySettings.getMaxWorkersCount());

		daySettings.setMinWorkersCount(4);
		assertEquals(4, daySettings.getMinWorkersCount());

		daySettings.setPrivilegedDay(true);
		assertTrue(daySettings.isPrivilegedDay());

		daySettings.setHardWorkReq(true);
		assertTrue(daySettings.isHardWorkReq());
	}

	@Test
	void gettersReturnCorrectValues() {
		DaySettings daySettings = new DaySettings(null);

		assertEquals(10, daySettings.getStartDayTime().getHour());
		assertEquals(20, daySettings.getEndDayTime().getHour());
		assertTrue(daySettings.isActiveFlag());
		assertFalse(daySettings.isMeetingFlag());
		assertEquals(10, daySettings.getStartMeetingTime().getHour());
		assertEquals(1, daySettings.getMeetingDurationTime().getHour());
		assertEquals(4, daySettings.getMaxWorkersCount());
		assertEquals(2, daySettings.getMinWorkersCount());
		assertFalse(daySettings.isPrivilegedDay());
		assertFalse(daySettings.isHardWorkReq());
	}
}