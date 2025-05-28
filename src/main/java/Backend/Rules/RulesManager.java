/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Rules;

import Backend.IManager;

public class RulesManager implements IManager {
	/* --- constructors --- */
	public RulesManager() {

	}
	/* --- constructors --- */

	/* --- setters --- */
	public void setMaxWeekHours(int maxWeekHours) {
		this.maxWeekHours = maxWeekHours;
	}

	public void setMinFreeHours(int minFreeHours) {
		this.minFreeHours = minFreeHours;
	}
	/* --- setters --- */

	/* --- getters --- */
	public int getMaxWeekHours() {
		return maxWeekHours;
	}

	public int getMinFreeHours() {
		return minFreeHours;
	}
	/* --- getters --- */

	/* --- private --- */
	private int maxWeekHours = 45;
	private int minFreeHours = 12;
	/* --- getters --- */
}
