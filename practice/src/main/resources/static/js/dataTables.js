$(document).ready(function(){
	$("input:checkbox[name=chkBox]").click(function(){
		var chk_id = $(this).attr('id');
		if($(this).is(':checked')==true){
			$("#memberinfo").append("<div class='"+chk_id+"info' name='mediadiv'><h4>"+chk_id+"</h4><audio src='/src/rain.wav'  controls >sample wav</audio><hr></div>");
		}
		else{
			$("."+chk_id+"info").remove();
		}
	})
	$("#allchk").click(function(){
		if($("#allchk").prop('checked')) {
				$("input:checkbox[name=chkBox]").prop('checked',true);
				 $('input:checkbox[name="chkBox"]').each(function() {
					var chkid = $(this).attr('id');
					$("#memberinfo").append("<div class='"+chkid+"info' name='mediadiv'><h4>"+chkid+"</h4><audio src='/src/rain.wav'  controls >sample wav</audio><hr></div>");
				})
		}
		else{
				$("input:checkbox[name=chkBox]").prop('checked',false);
				$("div[name=mediadiv]").remove();
		}
	})
})
