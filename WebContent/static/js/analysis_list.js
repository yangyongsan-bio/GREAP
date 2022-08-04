$(document).ready(function(){
        $("[data-toggle='popover']").popover();
    } );

$(document).ready(function(){
    var table = $('#example').DataTable( {
    	dom: '<"pull-left"B>ft<"pull-left"i>p',
	    buttons: [
	               {
	                    text: '<span class="glyphicon glyphicon-save"><font>Export</font></span>',
	                    extend: 'csv'
	                }],
	    aLengthMenu : [5],
	    scrollX: true,
    } );
    $('#example tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
           
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            var id = Math.random()*1.0E20;
            var set = row.data()[1];
            var data_class = $("#data_class").text();
            var tf_class = $("#tf_class").text();
            var atac_class = $("#atac_class").text();
            var tcof_class = $("#tcof_class").text();
            var lnc_class = $("#lnc_class").text();
            var enhancer_class = $("#enhancer_class").text();
            var se_class = $("#se_class").text();
            var risk_class = $("#risk_class").text();
            
            row.child(format(id)).show();
            tr.addClass('shown');
            $.ajax({
            	url: '../analysis/getRegion',
                data:{"data_class":data_class,"set":set},
                type: "GET",
                success: function (data) {
                	if(data_class == "TF"){
                		var myArray=eval(data);
                        $('#'+ id).DataTable( {
                       	    "data":myArray,
                       	    "bLengthChange":false,
                       	    "scrollX" : false,
                               "searching": false,
                               "aLengthMenu": [3],
   				               "columns": [
   								    {
   										"data":"",
   									},
   									
   									{
   										"data":"",
   									},
   									{
   										"data":"string7",
   									},
   									{
   										"data":"",
   									},
   									{
   										"data":"",
   									},
   									{
   										"data":"",
   									},
   								    ],
   						    "columnDefs": [
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = '<a href="http://localhost:8080/TRlnc/analysis/region_detail?region=' + row.string1 +":"+ row.string2 +"-"+ row.string3 + '&tf_class='+ tf_class  + '&atac_class='+ atac_class + '&tcof_class='+ tcof_class + '&lnc_class='+ lnc_class + '&enhancer_class='+ enhancer_class + '&se_class='+ se_class + '&risk_class='+ risk_class + ' " target="_blank">' + row.string1 +":"+ row.string2 +"-"+ row.string3+ '</a>';
   				                                   return html;
   				                               },
   				                               "targets": 0
   				                           },
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = row.string4 +":"+ row.string5 +"-"+ row.string6 ;
   				                                   return html;
   				                               },
   				                               "targets": 1
   				                           },  
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = row.string10.replace("_",".") ;
   				                                   return html;
   				                               },
   				                               "targets": 3
   				                           }, 
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = row.string11.replace("_",".") ;
   				                                   return html;
   				                               },
   				                               "targets": 4
   				                           },
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = row.string12.replace("_",".") ;
   				                                   return html;
   				                               },
   				                               "targets": 5
   				                           },
   				                       ],
   			                } );
                	}
                	if(data_class == "RiskSNP"){
                		var myArray=eval(data);
                        $('#'+ id).DataTable( {
                       	    "data":myArray,
                       	    "bLengthChange":false,
                       	    "scrollX" : false,
                               "searching": false,
                               "aLengthMenu": [3],
   				               "columns": [
   								    {
   										"data":"",
   									},
   									
   									{
   										"data":"",
   									},
   									{
   										"data":"string7",
   									},
   									{
   										"data":"string8",
   									},
   									{
   										"data":"string9",
   									},
   									{
   										"data":"string10",
   									},
   									{
   										"data":"string11",
   									},
   								    ],
   						    "columnDefs": [
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = '<a href="http://localhost:8080/TRlnc/analysis/region_detail?region=' + row.string1 +":"+ row.string2 +"-"+ row.string3 + '&tf_class='+ tf_class  + '&atac_class='+ atac_class + '&tcof_class='+ tcof_class + '&lnc_class='+ lnc_class + '&enhancer_class='+ enhancer_class + '&se_class='+ se_class + '&risk_class='+ risk_class + ' " target="_blank">' + row.string1 +":"+ row.string2 +"-"+ row.string3+ '</a>';
   				                                   return html;
   				                               },
   				                               "targets": 0
   				                           },
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = row.string4 +":"+ row.string5 ;
   				                                   return html;
   				                               },
   				                               "targets": 1
   				                           }, 
   				                       ],
   			                } );
                	}
                	if(data_class == "Enhancer"){
                		var myArray=eval(data);
                        $('#'+ id).DataTable( {
                       	    "data":myArray,
                       	    "bLengthChange":false,
                       	    "scrollX" : false,
                               "searching": false,
                               "aLengthMenu": [3],
   				               "columns": [
   								    {
   										"data":"",
   									},
   									
   									{
   										"data":"",
   									},
   									{
   										"data":"string7",
   									},
   									{
   										"data":"string8",
   									},
   									{
   										"data":"string9",
   									},
   									{
   										"data":"string10",
   									},
   									{
   										"data":"string14",
   									},
   								    ],
   						    "columnDefs": [
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = '<a href="http://localhost:8080/TRlnc/analysis/region_detail?region=' + row.string1 +":"+ row.string2 +"-"+ row.string3 + '&tf_class='+ tf_class  + '&atac_class='+ atac_class + '&tcof_class='+ tcof_class + '&lnc_class='+ lnc_class + '&enhancer_class='+ enhancer_class + '&se_class='+ se_class + '&risk_class='+ risk_class + ' " target="_blank">' + row.string1 +":"+ row.string2 +"-"+ row.string3+ '</a>';
   				                                   return html;
   				                               },
   				                               "targets": 0
   				                           },
   				                           {
   				                               "render": function (data, type, row, meta) {
   				                                   var html = row.string4 +":"+ row.string5 + "-" + row.string6;
   				                                   return html;
   				                               },
   				                               "targets": 1
   				                           }, 
   				                       ],
   			                } );
                	}
                	
                }
            }) 
            
        }
    } );
} );
function format(a){
    // `d` is the original data object for the row
	var data_class = $("#data_class").text();
	
	if(data_class == "TF"){
		return'<table id="'+ a + '" class="table table-bordered" style="width: 100%;">'+
        '<thead>'+
        '<tr>'+
        '<th>Input region</th>'+
        '<th>TF region</th>'+
        '<th>Peak name</th>'+
        '<th>fold change</th>'+
        '<th>-log10pvalue</th>'+
        '<th>-log10qvalue</th>'+
        '</tr>'+
        '</thead>'+
    '</table>';
	}
	if(data_class == "RiskSNP"){
		return'<table id="'+ a + '" class="table table-bordered" style="width: 100%;">'+
        '<thead>'+
        '<tr>'+
        '<th>Input region</th>'+
        '<th>SNP position</th>'+
        '<th>SNP id</th>'+
        '<th>Phenotype</th>'+
        '<th>Phenotype escription</th>'+
        '<th>P_value</th>'+
        '<th>Pubmed id</th>'+
        '</tr>'+
        '</thead>'+
    '</table>';
	}
	if(data_class == "Enhancer"){
		return'<table id="'+ a + '" class="table table-bordered" style="width: 100%;">'+
        '<thead>'+
        '<tr>'+
        '<th>Input region</th>'+
        '<th>Enhancer region</th>'+
        '<th>Sample id</th>'+
        '<th>TE id</th>'+
        '<th>Rank</th>'+
        '<th>Element</th>'+
        '<th>isSE</th>'+
        '</tr>'+
        '</thead>'+
    '</table>';
	}
}