/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Elements.Plan;

import Backend.Elements.Time;
import Backend.Plan.Schedule.ScheduleWorkerInfo;
import Backend.Plan.Schedule.WorkerShift;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class PlanWorkers extends HBox {
	public PlanWorkers(ArrayList<WorkerShift> workerSchedule, ScheduleWorkerInfo workerInfo) {
		/* --- first name column --- */
		BorderPane nameCol = new BorderPane();
		Label nameLabel = new Label(workerInfo.getFullName());

		nameCol.setMinWidth(NAME_COL_WIDTH);
		nameCol.setPrefHeight(ROW_HEIGHT);
		nameCol.setCenter(nameLabel);

		this.getChildren().add(nameCol);

		nameCol.getStyleClass().add("name-column");
		nameLabel.getStyleClass().add("name-column-label");
		/* --- first name column --- */

		/* --- columns of workers --- */
		for (WorkerShift workerShift : workerSchedule) {
			StackPane dayCol = new StackPane();

			dayCol.setMinWidth(HOUR_WIDTH * (HOURS_PER_DAY / HOURS_STEP));
			dayCol.setMaxWidth(HOUR_WIDTH * (HOURS_PER_DAY / HOURS_STEP));
			dayCol.setPrefHeight(ROW_HEIGHT);

			/* divisions */
			HBox hourDivisions = new HBox();
			for (int h = 0; h < HOURS_PER_DAY; h += HOURS_STEP) {
				Pane division = new Pane();

				division.setPrefWidth(HOUR_WIDTH);
				hourDivisions.getChildren().add(division);

				division.getStyleClass().add("hour-division");
			}
			/* divisions */

			/* worker shift */
			Rectangle shift = new Rectangle();
			Label timeLabel = new Label();

			Time timeStart = workerShift.getStartTime();
			Time timeEnd = workerShift.getEndTime();

			double shiftStart = timeStart.getHour() + ((double) (timeStart.getMinute() * 5) / 3);
			double shiftEnd = timeEnd.getHour() + ((double) (timeEnd.getMinute() * 5) / 3);

			double shiftWidth = (shiftEnd - shiftStart) * HOUR_WIDTH / HOURS_STEP;
			double shiftTranslate = shiftStart * HOUR_WIDTH / HOURS_STEP - ((HOURS_PER_DAY / HOURS_STEP) * HOUR_WIDTH) / 2 + shiftWidth / 2;

			String shiftTime = timeStart.getHour() + ":" + timeStart.getMinute();
			shiftTime += " - ";
			shiftTime += timeEnd.getHour() + ":" + timeEnd.getMinute();

			shift.setWidth(shiftWidth);
			shift.setTranslateX(shiftTranslate);
			shift.setTranslateY(-5);

			timeLabel.setText(shiftTime);
			timeLabel.setTranslateY(15);

			shift.setHeight(20);
			shift.getStyleClass().add("worker-shift");
			/* worker shift */

			this.getChildren().add(dayCol);
			dayCol.getChildren().addAll(hourDivisions, shift, timeLabel);

			dayCol.getStyleClass().add("worker-day-column");
		}
		/* --- columns of workers --- */

		/* last fact column */
		BorderPane factCol = new BorderPane();
		Label factLabel = new Label(workerInfo.getTotalTime().getHour() + ":" + workerInfo.getTotalTime().getMinute());

		factCol.setCenter(factLabel);
		this.getChildren().add(factCol);

		factCol.getStyleClass().add("fact-column");
		factLabel.getStyleClass().add("fact-column-label");
		/* last fact column */

		this.getStyleClass().add("plan-workers");
	}

	private final int HOUR_WIDTH = 40;
	private final int HOURS_PER_DAY = 24;
	private final int HOURS_STEP = 4;
	private final int NAME_COL_WIDTH = 200;
	private final int ROW_HEIGHT = 50;
}
