/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Limitations.Limits;

public class LimitWrapper {
	/* --- constructors --- */
	public LimitWrapper() {

	}
	/* --- constructors --- */

	/* --- setters --- */
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}

	public void setLimit(ILimit limit) {
		this.limit = limit;
	}
	/* --- setters --- */

	/* --- getters --- */
	public String getLimitName() {
		return limitName;
	}

	public ILimit getLimit() {
		return limit;
	}
	/* --- getters --- */

	/* --- private --- */
	String limitName = "Обмеження";
	ILimit limit = new LimitVacation();
	/* --- private --- */
}
