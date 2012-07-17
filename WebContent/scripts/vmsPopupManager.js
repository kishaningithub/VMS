var vmsPopupManager={
		"showAddPopup":function(operationsDialog,dataGridSettings){
			  operationsDialog.find(".date").datepicker({dateFormat:datepickerDateFormat});
			  operationsDialog.find(":text, :hidden").val("");
			  // populating region - applicable only for driver details 
			  if(dataGridSettings.gridId=="#driverDetailsGrid") 
			  {
			   var regionSelect=$("select[name='workLocation']",operationsDialog);
			   regionSelect.empty();
    		   $("#regionGrid tbody tr").each(function(){
    				var code =$("td[dval='regionCode']",this).html();
    				var value=$("td[dval='regionName']",this).html();
    				$("<option value="+code+">"+value+"</option>").appendTo(regionSelect);
    			});
			  }
    		  // removing the disable of first element
    		  operationsDialog.find("input:first").prop("disabled",false);
    		  operationsDialog.dialog({buttons:{
    			  "Add":function(){
    				  vmsDBOper.formId=operationsDialog.attr("id");
    				  vmsDBOper.add();
    				  $(this).dialog("close");
    			  },
    		   },
    		   close:function(){
    			   reloadVmsGrid(dataGridSettings);
    			   operationsDialog.dialog("destroy");// added so that the update dialog doesn't affect this (same element dialogs)
  		       },
    		   width:"680px",show:globalPopupEffect,hide:globalPopupEffect});
		},
		"showUpdatePopup":function(operationsDialog,selectedRow,dataGridSettings){
			operationsDialog.dialog("destroy");// added so that the update dialog doesn't affect this (same element dialogs)
			operationsDialog.find("input:text, input:hidden").each(function(){
				var textBox=$(this);
				textBox.val(selectedRow.find("td[dval="+textBox.attr("name")+"]").text());
			});
			operationsDialog.find(".date").datepicker({dateFormat:datepickerDateFormat});
			if(dataGridSettings.gridId=="#driverDetailsGrid") 
			{
				 // populating region - applicable only for driver detail
				 var regionSelect=$("select[name='workLocation']");
				 regionSelect.empty();
				 $("#regionGrid tbody tr").each(function(){
					 var code =$("td[dval='regionCode']",this).html();
					 var value=$("td[dval='regionName']",this).html();
					 $("<option value="+code+">"+value+"</option>").appendTo(regionSelect);
				 });
				 // selecting value from select element
				 operationsDialog.find("select").each(function(){
					 var select=$(this);
					 select.find("option").each(function(){
						 var selectVal=selectedRow.find("td[dval="+select.attr("name")+"]").text();
						 if($(this).text()==selectVal)
							 $(this).prop("selected",true);
						 else
							 $(this).removeProp("selected");
					 });
				 });
			}
  		  //disabling the first text element
			operationsDialog.find("input:first").prop("disabled",true);
			operationsDialog.dialog({buttons:{
  			   "Update":function(){
  				  vmsDBOper.formId=operationsDialog.attr("id");
				  vmsDBOper.update();
				  $(this).dialog("close");
			   },
			   "Delete":function(){
				   vmsDBOper.formId=operationsDialog.attr("id");
				   vmsDBOper.del(dataGridSettings);
				   $(this).dialog("close");
			   }
			  },
			  close:function(){
				  reloadVmsGrid(dataGridSettings);
				  operationsDialog.dialog("destroy");// added so that the add dialog doesn't affect this (same element dialogs)
			  },
			  width:"680px",show:globalPopupEffect,hide:globalPopupEffect,modal:true});
		}
};
