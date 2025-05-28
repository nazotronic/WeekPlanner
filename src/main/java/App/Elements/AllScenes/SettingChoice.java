/*
 * Project: Temperature Tick
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 02.03.2025
 */

package App.Elements.AllScenes;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.function.Consumer;

public class SettingChoice extends BorderPane {
	public SettingChoice(String text, ArrayList<String> options, int selected, Consumer<Integer> onIndexSelected) {
		Label label = new Label(text);
		ComboBox<String> comboBox = new ComboBox<>();

		comboBox.getItems().addAll(options);
		comboBox.getSelectionModel().select(selected);

		setLeft(label);
		setRight(comboBox);

		this.getStylesheets().add(getClass().getResource("/styles/LimitSettingsScene.css").toString());
		this.getStyleClass().add("setting-choice");

		label.getStyleClass().add("setting-choice-label");
		comboBox.getStyleClass().add("setting-choice-input");

		comboBox.setOnAction(event -> {
			int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();

			if (onIndexSelected != null && selectedIndex >= 0) {
				onIndexSelected.accept(selectedIndex);
			}
		});
	}
}
