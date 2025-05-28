/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Stages;

import App.Scenes.AScene;
import Backend.IManager;
import javafx.stage.Stage;

import java.util.Stack;

public class StageManager implements IManager {
	/* --- constructors --- */
	public StageManager() {
		stage = new Stage();
		sceneHistory = new Stack<>();

		stage.setTitle("WeekPlanner");
		stage.setResizable(false);
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void goBack() {
		if (!sceneHistory.isEmpty()) {
			AScene previous = sceneHistory.pop();
			previous.fillScene();

			stage.setScene(previous);
			stage.show();
		}
	}

	public void goToFirst() {
		if (!sceneHistory.isEmpty()) {
			AScene firstScene = sceneHistory.getFirst();
			firstScene.fillScene();

			sceneHistory.clear();
			setScene(firstScene);
		}
	}
	/* --- main logic --- */

	/* --- setters --- */
	public void setScene(AScene newScene) {
		AScene current = (AScene) stage.getScene();

		if (current != null) {
			sceneHistory.push(current);
		}

		stage.setScene(newScene);
		stage.show();
	}
	/* --- setters --- */

	/* --- getters --- */
	public Stage getStage() {
		return stage;
	}
	/* --- getters --- */

	/* --- private --- */
	private final Stage stage;
	private final Stack<AScene> sceneHistory;
	/* --- private --- */
}