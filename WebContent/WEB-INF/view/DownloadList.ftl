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
    <link rel="stylesheet" href="${base.contextPath}/static/css/header.css">
    <link rel="stylesheet" href="${base.contextPath}/static/css/footer.css"/>

    <!-- DataTable buttons -->
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/buttons.dataTables.min.css"/>

    <script type="text/javascript" src="${base.contextPath}/static/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${base.contextPath}/static/js/dataTables.buttons.min.js"></script>
    <!--  buttons 导出功能-->
    <script src="${base.contextPath}/static/js/buttons.html5.min.js"></script>


    <link rel="stylesheet" href="${base.contextPath}/static/css/style.css" >
    <style type="text/css">
        tr {text-align: center;}
	    th {text-align: center;}
    </style>
</head>


<!--==========================
  Header
============================-->

<div class="row" style="margin-right: 0px;"><!---手风琴开始--->
            <div class="col-md-12 col-md-offset">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" >	<!---手风琴/panel-group/开始--->
                    <div class="panel panel-default" ><!---手风琴/tissue-based/start--->
                        <div class="panel-heading" role="tab" id="1" style="background-color: #034786;width: ;height: 50px;">
                            <h4 class="panel-title" style="margin-top: 5px;">
                                <span class="glyphicon glyphicon-list" style="color: white;"></span> <font style="color: white;">Download genomic regions for each Set</font>
                            </h4>
                        </div>
                        <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                            <div class="panel-body" style="overflow-x:auto;">
                                <table id="tablebrowse" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
									 <thead>
				                        <tr>
				                            <th>Class</th>
				                            <th>Sub Class</th>
				                            <th>Set</th>
				                            <th>Download</th>
				                        </tr>
				                    </thead>
						       </table>
                            </div>
                        </div>
                        
                        <div class="panel-heading" role="tab" id="1" style="background-color: #034786;width: ;height: 50px;">
                            <h4 class="panel-title" style="margin-top: 5px;">
                                <span class="glyphicon glyphicon-list" style="color: white;"></span> <font style="color: white;">Download genomic regions for each SubClass</font>
                            </h4>
                        </div>
                        <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                            <div class="panel-body" style="overflow-x:auto;">
                                <table id="tabledownsubclass" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
									 <thead>
				                        <tr>
				                            <th>Class</th>
				                            <th>Sub Class</th>
				                            <th>Download</th>
				                        </tr>
				                    </thead>
						       </table>
                            </div>
                        </div>
                        
                       <div class="panel-heading" role="tab" id="1" style="background-color: #034786;width: ;height: 50px;">
                            <h4 class="panel-title" style="margin-top: 5px;">
                                <span class="glyphicon glyphicon-list" style="color: white;"></span> <font style="color: white;">Download genomic regions for each Class</font>
                            </h4>
                        </div>
                        <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                            <div class="panel-body" style="overflow-x:auto;">
                                <table id="tabledownclass" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
									 <thead>
				                        <tr>
				                            <th>Class</th>
				                            <th>Download</th>
				                        </tr>
				                    </thead>
						       </table>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>	
 </div>
                                
<script>
     $(document).ready(function () {
           let judge19or38 = "";
           if ('${download_grch}' === "hg19") {
               judge19or38 = "19";
           }
         $('#tablebrowse').DataTable({
            destroy:true,
            serverSide: true,
            searching : true,
            ajax: {
                url: '../analysis/downloadlist',
                type: 'POST'
            },  
                "aLengthMenu": [10, 15],
				"scrollX": true,
				 columns: [
					{
						"data": 'dataclass',
					},
	                {
	                    "data": 'subclass',
	                },
	                {
					    "data": 'set',
					},
	                {
					    "data": '',
					}
	            ],
	            "columnDefs": [
                      
                       {
                           "render": function (data, type, row, meta) {
	                           
	                           let dataclass = row.dataclass;
	                           if (row.dataclass === "ChromHMM") {
	                               dataclass = "HMM";
	                           }
	                           
                               var html = '<a href="http://www.liclab.net:8080/Greap_download/R' + judge19or38 + '/' + dataclass +judge19or38 + '/' + row.subclass + '/' + row.subclass + '/regions/' + row.set.replaceAll("~.", "/").replaceAll("~", "+") + '.bed"><img src="/Greap/static/img/download.png" style="width: 15px;margin-top: -5px;"></a>';
                               return html;
                           },
                           "targets": 3
                       }
   				],
	            
			});
	 });
	 
</script>
<script>
     $(document).ready(function () {
           let judge19or38 = "";
           if ('${download_grch}' === "hg19") {
               judge19or38 = "19";
           }
         $('#tabledownsubclass').DataTable({
            destroy:true,
            serverSide: true,
            searching : true,
            ajax: {
                url: '../analysis/downloadlist1',
                type: 'POST'
            },  
                "aLengthMenu": [5, 10, 15],
				"scrollX": true,
				 columns: [
					{
						"data": 'dataclass',
					},
	                {
	                    "data": 'subclass',
	                },
	                {
					    "data": '',
					}
	            ],
	            "columnDefs": [
                      
                       {
                           "render": function (data, type, row, meta) {
	                           if ('${download_grch}' === "hg19") {
						               var html = '<a href="http://www.liclab.net:8080/Greap_download/19/'+ row.dataclass +'/' + row.subclass + '.bed"><img src="/Greap/static/img/download.png" style="width: 15px;margin-top: -5px;"></a>';
                               	       return html;
						           }else{
							           var html = '<a href="http://www.liclab.net:8080/Greap_download/'+ row.dataclass +'/' + row.subclass + '.bed"><img src="/Greap/static/img/download.png" style="width: 15px;margin-top: -5px;"></a>';
	                               	   return html;
						           }
                               
                           },
                           "targets": 2
                       }
   				],
	            
			});
	 });
	 
</script>
<script>
     $(document).ready(function () {
           let judge19or38 = "";
           if ('${download_grch}' === "hg19") {
               judge19or38 = "19";
           }
         $('#tabledownclass').DataTable({
            destroy:true,
            serverSide: true,
            searching : true,
            ajax: {
                url: '../analysis/downloadlist2',
                type: 'POST'
            },  
                "aLengthMenu": [5, 10, 15],
				"scrollX": true,
				 columns: [
					{
						"data": 'dataclass',
					},
	                {
					    "data": '',
					}
	            ],
	            "columnDefs": [
                      
                       {
                           "render": function (data, type, row, meta) {
	                           
                               if ('${download_grch}' === "hg19") {
						               var html = '<a href="http://www.liclab.net:8080/Greap_download/class19/'+ row.dataclass +'.tar.gz"><img src="/Greap/static/img/download.png" style="width: 15px;margin-top: -5px;"></a>';
                               	       return html;
						           }else{
							           var html = '<a href="http://www.liclab.net:8080/Greap_download/class38/'+ row.dataclass +'.tar.gz"><img src="/Greap/static/img/download.png" style="width: 15px;margin-top: -5px;"></a>';
	                               	   return html;
						           }
                           },
                           "targets": 1
                       }
   				],
	            
			});
	 });
	 
</script>

</html>