package model;

public class StudentBean 
{
	private String name;
	private String major;
	private int courses;
	private double gpa;
	private String year;
	
	public StudentBean(String name, String major, int courses, double gpa, String year) {
		super();
		this.name = name;
		this.major = major;
		this.courses = courses;
		this.gpa = gpa;
		this.year = year;
	}
	
	public StudentBean(String year) {
		this.year = year;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getCourses() {
		return courses;
	}

	public void setCourses(int courses) {
		this.courses = courses;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

}
