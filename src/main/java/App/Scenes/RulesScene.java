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
import App.Elements.AllScenes.SettingNumber;

import App.Stages.StageManager;
import Backend.Rules.RulesManager;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class RulesScene extends AScene {
	public RulesScene(StageManager stageManager, RulesManager rules) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.rules = rules;

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
				"Правила",
				() -> {
					stageManager.goBack();
				} ));
		/* ExitTitle */

		/* settingsContainer */
		VBox settingsContainer = new VBox();
		mainContainer.getChildren().add(settingsContainer);

		settingsContainer.getChildren().add(new SettingNumber(
				"Макс. годин в тиждень",
				String.valueOf(rules.getMaxWeekHours()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						rules.setMaxWeekHours(number);
					}
				} ));

		settingsContainer.getChildren().add(new SettingNumber(
				"Мін. годин між змінами",
				String.valueOf(rules.getMinFreeHours()),
				value -> {
					if (!value.isEmpty()) {
						int number = Integer.parseInt(value);
						rules.setMinFreeHours(number);
					}
				} ));


		/* settingsContainer */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/RulesScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		settingsContainer.getStyleClass().add("settings-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private RulesManager rules;
}