window.onload = function () {
    Gene_Detail();
    researchpicBydetail();
    tf_list();
    nodelisttable();
};

function Gene_Detail() {
    let formData = new FormData();
    formData.append("sE_name",seid);
    $.ajax({
        // url:"http://101.34.103.67:8003/analysis/seanalysis/General_Details",
        url:"http://101.34.103.67:8003/analysis/seanalysis/General_Details",
        type:"post",
        async: true,
        data:formData,
        cache:false,
        processData:false,
        contentType:false,
        success:function (result) {
            let General_Details = $("#General_Details");
            General_Details.html("");
            General_Details.append(
                '<tbody>' +
                '<tr>' +
                '<td>'+'Super-enhancer:'+'</td>' +
                '<td>'+result.General_Details[0].sE_name+'</td>' +
                '</tr>' +
                
                '<tr>' +
                '<td>'+'Conservation:'+'</td>' +
                '<td>'+result.General_Details[0].conservation+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'BioSample Type:'+'</td>' +
                '<td>'+result.all_data[0].biosample_type+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'Tissue Type:'+'</td>' +
                '<td>'+result.all_data[0].tissue_type+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'Sample Name:'+'</td>' +
                '<td>'+result.all_data[0].sample_name+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'Source:'+'</td>' +
                '<td>'+result.all_data[0].data_Source+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'Element:'+'</td>' +
                '<td>'+result.all_data_sg.element_num+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'Rank:'+'</td>' +
                '<td>'+result.all_data_sg.rank+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'ChIP Density(Case):'+'</td>' +
                '<td>'+result.all_data_sg.case_peak+'</td>' +
                '</tr>' +
                '<td>'+'ChIP Density(Input):'+'</td>' +
                '<td>'+result.all_data_sg.input_peak+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'ROSE Overlap:'+'</td>' +
                '<td>'+result.all_data_sg.overlap+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'ROSE Proximal:'+'</td>' +
                '<td>'+result.all_data_sg.promix+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'ROSE Closest:'+'</td>' +
                '<td>'+result.all_data_sg.closest+'</td>' +
                '</tr>' +
                '<tr>' +
                '<td>'+'The Closest Active:'+'</td>' +
                '<td>'+result.all_data_sg.closest_active+'</td>' +
                '</tr>' +
                '</tbody>'
            )
        }
    });
}

/*Super-enhancer Associated Network */
function researchpicBydetail() {
    let pvalue = $("#pvalue").val();
    let method = $("#method").val();
    let pathwayvalue = $("#pathwayvalue").val();
    let formData = new FormData();
    formData.append("pvalue",pvalue);
    formData.append("method",method);
    formData.append("pathwayvalue",pathwayvalue);
    formData.append("seid",seid);
    formData.append("toppath",$("#toppathtu").val());
    $.ajax({
        url:"http://101.34.103.67:8003/analysis/seanalysis/researchsedetailpic",
        type:"post",
        async: true,
        data:formData,
        cache:false,
        processData:false,
        contentType:false,
        success:function (res) {
            chartb(res);
        },
        dataType: "json",
        complete:function () {
            $("#load").hide();
        }
    });
}

/*关系图*/
function chartb(nodedata) {
    let chart = echarts.init(document.getElementById('main'));
    chart.hideLoading();
    let categories = [];
    for (var i = 0; i < 4; i++) {
        if (i === 0) {
            categories[i] = {
                name: 'Super-enhancer'
            };
        } else if (i === 1) {
            categories[i] = {
                name: 'TF'
            };
        } else if (i === 2) {
            categories[i] = {
                name: 'Gene'
            };
        } else {
            categories[i] = {
                name: 'Pathway'
            };
        }

    }
    option = {
        tooltip: {},
        legend: [{
            orient: 'vertical',
            left: 'right',
            data: categories.map(function (a) {
                return a.name;
            })
        }],
        animationDuration: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [{
            type: 'graph',
            layout: 'none',
            symbolSize: function (value,params) {
                //根据数据params中的data来判定数据大小
                if (params.data.category === 0) {
                    return 25;
                } else if (params.data.category === 1) {
                    return 15;
                } else if (params.data.category === 2) {
                    return 20;
                } else {
                    return 10;
                }
            },
            data: nodedata.node,
            links: nodedata.linklist,
            categories: categories,
            roam: true,
            focusNodeAdjacency: true,
            itemStyle: {
                normal: {
                    borderColor: '#fff',
                    borderWidth: 1,
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.3)'
                }
            },
            label: {
                position: 'right',
                formatter: '{b}'
            },

            emphasis: {
                lineStyle: {
                    width: 10
                }
            },
        }]
    };
    chart.setOption(option);
    /* pic-end */
}

/*关系图详情*/
/*打开模态框*/
$("#myModalshowpicbtn").click(function () {
    $("#network_detail").modal('show');
    network_detail_refresh();
});

/*关闭模态框*/
$("#network_detail_close").click(function () {
    $("#network_detail").modal('hide');
});

function network_detail_refresh(){
    let pvalue = $("#pvalue1").val();
    let method = $("#method1").val();
    let pathwayvalue = $("#pathwayvalue1").val();
    let formData = new FormData();
    formData.append("pvalue",pvalue);
    formData.append("method",method);
    formData.append("pathwayvalue",pathwayvalue);
    formData.append("seid",seid);
    formData.append("toppath",$("#toppathtu1").val());
    $.ajax({
        url:"http://101.34.103.67:8003/analysis/seanalysis/researchsedetailpic",
        // url:"http://101.34.103.67:8003/analysis/seanalysis/researchsedetailpic",
        type:"post",
        async: true,
        data:formData,
        cache:false,
        processData:false,
        contentType:false,
        success:function (res) {
            node_data = res;
            chartc(res);
        },
        dataType: "json"
    });
}

function chartc(nodedata) {
    let chart = echarts.init(document.getElementById('main1'));
    chart.hideLoading();
    let categories = [];
    for (var i = 0; i < 4; i++) {
        if (i === 0) {
            categories[i] = {
                name: 'Super-enhancer'
            };
        } else if (i === 1) {
            categories[i] = {
                name: 'TF'
            };
        } else if (i === 2) {
            categories[i] = {
                name: 'Gene'
            };
        } else {
            categories[i] = {
                name: 'Pathway'
            };
        }

    }
    option = {
        tooltip: {},
        legend: [{
            orient: 'vertical',
            left: 'right',
            data: categories.map(function (a) {
                return a.name;
            })
        }],
        animationDuration: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [{
            type: 'graph',
            layout: 'none',
            symbolSize: function (value,params) {
                //根据数据params中的data来判定数据大小
                if (params.data.category === 0) {
                    return 25;
                } else if (params.data.category === 1) {
                    return 15;
                } else if (params.data.category === 2) {
                    return 20;
                } else {
                    return 10;
                }
            },
            data: nodedata.node,
            links: nodedata.linklist,
            categories: categories,
            roam: true,
            focusNodeAdjacency: true,
            itemStyle: {
                normal: {
                    borderColor: '#fff',
                    borderWidth: 1,
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.3)'
                }
            },
            label: {
                position: 'right',
                formatter: '{b}'
            },

            emphasis: {
                lineStyle: {
                    width: 10
                }
            },
        }]
    };
    chart.setOption(option);
    /* pic-end */
}

$("#myTab .nav-item .nav-link").click(function () {
    $("#myTab .nav-item .nav-link").removeClass("active");
    $(this).addClass("active");
    let get_id = $(this).attr("text");
    $(".tf_motif_table").css("display","none");
    $("#"+get_id).css("display","block");
    if (get_id==="tf_list"){
        tf_list();
    }else {
        se_motifList();
    }
});

/*tf_list*/
function tf_list() {
    $('#tf_list').DataTable({
        bLengthChange:true,
        aLengthMenu:[5],
        iDisplayLength:5,
        scrollX:true,
        destroy:true,
        dom:"Bfrtip",
        buttons:[
            {
                extend:'csv',
                text:'csv',
                exportOptions:{
                    "columns":':visible',
                }
            },
        ],
        ajax:{
            url:"http://101.34.103.67:8003/analysis/seanalysis/redetail/se_tfList",
            type:"post",
            data:function (data) {
                data.sE_name=seid;
            },
        },
        columns:[
            {title:"Chrome",data:"element_chr"},
            {title:"Element_Start",data:"star"},
            {title:"Element_End",data:"end"},
            {title:"TF",data:"tF"},
            {title:"TF Source",data:"antibody"},
            {title:"TF_Start",data:"star_1"},
            {title:"TF_End",data:"end_1"},
            {title:"TF Class",data:"tF_class"},
            {title:"Master TF<span class='fa fa-question-circle' title='Whether it is Master TFs predicted by CRC mapper algorithm. Y: Yes, N: NO.'></span>",data:"ismaster"},
        ],
    });
}

/*se_motifList*/
function se_motifList() {
    $('#se_motifList').DataTable({
        bLengthChange:true,
        aLengthMenu:[5],
        iDisplayLength:5,
        scrollX:true,
        dom:"Bfrtip",
        destroy:true,
        buttons:[
            {
                extend:'csv',
                text:'csv',
                exportOptions:{
                    "columns":':visible',
                }
            },
        ],
        ajax:{
            url:"http://101.34.103.67:8003/analysis/seanalysis/redetail/se_motifList",
            type:"post",
            data:function (data) {
                data.sE_name=seid;
            },
        },
        columns:[
            {title:"Chrome",data:"ele_chrome"},
            {title:"Element_Start",data:"start"},
            {title:"Element_End",data:"stop"},
            {title:"TF",data:"tF_offi"},
            {title:"Database",data:"database"},
            {title:"TF_Start",data:"start_1"},
            {title:"TF_End",data:"stop_1"},
            {title:"Score<span class='fa fa-question-circle' title=\"The “score” measures the sequence matching degree of a genomic region with a TF motif.\"></span>",data:"score"},
            {title:"P_Value",data:"p_value"},
            {title:"TF Class",data:"tF_class"},
            {title:"Master TF<span class='fa fa-question-circle' title='Whether it is Master TFs predicted by CRC mapper algorithm. Y: Yes, N: NO.'></span>",data:"ismaster"},
        ],
    });
}

/*nodelisttable*/
function nodelisttable() {
    $('#nodelisttable').DataTable({
        bLengthChange:true,
        aLengthMenu:[5],
        iDisplayLength:5,
        scrollX:true,
        dom:"Bfrtip",
        destroy:true,
        buttons:[
            {
                extend:'csv',
                text:'csv',
                exportOptions:{
                    "columns":':visible',
                }
            },
        ],
        "order": [[ 7, "asc" ]],
        ajax:{
            url:"http://101.34.103.67:8003/analysis/seanalysis/redetail/nodelisttable",
            type:"post",
            data:function (data) {
                data.sE_name=seid;
            },
        },
        columns:[
            {title:"Pathway ID<span class='fa fa-question-circle' title=\"Click to view Pathways network on the ComPAT web-server.\"></span>",data:function (data) {
                    return '<a href=http://licpathway.net/msg/ComPAT/node.do?id='+data.pathwayID+' target="_blank">'+data.pathwayID+'</a>'
                }},
            {title:"Pathway Name",data:"pathwayName"},
            {title:"Source",data:"source"},
            {title:"Total Gene Number<span class='fa fa-question-circle' title=\"TFs binding to SE identified by ChIP-seq and predicted by Motif.\"></span>",data:"species"},
            {title:"Annotated TF",data:"geneID_Type"},
            {title:"Annotated TF Number",data:"annGene"},
            {title:"P_Value",data:"geneNumber"},
            {title:"FDR<span class='fa fa-question-circle' title=\"False discovery rate (FDR) : the corrected p-value.\"></span>",data:"fDR"},
        ],
    });
}