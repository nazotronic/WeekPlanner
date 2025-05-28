/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Elements.Plan;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlanHeader extends HBox {
	public PlanHeader() {
		/* --- first empty column --- */
		VBox emptyCol = new VBox();

		emptyCol.setMinWidth(NAME_COL_WIDTH);
		emptyCol.getChildren().add(new Label(""));

		this.getChildren().add(emptyCol);
		/* --- first empty column --- */

		/* --- columns of days --- */
		for (String day : daysOfWeek) {
			VBox dayCol = new VBox();
			Label dayLabel = new Label(day);

			dayCol.setMinWidth(HOUR_WIDTH * (HOURS_PER_DAY / HOURS_STEP));
			dayCol.getChildren().add(dayLabel);

			HBox hourRow = new HBox();
			for (int h = 0; h < HOURS_PER_DAY; h += HOURS_STEP) {
				VBox hourBox = new VBox();
				Label hourLabel = new Label(String.valueOf(h));

				hourBox.setPrefWidth(HOUR_WIDTH);

				hourBox.getChildren().add(hourLabel);
				hourRow.getChildren().add(hourBox);

				hourBox.getStyleClass().add("hour-box");
				hourLabel.getStyleClass().add("hour-box-label");
			}

			this.getChildren().add(dayCol);
			dayCol.getChildren().add(hourRow);

			dayCol.getStyleClass().add("header-day-column");
			dayLabel.getStyleClass().add("header-day-column-label");
		}
		/* --- columns of days --- */

		/* --- last fact column --- */
		BorderPane factCol = new BorderPane();
		Label factLabel = new Label("Факт");

		this.getChildren().add(factCol);
		factCol.setCenter(factLabel);

		factCol.getStyleClass().add("fact-column");
		factLabel.getStyleClass().add("fact-column-label");
		/* --- last fact column --- */

		this.getStyleClass().add("plan-header");
	}

	private final int HOUR_WIDTH = 40;
	private final int HOURS_PER_DAY = 24;
	private final int HOURS_STEP = 4;
	private final int NAME_COL_WIDTH = 200;
	private final String[] daysOfWeek = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд"};

}
