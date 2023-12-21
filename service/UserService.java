package course.registration.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import course.registration.DBConnection;
import course.registration.data.Course;
import course.registration.data.User;

public class UserService {
	// Register new user
	public static void registerNewUser(String name, String userID, String userPassword) throws SQLException {
		User user = new User(name, userID, userPassword);

		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		String checkIfUserExistSQL = "SELECT * FROM students\n" + "WHERE username = ?;";

		PreparedStatement preStmt = connection.prepareStatement(checkIfUserExistSQL);
		preStmt.setString(1, userID);

		ResultSet resultSet = preStmt.executeQuery();

		// Validate if user existed, if not add new account
		if (resultSet.next()) {
			System.out.println("Account already existed. Please register with a different userID or login.");
		} else {
			String addUserSQL = "INSERT INTO students (student_name, username, password) VALUES (?, ?, ?);";
			PreparedStatement addUserStmt = connection.prepareStatement(addUserSQL);

			addUserStmt.setString(1, name);
			addUserStmt.setString(2, userID);
			addUserStmt.setString(3, userPassword);
			int rowsAffected = addUserStmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Welcome " + name + "! Account Registered Successfully!");
			} else {
				System.out.println("Failed to register the account.");
			}

		}
	}

	// Return login account
	public static User validAccount(String userID, String userPassword) throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		String checkIfUserExistSQL = "SELECT * FROM students\n" + "WHERE username = ?;";

		PreparedStatement preStmt = connection.prepareStatement(checkIfUserExistSQL);
		preStmt.setString(1, userID);

		ResultSet resultSet = preStmt.executeQuery();
		if (resultSet.next()) {
			User currentUser = new User();
			currentUser.setUserID(resultSet.getString("username"));
			currentUser.setUserPassword(resultSet.getString("password"));
			currentUser.setName(resultSet.getString("student_name"));
			currentUser.setFailedCount(resultSet.getInt("failed_login"));

			if (currentUser.getFailedCount() >= 3) {
				System.out.println("Your account has been locked. Please contact Support to unlock your account.");
			} else if (userPassword.equals(currentUser.getUserPassword())) {
				System.out.println("Welcome " + currentUser.getName() + "! You have logged in successfully.");
				return currentUser;
			} else {
				System.out.println("Invalid password. Please try again");
				int failedCount = currentUser.getFailedCount() + 1;
				currentUser.setFailedCount(failedCount);
				// Update failed count
				updateFailedCount(userID, failedCount);
			}
		} else {
			System.out.println("Invalid Account, please try again");
		}
		return null;
	}

	// Update failed login attempt
	public static void updateFailedCount(String userID, int failedCount) throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		String updateFailedAttemptSQL = "UPDATE students SET failed_login = ? WHERE username = ?;";

		PreparedStatement updateStmt = connection.prepareStatement(updateFailedAttemptSQL);
		updateStmt.setInt(1, failedCount);
		updateStmt.setString(2, userID);
		updateStmt.executeUpdate();

	}

	// Show registered course
	public static void showRegisterCourseByUser(User user) throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		String showCourseSQL = "select username, course_id, course_name from enrolled_course e\n"
				+ "join courses c on e.course_id = c.id\n" + "join students s on e.student_id = s.id\n"
				+ "where username = ?;";

		PreparedStatement preStmt = connection.prepareStatement(showCourseSQL);
		preStmt.setString(1, user.getUserID());
		ResultSet resultSet = preStmt.executeQuery();

		System.out.println("Courses registered by " + user.getName() + " :");
		System.out.println("-------------------------------------");
		while (resultSet.next()) {
			int courseID = resultSet.getInt("course_id");
			String courseName = resultSet.getString("course_name");

			System.out.println("Course ID: " + courseID + " | " + courseName);
			System.out.println("-------------------------------------");
		}

	}

	// Register new course
	public static void registerNewCourses(User user, int courseID) throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		String enrolledCourseSQL = "select student_id, username, course_id, course_name from enrolled_course e\n"
				+ "join courses c on e.course_id = c.id\n" + "join students s on e.student_id = s.id\n"
				+ "where username = ?;";

		PreparedStatement preStmt = connection.prepareStatement(enrolledCourseSQL);
		preStmt.setString(1, user.getUserID());

		ResultSet resultSet = preStmt.executeQuery();

		if (resultSet.next()) {
			int courseid = resultSet.getInt("course_id");
			String courseName = resultSet.getString("course_name");
			Course course = new Course(courseid, courseName);

			System.out.println("You have already registered this course: " + course.getCourseName());
		} else {
			String addCourseSQL = "INSERT INTO enrolled_course (student_id, course_id) VALUES (?, ?);";
			PreparedStatement addCourseStmt = connection.prepareStatement(addCourseSQL);
			addCourseStmt.setInt(1, user.getStudentID());
			addCourseStmt.setInt(2, courseID);

			int rowsAffected = addCourseStmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Course has been registered successfully!");
				System.out.println("-------------------------------------");
			} else {
				System.out.println("Failed to register the course.");
				System.out.println("-------------------------------------");
			}

		}

	}
}
