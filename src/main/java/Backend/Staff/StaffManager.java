/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Staff;

import Backend.Database.DBManager;
import Backend.IManager;
import Backend.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class StaffManager implements IManager, Iterable<WorkerSettings> {
	/* --- constructors --- */
	public StaffManager(DBManager db) {
		workers = new ArrayList<>();
		this.db = db;

		syncFromDB();
	}

	public StaffManager(StaffManager other) {
		workers = new ArrayList<>();

		for (WorkerSettings worker : other) {
			workers.add(new WorkerSettings(worker) );
		}
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void syncFromDB() {
		if (db != null) {
			int workersCount = db.getRowCount("staff");
			Logger.logInfo("Синхронізація працівників з БД");
			workers.clear();

			for (int i = 0; i < workersCount; i++) {
				WorkerSettings worker;

				workers.add(new WorkerSettings(this));
				worker = workers.get(i);

				worker.setMonthWorkHours(db.getInt(i + 1, "staff", "month_work_hours"));
				worker.setRemainingTime(db.getTime(i + 1, "staff", "remaining_time"));

				worker.setName(db.get(i + 1, "staff", "name"));
				worker.setSurname(db.get(i + 1, "staff", "surname"));
				worker.setMiddleName(db.get(i + 1, "staff", "middle_name"));

				worker.setHardWork(db.getBoolean(i + 1, "staff", "hard_work"));
			}
		}
	}

	public void addWorker() {
		Logger.logInfo("Додавання працівника");

		workers.add(new WorkerSettings(this));
		WorkerSettings newWorker = workers.get(getWorkersCount() - 1);

		if (db != null) {
			db.insert(
					"staff",
					"name, surname, middle_name, month_work_hours, remaining_time, hard_work, log",
					newWorker.getName(),
					newWorker.getSurname(),
					newWorker.getMiddleName(),
					String.valueOf(newWorker.getMonthWorkHours()),
					newWorker.getRemainingTime().toString(),
					String.valueOf((newWorker.isHardWork()) ? 1 : 0),
					"");
		}
	}

	public void deleteWorker(WorkerSettings worker) {
		Logger.logInfo("Видалення працівника - " + worker.getFullName());

		if (db != null) {
			db.delete(workers.indexOf(worker) + 1, "staff");
		}

		workers.remove(worker);
	}
	/* --- main logic --- */

	/* --- overrides --- */
	@Override
	public void DBUpdate(Object object, String field, String value) {
		if (db != null) {
			db.update(
					workers.indexOf(object) + 1,
					"staff",
					field,
					value
			);
		}
	}

	@Override
	public void DBAdd(Object object, String field, String value) {
		if (db != null) {
			db.add(
					workers.indexOf(object) + 1,
					"staff",
					field,
					value
			);
		}
	}

	@Override
	public String DBGet(Object object, String field) {
		if (db != null) {
			return db.get(
					workers.indexOf(object) + 1,
					"staff",
					field
			);
		}

		return null;
	}

	@Override
	public Iterator<WorkerSettings> iterator() {
		return workers.iterator();
	}
	/* --- overrides --- */

	/* --- getters --- */
	public int getWorkersCount() {
		return workers.size();
	}

	public WorkerSettings getWorker(int index) {
		if (index >= getWorkersCount()) {
			return null;
		}

		return workers.get(index);
	}
	/* --- getters --- */

	/* --- private --- */
	private final ArrayList<WorkerSettings> workers;
	private DBManager db;
	/* --- private --- */
}
