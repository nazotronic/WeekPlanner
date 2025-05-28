/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Staff;

import Backend.Elements.Time;
import Backend.IManager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class WorkerSettings {
	/* --- constructors --- */
	public WorkerSettings(IManager manager) {
		this.manager = manager;
		remainingTime = new Time(getMonthWorkHours());
	}

	public WorkerSettings(WorkerSettings other) {
		this.monthWorkHours = other.getMonthWorkHours();
		this.remainingTime = new Time(other.getRemainingTime());

		this.name = other.getName();
		this.surname = other.getSurname();
		this.middleName = other.getMiddleName();
		this.hardWork = other.isHardWork();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void resetRemainingTime() {
		remainingTime.setHour(getMonthWorkHours());
		if (manager != null) manager.DBUpdate(this, "remaining_time", getRemainingTime());
	}

	public void minusRemainingTime(Time time) {
		remainingTime.minusHour(time.getHour());
		if (manager != null) manager.DBUpdate(this, "remaining_time", getRemainingTime());
	}

	public void addLog(String log) {
		if (manager != null) manager.DBAdd(this, "log", log);
	}

	public void saveLogAsPDF(String link) {
		if (manager != null) {
			String log = manager.DBGet(this, "log");
			Document document = new Document();

			try {
				PdfWriter.getInstance(document, new FileOutputStream(link + "/" + getName() + "_log.pdf"));
				document.open();

				document.add(new Paragraph(log));
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			} finally {
				document.close();
			}
		}
	}

	public void clearLog() {
		if (manager != null) manager.DBUpdate(this, "log", "");
	}
	/* --- main logic --- */


	/* --- setters --- */
	public void setMonthWorkHours(int monthWorkHours) {
		this.monthWorkHours = monthWorkHours;
		if (manager != null) manager.DBUpdate(this, "month_work_hours", getMonthWorkHours());
	}

	public void setRemainingTime(Time remainingTime) {
		this.remainingTime = remainingTime;
		if (manager != null) manager.DBUpdate(this, "remaining_time", getRemainingTime());
	}

	public void setName(String name) {
		this.name = name;
		if (manager != null) manager.DBUpdate(this, "name", getName());
	}

	public void setSurname(String surname) {
		this.surname = surname;
		if (manager != null) manager.DBUpdate(this, "surname", getSurname());
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
		if (manager != null) manager.DBUpdate(this, "middle_name", getMiddleName());
	}

	public void setHardWork(boolean hardWork) {
		this.hardWork = hardWork;
		if (manager != null) manager.DBUpdate(this, "hard_work", isHardWork());
	}
	/* --- setters --- */

	/* --- getters --- */
	public String getFullName() {
		return  getSurname() + " " +
				getName().charAt(0) + "." +
				getMiddleName().charAt(0) + ".";
	}

	public int getMonthWorkHours() {
		return monthWorkHours;
	}

	public Time getRemainingTime() {
		return remainingTime;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getMiddleName() {
		return middleName;
	}

	public boolean isHardWork() {
		return hardWork;
	}
	/* --- setters --- */

	/* --- private --- */
	private IManager manager;

	private int monthWorkHours = 160;
	private Time remainingTime;

	private String name = "n";
	private String surname = "surname";
	private String middleName = "mn";

	private boolean hardWork = false;
	/* --- private --- */
}
