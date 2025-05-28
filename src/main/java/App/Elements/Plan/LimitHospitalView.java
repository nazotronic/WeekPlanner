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
import Backend.Calendar.CalendarManager;
import Backend.Calendar.DaySettings;
import Backend.Limitations.Limits.LimitHospital;
import Backend.Plan.PlanManager;
import Backend.Staff.StaffManager;
import Backend.Staff.WorkerSettings;

import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class LimitHospitalView extends VBox {
	public LimitHospitalView(LimitHospital limit, PlanManager plan) {
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
		/* --- filling this --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/LimitSettingsScene.css").toString());
		this.getStyleClass().add("limit-hospital-view");
		/* --- adding styles --- */
	}
}