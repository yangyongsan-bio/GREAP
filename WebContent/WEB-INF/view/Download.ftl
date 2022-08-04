<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>GREAP</title>
    <link href="${base.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="${base.contextPath}/static/img/favicon.ico"/>
   
    <link rel="stylesheet" href="${base.contextPath}/static/css/header.css">
    <link rel="stylesheet" href="${base.contextPath}/static/css/footer.css"/>
    <link rel="stylesheet" href="${base.contextPath}/static/css/bootstrap-multiselect.css" type="text/css"/>
    <link href="${base.contextPath}/static/css/style.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" media="screen" href="https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css">
    <link href="${base.contextPath}/static/css/index.css" rel="stylesheet">
    
    <!-- DataTable buttons -->
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/buttons.dataTables.min.css"/>
   	<style>
		#example_filter {
			float: left;
			padding-top: 4px;
		}
		#example_filter label input{
			border-radius: 4px;
			width: 130px;
		}
		#example1_filter {
			float: left;
			padding-top: 4px;
		}
		#example1_filter label input{
			border-radius: 4px;
			width: 130px;
			
		}
		.dataTables_wrapper .dataTables_paginate .paginate_button{
			padding:2px 8px !important;
		}
		.btn.active, .btn:active {
			background-color: #007bff;
			color: #fff;
		}
		a[type="button"] {
			border: 1px #ccc solid;
			border-radius: 0;
			text-transform: initial;
		}
		.change-active{
			background-color: #007bff;
			color: #fff;
			text-transform: capitalize;
			border: 2px solid white;
		}
	</style>
   
   
</head>

<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />

<div class="row" style="width: 90%;margin: 100px auto;">
	<div style="margin-right: 61px; float: right;margin-bottom: 16px;">
		<a type="button" href="${base.contextPath}/analysis/download?grch=hg19" class="btn btn-general btn-white mr-2">hg19</a><a type="button" href="${base.contextPath}/analysis/download?grch=hg38" class="btn btn-general btn-white mr-2">hg38</a>
	</div>
	<div class="col-md-12">
	
	 <div class="col-lg-12 col-md-12 col-sm-12" style="width: 100%;">
	   <iframe src="${base.contextPath}/view/DownloadList" class="col-lg-12" frameborder="no"  name="iframe" style="height:1400px;"></iframe>
	 </div>
	 
	  
   </div>
</div>
<#include "nav/footer.ftl" />


<script src="${base.contextPath}/static/js/jquery.min.js"></script>
<script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${base.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base.contextPath}/static/js/dataTables.buttons.min.js"></script>
<script src="${base.contextPath}/static/js/buttons.html5.min.js"></script>
<script>
    $('#example').DataTable({
	    aLengthMenu : [4],
	    "bSort" : false,
	    "bLengthChange" : false,
	    "bInfo" : false,
	    "pagingType": "simple", 
		dom: "irtfp",
		fnPreDrawCallback: function() {
			$("#example_filter label input").attr("placeholder", "Search Class");
		},
		language: {
			"sSearch": "",
		},
    });       
           
    $('#example1').DataTable({
	    aLengthMenu : [4],
	    "bSort" : false,
	    "bLengthChange" : false,
	    "bInfo" : false,
	    "pagingType": "simple", 
		dom: "irtfp",
		fnPreDrawCallback: function() {
			$("#example1_filter label input").attr("placeholder", "Search SubClass");
		},
		language: {
			"sSearch": "",
		},
      
    });
    
	$('a[type="button"]').click(function(){
		$(".change-active").removeClass("change-active");
		$(this).addClass("change-active");
	})
    if ('${RequestParameters.grch}' === "hg19") {
		$(".change-active").removeClass("change-active");
		$('a[type="button"]').eq(0).addClass("change-active");
    }
    if ('${RequestParameters.grch}' === "hg38") {
		$(".change-active").removeClass("change-active");
		$('a[type="button"]').eq(1).addClass("change-active");
    }
    
</script> 


</body>
</html>