<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Result</title>
    <link rel="icon" type="image/x-icon" href="${base.contextPath}/static/img/favicon.ico"/>
    <link  rel="stylesheet" href="${base.contextPath}/static/css/bootstrap.min.css">
    <script src="${base.contextPath}/static/js/jquery.min.js"></script>
    <script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${base.contextPath}/static/css/header.css">
    <link rel="stylesheet" href="${base.contextPath}/static/css/footer.css"/>

    <!-- DataTable buttons -->
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/buttons.dataTables.min.css"/>

    <script type="text/javascript" src="${base.contextPath}/static/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${base.contextPath}/static/js/dataTables.buttons.min.js"></script>
    <!--  buttons 瀵煎嚭鍔熻兘-->
    <script src="${base.contextPath}/static/js/buttons.html5.min.js"></script>


    <link rel="stylesheet" href="${base.contextPath}/static/css/style.css" >
   
    <style type="text/css">
        tr {text-align: center;}
	    th {text-align: center;}
	    td.details-control {
		    background: url('${base.contextPath}/static/img/details_open.png') no-repeat center center;
		    cursor: pointer;
		}
		tr.shown td.details-control {
		    background: url('${base.contextPath}/static/img/details_close.png') no-repeat center center;
		}
	    .popover {
				word-break: break-all;
				}
				
        .bottom{margin-top:12px;}
        .dataTables_filter{margin-top:8px;}
        .dataTables_length{margin-top:8px;}


        .black_overlay{
            display: none;
            position: fixed;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: gray;
            z-index:1001;
            -moz-opacity: 0.8;
            opacity:.80;
            filter: alpha(opacity=88);
        }
        .white {

            display: none;
            width: 500px;
            height: 450px;
            background-color: white;
            position: fixed;
            z-index:1005;
            margin-left:23%;
            margin-top:-20px;
            text-align:center;
            font-size:20px;
            font-family:榛戜綋;
            color:#84072a;

            z-index:1005;

        }
        .white_content {

            position: absolute;
            top: 14%;
            right:  0%;
            width: 100%;
            height: 80%;
            text-align:center;
            font-size:20px;
            font-family:榛戜綋;
            color:#84072a;
            background-color: white;
            z-index:1004;
            overflow: auto;
            overflow-y:auto;
        }
        .cl{
            position: absolute;

            top:0%;
            right: 0%;
            z-index: 1005;
            color: #84072a;
        }
        .kl{
            position: absolute;
            font-size:37px;
            top:3%;
            right: 45%;
            z-index: 1005;
            color: #5183f2;
        }
        hr {
            border: 0;
            border-bottom: 2px solid black;
            z-index: 1007;
        }
        .qp{
            position:absolute;
            margin-top: 100px;
            margin-left:100px;
            width: 500px;

            height: 500px;
        }

        #accordion .panel-heading{
            padding: 0;
            border: none;
            border-radius: 0;
            position: relative;
        }
        #accordion .panel-title {
            display: block;
            padding: 15px 20px;
            margin: 0;
            background: #034786;
            font-size: 18px;
            font-weight: 700;
            letter-spacing: 1px;
            color: #fff;
            border-radius: 0;
            position: relative;
        }
        #accordion .panel-title .collapsed{
            background: #034786;}

        #accordion .panel-body{
            border-left: 3px solid #034786;
            border-top: none;
            background: #fff;
            font-size: 15px;
            color: #1c2336;
            line-height: 27px;
            position: relative;
        }
        #accordion .panel-body:before{
            content: "";
            height: 3px;
            width: 100%;
            background: #034786;
            position: absolute;
            bottom: 0;
            left: 0;
        }
        /* 		.btn-warning {
                color: #fff;
                background-color:#337ab7;
                border-color:#204d74;
                font-size: 10px;
                }
                .btn-warning:hover {
                color: #fff;
                background-color:#286090;
                border-color:#204d74;
                } */


        .hr1{ height:1px;border:none;border-top:3px solid #346191;}
    </style>
</head>
<body id="body">
<!--==========================
  Header
============================-->
            <div class="row"><!---鎵嬮鐞村紑濮�--->
                <div class="col-md-12 col-md-offset">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin: 30px 50px;">	<!---鎵嬮鐞�/panel-group/寮�濮�--->
                        <div class="panel panel-default" ><!---鎵嬮鐞�/tissue-based/start--->
                            <div class="panel-heading" role="tab" id="1" >
                                <h4 class="panel-title" >
                                    <span class="glyphicon glyphicon-list"></span> <font id="tname">Result</font>
                                </h4>
                            </div>
                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                                <div class="panel-body" style="overflow-x:auto;">
                                    <a type="button" class="btn btn-general btn-blue" style="text-transform: capitalize;border: 2px solid white;background-color:#ab0e0e;color: white;box-shadow: 0px 3px 24px 0px rgba(27, 140, 253, 0);padding: 10px 20px;" onclick = openOne("${base.contextPath}/view/analysis_chart_bubble")><img src="${base.contextPath}/static/img/qipao.png" style="width: 20px;margin-top: -5px;margin-right: 5px;">Bubble chart</a>
                                    <a type="button" class="btn btn-general btn-blue" style="text-transform: capitalize;border: 2px solid white;background-color:#ab0e0e;color: white;box-shadow: 0px 3px 24px 0px rgba(27, 140, 253, 0);padding: 10px 20px;" onclick = openTwo("${base.contextPath}/view/analysis_chart_bar")><img src="${base.contextPath}/static/img/bar.png" style="width: 20px;margin-top: -5px;margin-right: 5px;">Bar chart</a>
                                    
                                    <table style="border-collapse: collapse;float: right;">
										<tbody>
											<tr>
												<th style="border:0px solid black;background: #ADF7B2;height: 40px;width:40%">0-0.01</th>
												<th style="border:0px solid black;background:#60BDA0;height: 40px;;width:40%">0.01-0.05</th>
											</tr>
										</tbody>
									</table>
                                    <table id="example" class="table table-bordered" style="width:100%">
                                        <thead>
                                            <#if RequestParameters.method == "1">
	                                        <tr>
        										<th style="background-image:none"></th>
	                                            <th>Set</th>
	                                            <th>Class</th>
	                                            <th>Sub class</th>
	                                            <th>Count <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="Number of intersections"></i> </th>
	                                            <th>Num <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="The number of set"></i></th>
	                                            <th>P_value <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="Hypergeometric P value."></i></th>
	                                            <th>pValueLog <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="The -log10 of hypergeometric P value."></i></th>
	                                            <th>BH <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="Adjusted p value with FalseDiscovery Rate method."></i></th>
	                                            <th>Bonferroni <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="Adjusted p value with FalseDiscovery Rate method."></i></th>
	                                        </tr>
	                                        <script>
	                                            function format(set, id) {
												    let html = '<table id="' + id + '" class="table table-bordered"><thead>';
													let length = ${RequestParameters.lineLength};
													html += "<tr><td>Reference</td><td>Input</td>"
													if (length !== 3) {
														if (length >= 4) {
														    html += "<td>V4</td>"
														}
														if (length >= 5) {
														    html += "<td>V5</td>"
														}
														if (length >= 6) {
														    html += "<td>V6</td>"
														}
														if (length >= 7) {
														    html += "<td>V7</td>"
														}
														if (length >= 8) {
														    html += "<td>V8</td>"
														}
														if (length >= 9) {
														    html += "<td>V9</td>"
														}
														if (length >= 10) {
														    html += "<td>V10</td>"
														}
														if (length >= 11) {
														    html += "<td>V11</td>"
														}
														if (length >= 12) {
														    html += "<td>V12</td>"
														}
													}
													html += "</tr></thead><tbody>"
												    $.ajax({
												        url: "../email/subTable",
												        type: "GET",
												        data: {
												            "userId": '${RequestParameters.userId}',
												            "method": '${RequestParameters.method}',
												            "hg": '${RequestParameters.hg}',
												            "datatype": '${RequestParameters.datatype}',
												            "set": set
												        },
												        contentType: "application/json;charsetset=UTF-8",
												        datatype: "json",
												        async: false,
												        success: function (data) {
												            data = eval(JSON.stringify(data));
												            data.forEach((item) => {
												                html += '<tr><td>' + item.string2 + '</td><td>' + item.string1 + '</td>';
																if (length !== 3) {
																	if (length >= 4) {
																	    html += "<td>" + item.string3 + "</td>"
																	}
																	if (length >= 5) {
																	    html += "<td>" + item.string4 + "</td>"
																	}
																	if (length >= 6) {
																	    html += "<td>" + item.string5 + "</td>"
																	}
																	if (length >= 7) {
																	    html += "<td>" + item.string6 + "</td>"
																	}
																	if (length >= 8) {
																	    html += "<td>" + item.string7 + "</td>"
																	}
																	if (length >= 9) {
																	    html += "<td>" + item.string8 + "</td>"
																	}
																	if (length >= 10) {
																	    html += "<td>" + item.string9 + "</td>"
																	}
																	if (length >= 11) {
																	    html += "<td>" + item.string10 + "</td>"
																	}
																	if (length >= 12) {
																	    html += "<td>" + item.string11 + "</td>"
																	}
																}
												                html += '</tr>';
												            });
												        }
												    });
												    html += '</tbody></table>';
												    return html;
												}

		                                        let table = $('#example').DataTable({
												    oLanguage: {
												        sLoadingRecords: '<img src="${base.contextPath}/static/img/loading.gif" style="width: 350px;">'
												    },
												    dom: '<"pull-left"B>ft<"pull-left"i>p',
												    buttons: [{
												        text: '<span class="glyphicon glyphicon-save"><font>Export</font></span>',
												        extend: 'csv'
												    }],
												    aLengthMenu: [5],
												    "order": [[ 6, "asc" ]],
												    fnRowCallback: function(nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
												        if (0 < aData.string4 && aData.string4 < 0.01) {
												            $('td:eq(6)', nRow).css('background-color', '#adf7b2');
												        } else if (0.01 < aData.string4 && aData.string4 < 0.05 ) {
												            $('td:eq(6)', nRow).css('background-color', '#60bda0');
												        } else {
												            $('td:eq(6)', nRow).css('background-color', 'white');
												        }
												        if (0 < aData.string5 && aData.string5 < 0.01) {
												            $('td:eq(8)', nRow).css('background-color', '#adf7b2');
												        } else if (0.01 < aData.string5 && aData.string5 < 0.05 ){
												            $('td:eq(8)', nRow).css('background-color', '#60bda0');
												        } else {
												            $('td:eq(8)', nRow).css('background-color', 'white');
												        }
												        if (0 < aData.string6 && aData.string6 < 0.01) {
												            $('td:eq(9)', nRow).css('background-color', '#adf7b2');
												        } else if (0.01 < aData.string6 && aData.string6 < 0.05 ){
												            $('td:eq(9)', nRow).css('background-color', '#60bda0');
												        } else {
												            $('td:eq(9)', nRow).css('background-color', 'white');
												        }
												    },
									                fnInitComplete: function() {
									                    var tr = $('#example tbody td.details-control').eq(0).closest('tr');
													    var row = table.row(tr);
													    var id = Math.random() * 1.0E20;
												        var arrvalue = row.data().string1;
												        // Open this row
												        row.child(format(arrvalue, id)).show();
												        tr.addClass('shown');
												        $('#' + id).DataTable({
												            bLengthChange: false,
												            searching: false,
											            	aLengthMenu: [5]
												        });
									                },
												    scrollX: true,
												    ajax: {
												        url:"../email/result",
												        type:"post",
												        data: {
												            "userId": '${RequestParameters.userId}',
												            "method": '${RequestParameters.method}',
												            "hg": '${RequestParameters.hg}',
												            "min": '${RequestParameters.min}',
												            "max": '${RequestParameters.max}',
												            "intPvalue": '${RequestParameters.intPvalue}'
												        }
												    },
												    columns:[
												        {
												            "className": 'details-control',
												            "data": null,
												            "defaultContent": ''
												        },
												        {
												        	"data": function(data) {
												                return '<a href =\'/Greap/analysis/set_detail?grch='+"${RequestParameters.hg}"+'&datatype='+"${RequestParameters.datatype}"+'&subclass='+"${RequestParameters.subset}"+'&set='+data.string1+'&count='+data.string7+'&port=0\' target="_blank">' + data.string1 + '</a>' ;
												            },
												        },
												        {
												        	"data": function(data) {
												                return "${RequestParameters.datatype}"
												            },
												        },
												        {
												        	"data": function(data) {
												                return "${RequestParameters.subset}"
												            },
												        },
												        {"data": 'string8'},
												        {"data": 'string7'},
												        {
												        	"data": function(data) {
												                if(data.string4.split("e-").length == 1) {
												                    return Number(data.string4).toExponential(2);
												                } else {
												                    return Math.floor(data.string4.split("e-")[0] * 100) / 100 +'e-'+data.string4.split("e-")[1];
												                }
												            }
												        },
												        {
												        	"data": function(data) {
												                return Math.floor(-Math.log10(Number(data.string4))* 100) / 100;
												            },
												        },
												        {
												        	"data": function(data) {
												                if(data.string5.split("e-").length == 1) {
												                    return Number(data.string5).toExponential(2);
												                }else{
												                    return Math.floor(data.string5.split("e-")[0] * 100) / 100 +'e-'+data.string5.split("e-")[1];
												                }
												            },
												        },
												        {
												        	"data": function(data) {
												                if(data.string6.split("e-").length == 1) {
												                    return Number(data.string6).toExponential(2);
												                }else{
												                    return Math.floor(data.string6.split("e-")[0] * 100) / 100 +'e-'+data.string6.split("e-")[1];
												                }
												            },
												        }
												    ]
												});
												// Add event listener for opening and closing details
												$('#example tbody').on('click', 'td.details-control', function () {
												    var tr = $(this).closest('tr');
												    var row = table.row(tr);
												    if (row.child.isShown()) {
												        // This row is already open - close it
												        row.child.hide();
												        tr.removeClass('shown');
												    } else {
												        var id = Math.random() * 1.0E20;
												        var arrvalue = row.data().string1;
												        // Open this row
												        row.child(format(arrvalue, id)).show();
												        tr.addClass('shown');
												        $('#' + id).DataTable({
												            bLengthChange: false,
												            searching: false,
												            aLengthMenu: [5]
												        });
												    }
												});
											</script>
	                                        <#else>
	                                        <tr>
	                                           
	                                            <th>Set</th>
	                                            <th>Class</th>
	                                            <th>Sub class</th>
	                                            <th>Count <i data-toggle="tooltip" data-placement="left"  class="glyphicon glyphicon-question-sign" title="Number of intersections"></i></th>
	                                            <th>Num <i data-toggle="tooltip" data-placement="left"  class="glyphicon glyphicon-question-sign" title="The number of set"></i></th>
	                                            <th>P_value <i data-toggle="tooltip" data-placement="left"  class="glyphicon glyphicon-question-sign" title="Hypergeometric P value."></i></th>
	                                            <th>pValueLog <i data-toggle="tooltip" data-placement="left"  class="glyphicon glyphicon-question-sign" title="The -log10 of hypergeometric P value."></i></th>
	                                            <th>oddsRatio</th>	                                            
	                                        </tr>
		                                        <script>
												    $('#example').DataTable({
												        oLanguage: {
												            sLoadingRecords: '<img src="${base.contextPath}/static/img/loading.gif" style="width: 350px;">'
												        },
												        dom: '<"pull-left"B>ft<"pull-left"i>p',
													    buttons: [
													               {
													                    text: '<span class="glyphicon glyphicon-save"><font>Export</font></span>',
													                    extend: 'csv'
													                }],
													    aLengthMenu : [5],
													    "order": [[ 5, "asc" ]],
														fnRowCallback: function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
										                    if (aData.string1 > 2 ){
										                        $('td:eq(5)', nRow).css('background-color', '#adf7b2');
										                    }else if (1.30 < aData.string1 && aData.string1 < 2 ){
										                        $('td:eq(5)', nRow).css('background-color', '#60bda0');
										                    }else{
										                        $('td:eq(5)', nRow).css('background-color', 'white');
										                    }
										                },
														scrollX: true,
												        ajax: {
												            url:"../email/result",
												            type:"post",
													        data: {
													            "userId": '${RequestParameters.userId}',
													            "method": '${RequestParameters.method}',
													            "hg": '${RequestParameters.hg}',
													            "min": '${RequestParameters.min}',
													            "max": '${RequestParameters.max}',
													            "intPvalue": '${RequestParameters.intPvalue}'
													        }
												        },
												        columns:[
												            {"data": function(data){
															         return '<a href =\'/Greap/analysis/set_detail?grch='+"${RequestParameters.hg}"+'&datatype='+"${RequestParameters.datatype}"+'&subclass='+"${RequestParameters.subset}"+'&set='+data.string7.replace(".bed","")+'&count='+parseInt(data.string8)+'&port=0'+'&overlap_num='+data.string9+'\' target="_blank">' + data.string7.replace(".bed","") + '</a>' ;
															      },
															},
												            {"data": function(data){
															         return "${RequestParameters.datatype}"
															      },
															},
												            {"data": function(data){
															         return "${RequestParameters.subset}"
															      },
															},
															{"data": 'string3'},
															{"data": function(data){
															         return parseInt(data.string8) ;
															      },
															},
															{"data": function(data){
															         return Math.pow(10, -data.string1).toExponential(2);
															      },
															},
												            {"data": function(data){
															         return Math.round(data.string1*100)/100;
															      },
															},
												            {"data": function(data){
															         return Math.round(data.string2*100)/100;
															      },
															}
												        ],
												    });
												</script>    
	                                        </#if>
                                        </thead>
                                        <tbody></tbody>
                                        
                                    </table>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            

<script>
$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

function openWin(url){
	window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=500px,top=200px,width=1000px, height=500px");
}
function openOne(url){
	window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=300px,top=200px,width=1000px, height=500px");
}
function openTwo(url){
	window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=300px,top=200px,width=1000px, height=500px");
}
</script>
</body>
</html>