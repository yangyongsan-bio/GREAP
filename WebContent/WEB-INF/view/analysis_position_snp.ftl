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
            <form action="../analysis/position/SNP"  method="post" target="histoneiframe">
            <label>Select SNP class</label>
            <select class="form-control" onchange="form.submit()" name="dataclass" style="width: 30%;margin-bottom: 10px;display: inline;">
					<option value="risk_10kb" >risk_10kb</option>
					<option value="risk_15kb" >risk_15kb</option>
					<option value="risk_20kb" >risk_20kb</option>
					
            </select>
            <a href="/Greap/view/help#SNP" target="_blank"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
            </form>
            
            <iframe src="${base.contextPath}/analysis/position/SNP?dataclass=risk_10kb" frameborder="no"  name="histoneiframe" style="width:100%;height:650px;"></iframe>    
                   

</html>