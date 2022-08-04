<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>GREAP</title>
    <link href="${base.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="${base.contextPath}/static/img/favicon.ico"/>
   
    <link rel="icon" type="image/x-icon" href="${base.contextPath}/static/img/favicon.ico"/>
    <link  rel="stylesheet" href="${base.contextPath}/static/css/bootstrap.min.css">
    <script src="${base.contextPath}/static/js/jquery.min.js"></script>
    <script src="${base.contextPath}/static/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${base.contextPath}/static/css/header.css">
    <link rel="stylesheet" href="${base.contextPath}/static/css/footer.css"/>
    <link href="${base.contextPath}/static/css/style.css" rel="stylesheet">
    
    <!-- DataTable buttons -->
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/buttons.dataTables.min.css"/>

    <script type="text/javascript" src="${base.contextPath}/static/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${base.contextPath}/static/js/dataTables.buttons.min.js"></script>
    <!--  buttons 导出功能-->
    <script src="${base.contextPath}/static/js/buttons.html5.min.js"></script>
    <style type="text/css">
        tr {text-align: center;}
	    th {text-align: center;}
    </style>
</head>

<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />
     <div class="row" style="margin-right: 0px;margin-top: 100px;">
        <div class="col-lg-10 col-lg-offset-1">
             <div class="row"><!---手风琴开始--->
                <div class="col-md-12 col-md-offset">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" >	<!---手风琴/panel-group/开始--->
                        <div class="panel panel-default" ><!---手风琴/tissue-based/start--->
                            <div class="panel-heading" role="tab" id="1" style="background-color: #034786;width: ;height: 50px;">
                                <h4 class="panel-title" style="margin-top: 5px;">
                                    <span class="glyphicon glyphicon-list" style="color: white;"></span> <font style="color: white;">Search Result</font>
                                </h4>
                            </div>
                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                                <div class="panel-body" style="overflow-x:auto;">
                                    
                                    <table id="example" class="table table-bordered" style="width:100%" >
						                <thead>
						                    <tr>
						                        <th>Set</th>
						                        <th>Class</th>
						                        <th>Sub Class</th>
						                        <th>Count</th>
						                    </tr>
						                </thead>
						                <tbody>
						                   <#list list as data>
											<tr>
												<td><a href="${base.contextPath}/analysis/set_detail?grch=${genome}&datatype=${data['dataclass']}&subclass=${data['subclass']}&set=${data['set']}&count=${data['num']}&port=1">${data['set']}</a></td>
												<td>${data['dataclass']}</td>
												<td>${data['subclass']}</td>
												<td>${data['num']}</td>
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
             
        </div>
     </div>

<#include "nav/footer.ftl" />


<script>
$(document).ready(function(){
    var table = $('#example').DataTable( {
        dom: '<"pull-left"B>ft<"pull-left"i>p',
	    buttons: [
           {
                text: '<span class="glyphicon glyphicon-save"><font>Export</font></span>',
                extend: 'csv'
            }
        ],
	    "scrollX" : true,
    } );
    })
</script>

</body>
</html>