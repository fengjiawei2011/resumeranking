<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</head>
<body>
	<h1>Resume Ranking</h1>
	<form action="${pageContext.request.contextPath}/resumes">
		Job Title : <input type="text" name="jobTitle"> <input type="submit">
	</form>
</body>

</html>