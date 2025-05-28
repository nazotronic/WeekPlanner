/*
 * Project: Temperature Tick
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 02.03.2025
 */

package App.Elements.MainScene;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CardButton extends VBox {
	public CardButton(String text, String imagePath, Runnable onClickAction) {
		Image image = new Image(getClass().getResourceAsStream(imagePath));
		ImageView imageView = new ImageView(image);
		Label label = new Label(text);

		imageView.setFitWidth(200);
		imageView.setFitHeight(200);

		this.getChildren().addAll(imageView, label);

		this.getStyleClass().add("card-button");
		label.getStyleClass().add("card-button-label");

		this.setOnMouseClicked(e -> {
			if (onClickAction != null) {
				onClickAction.run();
			}
		});
	}
}
