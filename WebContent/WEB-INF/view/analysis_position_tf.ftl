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
               
                <form action="../analysis/position/TF" target="iframe_tf" method="post">
				   <label>Select TF class</label>
                   <select class="form-control" onchange="form.submit()" name="dataclass" style="width: 30%;margin-bottom: 10px;display: inline;">
		                <option value="alpha-Helices-exposed-by-beta-structures"  selected>alpha-Helices-exposed-by-beta-structures</option>
		                <option value="Basic-domains" >Basic-domains</option>
		                <option value="beta-Barrel-DNA-binding-domains" >beta-Barrel-DNA-binding-domains</option>
		                <option value="beta-Hairpin-exposed-by-an-alpha-beta-scaffold" >beta-Hairpin-exposed-by-an-alpha-beta-scaffold</option>
		                <option value="beta-Sheet-binding-to-DNA" >beta-Sheet-binding-to-DNA</option>
		                <option value="ENCODE-TF" >ENCODE-TF</option>
		                <option value="Helix-turn-helix-domains" >Helix-turn-helix-domains</option>
		                <option value="Immunoglobulin-fold" >Immunoglobulin-fold</option>
		                <option value="Other-all-alpha-helical-DNA-binding-domains" >Other-all-alpha-helical-DNA-binding-domains</option>
		                <option value="Undefined" >Undefined</option>
		                <option value="TcoF-TF" >TcoF-TF</option>
		                <option value="Yet-undefined-DNA-binding-domains" >Yet-undefined-DNA-binding-domains</option>
		                <option value="Zinc-coordinating-DNA-binding-domains" >Zinc-coordinating-DNA-binding-domains</option>
		            </select>
		            <a href="/Greap/view/help#TF" target="_blank"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		         </form>
		         <iframe src="${base.contextPath}/analysis/position/TF?dataclass=alpha-Helices-exposed-by-beta-structures"  frameborder="no" name="iframe_tf" style="height:650px;width: 100%;"></iframe>
                                


</html>