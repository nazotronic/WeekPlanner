/*
 * Project: Temperature Tick
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 02.03.2025
 */

package App.Elements.AllScenes;

import Backend.Elements.Time;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class TimeButton extends BorderPane {
	public TimeButton(String text, Time time, Runnable onClickAction) {
		TimeOutput timeOutput = new TimeOutput(time);
		Button input = new Button(text);

		setLeft(timeOutput);
		setRight(input);

		setAlignment(input, Pos.CENTER);

		this.getStylesheets().add(getClass().getResource("/styles/AllScenesElements.css").toString());
		this.getStyleClass().add("time-button");

		input.getStyleClass().add("time-button-input");

		input.setOnMouseClicked(e -> {
			if (onClickAction != null) {
				onClickAction.run();
			}
		});
	}
}
