function reloadVmsGrid(settings)
{
	if(settings==null){
		alert('settings is null');
		return;
	}
		
	var operationsDialog=settings.OperationsDialog;
	var gridId=settings.gridId;
	var addButtonId=settings.addButtonId;
	var gridColumnOrder=settings.gridColumnOrder;
	var columnNames=settings.columnNames;
	var hideColumns=settings.hideColumns;
	$(gridId).datagrid({dataSource:settings.gridDataSource,
		                          dateFormat: gridDateFormat,
		                          highlightOnSelect:false,
		                          loadingGif:"images/loading.gif",
		                          colWidth:'1px', // just width magic!
		                          hideColumns:hideColumns,
		                          columnOrder:gridColumnOrder,
		                          columnNames:columnNames,
		                          afterTableCreate:function(){
		                        	  standardistaTableSorting.init();
		                        	 // Removing unwanted info from place of work
		                        	  // The value returned will be A2Madras
		                        	  // where A-> Record status 2->Region Code Madras->Region Name
		                        	  // we just want region name!
		                        	  if(gridId=="#driverDetailsGrid")
		                        	  $("tbody td[dval='workLocation']").each(function(){
		                        		  $(this).text($(this).text().substr(2));
		                        	  })
		                        	 // Will fire on double click of a row in all data grid
		                        	  $(gridId).find("tbody > tr").dblclick(function(){
		                        		  var selectedRow=$(this);
		                        		  vmsPopupManager.showUpdatePopup(operationsDialog,selectedRow,settings);
		                        	  });
		                        	  // Handler will be called when + button is clicked
		                        	  $(addButtonId).click(function(){
		                        		  vmsPopupManager.showAddPopup(operationsDialog,settings);
		                        	  })
		                          }});
}