/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend;

import org.apache.logging.log4j.LogManager;

public class Logger {
	/* --- main logic --- */
	public static void logInfo(String log) {
		logger.info(log);
	}

	public static void logError(String log) {
		logger.error(log);
	}
	/* --- main logic --- */

	/* --- private --- */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	/* --- private --- */
}
