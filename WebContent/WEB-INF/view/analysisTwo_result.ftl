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
    <link rel="stylesheet" href="${base.contextPath}/static/css/kuang.css" >
    <style>
    pre{
        margin-bottom: 0;
        border: 0;
        border-radius: 0px;
        font-size: 15px;
    }
    .bottom tr td{
    	line-height:5px;
    }
    </style>
</head>
<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />
   <div class="row" style="margin-right: 0;margin-top: 100px;"><!---æé£ç´å¼å§--->
        
        <div class="col-md-10" style="padding-left: 172px;">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">	<!---æé£ç´/panel-group/å¼å§--->
                <div class="panel panel-default" ><!---æé£ç´/tissue-based/start--->
                    <div class="panel-heading" role="tab" id="1" >
                        <h4 class="panel-title" >
                            <span class="glyphicon glyphicon-list"></span> <font id="tname">Detailed primer reports</font>
                        </h4>
                    </div>
                    <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                        <div class="panel-body">
							 <table class="table table-striped" style="font-size: 16px;">
							  <thead>
							  	<tr>
							  		<td>OLIGO</td>
							  		<td>Start</td>
							  		<td>Length</td>
							  		<td>tm</td>
							  		<td>gc%</td>
							  		<td>any</td>
							  		<td>3'</td>
							  		<td>Sequence</td>
							  	</tr>
							  </thead>
							  <tbody>
							  	<tr>
							  		<td>${string1?split(',')[0]} ${string2?split(',')[1]}</td>
							  		<td>${string1?split(',')[2]}</td>
							  		<td>${string1?split(',')[3]}</td>
							  		<td>${string1?split(',')[4]}</td>
							  		<td>${string1?split(',')[5]}</td>
							  		<td>${string1?split(',')[6]}</td>
							  		<td>${string1?split(',')[7]}</td>
							  		<td>${string1?split(',')[8]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
							  	</tr>
							  	<tr>
							  		<td>${string2?split(',')[0]} ${string2?split(',')[1]}</td>
							  		<td>${string2?split(',')[2]}</td>
							  		<td>${string2?split(',')[3]}</td>
							  		<td>${string2?split(',')[4]}</td>
							  		<td>${string2?split(',')[5]}</td>
							  		<td>${string2?split(',')[6]}</td>
							  		<td>${string2?split(',')[7]}</td>
							  		<td>${string2?split(',')[8]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
							  	</tr>
							  </tbody>
							</table>
<!-- 序列展示 -->							
							<pre style="padding-bottom: 0;">${string3}</pre>
							<pre style="padding-bottom: 0;">${string4}</pre>
							<pre style="padding-bottom: 0;">${string5}</pre>
							<#list aaa  as bbb>
							<pre style="padding-bottom: 0;">${bbb}</pre>
							</#list>
							<br>
<!-- 用户传递参数为：2 -->   

							<#if num == 2>
							<table class="table table-striped" style="font-size: 16px;margin-bottom:0px;">
								  	<tr>
								  		<td></td>
								  		<td>ADDITIONAL OLIGOS</td>
								  		<td>Start</td>
								  		<td>Length</td>
								  		<td>tm</td>
								  		<td>gc%</td>
								  		<td>any</td>
								  		<td>3'</td>
								  		<td>Sequence</td>
								  	</tr>
								  	<tr>
								  		<td>1</td>
								  		<td>${string50?split(',')[2]} ${string50?split(',')[3]}</td>
										 <td>${string50?split(',')[4]}</td>
										 <td>${string50?split(',')[5]}</td>
										 <td>${string50?split(',')[6]}</td>
										 <td>${string50?split(',')[7]}</td>
										 <td>${string50?split(',')[8]}</td>
										 <td>${string50?split(',')[9]}</td>
										 <td>${string50?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string51?split(',')[1]} ${string51?split(',')[2]}</td>
										 <td>${string51?split(',')[3]}</td>
										 <td>${string51?split(',')[4]}</td>
										 <td>${string51?split(',')[5]}</td>
										 <td>${string51?split(',')[6]}</td>
										 <td>${string51?split(',')[7]}</td>
										 <td>${string51?split(',')[8]}</td>
										 <td>${string51?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="9" style="padding-left:45px;">${string52}</td>
								  	</tr>
							</table>
							
							</#if>
<!-- 用户传递参数为：3 -->
							<#if num == 3>
							<table class="table table-striped" style="font-size: 16px;margin-bottom:0px;">
									
								  	<tr>
								  		<td></td>
								  		<td>OLIGO</td>
								  		<td>start</td>
								  		<td>len</td>
								  		<td>tm</td>
								  		<td>gc%</td>
								  		<td>any</td>
								  		<td>3'</td>
								  		<td>seq</td>
								  	</tr>
								  	<tr>
								  		<td>1</td>
								  		<td>${string50?split(',')[2]} ${string50?split(',')[3]}</td>
										 <td>${string50?split(',')[4]}</td>
										 <td>${string50?split(',')[5]}</td>
										 <td>${string50?split(',')[6]}</td>
										 <td>${string50?split(',')[7]}</td>
										 <td>${string50?split(',')[8]}</td>
										 <td>${string50?split(',')[9]}</td>
										 <td>${string50?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string51?split(',')[1]} ${string51?split(',')[2]}</td>
										 <td>${string51?split(',')[3]}</td>
										 <td>${string51?split(',')[4]}</td>
										 <td>${string51?split(',')[5]}</td>
										 <td>${string51?split(',')[6]}</td>
										 <td>${string51?split(',')[7]}</td>
										 <td>${string51?split(',')[8]}</td>
										 <td>${string51?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string52}</td>
								  	</tr>
								  	<tr>
								  		<td>2</td>
								  		<td>${string53?split(',')[2]} ${string53?split(',')[3]}</td>
										 <td>${string53?split(',')[4]}</td>
										 <td>${string53?split(',')[5]}</td>
										 <td>${string53?split(',')[6]}</td>
										 <td>${string53?split(',')[7]}</td>
										 <td>${string53?split(',')[8]}</td>
										 <td>${string53?split(',')[9]}</td>
										 <td>${string53?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string54?split(',')[1]} ${string54?split(',')[2]}</td>
										 <td>${string54?split(',')[3]}</td>
										 <td>${string54?split(',')[4]}</td>
										 <td>${string54?split(',')[5]}</td>
										 <td>${string54?split(',')[6]}</td>
										 <td>${string54?split(',')[7]}</td>
										 <td>${string54?split(',')[8]}</td>
										 <td>${string54?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string55}</td>
								  	</tr>
							</table>
							
							</#if>
<!-- 用户传递参数为：4 -->
							<#if num == 4>
							<table class="table table-striped" style="font-size: 16px;margin-bottom:0px;">
									
								  	<tr>
								  		<td></td>
								  		<td>OLIGO</td>
								  		<td>start</td>
								  		<td>len</td>
								  		<td>tm</td>
								  		<td>gc%</td>
								  		<td>any</td>
								  		<td>3'</td>
								  		<td>seq</td>
								  	</tr>
								  	<tr>
								  		<td>1</td>
								  		<td>${string50?split(',')[2]} ${string50?split(',')[3]}</td>
										 <td>${string50?split(',')[4]}</td>
										 <td>${string50?split(',')[5]}</td>
										 <td>${string50?split(',')[6]}</td>
										 <td>${string50?split(',')[7]}</td>
										 <td>${string50?split(',')[8]}</td>
										 <td>${string50?split(',')[9]}</td>
										 <td>${string50?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string51?split(',')[1]} ${string51?split(',')[2]}</td>
										 <td>${string51?split(',')[3]}</td>
										 <td>${string51?split(',')[4]}</td>
										 <td>${string51?split(',')[5]}</td>
										 <td>${string51?split(',')[6]}</td>
										 <td>${string51?split(',')[7]}</td>
										 <td>${string51?split(',')[8]}</td>
										 <td>${string51?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string52}</td>
								  	</tr>
								  	<tr>
								  		<td>2</td>
								  		<td>${string53?split(',')[2]} ${string53?split(',')[3]}</td>
										 <td>${string53?split(',')[4]}</td>
										 <td>${string53?split(',')[5]}</td>
										 <td>${string53?split(',')[6]}</td>
										 <td>${string53?split(',')[7]}</td>
										 <td>${string53?split(',')[8]}</td>
										 <td>${string53?split(',')[9]}</td>
										 <td>${string53?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string54?split(',')[1]} ${string54?split(',')[2]}</td>
										 <td>${string54?split(',')[	3]}</td>
										 <td>${string54?split(',')[4]}</td>
										 <td>${string54?split(',')[5]}</td>
										 <td>${string54?split(',')[6]}</td>
										 <td>${string54?split(',')[7]}</td>
										 <td>${string54?split(',')[8]}</td>
										 <td>${string54?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string55}</td>
								  	</tr>
								  	<tr>
								  		<td>3</td>
								  		<td>${string56?split(',')[2]} ${string56?split(',')[3]}</td>
										 <td>${string56?split(',')[4]}</td>
										 <td>${string56?split(',')[5]}</td>
										 <td>${string56?split(',')[6]}</td>
										 <td>${string56?split(',')[7]}</td>
										 <td>${string56?split(',')[8]}</td>
										 <td>${string56?split(',')[9]}</td>
										 <td>${string56?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string57?split(',')[1]} ${string57?split(',')[2]}</td>
										 <td>${string57?split(',')[3]}</td>
										 <td>${string57?split(',')[4]}</td>
										 <td>${string57?split(',')[5]}</td>
										 <td>${string57?split(',')[6]}</td>
										 <td>${string57?split(',')[7]}</td>
										 <td>${string57?split(',')[8]}</td>
										 <td>${string57?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string58}</td>
								  	</tr>
							</table>
							
							</#if>
<!-- 用户传递参数为：5 -->
							<#if num == 5>
							<table class="table table-striped" style="font-size: 16px;margin-bottom:0px;">
									
								  	<tr>
								  		<td></td>
								  		<td>OLIGO</td>
								  		<td>start</td>
								  		<td>len</td>
								  		<td>tm</td>
								  		<td>gc%</td>
								  		<td>any</td>
								  		<td>3'</td>
								  		<td>seq</td>
								  	</tr>
								  	<tr>
								  		<td>1</td>
								  		<td>${string50?split(',')[2]} ${string50?split(',')[3]}</td>
										 <td>${string50?split(',')[4]}</td>
										 <td>${string50?split(',')[5]}</td>
										 <td>${string50?split(',')[6]}</td>
										 <td>${string50?split(',')[7]}</td>
										 <td>${string50?split(',')[8]}</td>
										 <td>${string50?split(',')[9]}</td>
										 <td>${string50?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string51?split(',')[1]} ${string51?split(',')[2]}</td>
										 <td>${string51?split(',')[3]}</td>
										 <td>${string51?split(',')[4]}</td>
										 <td>${string51?split(',')[5]}</td>
										 <td>${string51?split(',')[6]}</td>
										 <td>${string51?split(',')[7]}</td>
										 <td>${string51?split(',')[8]}</td>
										 <td>${string51?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string52}</td>
								  	</tr>
								  	<tr>
								  		<td>2</td>
								  		<td>${string53?split(',')[2]} ${string53?split(',')[3]}</td>
										 <td>${string53?split(',')[4]}</td>
										 <td>${string53?split(',')[5]}</td>
										 <td>${string53?split(',')[6]}</td>
										 <td>${string53?split(',')[7]}</td>
										 <td>${string53?split(',')[8]}</td>
										 <td>${string53?split(',')[9]}</td>
										 <td>${string53?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string54?split(',')[1]} ${string54?split(',')[2]}</td>
										 <td>${string54?split(',')[3]}</td>
										 <td>${string54?split(',')[4]}</td>
										 <td>${string54?split(',')[5]}</td>
										 <td>${string54?split(',')[6]}</td>
										 <td>${string54?split(',')[7]}</td>
										 <td>${string54?split(',')[8]}</td>
										 <td>${string54?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string55}</td>
								  	</tr>
								  	<tr>
								  		<td>3</td>
								  		<td>${string56?split(',')[2]} ${string56?split(',')[3]}</td>
										 <td>${string56?split(',')[4]}</td>
										 <td>${string56?split(',')[5]}</td>
										 <td>${string56?split(',')[6]}</td>
										 <td>${string56?split(',')[7]}</td>
										 <td>${string56?split(',')[8]}</td>
										 <td>${string56?split(',')[9]}</td>
										 <td>${string56?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string57?split(',')[1]} ${string57?split(',')[2]}</td>
										 <td>${string57?split(',')[3]}</td>
										 <td>${string57?split(',')[4]}</td>
										 <td>${string57?split(',')[5]}</td>
										 <td>${string57?split(',')[6]}</td>
										 <td>${string57?split(',')[7]}</td>
										 <td>${string57?split(',')[8]}</td>
										 <td>${string57?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string58}</td>
								  	</tr>
								  	<tr>
								  		<td>4</td>
								  		<td>${string59?split(',')[2]} ${string59?split(',')[3]}</td>
										 <td>${string59?split(',')[4]}</td>
										 <td>${string59?split(',')[5]}</td>
										 <td>${string59?split(',')[6]}</td>
										 <td>${string59?split(',')[7]}</td>
										 <td>${string59?split(',')[8]}</td>
										 <td>${string59?split(',')[9]}</td>
										 <td>${string59?split(',')[10]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr>
								  		<td></td>
								  		<td>${string60?split(',')[1]} ${string60?split(',')[2]}</td>
										 <td>${string60?split(',')[3]}</td>
										 <td>${string60?split(',')[4]}</td>
										 <td>${string60?split(',')[5]}</td>
										 <td>${string60?split(',')[6]}</td>
										 <td>${string60?split(',')[7]}</td>
										 <td>${string60?split(',')[8]}</td>
										 <td>${string60?split(',')[9]} &nbsp;&nbsp;&nbsp;<a href="https://www.ncbi.nlm.nih.gov/tools/primer-blast/index.cgi">blast</a></td>
								  	</tr>
								  	<tr style="text-align:left;">
								  		<td></td>
								  		<td colspan="8" style="padding-left:45px;">${string61}</td>
								  	</tr>
							</table>
						    
							</#if>
							<div style="text-align: center;">
							  <p style="margin: 15px;font-size: 20px;">*If you need the complete result, please click to <a href="${base.contextPath}/data/test.txt" download="result.txt">download</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
        
    </div>

    <div class="row" style="margin-right: 0;margin-left: 0;">
        <div class="col-lg-10 col-md-offset-1">
            
        </div>
    </div>



<#include "nav/footer.ftl" />


</body>
</html>