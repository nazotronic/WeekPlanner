/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Stages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class SaveDialogStage {
	/* --- constructors --- */
	public SaveDialogStage(StageManager stageManager, String text, Consumer<String> onValueSubmitted) {
		Stage dialog = new Stage();
		Scene dialogScene = new Scene(new VBox(), 300, 250);
		VBox root = (VBox) dialogScene.getRoot();

		dialog.setScene(dialogScene);

		dialog.initOwner(stageManager.getStage());
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setResizable(false);

		/* --- filling the root --- */
		/* name */
		Label nameLabel = new Label(text);
		TextField input = new TextField("Введіть");
		/* name */

		/* buttonsContainer */
		HBox buttonsContainer = new HBox();
		Button backButton = new Button("Назад");
		Button confirmButton = new Button("Зберегти");

		buttonsContainer.getChildren().addAll(backButton, confirmButton);
		/* buttonsContainer */

		root.getChildren().addAll(nameLabel, input, buttonsContainer);
		/* --- filling the root --- */

		/* --- adding styles --- */
		dialogScene.getStylesheets().add(getClass().getResource("/styles/SaveDialogStage.css").toString());
		root.getStyleClass().add("root");

		buttonsContainer.getStyleClass().add("buttons-container");
		backButton.getStyleClass().add("back-button");
		confirmButton.getStyleClass().add("confirm-button");
		/* --- adding styles --- */

		backButton.setOnAction(e -> dialog.close());
		confirmButton.setOnAction(e -> {
			if (onValueSubmitted != null) {
				onValueSubmitted.accept(input.getText());
			}

            dialog.close();
        });

		dialog.showAndWait();
	}
	/* --- constructors --- */
}
