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
import App.Elements.WorkerSettingsScene.LogButtons;
import App.Elements.WorkerSettingsScene.WorkerTimeInfo;

import App.Stages.SaveDialogStage;
import App.Stages.StageManager;

import Backend.Staff.WorkerSettings;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class WorkerSettingsScene extends AScene {
	public WorkerSettingsScene(StageManager stageManager, WorkerSettings worker) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.worker = worker;

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
				worker.getFullName(),
				() -> {
					stageManager.goBack();
				} ));
		/* ExitTitle */

		/* settingsContainer */
		VBox settingsContainer = new VBox();
		mainContainer.getChildren().add(settingsContainer);

		settingsContainer.getChildren().add(new WorkerTimeInfo(
				"Залишок часу",
				worker.getRemainingTime(),
				() -> {
					worker.resetRemainingTime();
					fillScene();
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Години на місяць",
				String.valueOf(worker.getMonthWorkHours()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						worker.setMonthWorkHours(number);
					}
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Прізвище",
				worker.getSurname(),
				value -> {
					if (!value.isEmpty()) {
						worker.setSurname(value);
						fillScene();
					}
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Ім'я",
				worker.getName(),
				value -> {
					if (!value.isEmpty()) {
						worker.setName(value);
						fillScene();
					}
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"По батькові",
				worker.getMiddleName(),
				value -> {
					if (!value.isEmpty()) {
						worker.setMiddleName(value);
						fillScene();
					}
				} ));

		settingsContainer.getChildren().add(new SettingCheckBox(
				"Можливість \"силової\" роботи",
				worker.isHardWork(),
				value -> {
					worker.setHardWork(value);
				} ));
		/* settingsContainer */

		/* logButtons */
		mainContainer.getChildren().add(new LogButtons(
				() -> {
					worker.clearLog();
				},
				() -> {
					new SaveDialogStage(
							stageManager,
							"Введіть шлях збереження:",
							value -> {
								if (!value.isEmpty()) {
									worker.saveLogAsPDF(value);
								}
							});
				}
		));
		/* logButtons */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/WorkerSettingsScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		settingsContainer.getStyleClass().add("settings-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private WorkerSettings worker;
}
