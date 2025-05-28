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
import App.Elements.Calendar.DayButton;
import App.Stages.StageManager;

import Backend.Calendar.CalendarManager;
import Backend.Calendar.DaySettings;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class GlobalCalendarScene extends AScene {
	public GlobalCalendarScene(StageManager stageManager, CalendarManager globalCalendar) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.globalCalendar = globalCalendar;

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
				"Загальний календар",
				() -> {
					stageManager.goBack();
				} ));
		/* ExitTitle */

		/* daysContainer */
		FlowPane daysContainer = new FlowPane();
		mainContainer.getChildren().add(daysContainer);

		for (int i = 0;i < globalCalendar.getDaysCount();i++) {
			final DaySettings globalDay = globalCalendar.getDay(i);

			daysContainer.getChildren().add(new DayButton(
					globalDay.getName(),
					() -> {
						stageManager.setScene(new DaySettingsScene(stageManager, globalDay));
					} ));
		}
		/* daysContainer */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/GlobalCalendarScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		daysContainer.getStyleClass().add("days-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private CalendarManager globalCalendar;
}
