/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Elements.Staff;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WorkerButton extends VBox {
	public WorkerButton(String text, String imagePath, Runnable onClickAction) {
		this(text, imagePath, onClickAction, null);
	}

	public WorkerButton(String text, String imagePath, Runnable onClickAction, Runnable onDeleteClickAction) {
		StackPane imageContainer = new StackPane();

		Image image = new Image(getClass().getResourceAsStream(imagePath));
		Image deleteImage = new Image(getClass().getResourceAsStream("/images/AllScenes/delete.png"));
		ImageView imageView = new ImageView(image);
		ImageView deleteImageView = new ImageView(deleteImage);

		Label label = new Label(text);

		imageView.setFitWidth(100);
		imageView.setFitHeight(100);

		deleteImageView.setFitWidth(40);
		deleteImageView.setFitHeight(40);
		deleteImageView.setPickOnBounds(true);

		imageContainer.getChildren().add(imageView);

		if (onDeleteClickAction != null) {
			imageContainer.getChildren().add(deleteImageView);
		}

		this.getChildren().addAll(imageContainer, label);

		this.getStyleClass().add("worker-button");
		deleteImageView.getStyleClass().add("delete-icon");
		label.getStyleClass().add("worker-button-label");

		this.setOnMouseClicked(e -> {
			if (onClickAction != null) {
				onClickAction.run();
			}
		});

		deleteImageView.setOnMouseClicked(e -> {
			if (onDeleteClickAction != null) {
				onDeleteClickAction.run();
				e.consume();
			}
		});
	}
}
