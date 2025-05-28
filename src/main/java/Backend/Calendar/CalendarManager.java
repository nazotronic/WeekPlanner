/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Calendar;

import Backend.Database.DBManager;
import Backend.IManager;
import Backend.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class CalendarManager implements IManager, Iterable<DaySettings> {
	/* --- constructors --- */
	public CalendarManager(DBManager db) {
		days = new ArrayList<>();
		this.db = db;

		syncFromDB();
	}

	public CalendarManager(CalendarManager other) {
		days = new ArrayList<>();

		for (DaySettings day : other) {
			days.add(new DaySettings(this, day) );
		}
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void syncFromDB() {
		if (db != null) {
			int workersCount = db.getRowCount("calendar");
			Logger.logInfo("Синхронізація календаря з БД");
			days.clear();

			for (int i = 0; i < workersCount; i++) {
				DaySettings day;

				days.add(new DaySettings(this));
				day = days.get(i);

				day.setName(db.get(i + 1, "calendar", "name"));
				day.setActiveFlag(db.getBoolean(i + 1, "calendar", "active"));
				day.setStartDayTime(db.getTime(i + 1, "calendar", "start_day_time").getHour());
				day.setEndDayTime(db.getTime(i + 1, "calendar", "end_day_time").getHour());

				day.setMeetingFlag(db.getBoolean(i + 1, "calendar", "meeting"));
				day.setStartMeetingTime(db.getTime(i + 1, "calendar", "start_meeting_time").getHour());
				day.setMeetingDurationTime(db.getTime(i + 1, "calendar", "meeting_duration_time").getHour());

				day.setMaxWorkersCount(db.getInt(i + 1, "calendar", "max_workers_count"));
				day.setMinWorkersCount(db.getInt(i + 1, "calendar", "min_workers_count"));
				day.setPrivilegedDay(db.getBoolean(i + 1, "calendar", "privileged"));
				day.setHardWorkReq(db.getBoolean(i + 1, "calendar", "hard_work"));
			}
		}
	}
	/* --- main logic --- */

	/* --- overrides --- */
	@Override
	public void DBUpdate(Object object, String field, String value) {
		if (db != null) {
			db.update(
					days.indexOf(object) + 1,
					"calendar",
					field,
					value
			);
		}
	}

	@Override
	public Iterator<DaySettings> iterator() {
		return days.iterator();
	}
	/* --- overrides --- */

	/* --- getters --- */
	public int getDaysCount() {
		return days.size();
	}

	public DaySettings getDay(int index) {
		if (index >= getDaysCount()) {
			return null;
		}

		return days.get(index);
	}
	/* --- getters --- */

	/* --- private --- */
	private final ArrayList<DaySettings> days;
	private DBManager db;
	/* --- private --- */
}
