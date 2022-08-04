<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/style.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/datatable.min.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/lnc_detail.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/font-awesome.min.css" />

    <!--datables导出-->
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/jquery.datatables.min.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/buttons.dataTables.min.css" />
    <style>
        .btn-box{
            margin-left: 0;
            display: flex;
            justify-content: left;
            flex-wrap: wrap;
        }
        .btn-box a{
            margin-top: 5px;
        }
        .regulatory_info .col-md-3{
            margin-top: 6px;
        }
        .regulatory_info .col-md-8{
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
        }
        .display_flex_1{
            margin: 2px 0;
            display: flex;
            flex-wrap: wrap;
        }
        .display_flex_1 select{
            width: 150px;
        }
          .Pathway_a{
            text-decoration: none;
            color: #4d8bc0;
        }
        .Pathway_a:hover{
            color: #1B8CFD;
            text-decoration: none;
        }
    </style>
</head>
<body>
<!--model-->
<div id="trlnc_model">
    <!--The regulatory info-->
    <div class="card" style="width: 100%">
        <div class="card-header">
            <div class="display_flex">
                <div><span class="fa fa-list title-img"></span>&nbsp;<span class="title">The regulatory info</span></div>
                <div class="row" style="width: 80%">
                    <div class="col-md-4 display_flex">
                        <span style="margin-top: 3px;white-space: nowrap;">Sample type:&nbsp;</span>
                        <select id="Sample_type" onchange="Tissue();"></select>
                    </div>
                    <div class="col-md-4 display_flex">
                        <span style="margin-top: 3px;white-space: nowrap;">Tissue:&nbsp;</span>
                        <select id="Tissue" onchange="Sample_name()"></select>
                    </div>
                    <div class="col-md-4 display_flex">
                        <span style="margin-top: 3px;white-space: nowrap;">Sample name:&nbsp;</span>
                        <select id="Sample_name" onchange="PromoterRegion('SNP');SE();reset_checked_color();Chromatin();Pathway_associated()"></select>
                    </div>
                </div>
            </div>
        </div>

        <div class="card-body">
            <!--SNP-->
            <div class="card">
                <!--/* ***************************************第一个表**************************************** */-->
                <div class="card-header card-header_color">
                    Promoter region <a onclick="PromoterRegion('SNP')" style="cursor: pointer"><img src="${base.contextPath}/static/img/api/return.png" style="width: 20px;"></a>
                </div>
                <div class="card-body">
                    <div class="row btn-box">
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('SNP')">Common SNP</a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('RiskSNP')">Risk SNP</a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('eqtl')">eQTL</a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('sas')">SAS <img src="${base.contextPath}/static/img/api/1.png" title="Sout Asian Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('eur')">EUR <img src="${base.contextPath}/static/img/api/1.png" title="European Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('eas')">EAS <img src="${base.contextPath}/static/img/api/1.png" title="East Asian Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('amr')">AMR <img src="${base.contextPath}/static/img/api/1.png" title="American Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('afr')">AFR <img src="${base.contextPath}/static/img/api/1.png" title="African Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('Motif')">Motif <img src="${base.contextPath}/static/img/api/1.png" title="TF predicted by motif" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('TF')">TF <img src="${base.contextPath}/static/img/api/1.png" title="TF identified by ChIP-seq" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('Methylation_450k')">450k<img src="${base.contextPath}/static/img/api/1.png" title="methylation sites of 450 karray" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('Methylation_WGBS')">WGBS <img src="${base.contextPath}/static/img/api/1.png" title="methylation sites of whole-genome shotgun bisulfite sequencing" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="PromoterRegion('EpiTensor')">Interaction <img src="${base.contextPath}/static/img/api/1.png" title="3D chromatin interaction" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-blue" onclick="Histone_Select()">Histone <img src="${base.contextPath}/static/img/api/1.png" title="Histone modification" style="width: 12px;"></a>
                    </div>
                    <div style="margin: 10px 0"><span id="information_title"></span></div>
                    <div id="table_load" style="text-align: center"></div>
                    <div id="create_table">

                    </div>
                </div>
            </div>
            <br>
            <!--/* ***************************************第二个表**************************************** */-->
            <div class="card">
                <div class="card-header card-header_color_1">
                    Enhancer region&nbsp;<a onclick="SE()" style="cursor: pointer"><img src="${base.contextPath}/static/img/api/return.png" style="width: 20px;"></a></h3>
                </div>
                <div class="card-body">
                    <div class="row btn-box">
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('SNP')">Common SNP</a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('RiskSNP')">Risk SNP</a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('EqtL')">eQTL</a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('SAS')">SAS <img src="${base.contextPath}/static/img/api/1.png" title="Sout Asian Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('EUR')">EUR <img src="${base.contextPath}/static/img/api/1.png" title="European Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('EAS')">EAS <img src="${base.contextPath}/static/img/api/1.png" title="East Asian Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('AMR')">AMR <img src="${base.contextPath}/static/img/api/1.png" title="American Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('AFR')">AFR <img src="${base.contextPath}/static/img/api/1.png" title="African Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('motif')">Motif <img src="${base.contextPath}/static/img/api/1.png" title="TF predicted by motif" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('TF')">TF <img src="${base.contextPath}/static/img/api/1.png" title="TF identified by ChIP-seq" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('450K')">450k<img src="${base.contextPath}/static/img/api/1.png" title="methylation sites of 450 karray" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('WGBS')">WGBS <img src="${base.contextPath}/static/img/api/1.png" title="methylation sites of whole-genome shotgun bisulfite sequencing" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="EnhancerRegion('Intersection')">Interaction <img src="${base.contextPath}/static/img/api/1.png" title="3D chromatin interaction" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-little_blue" onclick="SE_Histone_Select()">Histone<img src="${base.contextPath}/static/img/api/1.png" title="Histone modification" style="width: 12px;"></a>
                    </div>
                    <div style="margin: 10px 0"><span id="information_title_1"></span></div>
                    <div id="table_load_1" style="text-align: center"></div>
                    <div id="create_table_1">

                    </div>
                </div>
            </div>

            <br>
            <!--/* ***************************************第三个表**************************************** */-->
            <div class="card">
                <div class="card-header card-header_color_2">
                    Chromatin accessibility region&nbsp;<a onclick="Chromatin()" style="cursor: pointer"><img src="${base.contextPath}/static/img/api/return.png" style="width: 20px;"></a></h3>
                </div>
                <div class="card-body">
                    <div class="row btn-box">
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('SNP')">Common SNP</a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('RiskSNP')">Risk SNP</a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('Eqtl')">eQTL</a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('SAS')">SAS <img src="${base.contextPath}/static/img/api/1.png" title="Sout Asian Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('EUR')">EUR <img src="${base.contextPath}/static/img/api/1.png" title="European Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('EAS')">EAS <img src="${base.contextPath}/static/img/api/1.png" title="East Asian Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('AMR')">AMR <img src="${base.contextPath}/static/img/api/1.png" title="American Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('AFR')">AFR <img src="${base.contextPath}/static/img/api/1.png" title="African Population LD" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('motif')">Motif <img src="${base.contextPath}/static/img/api/1.png" title="TF predicted by motif" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('TF')">TF <img src="${base.contextPath}/static/img/api/1.png" title="TF identified by ChIP-seq" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('450K')">450k <img src="${base.contextPath}/static/img/api/1.png" title="methylation sites of 450 karray" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('WGBS')">WGBS <img src="${base.contextPath}/static/img/api/1.png" title="methylation sites of whole-genome shotgun bisulfite sequencing" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="ChromatinRegion('Intersection')">Interaction <img src="${base.contextPath}/static/img/api/1.png" title="3D chromatin interaction" style="width: 12px;"></a>
                        <a type="button" class="btn btn-general btn-less_blue" onclick="Chromatin_Histone_Select()">Histone <img src="${base.contextPath}/static/img/api/1.png" title="Histone modification" style="width: 12px;"></a>
                    </div>
                    <div style="margin: 10px 0"><span id="information_title_2"></span></div>
                    <div id="table_load_2" style="text-align: center"></div>
                    <div id="create_table_2">

                    </div>
                </div>
            </div>
            
            <br>
		    <!--Pathway associated-->
		    <div class="card" style="width: 100%">
		        <div class="card-header">
		            <div class="col-md-12"><span class="title">&nbsp;Pathway associated with ${lncrna_name}</span></div>
		        </div>
		        <div class="card-body">
		            <table class="table table-striped table-bordered" id="Pathway_associated" style="width: 100%"></table>
		        </div>
		    </div>
        </div>
    </div>
    
    
</div>
</body>
<script type="text/javascript">
    var lncrna_name = "${lncrna_name}";
    var regulation = "${regulation}";
</script>
<script src="${base.contextPath}/static/js/api/jquery-3.6.0.min.js" ></script>
<script src="${base.contextPath}/static/js/api/bootstrap.min.js" ></script>
<script src="${base.contextPath}/static/js/api/echarts.min.js" ></script>
<script src="${base.contextPath}/static/js/api/datatable.min.js" ></script>

<!--datables导出-->
<script src="${base.contextPath}/static/js/api/jszip.min.js" ></script>
<script src="${base.contextPath}/static/js/api/vfs_fonts.js" ></script>
<script src="${base.contextPath}/static/js/api/dataTables.buttons.min.js" ></script>
<script src="${base.contextPath}/static/js/api/buttons.html5.min.js" ></script>
<script src="${base.contextPath}/static/js/api/trlnc_api.js"></script>
</html>