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
                            <th>SE chr</th>
                            <th>SE start</th>
                            <th>SE end</th>
                            <th>SE ID</th>
							<#if se_dataclass?split("_")[0] == "dbSUPER">
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

                </table>
                <label>Annotated browse of Super_Enhancer in ${browser_region}</label>
                <div id='igv-div'></div>              
<script>

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
        url:"../position/SE/list",
        type:"post",
    },
    columns:[
        {"data": 'string6'},
        {"data": 'string7'},
        {"data": 'string8'},
        {"data": 'string1'},
        {"data": 'string2'},
        {"data": 'string3'},
        {"data": function(data){
		         if("${se_dataclass}" == "dbSUPER"){
		              return '<a href="https://asntech.org/dbsuper/details.php?se_id='+data.string4+'" target="_blank">'+data.string4+'</a>';
		         }else{
		              return '<a href="http://www.licpathway.net/sedb/search/search_se.php?se_id='+data.string4+'" target="_blank">'+data.string4+'</a>';
		         }
		         
		      },
        },
        {"data": function(data){
		         if("${se_dataclass}" == "dbSUPER"){
		              return data.string5.replace("dbSUPER_","");
		         }else{
		              return data.string5.split("_")[0]+'_'+data.string5.split("_")[1]+'_'+data.string5.split("_")[2] ;
		         }
		         
		      },
        },
        {
            "data": ''
        },
        <#if se_dataclass?split("_")[0] == "dbSUPER">
		<#else>
        {
            "data": ''
        },
        </#if>
        {
        "data": function(data){
	             return "<a href='http://genome.ucsc.edu/cgi-bin/hgTracks?db=${genome}&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position="+data.string6 + ":" + data.string7 + "-" + data.string8 + "&hgsid=1391680179_pGmIUy9Vavj4F4wLGeNdTYENVNHo' target='_blank'><img src='${base.contextPath}/static/img/ucsc.jpg' style='width:50px'></a> | <a href='http://39.98.139.1/GREAP_gb/?loc="+ data.string6 + ":" + data.string7 + "-" + data.string8 + "&tracks=${se_dataclass}&highlight=' target='_blank'><img src='${base.contextPath}/static/img/greap.jpg' style='width:50px'></a>";
		       	
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
	                   return "Sample_" + line.split("_")[1] + "_" + line.split("_")[2];
	               }
	               return split[0];
	           },
	           "targets": 7
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
	           "targets": 8
	       },
		<#if se_dataclass?split("_")[0] == "dbSUPER">
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
	           "targets": 9
	       }
	    </#if>
	]
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
                name: "${se_dataclass}",
                type: "annotation",
                format: "bed",
                indexed: false,
                // delimiter: ' ',
                url: "http://www.liclab.net:8080/Greap_download/igvchr" + is19or38 + "/SE/${se_dataclass}/${browser_region?split(":")[0]}.bed",
            },
        ]
  };
  igv.createBrowser(igvDiv, options)
    .then(function (browser) {
      console.log("Created IGV browser");
    })

</script>
</html>