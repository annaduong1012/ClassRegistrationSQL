package course.registration.data;

import java.util.ArrayList;
import java.util.List;

public class User {
	String name;
	String userID;
	private String userPassword;
	int failedCount;
	List<Course> registeredCourses = new ArrayList<Course>();

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String userID, String userPassword) {
		super();
		this.name = name;
		this.userID = userID;
		this.userPassword = userPassword;
	}

	public User(String userID, String userPassword) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
	}

	public String getName() {
		return name;
	}

	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}

}
