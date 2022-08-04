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


    <!-- DataTable buttons -->
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/buttons.dataTables.min.css"/>

    <script type="text/javascript" src="${base.contextPath}/static/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${base.contextPath}/static/js/dataTables.buttons.min.js"></script>
    <!--  buttons 导出功能-->
    <script src="${base.contextPath}/static/js/buttons.html5.min.js"></script>

</head>


<!--==========================
  Header
============================-->
                <p style="color: black;margin-bottom: 5px;font-size: 16px;">* The ${datatype} region</p>
                <#if datatype == "TF">
                 <table id="example" class="table table-bordered" style="width:100%">
	                <thead>
	                    <tr>
	                        <th></th>
	                        <th hidden>chr</th>
	                        <th hidden>start</th>
	                        <th hidden>end</th>
	                        <th>Input region</th>
	                        <th>TF region</th>
	                        <th>Peak name</th>
	                        <th>fold change</th>
	                        <th>-log10pvalue</th>
	                        <th>-log10qvalue</th>
	                    </tr>
	                </thead>
	                <tbody>
	                   <#list list as data>
						<tr>
						    <td><input type="radio" name="position" value=""  onclick="position('${data.string1}','${data.string2}','${data.string3}')"></td>
							<td id="chr" hidden>${data['string1']}</td>
							<td id="start" hidden>${data['string2']}</td>
							<td id="end" hidden>${data['string3']}</td>
							<td>${data['string1']}:${data['string2']}-${data['string3']}</td>
							<td>${data['string4']}:${data['string5']}-${data['string6']}</td>
							<td>${data['string7']}</td>
							<td>${data['string10']}</td>
							<td>${data['string11']}</td>
							<td>${data['string12']}</td>
						</tr>
	                   </#list>
	                </tbody>
	                
	            </table>
				</#if>   
				<#if datatype == "RiskSNP">
                 <table id="example" class="table table-bordered" style="width:100%">
	                <thead>
	                    <tr>
	                        <th></th>
	                        <th hidden>chr</th>
	                        <th hidden>start</th>
	                        <th hidden>end</th>
	                        <th>SNP position</th>
	                        <th>SNP id</th>
	                        <th>Phenotype</th>
	                        <th>Phenotype escription</th>
	                        <th>P_value</th>
	                        <th>Pubmed id</th>
	                    </tr>
	                </thead>
	                <tbody>
	                   <#list list as data>
						<tr>
						    
						    <td><input type="radio" name="position" value=""  onclick="position('${data.string4}','${data.string5}','${data.string6}')"></td>
							<td id="chr" hidden>${data['string4']}</td>
							<td id="start" hidden>${data['string5']}</td>
							<td id="end" hidden>${data['string6']}</td>
							<td>${data['string4']}:${data['string5']}</td>
							<td>${data['string7']}</td>
							<td>${data['string8']}</td>
							<td>${data['string9']}</td>
							<td>${data['string10']}</td>
							<td>${data['string11']}</td>
						</tr>
	                    </#list>
	                </tbody>
	                
	            </table>
				</#if> 
				
				<#if datatype == "ChromHMM">
                 <table id="example" class="table table-bordered" style="width:100%">
	                <thead>
	                    <tr>
	                        <th></th>
	                        <th hidden>chr</th>
	                        <th hidden>start</th>
	                        <th hidden>end</th>
	                        <th>Input region</th>
	                        <th>Chr</th>
	                        <th>Start</th>
	                        <th>End</th>
	                        <th>State</th>
	                        <th>Sample ID</th>
	                        <th>Visualization</th>
	                    </tr>
	                </thead>
	                <tbody>
	                   <#list list as data>
	                    
						<tr>
						    
						    <td><input type="radio" name="position" value="${data_index}"  onclick="position('${data.string1}','${data.string2}','${data.string3}')"></td>
							<td id="chr" hidden>${data['string1']}</td>
							<td id="start" hidden>${data['string2']}</td>
							<td id="end" hidden>${data['string3']}</td>
							<td id="colorchange" >${data['string1']}:${data['string2']}-${data['string3']}</td>
							<td>${data['string4']}</td>
							<td>${data['string5']}</td>
							<td>${data['string6']}</td>
							<td>${data['string7']}</td>
							<td>${data['string8']}</td>
							<td><a href="http://www.licpathway.net/sedb_gb/?loc=${data['string1']}:${data['string2']}-${data['string3']}"  target="_blank">JBrowse</a></td>
						</tr>
	                    </#list>
	                </tbody>
	                
	            </table>
				</#if> 
				<#if datatype == "Super_Enhancer">
                 <table id="example" class="table table-bordered" style="width:100%">
	                <thead>
	                    <tr>
	                        <th></th>
	                        <th>Chr</th>
	                        <th>Start</th>
	                        <th>End</th>
	                        <th>SE id</th>
	                        <th>Sample id</th>
	                    </tr>
	                </thead>
	                <tbody>
	                   <#list list as data>
						<tr>
						    
						    <td><input type="radio" name="position" value=""  onclick="position('${data.string1}','${data.string2}','${data.string3}')"></td>
							<td id="chr">${data['string1']}</td>
							<td id="start">${data['string2']}</td>
							<td id="end">${data['string3']}</td>
							<td><a href="http://www.licpathway.net/sedb/search/search_se.php?cell_id=${data['string5']?replace("Sample","SE")}&se_id=${data['string4']}" target="_blank">${data['string4']}</a></td>
							<td><a href="http://www.licpathway.net/sedb/search/analysis_gene_cell.php?cell_id=${data['string5']?replace("Sample","SE")}" target="_blank">${data['string5']}</a></td>
						</tr>
	                    </#list>
	                </tbody>
	                
	            </table>
				</#if> 
				                     
                             
<script>
$(document).ready(function(){
    var table = $('#example').DataTable( {
        dom: '<"pull-left"B>ft<"pull-left"i>p',
	    buttons: [
	               {
	                    text: '<span class="glyphicon glyphicon-save"><font>Export</font></span>',
	                    extend: 'csv'
	                }],
	    aLengthMenu : [5],
	    "scrollX" : true,
    } );
    })
</script>
<script>
	$(document).ready(function() {
		$("input[name='position']").eq(0).attr("checked","checked");
		$.ajax({
			url:'../position',
			data:{"chr":$("#chr").eq(0).text(),"start":$("#start").eq(0).text(),"end":$("#end").eq(0).text()},
			dataType:"json",
			type:"GET",
			success:function(){
			},
			})
		 })
</script>
<script>
	function position(string1,string2,string3) {
	$.ajax({
		url:'../position',
		data:{"chr":string1,"start":string2,"end":string3},
		dataType:"json",
		type:"GET",
		success:function(){
		},
		})
		}
</script>
</html>