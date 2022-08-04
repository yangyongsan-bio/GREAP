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

    <style>
	    tr {text-align: center;}
	    th {text-align: center;}
		.change-active{
			background-color:#ab0e0e;
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
		<table id="example" class="table table-bordered" style="width:100%" >
		    <thead>
		        <tr>
		            <th>Class</th>
		            <th>Sub Class</th>
		            <th>Download</th>
		        </tr>
		    </thead>
		    <tbody>
		       <#list list as data>
				<tr>
					<td>${data['datatype']}</td>
					<td>${data['dataset']}</td>
					<td><a href="http://www.liclab.net:8080/Greap_download/${data['datatype']}/${data['dataset']}.bed"><img src="/Greap/static/img/download.png" style="width: 15px;margin-top: -5px;"></a></td>
				</tr>
		       </#list>
		    </tbody>
		</table>

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
	    aLengthMenu : [10],
		scrollX: true,
      
    });
</script>    

</body>
</html>