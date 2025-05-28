/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend;

import org.junit.jupiter.api.Test;

public class LoggerTest {

	@Test
	public void testLogInfo_DoesNotThrow() {
		Logger.logInfo("Test info message");
	}

	@Test
	public void testLogError_DoesNotThrow() {
		Logger.logError("Test error message");
	}
}
