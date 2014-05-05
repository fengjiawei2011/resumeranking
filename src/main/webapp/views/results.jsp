<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#search').submit(function(e) {
			e.preventDefault();
			keyword = $('#keyword').attr("value");
			keywords = keyword.split(" ");
			newKeyword = "";
			for (var i = 0; i < keywords.length; ++i) {
				if (i < keywords.length - 1) {
					newKeyword += keywords[i] + "-";
				} else {
					newKeyword += keywords[i];
				}
			}
			$.ajax({
				url : "/resumeranking/resumes/" + newKeyword,
				type : 'GET',
				success : function(data) {
					$('#result').text(data);
				},
				error : function() {
					alert("error");
				}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<h1>Resume Ranking</h1>
	<form id="search">
		Job Title : <input id="keyword" type="text" name="jobTitle"> <input
			type="submit">
	</form>

	<div id="result">11</div>

	<div>
		<ul class="pagination">
			<li><a href="#">&laquo;</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">&raquo;</a></li>
		</ul>
	</div>
</body>

</html>