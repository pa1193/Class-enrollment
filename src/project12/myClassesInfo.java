package project12;

import java.io.Serializable;

/**
 * 
 */

/**
 * @author Paul
 *
 */
public class myClassesInfo implements Serializable {
	private String ssn, courseID, dateRegistered, grade, title;

	/**
	 * bean
	 */
	public myClassesInfo() {
		// TODO Auto-generated constructor stub
	}

	public myClassesInfo(String ssn, String courseID, String dateRegistered, String grade, String title) {
		super();
		this.ssn = ssn;
		this.courseID = courseID;
		this.dateRegistered = dateRegistered;
		this.grade = grade;
		this.title = title;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(String dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
