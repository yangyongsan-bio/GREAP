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
    <!--  buttons 导出功能-->
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


    </style>
</head>


<!--==========================
  Header
============================-->
                
                <form action="../analysis/position/TcoF" target="iframe_tcof" method="post">
				   <label>Select TcoF class</label>
                   <select class="form-control" onchange="form.submit()" name="dataclass" style="width: 30%;margin-bottom: 10px;display: inline;">
		                <option value="TcoF-class-1"  selected>TcoF-class-1</option>
		                <option value="TcoF-class-2" >TcoF-class-2</option>
		                <option value="TcoF-class-3" >TcoF-class-3</option>
		                <option value="TcoF-class-HC" >TcoF-class-HC</option>
		                <option value="Undefined" >Undefined</option>
		            </select>
		            <a href="/Greap/view/help#TcoF" target="_blank"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		         </form>
		          
		         <iframe src="${base.contextPath}/analysis/position/TcoF?dataclass=TcoF-class-1" frameborder="no"  name="iframe_tcof" style="height:650px;width: 100%;"></iframe>
                                


</html>