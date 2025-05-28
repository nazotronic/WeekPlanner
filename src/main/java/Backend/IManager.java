/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend;

import Backend.Elements.Time;

public interface IManager {
	public default void DBUpdate(Object object, String field, String value) {

	}
	public default void DBUpdate(Object object, String field, int value) {
		DBUpdate(object, field, String.valueOf(value));
	}
	public default void DBUpdate(Object object, String field, boolean value) {
		int intValue = (value) ? 1 : 0;
		DBUpdate(object, field, String.valueOf(intValue));
	}
	public default void DBUpdate(Object object, String field, Time value) {
		DBUpdate(object, field, value.toString());
	}

	public default void DBAdd(Object object, String field, String value) {

	}

	public default String DBGet(Object object, String field) {
		return null;
	}
}
