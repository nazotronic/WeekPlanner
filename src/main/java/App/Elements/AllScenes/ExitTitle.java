/*
 * Project: Temperature Tick
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 02.03.2025
 */

package App.Elements.AllScenes;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.util.Objects;

public class ExitTitle extends BorderPane {
	public ExitTitle(String text, Runnable onClickAction) {
		Image imageExit = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/AllScenes/exit.png")) );
		ImageView imageExitView = new ImageView(imageExit);
		Label label = new Label(text);

		imageExitView.setFitWidth(50);
		imageExitView.setFitHeight(50);
		imageExitView.setPickOnBounds(true);

		setLeft(imageExitView);
		setCenter(label);

		this.getStylesheets().add(getClass().getResource("/styles/AllScenesElements.css").toString());
		this.getStyleClass().add("exit-title");
		label.getStyleClass().add("exit-title-label");

		imageExitView.setOnMouseClicked(e -> {
			if (onClickAction != null) {
				onClickAction.run();
			}
		});
	}
}
