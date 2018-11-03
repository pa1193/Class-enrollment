package project12;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
/**
 * Perform the following tests on a database:
 * <OL>
 * <LI>Create a JDBC connection to the database and report the product name and
 * version.
 * <LI>Create a simple "authors" table containing the ID, first name, and last
 * name for the two authors of Core Servlets and JavaServer Pages, 2nd Edition.
 * <LI>Query the "authors" table for all rows.
 * <LI>Determine the JDBC version. Use with caution: the reported JDBC version
 * does not mean that the driver has been certified.
 * </OL>
 * <P>
 * Taken from Core Servlets and JavaServer Pages 2nd Edition from Prentice Hall
 * and Sun Microsystems Press, http://www.coreservlets.com/. &copy; 2003 Marty
 * Hall and Larry Brown. May be freely used or adapted.
 */

public class DBUtilities {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	studentInfo student = null;
	public DBUtilities() {
		super();
	}

	public static void connectDB() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded");
			connection = DriverManager.getConnection("jdbc:sqlserver://s16988308.onlinehome-server.com:1433;"
					+ "databaseName=CUNY_DB;integratedSecurity=false;", "cst3613", "password1");
			System.out.println("Database connected");
		} catch (ClassNotFoundException e) {
			System.out.println("diver not loaded " + e);
		}
		// Connect to a database

	}

	public studentInfo getLogin(String ssn) throws SQLException, ClassNotFoundException {
		
		
		
		try {
			this.connectDB();
			
			String queryString = "select firstName, mi, lastName, birthDate, street, phone, zipcode, deptId from Students where ssn = ? ";
			String firstName, mi, lastName, birthDate, street, phone, zipcode, deptId = "";
			preparedStatement = connection.prepareStatement(queryString);
			System.out.println("preparedStatement getLogin");
			preparedStatement.setString(1, ssn);
			ResultSet rset = preparedStatement.executeQuery();

			if (rset.next()) {
				
				
				 firstName = rset.getString("firstName");
				 mi = rset.getString("mi");
				 lastName = rset.getString("lastName");
				 birthDate = rset.getDate("birthDate").toString();
				 street = rset.getString("street");
				 phone = rset.getString("phone");
				 zipcode = rset.getString("zipcode");
				 deptId = rset.getString("deptId");

//				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//				String date = formatter.format(birthDate);
				student = new studentInfo(ssn, firstName, mi, lastName, birthDate.toString(), street, phone, zipcode, deptId);
			} else {
				System.out.println("Not found");
			}
			rset.close();
		//	this.closeDB();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		

		return student;

	}

	public Boolean enrollClass(String ssn, String courseId, String grade)throws  ClassNotFoundException{
		boolean successful = false;
		int x=0;
	try {
		//this.connectDB();
		
		String query ="insert into Enrollment values (?,?,GETDATE(),?)";
		
	
		preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, ssn);
		preparedStatement.setString(2, courseId);
		//preparedStatement.setString(3, date);
		preparedStatement.setString(3, grade);
		x = preparedStatement.executeUpdate();
		//this.closeDB();
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		successful = false;
	}
	if ( x == 1) successful = true;
	return successful;
	
	
	}
	
	public Boolean dropClass(String ssn, String courseId)throws  ClassNotFoundException{
		System.out.println("***** drop class COnnect");
		boolean successful = false;
		int x = 0;
		try{
			//this.connectDB();
			System.out.println("***** sucess");
			String query ="delete from Enrollment where ssn = ? and courseId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ssn);
			preparedStatement.setString(2,courseId);
			 x = preparedStatement.executeUpdate();
			
			
		}catch (SQLException e){
			System.out.println("delete catch "+e.getMessage());
			successful = false;
		}
		if (x == 1)successful = true; 
		return successful;
		
	}
	
public ArrayList<myClassesInfo> getMyClasses(String ssn) throws SQLException, ClassNotFoundException{
	ArrayList<myClassesInfo> list = new ArrayList<myClassesInfo>();
		try {
		//	this.connectDB();
			
			
			//String query = "select * from Enrollment where ssn = ?";
			String query= "select Students.ssn, Enrollment.dateRegistered, Enrollment.courseId, Course.title, Enrollment.grade from Students, Enrollment, Course where (Students.ssn = ? and Enrollment.ssn = ?) and(Enrollment.courseId = Course.courseID) ";
			String  courseID, dateRegistered, grade ="";
			ResultSet rset = null;
			System.out.println(preparedStatement.toString());
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ssn);
			preparedStatement.setString(2, ssn);

			 rset = preparedStatement.executeQuery();
			int x=0;
			while (rset.next()) {
				myClassesInfo course = new myClassesInfo();
				course.setSsn(rset.getString("ssn"));
				course.setCourseID(rset.getString("courseID"));
				course.setDateRegistered(rset.getString("dateRegistered"));
				course.setGrade(rset.getString("grade"));
				course.setTitle(rset.getString("title"));
				list.add(course);
				
	
			} 
			rset.close();
		//	this.closeDB();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	
		
		
		
		return list;
		
		
		
	
	}
public String getCourseTitle(String classID)throws SQLException, ClassNotFoundException{
		
		String title;
		String query = "select title from Course where courseID = ?";
				ResultSet rset = null;
		try {
		//	this.connectDB();
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, classID);
			
			 rset = preparedStatement.executeQuery();
			 if(rset.next()){
				 title = rset.getString("title");
			 }
				rset.close();
			//this.closeDB();	
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
		
		return classID;
		
		
		
	}

	public ArrayList<courseInfo> getAllCourse() throws SQLException, ClassNotFoundException {
		
		ArrayList<courseInfo> list = new ArrayList<courseInfo>();
		
		ResultSet resultSet = null;
	
		String query = "Select * from course";
		try {
			//this.connectDB();
			Statement statement = connection.createStatement();

			resultSet = statement.executeQuery(query);
			System.out.println("preparedStatement getAllCourse");
			while (resultSet.next()) {
				courseInfo course = new courseInfo();
				
					course.setCourseID(resultSet.getString("courseID"));
			
					course.setCourseNumber(resultSet.getInt("courseNumber"));
			
			
					course.setSubjectID(resultSet.getString("subjectID"));
				
			
					course.setTitle(resultSet.getString("title"));
			
			
					course.setNumOfCredits(resultSet.getInt("numOfCredits"));
					list.add(course);
					
					
			}
			resultSet.close();
		//	this.closeDB();
		} catch (SQLException ex) {

		}

		

		return list;
	}
	public void closeDB()throws ClassNotFoundException, SQLException{
		try {
			connection.close();
			System.out.println("Connection Closed");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}