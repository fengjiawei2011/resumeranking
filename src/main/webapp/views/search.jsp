<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
#mydiv {
    position:fixed;
    top: 50%;
    left: 45%;
    margin-top: -9em; /*set to a negative number 1/2 of your height*/
    margin-left: -13em; /*set to a negative number 1/2 of your width*/

}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div id="mydiv">
		<h1>Resume Ranking</h1>
		<p></p>
		<p></p>
		<p></p>
		<div class="row">
			<div class="col-lg-7">
				<div class="input-group">
					<form id="searchForm" action="${pageContext.request.contextPath}/search">
						<input type="text" class="form-control" name="keywords" placeholder="input a job title or major">
					</form>
					<span class="input-group-btn">
						<button id="subsearch" class="btn btn-default" type="button"><i class="glyphicon glyphicon-search"></i></button>
					</span>

				</div>
				<!-- /input-group -->
			</div>
			<!-- /.col-lg-6 -->
		</div>
		<!-- /.row -->
	</div>
</body>

</html>