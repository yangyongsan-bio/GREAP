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
   	<style>
		#myTab3{
			height: 510px;
			overflow-y:auto;
		}
		.is_show {
		    display: none;
		}
		.btn.active, .btn:active {
			background-color: #007bff;
			color: #fff;
		}
		.btn-group-vertical > .btn, .btn-group > .btn {
			border: 1px #ccc solid;
		}
	</style>

</head>

<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />
<form action="../analysis" method="post" enctype="multipart/form-data" style="margin-top:110px">
<div style="display: none" >
    <!--隐藏将block改为none-->
    <input id="radiotf1" type="radio" name="radiotf" value="1" checked="" />
    <input id="radiotf2" type="radio" name="radiotf" value="2">
</div>
<div style="display: none" >
    <!--隐藏将block改为none-->
    <input id="radiotcof1" type="radio" name="radiotcof" value="1" checked="" />
    <input id="radiotcof2" type="radio" name="radiotcof" value="2">
</div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1">
                <div class="row">
                    <div class="col-lg-12 ">
                        <div class="form-group">
                            <div class="btn-group btn-group-toggle" data-toggle="buttons" style="margin-top: 7px;margin-bottom: 0;">
							  <label class="grch1 btn btn-sm" style="border-radius: 10px 0px 0px 10px;">
							    <input type="radio" name="grch" value="0" id="option1"> hg19
							  </label>
							  <label class="grch2 btn btn-sm active" style="border-radius: 0px 10px 10px 0px;">
							    <input type="radio" name="grch" value="1" id="option2" checked="checked"> hg38
							  </label>
							</div>
                            <br />
                            <label class="radio-inline" style="margin: 5px 0;">
                            	<input type="radio" id="genesource1" name="source" value="0" autocomplete="off" checked="">
                                Paste your data: 
                            </label>
                            <br />
                            <textarea class="form-control" rows="12" id="input" name="input" spellcheck="false"style="margin-bottom: 7px;" placeholder="Please enter the genomic region of hg38 version"></textarea>
                            <label class="radio-inline">
                                <input type="radio" id="genesource2" name="source" value="1" autocomplete="off" style="margin-bottom: 10px"> Upload a file:
                                <input id="file" type="file" name="file" style="display:inline;"/>
                            </label>
                            <a href="/Greap/data/example.bed"><button type="button" style="color: black;">Example of Upload File</button></a>
                            <br />
                            <img class="bed_warning" src="${base.contextPath}/static/img/warning.png" alt="" style="height: 20px;display: none;" />
                            <span class="bed_warning" style="display: none;" >Note: the uploaded file should not be too large!</span>
                            <br class="line_warning" />
                            <div>
                                <label for="min">
                                    Min<img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="The minimum number of regions in a reference set" style="width: 12px;">: <input type="number" name="min" id="min" placeholder="Unlimite" class="form-control" style="display: inline-block; width: 100px; height: 30px; margin-left: 15px;"/>
                                </label>
                                <label for="max">
                                    Max<img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="The maximum number of regions in a reference set" style="width: 12px;">: <input type="number" name="max" id="max" placeholder="Unlimite" class="form-control" style="display: inline-block; width: 100px; height: 30px; margin-left: 15px;"/>
                                </label>
                                <label for="pValue">
                                P-value:
                                <select class="form-control" id="pValue" name="pValue" style="display: inline-block; width: 100px; padding: 0;">
                                    <option value="0.05">5.00E-2</option>
                                    <option value="2">1.00E-2</option>
                                    <option value="3">1.00E-3</option>
                                    <option value="4">1.00E-4</option>
                                    <option value="5">1.00E-5</option>
                                    <option value="6" selected>1.00E-6</option>
                                    <option value="7">1.00E-7</option>
                                    <option value="8">1.00E-8</option>
                                    <option value="9">1.00E-9</option>
                                    <option value="10">1.00E-10</option>
                                    <option value="30">1.00E-30</option>
                                    <option value="50">1.00E-50</option>
                                </select>
                                <label for="max">
                            </div>

                            <br />
                            <ul id="myTab" class="nav nav-tabs">
								<li class="active">
									<a href="#1" data-toggle="tab" id="page1">Hypergeometric test</a>
								</li>
								<li>
								    <a href="#2" data-toggle="tab" id="page2">Locus overlap analysis <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Locus Overlap Analysis (LOLA) provides easy and automatable enrichment analysis for genomic region sets, thus facilitating the interpretation of functional genomics and epigenomics data" style="width: 12px;"></a>
								</li>
								<!--
								<li>
								    <a href="#3" data-toggle="tab" id="page3">OLOGRAM-MODL</a>
								</li>
		                        -->
	                            <div style="display: none" >
						            <!--隐藏将block改为none-->
						            <input id="radio1" type="radio" name="methodForm" value="1" checked="" />
						            <input id="radio2" type="radio" name="methodForm" value="2">
						            <!-- <input id="radio3" type="radio" name="methodForm" value="3"> -->
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
		                            <!--<img src="${base.contextPath}/static/img/hy.png" style="margin-left: 10px;">-->
		                            <br>
		                            <br>
		                            <label class="radio-inline"><input type="radio" name="outputForm" value="1" autocomplete="off" checked=""/> Applied on your submitted regions</label>
		                            <br>
		                            <br>
		                            <label class="radio-inline"><input type="radio" name="outputForm" value="0" autocomplete="off" />Applied on peaks of reference sets</label>
		                            <br>
		                            <br>
		                            <label class="radio-inline"><input type="radio" name="outputForm" value="2" autocomplete="off" /> Apply the minimum percentage of overlap on both data</label>
		                            <br>
		                            <br>
								</div>
								<div class="tab-pane fade" id="2">
								    <br/>
		                            <label class="radio-inline">
								    	<input type="radio" name="lolaRadio" id="lola1a" value="0" autocomplete="off" checked=""> Use pre-loaded universe
								    </label>
								    <br/>
								    <br/>
		                            <label class="radio-inline">
								    	<input type="radio" name="lolaRadio" id="lola1b" value="1" autocomplete="off"> Upload universe
								    </label>
								    <br/>
								    <br/>
									<label class="lola_1">Select background universe <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="The universe set is tested for overlap with the database, and the counts are used in the contingency tables that determine significance for your user sets." style="width: 12px;"></label>
						            <select class="form-control lola_1" name="universe" style="width: 50%;margin-bottom: 10px;">
						                <option value="" >activeDHS_universe.bed</option>
						            </select>
								    <label for="file2" class="lola_2 is_show">Choose universe file</label>
		                            <label class="lola_2 is_show">
								    	<input id="file2" type="file" name="lolaFile" style="display:inline;"/>
		                            </label>
                            		<a class="lola_2 is_show" href="/Greap/data/activeDHS_universe.bed"><button type="button" style="color: black;">Example of Upload File</button></a>
							    	<br />
		                            <img class="lola_2 is_show" src="${base.contextPath}/static/img/warning.png" alt="" style="width: 22px;" />
		                            <span class="lola_2 is_show">Note: the uploaded file should not be too large!</span>
		                            <br class="lola_2 is_show" style="margin-bottom: 12px;margin-top: 10px;" />
								</div>
		                        <!--
								<div class="tab-pane fade" id="3">
									<div class="radio">
									    <label>
									        <input type="radio" name="olo" id="olo1" value="olo1" checked> Use our reference<img src="/Greap/static/img/question.png" data-toggle="tooltip" title="" style="width: 12px;" data-original-title="Please select at least two reference sets">
									    </label>
									</div>
									<div class="radio">
									    <label>
									        <input type="radio" name="olo" id="olo2" value="olo2"> Upload your reference<img src="/Greap/static/img/question.png" data-toggle="tooltip" title="" style="width: 12px;" data-original-title="Please upload at least two bed files">
									    </label>
									</div>
								    <br/>
								    <div id="oloshow" hidden>
									    <label>Choose bed file</label>
			                            <label class="radio-inline">
									    	<input id="file3" type="file" name="file3"/>
			                            </label>
			                            </br>
			                            <label>Choose bed file</label>
			                            <label class="radio-inline">
									    	<input id="file4" type="file" name="file4"/>
			                            </label>
			                            <i class="icicon ion-plus-circled btn-lg" id="addfileon"></i>
		                            </div>
		                            <br/>
								</div> -->
							</div>
                            <button type="submit" class="btn btn-danger" id="analysis_check">Submit</button>
		                    <button type="reset" class="btn btn-danger" id="analysis_reset">Reset</button>
		                    <button type="button" class="btn btn-danger" id="analysis_example">Example</button>
                        </div>
                    </div>
                         
                </div>
            </div>
            <div class="col-lg-6 " id="sample">
                <ul id="myTab" class="nav nav-tabs">
					<li class="active">
					    <a href="#4" data-toggle="tab" id="page4" style="font-size: 28px;">Quick analysis </a> 
					</li>
					<li id="pagecustom">
						<a href="#myTab3" data-toggle="tab" id="page3" style="font-size: 28px;">Custom analysis</a>
					</li>
                    <div style="display: none;" >
			            <!--隐藏将block改为none-->
			            <input id="radio3" type="radio" name="analysisForm" value="1" />
			            <input id="radio4" type="radio" name="analysisForm" value="2" checked="" />
			            <!-- <input id="radioolo" type="radio" name="analysisForm" value="3"> -->
			        </div>
				</ul>
				<div id="myTabContent" class="tab-content">
				
					<div class="tab-pane fade in active" id="4">
					    <br>
					    <p style="margin-bottom: auto;font-size: 15px;margin-left: 20px;margin-top: -15px;color:red">You can quickly select your parameters </p>
		                <div style="margin-left: 16px;margin-top: 5px;" id="search_set">
	                    <select class="form-control" v-model='Data_Type' @change="list_Tissue_Name()" name="Data_Type" style="width: 60%;display: inline;">
							<option v-for="Data_Type in Data_Types" v-bind:value="Data_Type" >{{Data_Type}}</option>
						</select>
			            <label style="font-size: 18px;">DataClass</label>
			            <br>
			            <br>
			            <select class="form-control" v-model='Tissue_Name' name="Tissue_Name" style="width: 60%;display: inline;">
							<option v-for="Tissue_Name in Tissue_Names"  v-bind:value="Tissue_Name" >{{Tissue_Name}}</option>
						</select>
			            <label style="font-size: 18px;">SubClass</label>
			            </div>
			            
						<div style="width: 90%;margin-top: 50px;">
							<p style="color: red; font-size: 25px;margin-bottom: 10px;">*Tips</p>
							<p style="color: #06153e; font-size: 18px; text-align: justify;">
							    GREAP provides the TF TCF7L2 ChIP-seq data of HCT116 sample obtained from Cistrome database as input example list file and selects recommended category options for analysis. Users can also set different thresholds as needed for analysis, such as hypergeometric test p value and adjust p value. User can obtain the analysis results within a short time and download results (files and graphs) as needed.
							</p>
						</div>
						
						<div style="margin-top: -18px;">
							<label class="radio-inline" style="margin: 0;padding: 0;">
							    <input name="judge_email" value="1" type="checkbox" aria-label="Checkbox for following text input" />
							    Email notification: 
							    <img src="/Greap/static/img/question.png" data-toggle="tooltip" title="" style="width: 12px;" data-original-title="We will notify you by email when the analysis is complete.">
							    <input name="judge_email" value="0" type="checkbox" aria-label="Checkbox for following text input" style="display: none;" />
							    <input name="userId" value="" type="text" style="display: none;" />
							    <input id="email" name="userEmail" type="text" class="form-control" placeholder="Please enter your e-mail." style="width: 350px;" />
							</label>
						</div>

					</div>
					<div class="tab-pane fade" id="myTab3">
					    <br>
					    <!--<div class="col-lg-12">
		                    <table width="100%">
		                        <tr>
		                            <td></td>
		                            <td>
		                                <button type="button" href="#" type="button" class="btn btn-w3r" id="All" onclick="AllOpen(this,'box','box1','box2','box_2','box3','box4','box5','box6','box7','box8','box9','box10','box11','box12','box13','box14','box15','box16','box17','box18')" style="border: 1px solid #50d8af">
		                                    <span style="font-size: medium; ">Open all</span>
		                                </button>
		                                <button type="button" class="btn btn-w3r" onclick="CheckAll(this)" id="1"><span style="font-size: medium; ">Check all</span></button>
		                            </td>
		                        </tr>
		                    </table>
		                </div>-->
		                
		                <p style="margin-bottom: auto;font-size: 15px;margin-left: 20px;margin-top: -15px;color:red">You can select the background reference sets  you are interested in </p>
		                <div style="margin-left: -16px;">
		                <div class="col-lg-11" >
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d11" onclick="openShutManager(this,'box11');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">ChromHMM state:</font>
		                        <font id="pf11">0 options selected</font><a href="${base.contextPath}/view/help#ChromHMM"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		                        <div id="box11" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa11" onclick="sa('box11','tp11','sa11','pf11')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                <tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TssA" onclick="javascript:doit('tp11','pf11');"></td><td>TssA <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Active TSS" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TssAFlnk" onclick="javascript:doit('tp11','pf11');"></td><td>TssAFlnk <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Flanking Active TSS" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TxFlnk" onclick="javascript:doit('tp11','pf11');"></td><td>TxFlnk <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Transcr. at gene 5' and 3'" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="Tx" onclick="javascript:doit('tp11','pf11');"></td><td>Tx <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Strong transcription" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TxWk" onclick="javascript:doit('tp11','pf11');"></td><td>TxWk <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Weak transcription" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="EnhG" onclick="javascript:doit('tp11','pf11');"></td><td>EnhG <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Genic enhancers" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="Enh" onclick="javascript:doit('tp11','pf11');"></td><td>Enh <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Enhancers" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="ZNF_Rpts" onclick="javascript:doit('tp11','pf11');"></td><td>ZNF/Rpts <img src="${base.contextPath}/static/img/question.png"  title="ZNF genes & repeats" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="Het" onclick="javascript:doit('tp11','pf11');"></td><td>Het <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Heterochromatin" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="TssBiv" onclick="javascript:doit('tp11','pf11');"></td><td>TssBiv <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Bivalent/Poised TSS" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="BivFlnk" onclick="javascript:doit('tp11','pf11');"></td><td>BivFlnk <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Flanking Bivalent TSS/Enh" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="12_EnhBiv" onclick="javascript:doit('tp11','pf11');"></td><td>EnhBiv <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Bivalent Enhancer" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="ReprPC" onclick="javascript:doit('tp11','pf11');"></td><td>ReprPC <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Repressed PolyComb" style="width: 12px;"></td>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="ReprPCWk" onclick="javascript:doit('tp11','pf11');"></td><td>ReprPCWk <img src="${base.contextPath}/static/img/question.png" data-toggle="tooltip"   title="Weak Repressed PolyComb" style="width: 12px;"></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp11" name="hmm_class" value="Quies" onclick="javascript:doit('tp11','pf11');"></td><td>Quies <img src="${base.contextPath}/static/img/question.png"  data-toggle="tooltip"  title="Quiescent/Low" style="width: 12px;"></td>
		                                <td></td><td></td>
		                                </tr>
		                            </table>
		                            
		                        </div>
		                    </div>
		                
		                     <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d" onclick="openShutManager(this,'box');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Transcription factor:</font>
		                        <font id="pf">0 options selected</font><a href="${base.contextPath}/view/help#TF"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		                        <div id="box" style="display:none;" class="alert alert-info alert-dismissable">
		                            
		                            <ul id="myTab2" class="nav nav-tabs">
										<li class="active" id="radiotf1click">
											<a href="#21" data-toggle="tab">Cistrome</a>
										</li>
										<li id="radiotf2click">
										    <a href="#22" data-toggle="tab">Remap</a>
										</li>
									</ul>
									
		                            <div id="myTabContent2" class="tab-content">
										<div class="tab-pane fade in active" id="21">
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
										<div class="tab-pane fade" id="22">
											<table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
												<tr>
													<td><input type="checkbox" class="saa" onclick="sa('box','tpp','saa','pf')"></td>
													<td><font style="color: red">Select all</font></td>
													<td></td><td></td>
												</tr>
												<tr>
												<td><img src="${base.contextPath}/static/img/class.png" style="width: 16px;"></td><td>Tissue type</td>
												<td></td><td></td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Adipose" onclick="javascript:doit('tpp','pf');"></td><td>Adipose</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Adrenal_gland" onclick="javascript:doit('tpp','pf');"></td><td>Adrenal_gland</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Artery" onclick="javascript:doit('tpp','pf');"></td><td>Artery</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="biliary_tract" onclick="javascript:doit('tpp','pf');"></td><td>biliary_tract</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Blood_vessel" onclick="javascript:doit('tpp','pf');"></td><td>Blood_vessel</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Blood" onclick="javascript:doit('tpp','pf');"></td><td>Blood</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Bone_Marrow" onclick="javascript:doit('tpp','pf');"></td><td>Bone_Marrow</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Bone" onclick="javascript:doit('tpp','pf');"></td><td>Bone</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Brain" onclick="javascript:doit('tpp','pf');"></td><td>Brain</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Breast" onclick="javascript:doit('tpp','pf');"></td><td>Breast</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Bronchia" onclick="javascript:doit('tpp','pf');"></td><td>Bronchia</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Cervix" onclick="javascript:doit('tpp','pf');"></td><td>Cervix</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Chorion" onclick="javascript:doit('tpp','pf');"></td><td>Chorion</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Colon" onclick="javascript:doit('tpp','pf');"></td><td>Colon</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Corneal" onclick="javascript:doit('tpp','pf');"></td><td>Corneal</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Embryo" onclick="javascript:doit('tpp','pf');"></td><td>Embryo</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Epithelium" onclick="javascript:doit('tpp','pf');"></td><td>Epithelium</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Esophagus" onclick="javascript:doit('tpp','pf');"></td><td>Esophagus</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Eye" onclick="javascript:doit('tpp','pf');"></td><td>Eye</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Fallopian_tube" onclick="javascript:doit('tpp','pf');"></td><td>Fallopian_tube</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Foreskin" onclick="javascript:doit('tpp','pf');"></td><td>Foreskin</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Gastrocnemius_medialis" onclick="javascript:doit('tpp','pf');"></td><td>Gastrocnemius_medialis</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Gingiva" onclick="javascript:doit('tpp','pf');"></td><td>Gingiva</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Head_and_neck" onclick="javascript:doit('tpp','pf');"></td><td>Head_and_neck</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="heart" onclick="javascript:doit('tpp','pf');"></td><td>heart</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Islet" onclick="javascript:doit('tpp','pf');"></td><td>Islet</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="kidney" onclick="javascript:doit('tpp','pf');"></td><td>kidney</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Large_intestine" onclick="javascript:doit('tpp','pf');"></td><td>Large_intestine</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Liver" onclick="javascript:doit('tpp','pf');"></td><td>Liver</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Lung" onclick="javascript:doit('tpp','pf');"></td><td>Lung</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="lymphoid" onclick="javascript:doit('tpp','pf');"></td><td>lymphoid</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Muscle" onclick="javascript:doit('tpp','pf');"></td><td>Muscle</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Myotube" onclick="javascript:doit('tpp','pf');"></td><td>Myotube</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Nervous_System" onclick="javascript:doit('tpp','pf');"></td><td>Nervous_System</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Other" onclick="javascript:doit('tpp','pf');"></td><td>Other</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Pancreatic" onclick="javascript:doit('tpp','pf');"></td><td>Pancreatic</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Prostate" onclick="javascript:doit('tpp','pf');"></td><td>Prostate</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Proximal_tubule" onclick="javascript:doit('tpp','pf');"></td><td>Proximal_tubule</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="retina" onclick="javascript:doit('tpp','pf');"></td><td>retina</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Skin" onclick="javascript:doit('tpp','pf');"></td><td>Skin</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Spleen" onclick="javascript:doit('tpp','pf');"></td><td>Spleen</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Stomach" onclick="javascript:doit('tpp','pf');"></td><td>Stomach</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Testis" onclick="javascript:doit('tpp','pf');"></td><td>Testis</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Thymus" onclick="javascript:doit('tpp','pf');"></td><td>Thymus</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Thyroid_gland" onclick="javascript:doit('tpp','pf');"></td><td>Thyroid_gland</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Tonsil" onclick="javascript:doit('tpp','pf');"></td><td>Tonsil</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Umbilical_vein" onclick="javascript:doit('tpp','pf');"></td><td>Umbilical_vein</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="upper_aerodigestive_tract" onclick="javascript:doit('tpp','pf');"></td><td>upper_aerodigestive_tract</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="urinary_tract" onclick="javascript:doit('tpp','pf');"></td><td>urinary_tract</td>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Uterus" onclick="javascript:doit('tpp','pf');"></td><td>Uterus</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tpp" name="tf_class2" value="Vagina" onclick="javascript:doit('tpp','pf');"></td><td>Vagina</td>
												</tr>
											</table>

																						
										</div>
									</div>
		                            
		                            
		                        </div>
		                    </div>
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d2" onclick="openShutManager(this,'box2');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Transcription cofactor:</font>
		                        <font id="pf2">0 options selected</font><a href="${base.contextPath}/view/help#TcoF"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box2" style="display:none;" class="alert alert-info alert-dismissable">
			                        <ul class="nav nav-tabs" id="myTab">
									    <li class="active"  id="radiotcof1click"><a href="#tcof1p" data-toggle="tab">Cistrome</a></li>
									    <li  id="radiotcof2click"><a href="#tcof2p" data-toggle="tab">Remap</a></li>
									</ul>
									<div class="tab-content">
									    <div class="tab-pane active" id="tcof1p">
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
									    <div class="tab-pane" id="tcof2p">
									    	<table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
												<tr>
													<td><input type="checkbox" class="sa2a" onclick="sa('box2','tp2p','sa2a','pf2')"></td>
													<td><font style="color: red">Select all</font></td>
													<td></td><td></td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Adipose" onclick="javascript:doit('tp2p','pf2');"></td><td>Adipose</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="biliary_tract" onclick="javascript:doit('tp2p','pf2');"></td><td>biliary_tract</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Blood" onclick="javascript:doit('tp2p','pf2');"></td><td>Blood</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Bone_Marrow" onclick="javascript:doit('tp2p','pf2');"></td><td>Bone_Marrow</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Bone" onclick="javascript:doit('tp2p','pf2');"></td><td>Bone</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Brain" onclick="javascript:doit('tp2p','pf2');"></td><td>Brain</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Breast" onclick="javascript:doit('tp2p','pf2');"></td><td>Breast</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Bronchia" onclick="javascript:doit('tp2p','pf2');"></td><td>Bronchia</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Cervix" onclick="javascript:doit('tp2p','pf2');"></td><td>Cervix</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Colon" onclick="javascript:doit('tp2p','pf2');"></td><td>Colon</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Embryo" onclick="javascript:doit('tp2p','pf2');"></td><td>Embryo</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Esophagus" onclick="javascript:doit('tp2p','pf2');"></td><td>Esophagus</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Foreskin" onclick="javascript:doit('tp2p','pf2');"></td><td>Foreskin</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="heart" onclick="javascript:doit('tp2p','pf2');"></td><td>heart</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Kidney" onclick="javascript:doit('tp2p','pf2');"></td><td>Kidney</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Large_intestine" onclick="javascript:doit('tp2p','pf2');"></td><td>Large_intestine</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Liver" onclick="javascript:doit('tp2p','pf2');"></td><td>Liver</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Lung" onclick="javascript:doit('tp2p','pf2');"></td><td>Lung</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="lymphoid" onclick="javascript:doit('tp2p','pf2');"></td><td>lymphoid</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Muscle" onclick="javascript:doit('tp2p','pf2');"></td><td>Muscle</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Nervous_System" onclick="javascript:doit('tp2p','pf2');"></td><td>Nervous_System</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Other" onclick="javascript:doit('tp2p','pf2');"></td><td>Other</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Ovaries" onclick="javascript:doit('tp2p','pf2');"></td><td>Ovaries</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Pancreatic" onclick="javascript:doit('tp2p','pf2');"></td><td>Pancreatic</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Prostate" onclick="javascript:doit('tp2p','pf2');"></td><td>Prostate</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="retina" onclick="javascript:doit('tp2p','pf2');"></td><td>retina</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Skin" onclick="javascript:doit('tp2p','pf2');"></td><td>Skin</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Testis" onclick="javascript:doit('tp2p','pf2');"></td><td>Testis</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Umbilical_vein" onclick="javascript:doit('tp2p','pf2');"></td><td>Umbilical_vein</td>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="upper_aerodigestive_tract" onclick="javascript:doit('tp2p','pf2');"></td><td>upper_aerodigestive_tract</td>
												</tr>
												<tr>
												<td><input type="checkbox" class="tp2p" name="tcof_class2" value="Uterus" onclick="javascript:doit('tp2p','pf2');"></td><td>Uterus</td>
												</tr>
											</table>
									    </div>
									</div>
		                            
		                        </div>
		                    </div>
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d8" onclick="openShutManager(this,'box8');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Histone modification:</font>
		                        <font id="pf8">0 options selected</font><a href="${base.contextPath}/view/help#Histone"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
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
		                        <font style="font-size: 25px;">Accessible chromatin:</font>
		                        <font id="pf1">0 options selected</font><a href="${base.contextPath}/view/help#ATAC"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
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
		                        <font id="pf4">0 options selected</font><a href="${base.contextPath}/view/help#Enhancer"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
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
		                        <font style="font-size: 25px;">Super enhancer:</font>
		                        <font id="pf5">0 options selected</font><a href="${base.contextPath}/view/help#Super_Enhancer"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box5" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" id="seall" class="sa5" onclick="sa('box5','tp5','sa5','pf5')"></td>
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
		                        <font id="pf6">0 options selected</font><a href="${base.contextPath}/view/help#SNP"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box6" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa6" onclick="sa('box6','tp6','sa6','pf6')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="risk_15kb" onclick="javascript:doit('tp6','pf6');"></td><td>risk_15kb</td>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="risk_20kb" onclick="javascript:doit('tp6','pf6');"></td><td>risk_20kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp6" name="snp_class" value="risk_10kb" onclick="javascript:doit('tp6','pf6');"></td><td>risk_10kb</td>
		                                	<td></td>
		                                </tr>
		                                
		                            </table>
		                        </div>
		                    </div>
		                    
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d12" onclick="openShutManager(this,'box12');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">eQTL:</font>
		                        <font id="pf12">0 options selected</font><a href="${base.contextPath}/view/help#SNP"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
		                        <div id="box12" style="display:none;" class="alert alert-info alert-dismissable">
		                            <table class="table table-hover" style="background-color: white;margin-left: 2%;margin-top:2%; ">
		                                <tr>
		                                    <td><input type="checkbox" class="sa12" onclick="sa('box12','tp12','sa12','pf12')"></td>
		                                    <td><font style="color: red">Select all</font></td>
		                                    <td></td><td></td>
		                                </tr>
		                                <tr>
										<td><img src="${base.contextPath}/static/img/class.png" style="width: 20px;"></td><td>Cancer type</td>
										<td></td><td></td>
										</tr>
		                                <tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="ACC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>ACC_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="BLCA_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>BLCA_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="BRCA_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>BRCA_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="CESC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>CESC_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="CHOL_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>CHOL_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="COAD_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>COAD_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="DLBC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>DLBC_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="ESCA_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>ESCA_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="GBM_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>GBM_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="HNSC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>HNSC_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="KICH_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>KICH_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="KIRC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>KIRC_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="KIRP_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>KIRP_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="LAML_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>LAML_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="LGG_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>LGG_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="LIHC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>LIHC_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="LUAD_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>LUAD_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="LUSC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>LUSC_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="MESO_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>MESO_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="OV_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>OV_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="PAAD_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>PAAD_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="PCPG_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>PCPG_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="PRAD_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>PRAD_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="READ_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>READ_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="SARC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>SARC_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="SKCM_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>SKCM_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="STAD_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>STAD_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="TGCT_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>TGCT_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="THCA_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>THCA_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="THYM_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>THYM_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="UCEC_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>UCEC_tumor</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="UCS_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>UCS_tumor</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="UVM_tumor" onclick="javascript:doit('tp12','pf12');"></td><td>UVM_tumor</td>
										</tr>
										<tr>
										<td><img src="${base.contextPath}/static/img/class.png" style="width: 20px;"></td><td>Tissue type</td>
										<td></td><td></td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Adipose_Subcutaneous" onclick="javascript:doit('tp12','pf12');"></td><td>Adipose_Subcutaneous</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Adipose_Visceral_Omentum" onclick="javascript:doit('tp12','pf12');"></td><td>Adipose_Visceral_Omentum</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Adrenal_Gland" onclick="javascript:doit('tp12','pf12');"></td><td>Adrenal_Gland</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Artery_Aorta" onclick="javascript:doit('tp12','pf12');"></td><td>Artery_Aorta</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Artery_Coronary" onclick="javascript:doit('tp12','pf12');"></td><td>Artery_Coronary</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Artery_Tibial" onclick="javascript:doit('tp12','pf12');"></td><td>Artery_Tibial</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Amygdala" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Amygdala</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Anterior_cingulate_cortex_BA24" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Anterior_cingulate_cortex_BA24</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Caudate_basal_ganglia" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Caudate_basal_ganglia</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Cerebellar_Hemisphere" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Cerebellar_Hemisphere</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Cerebellum" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Cerebellum</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Cortex" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Cortex</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Frontal_Cortex_BA9" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Frontal_Cortex_BA9</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Hippocampus" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Hippocampus</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Hypothalamus" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Hypothalamus</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Nucleus_accumbens_basal_ganglia" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Nucleus_accumbens_basal_ganglia</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Putamen_basal_ganglia" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Putamen_basal_ganglia</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Spinal_cord_cervical_c-1" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Spinal_cord_cervical_c-1</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Brain_Substantia_nigra" onclick="javascript:doit('tp12','pf12');"></td><td>Brain_Substantia_nigra</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Breast_Mammary_Tissue" onclick="javascript:doit('tp12','pf12');"></td><td>Breast_Mammary_Tissue</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Cells_EBV-transformed_lymphocytes" onclick="javascript:doit('tp12','pf12');"></td><td>Cells_EBV-transformed_lymphocytes</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Cells_Transformed_fibroblasts" onclick="javascript:doit('tp12','pf12');"></td><td>Cells_Transformed_fibroblasts</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Colon_Sigmoid" onclick="javascript:doit('tp12','pf12');"></td><td>Colon_Sigmoid</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Colon_Transverse" onclick="javascript:doit('tp12','pf12');"></td><td>Colon_Transverse</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Esophagus_Gastroesophageal_Junction" onclick="javascript:doit('tp12','pf12');"></td><td>Esophagus_Gastroesophageal_Junction</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Esophagus_Mucosa" onclick="javascript:doit('tp12','pf12');"></td><td>Esophagus_Mucosa</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Esophagus_Muscularis" onclick="javascript:doit('tp12','pf12');"></td><td>Esophagus_Muscularis</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Heart_Atrial_Appendage" onclick="javascript:doit('tp12','pf12');"></td><td>Heart_Atrial_Appendage</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Heart_Left_Ventricle" onclick="javascript:doit('tp12','pf12');"></td><td>Heart_Left_Ventricle</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Liver" onclick="javascript:doit('tp12','pf12');"></td><td>Liver</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Lung" onclick="javascript:doit('tp12','pf12');"></td><td>Lung</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Minor_Salivary_Gland" onclick="javascript:doit('tp12','pf12');"></td><td>Minor_Salivary_Gland</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Muscle_Skeletal" onclick="javascript:doit('tp12','pf12');"></td><td>Muscle_Skeletal</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Nerve_Tibial" onclick="javascript:doit('tp12','pf12');"></td><td>Nerve_Tibial</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Ovary" onclick="javascript:doit('tp12','pf12');"></td><td>Ovary</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Pancreas" onclick="javascript:doit('tp12','pf12');"></td><td>Pancreas</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Pituitary" onclick="javascript:doit('tp12','pf12');"></td><td>Pituitary</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Prostate" onclick="javascript:doit('tp12','pf12');"></td><td>Prostate</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Skin_Not_Sun_Exposed_Suprapubic" onclick="javascript:doit('tp12','pf12');"></td><td>Skin_Not_Sun_Exposed_Suprapubic</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Skin_Sun_Exposed_Lower_leg" onclick="javascript:doit('tp12','pf12');"></td><td>Skin_Sun_Exposed_Lower_leg</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Small_Intestine_Terminal_Ileum" onclick="javascript:doit('tp12','pf12');"></td><td>Small_Intestine_Terminal_Ileum</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Spleen" onclick="javascript:doit('tp12','pf12');"></td><td>Spleen</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Stomach" onclick="javascript:doit('tp12','pf12');"></td><td>Stomach</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Testis" onclick="javascript:doit('tp12','pf12');"></td><td>Testis</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Thyroid" onclick="javascript:doit('tp12','pf12');"></td><td>Thyroid</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Uterus" onclick="javascript:doit('tp12','pf12');"></td><td>Uterus</td>
										</tr>
										<tr>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Vagina" onclick="javascript:doit('tp12','pf12');"></td><td>Vagina</td>
										<td><input type="checkbox" class="tp12" name="eqtl_class" value="Whole_Blood" onclick="javascript:doit('tp12','pf12');"></td><td>Whole_Blood</td>
										</tr>
																						                                
		                            </table>
		                        </div>
		                    </div>
		                    
		                    <div class="col-lg-11">
		                        <label></label>
		                        <i class="icicon ion-plus-circled btn-lg" id="d7" onclick="openShutManager(this,'box7');" style="cursor:pointer;"></i>
		                        <font style="font-size: 25px;">Methylation:</font>
		                        <font id="pf7">0 options selected</font><a href="${base.contextPath}/view/help#Methylation"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
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
		                        <font id="pf3">0 options selected</font><a href="${base.contextPath}/view/help#LncRNA"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
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
		                        <font id="pf10">0 options selected</font><a href="${base.contextPath}/view/help#mRNA"><img data-toggle="tooltip" title="Please click" src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
		
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
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Cell_Marker_10kb" onclick="javascript:doit('tp10','pf10');"></td><td>Cell_Marker_10kb</td>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Goterm_2kb" onclick="javascript:doit('tp10','pf10');"></td><td>Goterm_2kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Goterm_5kb" onclick="javascript:doit('tp10','pf10');"></td><td>Goterm_5kb</td>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="Goterm_10kb" onclick="javascript:doit('tp10','pf10');"></td><td>Goterm_10kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="GTEx_2kb" onclick="javascript:doit('tp10','pf10');"></td><td>GTEx_2kb</td>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="GTEx_5kb" onclick="javascript:doit('tp10','pf10');"></td><td>GTEx_5kb</td>
		                                </tr>
		                                <tr>
		                                    <td><input type="checkbox" class="tp10" name="m_class" value="GTEx_10kb" onclick="javascript:doit('tp10','pf10');"></td><td>GTEx_10kb</td>
		                                    <td></td><td></td>
		                                </tr>
		                            </table>
		                        </div>
		                    </div>
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
<script src="${base.contextPath}/static/js/vue.js"></script>
<script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
<script  src="${base.contextPath}/static/js/require.min.js"></script>
<script type="text/javascript" src="${base.contextPath}/static/js/bootstrap-multiselect.js"></script>
<script src="${base.contextPath}/static/js/analysis.js"></script>
<script type="text/javascript" charset="utf-8" src="${base.contextPath}/static/js/popper.min.js"></script>
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
    $("#page3").click(function(){
        $('#radio3').click();
    });
    $("#radiotf1click").click(function(){
        $('#radiotf1').click();
    });
    $("#radiotf2click").click(function(){
        $('#radiotf2').click();
    });
    $("#radiotcof1click").click(function(){
        $('#radiotcof1').click();
    });
    $("#radiotcof2click").click(function(){
        $('#radiotcof2').click();
    });
    $("#page4").click(function(){
        $('#radio4').click();
        document.getElementById("box5").getElementsByClassName("tp5")[0].checked=true;
    });
</script>
<script type="text/javascript">
 $(document).ready(function(){
 
 	const _charStr = 'abacdefghjklmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ0123456789';
	 /**
	 * 随机生成索引
	 * @param min 最小值
	 * @param max 最大值
	 * @param i 当前获取位置
	 */
	function RandomIndex(min, max, i){
	    let index = Math.floor(Math.random()*(max-min+1)+min),
	        numStart = _charStr.length - 10;
	    //如果字符串第一位是数字，则递归重新获取
	    if(i==0&&index>=numStart){
	        index = RandomIndex(min, max, i);
	    }
	    //返回最终索引值
	    return index;
	}
	 
	 /**
	 * 随机生成字符串
	 * @param len 指定生成字符串长度
	 */
	function getRandomString(len){
	    let min = 0, max = _charStr.length-1, _str = '';
	    //判断是否指定长度，否则默认长度为15
	    len = len || 15;
	    //循环生成字符串
	    for(var i = 0, index; i < len; i++){
	        index = RandomIndex(min, max, i);
	        _str += _charStr[index];
	    }
	    return _str;
	}
	
    var analysistab = $("input:radio[name='analysisForm']:checked").val();
    	
	    let userId = getRandomString(20);
		$("input[name='userId']").val(userId);

       $("#analysis_check").click(function(){
       
       	if (analysistab === 1) {
       		
	  	  var arr = ["hmm_class","tf_class","tf_class2","tcof_class","tcof_class2","histone_class","atac_class","enhancer_class","se_class","snp_class","eqtl_class","methylation_class","lnc_class","m_class"];
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
	  	
       	}
       
	  	  var source = $("input:radio[name='source']:checked").val();
	  	  if(source == "0"){
	  	      var textarea = $("#input").val();
	  	      var num = $("#input").val().split("\n").length;
		  	  if(textarea == ""){
		  	    alert("Error: Please input the region list!");
		  	    return false;
		  	    }
		  	  //if(num > 2000){
		  	  //  alert("Error: Too many inputs, the best input is 2000 regions!");
		  	  //  return false;
		  	  //  }
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
	  	      }
		  	  //alert("Warning: If the uploaded file is too large, the website may take a long time to respond!");
	  	      //else{
	  	       // var fileSize = $("#file")[0].files[0].size/(1024*1024);
	  	       // if(fileSize > 0.1){
			  //    alert("Error: File size is too large. Optimal size is 0.1M!");
			  //    return false;
		  	  // } 
	  	      //}
	  	  }
	  	  if (lolaRadio === "1") {
	  	      var file = document.getElementById('file2').value.length;
	  	      var filename = document.getElementById('file2').value;
	  	      if(filename.split(".")[1] != "bed"){
	  	         alert("Error: Please upload the file of bed format!");
		  	     return false;
	  	      }
	  	      if(file == 0){
	  	         alert("Error: Please upload the file!");
		  	     return false;
	  	      }
		      //alert("Warning: If the uploaded file is too large, the website may take a long time to respond!");
	  	  }
	  	  

	  	  
	  	  // 提交 email
          if ($("input[name='judge_email']")[0].checked) {
              let email = $("#email").val();
		      if (email === "" || email === null ) {
		          alert('Mailbox cannot be empty!');
		          return false;
		      }
		      //验证邮箱
		      var reg = /^([a-z0-9A-Z]+[-|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
		      if(!reg.test(email)) {
		          alert("Incorrect email format!");
		          return false;
		      }
          }

	    });

    $("#analysis_example").click(function(){
        document.getElementById('input').value="chr6	83859315	83859740\r\n"+
"chr1	204647527	204647955\r\n"+
"chr17	52649037	52649441\r\n"+
"chr1	153990448	153990935\r\n"+
"chr8	38786902	38787469\r\n"+
"chr2	84613037	84613527\r\n"+
"chr3	185527388	185527912\r\n"+
"chr11	36431626	36432198\r\n"+
"chr4	26197315	26197798\r\n"+
"chr3	101573826	101574293\r\n"+
"chr20	51661571	51662004\r\n"+
"chr3	149678328	149678747\r\n"+
"chr10	118423660	118424165\r\n"+
"chr3	64533534	64533997\r\n"+
"chr8	6576742	6577241\r\n"+
"chr8	111043711	111044198\r\n"+
"chr5	39217605	39218169\r\n"+
"chr12	27841357	27841782\r\n"+
"chr7	6077157	6077608\r\n"+
"chr3	141368154	141368641\r\n"+
"chr13	73090246	73090810\r\n"+
"chr14	89027062	89027689\r\n"+
"chr3	128416767	128417241\r\n"+
"chr10	52221934	52222375\r\n"+
"chr1	39387484	39387890\r\n"+
"chr19	9075505	9076027\r\n"+
"chr13	34019887	34020254\r\n"+
"chr13	113816927	113817339\r\n"+
"chr4	30757354	30757823\r\n"+
"chr19	44684939	44685462\r\n"+
"chr16	66433664	66434217\r\n"+
"chr15	52568841	52569427\r\n"+
"chr1	222643877	222644501\r\n"+
"chr5	157778113	157778534\r\n"+
"chr15	74546046	74546578\r\n"+
"chr8	11853888	11854359\r\n"+
"chr14	103576616	103577053\r\n"+
"chr15	62060279	62060868\r\n"+
"chr10	31242847	31243409\r\n"+
"chr7	156903406	156903958\r\n"+
"chr6	125956574	125957209\r\n"+
"chr5	171419117	171419794\r\n"+
"chr1	156020849	156021257\r\n"+
"chr10	4431301	4431797\r\n"+
"chr22	17847395	17847828\r\n"+
"chr10	102432216	102432842\r\n"+
"chr1	234772264	234772758\r\n"+
"chr8	8288266	8288837\r\n"+
"chr17	56699218	56700033\r\n"+
"chr3	31377049	31377492\r\n"+
"chr19	46068504	46068946\r\n"+
"chr12	75960037	75960484\r\n"+
"chr10	22436574	22437463\r\n"+
"chr4	158723193	158723817\r\n"+
"chr15	29736589	29736979\r\n"+
"chr19	11738707	11739258\r\n"+
"chr5	37665811	37666330\r\n"+
"chr6	28225067	28225627\r\n"+
"chr20	4973527	4973930\r\n"+
"chr14	100693152	100693678\r\n"+
"chr7	104943522	104944031\r\n"+
"chr1	93078948	93079447\r\n"+
"chr1	45303882	45304412\r\n"+
"chr19	7652035	7652590\r\n"+
"chr11	13073541	13073981\r\n"+
"chr11	64566401	64566898\r\n"+
"chr17	81967761	81968377\r\n"+
"chr21	32463825	32464297\r\n"+
"chr16	71895039	71895629\r\n"+
"chr7	105776092	105776451\r\n"+
"chr5	102090775	102091209\r\n"+
"chr8	42843066	42843651\r\n"+
"chr4	4574842	4575675\r\n"+
"chr3	49939875	49940555\r\n"+
"chr2	230905361	230905845\r\n"+
"chr17	60037056	60037468\r\n"+
"chr5	175861304	175862040\r\n"+
"chr10	75294791	75295561\r\n"+
"chr17	40956753	40957223\r\n"+
"chr7	151057942	151058524\r\n"+
"chr9	21684858	21685337\r\n"+
"chr17	43530705	43531391\r\n"+
"chr7	92695297	92696137\r\n"+
"chr11	44523536	44523903\r\n"+
"chr14	77128321	77128845\r\n"+
"chr16	68448320	68448818\r\n"+
"chr6	137001283	137001768\r\n"+
"chr8	37545426	37545919\r\n"+
"chr1	151972879	151973354\r\n"+
"chr12	124609069	124609581\r\n"+
"chr10	74150578	74151244\r\n"+
"chr14	26394418	26394932\r\n"+
"chr15	92904020	92904593\r\n"+
"chr10	112490510	112491027\r\n"+
"chr12	89542976	89543462\r\n"+
"chr8	100559306	100559990\r\n"+
"chr17	49230589	49231259\r\n"+
"chr1	55429051	55429673\r\n"+
"chr8	90670927	90671331\r\n"+
"chr11	73876540	73877385\r\n"+
"chr9	6897856	6898259\r\n"+
"chr2	223549068	223549693\r\n"+
"chr10	70477645	70478136\r\n"+
"chr7	4631635	4632186\r\n"+
"chr12	75635590	75636075\r\n"+
"chr7	13017057	13017797\r\n"+
"chr14	89040445	89041186\r\n"+
"chr16	15899492	15900071\r\n"+
"chrX	54808740	54809130\r\n"+
"chr17	67528900	67529414\r\n"+
"chr12	65494510	65495072\r\n"+
"chr8	19013971	19014673\r\n"+
"chr5	71601271	71602029\r\n"+
"chr5	74326848	74327353\r\n"+
"chr9	108579871	108580435\r\n"+
"chr2	133818128	133818690\r\n"+
"chr1	41496206	41496651\r\n"+
"chr12	42238272	42238848\r\n"+
"chr1	235079380	235079917\r\n"+
"chr22	28923748	28924236\r\n"+
"chr1	23809740	23810144\r\n"+
"chr16	87407541	87408045\r\n"+
"chr9	5437653	5438154\r\n"+
"chr2	100284151	100284582\r\n"+
"chr7	73799355	73799947\r\n"+
"chr2	216573059	216573574\r\n"+
"chr19	2047318	2047764\r\n"+
"chr12	12903777	12904283\r\n"+
"chr1	31689658	31690190\r\n"+
"chr8	125333934	125334615\r\n"+
"chr5	149210151	149210667\r\n"+
"chr22	46773860	46774306\r\n"+
"chr14	24673433	24673955\r\n"+
"chr17	73359978	73360423\r\n"+
"chr14	68564964	68565418\r\n"+
"chr18	3665698	3666194\r\n"+
"chr12	31959005	31959568\r\n"+
"chr2	97869235	97869774\r\n"+
"chr14	35291959	35292588\r\n"+
"chr11	120710353	120710778\r\n"+
"chr1	192531094	192531432\r\n"+
"chr8	127742860	127743308\r\n"+
"chr2	226995575	226996089\r\n"+
"chr12	2004285	2004786\r\n"+
"chr9	124574871	124575416\r\n"+
"chr18	48538740	48539125\r\n"+
"chr19	17075078	17075760\r\n"+
"chr17	68821798	68822355\r\n"+
"chr3	4986777	4987275\r\n"+
"chr7	150378913	150379495\r\n"+
"chr18	9869783	9870321\r\n"+
"chr1	84365508	84366204\r\n"+
"chr22	28883418	28883805\r\n"+
"chr8	41151597	41152028\r\n"+
"chr3	142578507	142579029\r\n"+
"chr17	39451080	39451550\r\n"+
"chr2	234203123	234203637\r\n"+
"chr2	27429024	27429498\r\n"+
"chr10	24944809	24945194\r\n"+
"chr10	102502650	102503123\r\n"+
"chr19	2543562	2543954\r\n"+
"chr19	511061	511582\r\n"+
"chrX	23807328	23807925\r\n"+
"chr8	121348704	121349269\r\n"+
"chr9	137083280	137083780\r\n"+
"chr6	12348562	12349000\r\n"+
"chr19	41263725	41264417\r\n"+
"chr2	20350759	20351285\r\n"+
"chr9	22237499	22238219\r\n"+
"chr4	98522510	98522929\r\n"+
"chr9	94726443	94726826\r\n"+
"chr8	132703505	132704543\r\n"+
"chr20	31712615	31713098\r\n"+
"chr12	12033410	12033773\r\n"+
"chr1	94281318	94281966\r\n"+
"chr7	87548539	87548921\r\n"+
"chr11	44506417	44506842\r\n"+
"chr8	109593533	109593946\r\n"+
"chr14	60721222	60721919\r\n"+
"chr17	32486186	32486799\r\n"+
"chr8	93846507	93847106\r\n"+
"chr3	11944035	11944399\r\n"+
"chr6	30769115	30769961\r\n"+
"chr10	90227211	90227652\r\n"+
"chr21	17501233	17501778\r\n"+
"chr7	34035903	34036524\r\n"+
"chr5	135263117	135263644\r\n"+
"chr16	55623000	55623513\r\n"+
"chr10	122954025	122954611\r\n"+
"chr6	151501815	151502192\r\n"+
"chr17	82003492	82004085\r\n"+
"chr20	58980899	58981367\r\n"+
"chr2	20578987	20579669\r\n"+
"chr17	40951174	40951699\r\n"+
"chr9	129557337	129557858\r\n"+
"chr14	54337307	54337858\r\n"+
"chr1	94605647	94606160\r\n"+
"chr17	58391365	58391980\r\n"+
"chr20	54123503	54124122\r\n"+
"chr5	101102521	101102902\r\n"+
"chr18	3618054	3618659\r\n"+
"chr10	10805334	10805951\r\n"+
"chr6	21109687	21110307\r\n"+
"chr1	226626509	226626926\r\n"+
"chr1	14791610	14792036\r\n"+
"chr1	9145236	9145662\r\n"+
"chr2	55419711	55420176\r\n"+
"chr14	68295267	68295794\r\n"+
"chr14	68779818	68780371\r\n"+
"chr1	43373050	43373643\r\n"+
"chr1	228139753	228140362\r\n"+
"chr7	74993926	74994361\r\n"+
"chr2	234679750	234680475\r\n"+
"chr10	99620317	99621180\r\n"+
"chr1	59556800	59557329\r\n"+
"chr14	32259673	32260079\r\n"+
"chr9	136399994	136400425\r\n"+
"chr9	33001430	33002020\r\n"+
"chr22	35561750	35562384\r\n"+
"chr2	62304798	62305524\r\n"+
"chr17	41403878	41404492\r\n"+
"chr16	1677909	1678423\r\n"+
"chr17	58352167	58352818\r\n"+
"chr22	37782356	37782995\r\n"+
"chr2	234362951	234363431\r\n"+
"chr6	34757200	34757647\r\n"+
"chr18	9016871	9017460\r\n"+
"chr14	61355849	61356268\r\n"+
"chr4	6337454	6337903\r\n"+
"chr1	115430819	115431141\r\n"+
"chr5	115634687	115634992\r\n"+
"chr15	79549180	79549621\r\n"+
"chr17	78142055	78142498\r\n"+
"chr1	202348380	202348819\r\n"+
"chr4	30789056	30789697\r\n"+
"chr9	38047705	38048183\r\n"+
"chr4	145938657	145939132\r\n"+
"chr22	29030442	29031089\r\n"+
"chr1	67700642	67701078\r\n"+
"chr17	35063533	35063903\r\n"+
"chr7	50206397	50206883\r\n"+
"chr8	126434706	126435328\r\n"+
"chr1	12178972	12179416\r\n"+
"chr6	142845831	142846255\r\n"+
"chr12	6554110	6554487\r\n"+
"chr3	57060398	57060878\r\n"+
"chr1	16162198	16162711\r\n"+
"chr5	174608720	174609504\r\n"+
"chr10	58137754	58138239\r\n"+
"chr4	123478768	123479156\r\n"+
"chr2	62578015	62578545\r\n"+
"chr2	145323470	145323970\r\n"+
"chr19	36070366	36070775\r\n"+
"chr5	399696	400406\r\n"+
"chr20	371856	372313\r\n"+
"chr21	43659321	43659750\r\n"+
"chr17	1684715	1685170\r\n"+
"chr8	48788733	48789186\r\n"+
"chr10	31940837	31941430\r\n"+
"chr8	123516808	123517334\r\n"+
"chr19	49036757	49037158\r\n"+
"chr7	23334523	23335002\r\n"+
"chr12	51262713	51263190\r\n"+
"chr1	23019156	23019547\r\n"+
"chrX	1750194	1750664\r\n"+
"chr2	226030704	226031495\r\n"+
"chr3	134485665	134486436\r\n"+
"chrX	39975366	39975920\r\n"+
"chr20	54207617	54208172\r\n"+
"chr6	57089887	57090275\r\n"+
"chr17	76484449	76485037\r\n"+
"chr16	15590859	15591323\r\n"+
"chr8	41578011	41578486\r\n"+
"chr6	43635566	43636134\r\n"+
"chr1	226815103	226815642\r\n"+
"chr9	91100083	91100573\r\n"+
"chr17	65750639	65751070\r\n"+
"chr12	65430960	65431331\r\n"+
"chr3	111610195	111610995\r\n"+
"chr14	53846196	53846722\r\n"+
"chr19	40285437	40285960\r\n"+
"chr11	27873495	27874067\r\n"+
"chr12	132222415	132222802\r\n"+
"chr4	31089826	31090247\r\n"+
"chr8	37695469	37695933\r\n"+
"chr17	59754533	59754982\r\n"+
"chr6	7919817	7920231\r\n"+
"chr1	234998368	234998844\r\n"+
"chr6	87155281	87155773\r\n"+
"chr14	53882778	53883178\r\n"+
"chr10	112536598	112537070\r\n"+
"chr21	29849682	29850061\r\n"+
"chr5	142441539	142442278\r\n"+
"chr12	122895967	122896491\r\n"+
"chr22	16949554	16949944\r\n"+
"chr16	50467772	50468640\r\n"+
"chr4	34293443	34293809\r\n"+
"chr11	29282080	29282535\r\n"+
"chr2	241009772	241010496\r\n"+
"chr17	81711867	81712777\r\n"+
"chr7	23349494	23350208\r\n"+
"chr13	44306449	44307046\r\n"+
"chr9	129596744	129597958\r\n"+
"chr7	138352480	138352996\r\n"+
"chr20	1415853	1416322\r\n"+
"chr1	150876062	150876731\r\n"+
"chr3	134374274	134375309\r\n"+
"chr4	181549043	181549516\r\n"+
"chr14	54310139	54310737\r\n"+
"chr9	121692597	121692985\r\n"+
"chr14	63727954	63728580\r\n"+
"chr20	57212538	57212997\r\n"+
"chr2	26709756	26710252\r\n"+
"chr18	2905944	2906384\r\n"+
"chr10	79068675	79069215\r\n"+
"chr6	36239640	36240238\r\n"+
"chr1	8197750	8198252\r\n"+
"chr6	90218511	90219006\r\n"+
"chr11	9314685	9315167\r\n"+
"chr6	7974962	7975525\r\n"+
"chr1	24321572	24322547\r\n"+
"chr3	183162352	183162894\r\n"+
"chr22	29907778	29908226\r\n"+
"chr2	9099314	9099733\r\n"+
"chr1	39183615	39184214\r\n"+
"chr2	218908904	218909500\r\n"+
"chr1	16143837	16144293\r\n"+
"chr3	140941441	140942035\r\n"+
"chr7	27115058	27115639\r\n"+
"chr16	89917792	89918389\r\n"+
"chr11	118912035	118912708\r\n"+
"chr11	102111508	102112306\r\n"+
"chr10	56361024	56361527\r\n"+
"chr17	50661794	50662273\r\n"+
"chr3	139299398	139299970\r\n"+
"chr8	121725776	121726299\r\n"+
"chr11	10659513	10660091\r\n"+
"chr6	14270953	14271555\r\n"+
"chr5	44875642	44876184\r\n"+
"chr19	5790494	5790934\r\n"+
"chr20	4041768	4042159\r\n"+
"chr1	68323101	68323832\r\n"+
"chr7	33987308	33987744\r\n"+
"chr17	82458130	82459140\r\n"+
"chr10	3889902	3890478\r\n"+
"chr11	110565219	110565719\r\n"+
"chr17	21253119	21253712\r\n"+
"chr12	62866009	62866404\r\n"+
"chr8	63038718	63039673\r\n"+
"chr14	28382794	28383215\r\n"+
"chr9	129178093	129178853\r\n"+
"chr12	114799605	114800072\r\n"+
"chr7	47557946	47558641\r\n"+
"chr18	3448165	3448617\r\n"+
"chr22	39592694	39593344\r\n"+
"chr2	157628517	157629398\r\n"+
"chr14	105091558	105092000\r\n"+
"chr2	27369798	27370586\r\n"+
"chr2	20491969	20492534\r\n"+
"chr12	52147309	52147740\r\n"+
"chr17	68511659	68512519\r\n"+
"chr5	139755968	139756565\r\n"+
"chr15	55587715	55588378\r\n"+
"chr11	111870917	111871696\r\n"+
"chr1	15800135	15800702\r\n"+
"chr4	6974416	6974795\r\n"+
"chr18	3602857	3603779\r\n"+
"chr14	54291833	54292246\r\n"+
"chr16	85316306	85316724\r\n"+
"chr4	74320515	74321053\r\n"+
"chr3	111847986	111848683\r\n"+
"chr11	72609442	72609876\r\n"+
"chr16	56654218	56654626\r\n"+
"chrX	106917641	106918211\r\n"+
"chr17	81365598	81366216\r\n"+
"chr16	74577977	74578475\r\n"+
"chr1	108865559	108865976\r\n"+
"chr5	181260874	181261313\r\n"+
"chr6	29965942	29966411\r\n"+
"chr2	111117938	111118322\r\n"+
"chr15	62254304	62254673\r\n"+
"chr7	34339114	34339618\r\n"+
"chr11	2526923	2527323\r\n"+
"chr6	11093374	11094332\r\n"+
"chr3	50260039	50260783\r\n"+
"chr19	47190114	47190861\r\n"+
"chr3	126260938	126261327\r\n"+
"chr12	70449207	70449873\r\n"+
"chr15	23039412	23039868\r\n"+
"chr4	173252194	173252722\r\n"+
"chr8	128554784	128555493\r\n"+
"chr1	15152352	15153067\r\n"+
"chr12	117105867	117106236\r\n"+
"chr18	49656048	49656533\r\n"+
"chr13	44281628	44281991\r\n"+
"chr1	3320495	3320909\r\n"+
"chr1	181310454	181310880\r\n"+
"chr22	40636385	40637103\r\n"+
"chr19	38374373	38374997\r\n"+
"chr7	149028439	149029035\r\n"+
"chr2	113818462	113818994\r\n"+
"chr19	48558144	48558678\r\n"+
"chr19	6199161	6199776\r\n"+
"chr15	66701896	66702448\r\n"+
"chr17	39753848	39754609\r\n"+
"chr4	108247663	108248111\r\n"+
"chr19	45584702	45585114\r\n"+
"chr1	202283294	202283843\r\n"+
"chr10	86365655	86366270\r\n"+
"chr2	219522051	219522425\r\n"+
"chr2	181810765	181811173\r\n"+
"chr8	125498238	125498616\r\n"+
"chr7	50446591	50447054\r\n"+
"chr7	98822488	98822977\r\n"+
"chr16	87777631	87778135\r\n"+
"chr12	116497014	116497530\r\n"+
"chr3	11136839	11137614\r\n"+
"chr17	59154732	59155468\r\n"+
"chr10	75498349	75498864\r\n"+
"chr18	24076226	24076588\r\n"+
"chr15	67065348	67065950\r\n"+
"chr8	29348550	29349054\r\n"+
"chr4	55346129	55346779\r\n"+
"chr5	174571795	174572281\r\n"+
"chr7	121298702	121299178\r\n"+
"chr10	124380935	124381498\r\n"+
"chr5	91314024	91314664\r\n"+
"chr9	127388350	127388746\r\n"+
"chr4	15906031	15906521\r\n"+
"chrX	96684439	96684915\r\n"+
"chr17	77002113	77002825\r\n"+
"chr21	29187725	29188313\r\n"+
"chr10	113007252	113007671\r\n"+
"chr10	11684642	11685143\r\n"+
"chr3	189132238	189132811\r\n"+
"chr12	55956044	55956424\r\n"+
"chr16	9160036	9160456\r\n"+
"chr2	226063681	226064184\r\n"+
"chr2	100291128	100291663\r\n"+
"chr15	62066768	62067148\r\n"+
"chr6	157428783	157429177\r\n"+
"chr1	60281424	60282028\r\n"+
"chr17	38703698	38704390\r\n"+
"chr7	155965051	155965569\r\n"+
"chr2	170714543	170715822\r\n"+
"chr19	1028379	1029022\r\n"+
"chr8	86703317	86703718\r\n"+
"chrGL000219.1	42292	42790\r\n"+
"chr6	79631099	79631580\r\n"+
"chr17	57867990	57868695\r\n"+
"chr10	17029561	17029987\r\n"+
"chr11	34685159	34685666\r\n"+
"chr16	88455522	88456315\r\n"+
"chr10	42782526	42782973\r\n"+
"chr19	48354508	48354975\r\n"+
"chr11	15074577	15075189\r\n"+
"chr21	31354498	31355269\r\n"+
"chr12	95942763	95943600\r\n"+
"chr5	139138819	139139175\r\n"+
"chr12	109695959	109696442\r\n"+
"chr6	135497446	135497936\r\n"+
"chr4	4856264	4857112\r\n"+
"chr4	121376103	121376478\r\n"+
"chr14	44316900	44317283\r\n"+
"chr3	50171622	50172059\r\n"+
"chr10	112950020	112950513\r\n"+
"chr10	101191004	101191415\r\n"+
"chr2	172253209	172254056\r\n"+
"chr1	55572891	55573501\r\n"+
"chr2	156339425	156339853\r\n"+
"chr3	93470265	93470878\r\n"+
"chr6	44073069	44073543\r\n"+
"chr12	116528044	116528481\r\n"+
"chr17	17042095	17042457\r\n"+
"chr1	95384526	95384925\r\n"+
"chr17	71071012	71071450\r\n"+
"chr19	49987151	49987562\r\n"+
"chr1	51284439	51284812\r\n"+
"chr9	113884943	113885283\r\n"+
"chr2	37376552	37377126\r\n"+
"chr11	27753909	27754501\r\n"+
"chr4	100299079	100299965\r\n"+
"chr1	204493461	204494501\r\n"+
"chr17	17900593	17901325\r\n"+
"chr9	130499868	130500229\r\n"+
"chr5	137833486	137833853\r\n"+
"chr14	53613545	53614278\r\n"+
"chr12	75766673	75767095\r\n"+
"chr17	58007104	58007824\r\n"+
"chr10	52376746	52377218\r\n"+
"chr6	15132834	15133605\r\n"+
"chr4	11505380	11506059\r\n"+
"chr14	53458207	53458873\r\n"+
"chr8	22693408	22693818\r\n"+
"chr6	44041589	44042106\r\n"+
"chr11	2463128	2463930\r\n"+
"chr12	26114274	26114772\r\n"+
"chr8	125644251	125644785\r\n"+
"chr1	164748206	164748882\r\n"+
"chr13	44243087	44243895\r\n"+
"chr17	933356	933802\r\n"+
"chr9	132857602	132858062\r\n"+
"chr1	77024531	77024867\r\n"+
"chr13	22793475	22794100\r\n"+
"chr11	78030466	78030918\r\n"+
"chr12	91804462	91804908\r\n"+
"chr13	20419680	20420364\r\n"+
"chr3	57450784	57451192\r\n"+
"chr3	72661011	72661400\r\n"+
"chr11	75035922	75036438\r\n"+
"chr12	93570131	93571060\r\n"+
"chr2	46967901	46968319\r\n"+
"chr1	56688430	56689029\r\n"+
"chr9	129488524	129489060\r\n"+
"chr1	94310307	94310996\r\n"+
"chr12	88878259	88878965\r\n"+
"chr4	56436940	56437443\r\n"+
"chr8	143597037	143597791\r\n"+
"chr19	41176048	41176625\r\n"+
"chr1	236471654	236472022\r\n"+
"chr2	218659274	218659859\r\n"+
"chr9	110016175	110016673\r\n"+
"chr5	37370758	37371482\r\n"+
"chr5	82386987	82387875\r\n"+
"chr11	108897037	108897664\r\n"+
"chr1	206470730	206471365\r\n"+
"chr15	80989920	80990255\r\n"+
"chr1	200040544	200040922\r\n"+
"chr8	103372135	103372591\r\n"+
"chr11	28146123	28146503\r\n"+
"chr7	92835296	92835823\r\n"+
"chr8	125679928	125680560\r\n"+
"chr7	50962892	50963518\r\n"+
"chr8	125655524	125656240\r\n"+
"chr2	52082745	52083740\r\n"+
"chr11	85815402	85815813\r\n"+
"chr10	110372733	110373192\r\n"+
"chr1	156706110	156706939\r\n"+
"chr3	188948040	188948895\r\n"+
"chr1	53238253	53238713\r\n"+
"chr2	149994311	149994814\r\n"+
"chr16	86479442	86479841\r\n"+
"chr6	169292581	169293110\r\n"+
"chr1	21586950	21587498\r\n"+
"chr22	28917922	28918527\r\n"+
"chrX	45506943	45507367\r\n"+
"chr12	50247176	50247598\r\n"+
"chr18	31241879	31242280\r\n"+
"chr3	185473466	185473897\r\n"+
"chr3	156816837	156817482\r\n"+
"chr6	15763839	15764201\r\n"+
"chr11	61508155	61508730\r\n"+
"chr8	79023646	79023951\r\n"+
"chr6	18692913	18693336\r\n"+
"chr8	28118360	28118871\r\n"+
"chr16	28277669	28278129\r\n"+
"chr14	61537501	61537980\r\n"+
"chr7	6015884	6016340\r\n"+
"chr8	29402121	29402552\r\n"+
"chr11	96389514	96390033\r\n"+
"chr8	127330099	127330874\r\n"+
"chr7	131015595	131015987\r\n"+
"chr6	6811202	6812081\r\n"+
"chr6	155171134	155171564\r\n"+
"chr1	212030983	212031297\r\n"+
"chr6	21000219	21000828\r\n"+
"chr15	65185384	65185778\r\n"+
"chr20	56554871	56555290\r\n"+
"chr6	21742409	21742744\r\n"+
"chr2	191245930	191246354\r\n"+
"chr9	28213010	28213426\r\n"+
"chr17	77288758	77289134\r\n"+
"chr7	126701047	126701457\r\n"+
"chr8	127320967	127321543\r\n"+
"chr1	198935420	198935860\r\n"+
"chr4	97367862	97368204\r\n"+
"chr6	44084662	44085086\r\n"+
"chr10	103451039	103451409\r\n"+
"chr11	12802613	12803000\r\n"+
"chr17	14302575	14303061\r\n"+
"chr6	14622019	14622578\r\n"+
"chr2	176621364	176621863\r\n"+
"chr10	117496550	117496921\r\n"+
"chr22	32411841	32412383\r\n"+
"chr21	45405683	45406247\r\n"+
"chr6	92810901	92811241\r\n"+
"chr20	8019451	8020075\r\n"+
"chr9	107243674	107244159\r\n"+
"chr7	20777585	20778354\r\n"+
"chr7	105830384	105830817\r\n"+
"chr2	159124508	159125014\r\n"+
"chr9	83620807	83621233\r\n"+
"chr2	136236058	136236462\r\n"+
"chr16	75305343	75305894\r\n"+
"chr10	124093443	124093869\r\n"+
"chr10	52313961	52314443\r\n"+
"chr1	27686493	27687011\r\n"+
"chr5	174735359	174735998\r\n"+
"chr17	57295172	57295871\r\n"+
"chr6	21241121	21241533\r\n"+
"chr12	89353835	89354395\r\n"+
"chrX	155026718	155027194\r\n"+
"chr10	97070032	97070436\r\n"+
"chr3	7876773	7877297\r\n"+
"chr1	199733955	199734603\r\n"+
"chr6	46649100	46649506\r\n"+
"chrX	118823582	118824090\r\n"+
"chr11	76210554	76211047\r\n"+
"chr17	75706918	75707455\r\n"+
"chr6	129191201	129191664\r\n"+
"chr5	62305742	62306331\r\n"+
"chr19	45607296	45607702\r\n"+
"chr10	63712028	63712661\r\n"+
"chr14	34982393	34983066\r\n"+
"chr10	112519116	112519764\r\n"+
"chr6	117195700	117196140\r\n"+
"chr17	48972394	48972812\r\n"+
"chr11	94734580	94734949\r\n"+
"chr2	36561349	36561885\r\n"+
"chr14	76904882	76905282\r\n"+
"chr2	42132953	42133436\r\n"+
"chr15	74461027	74461671\r\n"+
"chr16	28925382	28925901\r\n"+
"chr6	34248676	34249297\r\n"+
"chr1	85328948	85329336\r\n"+
"chr13	30832806	30833303\r\n"+
"chr16	75623033	75623464\r\n"+
"chr5	179732490	179732930\r\n"+
"chr16	85329320	85329871\r\n"+
"chr2	238864197	238864573\r\n"+
"chr13	95256123	95256510\r\n"+
"chr2	238597120	238597521\r\n"+
"chr21	29168734	29169247\r\n"+
"chr6	8958886	8959331\r\n"+
"chr1	225767574	225768088\r\n"+
"chr1	94068119	94068613\r\n"+
"chr8	66026445	66026853\r\n"+
"chr4	25953754	25954252\r\n"+
"chr11	1842523	1842922\r\n"+
"chr2	25376820	25377315\r\n"+
"chr6	98957441	98957821\r\n"+
"chr9	72557535	72557973\r\n"+
"chr9	133375883	133376320\r\n"+
"chr8	93935638	93936266\r\n"+
"chr14	76956393	76956971\r\n"+
"chr5	57302238	57302566\r\n"+
"chr15	51869099	51869418\r\n"+
"chr2	70984838	70985268\r\n"+
"chr3	119293753	119294390\r\n"+
"chr22	37735154	37735525\r\n"+
"chr10	114320664	114321072\r\n"+
"chr6	44000246	44000690\r\n"+
"chr7	35003713	35004350\r\n"+
"chr10	32958057	32958679\r\n"+
"chr2	27721413	27721783\r\n"+
"chr5	57366766	57367234\r\n"+
"chr7	20786717	20787294\r\n"+
"chr7	138752501	138753004\r\n"+
"chr2	26785744	26786219\r\n"+
"chr14	53529552	53529912\r\n"+
"chr6	56938613	56939200\r\n"+
"chr11	109859396	109859779\r\n"+
"chr9	127578960	127579364\r\n"+
"chr7	92528395	92529040\r\n"+
"chr2	177212965	177213464\r\n"+
"chr6	129852278	129852762\r\n"+
"chr2	172442015	172442397\r\n"+
"chr8	127687156	127687617\r\n"+
"chr7	55778643	55779027\r\n"+
"chr14	54252049	54252398\r\n"+
"chr11	93614479	93614802\r\n"+
"chr8	37675136	37675875\r\n"+
"chr8	72018598	72018908\r\n"+
"chr17	82336448	82336967\r\n"+
"chr18	61606714	61607109\r\n"+
"chr11	69825118	69825508\r\n"+
"chr4	101155258	101155725\r\n"+
"chr17	43906517	43906946\r\n"+
"chr1	12573805	12574210\r\n"+
"chr9	15422523	15423031\r\n"+
"chr5	131796758	131797271\r\n"+
"chr1	150515180	150515853\r\n"+
"chr10	124354717	124355318\r\n"+
"chr17	1268105	1268685\r\n"+
"chr1	84271602	84272128\r\n"+
"chr4	78954072	78954483\r\n"+
"chr3	31288618	31289125\r\n"+
"chr10	96043217	96043799\r\n"+
"chr2	241271871	241272220\r\n"+
"chr12	65656864	65657394\r\n"+
"chr5	102959084	102959432\r\n"+
"chr8	93883391	93883818\r\n"+
"chr22	29004500	29005283\r\n"+
"chr16	29863089	29863734\r\n"+
"chr4	124631755	124632118\r\n"+
"chr6	32177640	32178094\r\n"+
"chr2	226124509	226124918\r\n"+
"chr17	40516721	40517301\r\n"+
"chr2	226115033	226115452\r\n"+
"chr12	75457700	75458172\r\n"+
"chr9	6955283	6955774\r\n"+
"chr17	75036651	75037057\r\n"+
"chr5	134114490	134115151\r\n"+
"chr12	47943572	47943975\r\n"+
"chr17	72413196	72413697\r\n"+
"chr2	226652356	226652793\r\n"+
"chr1	229064391	229064868\r\n"+
"chr12	43758660	43759033\r\n"+
"chr1	211735693	211736254\r\n"+
"chr12	91778933	91779325\r\n"+
"chr21	43880279	43880647\r\n"+
"chr3	30282153	30282691\r\n"+
"chr22	37318198	37318797\r\n"+
"chr1	113904829	113905494\r\n"+
"chr3	98971105	98971651\r\n"+
"chr20	38779668	38780213\r\n"+
"chr11	47848082	47848595\r\n"+
"chr6	12008449	12008875\r\n"+
"chrX	37685404	37685767\r\n"+
"chr12	53324816	53326020\r\n"+
"chr7	106775512	106775938\r\n"+
"chr12	45215758	45216263\r\n"+
"chr1	7902607	7903048\r\n"+
"chr16	87457710	87458103\r\n"+
"chr7	44606383	44607257\r\n"+
"chr11	103682187	103682672\r\n"+
"chr10	64209580	64210041\r\n"+
"chr17	82228137	82228594\r\n"+
"chr16	56682212	56682674\r\n"+
"chr6	21005777	21006168\r\n"+
"chr1	93813957	93814357\r\n"+
"chr15	74540714	74541119\r\n"+
"chr4	174283605	174284111\r\n"+
"chr1	33436857	33437256\r\n"+
"chr15	58770667	58771355\r\n"+
"chr9	124309585	124310110\r\n"+
"chr20	3811554	3812161\r\n"+
"chr16	11084399	11084851\r\n"+
"chr20	54132764	54133350\r\n"+
"chr5	113166929	113167401\r\n"+
"chr4	36394324	36394942\r\n"+
"chr22	42964999	42965449\r\n"+
"chr17	46822377	46823012\r\n"+
"chr1	171916334	171916877\r\n"+
"chr5	136016912	136017524\r\n"+
"chr2	191020176	191020591\r\n"+
"chr5	149551366	149551979\r\n"+
"chr1	147670352	147670828\r\n"+
"chr17	74442668	74443358\r\n"+
"chr1	147545119	147545489\r\n"+
"chr1	54488197	54488662\r\n"+
"chr1	159925278	159925868\r\n"+
"chr2	70328907	70329515\r\n"+
"chr7	127820255	127820644\r\n"+
"chr8	125125515	125126257\r\n"+
"chr22	36454981	36455425\r\n"+
"chr12	13739666	13740009\r\n"+
"chr10	112517433	112517879\r\n"+
"chr12	57522125	57522725\r\n"+
"chr3	197959921	197960342\r\n"+
"chr4	3836614	3837297\r\n"+
"chr4	11650685	11651184\r\n"+
"chr10	38010396	38011243\r\n"+
"chr21	28517009	28517417\r\n"+
"chr17	46795481	46795861\r\n"+
"chr8	93869258	93869981\r\n"+
"chr6	73224221	73224627\r\n"+
"chr17	57860809	57861723\r\n"+
"chr10	25059896	25060341\r\n"+
"chr11	46309865	46310194\r\n"+
"chr2	113877838	113878301\r\n"+
"chr12	75640982	75641350\r\n"+
"chr14	53391206	53392072\r\n"+
"chr10	119596598	119597384\r\n"+
"chr5	80181675	80182046\r\n"+
"chr12	52050761	52051748\r\n"+
"chr17	75700361	75700788\r\n"+
"chr11	27838604	27839129\r\n"+
"chr4	121071992	121072529\r\n"+
"chr17	45060937	45061468\r\n"+
"chr5	57383032	57383526\r\n"+
"chr19	45692082	45692856\r\n"+
"chr22	30649440	30649870\r\n"+
"chr4	121448248	121448655\r\n"+
"chr1	55384541	55385145\r\n"+
"chr2	224145792	224146264\r\n"+
"chr12	31749168	31749646\r\n"+
"chr5	124289717	124290176\r\n"+
"chr9	35732099	35732539\r\n"+
"chr18	48948432	48948796\r\n"+
"chr7	106346586	106347024\r\n"+
"chr19	29845073	29845454\r\n"+
"chr14	54177836	54178465\r\n"+
"chr4	57043740	57044246\r\n"+
"chr6	92647623	92647967\r\n"+
"chr11	45922019	45922730\r\n"+
"chr18	70181007	70181421\r\n"+
"chr6	89298731	89299164\r\n"+
"chr3	98922745	98923225\r\n"+
"chr6	129501230	129501767\r\n"+
"chr6	90587214	90587564\r\n"+
"chr9	128052012	128052437\r\n"+
"chr1	234599653	234600087\r\n"+
"chr20	19692583	19692984\r\n"+
"chr3	143648180	143648555\r\n"+
"chr12	53451757	53452174\r\n"+
"chr2	226694173	226694589\r\n"+
"chr8	112674152	112674761\r\n"+
"chr3	114650848	114651275\r\n"+
"chr7	7177129	7177472\r\n"+
"chr13	109211659	109212009\r\n"+
"chr4	114026282	114026830\r\n"+
"chr13	35855530	35855987\r\n"+
"chr10	52391958	52392293\r\n"+
"chr1	219784961	219785584\r\n"+
"chr11	62123569	62123956\r\n"+
"chr17	72421180	72421655\r\n"+
"chrX	46320311	46320822\r\n"+
"chr20	2626882	2627249\r\n"+
"chr8	112675327	112675705\r\n"+
"chr8	112398361	112398857\r\n"+
"chr4	74768871	74769195\r\n"+
"chr3	90080940	90081484\r\n"+
"chr1	8120961	8121657\r\n"+
"chr7	101143984	101144327\r\n"+
"chr12	88960337	88960956\r\n"+
"chr10	11035361	11035681\r\n"+
"chr5	95730859	95731444\r\n"+
"chr3	46065252	46065752\r\n"+
"chr5	40835056	40835456\r\n"+
"chr10	86971013	86971942\r\n"+
"chr2	239275094	239275536\r\n"+
"chr4	74629254	74629631\r\n"+
"chr1	199784139	199784610\r\n"+
"chr1	84174128	84174681\r\n"+
"chr4	88284456	88284992\r\n"+
"chr17	71632315	71632746\r\n"+
"chr1	154974340	154975021\r\n"+
"chr3	171248353	171248780\r\n"+
"chr6	16511856	16512345\r\n"+
"chr8	50666136	50666540\r\n"+
"chr3	36362885	36363362\r\n"+
"chr17	42055589	42055985\r\n"+
"chr1	156103558	156103942\r\n"+
"chrX	47658868	47659519\r\n"+
"chr22	36451729	36452116\r\n"+
"chr8	127247188	127247715\r\n"+
"chr1	234724021	234724733\r\n"+
"chr19	2977219	2977641\r\n"+
"chr1	39117170	39117672\r\n"+
"chrX	106300221	106300618\r\n"+
"chr2	241149129	241149649\r\n"+
"chr11	121928963	121929449\r\n"+
"chr9	127699119	127699586\r\n"+
"chr10	1048871	1049483\r\n"+
"chr1	213443432	213443759\r\n"+
"chr11	19723612	19724124\r\n"+
"chr3	151971807	151972212\r\n"+
"chr7	39949301	39950277\r\n"+
"chrKI270713.1	28450	28945\r\n"+
"chr14	57268600	57269295\r\n"+
"chr13	24670902	24671282\r\n"+
"chr20	31608425	31608929\r\n"+
"chr7	14748429	14748816\r\n"+
"chr4	165112218	165112868\r\n"+
"chr10	76051047	76051497\r\n"+
"chr6	53615657	53616106\r\n"+
"chr11	628504	628904\r\n"+
"chr3	33717618	33718410\r\n"+
"chr22	46267537	46267972\r\n"+
"chr20	9977510	9978047\r\n"+
"chr12	65300092	65300558\r\n"+
"chr22	31646335	31647033\r\n"+
"chr17	81393672	81394257\r\n"+
"chr9	137423045	137423390\r\n"+
"chr1	43008331	43009133\r\n"+
"chr2	219698754	219699395\r\n"+
"chr9	21995044	21995946\r\n"+
"chr19	13846405	13847269\r\n"+
"chr9	75890398	75890753\r\n"+
"chr4	119300395	119301080\r\n"+
"chr18	59358602	59359361\r\n"+
"chr5	1933326	1933753\r\n"+
"chr7	5594085	5594463\r\n"+
"chr1	7300989	7301303\r\n"+
"chr1	68213882	68214299\r\n"+
"chr12	88931595	88931934\r\n"+
"chr1	213176220	213176792\r\n"+
"chr17	41071584	41072017\r\n"+
"chr6	75065598	75065967\r\n"+
"chr16	15494240	15494629\r\n"+
"chr6	35731835	35732531\r\n"+
"chr8	29555264	29555787\r\n"+
"chr18	68916635	68917082\r\n"+
"chr17	76029403	76029867\r\n"+
"chr17	17783071	17783752\r\n"+
"chr5	82356837	82357634\r\n"+
"chr14	68598771	68599390\r\n"+
"chr1	8231978	8232490\r\n"+
"chr2	161123081	161123626\r\n"+
"chr1	84253881	84254378\r\n"+
"chr17	1617560	1618195\r\n"+
"chr5	133489677	133490066\r\n"+
"chr8	112616438	112616837\r\n"+
"chr3	193869296	193870086\r\n"+
"chr4	98260215	98261226\r\n"+
"chrX	2894710	2895270\r\n"+
"chr3	48240916	48241318\r\n"+
"chr12	88934263	88934838\r\n"+
"chr3	195040804	195041166\r\n"+
"chr7	13908678	13909371\r\n"+
"chr17	13776124	13776693\r\n"+
"chr7	116210269	116210801\r\n"+
"chr11	76839360	76839731\r\n"+
"chr17	2511742	2512314\r\n"+
"chr3	177357405	177357823\r\n"+
"chr6	132063696	132064101\r\n"+
"chr19	610702	611464\r\n"+
"chr7	79711685	79712051\r\n"+
"chr2	110212346	110212804\r\n"+
"chr21	35051275	35051623\r\n"+
"chr12	88753043	88753489\r\n"+
"chr3	78507287	78507804\r\n"+
"chr4	181150205	181150614\r\n"+
"chr7	80722790	80723181\r\n"+
"chr9	114278907	114279315\r\n"+
"chr1	199685392	199685741\r\n"+
"chr2	130240986	130241352\r\n"+
"chr1	75034297	75034716\r\n"+
"chr19	10332848	10333437\r\n"+
"chr16	11497968	11498316\r\n"+
"chr12	98399534	98399943\r\n"+
"chr14	80941617	80942069\r\n"+
"chr1	181104686	181105412\r\n"+
"chrX	1481639	1482154\r\n"+
"chr2	232988452	232988893\r\n"+
"chr10	73932345	73932746\r\n"+
"chr10	52452500	52453106\r\n"+
"chr15	55279705	55280014\r\n"+
"chr6	46993875	46994255\r\n"+
"chr8	88715556	88716184\r\n"+
"chr15	23566110	23566535\r\n"+
"chr1	33433540	33434132\r\n"+
"chr2	28711713	28712076\r\n"+
"chr15	22776000	22776362\r\n"+
"chr7	142583514	142583860\r\n"+
"chr12	46371248	46371832\r\n"+
"chr2	236078278	236078883\r\n"+
"chr1	94247359	94248018\r\n"+
"chr7	97104642	97105025\r\n"+
"chr6	63527671	63528035\r\n"+
"chr3	45167383	45167888\r\n"+
"chr20	57168050	57168642\r\n"+
"chr7	130845250	130846061\r\n"+
"chr4	113468221	113468718\r\n"+
"chr9	2260647	2261484\r\n"+
"chr14	57833239	57833754\r\n"+
"chr15	50116511	50116846\r\n"+
"chr1	15834378	15835304\r\n"+
"chr18	8389707	8390058\r\n"+
"chr7	25951041	25951394\r\n"+
"chr3	46109270	46109610\r\n"+
"chr2	226673246	226673817\r\n"+
"chr7	27453772	27454263\r\n"+
"chr15	64703195	64703554\r\n"+
"chr2	110105854	110106474\r\n"+
"chr1	199759031	199759772\r\n"+
"chr7	25862431	25862964\r\n"+
"chr15	96273519	96274095\r\n"+
"chr22	50485505	50486121\r\n"+
"chr2	60581734	60582125\r\n"+
"chr2	234880500	234880882\r\n"+
"chr15	75775303	75775661\r\n"+
"chr10	58384919	58385545\r\n"+
"chr17	67421841	67422283\r\n"+
"chr5	151745087	151745627\r\n"+
"chr4	1521204	1521657\r\n"+
"chr6	18584978	18585494\r\n"+
"chr21	36141540	36142014\r\n"+
"chr8	127215016	127215608\r\n"+
"chr11	36231316	36231677\r\n"+
"chr20	38764971	38765526\r\n"+
"chr4	151324317	151325135\r\n"+
"chr6	27177661	27178163\r\n"+
"chr7	73433514	73433866\r\n"+
"chr7	81012941	81013428\r\n"+
"chr2	74529636	74530060\r\n"+
"chr8	128167630	128168146\r\n"+
"chr12	53675768	53676401\r\n"+
"chr8	127400806	127401306\r\n"+
"chr17	40480549	40481181\r\n"+
"chr17	43545438	43546514\r\n"+
"chr16	50548169	50548572\r\n"+
"chr8	124372339	124372906\r\n"+
"chr9	755681	756049\r\n"+
"chr4	186824747	186825453\r\n"+
"chr1	152543858	152544245\r\n"+
"chr7	102440175	102440559\r\n"+
"chr5	133051313	133052055\r\n"+
"chr12	109713445	109713869\r\n"+
"chr1	203661578	203661942\r\n"+
"chr1	100132735	100133233\r\n"+
"chr20	50165971	50166533\r\n"+
"chr7	16989927	16990399\r\n"+
"chr15	70528264	70528630\r\n"+
"chr20	62739976	62740744\r\n"+
"chr19	45154491	45154885\r\n"+
"chr12	75666950	75667348\r\n"+
"chr19	19138326	19138955\r\n"+
"chr7	99504299	99504877\r\n"+
"chr1	55472755	55473192\r\n"+
"chr8	125647887	125648406\r\n"+
"chr7	134554009	134554354\r\n"+
"chr17	64661915	64662367\r\n"+
"chr12	95334442	95334760\r\n"+
"chr1	30526050	30526641\r\n"+
"chr11	59545140	59545685\r\n"+
"chr19	18380088	18380665\r\n"+
"chr5	90645000	90645360\r\n"+
"chr17	39632300	39632906\r\n"+
"chr1	40875652	40876061\r\n"+
"chr3	61561017	61561711\r\n"+
"chr5	104512917	104513661\r\n"+
"chr5	68505927	68506296\r\n"+
"chr7	81235738	81236311\r\n"+
"chr12	54033232	54033846\r\n"+
"chr11	15053179	15053729\r\n"+
"chr15	74196237	74196645\r\n"+
"chr19	940609	941264\r\n"+
"chr1	25167309	25167934\r\n"+
"chr17	45161351	45161991\r\n"+
"chr2	168216485	168216975\r\n"+
"chr14	54258309	54258810\r\n"+
"chr1	230702970	230703359\r\n"+
"chr8	127297532	127297996\r\n"+
"chr4	85474733	85475263\r\n"+
"chr6	78867286	78868063\r\n"+
"chr10	80498723	80499206\r\n"+
"chr6	20630935	20631386\r\n"+
"chr2	179239262	179239617\r\n"+
"chr9	124769639	124770178\r\n"+
"chr11	62880831	62881793\r\n"+
"chr6	150813977	150814356\r\n"+
"chr1	12618983	12619401\r\n"+
"chr1	3313019	3313516\r\n"+
"chr2	171687341	171688046\r\n"+
"chr7	100538499	100539264\r\n"+
"chr1	216325997	216326398\r\n"+
"chr13	19596897	19597199\r\n"+
"chr17	76264824	76265398\r\n"+
"chr1	59156747	59157198\r\n"+
"chr6	46105499	46105946\r\n"+
"chr11	27933991	27934590\r\n"+
"chr10	4663177	4663866\r\n"+
"chr22	39308857	39309202\r\n"+
"chr1	244801275	244801612\r\n"+
"chr18	70371115	70371651\r\n"+
"chr18	75489778	75490217\r\n"+
"chr14	22689219	22689593\r\n"+
"chr2	85754692	85755036\r\n"+
"chr1	219669089	219669476\r\n"+
"chr14	92694042	92694356\r\n"+
"chr6	137051708	137052199\r\n"+
"chr12	65631615	65632009\r\n"+
"chr17	1190694	1191046\r\n"+
"chrX	48475871	48476294\r\n"+
"chr11	69948304	69948830\r\n"+
"chr12	109840708	109841143\r\n"+
"chr6	151208199	151208553\r\n"+
"chr6	150668375	150668724\r\n"+
"chr19	12401021	12401641\r\n"+
"chr12	88763466	88763989\r\n"+
"chr2	79393636	79394146\r\n"+
"chr10	75401542	75402028\r\n"+
"chr7	151426518	151427097\r\n"+
"chr2	121075154	121075668\r\n"+
"chr2	181876604	181877120\r\n"+
"chr22	41468918	41469285\r\n"+
"chr5	23466358	23466905\r\n"+
"chr11	59810609	59811007\r\n"+
"chr8	127265617	127266163\r\n"+
"chr6	130602213	130602793\r\n"+
"chr9	72476972	72477350\r\n"+
"chr8	134832381	134833330\r\n"+
"chr1	941628	942142\r\n"+
"chr12	124917180	124917804\r\n"+
"chr15	57607608	57608097\r\n"+
"chr3	114056436	114056905\r\n"+
"chr11	10647397	10647859\r\n"+
"chr7	50197568	50197953\r\n"+
"chr19	11155692	11156102\r\n"+
"chr10	63629446	63629919\r\n"+
"chr4	183183215	183183676\r\n"+
"chr5	10760049	10760481\r\n"+
"chr17	71424080	71424596\r\n"+
"chr11	119205762	119206283\r\n"+
"chr16	30352074	30352431\r\n"+
"chr9	127828263	127828760\r\n"+
"chr5	149455850	149456496\r\n"+
"chr1	247104123	247104610\r\n"+
"chr10	117186794	117187327\r\n"+
"chr6	158730488	158731078\r\n"+
"chr14	21024675	21025137\r\n"+
"chr12	3203000	3203358\r\n"+
"chr10	117168098	117168568\r\n"+
"chr15	40103294	40103696\r\n"+
"chr10	95561040	95561566\r\n"+
"chr12	14257616	14258257\r\n"+
"chr11	76700336	76700943\r\n"+
"chr2	224678264	224679029\r\n"+
"chr3	107216905	107217547\r\n"+
"chr22	18001254	18002027\r\n"+
"chr5	90096740	90097128\r\n"+
"chr22	39254783	39255189\r\n"+
"chr2	226756136	226756471\r\n"+
"chr17	33142743	33143111\r\n"+
"chr16	11497315	11497743\r\n"+
"chr6	36225025	36225491\r\n"+
"chr3	23668367	23668941\r\n"+
"chr1	199778288	199778627\r\n"+
"chr7	77798290	77798960\r\n"+
"chr2	58042610	58042968\r\n"+
"chr11	117316084	117316509\r\n"+
"chr19	461832	462320\r\n"+
"chr17	43562832	43563230\r\n"+
"chr11	69637781	69638572\r\n"+
"chr16	69404243	69404947\r\n"+
"chr9	21576423	21577010\r\n"+
"chr17	82324529	82324883\r\n"+
"chr2	81304329	81305001\r\n"+
"chr9	2843939	2844413\r\n"+
"chr1	84156135	84156617\r\n"+
"chr17	46922678	46923297\r\n"+
"chr11	47355248	47355652\r\n"+
"chr8	93880109	93880684\r\n"+
"chr5	95646598	95647022\r\n"+
"chr7	22822710	22823060\r\n"+
"chr10	73743937	73744495\r\n"+
"chr17	65560453	65562178\r\n"+
"chr1	244833681	244834122\r\n"+
"chr7	130894145	130894884\r\n"+
"chr10	110641246	110641652\r\n"+
"chr5	68554851	68555326\r\n"+
"chr12	2397719	2398102\r\n"+
"chr20	297216	297647\r\n"+
"chr7	121544676	121545235\r\n"+
"chr16	21820252	21820675\r\n"+
"chr8	129685581	129685956\r\n"+
"chr9	33458526	33458907\r\n"+
"chr1	219310129	219310718\r\n"+
"chr2	70087920	70088438\r\n"+
"chr8	19817159	19817510\r\n"+
"chr10	121927857	121928341\r\n"+
"chr22	43104007	43104590\r\n"+
"chr6	28863290	28863918\r\n"+
"chr18	48002992	48003351\r\n"+
"chr6	100296861	100297575\r\n"+
"chr12	27549760	27550393\r\n"+
"chr2	37379861	37380239\r\n"+
"chr2	175810	176235\r\n"+
"chr11	95150586	95151387\r\n"+
"chr11	33941594	33941994\r\n"+
"chr11	35235885	35236298\r\n"+
"chr19	47130591	47131067\r\n"+
"chr5	111530304	111530667\r\n"+
"chr4	103429313	103429629\r\n"+
"chr2	201882378	201882765\r\n"+
"chr10	21724647	21725017\r\n"+
"chr15	85413439	85413802\r\n"+
"chr3	120349216	120349925\r\n"+
"chr3	9933541	9933990\r\n"+
"chr4	4763400	4763882\r\n"+
"chr1	170531675	170532522\r\n"+
"chrX	1649605	1649968\r\n"+
"chr3	20054517	20054968\r\n"+
"chrX	49190746	49191454\r\n"+
"chr9	97109195	97109523\r\n"+
"chr7	121282082	121282917\r\n"+
"chr18	63969906	63970754\r\n"+
"chr2	38602785	38603701\r\n"+
"chr22	37328001	37328353\r\n"+
"chr17	10095326	10095667\r\n"+
"chr7	129779736	129780252\r\n"+
"chr11	121464523	121465007\r\n"+
"chr14	54189754	54190182\r\n"+
"chr2	65382782	65383208\r\n"+
"chr9	110744691	110745315\r\n"+
"chr8	102806089	102807197\r\n"+
"chr1	11478697	11479351\r\n"+
"chr6	158737615	158738205\r\n"+
"chr12	108838163	108838719\r\n"+
"chr7	44490376	44490982\r\n"+
"chr20	37615875	37616176\r\n"+
"chr22	46754336	46754759\r\n"+
"chr8	128653282	128653664\r\n"+
"chr2	155099633	155100179\r\n"+
"chr22	42303875	42304434\r\n"+
"chr4	180909351	180909704\r\n"+
"chr1	82632117	82632529\r\n"+
"chr1	204516239	204516874\r\n"+
"chr5	173764391	173764802\r\n"+
"chr15	69760851	69761281\r\n"+
"chr1	15356062	15356493\r\n"+
"chr5	45384031	45384368\r\n"+
"chr2	232568172	232568503\r\n"+
"chr2	26355481	26355906\r\n"+
"chr20	54139179	54139871\r\n"+
"chr5	157464405	157464819\r\n"+
"chr11	109424930	109425233\r\n"+
"chr7	44761794	44762255\r\n"+
"chr8	28889831	28890410\r\n"+
"chr21	45365669	45366114\r\n"+
"chr8	131843029	131843428\r\n"+
"chr22	37742370	37742780\r\n"+
"chr5	142356674	142357383\r\n"+
"chr10	37857482	37857850\r\n"+
"chr19	23686826	23687431\r\n"+
"chr17	59355104	59355581\r\n"+
"chr1	25091001	25091392\r\n"+
"chr3	32226844	32227238\r\n"+
"chr10	102641997	102642712\r\n"+
"chr6	4358591	4359027\r\n"+
"chr19	18364894	18365284\r\n"+
"chr11	12724302	12724975\r\n"+
"chr11	122105383	122105828\r\n"+
"chr4	74559891	74560238\r\n"+
"chr7	44044475	44044883\r\n"+
"chr1	66543460	66543867\r\n"+
"chr21	36845102	36845457\r\n"+
"chr8	125271406	125271890\r\n"+
"chr12	105562091	105562917\r\n"+
"chr15	81091974	81092873\r\n"+
"chr2	231613685	231614511\r\n"+
"chr11	10857679	10858667\r\n"+
"chr1	16727235	16728015\r\n"+
"chrX	17674583	17674951\r\n"+
"chr12	49069619	49070244\r\n"+
"chr10	114303968	114304383\r\n"+
"chr14	53980083	53980437\r\n"+
"chr1	219806651	219807025\r\n"+
"chr3	111874434	111874823\r\n"+
"chr2	181782650	181783003\r\n"+
"chr3	194134858	194135296\r\n"+
"chr17	63675881	63676356\r\n"+
"chr20	21017656	21018129\r\n"+
"chr3	101677018	101677419\r\n"+
"chr9	105064572	105064921\r\n"+
"chr12	121799953	121800646\r\n"+
"chr10	103908204	103908644\r\n"+
"chr3	42600264	42600807\r\n"+
"chr12	52036863	52037195\r\n"+
"chr16	89505409	89505764\r\n"+
"chr5	145936396	145936802\r\n"+
"chr12	57693713	57694254\r\n"+
"chr20	33914952	33915270\r\n"+
"chr7	33128897	33129672\r\n"+
"chr9	134896346	134896852\r\n"+
"chr17	2893526	2894001\r\n"+
"chr9	109036097	109036438\r\n"+
"chr15	41513637	41514231\r\n"+
"chr14	93959357	93959811\r\n"+
"chr7	130995407	130996020\r\n"+
"chrX	1764985	1765442\r\n"+
"chr17	81316702	81317071\r\n"+
"chr19	35213760	35214272\r\n"+
"chr10	101135900	101136228\r\n"+
"chr4	57504218	57504556\r\n"+
"chr14	55803758	55804184\r\n"+
"chr4	31236911	31237604\r\n"+
"chr1	25875144	25875824\r\n"+
"chr17	79708549	79708925\r\n"+
"chr1	27321981	27322476\r\n"+
"chr4	100591148	100591484\r\n"+
"chr1	76254218	76254629\r\n"+
"chr20	2798797	2799223\r\n"+
"chr3	195259493	195260303\r\n"+
"chr2	11830067	11830640\r\n"+
"chr3	12345237	12345589\r\n"+
"chr14	100476333	100476867\r\n"+
"chr6	47309709	47310143\r\n"+
"chr20	36951162	36951795\r\n"+
"chr3	98593533	98594111\r\n"+
"chr3	156317377	156317829\r\n"+
"chr2	237299973	237300251\r\n"+
"chr14	63543033	63543712\r\n"+
"chr17	72381795	72382378\r\n"+
"chr9	133558099	133558612\r\n"+
"chr16	69530599	69531011\r\n"+
"chr1	16613287	16614002\r\n"+
"chr14	90969972	90970565\r\n"+
"chr19	37906982	37907368\r\n"+
"chr3	149129362	149129865\r\n"+
"chr2	225354361	225354714\r\n"+
"chr17	65588263	65588794\r\n"+
"chr12	31324089	31324704\r\n"+
"chr4	41935011	41935571\r\n"+
"chr5	134071072	134071559\r\n"+
"chr2	27380425	27381015\r\n"+
"chr21	43884028	43884367\r\n"+
"chr16	67115647	67115982\r\n"+
"chr6	35776521	35777037\r\n"+
"chr17	10616477	10616835\r\n"+
"chr4	186684828	186685297\r\n"+
"chr15	84734954	84735454\r\n"+
"chr15	78726885	78727222\r\n"+
"chr3	182212430	182213109\r\n"+
"chr16	30628793	30629085\r\n"+
"chr11	104668973	104669511\r\n"+
"chr2	226618662	226619449\r\n"+
"chr1	3775956	3776295\r\n"+
"chr19	58380490	58381235\r\n"+
"chr6	151120073	151120576\r\n"+
"chr2	234840349	234840683\r\n"+
"chr17	44141698	44142099\r\n"+
"chr2	181949138	181949533\r\n"+
"chr6	35606084	35606515\r\n"+
"chr1	53838059	53838711\r\n"+
"chr17	54900279	54900969\r\n"+
"chr8	18776567	18776909\r\n"+
"chr18	9025513	9025962\r\n"+
"chr2	233353569	233354000\r\n"+
"chr5	91280404	91281052\r\n"+
"chr17	80036367	80036868\r\n"+
"chr4	74543220	74543860\r\n"+
"chr15	60371310	60371784\r\n"+
"chr17	17921245	17921761\r\n"+
"chr11	23359307	23359623\r\n"+
"chr4	42302362	42302727\r\n"+
"chr6	35368458	35368924\r\n"+
"chr8	125686250	125686958\r\n"+
"chr17	2400950	2401268\r\n"+
"chr17	48830707	48831109\r\n"+
"chr18	9741836	9742831\r\n"+
"chr12	75623629	75624201\r\n"+
"chr6	121959487	121959841\r\n"+
"chr10	89644524	89645507\r\n"+
"chr8	106208720	106209158\r\n"+
"chr1	116988272	116988738\r\n"+
"chr5	17907131	17907460\r\n"+
"chr8	124200005	124200519\r\n"+
"chr6	142009621	142010058\r\n"+
"chr11	34495628	34496424\r\n"+
"chr20	46199239	46199660\r\n"+
"chr6	81451773	81452200\r\n"+
"chr9	129568571	129569218\r\n"+
"chr14	23301274	23301868\r\n"+
"chr10	12270855	12271370\r\n"+
"chr4	30840194	30840512\r\n"+
"chr8	37598519	37598910\r\n"+
"chr4	185573690	185574213\r\n"+
"chr6	142944392	142944891\r\n"+
"chr6	2857489	2858111\r\n"+
"chr2	240255994	240256380\r\n"+
"chr1	211675496	211676160\r\n"+
"chr20	31704537	31704953\r\n"+
"chr10	100697135	100697572\r\n"+
"chr2	111049736	111050152\r\n"+
"chr3	139202398	139202836\r\n"+
"chr1	211258735	211259402\r\n"+
"chr1	224542859	224543216\r\n"+
"chr2	235347674	235348125\r\n"+
"chr1	203177892	203178481\r\n"+
"chr9	100098895	100099615\r\n"+
"chr13	85313766	85314269\r\n"+
"chr1	18643416	18643996\r\n"+
"chr2	71227049	71227608\r\n"+
"chr1	170619282	170619912\r\n"+
"chr22	45553841	45554326\r\n"+
"chr3	47781257	47781685\r\n"+
"chr2	28390090	28390728\r\n"+
"chr2	160205519	160205915\r\n"+
"chr5	174503646	174504216\r\n"+
"chr11	65279777	65280101\r\n"+
"chr6	6790834	6791225\r\n"+
"chr6	54194089	54194442\r\n"+
"chr1	165871417	165871777\r\n"+
"chr1	40691390	40692235\r\n"+
"chr4	188127257	188127772\r\n"+
"chr21	26587913	26588349\r\n"+
"chr10	119030384	119030877\r\n"+
"chr2	29747916	29748466\r\n"+
"chr8	143635782	143636588\r\n"+
"chr11	122196991	122197500\r\n"+
"chr15	62896979	62897466\r\n"+
"chr6	18241445	18241851\r\n"+
"chr8	79103762	79104098\r\n"+
"chr18	32018242	32018998\r\n"+
"chrX	134885342	134885926\r\n"+
"chr16	57590909	57591618\r\n"+
"chr12	53345154	53345680\r\n"+
"chr7	155951428	155952142\r\n"+
"chr17	65783754	65784286\r\n"+
"chr1	55828063	55828631\r\n"+
"chr1	56376712	56377062\r\n"+
"chr13	27251158	27251730\r\n"+
"chr20	4512912	4513332\r\n"+
"chr1	155069736	155070270\r\n"+
"chr9	89180314	89180796\r\n"+
"chr15	90994507	90994995\r\n"+
"chr12	100266843	100267432\r\n"+
"chr2	111975084	111975539\r\n"+
"chr20	62735307	62735674\r\n"+
"chr9	96339632	96340067\r\n"+
"chr13	100393946	100394424\r\n"+
"chr20	9241268	9241723\r\n"+
"chr7	123209758	123210147\r\n"+
"chr19	49487256	49487736\r\n"+
"chr18	58785218	58785765\r\n"+
"chr18	58605408	58605834\r\n"+
"chr4	94452709	94453056\r\n"+
"chr5	129377551	129377932\r\n"+
"chr15	92683400	92683772\r\n"+
"chr13	100454947	100455257\r\n"+
"chr1	153566422	153566857\r\n"+
"chr11	102452485	102453217\r\n"+
"chr20	38181317	38181691\r\n"+
"chr14	53975445	53975806\r\n"+
"chr2	146874012	146874753\r\n"+
"chr18	48003685	48004362\r\n"+
"chr12	31589717	31590210\r\n"+
"chr15	44195190	44195760\r\n"+
"chr3	23743395	23743794\r\n"+
"chr1	230186172	230186509\r\n"+
"chr17	76688537	76689057\r\n"+
"chr22	39574713	39575233\r\n"+
"chr12	75701874	75702227\r\n"+
"chr14	68685475	68686052\r\n"+
"chr3	192830974	192831383\r\n"+
"chr17	7281535	7282064\r\n"+
"chr3	14598275	14598658\r\n"+
"chr14	68181356	68181953\r\n"+
"chr20	2652299	2653012\r\n"+
"chr5	171450698	171451244\r\n"+
"chr10	123154004	123154481\r\n"+
"chr1	193059197	193059697\r\n"+
"chr9	99433615	99433973\r\n"+
"chr10	122307801	122308378\r\n"+
"chr1	226308872	226309459\r\n"+
"chr8	101080595	101081281\r\n"+
"chr3	121165325	121165841\r\n"+
"chr6	6820577	6821174\r\n"+
"chr6	161353209	161353574\r\n"+
"chr2	46909740	46910164\r\n"+
"chr7	135232780	135233128\r\n"+
"chr3	155110972	155111449\r\n"+
"chr16	28863271	28863678\r\n"+
"chr19	49568047	49568558\r\n"+
"chr22	45163529	45163965\r\n"+
"chr10	27893307	27893756\r\n"+
"chr19	38417797	38418531\r\n"+
"chr6	20291715	20292025\r\n"+
"chr1	156054485	156054997\r\n"+
"chr4	186932192	186933193\r\n"+
"chr2	226118450	226118923\r\n"+
"chr6	111105564	111105956\r\n"+
"chr1	84319696	84320093\r\n"+
"chr2	38750988	38751770\r\n"+
"chr15	64093676	64094125\r\n"+
"chr1	234975041	234975561\r\n"+
"chr5	176536508	176536839\r\n"+
"chr1	42658215	42658905\r\n"+
"chr9	126517164	126517496\r\n"+
"chr6	45433627	45434086\r\n"+
"chr13	110712754	110713229\r\n"+
"chr1	19011078	19011479\r\n"+
"chr2	188292503	188293147\r\n"+
"chr2	120791824	120792552\r\n"+
"chr13	95301006	95301855\r\n"+
"chr13	43057645	43058028\r\n"+
"chr16	85350964	85351296\r\n"+
"chr18	3596668	3597045\r\n"+
"chr17	76726323	76726874\r\n"+
"chr11	10633365	10633712\r\n"+
"chr1	7265768	7266357\r\n"+
"chr6	96521551	96521950\r\n"+
"chr1	53685236	53685691\r\n"+
"chr8	141026715	141027195\r\n"+
"chr6	42136568	42137269\r\n"+
"chr18	40120996	40121427\r\n"+
"chr5	81851539	81852167\r\n"+
"chr16	30534636	30535277\r\n"+
"chr3	139310390	139311021\r\n"+
"chrX	9708975	9709296\r\n"+
"chr3	184561345	184561773\r\n"+
"chr2	223815145	223815574\r\n"+
"chr1	10679612	10679917\r\n"+
"chr6	147709481	147710102\r\n"+
"chr5	142223611	142224124\r\n"+
"chr20	43955307	43956147\r\n"+
"chr10	100653916	100654404\r\n"+
"chr11	108616621	108616964\r\n"+
"chr16	21188715	21189023\r\n"+
"chr10	61837239	61837576\r\n"+
"chr7	17461957	17462688\r\n"+
"chr5	93192101	93192442\r\n"+
"chr14	77302977	77303306\r\n"+
"chr1	12478055	12478797\r\n"+
"chr6	36243597	36243880\r\n"+
"chr5	130190018	130190369\r\n"+
"chr21	29114874	29115285\r\n"+
"chr1	45892443	45892815\r\n"+
"chr12	89225363	89225708\r\n"+
"chr10	21292733	21293282\r\n"+
"chr10	84328489	84329073\r\n"+
"chr17	16296137	16296432\r\n"+
"chr11	64917160	64917655\r\n"+
"chr1	46394113	46394708\r\n"+
"chr2	9630770	9631416\r\n"+
"chrX	2188349	2188828\r\n"+
"chr4	101790502	101791433\r\n"+
"chr8	25690252	25690679\r\n"+
"chr14	97266646	97267026\r\n"+
"chr17	65243790	65244284\r\n"+
"chr1	237873458	237874030\r\n"+
"chr5	174520424	174520878\r\n"+
"chr15	41490340	41490690\r\n"+
"chr4	30769972	30770604\r\n"+
"chr19	10417918	10418498\r\n"+
"chr1	180521530	180521879\r\n"+
"chr3	114775467	114775793\r\n"+
"chr16	1942898	1943395\r\n"+
"chr2	232901670	232902454\r\n"+
"chr3	157174853	157175299\r\n"+
"chr22	18077775	18078280\r\n"+
"chr2	223816169	223816606\r\n"+
"chr11	38547877	38548279\r\n"+
"chr4	83548409	83548831\r\n"+
"chr3	41265888	41266322\r\n"+
"chr19	12440850	12441428\r\n"+
"chr11	130181519	130181952\r\n"+
"chr2	215410779	215411245\r\n"+
"chr2	45964211	45964614\r\n"+
"chr15	88904671	88905026\r\n"+
"chr11	69702326	69702662\r\n"+
"chr20	50561142	50561904\r\n"+
"chr2	177263369	177263737\r\n"+
"chr12	65533264	65533998\r\n"+
"chr3	172129981	172130267\r\n"+
"chr2	157898509	157898944\r\n"+
"chr3	12471114	12471705\r\n"+
"chr9	96566326	96567071\r\n"+
"chr22	39205315	39205704\r\n"+
"chr12	65603472	65603888\r\n"+
"chr7	93043317	93043768\r\n"+
"chr11	34916069	34916763\r\n"+
"chr6	92978369	92978677\r\n"+
"chr13	20174782	20175248\r\n"+
"chr1	164465917	164466430\r\n"+
"chr20	47725297	47725671\r\n"+
"chr8	22352522	22353055\r\n"+
"chr7	148339272	148339625\r\n"+
"chr6	142235280	142235656\r\n"+
"chr2	85468726	85469268\r\n"+
"chr14	54169412	54169946\r\n"+
"chr7	9097634	9098065\r\n"+
"chr6	79574663	79575004\r\n"+
"chr2	172098842	172099342\r\n"+
"chr14	24036137	24036530\r\n"+
"chr15	70526525	70526924\r\n"+
"chr8	140510838	140511373\r\n"+
"chr2	200408942	200409315\r\n"+
"chr10	73887585	73888107\r\n"+
"chr11	109864179	109864722\r\n"+
"chr9	112119421	112119861\r\n"+
"chr1	235642087	235642666\r\n"+
"chr10	122140809	122141557\r\n"+
"chr9	136051838	136052305\r\n"+
"chr6	121479467	121480009\r\n"+
"chr4	186845217	186846028\r\n"+
"chr22	30634449	30634829\r\n"+
"chr1	82565165	82565960\r\n"+
"chr16	85330803	85331102\r\n"+
"chr17	64505421	64505747\r\n"+
"chr2	227191422	227191744\r\n"+
"chr16	9047698	9048336\r\n"+
"chr12	71440606	71441260\r\n"+
"chr1	43929586	43929935\r\n"+
"chr17	15649778	15650280\r\n"+
"chr12	75712109	75712616\r\n"+
"chr5	151447379	151447847\r\n"+
"chr11	76784419	76784869\r\n"+
"chr17	58535485	58535838\r\n"+
"chr2	181761991	181762339\r\n"+
"chr10	28678006	28678384\r\n"+
"chr19	43504111	43504434\r\n"+
"chr2	120935678	120936095\r\n"+
"chr4	1022629	1022987\r\n"+
"chr3	111735250	111735697\r\n"+
"chr1	84603795	84604248\r\n"+
"chr3	30227693	30228179\r\n"+
"chr16	55035089	55035416\r\n"+
"chr8	130528367	130528692\r\n"+
"chr15	67174940	67175361\r\n"+
"chr13	31044438	31044749\r\n"+
"chr1	10937394	10937722\r\n"+
"chr2	78967276	78967629\r\n"+
"chr11	61730235	61730675\r\n"+
"chr7	116218145	116218602\r\n"+
"chr1	25861303	25861861\r\n"+
"chr7	128869142	128869818\r\n"+
"chr5	145587696	145588052\r\n"+
"chr1	116909800	116910840\r\n"+
"chr5	82352449	82352991\r\n"+
"chr2	223602229	223602518\r\n"+
"chr11	46845963	46846512\r\n"+
"chr12	46523911	46524464\r\n"+
"chr14	90521060	90521684\r\n"+
"chr5	127999956	128000270\r\n"+
"chr1	8189962	8190329\r\n"+
"chr15	75104782	75105176\r\n"+
"chr5	174751108	174751710\r\n"+
"chr16	11328522	11328902\r\n"+
"chr2	181790706	181791120\r\n"+
"chr1	79193294	79193780\r\n"+
"chr15	99790833	99791145\r\n"+
"chr4	169010195	169010572\r\n"+
"chr2	191866743	191867342\r\n"+
"chr9	22203662	22204118\r\n"+
"chr16	3500715	3501171\r\n"+
"chr1	19925315	19925704\r\n"+
"chr11	28702047	28702538\r\n"+
"chr4	17336633	17337044\r\n"+
"chr3	114880100	114880507\r\n"+
"chr5	112160631	112161035\r\n"+
"chr2	159615415	159616249\r\n"+
"chr8	129707659	129708378\r\n"+
"chr11	86193524	86193811\r\n"+
"chr15	37877817	37878271\r\n"+
"chr10	102917978	102918721\r\n"+
"chr10	52319073	52319477\r\n"+
"chr5	74685023	74685484\r\n"+
"chr2	25187749	25188205\r\n"+
"chr13	87123977	87124412\r\n"+
"chr19	7522372	7522846\r\n"+
"chr5	142169369	142169942\r\n"+
"chr11	15106590	15107009\r\n"+
"chr17	81386552	81387739\r\n"+
"chr10	113326187	113326581\r\n"+
"chr2	66334694	66335022\r\n"+
"chr14	68464611	68465068\r\n"+
"chr5	174951096	174951473\r\n"+
"chr16	77877744	77878061\r\n"+
"chr8	30052458	30052840\r\n"+
"chr19	50975822	50976140\r\n"+
"chr4	186728693	186729031\r\n"+
"chr7	32942856	32943509\r\n"+
"chr18	11500470	11500806\r\n"+
"chr10	63738463	63738813\r\n"+
"chr17	82751841	82752372\r\n"+
"chr2	32357051	32357724\r\n"+
"chr2	161128825	161129403\r\n"+
"chr16	84138754	84139547\r\n"+
"chr11	69297135	69297723\r\n"+
"chr20	381842	382342\r\n"+
"chr1	202222233	202222649\r\n"+
"chr3	42853777	42854079\r\n"+
"chr1	71490507	71490830\r\n"+
"chr15	41774340	41774885\r\n"+
"chr17	81397330	81397732\r\n"+
"chr16	73048192	73048747\r\n"+
"chr3	168024505	168024913\r\n"+
"chr15	64536189	64536700\r\n"+
"chr12	123141659	123141958\r\n"+
"chr8	135456648	135457298\r\n"+
"chr17	69327001	69327546\r\n"+
"chr17	71438655	71439115\r\n"+
"chr5	16187684	16187988\r\n"+
"chr15	89334732	89335422\r\n"+
"chr4	156931327	156931677\r\n"+
"chr7	5529608	5530182\r\n"+
"chr20	41008311	41008721\r\n"+
"chrX	87409428	87409864\r\n"+
"chr7	73564407	73564713\r\n"+
"chr7	66110785	66111142\r\n"+
"chr18	9779646	9780030\r\n"+
"chr3	124878567	124879294\r\n"+
"chr5	68432676	68433028\r\n"+
"chr14	45743900	45744225\r\n"+
"chr1	185733747	185734606\r\n"+
"chr12	113007176	113007619\r\n"+
"chr17	50035461	50035838\r\n"+
"chr17	79074706	79075074\r\n"+
"chr17	27957135	27957477\r\n"+
"chr14	53463172	53463766\r\n"+
"chr8	100510119	100510446\r\n"+
"chr16	12803372	12804081\r\n"+
"chr1	156693037	156693500\r\n"+
"chr4	167428187	167428561\r\n"+
"chr6	45655899	45656350\r\n"+
"chr11	95789538	95790197\r\n"+
"chr3	111926262	111926634\r\n"+
"chr9	129080737	129081210\r\n"+
"chr16	73015689	73016009\r\n"+
"chr9	128558440	128558848\r\n"+
"chr1	119294782	119295094\r\n"+
"chr19	35845310	35845801\r\n"+
"chr8	60792927	60793232\r\n"+
"chr8	100832766	100833143\r\n"+
"chr5	56816595	56817094\r\n"+
"chr3	74789119	74789571\r\n"+
"chr14	54102041	54102559\r\n"+
"chr1	151494769	151495149\r\n"+
"chr2	147352745	147353351\r\n"+
"chr1	113308313	113308728\r\n"+
"chr20	18793922	18794284\r\n"+
"chr12	65306716	65307274\r\n"+
"chr3	149480369	149480712\r\n"+
"chr12	31491786	31492497\r\n"+
"chr13	48037391	48038146\r\n"+
"chr10	78047334	78047645\r\n"+
"chr8	127331892	127332347\r\n"+
"chr20	4014032	4014418\r\n"+
"chr7	17099459	17099936\r\n"+
"chr2	175167810	175168620\r\n"+
"chr18	58545336	58545657\r\n"+
"chr4	153246731	153247189\r\n"+
"chr2	235048991	235049424\r\n"+
"chr2	91477738	91478350\r\n"+
"chr17	50161463	50161850\r\n"+
"chr10	95467230	95467546\r\n"+
"chr4	188214909	188215245\r\n"+
"chr17	3988764	3989106\r\n"+
"chr5	132387572	132387882\r\n"+
"chr14	55454522	55454984\r\n"+
"chr17	72338967	72339706\r\n"+
"chr7	92245463	92246101\r\n"+
"chr16	85553205	85553781\r\n"+
"chr1	151165808	151166222\r\n"+
"chr5	38809749	38810093\r\n"+
"chr22	38057036	38057490\r\n"+
"chr3	32177081	32177582\r\n"+
"chr1	9163656	9164336\r\n"+
"chr8	133217830	133218410\r\n"+
"chr7	102991402	102992039\r\n"+
"chr20	22691551	22691957\r\n"+
"chr19	7373909	7374256\r\n"+
"chr2	237184623	237184968\r\n"+
"chr3	18098709	18099329\r\n"+
"chr11	111878537	111879575\r\n"+
"chr6	1054897	1055429\r\n"+
"chr19	16160831	16161523\r\n"+
"chr16	67562096	67562536\r\n"+
"chr11	74398515	74399032\r\n"+
"chr1	234612474	234613006\r\n"+
"chr4	4761141	4761791\r\n"+
"chr11	40602815	40603434\r\n"+
"chr2	226585173	226585605\r\n"+
"chr3	161349861	161350284\r\n"+
"chr9	128078523	128079315\r\n"+
"chr1	68128241	68128596\r\n"+
"chr10	3852368	3852829\r\n"+
"chr6	32255161	32255836\r\n"+
"chr11	8384313	8384865\r\n"+
"chr12	45192332	45192719\r\n"+
"chr11	111960880	111961246\r\n"+
"chr2	12816152	12816740\r\n"+
"chr1	225710854	225711251\r\n"+
"chr10	11685790	11686121\r\n"+
"chr18	48944226	48944612\r\n"+
"chr8	58553002	58553512\r\n"+
"chr17	59220285	59220780\r\n"+
"chr10	7687247	7687587\r\n"+
"chr12	46433863	46434258\r\n"+
"chr19	35757895	35758695\r\n"+
"chr3	126287403	126287802\r\n"+
"chr17	932884	933216\r\n"+
"chr19	12722369	12722919\r\n"+
"chr10	56537870	56538309\r\n"+
"chr5	132830065	132830875\r\n"+
"chr17	49191855	49192723\r\n"+
"chr4	56026619	56026924\r\n"+
"chr17	82037291	82038221\r\n"+
"chr17	59786391	59786913\r\n"+
"chr8	112115135	112115488\r\n"+
"chr20	34525841	34526312\r\n"+
"chr10	104338071	104338867\r\n"+
"chr2	158795133	158795472\r\n"+
"chr22	38924382	38924870\r\n"+
"chr5	145946557	145946893\r\n"+
"chr12	31103657	31104031\r\n"+
"chr7	80883293	80883956\r\n"+
"chr5	76805999	76806652\r\n"+
"chr10	88897350	88897795\r\n"+
"chr5	142362775	142363117\r\n"+
"chr3	194269534	194269995\r\n"+
"chr13	21637197	21637813\r\n"+
"chr6	42050487	42051055\r\n"+
"chr19	55216358	55216813\r\n"+
"chr7	34747982	34748826\r\n"+
"chr14	53979384	53979818\r\n"+
"chr3	194296420	194296793\r\n"+
"chr12	75974894	75975277\r\n"+
"chr8	116833328	116833628\r\n"+
"chr8	103281148	103281486\r\n"+
"chr16	87076876	87077582\r\n"+
"chr10	75405135	75405473\r\n"+
"chr9	114153822	114154381\r\n"+
"chr21	35294300	35294829\r\n"+
"chr3	59575080	59575605\r\n"+
"chr2	209495881	209496363\r\n"+
"chr14	105020894	105021304\r\n"+
"chr4	175935049	175935393\r\n"+
"chr12	63917993	63918508\r\n"+
"chr8	142184101	142184502\r\n"+
"chrX	141405818	141406534\r\n"+
"chr14	64841175	64841563\r\n"+
"chr6	4018240	4018938\r\n"+
"chr22	47328849	47329287\r\n"+
"chr1	225817660	225818065\r\n"+
"chr3	52128085	52128534\r\n"+
"chr22	23521482	23522114\r\n"+
"chr15	60391103	60391467\r\n"+
"chr7	112789922	112790539\r\n"+
"chr6	79545018	79545377\r\n"+
"chr12	69585193	69585686\r\n"+
"chr20	2315122	2315482\r\n"+
"chr14	97077375	97077735\r\n"+
"chr5	91190068	91190615\r\n"+
"chr6	56714964	56715566\r\n"+
"chr4	40284011	40284368\r\n"+
"chr5	82313077	82313734\r\n"+
"chr20	46293121	46293451\r\n"+
"chr11	25702414	25702813\r\n"+
"chr14	21103573	21104471\r\n"+
"chrX	107118614	107119010\r\n"+
"chr5	1003243	1003652\r\n"+
"chr1	234978687	234979089\r\n"+
"chr1	51316126	51316543\r\n"+
"chr9	129460364	129461009\r\n"+
"chr7	55129684	55130049\r\n"+
"chr15	67106513	67107330\r\n"+
"chr8	127222641	127223289\r\n"+
"chr15	75843294	75843623\r\n"+
"chr12	30795083	30796180\r\n"+
"chr12	108854475	108854914\r\n"+
"chr14	77376949	77377725\r\n"+
"chr7	45978100	45978520\r\n"+
"chr5	143245918	143246407\r\n"+
"chr9	137301112	137301508\r\n"+
"chr9	97501105	97501591\r\n"+
"chr5	137753895	137754898\r\n"+
"chr4	91064482	91064879\r\n"+
"chr12	49059692	49060270\r\n"+
"chr19	16111342	16111944\r\n"+
"chr8	127126620	127127019\r\n"+
"chr12	71710294	71710713\r\n"+
"chr3	113098123	113098539\r\n"+
"chr19	18636085	18636484\r\n"+
"chr10	50739572	50740276\r\n"+
"chr11	45259659	45260009\r\n"+
"chr12	62602375	62602906\r\n"+
"chr10	12707627	12707987\r\n"+
"chr22	28977477	28977928\r\n"+
"chr6	7548312	7548854\r\n"+
"chr2	146830864	146831334\r\n"+
"chr6	10434235	10434758\r\n"+
"chr13	33260343	33260724\r\n"+
"chr9	123375794	123376188\r\n"+
"chr1	53413250	53413946\r\n"+
"chr1	39901344	39902078\r\n"+
"chr6	162964626	162965165\r\n"+
"chr9	98215827	98216206\r\n"+
"chr16	51538768	51539301\r\n"+
"chr6	37819014	37819818\r\n"+
"chr7	74453644	74454013\r\n"+
"chr1	27392202	27392764\r\n"+
"chr11	31655393	31655830\r\n"+
"chr19	32971893	32972425\r\n"+
"chr1	152167900	152168260\r\n"+
"chr3	65963862	65964668\r\n"+
"chr3	171260944	171261276\r\n"+
"chrX	84046472	84046822\r\n"+
"chr2	61538198	61539006\r\n"+
"chr17	58136722	58137004\r\n"+
"chr6	163311830	163312161\r\n"+
"chr12	50764490	50765198\r\n"+
"chr1	218674402	218674838\r\n"+
"chr3	7112586	7112994\r\n"+
"chr5	134758499	134758987\r\n"+
"chr16	85759822	85760133\r\n"+
"chr12	65450541	65451007\r\n"+
"chr5	54310493	54311043\r\n"+
"chr7	5397085	5397627\r\n"+
"chr11	122271064	122271557\r\n"+
"chr10	57983175	57983629\r\n"+
"chr16	14399393	14399984\r\n"+
"chr13	28718720	28719287\r\n"+
"chr14	100408501	100408811\r\n"+
"chr6	29752730	29753354\r\n"+
"chr17	82160122	82160451\r\n"+
"chr3	30631248	30631625\r\n"+
"chr11	46616980	46617414\r\n"+
"chr12	120925716	120926136\r\n"+
"chr3	111585246	111585568\r\n"+
"chr5	1973526	1974094\r\n"+
"chr1	41840691	41841272\r\n"+
"chr7	80611783	80612277\r\n"+
"chr2	201532695	201533054\r\n"+
"chr5	173455092	173455665\r\n"+
"chr17	49851512	49852056\r\n"+
"chr1	202213284	202213642\r\n"+
"chr6	129493325	129493670\r\n"+
"chr10	52313259	52313868\r\n"+
"chr19	11374567	11375117\r\n"+
"chr10	102453055	102453346\r\n"+
"chr9	2241908	2242662\r\n"+
"chr4	151744648	151745027\r\n"+
"chr13	27383274	27383957\r\n"+
"chr11	61333058	61333923\r\n"+
"chr11	64545595	64545945\r\n"+
"chr10	32055472	32056537\r\n"+
"chr7	27171321	27171619\r\n"+
"chr15	85786107	85786712\r\n"+
"chr11	13261558	13261960\r\n"+
"chrX	38803579	38804386\r\n"+
"chr19	46638586	46639194\r\n"+
"chr17	78840702	78841296\r\n"+
"chrX	106933523	106933918\r\n"+
"chr5	21774822	21775206\r\n"+
"chr6	121615559	121615898\r\n"+
"chr16	1002643	1003130\r\n"+
"chr17	49414805	49415277\r\n"+
"chr2	85753692	85754118\r\n"+
"chrX	72572566	72573211\r\n"+
"chr1	8211880	8212377\r\n"+
"chr16	89624759	89625177\r\n"+
"chr17	66951902	66952417\r\n"+
"chr9	24545292	24545689\r\n"+
"chr9	122828271	122828821\r\n"+
"chr7	151608111	151608567\r\n"+
"chr8	48921216	48921598\r\n"+
"chr12	110813307	110813669\r\n"+
"chr1	234957400	234957997\r\n"+
"chr1	171644852	171645267\r\n"+
"chr16	48385471	48385955\r\n"+
"chr20	62523902	62524231\r\n"+
"chr9	83296196	83296509\r\n"+
"chr15	66883341	66883750\r\n"+
"chr8	110848165	110848538\r\n"+
"chr15	31398614	31399072\r\n"+
"chr4	83618502	83618805\r\n"+
"chr10	75959272	75959627\r\n"+
"chr12	101566333	101566631\r\n"+
"chr9	21591566	21591980\r\n"+
"chr2	226631709	226632112\r\n"+
"chr5	68334017	68334700\r\n"+
"chr1	68384331	68384821\r\n"+
"chr17	53626347	53626639\r\n"+
"chr12	82650649	82651009\r\n"+
"chr22	47125589	47125932\r\n"+
"chr12	65510446	65511386\r\n"+
"chr3	45818148	45818636\r\n"+
"chr1	186082293	186082646\r\n"+
"chr4	114015394	114015895\r\n"+
"chr3	191246678	191247075\r\n"+
"chr20	33192923	33193357\r\n"+
"chr20	63682365	63682838\r\n"+
"chr1	199791564	199792065\r\n"+
"chr4	37685628	37686114\r\n"+
"chr1	162690966	162691255\r\n"+
"chr1	13749127	13749492\r\n"+
"chr7	130887229	130887663\r\n"+
"chr6	55713409	55713776\r\n"+
"chr1	63714274	63714617\r\n"+
"chr5	135438085	135438459\r\n"+
"chr4	87391126	87391747\r\n"+
"chr4	113951278	113951604\r\n"+
"chr10	92914533	92914813\r\n"+
"chr12	65500158	65500842\r\n"+
"chr19	2540793	2541163\r\n"+
"chr3	189324606	189325122\r\n"+
"chr5	168114497	168114834\r\n"+
"chr7	37031973	37032471\r\n"+
"chr2	239247480	239248101\r\n"+
"chr12	98503782	98504378\r\n"+
"chr1	18926418	18926909\r\n"+
"chr3	23685509	23686187\r\n"+
"chr8	140668311	140668666\r\n"+
"chr14	104116338	104117372\r\n"+
"chr15	89087880	89088441\r\n"+
"chr11	122180384	122180816\r\n"+
"chr20	5119760	5120185\r\n"+
"chr10	90087256	90087677\r\n"+
"chr22	42079467	42080061\r\n"+
"chr1	30823800	30824565\r\n"+
"chr1	84390218	84390586\r\n"+
"chr16	85516075	85516370\r\n"+
"chr1	43143364	43143920\r\n"+
"chr3	139326782	139327104\r\n"+
"chr15	70892310	70893088\r\n"+
"chr18	50287557	50288227\r\n"+
"chr1	63523103	63523590\r\n"+
"chr17	64385170	64385521\r\n"+
"chr11	3797368	3798066\r\n"+
"chr6	137805552	137806002\r\n"+
"chr19	7853067	7853669\r\n"+
"chr1	211579070	211579792\r\n"+
"chr6	41581381	41582042\r\n"+
"chr22	24601590	24601892\r\n"+
"chr9	110977275	110977750";
        $('#page1').click();
        $('#page3').click();

    	$("#genesource1").prop("checked",true);
    	$("input[name='grch']").prop("checked",true);
    	$(".grch1").removeClass("active");
    	$(".grch2").addClass("active");

    	$('#myTab a[href="#myTab3"]').tab('show')
    	
    	$('#d5').click();
    	$('#seall').click();
    });
 });
  $("#analysis_reset").click(function(){
        $('#page1').click();
  })
  
  $("#genesource1").click(function(){
        $('.bed_warning').attr("style", "display: none; height: 20px;");
        $('.line_warning').attr("style", "");
  })

  $("#genesource2").click(function(){
        $('.bed_warning').attr("style", "display: inline-block; height: 20px;");
        $('.line_warning').attr("style", "margin-bottom: 10px;margin-top: 10px;");
  })

	$("#lola1b").click(function () {
        $(".lola_1").addClass("is_show");
        $(".lola_2").removeClass("is_show");
    });
    $("#lola1a").click(function () {
        $(".lola_1").removeClass("is_show");
        $(".lola_2").addClass("is_show");
    });
	$("#file").click(function () {
        $("input[name='source']").prop("checked",true);
    });
	$(".grch1").click(function () {
        $("textarea[name='input']").val("");
        $("textarea[name='input']").removeAttr("placeholder");
        $("textarea[name='input']").attr("placeholder", "Please enter the genomic region of hg19 version");
    });
	$(".grch2").click(function () {
        $("textarea[name='input']").val("");
        $("textarea[name='input']").removeAttr("placeholder");
        $("textarea[name='input']").attr("placeholder", "Please enter the genomic region of hg38 version");
    });
    $("#olo1").click(function () {
        $("#oloshow").hide();
    });
    $("#olo2").click(function () {
        $("#oloshow").show();
    });
    
    var filesum = 1;
    var html = "";
    $("#addfileon").click(function () {
    	if(filesum==1){
    		html = '</br><label>Choose bed file</label><label class="radio-inline"><input id="file5" type="file" name="file5"/></label>'
        	$("#addfileon").before(html);
        	filesum+=1;
    	}else if(filesum==2){
    		html = '</br><label>Choose bed file</label><label class="radio-inline"><input id="file6" type="file" name="file6"/></label>'
        	$("#addfileon").before(html);
        	filesum+=1;
    	}else if(filesum==3){
    		html = '</br><label>Choose bed file</label><label class="radio-inline"><input id="file7" type="file" name="file7"/></label>'
        	$("#addfileon").before(html);
        	filesum+=1;
    	}else{
    		$("#addfileon").before('</br><label style="color:red;">Too many files !</label>');
    		$("#addfileon").hide();
    	}	
    });
    $("#analysis_reset").click(function(){
    	$("#oloshow").hide();
        $(".lola_1").removeClass("is_show");
        $(".lola_2").addClass("is_show");
    });

    $("input[name='judge_email']").click(function(){
        if ($("input[name='judge_email']")[0].checked) {
    	    $("#email").removeAttr("disabled");
    	    $("input[name='judge_email']")[1].checked = false;
        } else {
    	    $("#email").attr("disabled","disabled");
    	    $("input[name='judge_email']")[1].checked = true;
        }
    });
    $("input[name='judge_email']")[0].checked = false;
    $("input[name='judge_email']")[1].checked = true;
    $("#email").attr("disabled","disabled");
</script>

</body>
</html>