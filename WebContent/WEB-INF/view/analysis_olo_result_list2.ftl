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
<body id="body" style="overflow: -moz-hidden-unscrollable;">
<!--==========================
  Header
============================-->
            <div class="row"><!---鎵嬮鐞村紑濮�--->
                <div class="col-md-12 col-md-offset">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-top: 100px;">	<!---鎵嬮鐞�/panel-group/寮�濮�--->
                        <div class="panel panel-default" ><!---鎵嬮鐞�/tissue-based/start--->
                            <div class="panel-heading" role="tab" id="1" >
                                <h4 class="panel-title" >
                                    <span class="glyphicon glyphicon-list"></span> <font id="tname"> ${class}</font>
                                </h4>
                            </div>
                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                                <div class="panel-body" style="overflow-x:auto;">
                                  
									<table id="example_olo" class="table table-bordered" style="width:100%">
                                        <thead>
                                        
	                                        <tr>
        										<th style="background-image:none">p</th>
	                                            <th>o</th>
	                                            <th>n</th>
	                                            <th>m class</th>
	                                            <th>l <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="Number of intersections"></i> </th>
	                                            <th>k <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="The number of set"></i></th>
	                                            <th>j <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="Hypergeometric P value."></i></th>
	                                            <th>i <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="The -log10 of hypergeometric P value."></i></th>
	                                            <th>h <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="Adjusted p value with FalseDiscovery Rate method."></i></th>
	                                            <th>g <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="Adjusted p value with FalseDiscovery Rate method."></i></th>
	                                            <th>f <i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="A Venn graph represents the relationship between the input and the reference set."></i></th>
	                                            <th>a</th>
	                                            <th>c</th>
	                                            <th>b</th>
	                                            <th>d</th>
	                                            <th>e</th>
	                                        </tr>
	                                     </thead>
	                                     <tbody>
	                                     	<#list list as data>
						                     <tr>
						                     	<td>${data['str1']}</td>
						                     	<td>${data['str2']}</td>
						                     	<td>${data['str3']}</td>
						                     	<td>${data['str4']}</td>
						                     	<td>${data['str5']}</td>
						                     	<td>${data['str6']}</td>
						                     	<td>${data['str7']}</td>
						                     	<td>${data['str8']}</td>
						                     	<td>${data['str9']}</td>
						                     	<td>${data['str10']}</td>
						                     	<td>${data['str11']}</td>
						                     	<td>${data['str12']}</td>
						                     	<td>${data['str13']}</td>
						                     	<td>${data['str14']}</td>
						                     	<td>${data['str15']}</td>
						                     	<td>${data['str16']}</td>
						                     </tr>
						                    
						                    </#list>
	                                     </tbody>
	                                  </table>
									
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            

<script>

$('#example_olo').DataTable({
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
	scrollX: true
    
});


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