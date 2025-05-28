/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeTest {

	@Test
	void defaultConstructorInitializesToZero() {
		Time time = new Time();
		assertEquals(0, time.getHour());
		assertEquals(0, time.getMinute());
	}

	@Test
	void hourConstructorSetsCorrectTime() {
		Time time = new Time(5);
		assertEquals(5, time.getHour());
		assertEquals(0, time.getMinute());
	}

	@Test
	void stringConstructorParsesCorrectly() {
		Time time = new Time("12:30");
		assertEquals(12, time.getHour());
		assertEquals(30, time.getMinute());
	}

	@Test
	void copyConstructorCopiesValues() {
		Time original = new Time(8, 45);
		Time copy = new Time(original);
		assertEquals(8, copy.getHour());
		assertEquals(45, copy.getMinute());
	}

	@Test
	void plusHourAddsHours() {
		Time time = new Time(10);
		time.plusHour(3);
		assertEquals(13, time.getHour());
	}

	@Test
	void minusHourSubtractsHours() {
		Time time = new Time(10);
		time.minusHour(3);
		assertEquals(7, time.getHour());
	}

	@Test
	void minusHourDoesNotGoBelowZero() {
		Time time = new Time(2);
		time.minusHour(3);
		assertEquals(2, time.getHour()); // Should not change
	}

	@Test
	void copyCopiesAllValues() {
		Time source = new Time(9, 15);
		Time target = new Time();
		target.copy(source);
		assertEquals(9, target.getHour());
		assertEquals(15, target.getMinute());
	}

	@Test
	void toStringReturnsCorrectFormat() {
		Time time = new Time(14, 5);
		assertEquals("14:5:00", time.toString());
	}

	@Test
	void settersUpdateValues() {
		Time time = new Time();
		time.setHour(7);
		time.setMinute(30);
		assertEquals(7, time.getHour());
		assertEquals(30, time.getMinute());
	}
}