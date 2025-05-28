/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend;

import Backend.Calendar.CalendarManager;
import Backend.Database.DBManager;
import Backend.Plan.PlanManager;
import Backend.Rules.RulesManager;
import Backend.Staff.StaffManager;

public class MainManager implements IManager {
	/* --- constructors --- */
	public MainManager() {
		db = DBManager.getInstance();

		globalCalendar = new CalendarManager(db);
		globalStaff = new StaffManager(db);
		rules = new RulesManager();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void startPlan() {
		if (plan == null) {
			Logger.logInfo("Початок створення плану");
		}

		plan = (plan == null) ? new PlanManager(getGlobalCalendar(), getGlobalStaff()) : plan;
	}

	public void stopPlan() {
		Logger.logInfo("Кінець створення плану");
		plan.stopSchedule();
		plan = null;
	}

	public void acceptPlan(String planName) {
		Logger.logInfo("Затвердження плану");

		plan.acceptPlan(planName, globalStaff);
		stopPlan();
	}
	/* --- main logic --- */

	/* --- getters --- */
	public CalendarManager getGlobalCalendar() {
		return globalCalendar;
	}

	public StaffManager getGlobalStaff() {
		return globalStaff;
	}

	public RulesManager getRules() {
		return rules;
	}

	public PlanManager getPlan() {
		return plan;
	}
	/* --- getters --- */

	/* --- private --- */
	private final DBManager db;

	private final CalendarManager globalCalendar;
	private final StaffManager globalStaff;
	private final RulesManager rules;

	private PlanManager plan = null;
	/* --- private --- */
}
