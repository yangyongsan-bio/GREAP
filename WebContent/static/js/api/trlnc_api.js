var ctx = "\/";
var checked_num=0;
var check_num_Chromatin=0;
function openWinthree(url){
    window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, left=300px,top=350px,width=700px, height=150px");
}
function completed(){
    $('#loading_background').hide();
    $('#loading_manage').hide();
    $('#progress_box').hide();
}

$.ajax({
    url:"http://101.34.103.67:8003/analysis/trlnc/search/Biosample_Type",
    type:"post",
    success:function(result){
        let Sample_type=document.getElementById("Sample_type");
        Sample_type.innerHTML="";
        for(var i=0;i<result.Biosample_Type.length;i++){
            if(result.Biosample_Type[i]==="Cell Line"){
                Sample_type.innerHTML=Sample_type.innerHTML+
                    '<option selected>'+result.Biosample_Type[i]+'</option>'
            }else{
                Sample_type.innerHTML=Sample_type.innerHTML+
                    '<option>'+result.Biosample_Type[i]+'</option>'
            }
        }
        Tissue();
    }
});

function Tissue(){
    let Sample_type=$('#Sample_type option:selected').val();
    $.ajax({
        url:"http://101.34.103.67:8003/analysis/trlnc/search/tiss?Biosample_Type="+Sample_type,
        type:"post",
        success:function(result){
            let Tissue=document.getElementById("Tissue");
            Tissue.innerHTML="";
            for(var i=0;i<result.tiss.length;i++){
                if(result.tiss[i]==="Colon"){
                    Tissue.innerHTML=Tissue.innerHTML+
                        '<option selected>'+result.tiss[i]+'</option>';
                }else{
                    Tissue.innerHTML=Tissue.innerHTML+
                        '<option>'+result.tiss[i]+'</option>';
                }
            }
            Sample_name();
        }
    });
}
function Sample_name(){
    let Sample_type=$('#Sample_type option:selected').val();
    let Tissue=$('#Tissue option:selected').val();
    $.ajax({
        url:"http://101.34.103.67:8003/analysis/trlnc/search/Biosample_name?Biosample_Type="+Sample_type+"&tiss="+Tissue,
        type:"post",
        cache:false,
        processData:false,
        contentType:false,
        success:function(result){
            let Sample_name=document.getElementById("Sample_name");
            Sample_name.innerHTML="";
            for(var i=0;i<result.Biosample_name.length;i++){
                if(result.Biosample_name[i]==="HCT116"){
                    Sample_name.innerHTML=Sample_name.innerHTML+
                        '<option selected>'+result.Biosample_name[i]+'</option>';
                }else{
                    Sample_name.innerHTML=Sample_name.innerHTML+
                        '<option>'+result.Biosample_name[i]+'</option>';
                }
            }
            PromoterRegion('SNP');
            SE();
            Chromatin();
            Pathway_associated();
        }
    });
}
function PromoterRegion(Url){
    let table=document.getElementById("create_table");
    table.innerHTML="";
    let table_columns;
    let common_columns=[
        {title:"CHR_A",data:"CHR_A"},
        {title:"Position_A",data:"BP_A"},
        {title:"SNP_A",data:"SNP_A"},
        {title:"CHR_B",data:"CHR_B"},
        {title:"Position_B",data:"BP_B"},
        {title:"SNP_B",data:"SNP_B"},
        {title:"R2",data:"R2"},
        {title:"DP",data:"DP"},
    ];
    let table_load=document.getElementById("table_load");
    table_load.innerHTML="";
    table_load.innerHTML=table_load.innerHTML+
        '<img src='+ctx+"Greap/static/img/loading.gif"+'>';
    let information_title=document.getElementById("information_title");
    let sample_name=$('#Sample_name option:selected').val();
    let sample_type=$('#Sample_type option:selected').val();
    let Tissue=$('#Tissue option:selected').val();
    table.innerHTML=table.innerText+
        '<table id="mytable" class="table_style table table-bordered table-striped table-hover" width="100%">'+'</table>';
    let formData=new FormData();
    formData.append("GeneName",lncrna_name);
    formData.append("regulation",regulation);
    formData.append("Biosample_Type",sample_type);
    formData.append("Biosample_Name",sample_name);
    formData.append("tiss",Tissue);
    $.ajax({
        url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/"+Url,
        type:'post',
        data:formData,
        cache:false,
        processData:false,
        contentType:false,
        success:function(result){
            if(Url==="SNP"){
                information_title.innerText="* Common SNPs which appear in promoter region";
                table_columns=[
                    {title:"CHR",data:"CHR"},
                    {title:"Position",data:"STAR"},
                    {title:"SNP ID",data:"ID"},
                    {title:"ref",data:"ref"},
                    {title:"alt",data:"alt"},
                    {title:"AFR",data:function(data){
                            return'<a onclick="SNP_Detail('+"&quot;"+"AFR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.AFR+'</a>'
                        }},
                    {title:"AMR",data:function(data){
                            return'<a onclick="SNP_Detail('+"&quot;"+"AMR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.AMR+'</a>'
                        }},
                    {title:"EAS",data:function(data){
                            return'<a onclick="SNP_Detail('+"&quot;"+"EAS"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.EAS+'</a>'
                        }},
                    {title:"EUR",data:function(data){
                            return'<a onclick="SNP_Detail('+"&quot;"+"EUR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.EUR+'</a>'
                        }},
                    {title:"SAS",data:function(data){
                            return'<a onclick="SNP_Detail('+"&quot;"+"SAS"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.SAS+'</a>'
                        }},
                    {title:"eQTL",data:function(data){
                            return'<a onclick="SNP_Detail('+"&quot;"+"EQTL"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.Eqtl+'</a>'
                        }},
                    {title:"Risk SNP",data:function(data){
                            return'<a onclick="SNP_Detail('+"&quot;"+"RISKSNP"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.Risk_SNP+'</a>'
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Url==="eqtl"){
                information_title.innerText="* eQTLs which appear in promoter region";
                table_columns=[
                    {title:"SNP ID",data:"SNP_ID"},
                    {title:"CHR",data:"CHR"},
                    {title:"Position",data:"STAR"},
                    {title:"eQTL Gene",data:"eqtl_gene"},
                    {title:"eQTL Cell Type",data:"eqtl_cell_type"},
                    {title:"eQTL Source",data:"eqtl_source"},
//                    {title:"Visualization",data:function(data){
//                            return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Url==="RiskSNP"){
                information_title.innerText="* Risk SNPs which appear in promoter region";
                table_columns=[
                    {title:"SNP ID",data:"SNP_ID"},
                    {title:"CHR",data:"CHR"},
                    {title:"Position",data:"STAR"},
                    {title:"Gene",data:"ref"},
                    {title:"Disease",data:"alt"},
                    {title:"Type",data:"Gene"},
                    {title:"P value",data:"disease"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Url==="sas"){
                information_title.innerText="* Sout Asian Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(Url==="eur"){
                information_title.innerText="* European Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(Url==="eas"){
                information_title.innerText="* East Asian Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(Url==="amr"){
                information_title.innerText="* American Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(Url==="afr"){
                information_title.innerText="* African Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(Url==="Motif"){
                information_title.innerText="* TFs predicted by motif which appear in promoter region";
                table_columns=[
                    {title:"Database",data:"Database"},
                    {title:"TF",data:"TF"},
                    {title:"Region",data:function(data){
                            return data.CHR+":"+data.Star+"-"+data.End+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.Star+"&End="+data.End+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Strand",data:"strand"},
                    {title:"Scores",data:"Scores"},
                    {title:"Pvalue",data:"Pvalue"},
                    {title:"Motif sequence",data:"Motif_sequence"},
//                    {title:"Visualization",data:function(data){
//                            return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.Star+".."+data.End+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.Star+"-"+data.End+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Url==="TF"){
                information_title.innerText="* TFs identified by chip-seq which appear in promoter region";
                table_columns=[
                    {title:"Sample name",data:function(){
                            return sample_name;
                        }},
                    {title:"TF",data:"TF"},
                    {title:"Region",data:function(data){
                            return data.CHR+":"+data.STAR+"-"+data.END+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.STAR+"&End="+data.END+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Class",data:"t_class"},
                    {title:"Source",data:"ID"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.END+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+"-"+data.END+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Url==="Methylation_450k"){
                information_title.innerText="* Methylation sites of 450 k array which appear in promoter region";
                table_columns=[
                    {title:"Sample name",data:function(data){
                            return sample_name;
                        }},
                    {title:"Probe ID",data:"CG"},
                    {title:"CHR",data:"CHR"},
                    {title:"Position",data:"STAR"},
                    {title:"Bate",data:"bate"},
                    {title:"Source",data:function(data){
                            return result.result;
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Url==="Methylation_WGBS"){
                information_title.innerText="* Methylation sites of whole-genome shotgun bisulfite sequencing which appear in promoter region";
                table_columns=[
                    {title:"Sample name",data:function(data){
                            return sample_name;
                        }},
                    {title:"CHR",data:"CHR"},
                    {title:"Position",data:"STAR"},
                    {title:"Reads",data:"read"},
                    {title:"Source",data:function(data){
                            return result.result;
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Url==="EpiTensor"){
                information_title.innerText="* Chromatin interactions which appear in promoter region";
                table_columns=[
                    {title:"Tissue",data:"Tissue"},
                    {title:"Region_A",data:function(data){
                            return data.InteractorAChr+":"+data.InteractorAStart+"-"+data.InteractorAEnd+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.InteractorAChr+"&Start="+data.InteractorAStart+"&End="+data.InteractorAEnd+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Region_B",data:function(data){
                            return data.InteractorBChr+":"+data.InteractorBStart+"-"+data.InteractorBEnd+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.InteractorBChr+"&Start="+data.InteractorBStart+"&End="+data.InteractorBEnd+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Method",data:"ID"},
                    {title:"Source",data:"Class"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://bio.licpathway.net/TRlnc_give/data?interaction='+data.ID+"&coordinateCarOne="+data.InteractorAChr+"&coordinateStartOne="+data.InteractorAStart+"&coordinateEndOne="+data.InteractorAEnd+"&coordinateCarTwo="+data.InteractorBChr+"&coordinateStartTwo="+data.InteractorBStart+"&coordinateEndTwo="+data.InteractorBEnd+'" target="_blank">'+
//                                '<img src="'+ctx+'img/GIVE.jpg" width="50px">'+
//                                '<img src="'+ctx+'img/GIVE.jpg" width="50px">'+
//                                '</a>'
//                        }},
                    {title:"Sample name",data:function(data){
                            return sample_name;
                        }},
                ]
            }
            table_load.innerHTML="";
            $('#mytable').DataTable({
                scrollX:true,
                aLengthMenu:[5,10,15],
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
                    url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/"+Url,
                    type:'post',
                    data:function(data){
                        data.GeneName=lncrna_name;
                        data.regulation = regulation;
                        data.Biosample_Type=sample_type;
                        data.Biosample_Name=sample_name;
                        data.tiss=Tissue
                    }
                },
                columns:table_columns
            });
        }
    });
}
function Histone_Select(){
    let table=document.getElementById("create_table");
    let information_title=document.getElementById("information_title");
    information_title.innerText="* Histone modifications which appear in promoter region";
    table.innerHTML="";
    table.innerHTML=table.innerHTML+
        "Histone modification:&nbsp;"+
        '<select id="Histone" onchange="Histone()" style="width:150px">'+
        '<option value="H3K36me3">H3K36me3</option>'+
        '<option value="H3K4me3" selected>H3K4me3</option>'+
        '<option value="H3K4me1">H3K4me1</option>'+
        '<option value="H3K79me2">H3K79me2</option>'+
        '<option value="H3K9ac">H3K9ac</option>'+
        '<option value="H4K20me1">H4K20me1</option>'+
        '</select>'+
        '<table id="mytable" class="table_style table-striped table-bordered table-hover" width="100%">'+'</table>';
    Histone();
}
function Histone(){
    let sample_name=$('#Sample_name option:selected').val();
    let sample_type=$('#Sample_type option:selected').val();
    let Tissue=$('#Tissue option:selected').val();
    let histone=$('#Histone option:selected').val();
    $('#mytable').DataTable({
        scrollX:true,
        aLengthMenu:[5,10,15],
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
            url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/Histone",
            type:'post',
            data:function(data){
                data.GeneName=lncrna_name;
                data.regulation = regulation;
                data.Biosample_Type=sample_type;
                data.Biosample_Name=sample_name;
                data.tiss=Tissue;
                data.histone=histone
            }
        },
        columns:[
            {title:"Region",data:function(data){
                    return data.CHR+":"+data.Start+"-"+data.End+
                        '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.Start+"&End="+data.End+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                }},
//            {title:"Visualization",data:function(data){
//                    return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.Start+".."+data.End+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                        '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                        '</a>'+
//                        '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.Start+"-"+data.End+'">'+
//                        '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                        '</a>'
//                }},
        ]
    });
}
var param_se_id;
var param_chr;
var param_start;
var param_end;
function SE(){
    let table=document.getElementById("create_table_1");
    table.innerHTML="";
    let information_title_1=document.getElementById("information_title_1");
    let sample_name=$('#Sample_name option:selected').val();
    let sample_type=$('#Sample_type option:selected').val();
    let Tissue=$('#Tissue option:selected').val();
    information_title_1.innerText="* Enhancer regions associated with lncRNA";
    table.innerHTML=table.innerHTML+
        '<table id="mytable_1" class="table_style table-striped table-bordered table-hover" width="100%">'+'</table>';
    $('#mytable_1').DataTable({
        scrollX:true,
        aLengthMenu:[5,10,15],
        dom:"Bfrtip",
        fnInitComplete:function(obj){
            $("input[name='position']").eq(checked_num).attr("checked","checked");
            if(obj.aoData[0]===undefined){
                param_se_id="";param_chr="";param_start="";param_end="";
            }else{
                param_se_id=obj.aoData[0]._aData.SE_id;
                param_chr=obj.aoData[0]._aData.chr;
                param_start=obj.aoData[0]._aData.start;
                param_end=obj.aoData[0]._aData.end;
            }
        },
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
            url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/SE",
            type:'post',
            data:function(data){
                data.GeneName=lncrna_name;
                data.regulation = regulation;
                data.Biosample_Type=sample_type;
                data.Biosample_Name=sample_name;
                data.tiss=Tissue;
            }
        },
        columns:[
            {title:"",data:function(data){
                    return'<input name="position" type="radio" onclick="position('+"&quot;"+data.SE_id+"&quot;"+","+"&quot;"+data.chr+"&quot;"+","+"&quot;"+data.start+"&quot;"+","+"&quot;"+data.end+"&quot;"+');curry_checked(this)">'
                }},
            {title:"Sample name",data:"Biosample_Name"},
            {title:"Strategies",data:"CLASS"},
            {title:"Enhancer ID",data:"SE_id"},
            {title:"Region",data:function(data){
                    return data.chr+":"+data.start+"-"+data.end+
                        '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.chr+"&Start="+data.start+"&End="+data.end+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                }},
            {title:"Element",data:"element"},
            {title:"Size",data:"size"},
            {title:"Treat",data:"Treat"},
            {title:"Control",data:"Control"},
            {title:"Rank",data:"rank"},
            {title:"isSuper",data:"isSuper"},
            {title:"Sample ID",data:"SE-TE_Sample_ID"},
//            {title:"Visualization",data:function(data){
//                    return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.chr+":"+data.start+".."+data.end+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                        '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                        '</a>'+
//                        '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.chr+":"+data.start+"-"+data.end+'">'+
//                        '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                        '</a>'
//                }},
        ]
    });
}
function position(se_id,chr,start,end){
    param_se_id=se_id;
    param_chr=chr;
    param_start=start;
    param_end=end;
}
function curry_checked(obj){
    checked_num=$("input[name='position']").index(obj);
}
function reset_checked_color(){
    checked_num=0;
    check_num_Chromatin=0;
}
function EnhancerRegion(SE_Url){
    let table=document.getElementById("create_table_1");
    table.innerHTML="";
    let common_columns=[
        {title:"Enhancer ID",data:function(data){
                return param_se_id;
            }},
        {title:"SNP_A",data:"SNP_A"},
        {title:"CHR_A",data:"CHR_A"},
        {title:"Position_A",data:"BP_A"},
        {title:"SNP_B",data:"SNP_B"},
        {title:"CHR_B",data:"CHR_B"},
        {title:"Position_B",data:"BP_B"},
        {title:"R2",data:"R2"},
        {title:"DP",data:"DP"},
    ];
    let table_load=document.getElementById("table_load_1");
    table_load.innerHTML="";
    table_load.innerHTML=table_load.innerHTML+
        '<img src='+ctx+"Greap/static/img/loading.gif"+'>';
    let information_title_1=document.getElementById("information_title_1");
    let sample_name=$('#Sample_name option:selected').val();
    let sample_type=$('#Sample_type option:selected').val();
    let Tissue=$('#Tissue option:selected').val();
    let table_columns;
    table.innerHTML=table.innerHTML+
        '<table id="mytable_1" class="table_style table-striped table-bordered table-hover" width="100%">'+'</table>';
    let formData=new FormData();
    formData.append("Biosample_Type",sample_type);
    formData.append("Biosample_Name",sample_name);
    formData.append("chr",param_chr);
    formData.append("start",param_start);
    formData.append("end",param_end);
    formData.append("tiss",Tissue);
    $.ajax({
        url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/SE/"+SE_Url,
        type:'post',
        data:formData,
        cache:false,
        processData:false,
        contentType:false,
        success:function(result){
            if(SE_Url==="SNP"){
                information_title_1.innerText="* Common SNPs which appear in enhancer regions associated with lncRNA";
                table_columns=[
                    {title:"Enhancer ID",data:function(data){
                            return param_se_id;
                        }},
                    {title:"SNP ID",data:"ID"},
                    {title:"SNP chr",data:"CHR"},
                    {title:"SNP position",data:"STAR"},
                    {title:"Ref",data:"ref"},
                    {title:"Alt",data:"alt"},
                    {title:"AFR",data:function(data){
                            return'<a onclick="SNP_Detail_1('+"&quot;"+"AFR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.AFR+'</a>'
                        }},
                    {title:"AMR",data:function(data){
                            return'<a onclick="SNP_Detail_1('+"&quot;"+"AMR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.AMR+'</a>'
                        }},
                    {title:"EAS",data:function(data){
                            return'<a onclick="SNP_Detail_1('+"&quot;"+"EAS"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.EAS+'</a>'
                        }},
                    {title:"EUR",data:function(data){
                            return'<a onclick="SNP_Detail_1('+"&quot;"+"EUR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.EUR+'</a>'
                        }},
                    {title:"SAS",data:function(data){
                            return'<a onclick="SNP_Detail_1('+"&quot;"+"SAS"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.SAS+'</a>'
                        }},
                    {title:"eQTL",data:function(data){
                            return'<a onclick="SNP_Detail_1('+"&quot;"+"EQTL"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.Eqtl+'</a>'
                        }},
                    {title:"Risk SNP",data:function(data){
                            return'<a onclick="SNP_Detail_1('+"&quot;"+"RISKSNP"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.Risk_SNP+'</a>'
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(SE_Url==="RiskSNP"){
                information_title_1.innerText="* Risk SNPs which appear in enhancer regions associated with lncRNA";
                table_columns=[
                    {title:"Enhancer ID",data:function(data){
                            return param_se_id;
                        }},
                    {title:"SNP ID",data:"SNP_ID"},
                    {title:"SNP chr",data:"CHR"},
                    {title:"SNP position",data:"STAR"},
                    {title:"Gene",data:"ref"},
                    {title:"Disease",data:"alt"},
                    {title:"Type",data:"Gene"},
                    {title:"P_value",data:"disease"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(SE_Url==="EqtL"){
                information_title_1.innerText="* eQTLs which appear in enhancer regions associated with lncRNA";
                table_columns=[
                    {title:"Enhancer ID",data:function(data){
                            return param_se_id;
                        }},
                    {title:"SNP ID",data:"SNP_ID"},
                    {title:"SNP chr",data:"CHR"},
                    {title:"SNP position",data:"STAR"},
                    {title:"Gene",data:"eqtl_gene"},
                    {title:"Cell_type",data:"eqtl_cell_type"},
                    {title:"Source",data:"eqtl_source"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(SE_Url==="SAS"){
                information_title_1.innerText="* Sout Asian Population LDs which appear in enhancer regions associated with lncRNA";
                table_columns=common_columns
            }else if(SE_Url==="EUR"){
                information_title_1.innerText="* European Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(SE_Url==="EAS"){
                information_title_1.innerText="* East Asian Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(SE_Url==="AMR"){
                information_title_1.innerText="* American Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(SE_Url==="AFR"){
                information_title_1.innerText="* African Population LDs which appear in promoter region";
                table_columns=common_columns
            }else if(SE_Url==="motif"){
                information_title_1.innerText="* TFs predicted by motif which appear in enhancer regions associated with lncRNA";
                table_columns=[
                    {title:"Database",data:"Database"},
                    {title:"TF",data:"TF"},
                    {title:"Region",data:function(data){
                            return data.CHR+":"+data.Star+"-"+data.End+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.Star+"&End="+data.End+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Strand",data:"strand"},
                    {title:"Scores",data:"Scores"},
                    {title:"Pvalue",data:"Pvalue"},
                    {title:"Motif sequence",data:"Motif_sequence"},
//                    {title:"Visualization",data:function(data){
//                            return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.Star+".."+data.End+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.Star+"-"+data.End+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(SE_Url==="TF"){
                information_title_1.innerText="* TFs identified by chip-seq which appear in enhancer regions associated with lncRNA";
                table_columns=[
                    {title:"Sample name",data:function(){
                            return sample_name;
                        }},
                    {title:"Enhancer ID",data:function(data){
                            return param_se_id;
                        }},
                    {title:"TF",data:"TF"},
                    {title:"TF Region",data:function(data){
                            return data.CHR+":"+data.STAR+"-"+data.END+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.STAR+"&End="+data.END+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Class",data:"t_class"},
                    {title:"Source",data:"ID"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.END+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+"-"+data.END+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(SE_Url==="450K"){
                information_title_1.innerText="* Methylation sites of 450 k array which appear in enhancer regions associated with lncRNA";
                table_columns=[
                    {title:"Sample name",data:function(){
                            return sample_name;
                        }},
                    {title:"Enhancer ID",data:function(data){
                            return param_se_id;
                        }},
                    {title:"450K ID",data:"CG"},
                    {title:"450K chr",data:"CHR"},
                    {title:"450K position",data:"STAR"},
                    {title:"Bate",data:"bate"},
                    {title:"Source",data:function(){
                            return result.result;
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(SE_Url==="WGBS"){
                information_title_1.innerText="* Methylation sites of whole-genome shotgun bisulfite sequencing which appear in enhancer regions associated with lncRNA";
                table_columns=[
                    {title:"Sample name",data:function(data){
                            return sample_name;
                        }},
                    {title:"Enhancer ID",data:function(data){
                            return param_se_id;
                        }},
                    {title:"WGBS chr",data:"CHR"},
                    {title:"WGBS position",data:"STAR"},
                    {title:"Read",data:"read"},
                    {title:"Source",data:function(){
                            return result.result;
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(SE_Url==="Intersection"){
                information_title_1.innerText="* Chromatin interactions which appear in enhancer regions associated with lncRNA";
                table_columns=[
                    {title:"Tissue",data:"Tissue"},
                    {title:"Enhancer ID",data:function(data){
                            return param_se_id;
                        }},
                    {title:"Region_A",data:function(data){
                            return data.InteractorAChr+":"+data.InteractorAStart+"-"+data.InteractorAEnd+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.InteractorAChr+"&Start="+data.InteractorAStart+"&End="+data.InteractorAEnd+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Region_B",data:function(data){
                            return data.InteractorBChr+":"+data.InteractorBStart+"-"+data.InteractorBEnd+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.InteractorBChr+"&Start="+data.InteractorBStart+"&End="+data.InteractorBEnd+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Method",data:"ID"},
                    {title:"Source",data:"Class"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://bio.licpathway.net/TRlnc_give/data?interaction='+data.ID+"&coordinateCarOne="+data.InteractorAChr+"&coordinateStartOne="+data.InteractorAStart+"&coordinateEndOne="+data.InteractorAEnd+"&coordinateCarTwo="+data.InteractorBChr+"&coordinateStartTwo="+data.InteractorBStart+"&coordinateEndTwo="+data.InteractorBEnd+'" target="_blank">'+
//                                '<img src="'+ctx+'img/GIVE.jpg" width="50px">'+
//                                '</a>'
//                        }},
                    {title:"Sample name",data:function(){
                            return sample_name;
                        }},
                ]
            }
            table_load.innerHTML="";
            $('#mytable_1').DataTable({
                scrollX:true,
                aLengthMenu:[5,10,15],
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
                    url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/SE/"+SE_Url,
                    type:'post',
                    data:function(data){
                        data.chr=param_chr;
                        data.start=param_start;
                        data.end=param_end;
                        data.Biosample_Type=sample_type;
                        data.Biosample_Name=sample_name;
                        data.tiss=Tissue;
                    },
                },
                columns:table_columns,
            });
        }
    });
}
function SE_Histone_Select(){
    let table=document.getElementById("create_table_1");
    let information_title=document.getElementById("information_title_1");
    information_title.innerText="* Histone modifications which appear in enhancer regions associated with lncRNA";
    table.innerHTML="";
    table.innerHTML=table.innerText+
        "Histone modification:&nbsp;"+
        '<select id="SE_Histone" onchange="SE_Histone()" style="width:150px">'+
        '<option value="H3K36me3">H3K36me3</option>'+
        '<option value="H3K4me3" selected>H3K4me3</option>'+
        '<option value="H3K4me1">H3K4me1</option>'+
        '<option value="H3K79me2">H3K79me2</option>'+
        '<option value="H3K9ac">H3K9ac</option>'+
        '<option value="H4K20me1">H4K20me1</option>'+
        '</select>'+
        '<table id="mytable_1" class="table_style table-striped table-bordered table-hover" width="100%">'+'</table>';
    SE_Histone();
}
function SE_Histone(){
    let sample_name=$('#Sample_name option:selected').val();
    let sample_type=$('#Sample_type option:selected').val();
    let Tissue=$('#Tissue option:selected').val();
    let se_histone=$('#SE_Histone option:selected').val();
    $('#mytable_1').DataTable({
        scrollX:true,
        aLengthMenu:[5,10,15],
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
            url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/SE/Histone",
            type:'post',
            data:function(data){
                data.Biosample_Type=sample_type;
                data.Biosample_Name=sample_name;
                data.tiss=Tissue;
                data.histone=se_histone;
                data.chr=param_chr;
                data.start=param_start;
                data.end=param_end;
            }
        },
        columns:[
            {title:"Sample name",data:function(){
                    return sample_name;
                }},
            {title:"Enhancer ID",data:function(data){
                    return param_se_id;
                }},
            {title:"Histone",data:"Histone"},
            {title:"Region",data:function(data){
                    return data.CHR+":"+data.Start+"-"+data.End+
                        '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.Start+"&End="+data.End+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                }},
            {title:"Source",data:"Source"},
//            {title:"Visualization",data:function(data){
//                    return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.Start+".."+data.End+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                        '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                        '</a>'+
//                        '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.Start+"-"+data.End+'">'+
//                        '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                        '</a>'
//                }},
        ]
    });
}
var param_chr_Chromatin;
var param_start_Chromatin;
var param_end_Chromatin;
function curry_checked_Chromatin(obj){
    check_num_Chromatin=$("input[name='Chromatin_position']").index(obj);
}
function Chromatin(){
    let table=document.getElementById("create_table_2");
    table.innerHTML="";
    let table_load=document.getElementById("table_load_2");
    table_load.innerHTML="";
    table_load.innerHTML=table_load.innerHTML+
        '<img src='+ctx+"Greap/static/img/loading.gif"+'>';
    let information_title_2=document.getElementById("information_title_2");
    let sample_name=$('#Sample_name option:selected').val();
    let sample_type=$('#Sample_type option:selected').val();
    let formData=new FormData();
    formData.append("GeneName",lncrna_name);
    formData.append("regulation",regulation);
    formData.append("Biosample_Type",sample_type);
    formData.append("Biosample_Name",sample_name);
    information_title_2.innerText="* Chromatin accessibility regions associated with lncRNA";
    table.innerHTML=table.innerHTML+
        '<table id="mytable_2" class="table_style table-striped table-bordered table-hover" width="100%">'+'</table>';
    $.ajax({
        url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/Chromatin",
        type:"post",
        data:formData,
        cache:false,
        processData:false,
        contentType:false,
        success:function(result){
            table_load.innerHTML="";
            $('#mytable_2').DataTable({
                scrollX:true,
                aLengthMenu:[5,10,15],
                dom:"Bfrtip",
                fnInitComplete:function(obj){
                    $("input[name='Chromatin_position']").eq(check_num_Chromatin).attr("checked","checked");
                    if(obj.aoData[0]===undefined){
                        param_chr_Chromatin="";param_start_Chromatin="";param_end_Chromatin="";
                    }else{
                        param_chr_Chromatin=obj.aoData[0]._aData.CHR;
                        param_start_Chromatin=obj.aoData[0]._aData.Start;
                        param_end_Chromatin=obj.aoData[0]._aData.End;
                    }
                },
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
                    url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/Chromatin",
                    type:'post',
                    data:function(data){
                        data.GeneName=lncrna_name;
                        data.regulation=regulation;
                        data.Biosample_Type=sample_type;
                        data.Biosample_Name=sample_name;
                    }
                },
                columns:[
                    {title:"",data:function(data){
                            return'<input name="Chromatin_position" type="radio" onclick="position_Chromatin('+"&quot;"+data.CHR+"&quot;"+","+"&quot;"+data.Start+"&quot;"+","+"&quot;"+data.End+"&quot;"+');curry_checked_Chromatin(this)">'
                        }},
                    {title:"Sample name",data:function(){
                            return sample_name;
                        }},
                    {title:"Strategies",data:function(data){
                            if(data.t_class==="1"){
                                return"Overlap";
                            }else if(data.t_class==="2"){
                                return"PROXIMAL";
                            }else if(data.t_class==="3"){
                                return"CLOSEST";
                            }
                        }},
                    {title:"Region",data:function(data){
                            return data.CHR+":"+data.Start+"-"+data.End+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.Start+"&End="+data.End+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Source",data:function(data){
                            if(data.method==="DNase-seq"){
                                return result.result[0];
                            }else if(data.method==="ATAC-seq"){
                                return result.result[1];
                            }
                        }},
                    {title:"Method",data:"method"},
//                    {title:"Visualization",data:function(data){
//                            return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.Start+".."+data.End+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.Start+"-"+data.End+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            });
        }
    });
}
function position_Chromatin(chr,start,end){
    param_chr_Chromatin=chr;
    param_start_Chromatin=start;
    param_end_Chromatin=end;
}
function ChromatinRegion(Chromatin_Url){
    let table=document.getElementById("create_table_2");
    table.innerHTML="";
    let common_columns=[
        {title:"Region",data:function(){
                return param_start_Chromatin+"-"+param_end_Chromatin+
                    '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+param_chr_Chromatin+"&Start="+param_start_Chromatin+"&End="+param_end_Chromatin+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
            }},
        {title:"SNP_A",data:"SNP_A"},
        {title:"CHR_A",data:"CHR_A"},
        {title:"Position_A",data:"BP_A"},
        {title:"SNP_B",data:"SNP_B"},
        {title:"CHR_B",data:"CHR_B"},
        {title:"Position_B",data:"BP_B"},
        {title:"R2",data:"R2"},
        {title:"DP",data:"DP"},
    ];
    let table_load=document.getElementById("table_load_2");
    table_load.innerHTML="";
    table_load.innerHTML=table_load.innerHTML+
        '<img src='+ctx+"Greap/static/img/loading.gif"+'>';
    let information_title_2=document.getElementById("information_title_2");
    let sample_name=$('#Sample_name option:selected').val();
    let sample_type=$('#Sample_type option:selected').val();
    let Tissue=$('#Tissue option:selected').val();
    let formData=new FormData();
    formData.append("GeneName",lncrna_name);
    formData.append("regulation",regulation);
    formData.append("Biosample_Type",sample_type);
    formData.append("Biosample_Name",sample_name);
    formData.append("chr",param_chr_Chromatin);
    formData.append("start",param_start_Chromatin);
    formData.append("end",param_end_Chromatin);
    formData.append("tiss",Tissue);
    let table_columns;
    table.innerHTML=table.innerHTML+
        '<table id="mytable_2" class="table_style table-striped table-bordered table-hover dataTable" width="100%">'+'</table>';
    $.ajax({
        url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/Chromatin/"+Chromatin_Url,
        type:"post",
        data:formData,
        cache:false,
        processData:false,
        contentType:false,
        success:function(result){
            if(Chromatin_Url==="SNP"){
                information_title_2.innerText="* Common SNPs which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=[
                    {title:"Region",data:function(){
                            return param_start_Chromatin+"-"+param_end_Chromatin+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+param_chr_Chromatin+"&Start="+param_start_Chromatin+"&End="+param_end_Chromatin+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"SNP ID",data:"ID"},
                    {title:"SNP chr",data:"CHR"},
                    {title:"SNP position",data:"STAR"},
                    {title:"Ref",data:"ref"},
                    {title:"Alt",data:"alt"},
                    {title:"AFR",data:function(data){
                            return'<a onclick="SNP_Detail_2('+"&quot;"+"AFR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.AFR+'</a>'
                        }},
                    {title:"AMR",data:function(data){
                            return'<a onclick="SNP_Detail_2('+"&quot;"+"AMR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.AMR+'</a>'
                        }},
                    {title:"EAS",data:function(data){
                            return'<a onclick="SNP_Detail_2('+"&quot;"+"EAS"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.EAS+'</a>'
                        }},
                    {title:"EUR",data:function(data){
                            return'<a onclick="SNP_Detail_2('+"&quot;"+"EUR"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.EUR+'</a>'
                        }},
                    {title:"SAS",data:function(data){
                            return'<a onclick="SNP_Detail_2('+"&quot;"+"SAS"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.SAS+'</a>'
                        }},
                    {title:"eQTL",data:function(data){
                            return'<a onclick="SNP_Detail_2('+"&quot;"+"EQTL"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.Eqtl+'</a>'
                        }},
                    {title:"Risk SNP",data:function(data){
                            return'<a onclick="SNP_Detail_2('+"&quot;"+"RISKSNP"+"&quot;"+","+"&quot;"+data.ID+"&quot;"+")"+'">'+data.Risk_SNP+'</a>'
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Chromatin_Url==="RiskSNP"){
                information_title_2.innerText="* Risk SNPs which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=[
                    {title:"Region",data:function(){
                            return param_start_Chromatin+"-"+param_end_Chromatin+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+param_chr_Chromatin+"&Start="+param_start_Chromatin+"&End="+param_end_Chromatin+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"SNP ID",data:"SNP_ID"},
                    {title:"SNP chr",data:"CHR"},
                    {title:"SNP position",data:"STAR"},
                    {title:"Gene",data:"ref"},
                    {title:"Disease",data:"alt"},
                    {title:"Type",data:"Gene"},
                    {title:"P_value",data:"disease"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Chromatin_Url==="Eqtl"){
                information_title_2.innerText="* eQTLs which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=[
                    {title:"Region",data:function(){
                            return param_start_Chromatin+"-"+param_end_Chromatin+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+param_chr_Chromatin+"&Start="+param_start_Chromatin+"&End="+param_end_Chromatin+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"SNP ID",data:"SNP_ID"},
                    {title:"SNP chr",data:"CHR"},
                    {title:"SNP position",data:"STAR"},
                    {title:"Gene",data:"eqtl_gene"},
                    {title:"Cell_type",data:"eqtl_cell_type"},
                    {title:"Source",data:"eqtl_source"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Chromatin_Url==="SAS"){
                information_title_2.innerText="* Sout Asian Population LDs which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=common_columns;
            }else if(Chromatin_Url==="EUR"){
                information_title_2.innerText="* European Population LDs which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=common_columns;
            }else if(Chromatin_Url==="EAS"){
                information_title_2.innerText="* East Asian Population LDs which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=common_columns;
            }else if(Chromatin_Url==="AMR"){
                information_title_2.innerText="* American Population LDs which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=common_columns;
            }else if(Chromatin_Url==="AFR"){
                information_title_2.innerText="* African Population LDs which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=common_columns;
            }else if(Chromatin_Url==="motif"){
                information_title_2.innerText="* TFs predicted by motif which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=[
                    {title:"Database",data:"Database"},
                    {title:"TF",data:"TF"},
                    {title:"Region",data:function(data){
                            return data.CHR+":"+data.Star+"-"+data.End+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.Star+"&End="+data.End+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Strand",data:"strand"},
                    {title:"Scores",data:"Scores"},
                    {title:"Pvalue",data:"Pvalue"},
                    {title:"Motif sequence",data:"Motif_sequence"},
//                    {title:"Visualization",data:function(data){
//                            return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.Star+".."+data.End+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.Star+"-"+data.End+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Chromatin_Url==="TF"){
                information_title_2.innerText="* TFs identified by ChIP-seq which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=[
                    {title:"Sample name",data:function(){
                            return sample_name;
                        }},
                    {title:"Region",data:function(){
                            return param_start_Chromatin+"-"+param_end_Chromatin+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+param_chr_Chromatin+"&Start="+param_start_Chromatin+"&End="+param_end_Chromatin+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"TF",data:"TF"},
                    {title:"TF Region",data:function(data){
                            return data.CHR+":"+data.STAR+"-"+data.END+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.STAR+"&End="+data.END+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Class",data:"t_class"},
                    {title:"Source",data:"ID"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.END+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+"-"+data.END+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Chromatin_Url==="450K"){
                information_title_2.innerText="* Methylation sites of 450 k array which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=[
                    {title:"Sample name",data:function(){
                            return sample_name;
                        }},
                    {title:"Region",data:function(){
                            return param_start_Chromatin+"-"+param_end_Chromatin;
                        }},
                    {title:"450K ID",data:"CG"},
                    {title:"450K chr",data:"CHR"},
                    {title:"450K position",data:"STAR"},
                    {title:"Bate",data:"bate"},
                    {title:"Source",data:function(){
                            return result.result;
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Chromatin_Url==="WGBS"){
                information_title_2.innerText="* Methylation sites which appear in the lncRNA associated Chromatin accessibility region";
                table_columns=[
                    {title:"Sample name",data:function(data){
                            return sample_name;
                        }},
                    {title:"Region",data:function(){
                            return param_start_Chromatin+"-"+param_end_Chromatin;
                        }},
                    {title:"WGBS chr",data:"CHR"},
                    {title:"WGBS position",data:"STAR"},
                    {title:"Read",data:"read"},
                    {title:"Source",data:function(){
                            return result.result;
                        }},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.STAR+".."+data.STAR+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                                '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                                '</a>'+
//                                '<a href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.STAR+'">'+
//                                '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                                '</a>'
//                        }},
                ]
            }else if(Chromatin_Url==="Intersection"){
                information_title_2.innerText="* Chromatin interactions which appear in Chromatin accessibility region associated with lncRNA";
                table_columns=[
                    {title:"Tissue",data:"Tissue"},
                    {title:"Region",data:function(){
                            return param_start_Chromatin+"-"+param_end_Chromatin+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+param_chr_Chromatin+"&Start="+param_start_Chromatin+"&End="+param_end_Chromatin+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Region_A",data:function(data){
                            return data.InteractorAChr+":"+data.InteractorAStart+"-"+data.InteractorAEnd+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.InteractorAChr+"&Start="+data.InteractorAStart+"&End="+data.InteractorAEnd+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Region_B",data:function(data){
                            return data.InteractorBChr+":"+data.InteractorBStart+"-"+data.InteractorBEnd+
                                '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.InteractorBChr+"&Start="+data.InteractorBStart+"&End="+data.InteractorBEnd+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                        }},
                    {title:"Method",data:"ID"},
                    {title:"Source",data:"Class"},
//                    {title:"Visualization",data:function(data){
//                            return'<a href="http://bio.licpathway.net/TRlnc_give/data?interaction='+data.ID+"&coordinateCarOne="+data.InteractorAChr+"&coordinateStartOne="+data.InteractorAStart+"&coordinateEndOne="+data.InteractorAEnd+"&coordinateCarTwo="+data.InteractorBChr+"&coordinateStartTwo="+data.InteractorBStart+"&coordinateEndTwo="+data.InteractorBEnd+'" target="_blank">'+
//                                '<img src="'+ctx+'img/GIVE.jpg" width="50px">'+
//                                '</a>'
//                        }},
                    {title:"Sample name",data:function(){
                            return sample_name;
                        }},
                ]
            }
            table_load.innerHTML="";
            $('#mytable_2').DataTable({
                scrollX:true,
                aLengthMenu:[5,10,15],
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
                    url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/Chromatin/"+Chromatin_Url,
                    type:'post',
                    data:function(data){
                        data.GeneName=lncrna_name;
                        data.regulation=regulation;
                        data.Biosample_Type=sample_type;
                        data.Biosample_Name=sample_name;
                        data.chr=param_chr_Chromatin;
                        data.start=param_start_Chromatin;
                        data.end=param_end_Chromatin;
                        data.tiss=Tissue
                    }
                },
                columns:table_columns
            });
        }
    });
}
function Chromatin_Histone_Select(){
    let table=document.getElementById("create_table_2");
    let information_title=document.getElementById("information_title_2");
    information_title.innerText="* Histone modifications which appear in the lncRNA associated Chromatin accessibility region";
    table.innerHTML="";
    table.innerHTML=table.innerText+
        "Histone modification:&nbsp;"+
        '<select id="Chromatin_Histone" onchange="Chromatin_Histone_Select()" style="width:150px">'+
        '<option value="H3K36me3">H3K36me3</option>'+
        '<option value="H3K4me3" selected>H3K4me3</option>'+
        '<option value="H3K4me1">H3K4me1</option>'+
        '<option value="H3K79me2">H3K79me2</option>'+
        '<option value="H3K9ac">H3K9ac</option>'+
        '<option value="H4K20me1">H4K20me1</option>'+
        '</select>'+
        '<table id="mytable_2" class="table_style table-striped table-bordered table-hover" width="100%">'+'</table>';
    Chromatin_Histone();
}
function Chromatin_Histone(){
    let sample_name=$('#Sample_name option:selected').val();
    let sample_type=$('#Sample_type option:selected').val();
    let Tissue=$('#Tissue option:selected').val();
    let chromatin_histone=$('#Chromatin_Histone option:selected').val();
    $('#mytable_2').DataTable({
        scrollX:true,
        aLengthMenu:[5,10,15],
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
            url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/Chromatin/Histone",
            type:'post',
            data:function(data){
                data.Biosample_Type=sample_type;
                data.Biosample_Name=sample_name;
                data.tiss=Tissue;
                data.histone=chromatin_histone;
                data.chr=param_chr_Chromatin;
                data.start=param_start_Chromatin;
                data.end=param_end_Chromatin;
            }
        },
        columns:[
            {title:"Sample name",data:function(){
                    return sample_name;
                }},
            {title:"Region",data:function(){
                    return param_start_Chromatin+"-"+param_end_Chromatin+
                        '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+param_chr_Chromatin+"&Start="+param_start_Chromatin+"&End="+param_end_Chromatin+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                }},
            {title:"Histone",data:"Histone"},
            {title:"Region",data:function(data){
                    return data.CHR+":"+data.Start+"-"+data.End+
                        '<a onclick = openWinthree("http://www.licpathway.net/TRlnc/search/transition?CHR='+data.CHR+"&Start="+data.Start+"&End="+data.End+'<img src="'+ctx+'img/change.png" width="50px">'+'</a>'
                }},
            {title:"Source",data:"Source"},
//            {title:"Visualization",data:function(data){
//                    return'<a target="_blank" href="http://www.licpathway.net/TRlnc_JBrowser/?loc='+data.CHR+":"+data.Start+".."+data.End+"&tracks=GeneAnnot_GENCODE%2CEnhancer%2CATAC-seq%2CDNaseI-seq&highlight="+'">'+''+
//                        '<img src="'+ctx+'img/TRLnc.png" width="50px" alt="TRLnc.png">'+
//                        '</a>'+
//                        '<a target="_blank" href="http://genome-asia.ucsc.edu/cgi-bin/hgTracks?db=hg19&lastVirtModeType=default&lastVirtModeExtraState=&virtModeType=default&virtMode=0&nonVirtPosition=&position='+data.CHR+":"+data.Start+"-"+data.End+'">'+
//                        '<img src="'+ctx+'img/ucsc.jpg" width="50px" alt="ucsc.jpg">'+
//                        '</a>'
//                }},
        ]
    });
}
function SNP_Detail(SNP_Url,ID){
    let table=document.getElementById("create_table");
    table.innerHTML="";
    let common_columns=[
        {title:"CHR_A",data:"CHR_A"},
        {title:"Position_A",data:"BP_A"},
        {title:"SNP_A",data:"SNP_A"},
        {title:"CHR_B",data:"CHR_B"},
        {title:"Position_B",data:"BP_B"},
        {title:"SNP_B",data:"SNP_B"},
        {title:"R2",data:"R2"},
        {title:"DP",data:"DP"},
    ];
    let table_columns;
    let information_title=document.getElementById("information_title");
    information_title.innerText="";
    table.innerHTML=table.innerText+
        '<table id="SNP_AFR" class="table-striped table-bordered" width="100%">'+'</table>';
    if(SNP_Url==="AFR"){
        table_columns=common_columns;
    }else if(SNP_Url==="AMR"){
        table_columns=common_columns;
    }else if(SNP_Url==="EAS"){
        table_columns=common_columns;
    }else if(SNP_Url==="EUR"){
        table_columns=common_columns;
    }else if(SNP_Url==="SAS"){
        table_columns=common_columns;
    }else if(SNP_Url==="EQTL"){
        table_columns=[
            {title:"SNP ID",data:"SNP_ID"},
            {title:"CHR",data:"CHR"},
            {title:"Position",data:"STAR"},
            {title:"eQTL Gene",data:"eqtl_gene"},
            {title:"eQTL Cell Type",data:"eqtl_cell_type"},
            {title:"eQTL Source",data:"eqtl_source"},
        ]
    }else if(SNP_Url==="RISKSNP"){
        table_columns=[
            {title:"SNP ID",data:"SNP_ID"},
            {title:"CHR",data:"CHR"},
            {title:"Position",data:"STAR"},
            {title:"Gene",data:"Gene"},
            {title:"Disease",data:"disease"},
            {title:"Type",data:"Type"},
            {title:"P value",data:"P_value"},
        ]
    }
    $('#SNP_AFR').DataTable({
        bFilter:false,
        bDestroy:true,
        scrollX:true,
        aLengthMenu:[5,10,15],
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
            url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/SNP/"+SNP_Url,
            type:'post',
            data:function(data){
                data.ID=ID;
            }
        },
        columns:table_columns
    });
}
function SNP_Detail_1(SNP_Url,ID){
    let table=document.getElementById("create_table_1");
    table.innerHTML="";
    let common_columns=[
        {title:"CHR_A",data:"CHR_A"},
        {title:"Position_A",data:"BP_A"},
        {title:"SNP_A",data:"SNP_A"},
        {title:"CHR_B",data:"CHR_B"},
        {title:"Position_B",data:"BP_B"},
        {title:"SNP_B",data:"SNP_B"},
        {title:"R2",data:"R2"},
        {title:"DP",data:"DP"},
    ];
    let table_columns;
    let information_title=document.getElementById("information_title_1");
    information_title.innerText="";
    table.innerHTML=table.innerText+
        '<table id="SNP_detail_1" class="table-striped table-bordered" width="100%">'+'</table>';
    if(SNP_Url==="AFR"){
        table_columns=common_columns;
    }else if(SNP_Url==="AMR"){
        table_columns=common_columns;
    }else if(SNP_Url==="EAS"){
        table_columns=common_columns;
    }else if(SNP_Url==="EUR"){
        table_columns=common_columns;
    }else if(SNP_Url==="SAS"){
        table_columns=common_columns;
    }else if(SNP_Url==="EQTL"){
        table_columns=[
            {title:"SNP ID",data:"SNP_ID"},
            {title:"CHR",data:"CHR"},
            {title:"Position",data:"STAR"},
            {title:"eQTL Gene",data:"eqtl_gene"},
            {title:"eQTL Cell Type",data:"eqtl_cell_type"},
            {title:"eQTL Source",data:"eqtl_source"},
        ]
    }else if(SNP_Url==="RISKSNP"){
        table_columns=[
            {title:"SNP ID",data:"SNP_ID"},
            {title:"CHR",data:"CHR"},
            {title:"Position",data:"STAR"},
            {title:"Gene",data:"Gene"},
            {title:"Disease",data:"disease"},
            {title:"Type",data:"Type"},
            {title:"P value",data:"P_value"},
        ]
    }
    $('#SNP_detail_1').DataTable({
        bFilter:false,
        bDestroy:true,
        scrollX:true,
        aLengthMenu:[5,10,15],
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
            url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/SNP/"+SNP_Url,
            type:'post',
            data:function(data){
                data.ID=ID;
            }
        },
        columns:table_columns
    });
}
function SNP_Detail_2(SNP_Url,ID){
    let table=document.getElementById("create_table_2");
    table.innerHTML="";
    let common_columns=[
        {title:"CHR_A",data:"CHR_A"},
        {title:"Position_A",data:"BP_A"},
        {title:"SNP_A",data:"SNP_A"},
        {title:"CHR_B",data:"CHR_B"},
        {title:"Position_B",data:"BP_B"},
        {title:"SNP_B",data:"SNP_B"},
        {title:"R2",data:"R2"},
        {title:"DP",data:"DP"},
    ];
    let table_columns;
    let information_title=document.getElementById("information_title_2");
    information_title.innerText="";
    table.innerHTML=table.innerText+
        '<table id="SNP_detail_2" class="table-striped table-bordered" width="100%">'+'</table>';
    if(SNP_Url==="AFR"){
        table_columns=common_columns;
    }else if(SNP_Url==="AMR"){
        table_columns=common_columns;
    }else if(SNP_Url==="EAS"){
        table_columns=common_columns;
    }else if(SNP_Url==="EUR"){
        table_columns=common_columns;
    }else if(SNP_Url==="SAS"){
        table_columns=common_columns;
    }else if(SNP_Url==="EQTL"){
        table_columns=[
            {title:"SNP ID",data:"SNP_ID"},
            {title:"CHR",data:"CHR"},
            {title:"Position",data:"STAR"},
            {title:"eQTL Gene",data:"eqtl_gene"},
            {title:"eQTL Cell Type",data:"eqtl_cell_type"},
            {title:"eQTL Source",data:"eqtl_source"},
        ]
    }else if(SNP_Url==="RISKSNP"){
        table_columns=[
            {title:"SNP ID",data:"SNP_ID"},
            {title:"CHR",data:"CHR"},
            {title:"Position",data:"STAR"},
            {title:"Gene",data:"Gene"},
            {title:"Disease",data:"disease"},
            {title:"Type",data:"Type"},
            {title:"P value",data:"P_value"},
        ]
    }
    $('#SNP_detail_2').DataTable({
        bFilter:false,
        bDestroy:true,
        scrollX:true,
        aLengthMenu:[5,10,15],
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
            url:"http://101.34.103.67:8003/analysis/trlnc/search/LncName/SNP/"+SNP_Url,
            type:'post',
            data:function(data){
                data.ID=ID;
            }
        },
        columns:table_columns
    });
}

/*Pathway associated*/
function Pathway_associated() {
    $('#Pathway_associated').DataTable({
        bFilter:false,
        bDestroy:true,
        scrollX:true,
        aLengthMenu:[5,10,15],
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
            url:"http://101.34.103.67:8003/analysis/trlnc/search/Ranalyse",
            type:'post',
            data:function(data){
                data.lncRNA=lncrna_name;
                data.tftype=$("#Sample_name").val();
                data.promoterRegion=regulation;
            }
        },
        columns:[
            {title:"Pathway ID",data:function (data) {
                    return '<a class="Pathway_a" target="_blank" href=http://www.licpathway.net/msg/ComPAT/node.do?id='+data.pathwayID+'>'+data.pathwayID+'</a>'
                }},
            {title:"Pathway name",data:"pathwayName"},
            {title:"Source",data:"source"},
            {title:"Species",data:"species"},
            {title:"GeneID_Type",data:"geneID_Type"},
            {title:"AnnGene",data:"annGene"},
            {title:"GeneNumber",data:"geneNumber"},
            {title:"PValue",data:"pvalue"},
            {title:"FDR",data:"fdr"}
        ]
    });
}
