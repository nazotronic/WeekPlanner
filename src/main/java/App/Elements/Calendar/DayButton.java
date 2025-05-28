/*
 * Project: Temperature Tick
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 02.03.2025
 */

package App.Elements.Calendar;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DayButton extends VBox {
	public DayButton(String text, Runnable onClickAction) {
		Label label = new Label(text);

		this.getChildren().addAll(label);

		this.getStyleClass().add("day-button");
		label.getStyleClass().add("day-button-label");

		this.setOnMouseClicked(e -> {
			if (onClickAction != null) {
				onClickAction.run();
			}
		});
	}
}