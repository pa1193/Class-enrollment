package project12;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Enroll
 */
@WebServlet("/Enroll")
public class Enroll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Enroll() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();

		String ssn;
		DBUtilities db = new DBUtilities();
		boolean successEnroll = true;
		boolean successDrop = true;
		String address = "Login";

		String buttonValue = request.getParameter("button");
		String course = request.getParameter("courseList");
		String grade = request.getParameter("gradeList");

		String enroll = "enroll";

		studentInfo student = (studentInfo) session.getAttribute("studentInfo");
		ssn = student.getSsn();

		if (buttonValue == null) { // no button has been selected

		} else if (buttonValue.equals("enroll")) { // enroll button was
													// pressed

			try {
				successEnroll = db.enrollClass(ssn, course, grade);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (buttonValue.equals("drop")) { // drop button was pressed
			System.out.println("**drop button was pressed " + ssn + " course " + course);

			try {
				successDrop = db.dropClass(ssn, course);
			} catch (Exception e) {
				// TODO: handle exception
				
			}

		} else {// someone has altered the HTML and sent a different value!

		}

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
			List<courseInfo> allCourse = db.getAllCourse();

			System.out.println("all course set");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ------------------ ------------------------------------

		try {
			System.out.println("*downloading enrollment*");
			List<myClassesInfo> myCourse = db.getMyClasses(ssn);
			for (int x = 0; x < myCourse.size(); x++) {
				System.out.println("*******beans " + myCourse.get(x).getCourseID() + " " + myCourse.get(x).getTitle());
			}

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("mycourse not found" + e1);
			e1.printStackTrace();
		}

		if (successEnroll == false)
			address = "WEB-INF/registrationError/enrollRedisterError.jsp";
		if (successDrop == false)
			address = "WEB-INF/registrationError/enrollDropError.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
