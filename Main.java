package course.registration;

import java.sql.SQLException;
import java.util.Scanner;

import course.registration.data.Course;
import course.registration.data.User;
import course.registration.service.CourseService;
import course.registration.service.UserService;

public class Main {
	public static void main(String[] args) throws SQLException {

		final int DO_LOGIN = 1;
		final int DO_REGISTER = 2;

		Scanner keyboard = new Scanner(System.in);
		CourseService courseService = new CourseService();
		UserService userService = new UserService();
		User loggedInUser = null;

		// Login option
		while (true) {
			logInMenu();
			int loginOption = keyboard.nextInt();

			switch (loginOption) {
			case DO_LOGIN:
				loggedInUser = logInAccount();
				courseMenuFunction(loggedInUser);
				break;
			case DO_REGISTER:
				registerFunction();
				break;
			default:
				System.out.println("Please select a valid option.");
			}
		}

	}

	// Login Menu:
	public static void logInMenu() {
		System.out.println("----------");
		System.out.println("1. Log in");
		System.out.println("2. Register");
		System.out.println("----------");
		System.out.println("Please log in or register a new account: ");
	}

	// register function
	public static void registerFunction() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		UserService userService = new UserService();
		System.out.println("Please register your account: ");
		System.out.print("Your first name: ");
		String newUserName = keyboard.next();
		System.out.print("UserID: ");
		String newUserID = keyboard.next();
		System.out.print("Password: ");
		String newUserPass = keyboard.next();

		UserService.registerNewUser(newUserName, newUserID, newUserPass);
	}

	// login function:
	public static User logInAccount() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		UserService userService = new UserService();

		System.out.print("User ID: ");
		String loginID = keyboard.next();

		System.out.print("Password: ");
		String loginPass = keyboard.next();
		User loggedInUser = UserService.validAccount(loginID, loginPass);

		if (loggedInUser != null) {
			return loggedInUser;
		} else {
			return null;
		}
	}

	// course menu:
	public static void courseMenuFunction(User loggedInUser) throws SQLException {
		while (true) {
			if (loggedInUser != null) {
				System.out.println("-------------------------------------");
				System.out.println("Below is the course menu: ");
				System.out.println("0. View Your Registered Courses");
				CourseService.showAllCourses();
				System.out.println("Please select an option or any number to exit");
				courseMenuChoice(loggedInUser);
			} else {
				break;
			}
		}
	}

	// selectCourse function
	public static void courseMenuChoice(User loggedInUser) throws SQLException {
		final int VIEW_REGISTERED_COURSE = 0;
		Scanner keyboard = new Scanner(System.in);
		int courseOption = keyboard.nextInt();

		if (courseOption == VIEW_REGISTERED_COURSE) {
			UserService.showRegisterCourseByUser(loggedInUser);
			courseMenuFunction(loggedInUser);
			// to do
		} else if (courseOption >= 1 && courseOption <= 4) {
			showSelectedCourseMenu(loggedInUser, courseOption);
		} else {
			return;
		}
	}

	// selected Course Menu
	public static void showSelectedCourseMenu(User loggedInUser, int courseID) throws SQLException {
		System.out.println("-------------------------------------");
		CourseService.showCourseDetails(courseID);
		System.out.println("-------------------------------------");
		System.out.println("1. Register Course");
		System.out.println("2. View Mentor Details");
		System.out.println("-------------------------------------");
		System.out.println("Please select your option or any number to exit: ");
		selectedCourseOption(loggedInUser, courseID);
	}

	// Register Course or View Mentor Details
	final static int REGISTER_NEW_COURSE = 1;
	final static int SHOW_MENTOR = 2;

	public static void selectedCourseOption(User loggedInUser, int courseID) throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		int selectedCourseMenuUserOption = keyboard.nextInt();

		switch (selectedCourseMenuUserOption) {
		case REGISTER_NEW_COURSE:
			UserService.registerNewCourses(loggedInUser, courseID);
			break;
		case SHOW_MENTOR:
			CourseService.showMentorInfoByCourse(courseID);
			break;
		default:
			courseMenuFunction(loggedInUser);
			break;
		}
	}

}
