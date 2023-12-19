package course.registration.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
	int courseID;
	String courseName;
	Date beginDate;
	Date endDate;
	int fee;
	List<Mentor> teachingMentors = new ArrayList<Mentor>();

	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(int courseID, String courseName, Date beginDate, Date endDate, int fee,
			List<Mentor> teachingMentors) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.fee = fee;
		this.teachingMentors = teachingMentors;
	}

	public Course(int courseID, String courseName) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
	}
	
	

	public Course(int courseID, String courseName, Date beginDate, Date endDate, int fee) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.fee = fee;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public List<Mentor> getTeachingMentors() {
		return teachingMentors;
	}

	public void setTeachingMentors(List<Mentor> teachingMentors) {
		this.teachingMentors = teachingMentors;
	}

}
