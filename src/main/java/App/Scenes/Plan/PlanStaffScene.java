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
import App.Elements.Staff.WorkerButton;
import App.Scenes.AScene;
import App.Scenes.WorkerSettingsScene;
import App.Stages.StageManager;

import Backend.MainManager;
import Backend.Plan.PlanManager;
import Backend.Staff.StaffManager;
import Backend.Staff.WorkerSettings;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class PlanStaffScene extends AScene {
	public PlanStaffScene(StageManager stageManager, MainManager mainManager) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.mainManager = mainManager;

		fillScene();
	}

	public void fillScene() {
		PlanManager plan = mainManager.getPlan();
		StaffManager staff = plan.getStaff();

		ScrollPane root = (ScrollPane) this.getRoot();
		VBox mainContainer = new VBox();

		root.setContent(mainContainer);
		root.setFitToWidth(true);

		/* --- filling the mainContainer --- */
		/* PrevNextTitle */
		mainContainer.getChildren().add(new PrevNextTitle(
				"Працівники",
				() -> {
					stageManager.goBack();
				},
				() -> {
					stageManager.setScene(new PlanLimitsScene(stageManager, mainManager));
				} ));
		/* PrevNextTitle */

		/* workersContainer */
		FlowPane workersContainer = new FlowPane();
		mainContainer.getChildren().add(workersContainer);

		for (int i = 0; i < staff.getWorkersCount(); i++) {
			final WorkerSettings worker = staff.getWorker(i);

			workersContainer.getChildren().add(new WorkerButton(
					worker.getFullName(),
					"/images/AllScenes/worker.png",
					() -> {
						stageManager.setScene(new WorkerSettingsScene(stageManager, worker));
					}));
		}
		/* workersContainer */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/PlanStaffScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		workersContainer.getStyleClass().add("workers-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private MainManager mainManager;
}
