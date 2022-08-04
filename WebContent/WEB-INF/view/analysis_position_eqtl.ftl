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
            <form action="../analysis/position/eQTL"  method="post" target="histoneiframe">
            <label>Select SNP class</label>
            <select class="form-control" onchange="form.submit()" name="dataclass" style="width: 30%;margin-bottom: 10px;display: inline;">
				<option value="Adipose_Subcutaneous" selected>Adipose_Subcutaneous</option>
				<option value="Adipose_Visceral_Omentum">Adipose_Visceral_Omentum</option>
				<option value="Adrenal_Gland">Adrenal_Gland</option>
				<option value="Artery_Aorta">Artery_Aorta</option>
				<option value="Artery_Coronary">Artery_Coronary</option>
				<option value="Artery_Tibial">Artery_Tibial</option>
				<option value="Brain_Amygdala">Brain_Amygdala</option>
				<option value="Brain_Anterior_cingulate_cortex_BA24">Brain_Anterior_cingulate_cortex_BA24</option>
				<option value="Brain_Caudate_basal_ganglia">Brain_Caudate_basal_ganglia</option>
				<option value="Brain_Cerebellar_Hemisphere">Brain_Cerebellar_Hemisphere</option>
				<option value="Brain_Cerebellum">Brain_Cerebellum</option>
				<option value="Brain_Cortex">Brain_Cortex</option>
				<option value="Brain_Frontal_Cortex_BA9">Brain_Frontal_Cortex_BA9</option>
				<option value="Brain_Hippocampus">Brain_Hippocampus</option>
				<option value="Brain_Hypothalamus">Brain_Hypothalamus</option>
				<option value="Brain_Nucleus_accumbens_basal_ganglia">Brain_Nucleus_accumbens_basal_ganglia</option>
				<option value="Brain_Putamen_basal_ganglia">Brain_Putamen_basal_ganglia</option>
				<option value="Brain_Spinal_cord_cervical_c-1">Brain_Spinal_cord_cervical_c-1</option>
				<option value="Brain_Substantia_nigra">Brain_Substantia_nigra</option>
				<option value="Breast_Mammary_Tissue">Breast_Mammary_Tissue</option>
				<option value="Cells_EBV-transformed_lymphocytes">Cells_EBV-transformed_lymphocytes</option>
				<option value="Cells_Transformed_fibroblasts">Cells_Transformed_fibroblasts</option>
				<option value="Colon_Sigmoid">Colon_Sigmoid</option>
				<option value="Colon_Transverse">Colon_Transverse</option>
				<option value="Esophagus_Gastroesophageal_Junction">Esophagus_Gastroesophageal_Junction</option>
				<option value="Esophagus_Mucosa">Esophagus_Mucosa</option>
				<option value="Esophagus_Muscularis">Esophagus_Muscularis</option>
				<option value="Heart_Atrial_Appendage">Heart_Atrial_Appendage</option>
				<option value="Heart_Left_Ventricle">Heart_Left_Ventricle</option>
				<option value="Liver">Liver</option>
				<option value="Lung">Lung</option>
				<option value="Minor_Salivary_Gland">Minor_Salivary_Gland</option>
				<option value="Muscle_Skeletal">Muscle_Skeletal</option>
				<option value="Nerve_Tibial">Nerve_Tibial</option>
				<option value="Ovary">Ovary</option>
				<option value="Pancreas">Pancreas</option>
				<option value="Pituitary">Pituitary</option>
				<option value="Prostate">Prostate</option>
				<option value="Skin_Not_Sun_Exposed_Suprapubic">Skin_Not_Sun_Exposed_Suprapubic</option>
				<option value="Skin_Sun_Exposed_Lower_leg">Skin_Sun_Exposed_Lower_leg</option>
				<option value="Small_Intestine_Terminal_Ileum">Small_Intestine_Terminal_Ileum</option>
				<option value="Spleen">Spleen</option>
				<option value="Stomach">Stomach</option>
				<option value="Testis">Testis</option>
				<option value="Thyroid">Thyroid</option>
				<option value="Uterus">Uterus</option>
				<option value="Vagina">Vagina</option>
				<option value="Whole_Blood">Whole_Blood</option>
				<option value="ACC_tumor">ACC_tumor</option>
				<option value="BLCA_tumor">BLCA_tumor</option>
				<option value="BRCA_tumor">BRCA_tumor</option>
				<option value="CESC_tumor">CESC_tumor</option>
				<option value="CHOL_tumor">CHOL_tumor</option>
				<option value="COAD_tumor">COAD_tumor</option>
				<option value="DLBC_tumor">DLBC_tumor</option>
				<option value="ESCA_tumor">ESCA_tumor</option>
				<option value="GBM_tumor">GBM_tumor</option>
				<option value="HNSC_tumor">HNSC_tumor</option>
				<option value="KICH_tumor">KICH_tumor</option>
				<option value="KIRC_tumor">KIRC_tumor</option>
				<option value="KIRP_tumor">KIRP_tumor</option>
				<option value="LAML_tumor">LAML_tumor</option>
				<option value="LGG_tumor">LGG_tumor</option>
				<option value="LIHC_tumor">LIHC_tumor</option>
				<option value="LUAD_tumor">LUAD_tumor</option>
				<option value="LUSC_tumor">LUSC_tumor</option>
				<option value="MESO_tumor">MESO_tumor</option>
				<option value="OV_tumor">OV_tumor</option>
				<option value="PAAD_tumor">PAAD_tumor</option>
				<option value="PCPG_tumor">PCPG_tumor</option>
				<option value="PRAD_tumor">PRAD_tumor</option>
				<option value="READ_tumor">READ_tumor</option>
				<option value="SARC_tumor">SARC_tumor</option>
				<option value="SKCM_tumor">SKCM_tumor</option>
				<option value="STAD_tumor">STAD_tumor</option>
				<option value="TGCT_tumor">TGCT_tumor</option>
				<option value="THCA_tumor">THCA_tumor</option>
				<option value="THYM_tumor">THYM_tumor</option>
				<option value="UCEC_tumor">UCEC_tumor</option>
				<option value="UCS_tumor">UCS_tumor</option>
				<option value="UVM_tumor">UVM_tumor</option>
            </select>
            <a href="/Greap/view/help#SNP" target="_blank"><img src="/Greap/static/img/question.png" style="width: 12px;margin-left: 2px;margin-top: -3px;"></a>
            </form>
            
            <iframe src="${base.contextPath}/analysis/position/eQTL?dataclass=Adipose_Subcutaneous" frameborder="no"  name="histoneiframe" style="width:100%;height:650px;"></iframe>    
                   

</html>