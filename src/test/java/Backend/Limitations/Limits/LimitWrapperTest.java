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
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LimitWrapperTest {

	@Test
	void testDefaultValues() {
		LimitWrapper wrapper = new LimitWrapper();
		assertEquals("Обмеження", wrapper.getLimitName());
		assertNotNull(wrapper.getLimit());
	}

	@Test
	void testSetAndGetLimitName() {
		LimitWrapper wrapper = new LimitWrapper();
		wrapper.setLimitName("Test Limit");
		assertEquals("Test Limit", wrapper.getLimitName());
	}

	@Test
	void testSetAndGetLimit() {
		LimitWrapper wrapper = new LimitWrapper();
		LimitVacation limitVacation = new LimitVacation();
		limitVacation.setDayId(1);
		limitVacation.setWorkerId(2);
		wrapper.setLimit(limitVacation);
		assertEquals(limitVacation, wrapper.getLimit());
		assertEquals(1, wrapper.getLimit().getDayId());
		assertEquals(2, wrapper.getLimit().getWorkerId());
	}
}
