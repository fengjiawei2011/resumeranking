/**
 * 
 */
$(document).ready(function(){
	
	$('#postJobForm').submit(function(e){
		e.preventDefault();
		postForm = $(this).serialize();
		$.ajax({
			url : "/resumeranking/postjob",
			type : 'POST',
			data: postForm,
			success : function(data) {
				$('.close').click();
				console.log(data);
				callme();
			},
			error : function() {
				$('.close').click();
				alert("error");
			}
		});
	});
	
	function layout(){
		alert("call nme");
	}
});