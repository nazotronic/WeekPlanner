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

public class PrevNextTitle extends BorderPane {
	public PrevNextTitle(String text, Runnable onClickPrev, Runnable onClickNext) {
		Image imagePrev = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/AllScenes/exit.png")) );
		Image imageNext = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/AllScenes/next.png")) );
		ImageView imagePrevView = new ImageView(imagePrev);
		ImageView imageNextView = new ImageView(imageNext);

		Label label = new Label(text);

		imagePrevView.setFitWidth(50);
		imagePrevView.setFitHeight(50);
		imagePrevView.setPickOnBounds(true);

		imageNextView.setFitWidth(50);
		imageNextView.setFitHeight(50);
		imageNextView.setPickOnBounds(true);

		setLeft(imagePrevView);
		setCenter(label);
		setRight(imageNextView);

		this.getStylesheets().add(getClass().getResource("/styles/AllScenesElements.css").toString());
		this.getStyleClass().add("prev-next-title");
		label.getStyleClass().add("prev-next-title-label");

		imagePrevView.setOnMouseClicked(e -> {
			if (onClickPrev != null) {
				onClickPrev.run();
			}
		});

		imageNextView.setOnMouseClicked(e -> {
			if (onClickNext != null) {
				onClickNext.run();
			}
		});
	}
}
