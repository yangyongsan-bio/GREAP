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
    
   <style type="text/css">
      
	 .panel-default{
            border:2px solid #d9d9d9;
        }
        #accordion .panel-heading{
            padding: 0;
            border: none;
            border-radius: 0;
            position: relative;
        }
        #accordion .panel-title a{
            display: block;
            padding: 15px 20px;
            margin: 0;
            background:#ffffff;
            font-size: 20px;
            font-weight: 700;
            color:#2c3e50;
            letter-spacing: 1px;
            border-color: #2c3e50;
            position: relative;
        }
        .panel-group .panel-heading{
            border-bottom: black;
        }
        #accordion .panel-title a.collapsed{
            background: #F6F5F500}
        #accordion .panel-title a:before,
        #accordion .panel-title a.collapsed:before{
            content: "-";
            font-family: fontawesome;
            width: 30px;
            height: 30px;
            line-height: 19px;
            border-radius: 50%;
            background: #fe7725;
            font-size: 50px;
            font-weight: normal;
            color: #fff;
            text-align: center;
            border: 1.5px solid #e8ecf1;
            position: absolute;
            top: 10px;
            right: 14px;
        }
        #accordion .panel-title a.collapsed:before{
            content: "+";
            font-family: fontawesome;
            width: 30px;
            height: 30px;
            line-height: 27px;
            border-radius: 50%;
            background: #2c3e50;
            font-size: 30px;
            font-weight: normal;
            color: #fff;
            text-align: center;
            border: 1.5px solid #e8ecf1;
            position: absolute;
            top: 10px;
            right: 14px;
        }
        #accordion .panel-title a:after,
        #accordion .panel-title a.collapsed:after{
            content: "";
            width: 17px;
            height: 7px;
            
            position: absolute;
            top: 22px;
            right: 0;
        }

        #accordion .panel-body{
            border-top:2px solid #d9d9d9;
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
            background: #fe7725;
            position: absolute;
            bottom: 0;
            left: 0;
        }
        
    </style>

</head>

<body id="body">

<!--==========================
  Header
============================-->
<#include "nav/navbar.ftl" />

<div class="container" style="width: 97%;" id="search_set"> 
		    <div class="row">
		        
		        <div class="col-md-12" style="margin-top: 50px;">
		            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		                <div class="panel panel-default" style="margin-top: 100px;">
		                    <div class="panel-heading" role="tab" id="headingOne">
		                        <h4 class="panel-title">
		                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
		                                 <img src="${base.contextPath}/static/img/search.png" style="width: 25px;"> Search by data class
		                            </a>
		                        </h4>
		                    </div>
		                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
		                        <div class="panel-body" >
		                           <form action="../search/set"  method="post" >
                                    <div class="row" style="margin-top: 30px;">
                                        <div class="col-md-6" style="margin-top: -10px;">
                                            <div class="form-group">
                                                <label for="genome" style="margin-top: 10px;">Reference genome</label>
                                                <select class="form-control" name="genome" id="genome1" style="width: 60%;">
													<option value="hg19">hg19</option>
													<option value="hg38">hg38</option>
												</select>
                                                <label for="exampleSelect1" style="margin-top: 10px;">Class</label>
                                                <select class="form-control" v-model='Data_Type' @change="list_Tissue_Name()" name="datatype" id="datatype" style="width: 60%;">
													<option v-for="Data_Type in Data_Types" v-bind:value="Data_Type" >{{Data_Type}}</option>
												</select>
												<label for="exampleSelect1" style="margin-top: 10px;">Sub class</label>
												                            
                                                <select class="form-control" v-model='Tissue_Name' name="dataset" id="tissuetype" style="width: 60%;">
													<option v-for="Tissue_Name in Tissue_Names" v-bind:value="Tissue_Name">{{Tissue_Name}}</option>
												</select>
												
                                                <div class="col-lg-12" style="margin-top: 30px;padding-left: 0px;">
								                    <button type="submit" class="btn btn-danger" id="searchcheck1">Submit</button>
								                    <button type="reset" class="btn btn-danger">Reset</button>
								                    <button type="button" class="btn btn-danger" id ="example1">Example</button>
							                    </div>                              
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div id="search1" style="width: 800px;height:400px;margin-left: -160px;"></div>
                                         </div>
                                    </div> 
                                    
                                  </form>
		                        </div>
		                    </div>
		                </div>
		                <div class="panel panel-default">
		                    <div class="panel-heading" role="tab" id="headingTwo">
		                        <h4 class="panel-title">
		                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
		                                <img src="${base.contextPath}/static/img/search.png" style="width: 25px;"> Search by genomic region
		                            </a>
		                        </h4>
		                    </div>
		                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
		                        <div class="panel-body">
		                           <form  action="../search/region"  method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="genome" style="margin-top: 10px;">Reference genome</label>
                                                <select class="form-control" name="genome" id="genome2" style="width: 60%;">
													<option value="hg19">hg19</option>
													<option value="hg38">hg38</option>
												</select>
                                                <label for="exampleSelect1" style="margin-top: 10px;">Class</label>
                                                <select class="form-control" v-model='Data_Type' @change="list_Tissue_Name()" name="datatype" id="datatype" style="width: 60%;">
													<option v-for="Data_Type in Data_Types" v-bind:value="Data_Type" >{{Data_Type}}</option>
												</select>
												<label for="exampleSelect1" style="margin-top: 10px;">Sub class</label>
                                                <select class="form-control" v-model='Tissue_Name' name="dataset" id="tissuetype" style="width: 60%;">
													<option v-for="Tissue_Name in Tissue_Names" v-bind:value="Tissue_Name">{{Tissue_Name}}</option>
												</select>
												<label for="exampleSelect1" style="margin-top: 10px;">Genomic position</label>
                                                <div class="row">
	                                                <input name="chr" id="chr" placeholder="Chr" class="form-control" style="width: 18%; height: 30px; float: left; margin-left: 15px;"><p style="float: left;">&nbsp;:&nbsp;</p> 
	                                                <input name="start" id="start" placeholder="Start" class="form-control" style="width: 18%; height: 30px; float: left;"><p style="float: left;">&nbsp;-&nbsp;</p> 
	                                                <input name="end" id="end" placeholder="End" class="form-control" style="width: 18%; height: 30px; float: left;">
                                                </div>
                                                
                                                <div class="col-lg-12" style="margin-top: 30px;padding-left: 0px;">
								                    <button type="submit" class="btn btn-danger" id="searchcheck2">Submit</button>
								                    <button type="reset" class="btn btn-danger">Reset</button>
								                    <button type="button" class="btn btn-danger" id="example2">Example</button>
							                    </div>
                                                                               
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div id="search2" style="width: 800px;height:400px;margin-left: -160px;"></div>
                                         </div>
                                    </div> 
                                    
                                   </form>
		                        </div>
		                    </div>
		                </div>
		                <div class="panel panel-default">
		                    <div class="panel-heading" role="tab" id="headingThree">
		                        <h4 class="panel-title">
		                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseTwo">
		                                <img src="${base.contextPath}/static/img/search.png" style="width: 25px;"> Search by gene name
		                            </a>
		                        </h4>
		                    </div>
		                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
		                        <div class="panel-body">
		                           <form  action="../search/gene"  method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="genome" style="margin-top: 10px;">Reference genome</label>
                                                <select class="form-control" name="genome" id="genome3" style="width: 60%;">
													<option value="hg19">hg19</option>
													<option value="hg38">hg38</option>
												</select>
                                                <label for="exampleSelect1" style="margin-top: 10px;">Class</label>
                                                <select class="form-control" v-model='Data_Type' @change="list_Tissue_Name()" name="datatype" id="datatype" style="width: 60%;">
													<option v-for="Data_Type in Data_Types" v-bind:value="Data_Type" >{{Data_Type}}</option>
												</select>
												<label for="exampleSelect1" style="margin-top: 10px;">Sub class</label>
                                                <select class="form-control" v-model='Tissue_Name' name="dataset" id="tissuetype" style="width: 60%;">
													<option v-for="Tissue_Name in Tissue_Names" v-bind:value="Tissue_Name">{{Tissue_Name}}</option>
												</select>
												<label for="exampleSelect1" style="margin-top: 10px;">Gene</label>
                                                <div class="row">
	                                                <input name="gene" id="gene" placeholder="gene name" class="form-control" style="width: 57%; height: 30px; float: left; margin-left: 15px;"> 
	                                               
                                                </div>
                                                
                                                <div class="col-lg-12" style="margin-top: 30px;padding-left: 0px;">
								                    <button type="submit" class="btn btn-danger" id="searchcheck3">Submit</button>
								                    <button type="reset" class="btn btn-danger">Reset</button>
								                    <button type="button" class="btn btn-danger" id="example3">Example</button>
							                    </div>
                                                                               
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div id="search2" style="width: 800px;height:400px;margin-left: -160px;"></div>
                                         </div>
                                    </div> 
                                    
                                   </form>
		                        </div>
		                    </div>
		                </div>
		                
		                
                        </div> 
		            </div>
		           </div>
		        </div>

<#include "nav/footer.ftl" />


<script src="${base.contextPath}/static/js/jquery.min.js"></script>
<script src="${base.contextPath}/static/js/vue.js"></script>
<script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${base.contextPath}/static/js/echarts/echarts.min.js"></script>
<script  src="${base.contextPath}/static/js/search.js"></script>


</body>
</html>