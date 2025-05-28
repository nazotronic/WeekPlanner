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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.time.LocalTime;
import java.util.function.Consumer;

public class TimeOutput extends BorderPane {
	public TimeOutput(Time time, Consumer<LocalTime> onValueSubmitted) {
		TextField hour = new TextField();
		Label divider = new Label(":");
		TextField minute = new TextField();

		hour.setText(String.valueOf(time.getHour()) );
		minute.setText(String.valueOf(time.getMinute()) );

		setLeft(hour);
		setCenter(divider);
		setRight(minute);

		this.getStylesheets().add(getClass().getResource("/styles/AllScenesElements.css").toString());
		this.getStyleClass().add("time-output");

		hour.getStyleClass().add("time-output-input");
		divider.getStyleClass().add("time-output-label");
		minute.getStyleClass().add("time-output-input");
	}

	public TimeOutput(Time time) {
		Label hour = new Label();
		Label divider = new Label(":");
		Label minute = new Label();

		hour.setText(String.valueOf(time.getHour()) );
		minute.setText(String.valueOf(time.getMinute()) );

		setLeft(hour);
		setCenter(divider);
		setRight(minute);

		this.getStylesheets().add(getClass().getResource("/styles/AllScenesElements.css").toString());
		this.getStyleClass().add("time-output");

		hour.getStyleClass().add("time-output-t-label");
		divider.getStyleClass().add("time-output-label");
		minute.getStyleClass().add("time-output-t-label");
	}
}
