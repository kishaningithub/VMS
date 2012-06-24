var vmsDBOper ={
		"formId":"",
		"updateCore":function(recordStatus){
			var vehicleOperationsForm=$("#"+vmsDBOper.formId);
			vehicleOperationsForm.find("input[name='recordStatus']").val(recordStatus);
			$("input:first",vehicleOperationsForm).prop("disabled",false);
			$.get(vehicleOperationsForm.attr("action"),
					vehicleOperationsForm.serialize(),
					  function(data){
				        vmsAlert(successMessages.operationCompleted);
				      })
				      .error(function(){
				    	  vmsAlert(successMessages.operationCompleted);
				      });
			$("input:first",vehicleOperationsForm).prop("disabled",false);
		},
		"add":function(){
			vmsDBOper.updateCore('A');// core logic
		},
		"update":function(){
			vmsDBOper.updateCore('A');// core logic
		},
		"del":function(){// Just shows a dialog and triggers updateCore() with parameter D
			$(warnings.deleteWarning).dialog({
				buttons: {
					"Delete": function() {
							$( this ).dialog( "close" );
							vmsDBOper.updateCore('D'); // core logic
					 },
					"Cancel": function() {
							$( this ).dialog( "close" );
					}
				  },
				  modal: true,show:globalPopupEffect,hide:globalPopupEffect
				});
		}
};
