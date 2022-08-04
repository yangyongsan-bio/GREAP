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
    
</head>
<body id="body">

<!--==========================
  Header
============================-->/
<#include "nav/navbar.ftl" />

<div class="row" style="width:99%;">
    <div class="col-md-2 " style="margin-top: 100px;max-height: 800px;overflow-y: auto;padding-right: 0;">
       <nav class="daohang" role="navigation" id="clickMe">
	        <ul class="daohang__list" style="margin-left: -30px;">
	            <li>
	                <input id="group-1" type="checkbox" hidden />
	                <label for="group-1"><span class="fa fa-angle-right"></span> ChromHMM</label>
	                <ul class="group-list">
	                    
	                    <#list list_hmm as data>
	                    <li><a href="${base.contextPath}/analysis/overlap?datatype=ChromHMM&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-2" type="checkbox" hidden />
	                <label for="group-2"><span class="fa fa-angle-right"></span> TF</label>
	                <ul class="group-list">
	                    <#list list_tf as data>
	                    <li><a href="${base.contextPath}/analysis/overlap?datatype=TF&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-3" type="checkbox" hidden />
	                <label for="group-3"><span class="fa fa-angle-right"></span> TcoF</label>
	                <ul class="group-list">
	                    
	                    <#list list_tcof as data>
	                    <li><a href="${base.contextPath}/analysis/overlap?datatype=TcoF&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-4" type="checkbox" hidden />
	                <label for="group-4"><span class="fa fa-angle-right"></span> Histone</label>
	                <ul class="group-list">
	                    
	                     <#list list_histone as data>
	                     <li><a href="${base.contextPath}/analysis/overlap?datatype=Histone&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-5" type="checkbox" hidden />
	                <label for="group-5"><span class="fa fa-angle-right"></span> ATAC</label>
	                <ul class="group-list">
	                    
	                    <#list list_atac as data>
	                   
	                       <li><a href="${base.contextPath}/analysis/overlap?datatype=ATAC&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                   
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-6" type="checkbox" hidden />
	                <label for="group-6"><span class="fa fa-angle-right"></span> Enhancer</label>
	                <ul class="group-list">
	                    
	                    <#list list_enhancer as data>
	                     <li><a href="${base.contextPath}/analysis/overlap?datatype=Enhancer&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-7" type="checkbox" hidden />
	                <label for="group-7"><span class="fa fa-angle-right"></span>Super Enhancer</label>
	                <ul class="group-list">
	                    
	                    <#list list_se as data>
	                     <li><a href="${base.contextPath}/analysis/overlap?datatype=Super_Enhancer&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-8" type="checkbox" hidden />
	                <label for="group-8"><span class="fa fa-angle-right"></span>SNP</label>
	                <ul class="group-list">
	                    
	                    <#list list_snp as data>
	                     <li><a href="${base.contextPath}/analysis/overlap?datatype=SNP&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-9" type="checkbox" hidden />
	                <label for="group-9"><span class="fa fa-angle-right"></span>eQTL</label>
	                <ul class="group-list">
	                    
	                    <#list list_eqtl as data>
	                     <li><a href="${base.contextPath}/analysis/overlap?datatype=eQTL&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-10" type="checkbox" hidden />
	                <label for="group-10"><span class="fa fa-angle-right"></span>Methylation</label>
	                <ul class="group-list">
	                    
	                    <#list list_methylation as data>
	                    <li><a href="${base.contextPath}/analysis/overlap?datatype=Methylation&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	            <li>
	                <input id="group-11" type="checkbox" hidden />
	                <label for="group-11"><span class="fa fa-angle-right"></span>LncRNA</label>
	                <ul class="group-list">
	                    
	                    <#list list_lnc as data>
	                     <li><a href="${base.contextPath}/analysis/overlap?datatype=LncRNA&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                </ul>
	            </li>
	            
	            <li>
	                <input id="group-12" type="checkbox" hidden />
	                <label for="group-12"><span class="fa fa-angle-right"></span>mRNA</label>
	                <ul class="group-list">
	                    
	                    <#list list_m as data>
	                     <li><a href="${base.contextPath}/analysis/overlap?datatype=mRNA&subset=${data['list_change']}"  target="iframe">&nbsp;&nbsp;&nbsp;&nbsp;${data['list_change']}</a></li>
	                    </#list>
	                    
	                </ul>
	            </li>
	
	        </ul>
	    </nav>
      </div>
      <div class="col-md-10">
          <iframe src=""  frameborder="no"  name="iframe" id="iframe" style="height:1024px;width: 100%;"></iframe>
      </div>
</div>
<#include "nav/footer.ftl" />

<script>
	let judge_email = '${judge_email}'
	if (judge_email === '1') {
		$.post('http://47.242.171.205:8046/greap_email/email/send/' + '${userId}');
	}
</script>

<script type="text/javascript">
    let c = 0;
    let array = [];
    for(i=0;i<document.getElementsByClassName("group-list").length;i++){
        if (document.getElementsByClassName("group-list")[i].getElementsByTagName("li").length == 0){
            c++;
        } else {
        	array.push(c++);
        }
    }
    
	for(let i = 0; i < array.length; i++) {
	    $("label[for='group-" + (array[i]+1) + "']").click();
	}
	
	$(".group-list li a").eq(0).attr("style", "background-color: #3f5d79;color: #09fbd2;");
	
	$(".group-list li a").click(function () {
		$(this).attr("style", "background-color: #3f5d79;color: #09fbd2;");
		$(this).parent("li").siblings().children("a").attr("style", "background: #23313f;color: #FFFFFF;");
		$(this).parent("li").parents(".group-list").parents("li").siblings().children(".group-list").children("li").children("a").attr("style", "background: #23313f;color: #FFFFFF;");
	});
    
    document.getElementsByClassName("group-list")[array[0]].getElementsByTagName("a")[0].click();

</script>

</body>
</html>