/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Elements;

public class Time {
	/* --- constructors --- */
	public Time() {	}

	public Time(int hour) {
		setHour(hour);
	}

	public Time(int hour, int minute) {
		setHour(hour);
		setMinute(minute);
	}

	public Time(String time) {
		String[] parts = time.split(":");

		if (parts.length < 2) {
			return;
		}

		this.hour = Integer.parseInt(parts[0]);
		this.minute = Integer.parseInt(parts[1]);
	}


	public Time(Time other) {
		this.hour = other.getHour();
		this.minute = other.getMinute();
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void plusHour(int hour) {
		this.hour += hour;
	}

	public void minusHour(int hour) {
		if (this.hour != 0 && hour <= this.hour) {
			this.hour -= hour;
		}
	}

	public void copy(Time other) {
		this.hour = other.getHour();
		this.minute = other.getMinute();
	}
	/* --- main logic --- */

	/* --- overrides --- */
	@Override
	public String toString() {
		return getHour() + ":" +
				getMinute() + ":00";

	}
	/* --- overrides --- */

	/* --- setters --- */
	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	/* --- setters --- */

	/* --- getters --- */
	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
	/* --- getters --- */

	/* --- private --- */
	private int hour = 0;
	private int minute = 0;
	/* --- private --- */
}
