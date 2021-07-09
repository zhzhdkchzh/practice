function refreshMemList() {location.reload();}
$(function(){

	for (var i in uml) {
		if (uml[i].parent =="root")  {
			$(".navbar-nav").append("<li class='nav-item dropdown'>"
				+ "<a	class='nav-link dropdown-toggle' href='"+uml[i].url+"'  role='button' data-bs-toggle='dropdown' aria-expanded='false' value=" + uml[i].mname + ">" + uml[i].mname+ "</a> "
				+ "<ul class='dropdown-menu dropdown-menu-dark' id='" + uml[i].mname + "' aria-labelledby='navbarDarkDropdownMenuLink'> "
				+ "</ul></li>");
		}
		if (uml[i].parent == "TestMenu") {
			$("#TestMenu").append("<li><a class='dropdown-item' id='"+uml[i].mname+"' >" + uml[i].mname + "</a></li> ")

		}
		if (uml[i].parent == "Reputation") {
			$("#Reputation").append("<li><a class='dropdown-item' id='"+uml[i].mname+"'>" + uml[i].mname + "</a></li> ")
		}
		if (uml[i].parent == "Level") {
			$("#Level").append("<li><a class='dropdown-item' id='"+uml[i].mname+"' >" + uml[i].mname + "</a></li> ")
		}
	}

});
$(document).ready(function(){
	$("#Up").click(function(){
		$.post("http://localhost:8090/Up", function(data){
			if(data==0){
				alert("do not level up");
			}
			else{
				window.location.href="/viewTable";
			}
		})
	})
	$("#Down").click(function(){
		$.post("http://localhost:8090/Down", function(data){
			if(data==0){
				alert("do not level Down");
			}
			else{
				window.location.href="/viewTable";
			}
		})
	})
});