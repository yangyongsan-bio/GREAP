<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>seanalysis</title>
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/style.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/datatable.min.css" />

    <!--datables导出-->
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/jquery.datatables.min.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/buttons.dataTables.min.css" />
    <link rel="stylesheet" type="text/css" href="${base.contextPath}/static/css/api/seanalysis_api.css" />
    <style>
        table th{
            white-space: nowrap;
        }
        #reset_select{
            display: flex;
            justify-content: flex-start;
            flex-wrap: wrap;
        }
        #reset_select div{
            display: flex;
            padding-right: 15px;
            padding-bottom: 10px;
        }
        #reset_select div span{
            font-size: 13px;
            white-space: nowrap;
            padding-right: 8px;
            line-height: 28px;
        }
        #reset_select1{
            display: flex;
            justify-content: flex-start;
            flex-wrap: wrap;
        }
        #reset_select1 div{
            display: flex;
            padding-right: 15px;
            padding-bottom: 10px;
        }
        #reset_select1 div span{
            font-size: 13px;
            white-space: nowrap;
            padding-right: 8px;
            line-height: 28px;
        }
        table.dataTable thead .sorting{background:url('${base.contextPath}/static/img/api/sort_both.png') no-repeat center right!important}
        table.dataTable thead .sorting_asc{background:url('${base.contextPath}/static/img/api/sort_asc.png') no-repeat center right!important}
        table.dataTable thead .sorting_desc{background:url('${base.contextPath}/static/img/api/sort_desc.png') no-repeat center right!important}
        table.dataTable thead .sorting_asc_disabled{background:url('${base.contextPath}/static/img/api/sort_asc_disabled.png') no-repeat center right!important}
        table.dataTable thead .sorting_desc_disabled{background:url('${base.contextPath}/static/img/api/sort_desc_disabled.png') no-repeat center right!important}
    </style>
</head>
<body>
<!-- Modal -->
<div id="seanalysis">
    <br>
    <!--1-->
    <div class="row">
        <div class="col-md-6">
            <div class="borderTop">
                <span style="font-weight: bold;float: left;color: black;font-size: 24px"><span class="fa fa-info-circle" style="font-size: 28px"></span>&nbsp;General Details</span>
                <table id="General_Details" class="table table-striped"></table>
            </div>
        </div>
        <div class="col-md-6">
            <div class="borderTop">
                <span style="font-weight: bold;float: left;color: black;font-size: 24px"><span class="fa fa-info-circle" style="font-size: 28px"></span>&nbsp;Super-enhancer Associated Network</span>
                <a>
                    <img id="myModalshowpicbtn" src="${base.contextPath}/static/img/api/network2.png" style="width: 30px;">
                </a>
                <br>
                <div style="height: 468px; text-align: center;" id="main"></div>
                <p>* The complex regulatory networks formed by this SE <img class="networkwidth" src="${base.contextPath}/static/img/api/networkse.png">,
                    SE associated genes <img class="networkwidth" src="${base.contextPath}/static/img/api/networkgene.png">, TFs <img class="networkwidth"
                                                                                                                  src="${base.contextPath}/static//img/api/networkpathway.png">
                    binding to the SE and their upstream pathways <img class="networkwidth" src="${base.contextPath}/static/img/api/networkpathway.png">
                    can be interactively visualized.</p>
                <div id="reset_select">
                    <div><span>Fimo:</span>
                        <select class="form-control input-sm" id="pvalue">
                            <option value="null">none</option>
                            <option value="1.0E-9" selected>1.0E-9</option>
                            <option value="1.0E-8">1.0E-8</option>
                            <option value="1.0E-7">1.0E-7</option>
                            <option value="1.0E-6">1.0E-6</option>
                        </select>
                    </div>
                    <div><span>SE-Gene Linking Strategies:</span>
                        <select class="form-control input-sm" id="method">
                            <option value="ALL">ALL</option>
                            <option value="Closest active">Closest active</option>
                            <option value="Closest">Closest</option>
                            <option value="Proximal">Proximal</option>
                            <option value="Overlap">Overlap</option>
                        </select>
                    </div>
                    <div><span>Pathway Enriciment Threshold<span
                            class="glyphicon glyphicon-question-sign"
                            title="P-Value is calculated using hypergeometric test."></span>:</span>
                        <input type="text" class="form-control input-sm" id="pathwayvalue" placeholder="1" value="1">
                    </div>
                    <div><span>Top Pathway<span
                            class="glyphicon glyphicon-question-sign"
                            title="Top pathways sorted by the statistical significance of pathway enrichment."></span>:</span>
                        <select class="form-control input-sm" id="toppathtu">
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                            <option value="ALL">ALL</option>
                        </select>
                    </div>
                    <div><span>&nbsp;</span>
                        <button type="button" class="btn btn-primary btn-sm" onclick="researchpicBydetail()">Refresh</button>
                    </div>
                    <br>
                    <br><br>
                </div>
            </div>
        </div>
    </div>
    <!--2-->
    
    <!--3-->
    <div class="row">
        <div class="col-md-12">
            <div class="borderTop">
                <span style="font-weight: bold;float: left;font-size: 24px"><span class="fa fa-info-circle" style="font-size: 28px"></span>&nbsp;Factors
                    Binding to Super-enhancer</span>
                <br><br>
                <ul class="nav nav-tabs" id="myTab">
                    <li class="nav-item">
                        <a class="nav-link active" text="tf_list_box">SE_TF</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" text="se_motifList_box">SE_motif</a>
                    </li>
                </ul>
                <br>
                <div id="tf_list_box" class="tf_motif_table">
                    <p style="margin-bottom: 10px;">
                        *The TFs binding to this SE identified by ChIP-seq.
                    </p>
                    <table id="tf_list" class="table table-striped table-bordered" width="100%"></table>
                </div>
                <div id="se_motifList_box" class="tf_motif_table" style="display: none">
                    <p style="margin-bottom: 10px;">
                        *The TFs binding to this SE predicted by Motif.
                    </p>
                    <table id="se_motifList" class="table table-striped table-bordered" width="100%"></table>
                </div>
            </div>
        </div>
    </div>
    <!--4-->
    <div class="row">
        <div class="col-md-12">
            <div class="borderTop">
                <span style="font-weight: bold;float: left;font-size: 24px"><span class="fa fa-list" style="font-size: 28px"></span>&nbsp;The
                    Upstream Pathways of TFs Binding to SE</span>
                <br><br>
                <table id="nodelisttable" class="table table-striped table-bordered" width="100%"></table>
            </div>
        </div>
    </div>
</div>
<!--network-->
<div id="network_detail" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" id="network_detail_close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="borderTop">
                    <br>
                    <div style="height: 568px; text-align: center;" id="main1"></div>
                    <div id="reset_select1">
                        <div><span>Fimo:</span>
                            <select class="form-control input-sm" id="pvalue1">
                                <option value="null">none</option>
                                <option value="1.0E-9" selected>1.0E-9</option>
                                <option value="1.0E-8">1.0E-8</option>
                                <option value="1.0E-7">1.0E-7</option>
                                <option value="1.0E-6">1.0E-6</option>
                            </select>
                        </div>
                        <div><span>SE-Gene Linking Strategies:</span>
                            <select class="form-control input-sm" id="method1">
                                <option value="ALL">ALL</option>
                                <option value="Closest active">Closest active</option>
                                <option value="Closest">Closest</option>
                                <option value="Proximal">Proximal</option>
                                <option value="Overlap">Overlap</option>
                            </select>
                        </div>
                        <div><span>Pathway Enriciment Threshold<span
                                class="glyphicon glyphicon-question-sign"
                                title="P-Value is calculated using hypergeometric test."></span>:</span>
                            <input type="text" class="form-control input-sm" id="pathwayvalue1" placeholder="1" value="1">
                        </div>
                        <div><span>Top Pathway<span
                                class="glyphicon glyphicon-question-sign"
                                title="Top pathways sorted by the statistical significance of pathway enrichment."></span>:</span>
                            <select class="form-control input-sm" id="toppathtu1">
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                                <option value="ALL">ALL</option>
                            </select>
                        </div>
                        <div><span>&nbsp;</span>
                            <button type="button" class="btn btn-primary btn-sm" onclick="network_detail_refresh()">Refresh</button>
                        </div>
                        <br>
                        <br><br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var ctx = "\/";
    var seid = "${sE_name}";
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
<script src="${base.contextPath}/static/js/api/seanalysis_api.js"></script>
</html>