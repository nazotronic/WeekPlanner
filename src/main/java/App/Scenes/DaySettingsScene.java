/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Scenes;

import App.Elements.AllScenes.ExitTitle;
import App.Elements.AllScenes.SettingCheckBox;
import App.Elements.AllScenes.SettingNumber;
import App.Stages.StageManager;

import Backend.Calendar.DaySettings;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class DaySettingsScene extends AScene {
	public DaySettingsScene(StageManager stageManager, DaySettings day) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.day = day;

		fillScene();
	}

	public void fillScene() {
		ScrollPane root = (ScrollPane) this.getRoot();
		VBox mainContainer = new VBox();

		root.setContent(mainContainer);
		root.setFitToWidth(true);

		/* --- filling the mainContainer --- */
		/* ExitTitle */
		mainContainer.getChildren().add(new ExitTitle(
				day.getName(),
				() -> {
					stageManager.goBack();
				} ));
		/* ExitTitle */

		/* settingsContainer */
		VBox settingsContainer = new VBox();
		mainContainer.getChildren().add(settingsContainer);

		settingsContainer.getChildren().add(new SettingCheckBox(
				"Активний",
				day.isActiveFlag(),
				value -> {
					day.setActiveFlag(value);
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Початок робочого дня",
				String.valueOf(day.getStartDayTime()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						day.setStartDayTime(number);
					}
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Кінець робочого дня",
				String.valueOf(day.getEndDayTime()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						day.setEndDayTime(number);
					}
				} ));

		settingsContainer.getChildren().add(new SettingCheckBox(
				"Збори",
				day.isMeetingFlag(),
				value -> {
					day.setMeetingFlag(value);
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Початок зборів",
				String.valueOf(day.getStartMeetingTime()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						day.setStartMeetingTime(number);
					}
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Тривалість зборів",
				String.valueOf(day.getMeetingDurationTime()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						day.setMeetingDurationTime(number);
					}
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Макс. к-ксть. працівників",
				String.valueOf(day.getMaxWorkersCount()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						day.setMaxWorkersCount(number);
					}
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Мін. к-ксть. працівників",
				String.valueOf(day.getMinWorkersCount()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						day.setMinWorkersCount(number);
					}
				} ));

		settingsContainer.getChildren().add(new SettingCheckBox(
				"Привілейований день",
				day.isPrivilegedDay(),
				value -> {
					day.setPrivilegedDay(value);
				} ));

		settingsContainer.getChildren().add(new SettingCheckBox(
				"Необхідність \"силової\" роботи",
				day.isHardWorkReq(),
				value -> {
					day.setHardWorkReq(value);
				} ));
		/* settingsContainer */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/DaySettingsScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		settingsContainer.getStyleClass().add("settings-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private DaySettings day;
}
