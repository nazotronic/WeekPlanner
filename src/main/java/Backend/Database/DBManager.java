/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package Backend.Database;

import Backend.Elements.Time;
import Backend.IManager;
import Backend.Logger;

import java.sql.*;

public class DBManager implements IManager {
	/* --- constructors --- */
	protected DBManager() {
		try {
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static DBManager getInstance() {
		if (instance == null) {
			Logger.logInfo("Базу даних ініціалізовано");
			instance = new DBManager();
		}

		return instance;
	}
	/* --- constructors --- */

	/* --- main logic --- */
	public void insert(String tableName, String fields, String... values) {
		StringBuilder placeholders = new StringBuilder();
//		Logger.logInfo("Вставлення в БД в таблицю - " + tableName);

		for (int i = 0; i < values.length; i++) {
			placeholders.append("?");

			if (i < values.length - 1) {
				placeholders.append(", ");
			}
		}

		String query = "INSERT INTO " + tableName + "(" + fields + ") VALUES (" + placeholders + ")";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			for (int i = 0; i < values.length; i++) {
				statement.setString(i + 1, values[i]);
			}

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(int id, String tableName, String field, String value) {
		String query = "UPDATE " + tableName + " SET " + field + " = ? WHERE id = ?";
//		Logger.logInfo("Оновлення в БД в таблиці - " + tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, value);
			statement.setInt(2, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(int id, String tableName, String field, String value) {
		String query = "UPDATE " + tableName + " SET " + field + " = CONCAT(" + field + ", ?) WHERE id = ?";
		Logger.logInfo("Додавання в БД в таблицю - " + tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, value);
			statement.setInt(2, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public String get(int id, String tableName, String field) {
		String query = "SELECT " + field + " FROM " + tableName + " WHERE id = ?";
//		Logger.logInfo("Отримання в БД з таблиці - " + tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getString(1);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	public int getInt(int id, String tableName, String field) {
		String string = get(id, tableName, field);

		if (string != null) {
			return Integer.parseInt(string);
		}

		return 0;
	}

	public boolean getBoolean(int id, String tableName, String field) {
		String string = get(id, tableName, field);

		if (string != null) {
			int intValue = Integer.parseInt(string);
			return intValue != 0;
		}

		return false;
	}

	public Time getTime(int id, String tableName, String field) {
		String string = get(id, tableName, field);

		if (string != null) {
			return new Time(string);
		}

		return null;
	}

	public void delete(int id, String tableName) {
		String deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";
		String shiftQuery = "UPDATE " + tableName + " SET id = id - 1 WHERE id > ?";
		String maxIdQuery = "SELECT MAX(id) FROM " + tableName;
		String resetAutoIncrementQuery = "ALTER TABLE " + tableName + " AUTO_INCREMENT = ?";
		Logger.logInfo("Видалення в БД з таблиці - " + tableName);

		try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
		     PreparedStatement shiftStmt = connection.prepareStatement(shiftQuery);
		     Statement maxIdStmt = connection.createStatement()) {

			/* Delete the row */
			deleteStmt.setInt(1, id);
			deleteStmt.executeUpdate();

			/* Shift following rows */
			shiftStmt.setInt(1, id);
			shiftStmt.executeUpdate();

			/* Get the new max id */
			ResultSet rs = maxIdStmt.executeQuery(maxIdQuery);
			int newAutoIncrement = 1;

			if (rs.next()) {
				newAutoIncrement = rs.getInt(1) + 1;
			}

			/* Reset auto-increment */
			try (PreparedStatement resetAIStmt = connection.prepareStatement(resetAutoIncrementQuery)) {
				resetAIStmt.setInt(1, newAutoIncrement);
				resetAIStmt.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getRowCount(String tableName) {
		String query = "SELECT COUNT(*) FROM " + tableName;

		try (PreparedStatement statement = connection.prepareStatement(query);
		     ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return 0;
	}
	/* --- main logic --- */

	/* --- private --- */
	private static DBManager instance;
	private final Connection connection;

	private final String URL = "jdbc:mysql://localhost:3306/mydb";
	private final String USER = "Nazar";
	private final String PASS = "admin";
	/* --- private --- */
}
