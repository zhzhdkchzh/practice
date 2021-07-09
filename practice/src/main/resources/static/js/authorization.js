function refreshMemList() {location.reload();}
$(document).ready(function() {$(".all").click(function() {alert("허용된 권한레벨 입니다")})
	$(".manager").click(function() {alert("허용된 권한레벨 입니다")})
	$(".leader").click(function() {
		if ($("#level").val() == 1) {alert("허용되지 않은 권한레벨 입니다")
			return;}
		else {alert("허용된 권한레벨 입니다")
			return;}})
	$(".director").click(function() {if ($("#level").val() <= 2) {alert("허용되지 않은 권한레벨 입니다")
			return;}
		else {alert("허용된 권한레벨 입니다")
			return;}})
	$(".chairman").click(function() {if ($("#level").val() <= 3) { alert("허용되지 않은 권한레벨 입니다")
			return;}
		else { alert("허용된 권한레벨 입니다")
			return;}
	})});
$(document).ready(function() {
	$("#up").click(function() {
		if ($("#level").val() == 4) { alert("최고권한 입니다.") 
		return;}
		$.ajax({url: '/levelUp', method: 'POST', async: false, data: { level: $("#level").val() },
			success: function(data) { switch (data) {
					case 1: $("#showLevel").text("Manager")+$("#level").val(data)+$("#leader").hide()+$("#director").hide()+$("#chairman").hide();window.location.href="/viewTable";
						break;
					case 2: $("#showLevel").text("Director")+$("#level").val(data)+$("#leader").show()+$("#director").hide()+$("#chairman").hide();window.location.href="/viewTable";
						break;
					case 3: $("#showLevel").text("Leader")+$("#level").val(data)+$("#leader").show()+$("#director").show()+$("#chairman").hide();window.location.href="/viewTable";
						break;
					case 4: $("#showLevel").text("ChairMan")+$("#level").val(data)+$("#leader").show()+$("#director").show()+$("#chairman").show();window.location.href="/viewTable";
						break;
				}}})})
	$("#down").click(function() {if ($("#level").val() == 1) {alert("최하권한 입니다.")
			return;}
		$.ajax({url: '/levelDown', method: 'POST', async: false, data: { level: $("#level").val() },
			success: function(data) { switch (data) {
					case 1: $("#showLevel").text("Manager") + $("#level").val(data) + $("#leader").hide() + $("#director").hide() + $("#chairman").hide();window.location.href="/viewTable";
						break;
					case 2: $("#showLevel").text("Leader") + $("#level").val(data) + $("#leader").show() + $("#director").hide() + $("#chairman").hide();window.location.href="/viewTable";
						break;
					case 3: $("#showLevel").text("Director") + $("#level").val(data) + $("#leader").show() + $("#director").show() + $("#chairman").hide();window.location.href="/viewTable";
						break;
					case 4: $("#showLevel").text("ChairMan") + $("#level").val(data) + $("#leader").show() + $("#director").show() + $("#chairman").show();window.location.href="/viewTable";
						break;
				}}})})});