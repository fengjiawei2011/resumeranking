<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/myjs.js"></script>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Resume Ranking</a>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Post a Job</h4>
						</div>
						<div class="modal-body">
							<form id="postJobForm" role="form">
								<div class="form-group">
									<label for="exampleInputEmail1">Company Name</label> <input
										type="" text"" class="form-control" id="company"
										name="company" placeholder="company name">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Job Title</label> <input
										type="" text"" class="form-control" id="job_title"
										name="job_title" placeholder="job title">
								</div>

								<div class="span12 form-group">
									<label>Job Description</label>
									<textarea style="margin: 0px; width: 557px; height: 140px;"
										class="field span12" id=job_des rows="8"
										placeholder="Enter a description" name="job_des"></textarea>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<input id="post" type="submit" value="Post"  class="btn btn-primary">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<!-- Button trigger modal -->
				<ul class="nav navbar-nav navbar-right">
					<li><a data-toggle="modal" data-target="#myModal" href="#">post
							job &raquo;</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</body>
</html>