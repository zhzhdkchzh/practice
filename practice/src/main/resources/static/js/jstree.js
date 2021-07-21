$(document).ready(function() {
	OnAjax.getData("/jstree", "post", null, function(result) {
		$('#tree').jstree({
			'core': {
				'data': result
			},
			"plugins": ["changed"]
		})
	});
});

	
/*var jsonData = [
				{ "id": "examole1", "parent": "#", "text": "parent1" },
					{ "id": "examole2", "parent": "examole1", "text": "1-1" },
						{ "id": "examole23", "parent": "examole2", "text": "1-1-1" },
							{ "id": "examole5", "parent": "examole4", "text": "1-1-1-1" },
						{ "id": "examole4", "parent": "examole2", "text": "1-1-2" },
							{ "id": "examole5", "parent": "examole4", "text": "1-1-2-1" },
							{ "id": "examole6", "parent": "examole4", "text": "1-1-2-2" },
							{ "id": "examole7", "parent": "examole4", "text": "1-1-2-3" },
					{ "id": "examole8", "parent": "examole1", "text": "1-2" },
						{ "id": "examole9", "parent": "examole8", "text": "1-2-1" },
							{ "id": "examole10", "parent": "examole9", "text": "1-2-1-1" },
								{ "id": "examole11", "parent": "examole10", "text": "1-2-1-1-1" },
					{ "id": "examole12", "parent": "examole1", "text": "1-3" },
						{ "id": "examole24", "parent": "examole12", "text": "1-3-1" },
					{ "id": "examole13", "parent": "examole1", "text": "1-4" },
						{ "id": "examole14", "parent": "examole13", "text": "1-4-1" },
						{ "id": "examole15", "parent": "examole13", "text": "1-4-2" },
							{ "id": "examole16", "parent": "examole15", "text": "1-4-2-1" },
				{ "id": "examole17", "parent": "#", "text": "parent2" },
					{ "id": "examole18", "parent": "examole17", "text": "2-1" },
					{ "id": "examole19", "parent": "examole17", "text": "2-2" },
						{ "id": "examole20", "parent": "examole19", "text": "2-2-1" },
						{ "id": "examole21", "parent": "examole19", "text": "2-2-2" },
					{ "id": "examole22", "parent": "examole17", "text": "2-3" },

				
			]
			*/
