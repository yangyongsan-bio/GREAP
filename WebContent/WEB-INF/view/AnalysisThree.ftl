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

<form action="../analysisThree" method="post" enctype="multipart/form-data" style="margin-top:110px">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1">
                <div class="row">
                    <div class="col-lg-12 ">
                        <div class="form-group">
                            <input type="radio" id="genesource1" name="source" value="0" / checked> Paste your sequence: 
                            <textarea class="form-control" rows="12" id="input" name="input" spellcheck="false" style="width: 68%;"></textarea>
                            <br>
                            <input type="radio" id="genesource2" name="source" value="1" style="margin-bottom: 10px"/> Input your region:
                            <div class="row">
	                            <input name="chr" id="chr" placeholder="chr" class="form-control" style="width: 20%; height: 30px; float: left; margin-left: 15px;">
	                            <p style="float: left;">&nbsp;:&nbsp;</p> 
	                            <input name="start" id="start" placeholder="Star" class="form-control" style="width: 20%; height: 30px; float: left;">
	                            <p style="float: left;">&nbsp;-&nbsp;</p> 
	                            <input name="end" id="end" placeholder="End" class="form-control" style="width: 20%; height: 30px; float: left;">
                            </div>
                            <label>Background library</label>
			                <select class="form-control" id="number" name="number" style="width: 50%;">
	                                <option value="2" >Human</option>
	                                <option value="3" >Mouse</option>
	                                <option value="4" >Rat</option>
                            </select>
                            <br>
                            
                             
                        </div>
                    </div>
                         
                    <div class="col-lg-12">
	                    <button type="submit" class="btn btn-danger">Submit</button>
	                    <button type="reset" class="btn btn-danger">Reset</button>
	                    <button type="button" class="btn btn-danger">Example</button>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 ">
                    <!-- 放图片和介绍 -->
            </div>
        </div>
        <hr style="margin-bottom: 60px">
    </div>
</form>

<#include "nav/footer.ftl" />

<script src="${base.contextPath}/static/js/jquery.min.js"></script>
<script src="${base.contextPath}/static/js/vue.min.js"></script>
<script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
<script  src="${base.contextPath}/static/js/require.min.js"></script>
<script type="text/javascript" src="${base.contextPath}/static/js/bootstrap-multiselect.js"></script>
<script src="${base.contextPath}/static/js/analysis.js"></script>



</body>
</html>