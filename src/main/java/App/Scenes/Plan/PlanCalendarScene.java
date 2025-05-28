/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Scenes.Plan;

import App.Elements.AllScenes.PrevNextTitle;
import App.Elements.Calendar.DayButton;
import App.Scenes.AScene;
import App.Scenes.DaySettingsScene;
import App.Stages.StageManager;

import Backend.Calendar.CalendarManager;
import Backend.Calendar.DaySettings;
import Backend.MainManager;
import Backend.Plan.PlanManager;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class PlanCalendarScene extends AScene {
	public PlanCalendarScene(StageManager stageManager, MainManager mainManager) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.mainManager = mainManager;

		fillScene();
	}

	public void fillScene() {
		PlanManager plan = mainManager.getPlan();
		CalendarManager calendar = plan.getCalendar();

		ScrollPane root = (ScrollPane) this.getRoot();
		VBox mainContainer = new VBox();

		root.setContent(mainContainer);
		root.setFitToWidth(true);

		/* --- filling the mainContainer --- */
		/* PrevNextTitle */
		mainContainer.getChildren().add(new PrevNextTitle(
				"Календар",
				() -> {
					stageManager.goBack();
				},
				() -> {
					stageManager.setScene(new PlanStaffScene(stageManager, mainManager) );
				} ));
		/* PrevNextTitle */

		/* daysContainer */
		FlowPane daysContainer = new FlowPane();
		mainContainer.getChildren().add(daysContainer);

		for (int i = 0; i < calendar.getDaysCount(); i++) {
			final DaySettings day = calendar.getDay(i);

			daysContainer.getChildren().add(new DayButton(
					day.getName(),
					() -> {
						stageManager.setScene(new DaySettingsScene(stageManager, day));
					} ));
		}
		/* daysContainer */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/PlanCalendarScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		daysContainer.getStyleClass().add("days-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private MainManager mainManager;
}
