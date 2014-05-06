/**
 * 
 */
$(document).ready(function(){
	resume_id = "";
	
	$('#subsearch').click(function(){
		$('#searchForm').submit();
	});
	 $('.trigger').hover(function(){
		  resume_id = $(this).attr("resume_id");
	      $("#"+resume_id).dialog({
	    	  height: 700,
	      	  width: 750
	      });
	  }); 
	 
	 $('html').click(function() {
		 $("#"+resume_id).dialog("close");
	 });

});