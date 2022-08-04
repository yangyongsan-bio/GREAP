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
    <!--  buttons 瀵煎嚭鍔熻兘-->
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


        .black_overlay{
            display: none;
            position: fixed;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: gray;
            z-index:1001;
            -moz-opacity: 0.8;
            opacity:.80;
            filter: alpha(opacity=88);
        }
        .white {

            display: none;
            width: 500px;
            height: 450px;
            background-color: white;
            position: fixed;
            z-index:1005;
            margin-left:23%;
            margin-top:-20px;
            text-align:center;
            font-size:20px;
            font-family:榛戜綋;
            color:#84072a;

            z-index:1005;

        }
        .white_content {

            position: absolute;
            top: 14%;
            right:  0%;
            width: 100%;
            height: 80%;
            text-align:center;
            font-size:20px;
            font-family:榛戜綋;
            color:#84072a;
            background-color: white;
            z-index:1004;
            overflow: auto;
            overflow-y:auto;
        }
        .cl{
            position: absolute;

            top:0%;
            right: 0%;
            z-index: 1005;
            color: #84072a;
        }
        .kl{
            position: absolute;
            font-size:37px;
            top:3%;
            right: 45%;
            z-index: 1005;
            color: #5183f2;
        }
        hr {
            border: 0;
            border-bottom: 2px solid black;
            z-index: 1007;
        }
        .qp{
            position:absolute;
            margin-top: 100px;
            margin-left:100px;
            width: 500px;

            height: 500px;
        }

        #accordion .panel-heading{
            padding: 0;
            border: none;
            border-radius: 0;
            position: relative;
        }
        #accordion .panel-title {
            display: block;
            padding: 15px 20px;
            margin: 0;
            background: #034786;
            font-size: 18px;
            font-weight: 700;
            letter-spacing: 1px;
            color: #fff;
            border-radius: 0;
            position: relative;
        }
        #accordion .panel-title .collapsed{
            background: #034786;}

        #accordion .panel-body{
            border-left: 3px solid #034786;
            border-top: none;
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
            background: #034786;
            position: absolute;
            bottom: 0;
            left: 0;
        }
        .hr1{ height:1px;border:none;border-top:3px solid #346191;}
    </style>
</head>
<body id="body" style="overflow: -moz-hidden-unscrollable;">

            <div class="row">
                <div class="col-md-12 col-md-offset">
                    <table id="example_olo" class="table table-bordered" style="width:100%">
                        <thead>
                        
                            <tr>
								<th>feature_type</th>
                                <th>nb_inter...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="nb_intersections_expectation_shuffled"></i></th>
                                <th>nb_inter...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="nb_intersections_variance_shuffled"></i></th>
                                <th>nb_inter...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="nb_intersections_negbinom_fit_quality"></i></th>
                                <th>nb_inter...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="nb_intersections_log2_fold_change"></i></th>
                                <th>nb_inter...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="nb_intersections_true"></i></th>
                                <!--
                                <th>nb_inter...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="nb_intersections_pvalue"></i></th>
                                <th>summed_bp...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="summed_bp_overlaps_expectation_shuffled"></i></th>
                                <th>summed_bp...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="summed_bp_overlaps_variance_shuffled"></i></th>
                                <th>summed_bp...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="summed_bp_overlaps_negbinom_fit_quality"></i></th>
                                <th>summed_bp...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="summed_bp_overlaps_log2_fold_change"></i></th>
                                <th>summed_bp...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="summed_bp_overlaps_true"></i></th>
                                <th>summed_bp...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="summed_bp_overlaps_pvalue"></i></th>
                                <th>combination...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="combination_order"></i></th>
                                <th>nb_inter...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="nb_intersections_empirical_pvalue"></i></th>
                                <th>summed_bp...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="summed_bp_overlaps_empirical_pvalue"></i></th>
                                <th>beta_summed...<i data-toggle="tooltip" data-placement="left" class="glyphicon glyphicon-question-sign" title="" data-original-title="beta_summed_bp_overlaps_pvalue_ad_hoc_for_deep_sampling_only"></i></th>
                                -->
                            </tr>
                         </thead>
                         <tbody>
                         	
                         </tbody>
                      </table>
                    <br/>
					<hr/>
					<embed src="" type="application/pdf" id="pdf" width="100%" height="700px" />
                </div>
            </div>
            
<span></span>
<script>

$('#example_olo').DataTable({
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
    "order": [[ 5, "asc" ]],
	scrollX: true,
	ajax: {
            url:"../analysis/olotable",
            type:"post",
        },
    columns:[
        {"data": function(data){
            return data.str1;
        }},
        {"data": function(data){
            return data.str2;
        }},
        {"data": function(data){
            return data.str3;
        }},
        {"data": function(data){
            return data.str4;
        }},
        {"data": function(data){
            return data.str5;
        }},
        {"data": function(data){
            return data.str6;
        }}
    ],
    "initComplete": function(settings, json) {
	    $("#pdf").attr("src", "http://www.liclab.net:8080/Greap_download/pdf/"+json.pdf);
	 }
    
});


$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

function openWin(url){
	window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=500px,top=200px,width=1000px, height=500px");
}
function openOne(url){
	window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=300px,top=200px,width=1000px, height=500px");
}
function openTwo(url){
	window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=300px,top=200px,width=1000px, height=500px");
}
</script>
</body>
</html>