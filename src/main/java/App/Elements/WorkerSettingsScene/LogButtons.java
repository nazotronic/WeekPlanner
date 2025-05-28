/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Elements.WorkerSettingsScene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LogButtons extends HBox {
	public LogButtons(Runnable onClickClear, Runnable onClickSave) {
		Button clearButton = new Button("Очистити");
		Button saveButton = new Button("Зберегти");
		Label label = new Label("Звітність");

		this.getChildren().addAll(label, clearButton, saveButton);

		this.getStylesheets().add(getClass().getResource("/styles/WorkerSettingsScene.css").toString());

		this.getStyleClass().add("log-buttons");
		label.getStyleClass().add("log-buttons-label");
		clearButton.getStyleClass().add("log-buttons-input");
		saveButton.getStyleClass().add("log-buttons-input");

		clearButton.setOnMouseClicked(e -> {
			if (onClickClear != null) {
				onClickClear.run();
			}
		});

		saveButton.setOnMouseClicked(e -> {
			if (onClickSave != null) {
				onClickSave.run();
			}
		});
	}
}
