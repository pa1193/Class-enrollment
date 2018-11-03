<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="project12.courseInfo"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix = "c" uri = "WEB-INF/lib/c.tld" %>
<title>Insert title here</title>
</head>
<body>
<form action="Enroll">
	<!-- display courses and grades three buttons drop registure exit(goes to myclass.jsp) -->
<fieldset>
				<legend>Course</legend>
	<center>
<select name="courseList">
	<c:forEach var = "i" begin ="0" end = "${sessionScope.allCourseID.size() - 1}">
			<option value ="${sessionScope.allCourseID[i].getCourseID()}">
				 <c:out value ="${sessionScope.allCourseID[i].getCourseID()}"/>, <c:out value ="${sessionScope.allCourseID[i].getTitle()}"/>
			</option>		
	</c:forEach>
</select>

		<br> <br> 
		<select id="gradeList" name="gradeList">
			<option id="A">A</option>
			<option id="B">B</option>
			<option id="C">C</option>
			<option id="D">D</option>
			<option id="F">F</option>
		</select> <br> 
		
		<input type="submit" id="dropButton" name="button"
			value="drop"> 
			
			<input type="submit" id="enrollButton"
			name="button" value="enroll">
	
	</center>
	</fieldset>
	<br>
	</form>
	<form action="myRegist.jsp">
		<input type="submit" id="exit" name="exit" value="exit">
	</form>





</body>
</html>