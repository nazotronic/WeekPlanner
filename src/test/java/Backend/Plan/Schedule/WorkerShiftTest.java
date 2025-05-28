/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Plan.Schedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerShiftTest {

	private WorkerShift shift;

	@BeforeEach
	public void setUp() {
		shift = new WorkerShift();
	}

	@Test
	public void testDefaultConstructor() {
		assertNotNull(shift.getStartTime());
		assertNotNull(shift.getEndTime());
		assertFalse(shift.isInitialized());
	}

	@Test
	public void testSetShiftFirstTime() {
		shift.setShift(8, 16);
		assertEquals(8, shift.getStartTime().getHour());
		assertEquals(16, shift.getEndTime().getHour());
		assertTrue(shift.isInitialized());
	}

	@Test
	public void testSetShiftExtendsEarlierAndLater() {
		shift.setShift(9, 17);
		shift.setShift(8, 18);
		assertEquals(8, shift.getStartTime().getHour());
		assertEquals(18, shift.getEndTime().getHour());
	}

	@Test
	public void testIsPeriodInShiftInside() {
		shift.setShift(8, 16);
		assertEquals(0, shift.isPeriodInShift(9, 4)); // 9-13 is fully inside 8-16
	}

	@Test
	public void testIsPeriodInShiftPartialStart() {
		shift.setShift(8, 16);
		assertEquals(1, shift.isPeriodInShift(7, 4)); // 7-11 starts before
	}

	@Test
	public void testIsPeriodInShiftPartialEnd() {
		shift.setShift(8, 16);
		assertEquals(1, shift.isPeriodInShift(14, 4)); // 14-18 ends after
	}

	@Test
	public void testIsPeriodInShiftOutside() {
		shift.setShift(8, 16);
		assertEquals(2, shift.isPeriodInShift(6, 12)); // 6-18, overshoots both ends
	}

	@Test
	public void testClearPeriodInShiftFullyInside() {
		shift.setShift(8, 16);
		int cleared = shift.clearPeriodInShift(9, 15);
		assertEquals(8, cleared); // Full shift cleared
		assertFalse(shift.isInitialized());
		assertEquals(0, shift.getStartTime().getHour());
		assertEquals(0, shift.getEndTime().getHour());
	}

	@Test
	public void testClearPeriodInShiftCoversAll() {
		shift.setShift(8, 16);
		int cleared = shift.clearPeriodInShift(6, 18);
		assertEquals(8, cleared); // All shift removed
		assertFalse(shift.isInitialized());
		assertEquals(0, shift.getStartTime().getHour());
		assertEquals(0, shift.getEndTime().getHour());
	}

	@Test
	public void testClearPeriodInShiftCutLeft() {
		shift.setShift(8, 16);
		int cleared = shift.clearPeriodInShift(6, 10);
		assertEquals(2, cleared); // 8->10 removed
		assertEquals(10, shift.getStartTime().getHour());
	}

	@Test
	public void testClearPeriodInShiftCutRight() {
		shift.setShift(8, 16);
		int cleared = shift.clearPeriodInShift(14, 18);
		assertEquals(2, cleared); // 14->16 removed
		assertEquals(14, shift.getEndTime().getHour());
	}

	@Test
	public void testToString() {
		shift.setShift(9, 17);
		String expected = "09:00 - 17:00";
		assertEquals(expected, shift.toString());
	}
}
