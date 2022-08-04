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
            <form action="../analysis/position/Histone"  method="post" target="histoneiframe">
            <label>Select histone class</label>
            <select class="form-control" onchange="form.submit()" name="dataclass" style="width: 30%;margin-bottom: 10px;display: inline;">
                    <option value="H2AFZ" >H2AFZ</option>
					<option value="H2AK5ac" >H2AK5ac</option>
					<option value="H2AK9ac" >H2AK9ac</option>
					<option value="H2BK120ac" >H2BK120ac</option>
					<option value="H2BK12ac" >H2BK12ac</option>
					<option value="H2BK15ac" >H2BK15ac</option>
					<option value="H2BK20ac" >H2BK20ac</option>
					<option value="H2BK5ac" >H2BK5ac</option>
					<option value="H3F3A" >H3F3A</option>
					<option value="H3K14ac" selected>H3K14ac</option>
					<option value="H3K18ac" >H3K18ac</option>
					<option value="H3K23ac" >H3K23ac</option>
					<option value="H3K23me2" >H3K23me2</option>
					<option value="H3K27ac" >H3K27ac</option>
					<option value="H3K27me3" >H3K27me3</option>
					<option value="H3K36me3" >H3K36me3</option>
					<option value="H3K4ac" >H3K4ac</option>
					<option value="H3K4me1" >H3K4me1</option>
					<option value="H3K4me2" >H3K4me2</option>
					<option value="H3K4me3" >H3K4me3</option>
					<option value="H3K56ac" >H3K56ac</option>
					<option value="H3K79me1" >H3K79me1</option>
					<option value="H3K79me2" >H3K79me2</option>
					<option value="H3K9ac" >H3K9ac</option>
					<option value="H3K9me1" >H3K9me1</option>
					<option value="H3K9me2" >H3K9me2</option>
					<option value="H3K9me3" >H3K9me3</option>
					<option value="H3T11ph" >H3T11ph</option>
					<option value="H4K12ac" >H4K12ac</option>
					<option value="H4K20me1" >H4K20me1</option>
					<option value="H4K5ac" >H4K5ac</option>
					<option value="H4K8ac" >H4K8ac</option>
					<option value="H4K91ac" >H4K91ac</option>
            </select>
            <a href="/Greap/view/help#Histone" target="_blank"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
            </form>
            
            <iframe src="${base.contextPath}/analysis/position/Histone?dataclass=H3K14ac" frameborder="no"  name="histoneiframe" style="width:100%;height:650px;"></iframe>    
                   

</html>