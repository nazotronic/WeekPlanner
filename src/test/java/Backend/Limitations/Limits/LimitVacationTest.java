/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Limitations.Limits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LimitVacationTest {

	@Test
	void testSetAndGetStartTime() {
		LimitVacation limitVacation = new LimitVacation();
		limitVacation.setStartTime(8);
		assertEquals(8, limitVacation.getStart().getHour());
	}

	@Test
	void testSetAndGetEndTime() {
		LimitVacation limitVacation = new LimitVacation();
		limitVacation.setEndTime(17);
		assertEquals(17, limitVacation.getEnd().getHour());
	}

	@Test
	void testSetAndGetWorkerId() {
		LimitVacation limitVacation = new LimitVacation();
		limitVacation.setWorkerId(3);
		assertEquals(3, limitVacation.getWorkerId());
	}

	@Test
	void testSetAndGetDayId() {
		LimitVacation limitVacation = new LimitVacation();
		limitVacation.setDayId(2);
		assertEquals(2, limitVacation.getDayId());
	}

	@Test
	void testDefaultStartAndEndTime() {
		LimitVacation limitVacation = new LimitVacation();
		assertEquals(0, limitVacation.getStart().getHour());
		assertEquals(0, limitVacation.getEnd().getHour());
	}
}
