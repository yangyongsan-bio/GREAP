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

    <!-- DataTable buttons -->
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/buttons.dataTables.min.css"/>

    <script type="text/javascript" src="${base.contextPath}/static/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${base.contextPath}/static/js/dataTables.buttons.min.js"></script>
    <script src="${base.contextPath}/static/js/buttons.html5.min.js"></script>
    
    <link rel="stylesheet" href="${base.contextPath}/static/css/style.css" >
    <link rel="stylesheet" href="${base.contextPath}/static/css/kuang.css" >
    
</head>
<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />
    <form id="tableform" method="post" target="iframe">
    </form>
    <form id="chartform" method="post" target="iframechart">
    </form>
   <div class="row" style="margin-right: 0;margin-left: 0;margin-top: 100px;">
        <div class="col-lg-10 col-md-offset-1">
              <div class="row" style="margin-right: 0;"><!---鎵嬮鐞村紑濮�--->
                <div class="col-md-12 col-md-offset">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">	<!---鎵嬮鐞�/panel-group/寮�濮�--->
                        <div class="panel panel-default" ><!---鎵嬮鐞�/tissue-based/start--->
                            <div class="panel-heading" role="tab" id="1" >
                                <h4 class="panel-title" >
                                    <span class="glyphicon glyphicon-list"></span> <font id="tname"> Set overview</font>
                                </h4>
                            </div>
                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                                <div class="panel-body">
                                     <div class="col-lg-5">  
										<table class="table table-hover" style="table-layout: fixed;">
											<tbody>
											<tr>
												<td><strong>Set name:</strong></td>
												<td id="set">${set}</td>
											</tr>
											<tr>
												<td><strong>Reference genome:</strong></td>
												<td id="set">${genome}</td>
											</tr>
											<tr>
												<td><strong>Class:</strong></td>
												<td id="datatype">${datatype}</td>
											</tr>
											<tr>
												<td><strong>Sub Class:</strong></td>
												<td>${subclass}</td>
											</tr>
											<tr>
												<td width="45%"><strong>Region number of the set:</strong></td>
												<td>${count}</td>
											</tr>
							                <tr>
							                    <td width="40%"><strong>
							                    <#if datatype=="SNP">
							                    SNP related diseases or traits:
							                    <#else>
							                    The meaning of the set name:
							                    </#if>
							                    </strong></td>
							                    
							                    <#if datatype=="TF">
							                    <td>Cell name_TF name</td>
							                    
							                    <#elseif datatype=="TcoF">
							                    <td>Cell name_TcoF name</td>
							                    
							                    <#elseif datatype=="Histone">
							                    <td>Experiment target_Cell name</td>
							                    
							                    <#elseif datatype=="ChromHMM" || datatype=="ATAC" || datatype=="Super_Enhancer" || datatype=="Enhancer">
							                    <td>Sample id_sample type_sample name</td>
						
							                    <#elseif datatype=="SNP">
							                    <td>${SNP_disease}</td>
							                   
							                    <#elseif datatype=="eQTL">
							                    <td>Sample name</td>
							                    
							                    <#elseif datatype=="Methylation">
							                    <td>Data type_sample name</td>
							                    
							                    <#elseif datatype=="LncRNA" && subclass?contains("Disease")>
							                    <td>Disease name</td>
							                    
							                    <#elseif datatype=="LncRNA" && subclass?contains("Drug")>
							                    <td>Drug name</td>
							                    
							                    <#elseif datatype=="LncRNA" && subclass?contains("Exosome")>
							                    <td>Exosome</td>
							                    
							                    <#elseif datatype=="LncRNA" && subclass?contains("SmORF")>
							                    <td>LncRNA type</td>
							                    
							                    <#elseif datatype=="LncRNA" && subclass?contains("Subcellular_Location")>
							                    <td>Subcellular localization type</td>
							                    
							                    <#elseif datatype=="LncRNA" && subclass?contains("Cancer_Hallmark")>
							                    <td>Cancer hall marker</td>
							                    
							                    <#elseif datatype=="LncRNA" && subclass?contains("Cell_Marker")>
							                    <td>Cell name</td>
							                    
							                    <#elseif datatype=="mRNA" && subclass?contains("Cell_Marker")>
							                    <td>Cell name</td>
							                    
							                    <#elseif datatype=="mRNA" && subclass?contains("Goterm")>
							                    <td>Goterm</td>
							                    
							                    <#elseif datatype=="mRNA" && subclass?contains("GTEx")>
							                    <td>GTEx</td>
							                    </#if>
							                    
							                </tr>
							                </tbody>
							            </table>
                                     </div>
                                     <div class="col-lg-5">  
										<div id="main" style="width: 600px;height:250px;"></div>
                                     </div>
                                     
                                     <div class="col-lg-12 ">
						    			<table class="table table-hover jiaxin">
						                    <tbody>
						                    <tr>
						                        <td width="17%"><strong>Description of set and category :</strong></td>
												<#if datatype=="ChromHMM">
												<td height="20px"> ChromHMM is an automated computational system for learning chromatin states, and for characterizing their biological functions and correlations with large-scale functional datasets. From  <a href="${base.contextPath}/view/help#ChromHMM" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="TF" || datatype=="TcoF">
												<td> As a transcriptional regulator, protein activities of TFs and TcoFs can affect expression of downstream target genes by indirectly occupying DNA regulatory elements, such as promoter <a href="${base.contextPath}/view/help#TF" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="TcoF">
												<td> As a transcriptional regulator, protein activities of TFs and TcoFs can affect expression of downstream target genes by indirectly occupying DNA regulatory elements, such as promoter <a href="${base.contextPath}/view/help#TcoF" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="Histone">
												<td> Histone modification is one of the main means of epigenetic control in biological processes, which is usually located in the free amino terminal tails of four common histones (H2A,  <a href="${base.contextPath}/view/help#Histone" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="ATAC">
												<td> Accessible chromatin is a highly informative structural feature for identifying regulatory elements, which provides information about transcriptional activities and gene regulatory  <a href="${base.contextPath}/view/help#ATAC" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="Enhancer">
												<td> Enhancers and super enhancers (SEs) are classes of cis-regulatory elements that can increase gene transcription by forming loops in intergenic regions, introns and exons. SEs have a  <a href="${base.contextPath}/view/help#Enhancer" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="Super_Enhancer">
												<td> Enhancers and super enhancers (SEs) are classes of cis-regulatory elements that can increase gene transcription by forming loops in intergenic regions, introns and exons. SEs have a  <a href="${base.contextPath}/view/help#Super_Enhancer" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="SNP" || datatype=="eQTL" >
												<td> GWAS have provided a large amount of data of associating genetic variants with common phenotypes. We collected SNPs from the NHGRI GWAS Catalog. Human eQTL datasets were downloaded and <a href="${base.contextPath}/view/help#SNP" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="Methylation">
												<td> A total of 198,468,712 methylation sites were collected across 60 samples, including the 450 K array from ENCODE. We divided these sites into hypermethylation and hypomethylation  <a href="${base.contextPath}/view/help#Methylation" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="LncRNA">
												<td> We collected data for multiple categories of lncRNA sets including Disease, Drug, Subcellular Localization, Cancer Hallmark, SmORF, Exosome and Cell Marker from LncSEA, which is a <a href="${base.contextPath}/view/help#LncRNA" style="font-weight: 700">...more</a></td>
												<#elseif datatype=="mRNA">
												<td> We collected data for multiple categories of lncRNA sets including Disease, Drug, Subcellular Localization, Cancer Hallmark, SmORF, Exosome and Cell Marker from LncSEA, which is a <a href="${base.contextPath}/view/help#mRNA" style="font-weight: 700">...more</a></td>
												</#if>
						                    </tr>
						                    </tbody>
						                </table>
									 </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
       <div class="row" style="margin-right: 0;margin-left: 0;margin-top: 100px;">
        <div class="col-lg-10 col-md-offset-1">
              <div class="row" style="margin-right: 0;">
                <div class="col-md-12 col-md-offset">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">	
                        <div class="panel panel-default" >
                            <div class="panel-heading" role="tab" id="1" >
                                <h4 class="panel-title" >
                                    <#if port=="0"> 
                                    <span class="glyphicon glyphicon-list"></span> <font id="tname"> The overlaped regions info</font>
                                    <#else>
                                    <span class="glyphicon glyphicon-list"></span> <font id="tname"> The set regions info</font>
                                    </#if>
                                </h4>
                            </div>
                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                                <div class="panel-body">
                                     <div class="col-lg-12 ">
                                        <#if port=="0">
                                        <select class="form-control" name="chr" id="chart_chr" autocomplete="off" style="width: 7%;height: 30px;display: inline;float: right;margin-left: 10px;">
											<option value="chr1">chr1</option>
											<option value="chr2">chr2</option>
											<option value="chr3">chr3</option>
											<option value="chr4">chr4</option>
											<option value="chr5">chr5</option>
											<option value="chr6">chr6</option>
											<option value="chr7">chr7</option>
											<option value="chr8">chr8</option>
											<option value="chr9">chr9</option>
											<option value="chr10">chr10</option>
											<option value="chr11">chr11</option>
											<option value="chr12">chr12</option>
											<option value="chr13">chr13</option>
											<option value="chr14">chr14</option>
											<option value="chr15">chr15</option>
											<option value="chr16">chr16</option>
											<option value="chr17">chr17</option>
											<option value="chr18">chr18</option>
											<option value="chr19">chr19</option>
											<option value="chr20">chr20</option>
											<option value="chr21">chr21</option>
											<option value="chr22">chr22</option>
											<option value="chrX">chrX</option>
											<option value="chrY">chrY</option>
										</select>
										<p style="color: black;margin-bottom: 5px;font-size: 16px;display: inline;float: right;">* Overlap on  </p>
                                        <p style="color: black;margin-bottom: 5px;font-size: 16px;">* The detailed information of the overlapped regions</p>
                                        
                                        <iframe src="" class="col-lg-12" frameborder="no" name="iframechart" style="height:380px;padding-left: 0px;padding-right: 0px;"></iframe>  
                                        </#if>
                                        
						                <p style="color: black;margin-bottom: 5px;font-size: 16px;">* The ${datatype} region</p>
	<!--################################################ 分析页进入  ########################################### -->	
						                <#if RequestParameters.port == "0">
										<#if datatype == "ChromHMM">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
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
							                        <th>Biosample name</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                   <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string7}','${data.string8}','${data.string9}')"></td>
							                        <td id="chr" hidden>${data['string7']}</td>
													<td id="start" hidden>${data['string8']}</td>
													<td id="end" hidden>${data['string9']}</td>
													<td id="colorchange" >${data['string7']}:${data['string8']}-${data['string9']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td>${data['string4']}</td>
													<td>${data['string5']}</td>
													<#if data['string6']?contains("ZNF_Rpts")>
													<td>${data['string6']?replace("E001_ZNF_Rpts","")}</td>
													<#else>
													<td>${data['string6']?replace(data['string6']?split("_")[0],"")?replace(data['string6']?split("_")[1],"")?replace("__","")}</td>
													</#if>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string7']}:${data['string8']}-${data['string9']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string7']}:${data['string8']}-${data['string9']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if>
										<#if datatype == "TF">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>TF chr</th>
							                        <th>TF start</th>
							                        <th>TF end</th>
							                        <th>Cell name</th>
							                        <th>TF name</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                   <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string5}','${data.string6}','${data.string7}')"></td>
							                        <td id="chr" hidden>${data['string5']}</td>
													<td id="start" hidden>${data['string6']}</td>
													<td id="end" hidden>${data['string7']}</td>
													<td id="colorchange" >${data['string5']}:${data['string6']}-${data['string7']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td>${data['string4']?split("_")[0]}</td>
													<td>
													<a href="http://bio.licpathway.net/TFTGdb/browserDetail?symbol=${data['string4']?split("_")[1]}" target="_blank">${data['string4']?split("_")[1]}</a>
													</td>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string5']}:${data['string6']}-${data['string7']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string5']}:${data['string6']}-${data['string7']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "TcoF">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>TcoF chr</th>
							                        <th>TcoF start</th>
							                        <th>TcoF end</th>
							                        <th>Cell name</th>
							                        <th>TcoF name</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                   <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string5}','${data.string6}','${data.string7}')"></td>
							                        <td id="chr" hidden>${data['string5']}</td>
													<td id="start" hidden>${data['string6']}</td>
													<td id="end" hidden>${data['string7']}</td>
													<td id="colorchange" >${data['string5']}:${data['string6']}-${data['string7']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td>${data['string4']?split("_")[0]}</td>
													<td>
													    <a href="http://tcof.liclab.net/TcoFbase/Browse/Human/main_function.php?&Symbol=${data['string4']?split("_")[1]}" target="_blank">${data['string4']?split("_")[1]}</a>
													</td>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string5']}:${data['string6']}-${data['string7']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string5']}:${data['string6']}-${data['string7']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "Histone">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>Histone chr</th>
							                        <th>Histone start</th>
							                        <th>Histone end</th>
							                        <th>Cell name</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                   <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string5}','${data.string6}','${data.string7}')"></td>
							                        <td id="chr" hidden>${data['string5']}</td>
													<td id="start" hidden>${data['string6']}</td>
													<td id="end" hidden>${data['string7']}</td>
													<td id="colorchange" >${data['string5']}:${data['string6']}-${data['string7']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td>${data['string4']?split("_")[1]}</td>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string5']}:${data['string6']}-${data['string7']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string5']}:${data['string6']}-${data['string7']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "ATAC">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>ATAC chr</th>
							                        <th>ATAC start</th>
							                        <th>ATAC end</th>
							                        <th>Sample ID</th>
							                        <th>Biosample type</th>
							                        <th>Biosample name</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                   <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string5}','${data.string6}','${data.string7}')"></td>
							                        <td id="chr" hidden>${data['string5']}</td>
													<td id="start" hidden>${data['string6']}</td>
													<td id="end" hidden>${data['string7']}</td>
													<td id="colorchange" >${data['string5']}:${data['string6']}-${data['string7']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td>
													    <a href="http://www.licpathway.net/ATACdb/search/search_sample_result.php?get_sample_id=${data['string4']?split("_")[1]}" target="_blank">Sample_${data['string4']?split("_")[1]}</a>
													</td>
													<#if data['string4']?contains("Cell_Line")>
													<td>Cell_Line</td>
													<td>${data['string4']?replace(data['string4']?split("_")[1],"")?replace("Sample__","")?replace("Cell_Line_","")}</td>
													</#if>
													
													<#if data['string4']?contains("Stem_Cell")>
													<td>Stem_Line</td>
													<td>${data['string4']?replace(data['string4']?split("_")[1],"")?replace("Sample__","")?replace("Stem_Cell_","")}</td>
													</#if>
													
													<#if data['string4']?contains("Primary_Cell")>
													<td>Primary_Line</td>
													<td>${data['string4']?replace(data['string4']?split("_")[1],"")?replace("Sample__","")?replace("Primary_Cell_","")}</td>
													</#if>
													
													<#if data['string4']?contains("Tissue")>
													<td>Tissue</td>
													<td>${data['string4']?replace(data['string4']?split("_")[1],"")?replace("Sample__","")?replace("Tissue_","")}</td>
													</#if>
													
													<#if data['string4']?contains("Other")>
													<td>Other</td>
													<td>${data['Other']?replace(data['string4']?split("_")[1],"")?replace("Sample__","")?replace("Other_","")}</td>
													</#if>
													
													<#if data['string4']?contains("Induced_pluripotent_stem_cell_line")>
													<td>Induced_pluripotent_stem_cell_line</td>
													<td>${data['Induced_pluripotent_stem_cell_line']?replace(data['string4']?split("_")[1],"")?replace("Sample__","")?replace("Induced_pluripotent_stem_cell_line_","")}</td>
													</#if>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string5']}:${data['string6']}-${data['string7']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string5']}:${data['string6']}-${data['string7']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
													
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "Enhancer">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>Enhancer chr</th>
							                        <th>Enhancer start</th>
							                        <th>Enhancer end</th>
							                        <th>Enhancer ID</th>
													<#if set == "ENdb">
						                            <th>Set</th>
						                            <th>Tissue Type</th>
													<#else>
						                            <th>Sample ID</th>
						                            <th>Biosample type</th>
						                            <th>Biosample name</th>
													</#if>
													<th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                    <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string6}','${data.string7}','${data.string8}')"></td>
							                        
													<#if data['string5'] == "ENdb">
													<td id="chr" hidden>${data['string7']}</td>
													<td id="start" hidden>${data['string8']}</td>
													<td id="end" hidden>${data['string7']}</td>
													<td id="colorchange" >${data['string7']}:${data['string8']}-${data['string9']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td>
													<a href="http://www.licpathway.net/ENdb/search/Detail.php?Species=Human&amp;Enhancer_id=${data['string4']}" target="_blank">${data['string4']}</a>
													</td>
													<td>${data['string5']}</td>
													<td>${data['string6']}</td>
													<#else>
													
													<td id="chr" hidden>${data['string6']}</td>
													<td id="start" hidden>${data['string7']}</td>
													<td id="end" hidden>${data['string8']}</td>
													<td id="colorchange" >${data['string6']}:${data['string7']}-${data['string8']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td>${data['string4']}</td>
													
													<td>${data['string5']?split("_")[0]}_${data['string5']?split("_")[1]}_${data['string5']?split("_")[2]}</td>
													
													
													<#if data['string5']?contains("Cell_line")>
													<td>Cell_Line</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Cell_line_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Stem_cell")>
													<td>Stem_Line</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Stem_cell_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Primary_cell")>
													<td>Primary_Line</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Primary_cell_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Tissue")>
													<td>Tissue</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Tissue_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Other")>
													<td>Other</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Other_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Induced_pluripotent_stem_cell_line")>
													<td>Induced_pluripotent_stem_cell_line</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Induced_pluripotent_stem_cell_line_","")}</td>
													</#if>
													
													<#if data['string5']?contains("In_vitro_differentiated_cells")>
													<td>In_vitro_differentiated_cells</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("In_vitro_differentiated_cells_","")}</td>
													</#if>
													
													</#if>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string6']}:${data['string7']}-${data['string8']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string6']}:${data['string7']}-${data['string8']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "Super_Enhancer">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>SE chr</th>
							                        <th>SE start</th>
							                        <th>SE end</th>
							                        <th>SE ID</th>
													<#if set?contains("dbSUPER")>
						                            <th>Source</th>
						                            <th>Tissue Type</th>
						                            <#else>
						                            <th>Sample ID</th>
						                            <th>Biosample type</th>
						                            <th>Biosample name</th>
						                            </#if>
						                            <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                    <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string6}','${data.string7}','${data.string8}')"></td>
							                        <td id="chr" hidden>${data['string6']}</td>
													<td id="start" hidden>${data['string7']}</td>
													<td id="end" hidden>${data['string8']}</td>
													<td id="colorchange" >${data['string6']}:${data['string7']}-${data['string8']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<#if data['string5']?contains("dbSUPER")>
													<td><a href = "http://bioinfo.au.tsinghua.edu.cn/dbsuper/" target='_blank'>${data['string4']}</a></td>
													<td>dbSUPER</td>
													<td>${data['string5']?replace("dbSUPER_","")}</td>
													<#else>
													<td><a onclick = openWin("/Greap/seanalysis?sE_name=${data['string4']}")>${data['string4']}</a></td>
													<td>
														<a href="http://www.licpathway.net/SEanalysis/browseSearch_result.do/?sampleId=${data['string5']?split("_")[0]}_${data['string5']?split("_")[1]}_${data['string5']?split("_")[2]}" target='_blank'>
														${data['string5']?split("_")[0]}_${data['string5']?split("_")[1]}_${data['string5']?split("_")[2]}
														</a>
													</td>
													
													<#if data['string5']?contains("Cell_line")>
													<td>Cell_Line</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Cell_line_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Stem_cell")>
													<td>Stem_Line</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Stem_cell_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Primary_cell")>
													<td>Primary_Line</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Primary_cell_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Tissue")>
													<td>Tissue</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Tissue_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Other")>
													<td>Other</td>
													<td>${data['string5']?replace(data['string5']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Other_","")}</td>
													</#if>
													
													<#if data['string5']?contains("Induced_pluripotent_stem_cell_line")>
													<td>Induced_pluripotent_stem_cell_line</td>
													<td>${data['string4']?replace(data['string4']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("Induced_pluripotent_stem_cell_line_","")}</td>
													</#if>
													
													<#if data['string5']?contains("In_vitro_differentiated_cells")>
													<td>In_vitro_differentiated_cells</td>
													<td>${data['string4']?replace(data['string4']?split("_")[1],"")?replace(data['string5']?split("_")[2],"")?replace("Sample___","")?replace("In_vitro_differentiated_cells_","")}</td>
													</#if>
													
													</#if>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string6']}:${data['string7']}-${data['string8']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string6']}:${data['string7']}-${data['string8']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "SNP">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>SNP chr</th>
							                        <th>SNP position</th>
							                        <th>rsID</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                    <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string6}','${data.string7}','${data.string8}')"></td>
							                        <td id="chr" hidden>${data['string6']}</td>
													<td id="start" hidden>${data['string7']}</td>
													<td id="end" hidden>${data['string8']}</td>
													<td id="colorchange" >${data['string6']}:${data['string7']}-${data['string8']}</td>
													<td>${data['string1']}</td>
													<#if subclass?split("_")[1] == "10kb">
													<td>${data['string3']?eval-10000}</td>
													<#elseif subclass?split("_")[1] == "15kb">
													<td>${data['string3']?eval-15000}</td>
													<#else>
													<td>${data['string3']?eval-20000}</td>
													</#if>
													<td>${data['string4']}</td>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string6']}:${data['string7']}-${data['string8']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string6']}:${data['string7']}-${data['string8']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> <#if datatype == "eQTL">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>SNP chr</th>
							                        <th>SNP position</th>
							                        <th>rsID</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                    <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string6}','${data.string7}','${data.string8}')"></td>
							                        <td id="chr" hidden>${data['string6']}</td>
													<td id="start" hidden>${data['string7']}</td>
													<td id="end" hidden>${data['string8']}</td>
													<td id="colorchange" >${data['string6']}:${data['string7']}-${data['string8']}</td>
													<td>${data['string1']}</td>
													<#if subclass?split("_")[1] == "10kb">
													<td>${data['string3']?eval-10000}</td>
													<#elseif subclass?split("_")[1] == "15kb">
													<td>${data['string3']?eval-15000}</td>
													<#else>
													<td>${data['string3']?eval-20000}</td>
													</#if>
													<td>${data['string4']}</td>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string6']}:${data['string7']}-${data['string8']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string6']}:${data['string7']}-${data['string8']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "Methylation">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>Methylation chr</th>
							                        <th>Methylation position</th>
							                        <th>Probe ID</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                    <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string6}','${data.string7}','${data.string8}')"></td>
							                        <td id="chr" hidden>${data['string6']}</td>
													<td id="start" hidden>${data['string7']}</td>
													<td id="end" hidden>${data['string8']}</td>
													<td id="colorchange" >${data['string6']}:${data['string7']}-${data['string8']}</td>
													<td>${data['string1']}</td>
													<#if subclass?split("_")[1] == "10kb">
													<td>${data['string3']?eval-10000}</td>
													<#elseif subclass?split("_")[1] == "15kb">
													<td>${data['string3']?eval-15000}</td>
													<#else>
													<td>${data['string3']?eval-20000}</td>
													</#if>
													<td>${data['string4']}</td>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string6']}:${data['string7']}-${data['string8']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string6']}:${data['string7']}-${data['string8']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "LncRNA">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>LncRNA chr</th>
							                        <th>LncRNA start</th>
							                        <th>LncRNA end</th>
							                        <th>LncRNA name</th>
							                        <th>Promoter</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                    <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string8}','${data.string9}','${data.string10}')"></td>
							                        <td id="chr" hidden>${data['string8']}</td>
													<td id="start" hidden>${data['string9']}</td>
													<td id="end" hidden>${data['string10']}</td>
													<td id="colorchange" >${data['string8']}:${data['string9']}-${data['string10']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td><a onclick = openWinlnc("/Greap/trlnc?lncrna_name=${data['string4']}&regulation=${data['string5']}")>${data['string4']}</a></td>
													<td>${data['string5']}</td>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string8']}:${data['string9']}-${data['string10']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string8']}:${data['string9']}-${data['string10']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										<#if datatype == "mRNA">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                <thead>
							                    <tr>
							                        <th></th>
							                        <th hidden>chr</th>
							                        <th hidden>start</th>
							                        <th hidden>end</th>
							                        <th>Input region</th>
							                        <th>mRNA chr</th>
							                        <th>mRNA start</th>
							                        <th>mRNA end</th>
							                        <th>mRNA name</th>
							                        <th>Promoter</th>
							                        <th>Visualization</th>
							                    </tr>
							                </thead>
							                <tbody id="chartchr">
							                    <#list list as data>
												<tr>
												    <td><input type="radio" name="check" class="check" autocomplete="off" onclick="position('${data.string8}','${data.string9}','${data.string10}')"></td>
							                        <td id="chr" hidden>${data['string8']}</td>
													<td id="start" hidden>${data['string9']}</td>
													<td id="end" hidden>${data['string10']}</td>
													<td id="colorchange" >${data['string8']}:${data['string9']}-${data['string10']}</td>
													<td>${data['string1']}</td>
													<td>${data['string2']}</td>
													<td>${data['string3']}</td>
													<td>${data['string4']}</td>
													<td>${data['string5']}</td>
													<td><a href="http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position=${data['string8']}:${data['string9']}-${data['string10']}&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo" target="_blank"><img src="${base.contextPath}/static/img/ucsc.jpg" style="width:50px"></a>|<a href="http://39.98.139.1/GREAP_gb/?loc=${data['string8']}:${data['string9']}-${data['string10']}&tracks=${subclass}&highlight=" target="_blank"><img src="${base.contextPath}/static/img/greap.jpg" style="width:50px"></a></td>
												</tr>
							                    </#list>
							                </tbody>
							            </table>
										</#if> 
										 
										<script type="text/javascript" src="${base.contextPath}/static/js/analysis_set_detail_0.js"></script>
    <!--################################################ browse页进入 ########################################### -->						              
						                <#elseif RequestParameters.port == "1">
						                <#if datatype == "ChromHMM">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>State</th>
						                            <th>Sample ID</th>
						                            <th>Biosample name</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
									                "aLengthMenu": [5, 10, 15],
													"scrollX": true,
													 columns: [
														{
														      "data": function(data){
														         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
														      },
														},
														{
																"data": function(data){
														         return data.string1 + ":" + data.string2 + "-" + data.string3 ;
														      },
														},
										                {
										                    "data": 'string4',
										                },
										                {
										                    "data": 'string5',
										                },
										                {
										                    "data": function(data){
														         return data.string6.substring(("E001_" + data.string6.split("_")[1] + "_").length);
														      }
										                },
										                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														      }
										                }
										            ]
												});
											 })
											 
										</script>
										</#if>
										<#if datatype == "TF">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>Cell name</th>
						                            <th>TF name</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
										                "aLengthMenu": [5, 10, 15],
														"scrollX": true,
														 columns: [
															{
															      "data": function(data){
															         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
															      },
															},
															{
																	"data": function(data){
															         return data.string1 + ":" + data.string2 + "-" + data.string3;
															      },
															},
											                {
											                    "data": function(data){
															         return data.string4.split("_")[0];
															      },
											                },
											                {
											                    "data": function(data){
													                 return "<a href='http://bio.licpathway.net/TFTGdb/browserDetail?symbol=" + data.string4.split("_")[1] + "' target='_blank'>" + data.string4.split("_")[1] + "</a>";
															         return ;
															      },
											                },
											                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														           }
														      }
											            ],
											            
													});
											 })
											 
										</script>
										</#if>   
										<#if datatype == "TcoF">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>Cell name</th>
						                            <th>TcoF name</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
										                "aLengthMenu": [5, 10, 15],
														"scrollX": true,
														 columns: [
															{
															      "data": function(data){
															         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
															      },
															},
															{
																	"data": function(data){
															         return data.string1 + ":" + data.string2 + "-" + data.string3;
															      },
															},
											                {
											                    "data": function(data){
															         return data.string4.split("_")[0];
															      },
											                },
											                {
											                    "data": function(data){
															         return "<a href=\"http://tcof.liclab.net/TcoFbase/Browse/Human/main_function.php?&Symbol=" + data.string4.split("_")[1] + "\" target=\"_blank\">" + data.string4.split("_")[1] + "</a>";
															      },
											                },
											                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
											            ],
											            
													});
											 })
											 
										</script>
										</#if>   
										<#if datatype == "Histone">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>Cell name</th>
						                            <th>Histone</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
										                "aLengthMenu": [5, 10, 15],
														"scrollX": true,
														 columns: [
															{
															      "data": function(data){
															         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
															      },
															},
															{
																	"data": function(data){
															         return data.string1 + ":" + data.string2 + "-" + data.string3;
															      },
															},
											                {
											                    "data": function(data){
															         return data.string4.split("_")[1];
															      },
											                },
											                {
											                    "data": function(data){
															         return data.string4.split("_")[0];
															      },
											                },
											                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
											            ],
											            
													});
											 })
											 
										</script>
										</#if>
										<#if datatype == "ATAC">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>Sample ID</th>
						                            <th>Biosample type</th>
						                            <th>Biosample name</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
											    function getSplitAfter(split, num) {
												  let reverse = split.reverse();
												  let new_split = [];
												  for (let i = 0; i < num; i++) {
												    new_split.push(reverse[i]);
												  }
												  reverse = new_split.reverse();
												  return reverse.join("_");
												}
												
												function getContent(str, num) {
												  let str_array = ["Cell_Line", "Stem_Cell", "Primary_Cell", "Tissue", "Other", "Induced_pluripotent_stem_cell_line"];
												  // Sample_0091_Cell_Line_AT-hMSC-TERT
												  let split = str.split("_");
												  let length = split.length - 2;
												  let judge = split[2]
												
												  if (judge === "Cell") {
												    return num === 1 ? str_array[0] : getSplitAfter(split, length - str_array[0].split("_").length);
												  } else if (judge === "Stem") {
												    return num === 1 ? str_array[1] : getSplitAfter(split, length - str_array[1].split("_").length);
												  } else if (judge === "Primary") {
												    return num === 1 ? str_array[2] : getSplitAfter(split, length - str_array[2].split("_").length);
												  } else if (judge === "Tissue") {
												    return num === 1 ? str_array[3] : getSplitAfter(split, length - str_array[3].split("_").length);
												  } else if (judge === "Other") {
												    return num === 1 ? str_array[4] : getSplitAfter(split, length - str_array[4].split("_").length);
												  } else if (judge === "Induced") {
												    return num === 1 ? str_array[5] : getSplitAfter(split, length - str_array[5].split("_").length);
												  }
												
												}

										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
										                "aLengthMenu": [5, 10, 15],
														"scrollX": true,
														 columns: [
															{
														        "data": function(data) {
														           return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
														        },
															},
															{
																"data": function(data) {
														         return data.string1 + ":" + data.string2 + "-" + data.string3;
														        },
															},
											                {
											                	"data": function(data) {
															         return '<a href="http://www.licpathway.net/ATACdb/search/search_sample_result.php?get_sample_id='+data.string4.split("_")[1]+'" target="_blank">Sample_'+data.string4.split("_")[1]+'</a>' ;
															    },
													        },
											                {
											                	"data": function(data) {
															         return getContent(data.string4, 1);
															    },
													        },
											                {
											                	"data": function(data) {
															         return getContent(data.string4, 2);
															    },
													        },
													        {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
											            ],
											            
													});
												 });
										</script>
										</#if>
										<#if datatype == "Enhancer">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>Enhancer ID</th>
													<#if set?split("_")[0] == "ENdb">
						                            <th>Set</th>
						                            <th>Tissue Type</th>
						                            <#else>
						                            <th>Sample ID</th>
						                            <th>Biosample type</th>
						                            <th>Biosample name</th>
						                            </#if>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										    
											    function getSplitAfter(split, num) {
												  let reverse = split.reverse();
												  let new_split = [];
												  for (let i = 0; i < num; i++) {
												    new_split.push(reverse[i]);
												  }
												  reverse = new_split.reverse();
												  return reverse.join("_");
												}
												
												function getContent(str, num) {
												  let str_array = ["Cell_line", "Stem_cell", "Primary_cell", "Tissue", "Other", "Induced_pluripotent_stem_cell_line", "In_vitro_differentiated_cells"];
												  // Sample_00_001_Tissue_adipose_tissue
												  let split = str.split("_");
												  let length = split.length - 3;
												  let judge = split[3]
												
												  if (judge === "Cell") {
												    return num === 1 ? str_array[0] : getSplitAfter(split, length - str_array[0].split("_").length);
												  } else if (judge === "Stem") {
												    return num === 1 ? str_array[1] : getSplitAfter(split, length - str_array[1].split("_").length);
												  } else if (judge === "Primary") {
												    return num === 1 ? str_array[2] : getSplitAfter(split, length - str_array[2].split("_").length);
												  } else if (judge === "Tissue") {
												    return num === 1 ? str_array[3] : getSplitAfter(split, length - str_array[3].split("_").length);
												  } else if (judge === "Other") {
												    return num === 1 ? str_array[4] : getSplitAfter(split, length - str_array[4].split("_").length);
												  } else if (judge === "Induced") {
												    return num === 1 ? str_array[5] : getSplitAfter(split, length - str_array[5].split("_").length);
												  } else {
												    return num === 1 ? str_array[6] : getSplitAfter(split, length - str_array[6].split("_").length);
												  }
												
												}
												
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
									                "aLengthMenu": [5, 10, 15],
													"scrollX": true,
													 columns: [
														{
														      "data": function(data){
														         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
														      },
														},
														{
																"data": function(data){
														         return data.string1 + ":" + data.string2 + "-" + data.string3;
														      },
														},
										                {
										                    "data": 'string4'
										                },
										                {
										                    "data": 'string5'
										                },
										                {
										                    "data": ''
										                },
														<#if set?split("_")[0] == "ENdb">
							                            <#else>
										                {
										                    "data": ''
										                },
							                            </#if>
							                            {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
										            ],
										            "columnDefs": [
									                       {
									                           "render": function (data, type, row, meta) {
									                               if (row.string5 === "ENdb") {
									                               		return "<a href='http://www.licpathway.net/ENdb/search/Detail.php?Species=Human&Enhancer_id=" + row.string4 + "' target='_blank'>" + row.string4 + "</a>";
									                               }
									                               return row.string4;
									                           },
									                           "targets": 2
									                       },
									                       {
									                           "render": function (data, type, row, meta) {
									                               let line = row.string5.replaceAll("~.", "/").replaceAll("~", "+");
									                               let split = line.split("_");
                                                                   let length = split.length - 1;
									                               if (row.string5 !== "ENdb") {
									                                   return "Sample_" + split[1] + "_" + split[2];
									                               }
									                               return split[0];
									                           },
									                           "targets": 3
									                       },
									                       {
									                           "render": function (data, type, row, meta) {
									                               let line = row.string5.replaceAll("~.", "/").replaceAll("~", "+");
									                               let split = line.split("_");
                                                                   let length = split.length - 1;
									                               if (row.string5 !== "ENdb") {
									                                   return getContent(line, 1);
									                               }
									                               return row.string6;
									                           },
									                           "targets": 4
									                       },
														<#if set?split("_")[0] == "ENdb">
							                            <#else>
									                       {
									                           "render": function (data, type, row, meta) {
									                               let line = row.string5.replaceAll("~.", "/").replaceAll("~", "+");
									                               let split = line.split("_");
                                                                   let length = split.length - 1;
									                               if (row.string5 !== "ENdb") {
									                                   return getContent(line, 2);
									                               }
									                               return "ENdb 2";
									                           },
									                           "targets": 5
									                       },
							                            </#if>
									   				]
												});
											 })
											 
										</script>
										</#if>  
										<#if datatype == "Super_Enhancer">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>SE ID</th>
													<#if set?split("_")[0] == "dbSUPER">
						                            <th>Source</th>
						                            <th>Tissue Type</th>
						                            <#else>
						                            <th>Sample ID</th>
						                            <th>Biosample type</th>
						                            <th>Biosample name</th>
						                            </#if>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										    
											    function getSplitAfter(split, num) {
												  let reverse = split.reverse();
												  let new_split = [];
												  for (let i = 0; i < num; i++) {
												    new_split.push(reverse[i]);
												  }
												  reverse = new_split.reverse();
												  return reverse.join("_");
												}
												
												function getContent(str, num) {
												  let str_array = ["Cell_line", "Stem_cell", "Primary_cell", "Tissue", "Other", "Induced_pluripotent_stem_cell_line", "In_vitro_differentiated_cells"];
												  // Sample_00_001_Tissue_adipose_tissue
												  let split = str.split("_");
												  let length = split.length - 3;
												  let judge = split[3]
												
												  if (judge === "Cell") {
												    return num === 1 ? str_array[0] : getSplitAfter(split, length - str_array[0].split("_").length);
												  } else if (judge === "Stem") {
												    return num === 1 ? str_array[1] : getSplitAfter(split, length - str_array[1].split("_").length);
												  } else if (judge === "Primary") {
												    return num === 1 ? str_array[2] : getSplitAfter(split, length - str_array[2].split("_").length);
												  } else if (judge === "Tissue") {
												    return num === 1 ? str_array[3] : getSplitAfter(split, length - str_array[3].split("_").length);
												  } else if (judge === "Other") {
												    return num === 1 ? str_array[4] : getSplitAfter(split, length - str_array[4].split("_").length);
												  } else if (judge === "Induced") {
												    return num === 1 ? str_array[5] : getSplitAfter(split, length - str_array[5].split("_").length);
												  } else {
												    return num === 1 ? str_array[6] : getSplitAfter(split, length - str_array[6].split("_").length);
												  }
												
												}
												
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
										                "aLengthMenu": [5, 10, 15],
														"scrollX": true,
														 columns: [
															{
															      "data": function(data){
															         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
															      },
															},
															{
																	"data": function(data){
															         return data.string1 + ":" + data.string2 + "-" + data.string3;
															      },
															},
															{
															      "data": function(data){
															         if(data.string5.split("_")[0] == "dbSUPER"){
															             return '<a href = "http://bioinfo.au.tsinghua.edu.cn/dbsuper/" target="_blank">'+ data.string4 +'</a>' ;
															         }else{
															             return '<a onclick = openWin("/Greap/seanalysis?sE_name='+data.string4+'")>'+ data.string4 +'</a>' ;
															         }
															         
															      },
															},
											                {
											                    "data": 'string5'
											                },
											                {
											                    "data": ''
											                },
															<#if set?split("_")[0] == "dbSUPER">
								                            <#else>
											                {
											                    "data": ''
											                },
								                            </#if>
								                            {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
											            ],
											            "columnDefs": [
										                       {
										                           "render": function (data, type, row, meta) {
										                               let line = row.string5.replaceAll("~.", "/").replaceAll("~", "+");
										                               let split = line.split("_");
                                                                       let length = split.length - 1;
										                               if (split[0] !== "dbSUPER") {
										                                   return "<a href='http://www.licpathway.net/SEanalysis/browseSearch_result.do/?sampleId=Sample_" + line.split("_")[1] + "_" + line.split("_")[2] + "' target='_blank'>Sample_" + line.split("_")[1] + "_" + line.split("_")[2] + "</a>";
										                               }
										                               return split[0];
										                           },
										                           "targets": 3
										                       },
										                       {
										                           "render": function (data, type, row, meta) {
										                               let line = row.string5.replaceAll("~.", "/").replaceAll("~", "+");
										                               let split = line.split("_");
                                                                       let length = split.length - 1;
										                               if (split[0] !== "dbSUPER") {
										                                   return getContent(line, 1);
										                               }
										                               return getSplitAfter(split, length);
										                           },
										                           "targets": 4
										                       },
															<#if set?split("_")[0] == "dbSUPER">
								                            <#else>
										                       {
										                           "render": function (data, type, row, meta) {
										                               let line = row.string5.replaceAll("~.", "/").replaceAll("~", "+");
										                               let split = line.split("_");
                                                                       let length = split.length - 1;
										                               if (split[0] !== "dbSUPER") {
										                                   return getContent(line, 2);
										                               }
										                               return getSplitAfter(split, length);
										                           },
										                           "targets": 5
										                       }
								                            </#if>
										   				]
													});
											 })
											 
										</script>
										</#if>  
										<#if datatype == "SNP">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>rsID</th>
						                            <th>Set</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
									                "aLengthMenu": [5, 10, 15],
													"scrollX": true,
													 columns: [
														{
														      "data": function(data){
														         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
														      },
														},
														{
																"data": function(data){
														         return data.string1 + ":" + data.string2 + "-" + data.string3;
														      },
														},
										                {
										                    "data": 'string4'
										                },
										                {
										                    "data": 'string5'
										                },
										                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
										            ],
										            "columnDefs": [
									                       {
									                           "render": function (data, type, row, meta) {
									                               return "<a href='http://www.licpathway.net/VARAdb/search/search_rsid_result.php?rs_sel_type=rsIDs&rs=" + row.string4 + "' target='_blank'>" + row.string4 + "</a>";
									                           },
									                           "targets": 2
									                       }
									   				]
										            
												});
											 })
											 
										</script>
										</#if>  
										<#if datatype == "eQTL">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>rsID</th>
						                            <th>Set</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
									                "aLengthMenu": [5, 10, 15],
													"scrollX": true,
													 columns: [
														{
														      "data": function(data){
														         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
														      },
														},
														{
																"data": function(data){
														         return data.string1 + ":" + data.string2 + "-" + data.string3;
														      },
														},
										                {
										                    "data": 'string4'
										                },
										                {
										                    "data": 'string5'
										                },
										                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
										            ],
										            "columnDefs": [
									                       {
									                           "render": function (data, type, row, meta) {
									                               return "<a href='http://www.licpathway.net/VARAdb/search/search_rsid_result.php?rs_sel_type=rsIDs&rs=" + row.string4 + "' target='_blank'>" + row.string4 + "</a>";
									                           },
									                           "targets": 2
									                       }
									   				]
										            
												});
											 })
											 
										</script>
										</#if>  
										<#if datatype == "Methylation">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>Probe ID</th>
						                            <th>Set</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
									                "aLengthMenu": [5, 10, 15],
													"scrollX": true,
													 columns: [
														{
														      "data": function(data){
														         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
														      },
														},
														{
																"data": function(data){
														         return data.string1 + ":" + data.string2 + "-" + data.string3;
														      },
														},
										                {
										                    "data": 'string4'
										                },
										                {
										                    "data": 'string5'
										                },
										                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
										            ],
										            "columnDefs": [
									                       {
									                           "render": function (data, type, row, meta) {
									                               return "<a href='https://ngdc.cncb.ac.cn/ewas/datahub/probe/" + row.string4 + "' target='_blank'>" + row.string4 + "</a>";
									                           },
									                           "targets": 2
									                       }
									   				]
												});
											 })
											 
										</script>
										</#if>
										<#if datatype == "LncRNA">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>LncRNA</th>
						                            <th>Promoter</th>
						                            <th>Set</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
										                "aLengthMenu": [5, 10, 15],
														"scrollX": true,
														 columns: [
															{
															      "data": function(data){
															         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
															      },
															},
															{
																	"data": function(data){
															         return data.string1 + ":" + data.string2 + "-" + data.string3;
															      },
															},
															{
															      "data": function(data){
															         return '<a onclick = openWinlnc("/Greap/trlnc?lncrna_name='+data.string4+'&regulation='+data.string5+'")>'+ data.string4 +'</a>' ;
															      },
															},
											                {
											                    "data": 'string5'
											                },
											                {
											                    "data": 'string6'
											                },
											                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
											            ],
											            
													});
											 })
											 
										</script>
										</#if>  
										<#if datatype == "mRNA">
						                 <table id="tableport" class="table table-bordered" style="width:100%">
							                 <thead>
						                        <tr>
						                            <th></th>
						                            <th>Region</th>
						                            <th>mRNA</th>
						                            <th>Promoter</th>
						                            <th>Set</th>
						                            <th>Visualization</th>
						                        </tr>
						                    </thead>
											 <tbody>
											    <tr></tr>
											 </tbody>
							            </table>
							            <script>
										    $(document).ready(function () {
										         $('#tableport').DataTable({
										            fnInitComplete: function () { 
                                                        
														//body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓彉鑹�
														document.getElementsByClassName("check")[0].checked = true;
														document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].style.backgroundColor="orange";
													    //body鍔犺浇鏃惰Е鍙戯紝榛樿绗竴涓紶鍊�
													    var chr = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[0];
													    var start = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[1];
													    var end = document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[1].innerHTML.replace(":","-").split("-")[2];
													    
													    document.getElementById("tableform").action="../analysis/position?chr="+chr+"&start="+start+"&end="+end;
                                                        document.getElementById("tableform").submit();
                                                     },
										            destroy:true,
										            serverSide: true,
										            searching : false,
										            ajax: {
										                url: '../search/set_detail',
										                type: 'POST'
										            },  
										                "aLengthMenu": [5, 10, 15],
														"scrollX": true,
														 columns: [
															{
															      "data": function(data){
															         return '<input type="radio" name="check" class="check" autocomplete="off" onclick="position(\''+ data.string1 +'\',\''+ data.string2 +'\',\''+ data.string3 +'\')">' ;
															      },
															},
															{
																	"data": function(data){
															         return data.string1 + ":" + data.string2 + "-" + data.string3;
															      },
															},
											                {
											                    "data": 'string4'
											                },
											                {
											                    "data": 'string5'
											                },
											                {
											                    "data": 'string7'
											                },
											                {
										                    "data": function(data){
														             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string1 + ":" + data.string2 + "-" + data.string3 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string1 + ":" + data.string2 + "-" + data.string3 + "&tracks=${subclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
															       	
														            }
										                    }
											            ],
											            
													});
											 })
											 
										</script>
										</#if>    
										<script type="text/javascript" src="${base.contextPath}/static/js/analysis_set_detail_1.js"></script>
									  </#if>
									  
									 </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<!-- ######################################################################## 姝ゅ鏄尯鍩熸敞閲� ################################################################################################ -->        
        
        <iframe src="${base.contextPath}/view/analysis_set_detail_region" class="col-lg-12" frameborder="no" name="iframe" style="height:1000px;padding-left: 0px;padding-right: 0px;"></iframe>
   
    </div>
    
    <div hidden id="port">${port}</div>
    
   

<#include "nav/footer.ftl" />

<script type="text/javascript" src="${base.contextPath}/static/js/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${base.contextPath}/static/js/echarts/dataTool.min.js"></script>
<script type="text/javascript">
    // 鍩轰簬鍑嗗濂界殑dom锛屽垵濮嬪寲echarts瀹炰緥
    var myChart = echarts.init(document.getElementById('main'));

    // 鎸囧畾鍥捐〃鐨勯厤缃」鍜屾暟鎹�
     var labelOption = {
                normal: {
                    show: true,
                    position: 'insideBottom',
                    rotate: 90,
                    textStyle: {
                        align: 'left',
                        verticalAlign: 'middle'
                    }
                }
            };
            option = {
            		title : {
        				text : 'The number of chromosomes in the set' ,
        				left: '25%'
        			},
            
                color: ['#003366', '#006699', '#4cabce', '#e5323e'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 鍧愭爣杞存寚绀哄櫒锛屽潗鏍囪酱瑙﹀彂鏈夋晥
                        type: 'shadow'        // 榛樿涓虹洿绾匡紝鍙�変负锛�'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['Samples', 'TFs'],
                    bottom:'0%'
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true,title: "Mark"},
                     
                        magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled'],title: ['line', 'bar', 'stack', 'tiled']},
                        restore: {show: true,title: "Restore"},
                        saveAsImage: {show: true,title: "SaveAsImage"}
                    }
                },
                calculable: true,
                grid: {
			         left: '20% '
			    },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {show: false},
                        data: ${x},
						axisLabel: 
	                      {
	                        show: true,
	                        interval: '0',
	                        rotate: 45,
	                      },
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        
                        type: 'bar',
                        barGap: 0,
                        label: labelOption,
                        data: ${y}
                    },
                    
                ]
            }

    
    myChart.setOption(option);
</script>
<script>
    function openWin(url){
        window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=300px,top=200px,width=800px, height=800px")
    };

    function openWinlnc(url){
        window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=300px,top=200px,width=800px, height=800px")
    };
</script>


</body>
</html>