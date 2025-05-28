/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Elements.WorkerSettingsScene;

import App.Elements.AllScenes.TimeButton;
import Backend.Elements.Time;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class WorkerTimeInfo extends BorderPane {
	public WorkerTimeInfo(String text, Time time, Runnable onClickAction) {
		Label label = new Label(text);
		TimeButton timeButton = new TimeButton(
				"Скинути",
				time,
				() -> {
					if (onClickAction != null) {
						onClickAction.run();
					}
				});

		setLeft(label);
		setRight(timeButton);

		setAlignment(label, Pos.CENTER);

		this.getStyleClass().add("worker-time-info");
		label.getStyleClass().add("worker-time-info-label");
	}
}
