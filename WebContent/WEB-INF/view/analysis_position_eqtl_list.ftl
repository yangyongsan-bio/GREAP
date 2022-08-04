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
	    td.details-control {
		    background: url('${base.contextPath}/static/img/details_open.png') no-repeat center center;
		    cursor: pointer;
		}
		tr.shown td.details-control {
		    background: url('${base.contextPath}/static/img/details_close.png') no-repeat center center;
		}
	    .popover {
				word-break: break-all;
				}
				
        .bottom{margin-top:12px;}
        .dataTables_filter{margin-top:8px;}
        .dataTables_length{margin-top:8px;}


    </style>
</head>


<!--==========================
  Header
============================-->
                
                <table id="example" class="table table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th>Input chr</th>
                            <th>Input start</th>
                            <th>Input end</th>
                            <th>eQTL chr</th>
                            <th>eQTL position</th>
                            <th>rsID</th>
                            <th>Visualization</th>
                        </tr>
                    </thead>
                </table>
                <label>Annotated browse of eQTL in ${browser_region}</label>
                <div id='igv-div'></div> 
                                
<script>
$('#example').DataTable({
    oLanguage: {
        sLoadingRecords: '<img src="${base.contextPath}/static/img/loading.gif" style="width: 350px;">'
    },
    dom: '<"pull-left"B>ft<"pull-left"i>p',
    buttons: [
               {
                    text: '<span class="glyphicon glyphicon-save"><font>Export</font></span>',
                    extend: 'csv'
                }],
    aLengthMenu : [5],
	scrollX: true,
    ajax: {
        url:"../position/eQTL/list",
        type:"post",
    },
    columns:[
        {"data": 'string6'},
        {"data": 'string7'},
        {"data": 'string8'},
        {"data": 'string1'},
        {"data": function(data){
                 var string = "${eqtl_dataclass}";
		         if(string.split("_")[1] == "10kb"){
		              return data.string3-10000;
		         }else if(string.split("_")[1] == "15kb"){
		              return data.string3-15000;
		         }else{
		              return data.string3-20000;
		         }
		         
		      },
        },
        {"data": function(data){
		           return '<a href="http://www.licpathway.net/VARAdb/search/search_rsid_result.php?rs_sel_type=rsIDs&rs='+data.string4+'" target="_blank">'+data.string4+'</a>';
		      },
        },
        {
        "data": function(data){
	             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string6 + ":" + data.string7 + "-" + data.string8 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string6 + ":" + data.string7 + "-" + data.string8 + "&tracks=${eqtl_dataclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
		       	
	           }
	      }
    ],
});
</script>
<script src="${base.contextPath}/static/js/igv.min.js"></script>
<script type="text/javascript">
  let is19or38 = '';
  if ("${genome}" === "hg19") {
  	is19or38 = '19';
  }
  var igvDiv = document.getElementById("igv-div");
  var options = {
    genome: "${genome}",
    locus: '${browser_region}',
    tracks: [
            {
                name: "${eqtl_dataclass}",
                type: "annotation",
                format: "bed",
                indexed: false,
                // delimiter: ' ',
                url: "http://www.liclab.net:8080/Greap_download/igvchr" + is19or38 + "/eQTL/${eqtl_dataclass}/${browser_region?split(":")[0]}.bed",
            },
        ]
  };
  igv.createBrowser(igvDiv, options)
    .then(function (browser) {
      console.log("Created IGV browser");
    })

</script>
</html>