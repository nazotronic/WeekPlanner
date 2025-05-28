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
import App.Elements.AllScenes.PrevNextTitle;
import App.Elements.Staff.WorkerButton;
import App.Scenes.AScene;
import App.Stages.StageManager;

import Backend.Limitations.LimitationsManager;
import Backend.Limitations.Limits.LimitWrapper;
import Backend.MainManager;
import Backend.Plan.PlanManager;
import Backend.Staff.StaffManager;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class PlanLimitsScene extends AScene {
	public PlanLimitsScene(StageManager stageManager, MainManager mainManager) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.mainManager = mainManager;

		fillScene();
	}

	public void fillScene() {
		PlanManager plan = mainManager.getPlan();
		StaffManager staff = plan.getStaff();
		LimitationsManager limitations = plan.getLimitations();

		ScrollPane root = (ScrollPane) this.getRoot();
		VBox mainContainer = new VBox();

		root.setContent(mainContainer);
		root.setFitToWidth(true);

		/* --- filling the mainContainer --- */
		/* ExitTitle or PrevNextTitle */
		if (staff.getWorkersCount() == 0) {
			mainContainer.getChildren().add(new ExitTitle(
					"Обмеження",
					() -> {
						stageManager.goBack();
					} ));
		} else {
			mainContainer.getChildren().add(new PrevNextTitle(
					"Обмеження",
					() -> {
						stageManager.goBack();
					},
					() -> {
						plan.startSchedule();
						stageManager.setScene(new PlanScene(stageManager, mainManager));
					} ));
		}
		/* ExitTitle or PrevNextTitle */

		/* limitsContainer */
		FlowPane limitsContainer = new FlowPane();
		mainContainer.getChildren().add(limitsContainer);

		for (LimitWrapper limit : limitations) {
			limitsContainer.getChildren().add(new WorkerButton(
					limit.getLimitName(),
					"/images/AllScenes/limit.png",
					() -> {
						stageManager.setScene(new LimitSettingsScene(stageManager, plan, limit));
					},
					() -> {
						limitations.deleteLimit(limit);
						fillScene();
					} ));
		}

		limitsContainer.getChildren().add(new WorkerButton(
				"Нове обмеження",
				"/images/AllScenes/plus.png",
				() -> {
					limitations.addLimit();
					fillScene();
				}));
		/* limitsContainer */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/PlanLimitsScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		limitsContainer.getStyleClass().add("limits-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private MainManager mainManager;
}

