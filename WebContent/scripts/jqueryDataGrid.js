(function($) {
		$.fn.datagrid = function(options) {
			var settings = {
				dataSource : null,
				afterTableCreate : null,
				dateFormat : 'dd-MMM-yyyy',
				colWidth:null,
				highlightOnSelect:true,
				caseSensitiveSearch:false,
				loadingGif:false,
				hideColumns:false,
				columnOrder:false,
				columnNames:false
			};
			$.extend(settings, options);
			var table = $(this);
			var dataSource = settings['dataSource'];
			var afterTableCreate = settings['afterTableCreate'];
			var dateFormat = settings['dateFormat'];
			var colWidth = settings['colWidth'];
			var highlightOnSelect = settings['highlightOnSelect'];
			var caseSensitiveSearch = settings['caseSensitiveSearch'];
			var loadingGif = settings['loadingGif'];
			var hideColumns = settings['hideColumns'];
			var columnOrder = settings['columnOrder'];
			var columnNames= settings['columnNames'];
			//check whether datasoure and the table element exists
			if (!(dataSource || table.length > 0))
				return;
			//removing all inner elements of table
			table.empty();
			//add loading Gif when image is loading if loadingGif parameter is set
			if(loadingGif)
			 $("<img src="+loadingGif+" />").appendTo(table);
		    // Getting the contents of table from datasource
			$.get(dataSource, function(data) {
				table.empty();
				var tableHeading = $("<thead></thead>");
				//inserting search box into tableHeading
				var firstRow = $("<tr></tr>");
				var vehicleList = $(data).children().children();
				vehicleList.first().children().each(
						function(index) {
							$("<td dval="+this.tagName +"><input style='width:100%'/></td>").appendTo(firstRow);
						});
				firstRow.appendTo(tableHeading);
				// inserting heading into tableHeading
				var secondRow = $("<tr></tr>");
				vehicleList.first().children().each(function(index) {
					$("<th dval="+this.tagName +">" + this.tagName + "</th>").appendTo(secondRow);
				});
				secondRow.appendTo(tableHeading);
				//inserting tableHeading element created into the table in screen
				$(tableHeading).appendTo(table);
				vehicleList.each(function() {
					// inserting data
					var tableRow = $("<tr></tr>");
					$(this).children().each(
							function(index) {
								var value=Date.parse($(this).text());
								var align="left";
								if(value){
								 value= $.format.date(value, dateFormat);
								 align="center";
								}
								else
								 var value=$(this).text();
								$("<td dval="+this.tagName+" align="+align+">" + value + "</td>").appendTo(
										tableRow);
							});
					$(tableRow).appendTo(table);
				});
				// Setting a constant width
				if(colWidth!=null){
					
					table.children().find("td,th").css("min-width",colWidth)
					                              .css("max-width",colWidth)
					                              .css("word-wrap","break-word"); // word-wrap is a CSS3 style
				}
				// change ordering of columns in the order specified by columnOrder (comprises of column dvals
				if(columnOrder){
					for(i=1;i<columnOrder.length;i++){
					  $("[dval="+columnOrder[i]+"]",table).each(function(index){
						  $(this).insertAfter($(this).parent().find("[dval="+columnOrder[i-1]+"]"));
					  });
					
					}
					
				}
			    // Hiding columns specified by hideColumns
				if(hideColumns)
				 for(var i=0;i<hideColumns.length;i++){
					 $("[dval="+hideColumns[i]+"]",table).hide();				   	
				 }
				//Change with the values from columnNames
				if(columnNames){
					for(index in columnNames)
						$("thead > tr:last > th[dval="+index+"]",table).html(columnNames[index]);	
				}
				// Function executes after table is generated
				if(afterTableCreate!=null){
					afterTableCreate();
				}
			// applying styles now
			$("th",table).each(function() {
				$(this).addClass("ui-state-default");
			});
			$("td",table).each(function() {
				$(this).addClass("ui-widget-content");
			});
			$("tr",table).hover(function() {
				$(this).children("td").addClass("ui-state-hover");
			}, function() {
				$(this).children("td").removeClass("ui-state-hover");
			});
			if(highlightOnSelect)
			 $("tr",table).click(function() {
				 $(this).children("td").toggleClass("ui-state-highlight");
			 });
			//adding handlers for search(this is the search logic)
			$(table).find("tr:first input").keyup(
					function(event) {
						var searchValue = (!caseSensitiveSearch)?$(this).val().toLowerCase():$(this).val();
						var positionInDOM = $(this).parent().parent().find("input").index(this);
						$(this).parent().parent().parent().next().children()
								.each(
										function(index) {
											var tdValue=$(this).find("td").eq(positionInDOM).text();
											if(!caseSensitiveSearch)
												tdValue=tdValue.toLowerCase();
											if (tdValue.search(searchValue) >= 0)
												$(this).show(500);
											else
												$(this).hide(500);

										});
					});
			});
			
		}

	})(jQuery);



/// Source from jquery.dateFormat-1.0.js
(function (jQuery) {
	
	var daysInWeek = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	var shortMonthsInYear = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
	var longMonthsInYear = ["January", "February", "March", "April", "May", "June", 
													"July", "August", "September", "October", "November", "December"];
	var shortMonthsToNumber = [];
	shortMonthsToNumber["Jan"] = "01";
	shortMonthsToNumber["Feb"] = "02";
	shortMonthsToNumber["Mar"] = "03";
	shortMonthsToNumber["Apr"] = "04";
	shortMonthsToNumber["May"] = "05";
	shortMonthsToNumber["Jun"] = "06";
	shortMonthsToNumber["Jul"] = "07";
	shortMonthsToNumber["Aug"] = "08";
	shortMonthsToNumber["Sep"] = "09";
	shortMonthsToNumber["Oct"] = "10";
	shortMonthsToNumber["Nov"] = "11";
	shortMonthsToNumber["Dec"] = "12";

jQuery.format = (function () {
    function strDay(value) {
						return daysInWeek[parseInt(value, 10)] || value;
    }

    function strMonth(value) {
					var monthArrayIndex = parseInt(value, 10) - 1;
						return shortMonthsInYear[monthArrayIndex] || value;
    }

    function strLongMonth(value) {
				var monthArrayIndex = parseInt(value, 10) - 1;
				return longMonthsInYear[monthArrayIndex] || value;					
    }

    var parseMonth = function (value) {
				return shortMonthsToNumber[value] || value;
    };

    var parseTime = function (value) {
            var retValue = value;
            var millis = "";
            if (retValue.indexOf(".") !== -1) {
                var delimited = retValue.split('.');
                retValue = delimited[0];
                millis = delimited[1];
            }

            var values3 = retValue.split(":");

            if (values3.length === 3) {
                hour = values3[0];
                minute = values3[1];
                second = values3[2];

                return {
                    time: retValue,
                    hour: hour,
                    minute: minute,
                    second: second,
                    millis: millis
                };
            } else {
                return {
                    time: "",
                    hour: "",
                    minute: "",
                    second: "",
                    millis: ""
                };
            }
        };

    return {
        date: function (value, format) {
            /* 
				value = new java.util.Date()
             	2009-12-18 10:54:50.546 
			*/
            try {
                var date = null;
                var year = null;
                var month = null;
                var dayOfMonth = null;
                var dayOfWeek = null;
                var time = null;
									if (typeof value == "number"){
										return this.date(new Date(value), format);
									} else if (typeof value.getFullYear == "function") {
                    year = value.getFullYear();
                    month = value.getMonth() + 1;
                    dayOfMonth = value.getDate();
                    dayOfWeek = value.getDay();
                    time = parseTime(value.toTimeString());
									} else if (value.search(/\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.?\d{0,3}[-+]?\d{2}:?\d{2}/) != -1) { /* 2009-04-19T16:11:05+02:00 */											
                    var values = value.split(/[T\+-]/);
                    year = values[0];
                    month = values[1];
                    dayOfMonth = values[2];
                    time = parseTime(values[3].split(".")[0]);
                    date = new Date(year, month - 1, dayOfMonth);
                    dayOfWeek = date.getDay();
                } else {
                    var values = value.split(" ");
                    switch (values.length) {
                    case 6:
                        /* Wed Jan 13 10:43:41 CET 2010 */
                        year = values[5];
                        month = parseMonth(values[1]);
                        dayOfMonth = values[2];
                        time = parseTime(values[3]);
                        date = new Date(year, month - 1, dayOfMonth);
                        dayOfWeek = date.getDay();
                        break;
                    case 2:
                        /* 2009-12-18 10:54:50.546 */
                        var values2 = values[0].split("-");
                        year = values2[0];
                        month = values2[1];
                        dayOfMonth = values2[2];
                        time = parseTime(values[1]);
                        date = new Date(year, month - 1, dayOfMonth);
                        dayOfWeek = date.getDay();
                        break;
                    case 7:
                        /* Tue Mar 01 2011 12:01:42 GMT-0800 (PST) */
                    case 9:
                        /*added by Larry, for Fri Apr 08 2011 00:00:00 GMT+0800 (China Standard Time) */
                    case 10:
                        /* added by Larry, for Fri Apr 08 2011 00:00:00 GMT+0200 (W. Europe Daylight Time) */
                        year = values[3];
                        month = parseMonth(values[1]);
                        dayOfMonth = values[2];
                        time = parseTime(values[4]);
                        date = new Date(year, month - 1, dayOfMonth);
                        dayOfWeek = date.getDay();
                        break;
                    case 1:
                        /* added by Jonny, for 2012-02-07CET00:00:00 (Doctrine Entity -> Json Serializer) */
                        var values2 = values[0].split("");
                        year=values2[0]+values2[1]+values2[2]+values2[3];
                        month= values2[5]+values2[6];
                        dayOfMonth = values2[8]+values2[9];
                        time = parseTime(values2[13]+values2[14]+values2[15]+values2[16]+values2[17]+values2[18]+values2[19]+values2[20])
                        date = new Date(year, month - 1, dayOfMonth);
                        dayOfWeek = date.getDay();
                        break;
                    default:
                        return value;
                    }
                }

                var pattern = "";
                var retValue = "";
                var unparsedRest = "";
                /*
					Issue 1 - variable scope issue in format.date 
                	Thanks jakemonO
				*/
                for (var i = 0; i < format.length; i++) {
                    var currentPattern = format.charAt(i);
                    pattern += currentPattern;
                    unparsedRest = "";
                    switch (pattern) {
                    case "ddd":
                        retValue += strDay(dayOfWeek);
                        pattern = "";
                        break;
                    case "dd":
                        if (format.charAt(i + 1) == "d") {
                            break;
                        }
                        if (String(dayOfMonth).length === 1) {
                            dayOfMonth = '0' + dayOfMonth;
                        }
                        retValue += dayOfMonth;
                        pattern = "";
                        break;
                    case "d":
                        if (format.charAt(i + 1) == "d") {
                            break;
                        }
                        retValue += parseInt(dayOfMonth, 10);
                        pattern = "";
                        break;
                    case "MMMM":
                        retValue += strLongMonth(month);
                        pattern = "";
                        break;
                    case "MMM":
                        if (format.charAt(i + 1) === "M") {
                            break;
                        }
                        retValue += strMonth(month);
                        pattern = "";
                        break;
                    case "MM":
                        if (format.charAt(i + 1) == "M") {
                            break;
                        }
                        if (String(month).length === 1) {
                            month = '0' + month;
                        }
                        retValue += month;
                        pattern = "";
                        break;
                    case "M":
                        if (format.charAt(i + 1) == "M") {
                            break;
                        }
                        retValue += parseInt(month, 10);
                        pattern = "";
                        break;
                    case "yyyy":
                        retValue += year;
                        pattern = "";
                        break;
                    case "yy":
                        if (format.charAt(i + 1) == "y" &&
                       	format.charAt(i + 2) == "y") {
                        	break;
                  	    }
                        retValue += String(year).slice(-2);
                        pattern = "";
                        break;
                    case "HH":
                        retValue += time.hour;
                        pattern = "";
                        break;
                    case "hh":
                        /* time.hour is "00" as string == is used instead of === */
                        var hour = (time.hour == 0 ? 12 : time.hour < 13 ? time.hour : time.hour - 12);
                        hour = String(hour).length == 1 ? '0' + hour : hour;
                        retValue += hour;
                        pattern = "";
                        break;
											case "h":
											    if (format.charAt(i + 1) == "h") {
											        break;
											    }
											    var hour = (time.hour == 0 ? 12 : time.hour < 13 ? time.hour : time.hour - 12);                           
											    retValue += parseInt(hour, 10);
													// Fixing issue https://github.com/phstc/jquery-dateFormat/issues/21
													// retValue = parseInt(retValue, 10);
											    pattern = "";
											    break;
                    case "mm":
                        retValue += time.minute;
                        pattern = "";
                        break;
                    case "ss":
                        /* ensure only seconds are added to the return string */
                        retValue += time.second.substring(0, 2);
                        pattern = "";
                        break;
                    case "SSS":
                        retValue += time.millis.substring(0, 3);
                        pattern = "";
                        break;
                    case "a":
                        retValue += time.hour >= 12 ? "PM" : "AM";
                        pattern = "";
                        break;
                    case " ":
                        retValue += currentPattern;
                        pattern = "";
                        break;
                    case "/":
                        retValue += currentPattern;
                        pattern = "";
                        break;
                    case ":":
                        retValue += currentPattern;
                        pattern = "";
                        break;
                    default:
                        if (pattern.length === 2 && pattern.indexOf("y") !== 0 && pattern != "SS") {
                            retValue += pattern.substring(0, 1);
                            pattern = pattern.substring(1, 2);
                        } else if ((pattern.length === 3 && pattern.indexOf("yyy") === -1)) {
                            pattern = "";
                        } else {
                        	unparsedRest = pattern;
                        }
                    }
                }
                retValue += unparsedRest;
                return retValue;
            } catch (e) {
                console.log(e);
                return value;
            }
        }
    };
}());
}(jQuery));

jQuery.format.date.defaultShortDateFormat = "dd/MM/yyyy";
jQuery.format.date.defaultLongDateFormat = "dd/MM/yyyy hh:mm:ss";

jQuery(document).ready(function () {
jQuery(".shortDateFormat").each(function (idx, elem) {
    if (jQuery(elem).is(":input")) {
        jQuery(elem).val(jQuery.format.date(jQuery(elem).val(), jQuery.format.date.defaultShortDateFormat));
    } else {
        jQuery(elem).text(jQuery.format.date(jQuery(elem).text(), jQuery.format.date.defaultShortDateFormat));
    }
});
jQuery(".longDateFormat").each(function (idx, elem) {
    if (jQuery(elem).is(":input")) {
        jQuery(elem).val(jQuery.format.date(jQuery(elem).val(), jQuery.format.date.defaultLongDateFormat));
    } else {
        jQuery(elem).text(jQuery.format.date(jQuery(elem).text(), jQuery.format.date.defaultLongDateFormat));
    }
});
});