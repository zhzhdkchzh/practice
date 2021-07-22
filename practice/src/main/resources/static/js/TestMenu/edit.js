$(document).ready(function() {

	OnAjax.getData("/jstree", "post", null, function(result) {

		$('#show_tree_frame').jstree({
			'core': {
				'data': result
			},
			"plugins": ["changed"]

		}).bind("loaded.jstree", function() {
			$('#show_tree_frame').jstree("open_all");
			console.log(result)
		})
			.bind('select_node.jstree', function(event, data) {
				var id = data.instance.get_node(data.selected).id;        //id 가져오기
				var parent = data.instance.get_node(data.selected).parent;    //parent 가져오기
				var name = data.instance.get_node(data.selected).text;    //text 가져오기
				if (parent === "#") {
					parent = "최상위 그룹"
				}
				$("#tree_group_id").val(id)
				$("#tree_group_parent").val(parent)
				$("#tree_group_name").val(name)
			})
		$(".action_btn").click(function() {
			if ($("#action_btn").text() == "open") {
				$(this).text("close")
				$("#show_tree_frame").jstree("open_all");
			}
			else if ($(".action_btn").text() == "close") {
				$(this).text("open")
				$("#show_tree_frame").jstree("close_all");
			}

		})
		$("#insert-group-btn").click(function() {
			var group_parent = $("#tree_group_id").val();
			var group_name = $("#tree_group_name").val();
			if (group_parent == '') {
				alert("추가할 소속을 선택하세요")
				return;
			}
			else if (group_name == '') {
				alert("추가할 그룹명을 입력하세요");
				return;
			}

			var get_tree_txt = result.filter(function(e) { return e.text === group_name; })
			var get_tree_id = result.filter(function(e) { return e.id == group_parent; })
			get_tree_txt != 0 ? alert("생성할 수 없는 그룹명입니다") : (get_tree_id == 0 ? alert("존재하지 않는 소속입니다") :
				OnAjax.getData("/jstree/add", "post", JSON.stringify
					({
						parent: group_parent,
						text: group_name,
						id: group_parent + "/" + group_name
					}),
					function() { }
				)

			);
			refreshMemList();
		})
		$("#do_delete").click(function() {
			var group_parent = $("#tree_group_id").val();
			if(group_parent == ''){
				alert("삭제할 그룹을 선택해주세요")
				$("#cancel_delete").click();
				return;
			}
			OnAjax.getData("/jstree/delete", "post", JSON.stringify({id : group_parent}), function(data){
				if(data==1){
				alert("삭제되었습니다")
				refreshMemList();}
				else{
					alert("error")
				refreshMemList();
				}
			});
		})
		$("#modify-group-btn").click(function(){
			alert("SPLIT_PART 함수 사용 2016버전부터 기능지원")
/*			var get_node = result.filter(function(e) { return e.id == $("#tree_group_id").val(); })
			var get_name = result.filter(function(e) { return e.text === $("#tree_group_name").val(); })
			
			if(get_name.length!=0){
				alert("수정할 수 없는 그룹명입니다")
				return;
			}
			console.log(get_node[0].parent+"/"+$("#tree_group_name").val())
			console.log(get_node[0].id)
			OnAjax.getData("/jstree/modify", "post", JSON.stringify({id:get_node[0].id, parent:get_node[0].parent+"/"+$("#tree_group_name").val(), text:$("#tree_group_name").val() }),
			function(data){
				if(data==1){
				alert("수정되었습니다")
				refreshMemList();
				}
			})*/
		})
	});


});
