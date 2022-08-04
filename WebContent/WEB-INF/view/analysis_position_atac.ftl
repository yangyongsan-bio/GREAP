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
            <form action="../analysis/position/ATAC"  method="post" target="histoneiframe">
            <label>Select ATAC class</label>
            <select class="form-control" onchange="form.submit()" name="dataclass" style="width: 30%;margin-bottom: 10px;display: inline;">
				<option value="Adipose" selected>Adipose</option>
				<option value="Artery">Artery</option>
				<option value="Blood">Blood</option>
				<option value="Bone">Bone</option>
				<option value="Bone_marrow">Bone_marrow</option>
				<option value="Brain">Brain</option>
				<option value="Breast">Breast</option>
				<option value="Bronchial">Bronchial</option>
				<option value="Catilage">Catilage</option>
				<option value="Cervix">Cervix</option>
				<option value="Colon">Colon</option>
				<option value="Connective_tissue">Connective_tissue</option>
				<option value="Coronary_artery_smooth_muscle">Coronary_artery_smooth_muscle</option>
				<option value="Cortex">Cortex</option>
				<option value="Cortical_Neurons">Cortical_Neurons</option>
				<option value="Cytokine-secreting_T_helper_cell_counterparts">Cytokine-secreting_T_helper_cell_counterparts</option>
				<option value="Embryo">Embryo</option>
				<option value="Epithelial">Epithelial</option>
				<option value="Fibroblast">Fibroblast</option>
				<option value="Forebrain">Forebrain</option>
				<option value="Foreskin">Foreskin</option>
				<option value="Frontal">Frontal</option>
				<option value="Gastrointestinal_stromal">Gastrointestinal_stromal</option>
				<option value="Glioblastoma">Glioblastoma</option>
				<option value="Hematopoietic">Hematopoietic</option>
				<option value="iPSC">iPSC</option>
				<option value="keratinocyte">keratinocyte</option>
				<option value="Kidney">Kidney</option>
				<option value="Lung">Lung</option>
				<option value="Lymphoblastoid">Lymphoblastoid</option>
				<option value="MA9_leukemia_xenograft">MA9_leukemia_xenograft</option>
				<option value="Mammary_Gland">Mammary_Gland</option>
				<option value="Myeloid">Myeloid</option>
				<option value="Neuron">Neuron</option>
				<option value="Other">Other</option>
				<option value="Ovarian">Ovarian</option>
				<option value="Pancreas">Pancreas</option>
				<option value="Peripheral_blood">Peripheral_blood</option>
				<option value="Prostate">Prostate</option>
				<option value="Sarcomas">Sarcomas</option>
				<option value="Skin">Skin</option>
				<option value="Sperm">Sperm</option>
				<option value="Testicle">Testicle</option>
				<option value="Tonsil">Tonsil</option>
            </select>
            <a href="/Greap/view/help#ATAC" target="_blank"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
            </form>
            
            <iframe src="${base.contextPath}/analysis/position/ATAC?dataclass=Adipose" frameborder="no"  name="histoneiframe" style="width:100%;height:650px;"></iframe>    
                   

</html>