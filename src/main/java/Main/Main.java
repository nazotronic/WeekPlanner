/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Main;

import App.Scenes.MainScene;
import App.Stages.StageManager;

import Backend.Logger;
import Backend.MainManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    /* --- main logic --- */
	public static void main(String[] args) {
		Logger.logInfo("Запуск програми");
		launch(args);

		Logger.logInfo("Зупинка програми");
	}

	@Override
	public void start(Stage primaryStage) {
		MainManager mainManager = new MainManager();
		StageManager stageManager = new StageManager();

		stageManager.setScene(new MainScene(stageManager, mainManager));
	}
    /* --- main logic --- */
}
