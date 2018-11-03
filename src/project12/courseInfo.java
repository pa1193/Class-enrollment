package project12;

import java.io.Serializable;

/**

 * 
 */

/**
 * @author Paul
 *
 */
public class courseInfo implements Serializable {
	private String courseID, subjectID, title;
	private int courseNumber, numOfCredits;

	/**
	 * bean
	 */
	public courseInfo() {
		// TODO Auto-generated constructor stub
	}

	public courseInfo(String courseID, String subjectID, String title, int courseNumber, int numOfCredits) {
		super();
		this.courseID = courseID;
		this.subjectID = subjectID;
		this.title = title;
		this.courseNumber = courseNumber;
		this.numOfCredits = numOfCredits;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public int getNumOfCredits() {
		return numOfCredits;
	}

	public void setNumOfCredits(int numOfCredits) {
		this.numOfCredits = numOfCredits;
	}

}
