/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Limitations.Limits;

import Backend.Elements.Time;

public interface ILimit {
	public int getDayId();
	public int getWorkerId();
	public Time getStart();
	public Time getEnd();
}
