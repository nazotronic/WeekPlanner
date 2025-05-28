/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Scenes.Plan;

import App.Elements.Plan.PlanHeader;
import App.Elements.Plan.PlanManageButtons;
import App.Elements.Plan.PlanWorkers;
import App.Scenes.AScene;
import App.Stages.SaveDialogStage;
import App.Stages.StageManager;

import Backend.MainManager;
import Backend.Plan.PlanManager;
import Backend.Plan.Schedule.ScheduleManager;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PlanScene extends AScene {
	public PlanScene(StageManager stageManager, MainManager mainManager) {
		super(new BorderPane(), 1200, 600);

		this.stageManager = stageManager;
		this.mainManager = mainManager;

		fillScene();
	}

	public void fillScene() {
		PlanManager plan = mainManager.getPlan();
		ScheduleManager schedule = plan.getSchedule();

		BorderPane root = (BorderPane) this.getRoot();

		/* --- filling the root --- */
		/* planContainer */
		ScrollPane planContent = new ScrollPane();
		VBox planContainer = new VBox();

		root.setCenter(planContent);
		planContent.setContent(planContainer);
		planContent.setFitToWidth(true);

		planContainer.getChildren().add(new PlanHeader());

		for (int i = 0;i < schedule.getWorkersCount();i++) {
			planContainer.getChildren().add(new PlanWorkers(schedule.makeWorkerSchedule(i), schedule.getWorkerInfo(i)) );
		}
		/* planContainer */

		/* PlanManageButtons */
		root.setBottom(new PlanManageButtons(
				() -> {
					plan.stopSchedule();
					stageManager.goBack();
				},
				() -> {
					mainManager.stopPlan();
					stageManager.goToFirst();
				},
				() -> {
					new SaveDialogStage(
							stageManager,
							"Введіть ім'я плану:",
							value -> {
								if (!value.isEmpty()) {
									mainManager.acceptPlan(value);

									stageManager.goToFirst();
								}
							});
				},
				() -> {
					new SaveDialogStage(
							stageManager,
							"Введіть шлях збереження:",
							value -> {
								if (!value.isEmpty()) {
									savePlanAsImage(planContainer, value);
								}
							});
				}
		));
		/* PlanManageButtons */
		/* --- filling the root --- */

		/* --- adding styles --- */
		this.getStylesheets().add(getClass().getResource("/styles/PlanScene.css").toString());

		root.getStyleClass().add("root");
		planContainer.getStyleClass().add("plan-container");
		/* --- adding styles --- */
	}

	private void savePlanAsImage(Node planContainer, String link) {
		try {
			WritableImage image = planContainer.snapshot(null, null);
			File file = new File(link + "/plan_screenshot.png");

			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private StageManager stageManager;
	private MainManager mainManager;
}
