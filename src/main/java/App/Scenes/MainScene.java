/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Scenes;

import App.Elements.MainScene.CardButton;
import App.Scenes.Plan.PlanCalendarScene;
import App.Stages.StageManager;
import Backend.MainManager;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class MainScene extends AScene {
	public MainScene(StageManager stageManager, MainManager mainManager) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.mainManager = mainManager;

		fillScene();
	}

	public void fillScene() {
		ScrollPane root = (ScrollPane) this.getRoot();
		VBox mainContainer = new VBox();

		root.setContent(mainContainer);
		root.setFitToWidth(true);

		/* --- filling the mainContainer --- */
		/* cardsContainer */
		FlowPane cardsContainer = new FlowPane();
		mainContainer.getChildren().add(cardsContainer);

		cardsContainer.getChildren().add(new CardButton(
				"Працівники",
				"/images/MainScene/workers.png",
				() -> {
					stageManager.setScene(new GlobalStaffScene(stageManager, mainManager.getGlobalStaff()) );
				} ));

		cardsContainer.getChildren().add(new CardButton(
				"Дні",
				"/images/MainScene/calendar.png",
				() -> {
					stageManager.setScene(new GlobalCalendarScene(stageManager, mainManager.getGlobalCalendar()) );
				} ));

		cardsContainer.getChildren().add(new CardButton(
				"Правила",
				"/images/MainScene/rules.png",
				() -> {
					stageManager.setScene(new RulesScene(stageManager, mainManager.getRules()) );
				} ));

		cardsContainer.getChildren().add(new CardButton(
				(mainManager.getPlan() == null) ? "Зробити план" : "Коригувати план",
				"/images/MainScene/plan.png",
				() -> {
					mainManager.startPlan();
					stageManager.setScene(new PlanCalendarScene(stageManager, mainManager) );
				} ));
		/* cardsContainer */
		/* --- filling the root --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/MainScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		cardsContainer.getStyleClass().add("cards-container");
		/* --- adding styles --- */
	}


	StageManager stageManager;
	MainManager mainManager;
}
