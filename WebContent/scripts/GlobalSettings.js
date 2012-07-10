var gridDateFormat='dd/MMM/yyyy';
var datepickerDateFormat='dd/M/yy'; // Throughout app date format is 23/Aug/2030, just that there are different 'format strings' for the same 'format'
var globalPopupEffect="scale";
var warnings={
		deleteWarning:'<div id="deleteWarning" title="Delete?">'+  
		             ' <p>'+
		             '  <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"/>'+
		             '  This will be permanently deleted and cannot be recovered. Are you sure?'+
		             ' </p>'+	
		             '</div>'
};
var errors={
		operationError:'<div id="operationError" title="Sorry!">'+  
        ' <p>'+
        '  <span class="ui-icon ui-icon-notice" style="float:left; margin:0 7px 20px 0;"/>'+
        '   Internal Error has occured. Sorry for inconvinience'+
        ' </p>'+	
        '</div>'
};
var successMessages={
	  operationCompleted:'<div id="operationCompleted" title="Sucess">'+  
                          ' <p>'+
                          '  <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 20px 0;"/>'+
                          '  Operation performed sucessfully!'+
                          ' </p>'+	
                          '</div>'
};
$(document).ready(function(){
	$("#driverAttendance").load("DriverAttendance.html");
	$("#vehicleDetails").load("VehicleDetails.html");
	$("#driverDetails").load("DriverDetails.html");
	$("#vmsMenu").tabs();
	$(".date").datepicker({dateFormat:datepickerDateFormat}); 
	$(".button").button();
	$( "#accordion" ).accordion({event: "mouseover"});
});
function returnResponse(urlLink)
{
	var response=$.ajax({
		url:urlLink,
		async:false
	}).responseText;
	return $(response);
}
function vmsAlert(message)
{
	$(message).dialog({buttons:{
		"Ok":function(){
			$(this).dialog("close");
		}
	   },modal:true,show:globalPopupEffect,hide:globalPopupEffect});
}
function vmsError(message)
{
	var errorMessage='<div id="operationError" title="Sorry!">'+  
    ' <p>'+
    '  <span class="ui-icon ui-icon-notice" style="float:left; margin:0 7px 20px 0;"/>'+
     message +
    ' </p>'+	
    '</div>';
	$(errorMessage).dialog({buttons:{
		"Ok":function(){
			$(this).dialog("close");
		}
	   },modal:true,show:globalPopupEffect,hide:globalPopupEffect});
}