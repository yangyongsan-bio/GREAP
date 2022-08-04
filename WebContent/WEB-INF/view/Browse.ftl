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
			text-transform: capitalize;border: 2px solid white;
		}
		
		.panel {
			box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
		}
	</style>
   
   
</head>

<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />

<div class="row" style="margin-top: 100px;margin-right: 0px">
	<div style="margin-right: 61px; float: right;margin-bottom: 16px;">
		<a type="button" href="${base.contextPath}/analysis/browse?grch=hg19&datatype=${RequestParameters.datatype}&dataset=${RequestParameters.dataset}" class="btn btn-general btn-white mr-2">hg19</a><a type="button" href="${base.contextPath}/analysis/browse?grch=hg38&datatype=${RequestParameters.datatype}&dataset=${RequestParameters.dataset}" class="btn btn-general btn-white mr-2">hg38</a>
	</div>
	<div class="col-md-12">
	  <div class="col-lg-3 col-md-3 col-sm-12" style="width: 20%;">
	  	
        <div class="panel text-dark pb-1 mb-3">
            <div class="panel-heading" style="font-size: 20px;font-weight: 600;">Class<span style="float: right;"></span></div>
            <input type="text" class="form-control" id="class-search-input" placeholder="Search" aria-label="Amount (to the nearest dollar)" style="border-radius: 0;"/>
            <ul class="list-group list-group-flush" id="class" style="overflow-y:auto;max-height: 200px;">
		       <#list list1 as data>
            	<li>
				    <#if RequestParameters.datatype == "">
            		<a class="list-group-item <#list Activedatatype as data> ${data} </#list>" href="${base.contextPath}/analysis/browse?grch=${RequestParameters.grch}&datatype=${data['dataclass']}&dataset=${browse_dataset}">
						${data['dataclass']}
						<span class="badge" style="float: right;">${data['number']}</span>
					</a>
					<#else>
					<a class="list-group-item <#list Activedatatype as data> ${data} </#list>" href="${base.contextPath}/analysis/browse?grch=${RequestParameters.grch}&datatype=&dataset=${browse_dataset}">
						${data['dataclass']}
						<span class="badge" style="float: right;">${data['number']}</span>
					</a>
					</#if>
            	</li>
		       </#list>
            </ul>
        </div>
        <div class="panel text-dark pb-1 mb-3">
            <div class="panel-heading" style="font-size: 20px;font-weight: 600;">SubClass<span style="float: right;"></span></div>
            <input type="text" class="form-control" id="subclass-search-input" placeholder="Search" aria-label="Amount (to the nearest dollar)" style="border-radius: 0;"/>
            <ul class="list-group list-group-flush" id="subclass" style="overflow-y:auto;max-height: 200px;">
		       <#list list2 as data>
				<li>
				    <#if RequestParameters.dataset == "">
					<a class="list-group-item <#list Activedataset as data> ${data} </#list>" href="${base.contextPath}/analysis/browse?grch=${RequestParameters.grch}&datatype=${browse_datatype}&dataset=${data['subclass']}">
						${data['subclass']}
						<span class="badge" style="float: right;">${data['number']}</span>
					</a>
				    <#else>
			        <a class="list-group-item <#list Activedataset as data> ${data} </#list>" href="${base.contextPath}/analysis/browse?grch=${RequestParameters.grch}&datatype=${browse_datatype}&dataset=">
						${data['subclass']}
						<span class="badge" style="float: right;">${data['number']}</span>
					</a>
				    </#if>
				</li>
		       </#list>
            </ul>
        </div>
	  
	  <!--
	     <table id="example" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
			 <thead>
		        <tr>
		            <th>Class</th>
		        </tr>
		    </thead>
			 <tbody>
		       <#list list1 as data>
				<tr>
				    <#if RequestParameters.datatype == "">
						<td><a class="list-group-item <#list Activedatatype as data> ${data} </#list>" href="${base.contextPath}/analysis/browse?grch=${RequestParameters.grch}&datatype=${data['dataclass']}&dataset=${browse_dataset}">
						${data['dataclass']} <span class="badge" style="float: right;">${data['number']}</span> </a></td>
					<#else>
						<td><a class="list-group-item <#list Activedatatype as data> ${data} </#list>" href="${base.contextPath}/analysis/browse?grch=${RequestParameters.grch}&datatype=&dataset=${browse_dataset}">
						${data['dataclass']} <span class="badge" style="float: right;">${data['number']}</span> </a></td>
					</#if>
				</tr>
		       </#list>
		    </tbody>
		</table>
		<table id="example1" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
			 <thead>
		        <tr>
		            <th>SubClass</th>
		        </tr>
		    </thead>
			 <tbody>
		       <#list list2 as data>
				<tr>
				    <#if RequestParameters.dataset == "">
						<td><a class="list-group-item <#list Activedataset as data> ${data} </#list>" href="${base.contextPath}/analysis/browse?grch=${RequestParameters.grch}&datatype=${browse_datatype}&dataset=${data['subclass']}">
						${data['subclass']} <span class="badge" style="float: right;">${data['number']}</span> </a></td>
				    <#else>
				        <td><a class="list-group-item <#list Activedataset as data> ${data} </#list>" href="${base.contextPath}/analysis/browse?grch=${RequestParameters.grch}&datatype=${browse_datatype}&dataset=">
						${data['subclass']} <span class="badge" style="float: right;">${data['number']}</span> </a></td>
				    </#if>
				</tr>
		       </#list>
		    </tbody>
		</table>
        -->
        
      </div>
      

	 <div class="col-lg-9 col-md-9 col-sm-12" style="width: 80%;">
	   <iframe src="${base.contextPath}/view/BrowseList" class="col-lg-12" frameborder="no"  name="iframe" style="height:700px;"></iframe>
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

<script>
  $(function(){
    $('#class-search-input').on('keyup',function(){
      var $str=$('#class-search-input').val();
      $('#class li').each(function(){
        if($(this).text().toUpperCase().indexOf($str.toUpperCase())>=0){
          $(this).show();
        }
        else{
          $(this).hide();
        }
      });
    });

    $('#subclass-search-input').on('keyup',function(){
      var $str=$('#subclass-search-input').val();
      $('#subclass li').each(function(){
        if($(this).text().toUpperCase().indexOf($str.toUpperCase())>=0){
          $(this).show();
        }
        else{
          $(this).hide();
        }
      });
    });
  })
</script>

</body>
</html>