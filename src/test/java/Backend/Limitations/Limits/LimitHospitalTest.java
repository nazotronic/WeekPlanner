/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Limitations.Limits;

import Backend.Elements.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LimitHospitalTest {

	@Test
	void testSetAndGetWorkerId() {
		LimitHospital limitHospital = new LimitHospital();
		limitHospital.setWorkerId(5);
		assertEquals(5, limitHospital.getWorkerId());
	}

	@Test
	void testSetAndGetDayId() {
		LimitHospital limitHospital = new LimitHospital();
		limitHospital.setDayId(7);
		assertEquals(7, limitHospital.getDayId());
	}

	@Test
	void testGetStartReturnsZeroHourTime() {
		LimitHospital limitHospital = new LimitHospital();
		Time start = limitHospital.getStart();
		assertNotNull(start);
		assertEquals(0, start.getHour());
	}

	@Test
	void testGetEndReturns24HourTime() {
		LimitHospital limitHospital = new LimitHospital();
		Time end = limitHospital.getEnd();
		assertNotNull(end);
		assertEquals(24, end.getHour());
	}
}
