/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Calendar;

import Backend.Elements.Time;
import Backend.IManager;

public class DaySettings {
	/* --- constructors --- */
	public DaySettings(IManager manager) {
		this.manager = manager;
	}

	public DaySettings(IManager manager, DaySettings other) {
		this.manager = manager;

		this.name = other.getName();
		this.activeFlag = other.isActiveFlag();
		this.startDayTime.copy(other.getStartDayTime());
		this.endDayTime.copy(other.getEndDayTime());
		this.meetingFlag = other.isMeetingFlag();
		this.startMeetingTime.copy(other.getStartMeetingTime());
		this.meetingDurationTime.copy(other.getMeetingDurationTime());
		this.maxWorkersCount = other.getMaxWorkersCount();
		this.minWorkersCount = other.getMinWorkersCount();
		this.privilegedDay = other.isPrivilegedDay();
		this.hardWorkReq = other.isHardWorkReq();
	}
	/* --- constructors --- */

	/* --- setters --- */
	public void setName(String name) {
		this.name = name;
		if (manager != null) manager.DBUpdate(this, "name", getName());
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
		if (manager != null) manager.DBUpdate(this, "active", isActiveFlag());
	}

	public void setStartDayTime(int startDayTime) {
		this.startDayTime.setHour(startDayTime);
		if (manager != null) manager.DBUpdate(this, "start_day_time", getStartDayTime());
	}

	public void setEndDayTime(int endDayTime) {
		this.endDayTime.setHour(endDayTime);
		if (manager != null) manager.DBUpdate(this, "end_day_time", getEndDayTime());
	}


	public void setMeetingFlag(boolean meetingFlag) {
		this.meetingFlag = meetingFlag;
		if (manager != null) manager.DBUpdate(this, "meeting", isMeetingFlag());
	}

	public void setStartMeetingTime(int startMeetingTime) {
		this.startMeetingTime.setHour(startMeetingTime);
		if (manager != null) manager.DBUpdate(this, "start_meeting_time", getStartMeetingTime());
	}

	public void setMeetingDurationTime(int meetingDurationTime) {
		this.meetingDurationTime.setHour(meetingDurationTime);
		if (manager != null) manager.DBUpdate(this, "meeting_duration_time", getMeetingDurationTime());
	}


	public void setMaxWorkersCount(int maxWorkersCount) {
		this.maxWorkersCount = maxWorkersCount;
		if (manager != null) manager.DBUpdate(this, "max_workers_count", getMaxWorkersCount());
	}

	public void setMinWorkersCount(int minWorkersCount) {
		this.minWorkersCount = minWorkersCount;
		if (manager != null) manager.DBUpdate(this, "min_workers_count", getMinWorkersCount());
	}

	public void setPrivilegedDay(boolean privilegedDay) {
		this.privilegedDay = privilegedDay;
		if (manager != null) manager.DBUpdate(this, "privileged", isPrivilegedDay());
	}

	public void setHardWorkReq(boolean hardWorkReq) {
		this.hardWorkReq = hardWorkReq;
		if (manager != null) manager.DBUpdate(this, "hard_work", isHardWorkReq());
	}
	/* --- setters --- */

	/* --- getters --- */
	public String getName() {
		return name;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public Time getStartDayTime() {
		return startDayTime;
	}

	public Time getEndDayTime() {
		return endDayTime;
	}

	public boolean isMeetingFlag() {
		return meetingFlag;
	}

	public Time getStartMeetingTime() {
		return startMeetingTime;
	}

	public Time getMeetingDurationTime() {
		return meetingDurationTime;
	}

	public int getMaxWorkersCount() {
		return maxWorkersCount;
	}

	public int getMinWorkersCount() {
		return minWorkersCount;
	}

	public boolean isPrivilegedDay() {
		return privilegedDay;
	}

	public boolean isHardWorkReq() {
		return hardWorkReq;
	}
	/* --- getters --- */

	/* --- private --- */
	private IManager manager;

	private String name;
	private boolean activeFlag = true;
	private Time startDayTime = new Time(10);
	private Time endDayTime = new Time(20);

	private boolean meetingFlag = false;
	private Time startMeetingTime =  new Time(startDayTime);
	private Time meetingDurationTime = new Time(1);

	private int maxWorkersCount = 4;
	private int minWorkersCount = 2;
	private boolean privilegedDay = false;
	private boolean hardWorkReq = false;
	/* --- private --- */
}
