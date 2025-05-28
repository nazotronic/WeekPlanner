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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.util.function.Consumer;

public class SettingNumber extends BorderPane {
	public SettingNumber(String text, String prompt, Consumer<String> onValueSubmitted) {
		Label label = new Label(text);
		TextField input = new TextField();

		input.setPromptText(prompt);

		setLeft(label);
		setRight(input);

		this.getStylesheets().add(getClass().getResource("/styles/AllScenesElements.css").toString());
		this.getStyleClass().add("setting-number");

		label.getStyleClass().add("setting-number-label");
		input.getStyleClass().add("setting-number-input");

		input.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal && onValueSubmitted != null) {
				onValueSubmitted.accept(input.getText());
			}
		});

	}
}
