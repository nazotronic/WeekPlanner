/*
 * Project: Temperature Tick
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 02.03.2025
 */

package App.Elements.Plan;

import App.Elements.AllScenes.SettingChoice;
import App.Elements.AllScenes.SettingNumber;

import Backend.Calendar.CalendarManager;
import Backend.Calendar.DaySettings;
import Backend.Limitations.Limits.LimitVacation;
import Backend.Plan.PlanManager;
import Backend.Staff.StaffManager;
import Backend.Staff.WorkerSettings;

import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class LimitVacationView extends VBox {
	public LimitVacationView(LimitVacation limit, PlanManager plan) {
		StaffManager staff = plan.getStaff();
		CalendarManager calendar = plan.getCalendar();

		ArrayList<String> staffOptions = new ArrayList<>();
		ArrayList<String> calendarOptions = new ArrayList<>();

		for (WorkerSettings worker : staff) {
			staffOptions.add(worker.getFullName());
		}

		for (DaySettings day : calendar) {
			calendarOptions.add(day.getName());
		}

		/* --- filling this --- */
		this.getChildren().add(new SettingChoice(
				"Працівник",
				staffOptions,
				limit.getWorkerId(),
				value -> {
					limit.setWorkerId(value);
				} ));

		this.getChildren().add(new SettingChoice(
				"День",
				calendarOptions,
				limit.getDayId(),
				value -> {
					limit.setDayId(value);
				} ));

		this.getChildren().add(new SettingNumber(
				"Початок",
				String.valueOf(limit.getStart()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						limit.setStartTime(number);
					}
				} ));

		this.getChildren().add(new SettingNumber(
				"Кінець",
				String.valueOf(limit.getEnd()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						limit.setEndTime(number);
					}
				} ));
		/* --- filling this --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/LimitSettingsScene.css").toString());
		this.getStyleClass().add("limit-vacation-view");
		/* --- adding styles --- */
	}
}
