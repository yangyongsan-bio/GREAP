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
                                <span class="glyphicon glyphicon-list" style="color: white;"></span> <font style="color: white;">Data Browse</font>
                            </h4>
                        </div>
                        <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="1">
                            <div class="panel-body" style="overflow-x:auto;">
                                <table id="tablebrowse" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
									 <thead>
				                        <tr>
				                            <th>Set</th>
				                            <th>Class</th>
				                            <th>Sub Class</th>
				                            <th>Count</th>
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
         $('#tablebrowse').DataTable({
            destroy:true,
            serverSide: true,
            searching : true,
            ajax: {
                url: '../analysis/browselist',
                type: 'POST'
            },  
                "aLengthMenu": [10, 15, 100, 500],
				"scrollX": true,
				 columns: [
					{
					    "data": 'set',
					},
					{
					    "data": 'dataclass',
					},
	                {
	                    "data": 'subclass',
	                },
	                {
	                    "data": 'num',
	                },
	            ],
	            "columnDefs": [
                       {
                           "render": function (data, type, row, meta) {
                               var html = '<a href="${base.contextPath}/analysis/set_detail?grch=${browse_grch}&datatype=' + row.dataclass + '&subclass='+ row.subclass + '&set='+ row.set + '&count='+ row.num + '&port=1' + '" target="_blank">' + row.set.replaceAll("~.", "/").replaceAll("~", "+") + '</a>';
                               return html;
                           },
                           "targets": 0
                       }
   				],
	            
			});
	 })
</script>


</html>