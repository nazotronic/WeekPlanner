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
import App.Elements.Staff.WorkerButton;
import App.Stages.StageManager;

import Backend.Staff.StaffManager;
import Backend.Staff.WorkerSettings;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class GlobalStaffScene extends AScene {
	public GlobalStaffScene(StageManager stageManager, StaffManager staff) {
		super(new ScrollPane(), 950, 500);

		this.stageManager = stageManager;
		this.staff = staff;

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
				"Працівники",
				() -> {
					stageManager.goBack();
				}));
		/* ExitTitle */

		/* workersContainer */
		FlowPane workersContainer = new FlowPane();
		mainContainer.getChildren().add(workersContainer);

		for (WorkerSettings globalWorker : staff) {
			workersContainer.getChildren().add(new WorkerButton(
					globalWorker.getFullName(),
					"/images/AllScenes/worker.png",
					() -> {
						stageManager.setScene(new WorkerSettingsScene(stageManager, globalWorker));
					},
					() -> {
						staff.deleteWorker(globalWorker);
						fillScene();
					}));
		}

		workersContainer.getChildren().add(new WorkerButton(
				"Новий працівник",
				"/images/AllScenes/plus.png",
				() -> {
					staff.addWorker();
					fillScene();
				}));
		/* workersContainer */
		/* --- filling the mainContainer --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/GlobalStaffScene.css").toString());

		mainContainer.getStyleClass().add("main-container");
		workersContainer.getStyleClass().add("workers-container");
		/* --- adding styles --- */
	}

	private StageManager stageManager;
	private StaffManager staff;
}
