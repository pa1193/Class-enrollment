<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.List"%>
<%@ page import="project12.myClassesInfo"%>
<%@ taglib prefix = "c" uri = "WEB-INF/lib/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="enroll.jsp">
	name: ${studentInfo.firstName} ${studentInfo.lastName} 
	Phone: ${studentInfo.phone}
	
	<br> My courses:
<table border="2">
<tr>
<th>Course ID</th>
<th>Course Title</th>
<th>Grade</th>
</tr>

<c:forEach var = "i" begin ="0" end = "${sessionScope.myCourse.size() - 1}">
<tr>
<td><c:out value ="${sessionScope.myCourse[i].getCourseID()}"/></td>
<td><c:out value ="${sessionScope.myCourse[i].getTitle()}"/></td>
<td><c:out value ="${sessionScope.myCourse[i].getGrade()}"/></td>
</tr>
</c:forEach>

</table>

	<br>

		<input type="submit" id="enroll" value="enroll">
	</form>
	<form action="registLogin.jsp">
	<input type="submit" id="exit" value="exit"></form>

</body>
</html>