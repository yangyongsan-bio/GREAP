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
   

</head>

<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />
<form action="../analysis" method="post" enctype="multipart/form-data" style="margin-top:110px">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1">
                <div class="row">
                    <div class="col-lg-12 ">
                        <div class="form-group">
                            <input type="radio" id="genesource1" name="source" value="0" autocomplete="off" checked=""> Paste your data <span style="color:red">(hg38)</span>: 
                            <textarea class="form-control" rows="12" id="input" name="input" spellcheck="false"></textarea>
                            <br>
                            <br>
                            <input type="radio" id="genesource2" name="source" value="1" autocomplete="off" style="margin-bottom: 10px"> Upload a file <span style="color:red">(hg38)</span>:
                            <input id="file" type="file" name="file" style="display:inline;"/>
                            <a href="/Greap/data/example.bed"><button type="button" style="color: black;">Example of Upload File</button></a>
                            <br>
                            <br>
                            <ul id="myTab" class="nav nav-tabs">
								<li class="active">
									<a href="#1" data-toggle="tab" id="page1">Hypergeometric Test</a>
								</li>
								<li>
								    <a href="#2" data-toggle="tab" id="page2">LOLA <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Locus Overlap Analysis (LOLA) provides easy and automatable enrichment analysis for genomic region sets, thus facilitating the interpretation of functional genomics and epigenomics data" style="width: 12px;"></a>
								</li>
								
	                            <div style="display: none" >
						            <!--隐藏将block改为none-->
						            <input id="radio1" type="radio" name="methodForm" value="1" checked="" />
						            <input id="radio2" type="radio" name="methodForm" value="2">
						        </div>
							</ul>
                            <div id="myTabContent" class="tab-content">
								<div class="tab-pane fade in active" id="1">
								    <br>
									Minimum percentage of overlap :
		                            <select class="form-control" id="inputNumber" name="proportion" style="display: inline;width: 10%;padding: 0;height: 25px;">
		                                <option value="0.1" >10%</option>
		                                <option value="0.5" >50%</option>
		                                <option value="1.0" >100%</option>
		                            </select>
		                            <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="The parameters of Bedtools" style="width: 12px;">
		                            <br>
		                            <br>
		                            <label class="radio-inline"><input type="radio" name="outputForm" value="0" autocomplete="off" checked="" /> Applied on our catalog of peaks</label>
		                            <br>
		                            <br>
		                            <label class="radio-inline"><input type="radio" name="outputForm" value="1" autocomplete="off" /> Applied on your submitted regions</label>
		                            <br>
		                            <br>
		                            <label class="radio-inline"><input type="radio" name="outputForm" value="2" autocomplete="off" /> Apply the minimum percentage of overlap on both data</label>
		                            <br>
		                            <br>
		                            <!--
		                            <font> P-value <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Hypergeometric P value" style="width: 12px;">:</font>
								    <input type="text" class="form-control" name="pvalue" id="pvalue" value="0.01" style="width: 10%;display: inline;">
								    <font style="margin-left: 10px;"> BH <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Adjusted p value with FalseDiscovery Rate method" style="width: 12px;">:</font>
								    <input type="text" class="form-control" name="pvalue" id="pvalue" value="0.05" style="width: 10%;display: inline;">
								    <font style="margin-left: 10px;"> Bonferroni <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Adjusted p value with FalseDiscovery Rate method" style="width: 12px;">:</font>
								    <input type="text" class="form-control" name="pvalue" id="pvalue" value="0.05" style="width: 10%;display: inline;">
								    <br>
		                            <br>
		                            -->
								</div>
								<div class="tab-pane fade" id="2">
								    <br>
									<label>Select background universe</label>
						            <select class="form-control" name="universe" style="width: 50%;margin-bottom: 10px;">
						                    <option value="" >activeDHS_universe.bed</option>
						            </select>
						            <!--
						            <font> P-value <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Hypergeometric P value" style="width: 12px;">:</font>
								    <input type="text" class="form-control" name="pvalue" id="pvalue" value="0.01" style="width: 10%;display: inline;">
								    <font style="margin-left: 10px;"> Odds ratio <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Adjusted p value with FalseDiscovery Rate method" style="width: 12px;">:</font>
								    <input type="text" class="form-control" name="pvalue" id="pvalue" value="0.05" style="width: 10%;display: inline;">
								    -->
								    <br>
		                            <br>
								</div>
							</div>
                            
                        </div>
                    </div>
                         
                    <div class="col-lg-12">
	                    <button type="submit" class="btn btn-danger" id="analysis_check">Submit</button>
	                    <button type="reset" class="btn btn-danger">Reset</button>
	                    <button type="button" class="btn btn-danger" id="analysis_example">Example</button>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 " id="sample">
                <ul id="myTab" class="nav nav-tabs">
					<li class="active">
						<a href="#3" data-toggle="tab" id="page3" style="font-size: 28px;">Custom Analysis</a>
					</li>
					<li>
					    <a href="#4" data-toggle="tab" id="page4" style="font-size: 28px;">Quick Analysis </a>
					</li>
					
                    <div style="display: none" >
			            <!--隐藏将block改为none-->
			            <input id="radio3" type="radio" name="analysisForm" value="1" checked="" />
			            <input id="radio4" type="radio" name="analysisForm" value="2">
			        </div>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="3">
					    <br>
					    <!--<div class="col-lg-12">
		                    <table width="100%">
		                        <tr>
		                            <td></td>
		                            <td>
		                                <button type="button" href="#" type="button" class="btn btn-w3r" id="All" onclick="AllOpen(this,'box','box1','box2','box3','box4','box5','box6','box7','box8','box9','box10','box11','box12','box13','box14','box15','box16','box17','box18')" style="border: 1px solid #50d8af">
		                                    <span style="font-size: medium; ">Open all</span>
		                                </button>
		                                <button type="button" class="btn btn-w3r" onclick="CheckAll(this)" id="1"><span style="font-size: medium; ">Check all</span></button>
		                            </td>
		                        </tr>
		                    </table>
		                </div>-->
		                <!--<div class="col-lg-12" style="border-style:solid;border-color:#98bf21;border-radius: 10px;margin-bottom: 10px;margin-top: 10px;">
		                <p style="margin-bottom: auto;font-size: 25px;">1、Chromatin state </p>-->
		                <p style="margin-bottom: auto;font-size: 15px;margin-left: 20px;margin-top: -15px;color:red">You can choose the parameters you are interested in </p>
		                <div style="margin-left: -16px;">
		                <div class="col-lg-11" >
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d11" onclick="openShutManager(this,'box11');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">ChromHMM state:</font>
		                        <font id="pf11">0 options selected</font><a href="${base.contextPath}/view/help#ChromHMM"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		                        <div id="box11" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa11" onclick="sa('box11','tp11','sa11','pf11')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                <tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TssA" onclick="javascript:doit('tp11','pf11');"></td><td>TssA <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Active TSS" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TssAFlnk" onclick="javascript:doit('tp11','pf11');"></td><td>TssAFlnk <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Flanking Active TSS" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TxFlnk" onclick="javascript:doit('tp11','pf11');"></td><td>TxFlnk <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"  title="Transcr. at gene 5' and 3'" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="Tx" onclick="javascript:doit('tp11','pf11');"></td><td>Tx <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Strong transcription" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TxWk" onclick="javascript:doit('tp11','pf11');"></td><td>TxWk <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Weak transcription" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="EnhG" onclick="javascript:doit('tp11','pf11');"></td><td>EnhG <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Genic enhancers" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="Enh" onclick="javascript:doit('tp11','pf11');"></td><td>Enh <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Enhancers" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="ZNF_Rpts" onclick="javascript:doit('tp11','pf11');"></td><td>ZNF/Rpts <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="ZNF genes & repeats" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="Het" onclick="javascript:doit('tp11','pf11');"></td><td>Het <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Heterochromatin" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TssBiv" onclick="javascript:doit('tp11','pf11');"></td><td>TssBiv <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Bivalent/Poised TSS" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="BivFlnk" onclick="javascript:doit('tp11','pf11');"></td><td>BivFlnk <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Flanking Bivalent TSS/Enh" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="12_EnhBiv" onclick="javascript:doit('tp11','pf11');"></td><td>EnhBiv <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Bivalent Enhancer" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="ReprPC" onclick="javascript:doit('tp11','pf11');"></td><td>ReprPC <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Repressed PolyComb" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="ReprPCWk" onclick="javascript:doit('tp11','pf11');"></td><td>ReprPCWk <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Weak Repressed PolyComb" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="Quies" onclick="javascript:doit('tp11','pf11');"></td><td>Quies <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip" title="Quiescent/Low" style="width: 12px;"></td>
		                                <td></td><td></td>
		                                </tr>
		                            </table>
		                            
		                        </div>
		                    </div>
		                
		                     <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d" onclick="openShutManager(this,'box');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Transcription factor:</font>
		                        <font id="pf">0 options selected</font><a href="${base.contextPath}/view/help#TF"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		                        <div id="box" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa" onclick="sa('box','tp','sa','pf')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                <tr>
										<td><img src="${base.contextPath}/static/img/class.png" style="width: 16px;"></td><td>Tissue type</td>
										<td></td><td></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Adipose" onclick="javascript:doit('tp','pf');"></td><td>Adipose</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Adrenal_Gland" onclick="javascript:doit('tp','pf');"></td><td>Adrenal_Gland</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Blood" onclick="javascript:doit('tp','pf');"></td><td>Blood</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Bone" onclick="javascript:doit('tp','pf');"></td><td>Bone</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Bone_Marrow" onclick="javascript:doit('tp','pf');"></td><td>Bone_Marrow</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Brain" onclick="javascript:doit('tp','pf');"></td><td>Brain</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Breast" onclick="javascript:doit('tp','pf');"></td><td>Breast</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Bronchia" onclick="javascript:doit('tp','pf');"></td><td>Bronchia</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Cerebellum" onclick="javascript:doit('tp','pf');"></td><td>Cerebellum</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Cervix" onclick="javascript:doit('tp','pf');"></td><td>Cervix</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Colon" onclick="javascript:doit('tp','pf');"></td><td>Colon</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Connective_Tissue" onclick="javascript:doit('tp','pf');"></td><td>Connective_Tissue</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Cord_blood" onclick="javascript:doit('tp','pf');"></td><td>Cord_blood</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Corneal_Epithelium" onclick="javascript:doit('tp','pf');"></td><td>Corneal_Epithelium</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Coronary_artery_smooth_muscle" onclick="javascript:doit('tp','pf');"></td><td>Coronary_artery_smooth_muscle</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Cranial" onclick="javascript:doit('tp','pf');"></td><td>Cranial</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Embryo" onclick="javascript:doit('tp','pf');"></td><td>Embryo</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Embryonic_Kidney" onclick="javascript:doit('tp','pf');"></td><td>Embryonic_Kidney</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Endoderm" onclick="javascript:doit('tp','pf');"></td><td>Endoderm</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Endometrium" onclick="javascript:doit('tp','pf');"></td><td>Endometrium</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Epididymis" onclick="javascript:doit('tp','pf');"></td><td>Epididymis</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="epithelioid_sarcoma" onclick="javascript:doit('tp','pf');"></td><td>epithelioid_sarcoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Esophagus" onclick="javascript:doit('tp','pf');"></td><td>Esophagus</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Eye" onclick="javascript:doit('tp','pf');"></td><td>Eye</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Fallopian_tube_secretory" onclick="javascript:doit('tp','pf');"></td><td>Fallopian_tube_secretory</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Fetal_Liver" onclick="javascript:doit('tp','pf');"></td><td>Fetal_Liver</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Fetal_Lung" onclick="javascript:doit('tp','pf');"></td><td>Fetal_Lung</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Fetal_Skin" onclick="javascript:doit('tp','pf');"></td><td>Fetal_Skin</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Foreskin" onclick="javascript:doit('tp','pf');"></td><td>Foreskin</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Gingiva" onclick="javascript:doit('tp','pf');"></td><td>Gingiva</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Heart" onclick="javascript:doit('tp','pf');"></td><td>Heart</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Head_and_neck" onclick="javascript:doit('tp','pf');"></td><td>Head_and_neck</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="HeLa_contaminant" onclick="javascript:doit('tp','pf');"></td><td>HeLa_contaminant</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Kidney" onclick="javascript:doit('tp','pf');"></td><td>Kidney</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Liver" onclick="javascript:doit('tp','pf');"></td><td>Liver</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="LNCaP_cells" onclick="javascript:doit('tp','pf');"></td><td>LNCaP_cells</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Lung" onclick="javascript:doit('tp','pf');"></td><td>Lung</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Lymph_Node" onclick="javascript:doit('tp','pf');"></td><td>Lymph_Node</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Malignant_rhabdoid_tumor" onclick="javascript:doit('tp','pf');"></td><td>Malignant_rhabdoid_tumor</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Mammary_Gland" onclick="javascript:doit('tp','pf');"></td><td>Mammary_Gland</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Midbrain" onclick="javascript:doit('tp','pf');"></td><td>Midbrain</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Muscle" onclick="javascript:doit('tp','pf');"></td><td>Muscle</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="None" onclick="javascript:doit('tp','pf');"></td><td>None</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Pancreas" onclick="javascript:doit('tp','pf');"></td><td>Pancreas</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Pancreatic_ductal" onclick="javascript:doit('tp','pf');"></td><td>Pancreatic_ductal</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Peripheral_Blood" onclick="javascript:doit('tp','pf');"></td><td>Peripheral_Blood</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Peritoneal_Effusion" onclick="javascript:doit('tp','pf');"></td><td>Peritoneal_Effusion</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Pleura" onclick="javascript:doit('tp','pf');"></td><td>Pleura</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Prostate" onclick="javascript:doit('tp','pf');"></td><td>Prostate</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Skeletal_Muscle" onclick="javascript:doit('tp','pf');"></td><td>Skeletal_Muscle</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Skin" onclick="javascript:doit('tp','pf');"></td><td>Skin</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Spinal_Cord" onclick="javascript:doit('tp','pf');"></td><td>Spinal_Cord</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Stomach" onclick="javascript:doit('tp','pf');"></td><td>Stomach</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Thymus" onclick="javascript:doit('tp','pf');"></td><td>Thymus</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Thyroid" onclick="javascript:doit('tp','pf');"></td><td>Thyroid</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Umbilical_Vein" onclick="javascript:doit('tp','pf');"></td><td>Umbilical_Vein</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Uterus" onclick="javascript:doit('tp','pf');"></td><td>Uterus</td>
										<td></td><td></td>
										</tr>
										<tr>
										<td><img src="${base.contextPath}/static/img/class.png" style="width: 16px;"></td><td>TF class <img src="/Greap/static/img/question.png" data-toggle="tooltip"  title="The classification from TFClass" style="width: 12px;"></td>
										<td></td><td></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="alpha-Helices-exposed-by-beta-structures" onclick="javascript:doit('tp','pf');"></td><td>alpha-Helices-exposed-by-beta-structures</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Basic-domains" onclick="javascript:doit('tp','pf');"></td><td>Basic-domains</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="beta-Barrel-DNA-binding-domains" onclick="javascript:doit('tp','pf');"></td><td>beta-Barrel-DNA-binding-domains</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="beta-Hairpin-exposed-by-an-alpha-beta-scaffold" onclick="javascript:doit('tp','pf');"></td><td>beta-Hairpin-exposed-by-an-alpha-beta-scaffold</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="beta-Sheet-binding-to-DNA" onclick="javascript:doit('tp','pf');"></td><td>beta-Sheet-binding-to-DNA</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="ENCODE-TF" onclick="javascript:doit('tp','pf');"></td><td>ENCODE-TF</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Helix-turn-helix-domains" onclick="javascript:doit('tp','pf');"></td><td>Helix-turn-helix-domains</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Immunoglobulin-fold" onclick="javascript:doit('tp','pf');"></td><td>Immunoglobulin-fold</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Other-all-alpha-helical-DNA-binding-domains" onclick="javascript:doit('tp','pf');"></td><td>Other-all-alpha-helical-DNA-binding-domains</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Undefined" onclick="javascript:doit('tp','pf');"></td><td>Undefined</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="TcoF-TF" onclick="javascript:doit('tp','pf');"></td><td>TcoF-TF</td>
										<td><input type="checkbox" class="tp" name="tf_class" value="Yet-undefined-DNA-binding-domains" onclick="javascript:doit('tp','pf');"></td><td>Yet-undefined-DNA-binding-domains</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp" name="tf_class" value="Zinc-coordinating-DNA-binding-domains" onclick="javascript:doit('tp','pf');"></td><td>Zinc-coordinating-DNA-binding-domains</td>
										<td></td><td></td>
										</tr>


                                
		                            </table>
		                        </div>
		                    </div>
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d2" onclick="openShutManager(this,'box2');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Transcription cofactor:</font>
		                        <font id="pf2">0 options selected</font><a href="${base.contextPath}/view/help#TcoF"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box2" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa2" onclick="sa('box2','tp2','sa2','pf2')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                <tr>
										<td><img src="${base.contextPath}/static/img/class.png" style="width: 15.1167px;"></td><td>Tissue type</td>
										<td></td><td></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Adipose" onclick="javascript:doit('tp2','pf2');"></td><td>Adipose</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Adrenal_Gland" onclick="javascript:doit('tp2','pf2');"></td><td>Adrenal_Gland</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Blood" onclick="javascript:doit('tp2','pf2');"></td><td>Blood</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Bone" onclick="javascript:doit('tp2','pf2');"></td><td>Bone</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Bone_Marrow" onclick="javascript:doit('tp2','pf2');"></td><td>Bone_Marrow</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Brain" onclick="javascript:doit('tp2','pf2');"></td><td>Brain</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Breast" onclick="javascript:doit('tp2','pf2');"></td><td>Breast</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Cervix" onclick="javascript:doit('tp2','pf2');"></td><td>Cervix</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Colon" onclick="javascript:doit('tp2','pf2');"></td><td>Colon</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Connective_Tissue" onclick="javascript:doit('tp2','pf2');"></td><td>Connective_Tissue</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Cord_blood" onclick="javascript:doit('tp2','pf2');"></td><td>Cord_blood</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Embryo" onclick="javascript:doit('tp2','pf2');"></td><td>Embryo</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Embryonic_Kidney" onclick="javascript:doit('tp2','pf2');"></td><td>Embryonic_Kidney</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Endometrium" onclick="javascript:doit('tp2','pf2');"></td><td>Endometrium</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="epithelioid_sarcoma" onclick="javascript:doit('tp2','pf2');"></td><td>epithelioid_sarcoma</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Esophagus" onclick="javascript:doit('tp2','pf2');"></td><td>Esophagus</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Fetal_Liver" onclick="javascript:doit('tp2','pf2');"></td><td>Fetal_Liver</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Foreskin" onclick="javascript:doit('tp2','pf2');"></td><td>Foreskin</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Head_and_neck" onclick="javascript:doit('tp2','pf2');"></td><td>Head_and_neck</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="HeLa_contaminant" onclick="javascript:doit('tp2','pf2');"></td><td>HeLa_contaminant</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Kidney" onclick="javascript:doit('tp2','pf2');"></td><td>Kidney</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Liver" onclick="javascript:doit('tp2','pf2');"></td><td>Liver</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Lung" onclick="javascript:doit('tp2','pf2');"></td><td>Lung</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Lymph_Node" onclick="javascript:doit('tp2','pf2');"></td><td>Lymph_Node</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Malignant_rhabdoid_tumor" onclick="javascript:doit('tp2','pf2');"></td><td>Malignant_rhabdoid_tumor</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Mammary_Gland" onclick="javascript:doit('tp2','pf2');"></td><td>Mammary_Gland</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Muscle" onclick="javascript:doit('tp2','pf2');"></td><td>Muscle</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="None" onclick="javascript:doit('tp2','pf2');"></td><td>None</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="ovarian" onclick="javascript:doit('tp2','pf2');"></td><td>ovarian</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Pancreas" onclick="javascript:doit('tp2','pf2');"></td><td>Pancreas</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Pleura" onclick="javascript:doit('tp2','pf2');"></td><td>Pleura</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Pontine" onclick="javascript:doit('tp2','pf2');"></td><td>Pontine</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Prostate" onclick="javascript:doit('tp2','pf2');"></td><td>Prostate</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Skin" onclick="javascript:doit('tp2','pf2');"></td><td>Skin</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Snap-frozen_tissue" onclick="javascript:doit('tp2','pf2');"></td><td>Snap-frozen_tissue</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Stomach" onclick="javascript:doit('tp2','pf2');"></td><td>Stomach</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Testis" onclick="javascript:doit('tp2','pf2');"></td><td>Testis</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Tonsil" onclick="javascript:doit('tp2','pf2');"></td><td>Tonsil</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Urinary_Bladder" onclick="javascript:doit('tp2','pf2');"></td><td>Urinary_Bladder</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Uterus" onclick="javascript:doit('tp2','pf2');"></td><td>Uterus</td>
										</tr>
										<tr>
										<td><img src="${base.contextPath}/static/img/class.png" style="width: 15.1167px;"></td><td>TcoF class <img src="/Greap/static/img/question.png" data-toggle="tooltip"  title="The classification from TcoF-DB" style="width: 12px;"></td>
										<td></td><td></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="TcoF-class-1" onclick="javascript:doit('tp2','pf2');"></td><td>TcoF-class-1</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="TcoF-class-2" onclick="javascript:doit('tp2','pf2');"></td><td>TcoF-class-2</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="TcoF-class-3" onclick="javascript:doit('tp2','pf2');"></td><td>TcoF-class-3</td>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="TcoF-class-HC" onclick="javascript:doit('tp2','pf2');"></td><td>TcoF-class-HC</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp2" name="tcof_class" value="Undefined" onclick="javascript:doit('tp2','pf2');"></td><td>Undefined</td>
										<td></td><td></td>
										</tr>
		                            </table>
		                        </div>
		                    </div>
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d8" onclick="openShutManager(this,'box8');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Histone modification:</font>
		                        <font id="pf8">0 options selected</font><a href="${base.contextPath}/view/help#Histone"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box8" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa8" onclick="sa('box8','tp8','sa8','pf8')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                
		                                <tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H2AFZ" onclick="javascript:doit('tp8','pf8');"></td><td>H2AFZ</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H2AK5ac" onclick="javascript:doit('tp8','pf8');"></td><td>H2AK5ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H2AK9ac" onclick="javascript:doit('tp8','pf8');"></td><td>H2AK9ac</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H2BK120ac" onclick="javascript:doit('tp8','pf8');"></td><td>H2BK120ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H2BK12ac" onclick="javascript:doit('tp8','pf8');"></td><td>H2BK12ac</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H2BK15ac" onclick="javascript:doit('tp8','pf8');"></td><td>H2BK15ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H2BK20ac" onclick="javascript:doit('tp8','pf8');"></td><td>H2BK20ac</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H2BK5ac" onclick="javascript:doit('tp8','pf8');"></td><td>H2BK5ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3F3A" onclick="javascript:doit('tp8','pf8');"></td><td>H3F3A</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K14ac" onclick="javascript:doit('tp8','pf8');"></td><td>H3K14ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K18ac" onclick="javascript:doit('tp8','pf8');"></td><td>H3K18ac</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K23ac" onclick="javascript:doit('tp8','pf8');"></td><td>H3K23ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K23me2" onclick="javascript:doit('tp8','pf8');"></td><td>H3K23me2</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K27ac" onclick="javascript:doit('tp8','pf8');"></td><td>H3K27ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K27me3" onclick="javascript:doit('tp8','pf8');"></td><td>H3K27me3</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K36me3" onclick="javascript:doit('tp8','pf8');"></td><td>H3K36me3</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K4ac" onclick="javascript:doit('tp8','pf8');"></td><td>H3K4ac</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K4me1" onclick="javascript:doit('tp8','pf8');"></td><td>H3K4me1</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K4me2" onclick="javascript:doit('tp8','pf8');"></td><td>H3K4me2</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K4me3" onclick="javascript:doit('tp8','pf8');"></td><td>H3K4me3</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K56ac" onclick="javascript:doit('tp8','pf8');"></td><td>H3K56ac</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K79me1" onclick="javascript:doit('tp8','pf8');"></td><td>H3K79me1</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K79me2" onclick="javascript:doit('tp8','pf8');"></td><td>H3K79me2</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K9ac" onclick="javascript:doit('tp8','pf8');"></td><td>H3K9ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K9me1" onclick="javascript:doit('tp8','pf8');"></td><td>H3K9me1</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K9me2" onclick="javascript:doit('tp8','pf8');"></td><td>H3K9me2</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3K9me3" onclick="javascript:doit('tp8','pf8');"></td><td>H3K9me3</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H3T11ph" onclick="javascript:doit('tp8','pf8');"></td><td>H3T11ph</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H4K12ac" onclick="javascript:doit('tp8','pf8');"></td><td>H4K12ac</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H4K20me1" onclick="javascript:doit('tp8','pf8');"></td><td>H4K20me1</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H4K5ac" onclick="javascript:doit('tp8','pf8');"></td><td>H4K5ac</td>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H4K8ac" onclick="javascript:doit('tp8','pf8');"></td><td>H4K8ac</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp8" name="histone_class" value="H4K91ac" onclick="javascript:doit('tp8','pf8');"></td><td>H4K91ac</td>
										<td></td><td></td>
										</tr>
		                            </table>
		                        </div>
		                    </div>
		                   
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d1" onclick="openShutManager(this,'box1');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Accessible Chromatin:</font>
		                        <font id="pf1">0 options selected</font><a href="${base.contextPath}/view/help#ATAC"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		                        <div id="box1" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa1" onclick="sa('box1','tp1','sa1','pf1')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td>
		                                    <td></td>
		                                </tr>
		                                <tr>
										<td><img src="${base.contextPath}/static/img/class.png" style="width: 20px;"></td><td>Tissue type</td>
										<td></td><td></td>
										</tr>
		                                <tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Adipose" onclick="javascript:doit('tp1','pf1');"></td><td>Adipose</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Artery" onclick="javascript:doit('tp1','pf1');"></td><td>Artery</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Blood" onclick="javascript:doit('tp1','pf1');"></td><td>Blood</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Bone" onclick="javascript:doit('tp1','pf1');"></td><td>Bone</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Bone_marrow" onclick="javascript:doit('tp1','pf1');"></td><td>Bone_marrow</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Brain" onclick="javascript:doit('tp1','pf1');"></td><td>Brain</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Breast" onclick="javascript:doit('tp1','pf1');"></td><td>Breast</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Bronchial" onclick="javascript:doit('tp1','pf1');"></td><td>Bronchial</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Catilage" onclick="javascript:doit('tp1','pf1');"></td><td>Catilage</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Cervix" onclick="javascript:doit('tp1','pf1');"></td><td>Cervix</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Colon" onclick="javascript:doit('tp1','pf1');"></td><td>Colon</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Connective_tissue" onclick="javascript:doit('tp1','pf1');"></td><td>Connective_tissue</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Coronary_artery_smooth_muscle" onclick="javascript:doit('tp1','pf1');"></td><td>Coronary_artery_smooth_muscle</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Cortex" onclick="javascript:doit('tp1','pf1');"></td><td>Cortex</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Cortical_Neurons" onclick="javascript:doit('tp1','pf1');"></td><td>Cortical_Neurons</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Cytokine-secreting_T_helper_cell_counterparts" onclick="javascript:doit('tp1','pf1');"></td><td>Cytokine-secreting_T_helper_cell_counterparts</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Embryo" onclick="javascript:doit('tp1','pf1');"></td><td>Embryo</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Epithelial" onclick="javascript:doit('tp1','pf1');"></td><td>Epithelial</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Fibroblast" onclick="javascript:doit('tp1','pf1');"></td><td>Fibroblast</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Forebrain" onclick="javascript:doit('tp1','pf1');"></td><td>Forebrain</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Foreskin" onclick="javascript:doit('tp1','pf1');"></td><td>Foreskin</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Frontal" onclick="javascript:doit('tp1','pf1');"></td><td>Frontal</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Gastrointestinal_stromal" onclick="javascript:doit('tp1','pf1');"></td><td>Gastrointestinal_stromal</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Glioblastoma" onclick="javascript:doit('tp1','pf1');"></td><td>Glioblastoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Hematopoietic" onclick="javascript:doit('tp1','pf1');"></td><td>Hematopoietic</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="iPSC" onclick="javascript:doit('tp1','pf1');"></td><td>iPSC</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="keratinocyte" onclick="javascript:doit('tp1','pf1');"></td><td>keratinocyte</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Kidney" onclick="javascript:doit('tp1','pf1');"></td><td>Kidney</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Lung" onclick="javascript:doit('tp1','pf1');"></td><td>Lung</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Lymphoblastoid" onclick="javascript:doit('tp1','pf1');"></td><td>Lymphoblastoid</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="MA9_leukemia_xenograft" onclick="javascript:doit('tp1','pf1');"></td><td>MA9_leukemia_xenograft</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Mammary_Gland" onclick="javascript:doit('tp1','pf1');"></td><td>Mammary_Gland</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Myeloid" onclick="javascript:doit('tp1','pf1');"></td><td>Myeloid</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Neuron" onclick="javascript:doit('tp1','pf1');"></td><td>Neuron</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Other" onclick="javascript:doit('tp1','pf1');"></td><td>Other</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Ovarian" onclick="javascript:doit('tp1','pf1');"></td><td>Ovarian</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Pancreas" onclick="javascript:doit('tp1','pf1');"></td><td>Pancreas</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Peripheral_blood" onclick="javascript:doit('tp1','pf1');"></td><td>Peripheral_blood</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Prostate" onclick="javascript:doit('tp1','pf1');"></td><td>Prostate</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Sarcomas" onclick="javascript:doit('tp1','pf1');"></td><td>Sarcomas</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Skin" onclick="javascript:doit('tp1','pf1');"></td><td>Skin</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Sperm" onclick="javascript:doit('tp1','pf1');"></td><td>Sperm</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Testicle" onclick="javascript:doit('tp1','pf1');"></td><td>Testicle</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Tonsil" onclick="javascript:doit('tp1','pf1');"></td><td>Tonsil</td>
										</tr>
										<tr>
										<td><img src="${base.contextPath}/static/img/class.png" style="width: 20px;"></td><td>Cancer type</td>
										<td></td><td></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Adenocarcinoma" onclick="javascript:doit('tp1','pf1');"></td><td>Adenocarcinoma</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Asthma" onclick="javascript:doit('tp1','pf1');"></td><td>Asthma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Branchio-Oculo-Facial_Syndrome" onclick="javascript:doit('tp1','pf1');"></td><td>Branchio-Oculo-Facial_Syndrome</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Breast_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Breast_cancer</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="CAID_Syndrome" onclick="javascript:doit('tp1','pf1');"></td><td>CAID_Syndrome</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Cancer</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Cervix_adenocarcinoma" onclick="javascript:doit('tp1','pf1');"></td><td>Cervix_adenocarcinoma</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Chordoma" onclick="javascript:doit('tp1','pf1');"></td><td>Chordoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Colon_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Colon_cancer</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Colorectal_carcinoma" onclick="javascript:doit('tp1','pf1');"></td><td>Colorectal_carcinoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Coronary_artery_disease" onclick="javascript:doit('tp1','pf1');"></td><td>Coronary_artery_disease</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Cultured_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Cultured_cancer</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Deficient_thoracic_sarcomas" onclick="javascript:doit('tp1','pf1');"></td><td>Deficient_thoracic_sarcomas</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Endometrial_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Endometrial_cancer</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Epstein-Barr_Virus" onclick="javascript:doit('tp1','pf1');"></td><td>Epstein-Barr_Virus</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Erythroleukemic" onclick="javascript:doit('tp1','pf1');"></td><td>Erythroleukemic</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Ewing_sarcoma" onclick="javascript:doit('tp1','pf1');"></td><td>Ewing_sarcoma</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Fibroscarcoma" onclick="javascript:doit('tp1','pf1');"></td><td>Fibroscarcoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Frontotemporal_degeneration" onclick="javascript:doit('tp1','pf1');"></td><td>Frontotemporal_degeneration</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Gastrointestinal_stromal_tumor" onclick="javascript:doit('tp1','pf1');"></td><td>Gastrointestinal_stromal_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Glioblastoma_caner" onclick="javascript:doit('tp1','pf1');"></td><td>Glioblastoma_caner</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Hemoglobinopathies" onclick="javascript:doit('tp1','pf1');"></td><td>Hemoglobinopathies</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="HIV-1" onclick="javascript:doit('tp1','pf1');"></td><td>HIV-1</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Human_Synovial_sarcoma" onclick="javascript:doit('tp1','pf1');"></td><td>Human_Synovial_sarcoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Hypogammaglobulinemia" onclick="javascript:doit('tp1','pf1');"></td><td>Hypogammaglobulinemia</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Leukemia" onclick="javascript:doit('tp1','pf1');"></td><td>Leukemia</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Lung_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Lung_cancer</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Lymphoma" onclick="javascript:doit('tp1','pf1');"></td><td>Lymphoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Medulloblastoma" onclick="javascript:doit('tp1','pf1');"></td><td>Medulloblastoma</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Melanoma" onclick="javascript:doit('tp1','pf1');"></td><td>Melanoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Myeloma" onclick="javascript:doit('tp1','pf1');"></td><td>Myeloma</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Neuroblastoma" onclick="javascript:doit('tp1','pf1');"></td><td>Neuroblastoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Normal" onclick="javascript:doit('tp1','pf1');"></td><td>Normal</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Osteosarcoma" onclick="javascript:doit('tp1','pf1');"></td><td>Osteosarcoma</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Other_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Other_cancer</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Ovarion_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Ovarion_cancer</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Pancreatic_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Pancreatic_cancer</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Prostate_cancer" onclick="javascript:doit('tp1','pf1');"></td><td>Prostate_cancer</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Sarcoma" onclick="javascript:doit('tp1','pf1');"></td><td>Sarcoma</td>
										<td><input type="checkbox" class="tp1" name="atac_class" value="Systemic_Lupus_Erythematosus" onclick="javascript:doit('tp1','pf1');"></td><td>Systemic_Lupus_Erythematosus</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp1" name="atac_class" value="The_Yellow_fever" onclick="javascript:doit('tp1','pf1');"></td><td>The_Yellow_fever</td>
										<td></td><td></td>
										</tr>

		                                
		                            </table>
		                        </div>
		                    </div>
		                    
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d4" onclick="openShutManager(this,'box4');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Enhancer:</font>
		                        <font id="pf4">0 options selected</font><a href="${base.contextPath}/view/help#Enhancer"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box4" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa4" onclick="sa('box4','tp4','sa4','pf4')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td>
		                                    <td></td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp4" name="enhancer_class" value="SEdb" onclick="javascript:doit('tp4','pf4');"></td><td>SEdb</td>
		                                    <td><input type="checkbox" class="tp4" name="enhancer_class" value="ENdb" onclick="javascript:doit('tp4','pf4');"></td><td>ENdb</td>
		                                </tr>
		                            </table>
		                        </div>
		                    </div>
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d5" onclick="openShutManager(this,'box5');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Super Enhancer:</font>
		                        <font id="pf5">0 options selected</font><a href="${base.contextPath}/view/help#Super_Enhancer"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box5" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa5" onclick="sa('box5','tp5','sa5','pf5')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td>
		                                    <td></td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp5" name="se_class" value="SEdb" onclick="javascript:doit('tp5','pf5');"></td><td>SEdb</td>
		                                    <td><input type="checkbox" class="tp5" name="se_class" value="dbSUPER" onclick="javascript:doit('tp5','pf5');"></td><td>dbSUPER</td>
		                                </tr>
		                            </table>
		                        </div>
		                    </div>
		                    
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d6" onclick="openShutManager(this,'box6');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">SNP:</font>
		                        <font id="pf6">0 options selected</font><a href="${base.contextPath}/view/help#SNP"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box6" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa6" onclick="sa('box6','tp6','sa6','pf6')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="eQTL_10kb" onclick="javascript:doit('tp6','pf6');"></td><td>eQTL_10kb</td>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="eQTL_15kb" onclick="javascript:doit('tp6','pf6');"></td><td>eQTL_15kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="eQTL_20kb" onclick="javascript:doit('tp6','pf6');"></td><td>eQTL_20kb</td>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="risk_10kb" onclick="javascript:doit('tp6','pf6');"></td><td>risk_10kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="risk_15kb" onclick="javascript:doit('tp6','pf6');"></td><td>risk_15kb</td>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="risk_20kb" onclick="javascript:doit('tp6','pf6');"></td><td>risk_20kb</td>
		                                </tr>
		                            </table>
		                        </div>
		                    </div>
		                    
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d7" onclick="openShutManager(this,'box7');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Methylation:</font>
		                        <font id="pf7">0 options selected</font><a href="${base.contextPath}/view/help#Methylation"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box7" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa7" onclick="sa('box7','tp7','sa7','pf7')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp7" name="methylation_class" value="Hyper_10kb" onclick="javascript:doit('tp7','pf7');"></td><td>Hyper_10kb</td>
		                                    <td><input type="checkbox" class="tp7" name="methylation_class" value="Hyper_15kb" onclick="javascript:doit('tp7','pf7');"></td><td>Hyper_15kb</td>
		                                 </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp7" name="methylation_class" value="Hyper_20kb" onclick="javascript:doit('tp7','pf7');"></td><td>Hyper_20kb</td>
		                                    <td><input type="checkbox" class="tp7" name="methylation_class" value="Hypo_10kb" onclick="javascript:doit('tp7','pf7');"></td><td>Hypo_10kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp7" name="methylation_class" value="Hypo_15kb" onclick="javascript:doit('tp7','pf7');"></td><td>Hypo_15kb</td>
		                                    <td><input type="checkbox" class="tp7" name="methylation_class" value="Hypo_20kb" onclick="javascript:doit('tp7','pf7');"></td><td>Hypo_20kb</td>
		                                </tr>
		                            </table>
		                        </div>
		                    </div>
		                    
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d3" onclick="openShutManager(this,'box3');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">LncRNA:</font>
		                        <font id="pf3">0 options selected</font><a href="${base.contextPath}/view/help#LncRNA"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box3" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa3" onclick="sa('box3','tp3','sa3','pf3')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td>
		                                    <td></td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Disease_2kb" onclick="javascript:doit('tp3','pf3');"></td><td>Disease_2kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Disease_5kb" onclick="javascript:doit('tp3','pf3');"></td><td>Disease_5kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Disease_10kb" onclick="javascript:doit('tp3','pf3');"></td><td>Disease_10kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Drug_2kb" onclick="javascript:doit('tp3','pf3');"></td><td>Drug_2kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Drug_5kb" onclick="javascript:doit('tp3','pf3');"></td><td>Drug_5kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Drug_10kb" onclick="javascript:doit('tp3','pf3');"></td><td>Drug_10kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Subcellular_Localization_2kb" onclick="javascript:doit('tp3','pf3');"></td><td>Subcellular_Localization_2kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Subcellular_Localization_5kb" onclick="javascript:doit('tp3','pf3');"></td><td>Subcellular_Localization_5kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Subcellular_Localization_10kb" onclick="javascript:doit('tp3','pf3');"></td><td>Subcellular_Localization_10kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Cancer_Hallmark_2kb" onclick="javascript:doit('tp3','pf3');"></td><td>Cancer_Hallmark_2kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Cancer_Hallmark_5kb" onclick="javascript:doit('tp3','pf3');"></td><td>Cancer_Hallmark_5kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Cancer_Hallmark_10kb" onclick="javascript:doit('tp3','pf3');"></td><td>Cancer_Hallmark_10kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="SmORF_2kb" onclick="javascript:doit('tp3','pf3');"></td><td>SmORF_2kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="SmORF_5kb" onclick="javascript:doit('tp3','pf3');"></td><td>SmORF_5kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="SmORF_10kb" onclick="javascript:doit('tp3','pf3');"></td><td>SmORF_10kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Exosome_2kb" onclick="javascript:doit('tp3','pf3');"></td><td>Exosome_2kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Exosome_5kb" onclick="javascript:doit('tp3','pf3');"></td><td>Exosome_5kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Exosome_10kb" onclick="javascript:doit('tp3','pf3');"></td><td>Exosome_10kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Cell_Marker_2kb" onclick="javascript:doit('tp3','pf3');"></td><td>Cell_Marker_2kb</td>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Cell_Marker_5kb" onclick="javascript:doit('tp3','pf3');"></td><td>Cell_Marker_5kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp3" name="lnc_class" value="Cell_Marker_10kb" onclick="javascript:doit('tp3','pf3');"></td><td>Cell_Marker_10kb</td>
		                                    <td></td><td></td>
		                                </tr>
		                                
		                            </table>
		                        </div>
		                    </div>
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d10" onclick="openShutManager(this,'box10');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">mRNA:</font>
		                        <font id="pf10">0 options selected</font><a href="${base.contextPath}/view/help#mRNA"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box10" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa10" onclick="sa('box10','tp10','sa10','pf10')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td>
		                                    <td></td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Cell_Marker_2kb" onclick="javascript:doit('tp10','pf10');"></td><td>Cell_Marker_2kb</td>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Cell_Marker_5kb" onclick="javascript:doit('tp10','pf10');"></td><td>Cell_Marker_5kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Cell_Marker_10kb" onclick="javascript:doit('tp10','pf10');"></td><td>Cell_Marker_2kb</td>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Goterm_2kb" onclick="javascript:doit('tp10','pf10');"></td><td>Goterm_2kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Goterm_5kb" onclick="javascript:doit('tp10','pf10');"></td><td>Goterm_5kb</td>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Goterm_10kb" onclick="javascript:doit('tp10','pf10');"></td><td>Goterm_10kb</td>
		                                </tr>
		                            </table>
		                        </div>
		                    </div>
		                    </div>		                
		                
		                
					</div>
					<div class="tab-pane fade" id="4">
					    <br>
					    <p style="margin-bottom: auto;font-size: 15px;margin-left: 20px;margin-top: -15px;color:red">You can quickly select your parameters </p>
		                <div style="margin-left: 16px;margin-top: 5px;" id="search_set">
	                    <select class="form-control" v-model='Data_Type' @change="list_Tissue_Name()" name="Data_Type" style="width: 60%;display: inline;">
							<option v-for="Data_Type in Data_Types" v-bind:value="Data_Type" >{{Data_Type}}</option>
						</select>
			            <label style="font-size: 18px;">Data Type</label>
			            <br>
			            <br>
			            <input type="text" id="tissuetype" name="Tissue_Name" autocomplete="off" class="selectpicker show-tick form-control" list="search_tissuetype" placeholder="Please select or input the tissue/set name" value="Adipose" style="width: 60%;display: inline;">
	                    <datalist v-model='Tissue_Name' name="Tissue_Name" id="search_tissuetype">
	                        <option v-for="Tissue_Name in Tissue_Names"  v-bind:value="Tissue_Name" >{{Tissue_Name}}</option>
	                    </datalist>
			            <label style="font-size: 18px;">Tissue</label>
			            </div>
					</div>
				</div>
				


                    
                    
                </div>
            </div>
        </div>
        <hr style="margin-bottom: 60px">
    </div>
</form>
<#include "nav/footer.ftl" />


<script src="${base.contextPath}/static/js/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
<script  src="${base.contextPath}/static/js/require.min.js"></script>
<script type="text/javascript" src="${base.contextPath}/static/js/bootstrap-multiselect.js"></script>
<script src="${base.contextPath}/static/js/analysis.js"></script>
<script type="text/javascript">
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})

    $("input[name=methodForm]").click(function(){
        var select = $(this).val();
        var obj = document.getElementsByName("methodForm");
        for (var i = 0; i < obj.length; i++) { //遍历Radio
            $('#content'+ obj[i].value).attr("style","display:none");
            if (obj[i].value == select) {
                $('#content'+ select).attr("style","display:block");
            }
        }
    });

</script>
<script type="text/javascript">
    $('#radio1').click();
    $("#page1").click(function(){
        $('#radio1').click();
    });
    $("#page2").click(function(){
        $('#radio2').click();
    });
    
    $('#radio3').click();
    $("#page3").click(function(){
        $('#radio3').click();
    });
    $("#page4").click(function(){
        $('#radio4').click();
    });
   
</script>
<script type="text/javascript">
 $(document).ready(function(){
  	$("#analysis_check").click(function(){
  	  var arr = ["hmm_class","tf_class","tcof_class","histone_class","atac_class","enhancer_class","se_class","snp_class","methylation_class","lnc_class","m_class"];
  	  var b=0;
  	  for(j=0; j<arr.length; j++){
  	  
  	      var r=document.getElementsByName(arr[j]); 
		  for(var i=0;i<r.length;i++){
		         if(r[i].checked){
		             b++;
		       }
		    }  
  	  }
  	  if(b==0){
	  	        alert("Error: Please select the option!");
		  	    return false;
	  	  }
  	
  	  var source = $("input:radio[name='source']:checked").val();
  	  if(source == "0"){
  	      var textarea = $("#input").val();
  	      var num = $("#input").val().split("\n").length;
	  	  if(textarea == ""){
	  	    alert("Error: Please input the region list!");
	  	    return false;
	  	    }
	  	  if(num > 2000){
	  	    alert("Error: Too many inputs, the best input is 2000 regions!");
	  	    return false;
	  	    }
  	  }else{
  	      var file = document.getElementById('file').value.length;
  	      var filename = document.getElementById('file').value;
  	      if(filename.split(".")[1] != "bed"){
  	         alert("Error: Please upload the file of bed format!");
	  	     return false;
  	      }
  	      if(file == 0){
  	         alert("Error: Please upload the file!");
	  	     return false;
  	      }else{
  	        var fileSize = $("#file")[0].files[0].size/(1024*1024);
  	        if(fileSize > 0.1){
		  	    alert("Error: File size is too large. Optimal size is 0.1M!");
		  	    return false;
	  	    } 
  	      }
  	  }
    });
    
    $("#analysis_example").click(function(){
        document.getElementById('input').value="chr16	84930270	84931045\r\n"+
"chr11	11246479	11247049\r\n"+
"chr16	88882243	88882678\r\n"+
"chr16	46390264	46390704\r\n"+
"chr10	11795367	11795717\r\n"+
"chr3	138609460	138609743\r\n"+
"chr18	4510671	4510951\r\n"+
"chr5	178547488	178548113\r\n"+
"chr12	124736453	124736901\r\n"+
"chrMT	328	946\r\n"+
"chr5	180594745	180595266\r\n"+
"chr12	124092831	124093328\r\n"+
"chr8	41827664	41828029\r\n"+
"chr2	148881621	148881884\r\n"+
"chr1	24804289	24804615\r\n"+
"chr11	118608557	118608999\r\n"+
"chr11	65215781	65216844\r\n"+
"chr5	14157441	14157693\r\n"+
"chrX	130509873	130510134\r\n"+
"chr5	2136808	2137293\r\n"+
"chr4	1042358	1043180\r\n"+
"chr10	130360179	130360708\r\n"+
"chr5	154371438	154371695\r\n"+
"chr17	39952738	39953151\r\n"+
"chr21	8234367	8234626\r\n"+
"chr16	89192831	89193125\r\n"+
"chr1	245464312	245464710\r\n"+
"chr14	76564664	76564932\r\n"+
"chr10	132364308	132364588\r\n"+
"chr6	145850250	145850510\r\n"+
"chr16	88979667	88980006\r\n"+
"chr22	22722023	22722270\r\n"+
"chr16	67028900	67029265\r\n"+
"chr10	44765402	44765849\r\n"+
"chr5	12834871	12835123\r\n"+
"chr6	143060819	143061104\r\n"+
"chr21	44810839	44811153\r\n"+
"chr13	76031833	76032090\r\n"+
"chrMT	3118	3784\r\n"+
"chr2	143346102	143346346\r\n"+
"chr20	6841184	6841408\r\n"+
"chr7	119566741	119566922\r\n"+
"chr5	173770659	173770896\r\n"+
"chr1	111592858	111593199\r\n"+
"chr19	38224377	38224726\r\n"+
"chr1	36273948	36274207\r\n"+
"chr19	52296840	52297375\r\n"+
"chr11	29365006	29365264\r\n"+
"chr7	4403251	4403515\r\n"+
"chrX	49843224	49843491\r\n"+
"chr3	128469733	128470301\r\n"+
"chr3	195799129	195799386\r\n"+
"chr22	32709969	32710219\r\n"+
"chr1	89524658	89524894\r\n"+
"chr11	58500774	58501031\r\n"+
"chr15	85896647	85897125\r\n"+
"chr7	1011085	1011587\r\n"+
"chr2	14539535	14539792\r\n"+
"chrX	44963373	44963631\r\n"+
"chr14	68736130	68736385\r\n"+
"chr1	21905059	21905414\r\n"+
"chr10	128583454	128583712\r\n"+
"chr16	71625158	71625646\r\n"+
"chr8	22396281	22396455\r\n"+
"chr2	1221715	1221971\r\n"+
"chr11	39176081	39176340\r\n"+
"chr9	136410506	136410722\r\n"+
"chr6	170173953	170174219\r\n"+
"chr8	102653721	102653914\r\n"+
"chr10	128219321	128219603\r\n"+
"chr22	25218597	25218856\r\n"+
"chr22	16601341	16601533\r\n"+
"chr11	66017386	66017745\r\n"+
"chrMT	16118	16537\r\n"+
"chr9	129454422	129454772\r\n"+
"chr20	62064981	62065217\r\n"+
"chr1	174797450	174797720\r\n"+
"chr22	20858180	20858431\r\n"+
"chr21	8219975	8220207\r\n"+
"chr21	27569313	27569571\r\n"+
"chr7	32891679	32892195\r\n"+
"chr14	77221308	77221620\r\n"+
"chr15	98834990	98835324\r\n"+
"chrX	118480380	118480638\r\n"+
"chr17	21235896	21236349\r\n"+
"chr18	13589943	13590265\r\n"+
"chr7	74496397	74496637\r\n"+
"chr8	13506437	13506693\r\n"+
"chr5	89504380	89504639\r\n"+
"chr3	171893602	171893860\r\n"+
"chr6	31652262	31652516\r\n"+
"chrGL000225.1	67166	67414\r\n"+
"chr6	157774111	157774314\r\n"+
"chr1	200487771	200488007\r\n"+
"chr22	21867715	21868013\r\n"+
"chr9	130334667	130334896\r\n"+
"chr2	46877573	46877875\r\n"+
"chr8	78221603	78221862\r\n"+
"chr3	72253923	72254181\r\n"+
"chr15	45461751	45462019\r\n"+
"chrMT	15049	15812\r\n"+
"chr13	42040466	42040712\r\n"+
"chr7	100428720	100428912\r\n"+
"chr2	2295540	2295877\r\n"+
"chr21	24899120	24899381\r\n"+
"chr8	1833317	1833551\r\n"+
"chr1	184593166	184593421\r\n"+
"chr9	114689852	114690193\r\n"+
"chr1	111755005	111755214\r\n"+
"chr7	76998749	76999060\r\n"+
"chr1	46522623	46522825\r\n"+
"chr5	38831960	38832295\r\n"+
"chr6	22756495	22756753\r\n"+
"chr2	198277485	198277745\r\n"+
"chr13	71858787	71859026\r\n"+
"chr11	41280949	41281209\r\n"+
"chr10	130404230	130404559\r\n"+
"chr21	37073279	37073651\r\n"+
"chr1	30732109	30732425\r\n"+
"chr21	45555272	45555539\r\n"+
"chrX	906188	906515\r\n"+
"chr17	73192634	73192874\r\n"+
"chr14	103356951	103357150\r\n"+
"chr1	20446383	20446628\r\n"+
"chr14	59417122	59417378\r\n"+
"chr3	9268171	9268428\r\n"+
"chrX	96580165	96580329\r\n"+
"chr9	69134836	69135094\r\n"+
"chr11	46976290	46976549\r\n"+
"chr8	142990910	142991290\r\n"+
"chr9	93576657	93576934\r\n"+
"chrX	147419764	147420038\r\n"+
"chr7	15495703	15495962\r\n"+
"chr6	20168378	20168643\r\n"+
"chr2	190648676	190649073\r\n"+
"chr6	132409793	132410047\r\n"+
"chrMT	0	177\r\n"+
"chr6	37644026	37644238\r\n"+
"chr3	157071797	157071978\r\n"+
"chr9	34665549	34665749\r\n"+
"chr3	66618796	66619052\r\n"+
"chr3	197913210	197913472\r\n"+
"chr2	212736797	212737057\r\n"+
"chr3	196694773	196695512\r\n"+
"chr3	153318898	153319251\r\n"+
"chr22	19727059	19727836\r\n"+
"chr16	86703136	86703499\r\n"+
"chr11	910371	910691\r\n"+
"chrX	79957040	79957287\r\n"+
"chr6	33028988	33029179\r\n"+
"chr17	80980439	80980719\r\n"+
"chr7	99276923	99277179\r\n"+
"chr13	100451594	100451847\r\n"+
"chrX	154595627	154595889\r\n"+
"chr4	38663894	38664169\r\n"+
"chr4	29405009	29405259\r\n"+
"chr13	48493206	48493464\r\n"+
"chr16	46400740	46400998\r\n"+
"chr20	57090752	57091273\r\n"+
"chr7	150999484	150999761\r\n"+
"chr8	30549657	30549929\r\n"+
"chr1	94253531	94253787\r\n"+
"chr3	4460477	4460733\r\n"+
"chr2	96894269	96894846\r\n"+
"chr8	40760581	40760828\r\n"+
"chr2	112630836	112631092\r\n"+
"chr9	23679087	23679343\r\n"+
"chr1	145647728	145647979\r\n"+
"chr1	36126703	36127108\r\n"+
"chr19	5996164	5996402\r\n"+
"chr1	110576595	110576761\r\n"+
"chr4	19592010	19592264\r\n"+
"chr6	83430912	83431243\r\n"+
"chr5	179550400	179550686\r\n"+
"chr4	190122710	190122973\r\n"+
"chr5	93581738	93582055\r\n"+
"chr10	110040819	110041071\r\n"+
"chr10	7378590	7378855\r\n"+
"chr17	42421825	42422024\r\n"+
"chr9	123607355	123607615\r\n"+
"chr7	68147242	68147408\r\n"+
"chr4	92653197	92653454\r\n"+
"chr15	29978635	29978892\r\n"+
"chr1	2483957	2484188\r\n"+
"chr11	236440	236688\r\n"+
"chr14	104724562	104724780\r\n"+
"chr2	318860	319453\r\n"+
"chr1	161390113	161390351\r\n"+
"chr17	82212638	82212890\r\n"+
"chrX	74744800	74744968\r\n"+
"chrX	47462846	47463095\r\n"+
"chr1	112881281	112881526\r\n"+
"chr1	15314673	15314913\r\n"+
"chr4	148059865	148060026\r\n"+
"chrKI270438.1	109675	109841\r\n"+
"chr5	90529405	90529720\r\n"+
"chr9	128947487	128947798\r\n"+
"chr17	51641023	51641269\r\n"+
"chr1	160751693	160751949\r\n"+
"chr8	113355642	113355897\r\n"+
"chr6	151850294	151850454\r\n"+
"chr13	98110848	98111107\r\n"+
"chr16	34586850	34587136\r\n"+
"chr5	11817074	11817423\r\n"+
"chr20	63678543	63679011\r\n"+
"chr22	20495639	20496034\r\n"+
"chrX	43857041	43857290\r\n"+
"chr3	60560725	60560976\r\n"+
"chr1	34247317	34247592\r\n"+
"chr1	210490935	210491191\r\n"+
"chr4	55077626	55077932\r\n"+
"chr10	112913169	112913514\r\n"+
"chr8	144233355	144233554\r\n"+
"chr8	32786005	32786259\r\n"+
"chr7	122476938	122477175\r\n"+
"chr11	26079771	26079961\r\n"+
"chr11	22316197	22316367\r\n"+
"chrGL000225.1	50761	51017\r\n"+
"chr18	57435579	57435869\r\n"+
"chr3	10319776	10319946\r\n"+
"chrX	80809602	80809871\r\n"+
"chr11	8209905	8210148\r\n"+
"chr2	32916198	32916618\r\n"+
"chr2	10042966	10043460\r\n"+
"chr6	129888100	129888352\r\n"+
"chr5	84445110	84445278\r\n"+
"chr5	132619677	132619937\r\n"+
"chr5	125611463	125611699\r\n"+
"chr2	191532011	191532263\r\n"+
"chr13	87603968	87604227\r\n"+
"chr7	130809617	130809779\r\n"+
"chr17	43458149	43458400\r\n"+
"chr11	43631435	43631681\r\n"+
"chrMT	2381	2658\r\n"+
"chr12	127568596	127569130\r\n"+
"chr21	44983966	44984208\r\n"+
"chr5	31418151	31418419\r\n"+
"chr22	43205559	43205851\r\n"+
"chr5	103258561	103258784\r\n"+
"chrMT	12815	13196\r\n"+
"chr6	144631105	144631259\r\n"+
"chr3	33452014	33452270\r\n"+
"chr17	48646935	48647183\r\n"+
"chr18	25033578	25033892\r\n"+
"chr1	222617779	222617977\r\n"+
"chr11	92705424	92705661\r\n"+
"chr16	85613603	85613815\r\n"+
"chr20	20582042	20582297\r\n"+
"chr2	241218232	241218421\r\n"+
"chr6	3053805	3054002\r\n"+
"chr6	11036890	11037154\r\n"+
"chr12	50135005	50135211\r\n"+
"chr7	143380923	143381118\r\n"+
"chr9	128191277	128191510\r\n"+
"chr8	73746711	73747076\r\n"+
"chr5	37699232	37699486\r\n"+
"chr2	174599688	174599937\r\n"+
"chr21	45980563	45981069\r\n"+
"chr2	241823060	241823398\r\n"+
"chr3	194352005	194352371\r\n"+
"chr1	30894176	30894811\r\n"+
"chr8	41662977	41663234\r\n"+
"chr15	81867292	81867538\r\n"+
"chr2	154654877	154655101\r\n"+
"chr7	99180605	99181149\r\n"+
"chr6	33208451	33208662\r\n"+
"chr7	31743448	31743704\r\n"+
"chr6	18111150	18111406\r\n"+
"chr3	38295657	38295922\r\n"+
"chr1	237883657	237883871\r\n"+
"chr1	230871945	230872197\r\n"+
"chr12	1196735	1196964\r\n"+
"chr6	41546243	41546436\r\n"+
"chr19	58020757	58021052\r\n"+
"chr1	10037	10361\r\n"+
"chr8	97643928	97644206\r\n"+
"chr6	6821891	6822063\r\n"+
"chr10	7319661	7319899\r\n"+
"chr7	130248427	130248679\r\n"+
"chr2	51354168	51354418\r\n"+
"chr1	239689923	239690161\r\n"+
"chr9	94616797	94617047\r\n"+
"chr9	3039240	3039492\r\n"+
"chr12	58858172	58858381\r\n"+
"chr13	91348246	91348660\r\n"+
"chr5	156444092	156444292\r\n"+
"chr5	177980499	177980653\r\n"+
"chrX	155244500	155244755\r\n"+
"chr21	43262502	43262758\r\n"+
"chr13	20566937	20567164\r\n"+
"chrX	118053706	118053965\r\n"+
"chr10	85414935	85415166\r\n"+
"chr10	133689307	133689470\r\n"+
"chr18	80258304	80258561\r\n"+
"chrX	40014746	40015015\r\n"+
"chr2	26245038	26245384\r\n"+
"chr21	43918002	43918262\r\n"+
"chr21	45442854	45443201\r\n"+
"chr19	47112144	47112464\r\n"+
"chr4	154760234	154760389\r\n"+
"chr16	76850711	76851015\r\n"+
"chr11	32892947	32893242\r\n"+
"chr16	90082515	90082766\r\n"+
"chr10	83818796	83819043\r\n"+
"chrMT	6377	6590\r\n"+
"chrX	11214156	11214402\r\n"+
"chr12	124338908	124339123\r\n"+
"chr1	633904	634058\r\n"+
"chr7	67438487	67438741\r\n"+
"chr21	38743112	38743413\r\n"+
"chr2	173964092	173964323\r\n"+
"chr6	107013508	107013763\r\n"+
"chr2	64524264	64524497\r\n"+
"chr12	110109372	110109629\r\n"+
"chr6	26980189	26980439\r\n"+
"chr3	146754723	146754897\r\n"+
"chr19	1718065	1718283\r\n"+
"chr2	60881211	60881449\r\n"+
"chr12	46383511	46383717\r\n"+
"chr12	104443095	104443252\r\n"+
"chrX	14690568	14690811\r\n"+
"chr8	26853980	26854138\r\n"+
"chr2	143846235	143846482\r\n"+
"chr20	63948479	63948668\r\n"+
"chr7	150999852	151000132\r\n"+
"chr12	131576802	131577048\r\n"+
"chr10	132172952	132173202\r\n"+
"chr9	126651119	126651276\r\n"+
"chr6	4135831	4136049\r\n"+
"chr19	45374174	45374428\r\n"+
"chr10	132364656	132364934\r\n"+
"chr11	69917092	69917651\r\n"+
"chr2	101834193	101834343\r\n"+
"chr13	38090887	38091121\r\n"+
"chr10	127582356	127582542\r\n"+
"chr9	95091956	95092208\r\n"+
"chr5	143515207	143515455\r\n"+
"chr17	113131	113390\r\n"+
"chr15	44288305	44288544\r\n"+
"chr1	2848049	2848354\r\n"+
"chr1	29732351	29732504\r\n"+
"chr18	31943123	31943531\r\n"+
"chr11	68816788	68817086\r\n"+
"chr11	1844897	1845152\r\n"+
"chr19	3477489	3477890\r\n"+
"chr16	85595746	85596163\r\n"+
"chr16	81096285	81096585\r\n"+
"chr11	133738595	133739002\r\n"+
"chr19	7197708	7197865\r\n"+
"chr8	131543433	131543591\r\n"+
"chr13	113154290	113154456\r\n"+
"chrX	152872463	152872823\r\n"+
"chr7	132132333	132132490\r\n"+
"chr5	147259938	147260374\r\n"+
"chr22	44300001	44300234\r\n"+
"chr22	37328910	37329068\r\n"+
"chr15	70449047	70449220\r\n"+
"chr4	2243907	2244109\r\n"+
"chr1	148132107	148132360\r\n"+
"chrX	108425106	108425356\r\n"+
"chr2	159615493	159615689\r\n"+
"chr1	876125	876447\r\n"+
"chr4	104494917	104495072\r\n"+
"chrX	120015490	120015669\r\n"+
"chrKI270438.1	109326	109635\r\n"+
"chr9	118466838	118467003\r\n"+
"chr22	41798110	41798264\r\n"+
"chr2	192037622	192037791\r\n"+
"chr20	51295722	51295930\r\n"+
"chr20	16905599	16905749\r\n"+
"chr15	27088093	27088320\r\n"+
"chrMT	14299	14461\r\n"+
"chr22	23145113	23145405\r\n"+
"chr16	89489368	89489614\r\n"+
"chr12	48350869	48351031\r\n"+
"chr12	13965446	13965697\r\n"+
"chr1	208086858	208087087\r\n"+
"chr20	61029094	61029250\r\n"+
"chr14	101499359	101499615\r\n"+
"chr7	144264995	144265252\r\n"+
"chr16	59476423	59476583\r\n"+
"chr12	94133268	94133422\r\n"+
"chr15	66100786	66101043\r\n"+
"chr1	46616292	46616495\r\n"+
"chr7	101163597	101163835\r\n"+
"chr5	56815246	56815444\r\n"+
"chr12	25250937	25251101\r\n"+
"chr12	102523797	102523977\r\n"+
"chr16	89011484	89011890\r\n"+
"chr17	15563459	15563812\r\n"+
"chr9	133563877	133564234\r\n"+
"chr22	22508584	22508961\r\n"+
"chr5	5494738	5495009\r\n"+
"chr9	36393355	36393608\r\n"+
"chr5	82478515	82478772\r\n"+
"chr1	54926574	54926767\r\n"+
"chr1	50075822	50075974\r\n"+
"chr14	76347303	76347558\r\n"+
"chrX	87209373	87209611\r\n"+
"chrX	19707716	19707985\r\n"+
"chr6	144819882	144820042\r\n"+
"chr5	155482923	155483154\r\n"+
"chr4	34485040	34485301\r\n"+
"chr1	65018363	65018612\r\n"+
"chr14	84897923	84898168\r\n"+
"chr14	70467043	70467295\r\n"+
"chrX	46917750	46918008\r\n"+
"chr6	155047563	155047824\r\n"+
"chr3	196316905	196317174\r\n"+
"chr15	81878138	81878425\r\n"+
"chr12	105506625	105506783\r\n"+
"chr12	6827745	6827916\r\n"+
"chr11	66312054	66312235\r\n"+
"chr7	24573259	24573470\r\n"+
"chr7	105012540	105012834\r\n"+
"chr4	27100512	27100769\r\n"+
"chr16	30658308	30658489\r\n"+
"chr7	140924652	140924811\r\n"+
"chr5	151075860	151076051\r\n"+
"chr2	139339604	139339938\r\n"+
"chr17	22521371	22521534\r\n"+
"chr1	230266873	230267124\r\n"+
"chr1	22800653	22800900\r\n"+
"chr5	10348721	10348972\r\n"+
"chr3	115869178	115869428\r\n"+
"chr14	37583135	37583383\r\n"+
"chrX	56270283	56270435\r\n"+
"chr7	21075695	21075855\r\n"+
"chr4	126871327	126871477\r\n"+
"chr17	14046148	14046401\r\n"+
"chr9	133870652	133870941\r\n"+
"chr11	763030	763218\r\n"+
"chr7	50793427	50793673\r\n"+
"chr14	100624849	100625018\r\n"+
"chr19	7566094	7566349\r\n"+
"chrY	21112483	21112739\r\n"+
"chr3	173600083	173600331\r\n"+
"chr2	76563857	76564112\r\n"+
"chr14	57275407	57275567\r\n"+
"chr5	40797996	40798444\r\n"+
"chr3	36969621	36969811\r\n"+
"chr15	62898345	62898599\r\n"+
"chr14	75237321	75237471\r\n"+
"chr4	8263600	8263860\r\n"+
"chr17	79778126	79778313\r\n"+
"chr20	38674462	38674670\r\n"+
"chr7	148698687	148698853\r\n"+
"chrX	6245140	6245289\r\n"+
"chr9	73971621	73971870\r\n"+
"chr8	33125992	33126220\r\n"+
"chr4	100459096	100459351\r\n"+
"chr3	71997355	71997507\r\n"+
"chr13	97025050	97025304\r\n"+
"chr10	16488495	16488748\r\n"+
"chr5	93583182	93583385\r\n"+
"chr8	67976329	67976693\r\n"+
"chr5	181281573	181281930\r\n"+
"chr22	45269365	45269573\r\n"+
"chr20	59564635	59564914\r\n"+
"chr1	46247513	46247713\r\n"+
"chr17	81034864	81035117\r\n"+
"chr18	41660219	41660468\r\n"+
"chr12	108082	108240\r\n"+
"chr1	172947104	172947358\r\n"+
"chr21	46414468	46414893\r\n"+
"chr16	31032951	31033194\r\n"+
"chr9	124503929	124504166\r\n"+
"chrX	70998690	70998945\r\n"+
"chr7	35007464	35007719\r\n"+
"chr3	97168458	97168670\r\n"+
"chr3	131671346	131671597\r\n"+
"chr22	36893612	36893851\r\n"+
"chr6	20403655	20403942\r\n"+
"chr22	40007441	40007713\r\n"+
"chr16	46401449	46401614\r\n"+
"chr5	150656837	150657026\r\n"+
"chr8	82962455	82962651\r\n"+
"chr8	101956494	101956656\r\n"+
"chr4	84695413	84695601\r\n"+
"chr4	138833360	138833600\r\n"+
"chr20	36609178	36609334\r\n"+
"chr18	26103525	26103775\r\n"+
"chr16	19138400	19138649\r\n"+
"chr10	106195278	106195453\r\n"+
"chr3	196694467	196694685\r\n"+
"chr19	9791809	9792208\r\n"+
"chr6	43509815	43510005\r\n"+
"chr8	117814115	117814369\r\n"+
"chr17	10114601	10114854\r\n"+
"chr1	240148172	240148466\r\n"+
"chr9	123551871	123552023\r\n"+
"chr20	64287229	64287478\r\n"+
"chr16	88688159	88688359\r\n"+
"chr14	105477638	105477879\r\n"+
"chr8	102806448	102806666\r\n"+
"chr7	152436498	152436664\r\n"+
"chr21	43605375	43605656\r\n"+
"chr16	66925594	66925778\r\n"+
"chr6	143060540	143060777\r\n"+
"chrX	156030146	156030382\r\n"+
"chr7	100539088	100539332\r\n"+
"chr7	100100352	100100630\r\n"+
"chr2	213407546	213407775\r\n"+
"chr15	29573622	29573778\r\n"+
"chr7	65873234	65873400\r\n"+
"chr6	47031186	47031456\r\n"+
"chr20	56429568	56429781\r\n"+
"chr15	71199342	71199596\r\n"+
"chr12	127277469	127277717\r\n"+
"chrX	137114580	137114732\r\n"+
"chr7	72894499	72894759\r\n"+
"chr5	110306494	110306709\r\n"+
"chr13	79475864	79476103\r\n"+
"chrX	84349571	84349824\r\n"+
"chr9	120615168	120615330\r\n"+
"chr2	231098433	231098586\r\n"+
"chr9	135907312	135907494\r\n"+
"chr3	8737928	8738166\r\n"+
"chr1	212381574	212381831\r\n"+
"chr11	131069740	131069987\r\n"+
"chr7	155002844	155003020\r\n"+
"chr7	1415261	1415600\r\n"+
"chr16	88714141	88714321\r\n"+
"chr16	49855541	49855781\r\n"+
"chr10	132331886	132332183\r\n"+
"chr3	155526290	155526526\r\n"+
"chr2	184213602	184213848\r\n"+
"chr10	65051631	65051835\r\n"+
"chr10	113159721	113159870\r\n"+
"chr15	89324949	89325258\r\n"+
"chr10	35336931	35337184\r\n"+
"chrX	46748438	46748694\r\n"+
"chr3	84498502	84498727\r\n"+
"chr2	28013602	28013856\r\n"+
"chr2	242183306	242183563\r\n"+
"chr2	195976662	195976905\r\n"+
"chr20	54613159	54613367\r\n"+
"chr18	38565852	38566070\r\n"+
"chr14	39857140	39857388\r\n"+
"chr1	101223643	101223891\r\n"+
"chr4	101347514	101347688\r\n"+
"chr1	32272687	32273101\r\n"+
"chr7	51477403	51477782\r\n"+
"chr17	78540888	78541065\r\n"+
"chr19	18770722	18770981\r\n"+
"chr14	104093718	104093886\r\n"+
"chrMT	11157	11310\r\n"+
"chr12	76144295	76144458\r\n"+
"chr1	178411038	178411291\r\n"+
"chr11	12330729	12330970\r\n"+
"chrY	7421604	7421839\r\n"+
"chrX	36698912	36699075\r\n"+
"chr9	2823927	2824076\r\n"+
"chr8	50975829	50976072\r\n"+
"chr6	114713798	114713947\r\n"+
"chr3	95316939	95317195\r\n"+
"chr8	123396478	123396685\r\n"+
"chr16	30675644	30675796\r\n"+
"chr12	121750444	121751014\r\n"+
"chr7	98242909	98243232\r\n"+
"chr7	92133977	92134189\r\n"+
"chr19	14163362	14163604\r\n"+
"chrX	63854608	63854852\r\n"+
"chr8	133541127	133541278\r\n"+
"chr7	31212620	31212768\r\n"+
"chr6	16107033	16107283\r\n"+
"chr6	137646681	137646829\r\n"+
"chr3	6253292	6253442\r\n"+
"chr14	38993798	38994048\r\n"+
"chr13	22897135	22897360\r\n"+
"chrX	153673242	153673412\r\n"+
"chr6	37169368	37169572\r\n"+
"chr16	3004558	3005002\r\n"+
"chr8	41184912	41185063\r\n"+
"chr8	4107997	4108196\r\n"+
"chr1	88925592	88925760\r\n"+
"chr13	112526464	112526710\r\n"+
"chr17	63550094	63550295\r\n"+
"chr18	12947785	12948242\r\n"+
"chr15	99427702	99427872\r\n"+
"chr6	468923	469090\r\n"+
"chr12	48957522	48957721\r\n"+
"chr21	10379102	10379288\r\n"+
"chr6	169840042	169840596\r\n"+
"chr9	31597577	31597729\r\n"+
"chr4	180253360	180253509\r\n"+
"chr2	56794718	56794963\r\n"+
"chr11	25402429	25402681\r\n"+
"chr10	106772469	106772619\r\n"+
"chr1	6613426	6613576\r\n"+
"chr9	100968946	100969184\r\n"+
"chr8	89371492	89371644\r\n"+
"chr8	21521696	21521847\r\n"+
"chr5	8788656	8788804\r\n"+
"chr3	72082909	72083057\r\n"+
"chr2	134381486	134381643\r\n"+
"chr12	13916612	13916858\r\n"+
"chr11	9675214	9675434\r\n"+
"chr2	170770864	170771169\r\n"+
"chr11	116130819	116130986\r\n"+
"chr9	111561008	111561254\r\n"+
"chr5	10545165	10545416\r\n"+
"chr4	112144691	112144854\r\n"+
"chr3	5239358	5239575\r\n"+
"chr3	38084353	38084571\r\n"+
"chr2	109170191	109170349\r\n"+
"chr17	17656130	17656375\r\n"+
"chr15	85897301	85897557\r\n"+
"chr16	85567120	85567282\r\n"+
"chr5	150284404	150284670\r\n"+
"chr2	88181074	88181320\r\n"+
"chr9	125746858	125747047\r\n"+
"chr7	65038207	65038408\r\n"+
"chr5	146203684	146204026\r\n"+
"chr14	104815578	104815755\r\n"+
"chr8	100310078	100310231\r\n"+
"chr5	119994914	119995065\r\n"+
"chr4	82529976	82530229\r\n"+
"chr3	23805790	23805956\r\n"+
"chr2	86223635	86223879\r\n"+
"chr11	121765705	121765859\r\n"+
"chr7	99143989	99144182\r\n"+
"chr9	138129077	138129226\r\n"+
"chr5	100678129	100678416\r\n"+
"chr2	242088432	242088609\r\n"+
"chr2	233225695	233225847\r\n"+
"chr2	148102676	148102831\r\n"+
"chr18	6841065	6841267\r\n"+
"chr12	79977909	79978159\r\n"+
"chr1	53946615	53946891\r\n"+
"chr2	127589509	127589683\r\n"+
"chrX	122522454	122522708\r\n"+
"chr8	80942887	80943139\r\n"+
"chr7	113983382	113983532\r\n"+
"chr5	56928954	56929120\r\n"+
"chr4	133897904	133898153\r\n"+
"chr17	3375120	3375361\r\n"+
"chr15	53772170	53772422\r\n"+
"chr14	84481129	84481378\r\n"+
"chr14	28108160	28108411\r\n"+
"chr13	94455363	94455515\r\n"+
"chr11	124283116	124283288\r\n"+
"chr10	54435907	54436155\r\n"+
"chr10	29009679	29009929\r\n"+
"chr6	50715600	50715840\r\n"+
"chr5	10761330	10761480\r\n"+
"chr1	26359339	26359579\r\n"+
"chr11	123003522	123003672\r\n"+
"chr8	98825518	98825711\r\n"+
"chr6	13274040	13274293\r\n"+
"chr4	186160731	186160905\r\n"+
"chr2	676786	677006\r\n"+
"chr20	51830608	51830872\r\n"+
"chr16	6214542	6214756\r\n"+
"chr15	64460980	64461174\r\n"+
"chr13	19809039	19809292\r\n"+
"chr1	155562665	155562936\r\n"+
"chr10	72625964	72626180\r\n"+
"chr16	69132106	69132320\r\n"+
"chr9	128127580	128127789\r\n"+
"chr1	26989772	26989920\r\n"+
"chr8	96262374	96262529\r\n"+
"chr3	73794799	73795044\r\n"+
"chr22	34396549	34396806\r\n"+
"chr2	181275825	181275973\r\n"+
"chr20	35989861	35990011\r\n"+
"chr19	42262123	42262372\r\n"+
"chr15	81000746	81000920\r\n"+
"chr15	29056618	29056768\r\n"+
"chr19	6532248	6532536\r\n"+
"chr20	29754839	29755074\r\n"+
"chrX	2610416	2610583\r\n"+
"chr13	20362708	20362916\r\n"+
"chr1	147540970	147541121\r\n"+
"chr9	95181032	95181183\r\n"+
"chr8	92473934	92474184\r\n"+
"chr8	71755256	71755506\r\n"+
"chr7	120523580	120523833\r\n"+
"chr4	160410486	160410639\r\n"+
"chr20	23959182	23959331\r\n"+
"chr1	78128739	78128894\r\n"+
"chr17	38389811	38389961\r\n"+
"chr15	49331987	49332220\r\n"+
"chr1	222595836	222596009\r\n"+
"chr1	200817069	200817322\r\n"+
"chrMT	13663	13855\r\n"+
"chrMT	4870	5017\r\n"+
"chr6	26520838	26521078\r\n"+
"chr9	133657355	133657544\r\n"+
"chr21	44316578	44316821\r\n"+
"chr21	42439596	42440066\r\n"+
"chr4	26447581	26447731\r\n"+
"chr19	14363709	14363897\r\n"+
"chr14	98371063	98371307\r\n"+
"chr13	20161098	20161318\r\n"+
"chr10	92690057	92690279\r\n"+
"chr6	127022113	127022261\r\n"+
"chr5	84356785	84356933\r\n"+
"chr3	192723751	192723901\r\n"+
"chr17	38257193	38257356\r\n"+
"chr9	85265025	85265316\r\n"+
"chr18	34381545	34381809\r\n"+
"chr1	39696545	39696694\r\n"+
"chr7	100895880	100896197\r\n"+
"chr5	420790	420978\r\n"+
"chr2	25129278	25129458\r\n"+
"chr19	10377670	10378011\r\n"+
"chr4	153469211	153469447\r\n"+
"chr21	37451563	37451813\r\n"+
"chr17	16939268	16939482\r\n"+
"chrX	149378534	149378685\r\n"+
"chr9	125250806	125251059\r\n"+
"chr7	100520228	100520474\r\n"+
"chr3	49955721	49955966\r\n"+
"chr2	77535070	77535326\r\n"+
"chr2	205021463	205021622\r\n"+
"chr2	134594755	134595003\r\n"+
"chr21	30191874	30192079\r\n"+
"chr1	59421474	59421623\r\n"+
"chr14	67900501	67900749\r\n"+
"chr1	148328205	148328458\r\n"+
"chr22	37227426	37227662\r\n"+
"chr16	85302412	85302565\r\n"+
"chr22	19725352	19725732\r\n"+
"chr20	3407535	3407936\r\n"+
"chr16	57802636	57802821\r\n"+
"chr19	3310421	3310647\r\n"+
"chrX	2691233	2691447\r\n"+
"chr7	141731742	141731975\r\n"+
"chr5	11069664	11069812\r\n"+
"chr22	39968357	39968604\r\n"+
"chr2	162002669	162002819\r\n"+
"chr16	19329635	19329795\r\n"+
"chr1	61589004	61589160\r\n"+
"chrKI270733.1	173095	173243\r\n"+
"chr7	106863721	106863880\r\n"+
"chr3	129121883	129122037\r\n"+
"chr3	10224621	10224789\r\n"+
"chr17	28903057	28903217\r\n"+
"chr15	101980762	101980922\r\n"+
"chr1	50960047	50960318\r\n"+
"chr12	117262385	117262566\r\n"+
"chr9	128689140	128689289\r\n"+
"chr19	56159201	56159415\r\n"+
"chr5	612045	612394\r\n"+
"chr19	54411112	54411415\r\n"+
"chr19	54133805	54133986\r\n"+
"chr17	78123601	78123810\r\n"+
"chr5	443193	443462\r\n"+
"chr7	72466127	72466275\r\n"+
"chr4	98995647	98995822\r\n"+
"chr22	39243221	39243373\r\n"+
"chr7	154656609	154656996\r\n"+
"chr17	73148247	73148410\r\n"+
"chrX	36336072	36336225\r\n"+
"chrX	36088385	36088533\r\n"+
"chr9	120659771	120659919\r\n"+
"chr8	120166794	120166943\r\n"+
"chr7	17845136	17845284\r\n"+
"chr6	106229347	106229552\r\n"+
"chr4	80731967	80732214\r\n"+
"chr4	78653094	78653247\r\n"+
"chr3	29387983	29388228\r\n"+
"chr2	222701179	222701422\r\n"+
"chr18	64858454	64858611\r\n"+
"chr15	60398874	60399037\r\n"+
"chr12	80976255	80976428\r\n"+
"chr11	90717406	90717556\r\n"+
"chr11	88941379	88941551\r\n"+
"chr10	95598825	95598975\r\n"+
"chr8	73189895	73190043\r\n"+
"chr4	72569096	72569291\r\n"+
"chr3	73227996	73228218\r\n"+
"chr2	3341563	3341781\r\n"+
"chr2	158835117	158835362\r\n"+
"chr20	8018309	8018548\r\n"+
"chr12	46125246	46125543\r\n"+
"chr11	95231186	95231353\r\n"+
"chrMT	2747	3010\r\n"+
"chrX	99207445	99207593\r\n"+
"chrX	9316659	9316808\r\n"+
"chrX	91434528	91434680\r\n"+
"chr8	118538053	118538302\r\n"+
"chr7	40370487	40370644\r\n"+
"chr6	18327442	18327592\r\n"+
"chr5	173117627	173117776\r\n"+
"chr5	161289688	161289924\r\n"+
"chr3	139114020	139114266\r\n"+
"chr2	186486598	186486757\r\n"+
"chr18	32928859	32929102\r\n"+
"chr17	58577803	58578016\r\n"+
"chr15	55940421	55940568\r\n"+
"chr14	72298322	72298469\r\n"+
"chr11	91369959	91370106\r\n"+
"chr11	42556845	42556995\r\n"+
"chr10	61623730	61623977\r\n"+
"chr3	96673176	96673437\r\n"+
"chr2	25340632	25340946\r\n"+
"chr22	47379102	47379302\r\n"+
"chr17	44086902	44087072\r\n"+
"chr16	85462523	85462684\r\n"+
"chr6	31777228	31777420\r\n"+
"chr19	14090130	14090284\r\n"+
"chr3	158105768	158105921\r\n"+
"chr17	81223422	81223601\r\n"+
"chr17	76840928	76841167\r\n"+
"chr11	29724541	29724912\r\n"+
"chr4	79352576	79352844\r\n"+
"chrX	40504329	40504478\r\n"+
"chr19	40848904	40849157\r\n"+
"chr19	38337047	38337208\r\n"+
"chr17	27360762	27360939\r\n"+
"chr11	17830436	17830639\r\n"+
"chr8	144313814	144313971\r\n"+
"chr17	73147943	73148101\r\n"+
"chr9	116941992	116942224\r\n"+
"chr8	52884840	52885024\r\n"+
"chr5	110620542	110620763\r\n"+
"chr2	230224397	230224651\r\n"+
"chr16	57729157	57729485\r\n"+
"chr1	200684623	200684773\r\n"+
"chr19	51704120	51704367\r\n"+
"chr16	3135015	3135201\r\n"+
"chr16	2038096	2038243\r\n"+
"chr14	104444242	104444402\r\n"+
"chr1	234724020	234724171\r\n"+
"chr19	47445999	47446220\r\n"+
"chr9	93936624	93936842\r\n"+
"chr9	135552930	135553105\r\n"+
"chr3	87739575	87740047\r\n"+
"chr12	106302449	106302644\r\n"+
"chr11	66856577	66856743\r\n"+
"chrX	15725879	15726028\r\n"+
"chrX	154962868	154963021\r\n"+
"chr9	74654215	74654379\r\n"+
"chr9	36347104	36347258\r\n"+
"chr7	95204907	95205061\r\n"+
"chr6	95416967	95417116\r\n"+
"chr5	163551029	163551281\r\n"+
"chr5	125374756	125375005\r\n"+
"chr5	117593965	117594113\r\n"+
"chr3	148614830	148615017\r\n"+
"chr3	100779486	100779737\r\n"+
"chr18	51541435	51541587\r\n"+
"chr16	64931235	64931383\r\n"+
"chr13	54844849	54845101\r\n"+
"chr11	25265214	25265363\r\n"+
"chr1	103827211	103827364\r\n"+
"chr7	101169880	101170069\r\n"+
"chr4	39639002	39639223\r\n"+
"chr2	47323077	47323230\r\n"+
"chr15	34366596	34366809\r\n"+
"chr13	113292151	113292335\r\n"+
"chr1	26201165	26201379\r\n"+
"chr12	32937347	32937497\r\n"+
"chr21	46228836	46229046\r\n"+
"chr16	993870	994256\r\n"+
"chr3	42600384	42600711\r\n"+
"chr2	1866564	1866837\r\n"+
"chr2	127623659	127623827\r\n"+
"chr7	157545090	157545375\r\n"+
"chr2	241896402	241896738\r\n"+
"chrX	149528491	149528750\r\n"+
"chrX	145853489	145853650\r\n"+
"chr8	141203376	141203580\r\n"+
"chr7	56268486	56268635\r\n"+
"chr6	31821745	31821892\r\n"+
"chr21	26855802	26855954\r\n"+
"chr13	98596016	98596256\r\n"+
"chr1	233947747	233947895\r\n"+
"chr10	63842570	63842717\r\n"+
"chr19	29420642	29421027\r\n"+
"chr7	140177150	140177348\r\n"+
"chr6	18154954	18155235\r\n"+
"chr20	32018170	32018391\r\n"+
"chr17	7350137	7350333\r\n"+
"chr11	119206533	119206720\r\n"+
"chr7	158595139	158595288\r\n"+
"chr5	172006461	172006634\r\n"+
"chr5	159050062	159050306\r\n"+
"chr13	95025192	95025560\r\n"+
"chr1	35192093	35192316\r\n"+
"chr1	32964102	32964261\r\n"+
"chr19	15813366	15813622\r\n"+
"chr12	104958421	104958752\r\n"+
"chr10	70816017	70816216\r\n"+
"chr19	7920820	7921104\r\n"+
"chr6	31652919	31653100\r\n"+
"chr21	46229230	46229389\r\n"+
"chrX	69624669	69624827\r\n"+
"chrX	5954485	5954633\r\n"+
"chrX	132044635	132044783\r\n"+
"chr9	101996500	101996655\r\n"+
"chr8	132595333	132595481\r\n"+
"chr7	106101330	106101572\r\n"+
"chr6	149520462	149520680\r\n"+
"chr5	109515658	109515806\r\n"+
"chr4	59731011	59731261\r\n"+
"chr4	147649064	147649296\r\n"+
"chr22	45024641	45024888\r\n"+
"chr2	234337753	234338003\r\n"+
"chr21	23436551	23436762\r\n"+
"chr19	32910151	32910386\r\n"+
"chr13	48220072	48220224\r\n"+
"chr13	105176224	105176475\r\n"+
"chr12	120830464	120830612\r\n"+
"chr11	56687257	56687509\r\n"+
"chr1	104507726	104507981\r\n"+
"chr10	53649578	53649832\r\n"+
"chr1	41806938	41807133\r\n"+
"chr10	123255001	123255183\r\n"+
"chrY	11329883	11330037\r\n"+
"chr19	4334847	4335187\r\n"+
"chr7	635880	636031\r\n"+
"chr6	34696798	34696968\r\n"+
"chr4	4673120	4673438\r\n"+
"chr18	79505516	79505798\r\n"+
"chr7	2314717	2314934\r\n"+
"chr9	808894	809042\r\n"+
"chr4	154014746	154014893\r\n"+
"chr19	48889234	48889483\r\n"+
"chr15	55993441	55993654\r\n"+
"chr14	79256035	79256233\r\n"+
"chr13	96851602	96851749\r\n"+
"chr11	61391145	61391292\r\n"+
"chr11	131633654	131633808\r\n"+
"chr4	140810898	140811179\r\n"+
"chr2	129759366	129759596\r\n"+
"chr19	36379017	36379215\r\n"+
"chr1	28668727	28668937\r\n"+
"chr11	75103803	75104041\r\n"+
"chr19	47423047	47423295\r\n"+
"chr6	158053505	158053888\r\n"+
"chr12	57846798	57846945\r\n"+
"chrX	76688751	76689002\r\n"+
"chrX	14284830	14284977\r\n"+
"chr9	39560222	39560369\r\n"+
"chr4	61062018	61062187\r\n"+
"chr4	48612392	48612540\r\n"+
"chr4	31056546	31056775\r\n"+
"chr19	29015406	29015574\r\n"+
"chr19	39162747	39162924\r\n"+
"chr13	113876140	113876311\r\n"+
"chr7	101163131	101163551\r\n"+
"chr4	7027911	7028162\r\n"+
"chr20	63653465	63653635\r\n"+
"chr20	50191337	50191487\r\n"+
"chr8	144513683	144513876\r\n"+
"chr15	45463388	45463536\r\n"+
"chrX	11758244	11758414\r\n"+
"chr6	111875721	111875870\r\n"+
"chr2	27494765	27494944\r\n"+
"chr16	34572938	34573191\r\n"+
"chr9	88534629	88535063\r\n"+
"chrX	1798475	1798682\r\n"+
"chr8	33390083	33390332\r\n"+
"chr8	14656479	14656637\r\n"+
"chr8	13229535	13229791\r\n"+
"chr6	123918859	123919125\r\n"+
"chr5	91150158	91150414\r\n"+
"chr5	83617883	83618106\r\n"+
"chr4	34961638	34961786\r\n"+
"chr4	126656192	126656442\r\n"+
"chr3	29338677	29338833\r\n"+
"chr3	19308767	19308918\r\n"+
"chr3	182199540	182199692\r\n"+
"chr2	48805540	48805694\r\n"+
"chr2	162715951	162716099\r\n"+
"chr20	7283588	7283839\r\n"+
"chr19	21630496	21630644\r\n"+
"chr17	13070407	13070611\r\n"+
"chr13	87560951	87561109\r\n"+
"chr10	58385272	58385422\r\n"+
"chrX	137012276	137012432\r\n"+
"chr9	15510482	15510752\r\n"+
"chr6	21160540	21160774\r\n"+
"chr5	129855555	129855792\r\n"+
"chr16	2537867	2538143\r\n"+
"chr16	85436795	85436946\r\n"+
"chr16	46388631	46388789\r\n"+
"chr8	142135269	142135416\r\n"+
"chr21	36966601	36966820\r\n"+
"chr19	50499258	50499408\r\n"+
"chr11	95790365	95790559\r\n"+
"chr1	119001975	119002122\r\n"+
"chr1	116373106	116373260\r\n"+
"chr20	62510608	62510757\r\n"+
"chr17	81984771	81985119\r\n"+
"chr16	81006732	81007011\r\n"+
"chr6	4708023	4708169\r\n"+
"chr3	38650065	38650216\r\n"+
"chr21	38424069	38424215\r\n"+
"chr20	25548866	25549040\r\n"+
"chr6	170052612	170052772\r\n"+
"chr3	12967687	12967840\r\n"+
"chr19	18281947	18282180\r\n"+
"chr17	81954816	81954963\r\n"+
"chr19	13118896	13119064\r\n"+
"chrX	25815104	25815387\r\n"+
"chr8	47659655	47659805\r\n";
    	$("#genesource1").prop("checked",true);
    	document.getElementById("box11").getElementsByClassName("tp11")[0].checked=true;
    	document.getElementById("pf11").innerHTML=" 1 options selected"
    });
 });
</script>
</body>
</html>