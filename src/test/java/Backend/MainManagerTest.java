/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend;

import Backend.Plan.PlanManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainManagerTest {
	private MainManager mainManager;

	@BeforeEach
	public void setUp() {
		mainManager = new MainManager();
	}

	@Test
	public void testStartPlan_CreatesPlanIfNull() {
		assertNull(mainManager.getPlan());

		mainManager.startPlan();

		assertNotNull(mainManager.getPlan());
	}

	@Test
	public void testStartPlan_DoesNotOverwriteExistingPlan() {
		mainManager.startPlan();
		PlanManager originalPlan = mainManager.getPlan();

		mainManager.startPlan();
		assertSame(originalPlan, mainManager.getPlan());
	}

	@Test
	public void testStopPlan_SetsPlanToNull() {
		mainManager.startPlan();
		assertNotNull(mainManager.getPlan());

		mainManager.stopPlan();
		assertNull(mainManager.getPlan());
	}

	@Test
	public void testAcceptPlan_StopsPlan() {
		mainManager.startPlan();
		PlanManager planBefore = mainManager.getPlan();
		planBefore.startSchedule();

		assertNotNull(planBefore);
		mainManager.acceptPlan("Test Plan");

		assertNull(mainManager.getPlan());
	}

	@Test
	public void testGetGlobalCalendar_NotNull() {
		assertNotNull(mainManager.getGlobalCalendar());
	}

	@Test
	public void testGetGlobalStaff_NotNull() {
		assertNotNull(mainManager.getGlobalStaff());
	}

	@Test
	public void testGetRules_NotNull() {
		assertNotNull(mainManager.getRules());
	}
}
