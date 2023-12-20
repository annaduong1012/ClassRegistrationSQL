package course.registration.data;

public class Mentor {
	String name;
	String expertise;
	String workPlace;

	public Mentor() {
		// TODO Auto-generated constructor stub
	}

	public Mentor(String name, String expertise, String workPlace) {
		super();
		this.name = name;
		this.expertise = expertise;
		this.workPlace = workPlace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

}
