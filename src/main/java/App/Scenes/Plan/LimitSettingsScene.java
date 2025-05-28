/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Scenes.Plan;

import App.Elements.AllScenes.ExitTitle;
import App.Elements.AllScenes.SettingChoice;
import App.Elements.AllScenes.SettingNumber;
import App.Elements.Plan.LimitHospitalView;
import App.Elements.Plan.LimitVacationView;
import App.Scenes.AScene;
import App.Stages.StageManager;

import Backend.Limitations.Limits.LimitHospital;
import Backend.Limitations.Limits.LimitVacation;
import Backend.Limitations.Limits.LimitWrapper;
import Backend.Plan.PlanManager;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class LimitSettingsScene extends AScene {
	public LimitSettingsScene(StageManager stageManager, PlanManager plan, LimitWrapper limit) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.plan = plan;
		this.limit = limit;

		fillScene();
	}

	public void fillScene() {
		ArrayList<String> options = new ArrayList<>();
		options.add("Відпуск");
		options.add("Лікарняний");

		ScrollPane root = (ScrollPane) this.getRoot();
		VBox mainContainer = new VBox();

		root.setContent(mainContainer);
		root.setFitToWidth(true);

		/* --- filling the mainContainer --- */
		/* ExitTitle */
		mainContainer.getChildren().add(new ExitTitle(
				limit.getLimitName(),
				() -> {
					stageManager.goBack();
				} ));
		/* ExitTitle */

		/* settingsContainer */
		VBox settingsContainer = new VBox();
		mainContainer.getChildren().add(settingsContainer);

		settingsContainer.getChildren().add(new SettingNumber(
				"Назва",
				limit.getLimitName(),
				value -> {
					if (!value.isEmpty()) {
						limit.setLimitName(value);
						fillScene();
					}
				} ));

		settingsContainer.getChildren().add(new SettingChoice(
				"Обмеження",
				options,
				(limit.getLimit() instanceof LimitVacation) ? 0 : 1,
				value -> {
					switch (value) {
						case 0: limit.setLimit(new LimitVacation()); break;
						case 1: limit.setLimit(new LimitHospital()); break;
					}

					fillScene();
				} ));

		if (limit.getLimit() != null) {
			if (limit.getLimit() instanceof LimitVacation) {
				settingsContainer.getChildren().add(new LimitVacationView((LimitVacation) limit.getLimit(), plan));
			}

			if (limit.getLimit() instanceof LimitHospital) {
				settingsContainer.getChildren().add(new LimitHospitalView((LimitHospital) limit.getLimit(), plan));
			}
		}
		/* settingsContainer */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/LimitSettingsScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		settingsContainer.getStyleClass().add("settings-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private PlanManager plan;
	private LimitWrapper limit;
}
