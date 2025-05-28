/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Limitations;

import Backend.Limitations.Limits.LimitWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LimitationsManagerTest {

	@Test
	void testAddLimitIncreasesCount() {
		LimitationsManager manager = new LimitationsManager();
		int initialCount = manager.getLimitsCount();
		manager.addLimit();
		assertEquals(initialCount + 1, manager.getLimitsCount());
	}

	@Test
	void testDeleteLimitDecreasesCount() {
		LimitationsManager manager = new LimitationsManager();
		manager.addLimit();
		LimitWrapper limit = manager.getLimit(0);
		int countAfterAdd = manager.getLimitsCount();
		manager.deleteLimit(limit);
		assertEquals(countAfterAdd - 1, manager.getLimitsCount());
	}

	@Test
	void testDeleteNonexistentLimitDoesNothing() {
		LimitationsManager manager = new LimitationsManager();
		manager.addLimit();
		int countBefore = manager.getLimitsCount();
		manager.deleteLimit(new LimitWrapper());
		assertEquals(countBefore, manager.getLimitsCount());
	}

	@Test
	void testGetLimitReturnsCorrectLimit() {
		LimitationsManager manager = new LimitationsManager();
		manager.addLimit();
		LimitWrapper limit = manager.getLimit(0);
		assertNotNull(limit);
		assertEquals(limit, manager.getLimit(0));
	}

	@Test
	void testGetLimitOutOfBoundsReturnsNull() {
		LimitationsManager manager = new LimitationsManager();
		assertNull(manager.getLimit(0));
		manager.addLimit();
		assertNull(manager.getLimit(1));
	}

	@Test
	void testIteratorIteratesAllLimits() {
		LimitationsManager manager = new LimitationsManager();
		manager.addLimit();
		manager.addLimit();
		int count = 0;
		for (LimitWrapper limit : manager) {
			assertNotNull(limit);
			count++;
		}
		assertEquals(manager.getLimitsCount(), count);
	}
}
