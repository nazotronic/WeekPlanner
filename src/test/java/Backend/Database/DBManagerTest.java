//package Backend.Database;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.AfterEach;
//import static org.junit.jupiter.api.Assertions.*;
//import java.sql.SQLException;
//import Backend.Elements.Time;
//
//class DBManagerTest {
//	private DBManager dbManager;
//	private static final String TEST_TABLE = "test_table";
//
//	@BeforeEach
//	void setUp() throws SQLException {
//		dbManager = DBManager.getInstance();
//		// Create test table
//		try (var statement = dbManager.connection.createStatement()) {
//			statement.execute("CREATE TABLE IF NOT EXISTS " + TEST_TABLE +
//					"(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), " +
//					"value INT, flag BOOLEAN, time VARCHAR(10))");
//			statement.execute("DELETE FROM " + TEST_TABLE);
//		}
//	}
//
//	@AfterEach
//	void tearDown() throws SQLException {
//		// Clean up test table
//		try (var statement = dbManager.connection.createStatement()) {
//			statement.execute("DROP TABLE IF EXISTS " + TEST_TABLE);
//		}
//	}
//
//	@Test
//	void getInstance_returnsSameInstance() {
//		DBManager anotherInstance = DBManager.getInstance();
//		assertSame(dbManager, anotherInstance);
//	}
//
//	@Test
//	void insert_addsRecordToDatabase() {
//		dbManager.insert(TEST_TABLE, "name, value, flag", "Test", "42", "1");
//		assertEquals(1, dbManager.getRowCount(TEST_TABLE));
//	}
//
//	@Test
//	void update_modifiesExistingRecord() {
//		dbManager.insert(TEST_TABLE, "name", "OldValue");
//		dbManager.update(1, TEST_TABLE, "name", "NewValue");
//		assertEquals("NewValue", dbManager.get(1, TEST_TABLE, "name"));
//	}
//
//	@Test
//	void add_concatenatesStringValues() {
//		dbManager.insert(TEST_TABLE, "name", "First");
//		dbManager.add(1, TEST_TABLE, "name", ",Second");
//		assertEquals("First,Second", dbManager.get(1, TEST_TABLE, "name"));
//	}
//
//	@Test
//	void get_returnsCorrectValue() {
//		dbManager.insert(TEST_TABLE, "name, value", "TestName", "123");
//		assertEquals("TestName", dbManager.get(1, TEST_TABLE, "name"));
//	}
//
//	@Test
//	void getInt_returnsParsedInteger() {
//		dbManager.insert(TEST_TABLE, "value", "42");
//		assertEquals(42, dbManager.getInt(1, TEST_TABLE, "value"));
//	}
//
//	@Test
//	void getBoolean_returnsParsedBoolean() {
//		dbManager.insert(TEST_TABLE, "flag", "1");
//		assertTrue(dbManager.getBoolean(1, TEST_TABLE, "flag"));
//	}
//
//	@Test
//	void getTime_returnsTimeObject() {
//		dbManager.insert(TEST_TABLE, "time", "10:30:00");
//		Time time = dbManager.getTime(1, TEST_TABLE, "time");
//		assertNotNull(time);
//		assertEquals(10, time.getHour());
//		assertEquals(30, time.getMinute());
//	}
//
//	@Test
//	void delete_removesRecordAndShiftsIds() {
//		dbManager.insert(TEST_TABLE, "name", "First");
//		dbManager.insert(TEST_TABLE, "name", "Second");
//		dbManager.delete(1, TEST_TABLE);
//		assertEquals(1, dbManager.getRowCount(TEST_TABLE));
//		assertEquals("Second", dbManager.get(1, TEST_TABLE, "name"));
//	}
//
//	@Test
//	void getRowCount_returnsCorrectCount() {
//		assertEquals(0, dbManager.getRowCount(TEST_TABLE));
//		dbManager.insert(TEST_TABLE, "name", "Test");
//		assertEquals(1, dbManager.getRowCount(TEST_TABLE));
//	}
//}