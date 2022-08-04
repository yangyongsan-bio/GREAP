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
    <!--  buttons 瀵煎嚭鍔熻兘-->
    <script src="${base.contextPath}/static/js/buttons.html5.min.js"></script>

    <style>
	    tr {text-align: center;}
	    th {text-align: center;}
		.change-active{
			background-color:#2a5880;
			color: white;
			width: 100px;
			text-transform: capitalize;border: 2px solid white;
		}
		.change-visitied{
		    color: black;
			background: orange !important;
		}
	</style>
</head>

<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />
     <div class="row" style="margin-right: 0px;margin-top: 100px;">
        <div class="col-lg-10 col-lg-offset-1">
             
			<iframe src="http://39.98.139.1/GREAP_gb/" class="col-lg-12" frameborder="no"  name="iframe" style="height:600px;"></iframe>
						     
        </div>
     </div>

<#include "nav/footer.ftl" />


</body>
</html>