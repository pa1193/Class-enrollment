package project12;

import java.io.Console;
import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "//direct to myclass.jsp add values to bean", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBUtilities db = new DBUtilities();
	studentInfo student = null;
	courseInfo course = null;
	String address = "errorLogin.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		 TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String uri = request.getHeader("referer");
		String enrollUrl = "http://localhost/Final_Project/enroll.jsp";
		String loginUrl = "http://localhost/Final_Project/registLogin.jsp";
		String ssn ;
		ArrayList<courseInfo> allCourse = null;
		ArrayList<myClassesInfo> myCourse= null;
		DBUtilities db = new DBUtilities();
		boolean successEnroll = true;
		boolean successDrop = true;
		
		if (uri.equals(loginUrl)) {//check if user is first time 

			System.out.println("++++++++++++++++" + uri);
			ssn = request.getParameter("ssn");
			session.setAttribute("ssn", ssn);
		} else {
			ssn = (String) session.getAttribute("ssn");
			
		}
	System.out.println(ssn);
		
		
		// -----------validate login ----------------------------------------
		try {
			System.out.println("*downloading student*");
			student = db.getLogin(ssn);
			System.out.println("student set");

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql error" + e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("class not found " + e);
			e.printStackTrace();
		}
		// ---------------------------------------------------------
		// -------------------load list of all course
		// offered--------------------------
		try {
			System.out.println("*downloading courses*");
			allCourse =  db.getAllCourse();

			

			System.out.println("all course set");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ------------------ ------------------------------------

		try {
			System.out.println("*downloading enrollment*");
			 myCourse = db.getMyClasses(ssn);
			for (int x = 0; x < myCourse.size(); x++) {
				System.out.println("*******beans " + myCourse.get(x).getCourseID() + " " + myCourse.get(x).getTitle());
			}

			

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("mycourse not found" + e1);
			e1.printStackTrace();
		}

		session.setAttribute("studentInfo", student);

		if (session.getAttribute("studentInfo") == null && session.getAttribute("enroll") == null) {
			address = "WEB-INF/registrationError/loginError.jsp";
		} else {
			address = "myRegist.jsp";
		}
		
		
		
		session.setAttribute("db", db);
		session.setAttribute("myCourse", myCourse);
		session.setAttribute("allCourseID", allCourse);
		session.setAttribute("studentInfo", student);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
