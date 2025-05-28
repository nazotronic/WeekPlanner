/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Limitations;

import Backend.IManager;
import Backend.Limitations.Limits.LimitWrapper;
import Backend.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class LimitationsManager implements IManager, Iterable<LimitWrapper> {
	/* --- constructors --- */
	public LimitationsManager() {
		limits = new ArrayList<>();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void addLimit() {
		Logger.logInfo("Додавання обмеження до розкладу");
		limits.add(new LimitWrapper());
	}

	public void deleteLimit(LimitWrapper limit) {
		Logger.logInfo("Видалення обмеження з розкладу - " + limit.getLimitName());
		limits.remove(limit);
	}
	/* --- main logic --- */

	/* --- overrides --- */
	@Override
	public Iterator<LimitWrapper> iterator() {
		return limits.iterator();
	}
	/* --- overrides --- */

	/* --- getters --- */
	public int getLimitsCount() {
		return limits.size();
	}

	public LimitWrapper getLimit(int index) {
		if (index >= getLimitsCount()) {
			return null;
		}

		return limits.get(index);
	}
	/* --- getters --- */

	/* --- private --- */
	private final ArrayList<LimitWrapper> limits;
	/* --- private --- */
}
