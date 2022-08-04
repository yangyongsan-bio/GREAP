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
			background-color:#2a5880;
			color: white;
			width: 100px;
			text-transform: capitalize;border: 2px solid white;
		}
		.change-visitied{
		    color: black;
			background: orange !important;
		}
		.btn.active, .btn:active {
			background-color: #007bff;
			color: #fff;
		}
		a[type="button"] {
			border: 1px #ccc solid;
			border-radius: 0;
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
             <div class="row"><!---手风琴开始--->
				<div style="margin-right: 15px; float: right;margin-bottom: 16px;">
					<a type="button" href="${base.contextPath}/analysis/browse?grch=hg19&datatype=${RequestParameters.datatype}&dataset=${RequestParameters.dataset}" class="btn btn-general btn-white mr-2 change-active">hg19</a><a type="button" href="${base.contextPath}/analysis/browse?grch=hg38&datatype=${RequestParameters.datatype}&dataset=${RequestParameters.dataset}" class="btn btn-general btn-white mr-2 change-active">hg38</a>
				</div>
                <div class="col-md-12 col-md-offset">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" >	<!---手风琴/panel-group/开始--->
                        <div class="panel panel-default" ><!---手风琴/tissue-based/start--->
                            <div class="panel-heading" role="tab" id="1" style="background-color: #034786;width: ;height: 50px;">
                                <h4 class="panel-title" style="margin-top: 5px;">
                                    <span class="glyphicon glyphicon-list" style="color: white;"></span> <font style="color: white;">Download</font>
                                </h4>
                            </div>
                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                                <div class="panel-body" style="overflow-x:auto;">
                                    <div class="row" style="margin-right: 0;margin-left: 2px;margin-bottom: 10px;">
						                <a type="button" href="${base.contextPath}/download?set=ChromHMM" target="iframe" class="btn btn-general btn-white mr-2 change-active change-visitied" style="width: 100px;text-transform: capitalize;border: 2px solid white;background-color:#2a5880;margin-left: 12px;">ChromHMM</a>
						                <a type="button" href="${base.contextPath}/download?set=TF" target="iframe" class="btn btn-general btn-white mr-2 change-active">TF</a>
						                <a type="button" href="${base.contextPath}/download?set=TcoF" target="iframe" class="btn btn-general btn-white mr-2 change-active" >TcoF</a>
						                <a type="button" href="${base.contextPath}/download?set=Histone" target="iframe" class="btn btn-general btn-white mr-2 change-active" >Histone</a>
									    <a type="button" href="${base.contextPath}/download?set=ATAC" target="iframe" class="btn btn-general btn-white mr-2 change-active" >ATAC</a>
									    <a type="button" href="${base.contextPath}/download?set=Enhancer" target="iframe" class="btn btn-general btn-white mr-2 change-active" >Enhancer</a>
									    <a type="button" href="${base.contextPath}/download?set=Super_Enhancer" target="iframe" class="btn btn-general btn-white mr-2 change-active" style="width: 150px;">Super_Enhancer</a> 		  		 
									    <a type="button" href="${base.contextPath}/download?set=SNP" target="iframe" class="btn btn-general btn-white mr-2 change-active" >SNP</a>
									    <a type="button" href="${base.contextPath}/download?set=Methylation" target="iframe" class="btn btn-general btn-white mr-2 change-active" >Methylation</a>	
									    <a type="button" href="${base.contextPath}/download?set=LncRNA" target="iframe" class="btn btn-general btn-white mr-2 change-active" >LncRNA</a>
									    <a type="button" href="${base.contextPath}/download?set=mRNA" target="iframe" class="btn btn-general btn-white mr-2 change-active" style="text-transform: inherit;">mRNA</a>		  		 
									 </div>
						             <iframe src="${base.contextPath}/download?set=ChromHMM" class="col-lg-12" frameborder="no"  name="iframe" style="height:600px;"></iframe>
						     
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
	$(".change-active").click(function(){
		$(".change-active").removeClass("change-visitied");
		$(this).addClass("change-visitied");
	})
</script>
  

</body>
</html>