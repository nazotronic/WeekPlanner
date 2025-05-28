/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RulesManagerTest {

	private RulesManager rulesManager;

	@BeforeEach
	public void setUp() {
		rulesManager = new RulesManager();
	}

	@Test
	public void testDefaultValues() {
		assertEquals(45, rulesManager.getMaxWeekHours());
		assertEquals(12, rulesManager.getMinFreeHours());
	}

	@Test
	public void testSetMaxWeekHours() {
		rulesManager.setMaxWeekHours(50);
		assertEquals(50, rulesManager.getMaxWeekHours());
	}

	@Test
	public void testSetMinFreeHours() {
		rulesManager.setMinFreeHours(10);
		assertEquals(10, rulesManager.getMinFreeHours());
	}
}
