package course.registration.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import course.registration.DBConnection;
import course.registration.data.Course;
import course.registration.data.Mentor;
import course.registration.data.User;

public class CourseService {

	// Show All Courses
	public List<Course> getAllCourses() throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		ResultSet resultSet = stmt.executeQuery("select * from courses");
		List<Course> list = new ArrayList<Course>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("course_name");
			Course course = new Course(id, name);
			list.add(course);
		}
		return list;
	}

	// Show Course Details:
	public static void showCourseDetails(int id) throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		String SQL = "select * from courses where id = ?";

		PreparedStatement preStmt = connection.prepareStatement(SQL);
		preStmt.setInt(1, id);

		ResultSet resultSet = preStmt.executeQuery();

		if (resultSet.next()) {
			String name = resultSet.getString("course_name");
			Date beginDate = resultSet.getDate("begin_date");
			Date endDate = resultSet.getDate("end_date");
			int fee = resultSet.getInt("fee");

			Course course = new Course(id, name, beginDate, endDate, fee);
			System.out.println("- Course: " + course.getCourseName());
			System.out.println("- Begin Date: " + course.getBeginDate());
			System.out.println("- End Date: " + course.getEndDate());
			System.out.println("- Fee: A$ " + course.getFee());
		} else {
			System.out.println("Course with ID " + id + " not found.");
		}

	}

	// Show Mentor Details by course
	public static void showMentorInfoByCourse(int id) throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		String SQL = "select courseid, mentor_name, expertise, workplace \n" + "from mentor_by_course mbc\n"
				+ "join mentors m on mbc.mentorid = m.id\n" + "where courseid = ?;";

		PreparedStatement preStmt = connection.prepareStatement(SQL);
		preStmt.setInt(1, id);

		ResultSet resultSet = preStmt.executeQuery();

		while (resultSet.next()) {
			String mentorname = resultSet.getString("mentor_name");
			String expertise = resultSet.getString("expertise");
			String workplace = resultSet.getString("workplace");

			Mentor mentorByCourse = new Mentor(mentorname, expertise, workplace);

			System.out.println("- Mentor Name: " + mentorByCourse.getName());
			System.out.println("- Job Title: " + mentorByCourse.getExpertise());
			System.out.println("- Workplace: " + mentorByCourse.getWorkPlace());

		}

	}

}
