<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>GREAP</title>
    <link rel="icon" type="image/x-icon" href="${base.contextPath}/static/img/favicon.ico"/>
    <link  rel="stylesheet" href="${base.contextPath}/static/css/bootstrap.min.css">
    <script src="${base.contextPath}/static/js/jquery.min.js"></script>
    <script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${base.contextPath}/static/css/header.css">
    <link rel="stylesheet" href="${base.contextPath}/static/css/footer.css"/>


    <link rel="stylesheet" href="${base.contextPath}/static/css/style.css" >
    <link rel="stylesheet" href="${base.contextPath}/static/css/nav.css" >
    <link href="${base.contextPath}/static/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
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


        .black_overlay{
            display: none;
            position: fixed;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: gray;
            z-index:1001;
            -moz-opacity: 0.8;
            opacity:.80;
            filter: alpha(opacity=88);
        }
        .white {

            display: none;
            width: 500px;
            height: 450px;
            background-color: white;
            position: fixed;
            z-index:1005;
            margin-left:23%;
            margin-top:-20px;
            text-align:center;
            font-size:20px;
            font-family:榛戜綋;
            color:#84072a;

            z-index:1005;

        }
        .white_content {

            position: absolute;
            top: 14%;
            right:  0%;
            width: 100%;
            height: 80%;
            text-align:center;
            font-size:20px;
            font-family:榛戜綋;
            color:#84072a;
            background-color: white;
            z-index:1004;
            overflow: auto;
            overflow-y:auto;
        }
        .cl{
            position: absolute;

            top:0%;
            right: 0%;
            z-index: 1005;
            color: #84072a;
        }
        .kl{
            position: absolute;
            font-size:37px;
            top:3%;
            right: 45%;
            z-index: 1005;
            color: #5183f2;
        }
        hr {
            border: 0;
            border-bottom: 2px solid black;
            z-index: 1007;
        }
        .qp{
            position:absolute;
            margin-top: 100px;
            margin-left:100px;
            width: 500px;

            height: 500px;
        }

        #accordion .panel-heading{
            padding: 0;
            border: none;
            border-radius: 0;
            position: relative;
        }
        #accordion .panel-title {
            display: block;
            padding: 15px 20px;
            margin: 0;
            background: #034786;
            font-size: 18px;
            font-weight: 700;
            letter-spacing: 1px;
            color: #fff;
            border-radius: 0;
            position: relative;
        }
        #accordion .panel-title .collapsed{
            background: #034786;}

        #accordion .panel-body{
            border-left: 3px solid #034786;
            border-top: none;
            background: #fff;
            font-size: 15px;
            color: #1c2336;
            line-height: 27px;
            position: relative;
        }
        #accordion .panel-body:before{
            content: "";
            height: 3px;
            width: 100%;
            background: #034786;
            position: absolute;
            bottom: 0;
            left: 0;
        }
        /* 		.btn-warning {
                color: #fff;
                background-color:#337ab7;
                border-color:#204d74;
                font-size: 10px;
                }
                .btn-warning:hover {
                color: #fff;
                background-color:#286090;
                border-color:#204d74;
                } */


        .hr1{ height:1px;border:none;border-top:3px solid #346191;}
    </style>
</head>
<body id="body">

<!--==========================
  Header
============================-->/
<#include "nav/navbar.ftl" />

<div class="row" style="width:99%;">
    <div class="col-md-2 " style="margin-top: 100px;max-height: 800px;overflow-y: auto;padding-right: 0;">
       <nav class="daohang" role="navigation" id="clickMe">
       		<#if oloselect == "olo1">
	        <ul class="daohang__list" style="margin-left: -30px;">
	        	<#if hmm_class??>
	            <li>
	                <input id="group-1" type="checkbox" hidden />
	                <a id="click1" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=ChromHMM&subset=${hmm_class}" target="iframe_olo"> ChromHMM</a>
	                
	            </li>			    
				</#if>
				<#if tf_class??>
	            <li>
	                <input id="group-2" type="checkbox" hidden />
	                <a id="click2" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=TF&subset=${tf_class}" target="iframe_olo"> TF</a>
	                
	            </li>
	            </#if>
	            <#if tcof_class??>
	            <li>
	                <input id="group-3" type="checkbox" hidden />
	                <a id="click3" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=TcoF&subset=${tcof_class}" target="iframe_olo"> TcoF</a>
	                
	            </li>
	            </#if>
	            <#if histone_class??>
	            <li>
	                <input id="group-4" type="checkbox" hidden />
	                <a id="click4" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=Histone&subset=${histone_class}" target="iframe_olo"> Histone</a>
	                
	            </li>
	            </#if>
	            <#if atac_class??>
	            <li>
	                <input id="group-5" type="checkbox" hidden />
	                <a id="click5" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=ATAC&subset=${atac_class}" target="iframe_olo"> ATAC</a>
	              
	            </li>
	            </#if>
	            <#if enhancer_class??>
	            <li>
	                <input id="group-6" type="checkbox" hidden />
	                
	                <a id="click6" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=Enhancer&subset=${enhancer_class}" target="iframe_olo"> Enhancer</a>
	               
	            </li>
	            </#if>
	            <#if se_class??>
	            <li>
	                <input id="group-7" type="checkbox" hidden />
	                <a id="click7" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=Super_Enhancer&subset=${se_class}" target="iframe_olo">Super Enhancer</a>
	                
	            </li>
	            </#if>
	            <#if snp_class??>
	            <li>
	                <input id="group-8" type="checkbox" hidden />
	                <a id="click8" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=snp_class&subset=${enhancer_class}" target="iframe_olo">SNP</a>
	                
	            </li>
	            </#if>
	            <#if methylation_class??>
	            <li>
	                <input id="group-9" type="checkbox" hidden />
	                <a id="click9" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=Methylation&subset=${methylation_class}" target="iframe_olo">Methylation</a>
	                
	            </li>
	            </#if>
	            <#if lnc_class??>
	            <li>
	                <input id="group-10" type="checkbox" hidden />
	                <a id="click10" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=LncRNA&subset=${lnc_class}" target="iframe_olo">LncRNA</a>
	                
	            </li>
	            </#if>
	            <#if m_class??>
	            <li>
	                <input id="group-11" type="checkbox" hidden />
	                <a id="click11" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=mRNA&subset=${m_class}" target="iframe_olo">mRNA</a>
	                
	            </li>
				</#if>
				<#if eqtl_class??>
	            <li>
	                <input id="group-12" type="checkbox" hidden />
	                <a id="click11" class="clicka" href="${base.contextPath}/analysis/ologram?datatype=eQTL&subset=${eQTL_class}" target="iframe_olo">eQTL</a>
	                
	            </li>
				</#if>
	        </ul>
	        <#else>
	        <ul class="daohang__list" style="margin-left: -30px;">
	            <li>
	                <a href="${base.contextPath}/analysis/ologram?datatype=no&subset=${m_class}" id="click12" class="clicka" target="iframe_olo"> Custom bed file</a>
	            </li>
	        </ul>    
	        </#if>
	    </nav>
      </div>
      <div class="col-md-10">
          
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-top: 100px;">
            <div class="panel panel-default" >
                <div class="panel-heading" role="tab" id="1" >
                    <h4 class="panel-title" >
                        <span class="glyphicon glyphicon-list"></span> <font id="tname"> </font>
                    </h4>
                </div>
                <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                    <div class="panel-body" style="overflow-x:auto;">
                      	<iframe src=""  frameborder="no"  name="iframe_olo" id="iframe_olo" style="height:1024px;width: 100%;"></iframe>
						
                    </div>
                </div>
            </div>
        </div>
      </div>
</div>
<#include "nav/footer.ftl" />
<script type="text/javascript">
	
	$(".clicka")[0].click();
	
	$("#tname").text($(".clicka")[0].innerText);
	
	$("#click1").click(function(){
	  $("#tname").text("ChromHMM");
	});
	
	$("#click2").click(function(){
	  $("#tname").text("TF");
	});
	
	$("#click3").click(function(){
	  $("#tname").text("TcoF");
	});
	
	$("#click4").click(function(){
	  $("#tname").text("Histone");
	});
	
	$("#click5").click(function(){
	  $("#tname").text("ATAC");
	});
	$("#click6").click(function(){
	  $("#tname").text("Enhancer");
	});
	$("#click7").click(function(){
	  $("#tname").text("Super Enhancer");
	});
	$("#click8").click(function(){
	  $("#tname").text("SNP");
	});
	$("#click9").click(function(){
	  $("#tname").text("Methylation");
	});
	$("#click10").click(function(){
	  $("#tname").text("LncRNA");
	});
	$("#click11").click(function(){
	  $("#tname").text("mRNA");
	});
	$("#click12").click(function(){
	  $("#tname").text("Custom");
	});
	$("#click13").click(function(){
	  $("#tname").text("eQTL");
	});
	
/***************
    var c=0 ,a=1 ,b=1;
    for(i=0;i<document.getElementsByClassName("group-list").length;i++){
        if(document.getElementsByClassName("group-list")[i].getElementsByTagName("li").length == 0){
            c++;
        }else{
            break;
        }
    }

    document.getElementById("group-"+ (c+1)).click();
    var list = document.getElementsByClassName("group-list");
    document.getElementsByClassName("group-list")[c].getElementsByTagName("a")[0].addEventListener('click',function(){
        if(a%2==0){
            document.getElementsByClassName("group-list")[c].getElementsByTagName("a")[0].setAttribute('style', 'background: #23313f;color: #fff');
        }else{
            document.getElementsByClassName("group-list")[c].getElementsByTagName("a")[0].setAttribute('style', 'background-color: #3f5d79;color: #09fbd2');
        }
        a++;
    });
    document.getElementsByClassName("group-list")[c].getElementsByTagName("a")[0].click();

    for (i = 0; i < list.length; i++) {
        if(i>0){
            for (j = 0; j < document.getElementsByClassName("group-list")[i].getElementsByTagName("a").length; j++) {
                document.getElementsByClassName("group-list")[i].getElementsByTagName("a")[j].addEventListener('click',function(){
                    if(a%2==0){
                        document.getElementsByClassName("group-list")[c].getElementsByTagName("a")[0].setAttribute('style', 'background: #23313f;color: #fff');
                        a=1;
                    }
                });
            }
        }else{
            for (j = 1; j < document.getElementsByClassName("group-list")[i].getElementsByTagName("a").length; j++) {
                document.getElementsByClassName("group-list")[i].getElementsByTagName("a")[j].addEventListener('click',function(){
                    if(a%2==0){
                        document.getElementsByClassName("group-list")[c].getElementsByTagName("a")[0].setAttribute('style', 'background: #23313f;color: #fff');
                        a=1;
                    }
                });
            }
        }

    }
   ***********/
</script>

</body>
</html>