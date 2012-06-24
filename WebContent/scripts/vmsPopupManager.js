var vmsPopupManager={
		"showAddPopup":function(operationsDialog){
			  operationsDialog.find(".date").datepicker({dateFormat:datepickerDateFormat});
			  operationsDialog.find(":text").val("");
    		  operationsDialog.find("input:first").prop("disabled",false);
    		  operationsDialog.dialog("destroy");// added so that the update dialog doesn't affect this (same element dialogs)
    		  operationsDialog.dialog({buttons:{
    			  "Add":function(){
    				  vmsDBOper.formId=operationsDialog.attr("id");
    				  vmsDBOper.add();
    				  $(this).dialog("close");
    			  }
    		   },
    		   width:"680px",show:globalPopupEffect,hide:globalPopupEffect});
		},
		"showUpdatePopup":function(operationsDialog,selectedRow){
			operationsDialog.find("input:text").each(function(){
				var textBox=$(this);
				textBox.val(selectedRow.find("td[dval="+textBox.attr("name")+"]").text());
			});
			operationsDialog.find(".date").datepicker({dateFormat:datepickerDateFormat});
			operationsDialog.find("input:first").prop("disabled",true);
			operationsDialog.dialog({buttons:{
  			   "Update":function(){
  				  vmsDBOper.formId=operationsDialog.attr("id");
				  vmsDBOper.update();
				  $(this).dialog("close");
			   },
			   "Delete":function(){
				   vmsDBOper.formId=operationsDialog.attr("id");
				   vmsDBOper.del();
				   $(this).dialog("close");
			   }
			  },
			  width:"680px",show:globalPopupEffect,hide:globalPopupEffect,modal:true});
		}
};
