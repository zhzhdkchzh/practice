
function doOpenCheck(chk){
    var obj = document.getElementsByName("selectMemberName");
    for(var i=0; i<obj.length; i++){
        if(obj[i] != chk){
            obj[i].checked = false;
			$("#"+obj[i].getAttribute('id')+"2").remove();
        }
		else{
			$("#selectName").append("<h3 id='"+obj[i].getAttribute('id')+"2'>"+obj[i].getAttribute('id')+" Select!</h3>")
			$("#"+obj[i].getAttribute('id')+"2").fadeIn("slow");
			$("#"+obj[i].getAttribute('id')+"2").fadeOut(2000);
			if(chk.checked === false){
				$("#selectName").children().remove();
			}
		}
    }
}
$(document).on("click", "#insert-btn", function(){
		if($("#selectWavFile").val()==''){
			alert("do not select file");
			return;
		}
		if ($("input:checkbox[name=selectMemberName]:checked").length == 0) {
			alert("do not select member");
			return;
		}

		OnAjax.getData("/uploadFile", "POST", JSON.stringify({
			name:$("input:checkbox[name=selectMemberName]:checked").attr('id'), 
			fileName:$("#selectWavFile").val()
			}), function(result){
				console.log(result);
				})


		
})

/*	$(document).ready(function() {
	$('#selectWavFile').change(function() {
$('#sample-play').attr('src', $('#selectWavFile').val())
	alert($('#selectWavFile').val());
 console.log(this.files[0].mozFullPath);
	});
});
*/
/*function setThumbnail(event) {
	var reader = new FileReader();
	reader.onload = function(event) {
		var audio = document.createElement("audio");
		audio.setAttribute("src", event.target.result);
		document.querySelector("div.play-select").appendChild(audio);
		console.log(audio)
	};
	reader.readAsDataURL(event.target.files[0]);
}*/
