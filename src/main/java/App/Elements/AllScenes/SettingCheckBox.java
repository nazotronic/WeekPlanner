/*
 * Project: Temperature Tick
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 02.03.2025
 */

package App.Elements.AllScenes;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.function.Consumer;

public class SettingCheckBox extends BorderPane {
	public SettingCheckBox(String text, boolean state, Consumer<Boolean> onValueSubmitted) {
		Label label = new Label(text);
		CheckBox input = new CheckBox();

		input.setSelected(state);

		setLeft(label);
		setRight(input);

		this.getStylesheets().add(getClass().getResource("/styles/AllScenesElements.css").toString());
		this.getStyleClass().add("setting-check-box");

		label.getStyleClass().add("setting-check-box-label");
		input.getStyleClass().add("setting-check-box-input");

		input.setOnAction(e -> {
			onValueSubmitted.accept(input.isSelected());
		});
	}
}