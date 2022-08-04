<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link  rel="stylesheet" href="${base.contextPath}/static/css/bootstrap.min.css">
    <script src="${base.contextPath}/static/js/jquery.min.js"></script>
    <script src="${base.contextPath}/static/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${base.contextPath}/static/css/kuang.css" >
    <style>
		.change-active{
			background-color:#2a5880;
			color: white;
			width: 100px;
			text-transform: capitalize;border: 2px solid white;
			
		}
		.change-visitied{
		    color: black !important;
			background: orange !important;
		}
		.btn:hover{color:orange;}
	</style>
</head>
<body id="body">

<!--==========================
  Header
============================-->
    <div class="row" style="margin-right: 0;margin-left: 0;">
        <div class="col-lg-10 col-md-offset-1">
              <div class="row" style="margin-right: 0;"><!---鎵嬮鐞村紑濮�--->
                <div class="col-md-12 col-md-offset">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">	<!---鎵嬮鐞�/panel-group/寮�濮�--->
                        <div class="panel panel-default" ><!---鎵嬮鐞�/tissue-based/start--->
                            <div class="panel-heading" role="tab" id="1" >
                                <h4 class="panel-title" >
                                    <span class="glyphicon glyphicon-list"></span> <font id="tname"> The regulatory info: <p style="display: inline;color: orange;">${browser_region}</p> <a href="javascript:history.go(-1)"><img src="${base.contextPath}/static/img/return.png" title="Back to previous " style="width: 20px;"></a></font>
                                </h4>
                            </div>
                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                                <div class="panel-body">
                                     
                                     <div class="row" style="margin-right: 0;margin-left: 2px;margin-bottom: 10px;">
                                        <a type="button" href="${base.contextPath}/view/analysis_position_tf" target="iframe1" class="btn btn-general btn-white mr-2 change-active change-visitied" style="width: 100px;text-transform: capitalize;border: 2px solid white;background-color:#2a5880;margin-left: 12px;">TF <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Transcription factor" style="width: 12px;"></a>
                                        <a type="button" href="${base.contextPath}/view/analysis_position_tcof" target="iframe1" class="btn btn-general btn-white mr-2 change-active" >TcoF <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Transcription cofactor" style="width: 12px;"></a>
                                        <a type="button" href="${base.contextPath}/view/analysis_position_histone" target="iframe1" class="btn btn-general btn-white mr-2 change-active" >Histone <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Histone modification" style="width: 12px;"></a>
									    <a type="button" href="${base.contextPath}/view/analysis_position_atac" target="iframe1" class="btn btn-general btn-white mr-2 change-active" >ATAC <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Chromatin accessibility regions (ATAC-seq)" style="width: 12px;"></a>
									    <a type="button" href="${base.contextPath}/view/analysis_position_enhancer" target="iframe1" class="btn btn-general btn-white mr-2 change-active" >Enhancer</a>
									    <a type="button" href="${base.contextPath}/view/analysis_position_se" target="iframe1" class="btn btn-general btn-white mr-2 change-active" style="width: 150px;">Super Enhancer</a>
									    <a type="button" href="${base.contextPath}/view/analysis_position_snp" target="iframe1" class="btn btn-general btn-white mr-2 change-active" >SNP</a>
									    <a type="button" href="${base.contextPath}/view/analysis_position_eqtl" target="iframe1" class="btn btn-general btn-white mr-2 change-active" style="text-transform: full-size-kana;">eQTL</a>
									    <a type="button" href="${base.contextPath}/view/analysis_position_methylation" target="iframe1" class="btn btn-general btn-white mr-2 change-active" style="width: 150px;">Methylation <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Methylation sites of 450 karray" style="width: 12px;"></a>		  		 
									    		  		 
									  </div>
									 
                                     <iframe src="${base.contextPath}/view/analysis_position_tf" class="col-lg-12" frameborder="no"  name="iframe1" style="height:800px;"></iframe>
                                     
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


<script>
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})

	$(".change-active").click(function(){
		$(".change-active").removeClass("change-visitied");
		$(this).addClass("change-visitied");
	})
</script>
<script>
$(document).ready(function(){
    var table = $('#example').DataTable( {

    } );
    })
</script>
</body>
</html>