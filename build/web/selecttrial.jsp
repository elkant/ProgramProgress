<%-- 
    Document   : selecttrial
    Created on : Mar 6, 2014, 11:53:06 AM
    Author     : Maureen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <script src="scripts/jquery-1.4.4.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
        <!--<script src="media/js/jquery-ui.js" type="text/javascript"></script>-->
<!--   <script src="media/js/jquery.validate.js" type="text/javascript"></script>-->
        <script src="scripts/jquery-ui.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.js" type="text/javascript"></script>
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />

        <script>     
        var editor; // use a global for the submit and return data rendering in the examples
 
// Our custom field type
$.fn.DataTable.Editor.fieldTypes.todo = $.extend( true, {}, $.fn.DataTable.Editor.models.fieldType, {
    "create": function ( conf ) {
        var that = this;
 
        conf._enabled = true;
 
        // Create the elements to use for the input
        conf._input = $( 
            '<div>'+
                '<button class="inputButton" value="0">To do</button>'+
                '<button class="inputButton" value="1">Done</button>'+
            '</div>')[0];
 
        // Use the fact that we are called in the Editor instance's scope to call
        // the API method for setting the value when needed
        $('button.inputButton', conf._input).click( function () {
            that.set( conf.name, $(this).attr('value') );
            return false;
        } );
 
        return conf._input;
    },
 
    "get": function ( conf ) {
        return $('button.selected', conf._input).attr('value');
    },
 
    "set": function ( conf, val ) {
        if ( ! conf._enabled ) {
            return;
        }
 
        $('button.selected', conf._input).removeClass( 'selected' );
        $('button.inputButton[value='+val+']', conf._input).addClass('selected');
    },
 
    "enable": function ( conf ) {
        conf._enabled = true;
        $(conf._input).removeClass( 'disabled' );
    },
 
    "disable": function ( conf ) {
        conf._enabled = false;
        $(conf._input).addClass( 'disabled' );
    }
} );
 
 
$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
        "ajaxUrl": "php/tableFormatting.php",
        "domTable": "#example",
        "fields": [ {
                "label": "Item:",
                "name": "item"
            }, {
                "label": "Status:",
                "name": "done",
                "type": "todo", // Using the custom field type
                "default": 0
            }, {
                "label": "Priority:",
                "name": "priority",
                "type": "select",
                "ipOpts": [
                    { "label": "1 (highest)", "value": "1" },
                    { "label": "2",           "value": "2" },
                    { "label": "3",           "value": "3" },
                    { "label": "4",           "value": "4" },
                    { "label": "5 (lowest)",  "value": "5" }
                ]
            }
        ]
    } );
 
    $('#example').dataTable( {
        "sDom": "Tfrtip",
        "sAjaxSource": "php/tableFormatting.php",
        "aoColumns": [
            { "mData": "priority", "sClass": "center" },
            { "mData": "item" },
            { 
                "sClass": "center",
                "mData": function (source, type, val) {
                    // Use mData to format the data for the table
                    if ( type === 'set' ) {
                        // Save the value
                        source.done = val;
                    }
                    else if ( type === 'display' || type === 'filter' ) {
                        // Filtering and display get the rendered string
                        return (source.done == 0) ? "To do" : "Done";
                    }
                    // Otherwise just give the original data
                    return source.done;
                }
            }
        ],
        "oTableTools": {
            "sRowSelect": "multi",
            "aButtons": [
                { "sExtends": "editor_create", "editor": editor },
                { "sExtends": "editor_edit",   "editor": editor },
                { "sExtends": "editor_remove", "editor": editor }
            ]
        }
    } );
} );
    </script>
    </head>
    <body>
      	
			<div id="demo">
<table cellpadding="0" cellspacing="0" border="0" class="display" id="example" width="100%">
	<thead>
		<tr>
			<th width="15%">Priority</th>
			<th width="70%">Item</th>
			<th width="15%">Status</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th>Priority</th>
			<th>Item</th>
			<th>Status</th>
		</tr>
	</tfoot>
</table>
			</div>
    </body>
</html>
