/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Elements.Plan;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PlanManageButtons extends HBox {
	public PlanManageButtons(Runnable onClickBack,
	                         Runnable onClickDelete,
	                         Runnable onClickConfirm,
	                         Runnable onClickSaveImg) {

		Button backButton = new Button("Назад");
		Button deleteButton = new Button("Видалити");
		Button confirmButton = new Button("Затвердити");
		Button imgButton = new Button("Фото");

		this.getChildren().addAll(backButton, deleteButton, confirmButton, imgButton);

		this.getStyleClass().add("plan-manage");
		backButton.getStyleClass().add("plan-manage-back");
		deleteButton.getStyleClass().add("plan-manage-back");
		confirmButton.getStyleClass().add("plan-manage-confirm");
		imgButton.getStyleClass().add("plan-manage-confirm");

		backButton.setOnMouseClicked(e -> {
			if (onClickBack != null) {
				onClickBack.run();
			}
		});

		deleteButton.setOnMouseClicked(e -> {
			if (onClickDelete != null) {
				onClickDelete.run();
			}
		});

		confirmButton.setOnMouseClicked(e -> {
			if (onClickConfirm != null) {
				onClickConfirm.run();
			}
		});

		imgButton.setOnMouseClicked(e -> {
			if (onClickSaveImg != null) {
				onClickSaveImg.run();
			}
		});
	}
}
