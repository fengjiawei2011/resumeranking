<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<div class="container">
		<div class="col-md-12">
			<c:choose>
				<c:when test="${ not empty resumes }">
					<c:forEach var="resume" items="${resumes}">
						<div>
							<h3>
								<a class="trigger" resume_id="${resume.resumeId}" href="${resume.resumeLink}">${resume.resumeName}</a>
							</h3>
							<div >
								${resume.workingExp}
							</div>
							<div style="margin-top: 12px;">
								<span class="badge"><a style="color: white;" class="download" href="${resume.resumeDownloadLink}">Download</a></span>
								<div class="pull-right">
									<span class="label label-default">design pattern</span> <span
										class="label label-primary">Java</span> <span
										class="label label-success">Spring</span> <span
										class="label label-info">C#</span> <span
										class="label label-warning">Git</span> <span>
								</div>
							</div>
							<div id="${resume.resumeId}" style="display: none" title="Resume">
								<iframe height="1000px" width="800px" src="${pageContext.request.contextPath}/resources/files/222"></iframe>
							</div>
							<hr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h1>No Result</h1>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>

</html>