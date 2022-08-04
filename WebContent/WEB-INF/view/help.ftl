<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Help</title>
    <link rel="icon" type="image/x-icon" href="${base.contextPath}/static/img/favicon.ico"/>
    <script src="${base.contextPath}/static/js/jquery.min.js"></script>
    <script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
    <link href="${base.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">
    <script src="${base.contextPath}/static/js/echarts.js"></script>
    <link href="${base.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${base.contextPath}/static/css/header.css">
    <link rel="stylesheet" href="${base.contextPath}/static/css/footer.css"/>
    <link href="${base.contextPath}/static/css/home.css" rel="stylesheet">
    <link href="${base.contextPath}/static/css/help.css" rel="stylesheet">
    <link href="${base.contextPath}/static/css/style.css" rel="stylesheet">
    <script>
        $(function () {
            $("[data-toggle='popover']").popover();
        });
    </script>
</head>

<body id="body">
<#include "nav/navbar.ftl" />
<div class="row" style="margin-top: 100px;">
    <div class="col-lg-2 ">
        <div class="panel-group" role="tablist">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title"><a href="#Disease">Help</a></h4>
                </div>
                <div class="list-group" id="left_classI_1">
                    <a class="list-group-item" href="#Introduction"><span class="badge"></span>Introduction</a>
                    <a class="list-group-item" href="#How to Use the GREAP?"><span class="badge"></span>How to Use the GREAP?</a>
                    <a class="list-group-item" href="#Docker_image"><span class="badge"></span>Docker image</a>
                    <a class="list-group-item" href="#GREAP Sets"><span class="badge"></span>GREAP Sets</a>
                </div>
            </div>
        </div>
        <div class="panel-group" role="tablist">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title"><a href="#ChromHMM">Sets</a></h4>
                </div>
                <div class="list-group" id="left_classI_1">
                    <a class="list-group-item" href="#ChromHMM"><span class="badge"></span>C1:ChromHMM</a>
                    <a class="list-group-item" href="#TF"><span class="badge"></span>C2:TF</a>
                    <a class="list-group-item" href="#TcoF"><span class="badge"></span>C3:TcoF</a>
                    <a class="list-group-item" href="#Histone"><span class="badge"></span>C4:Histone</a>
                    <a class="list-group-item" href="#ATAC"><span class="badge"></span>C5:ATAC-seq</a>
                    <a class="list-group-item" href="#Enhancer"><span class="badge"></span>C6:Enhancer</a>
                    <a class="list-group-item" href="#Super_Enhancer"><span class="badge"></span>C7:Super_Enhancer</a>
                    <a class="list-group-item" href="#SNP"><span class="badge"></span>C8&C9:SNP&eQTL</a>
                    <a class="list-group-item" href="#Methylation"><span class="badge"></span>C10:Methylation</a>
                    <a class="list-group-item" href="#LncRNA"><span class="badge"></span>C11:LncRNA</a>
                    <a class="list-group-item" href="#mRNA"><span class="badge"></span>C12:mRNA</a>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-10">
        <div class="panel panel-success">
            <a name="Introduction" style="position: relative;top: -100px;"></a>
            <div class="panel-heading"><h2><font style="color: white">Introduction</font></h2></div>

            <p style="text-align:justify;padding:10px 10px;font-size: 120%;">
                GREAP is a powerful platform that provides a variety of types of genome region sets for users and perform annotation and enrichment analysis of genome region sets based on genome region lists submited by users. GREAP integrated and classified various types of genome region collections. Importantly, based on these reference sets, GREAP provides two approaches for genomic region set annotation and enrichment analysis. GREAP also provides a user-friendly interface to search, browse and visualize detailed information about these genome region sets.
            <div style="text-align:center;">
                <img src="${base.contextPath}/static/img/help1.png" border="0" style="width:880px;">
            </div>
            <a name="How to Use the GREAP?" style="position: relative;top: -50px;"></a>
            <div class="panel-heading" style="margin-top: 20px;"><h2><font style="color: white">How to Use the GREAP?</font></h2></div>
            <div class="panel-body">
                <button id="Analysis" style="width:500px" class="btn btn-success collapsed" type="button" data-toggle="collapse" data-target="#analysis" aria-expanded="false" aria-controls="analysis">
                    <font size="3">Analysis</font>
                </button>
                <div class="collapse" id="analysis" aria-expanded="false" style="height: 0px;">
                    <br>
                    <div style="border-style: double;padding: 20px;font-size: 120%;text-align:justify ">
                        <b>I. Input:</b> Allow the user to input an interesting genome region list or a bed file. <br><br><b>II. Options:</b> Select class and sub class of reference sets and optional parameters for analysis.<br><br><b>III. Analysis:</b> Annotate the input into the reference collections selected by the user, and calculate the significance of enrichment analysis by hypergeometric test or LOLA according to the number of genome region intersecting.<br><br><b>IV. Output:</b> GREAP will return all significant collections information related the input to provide users with download and visualization. <br>
                        <div style="text-align:center;"><img src="${base.contextPath}/static/img/help_analysis.png" style="width: 100%;"></div>
                        <div style="text-align:center;"><img src="${base.contextPath}/static/img/help_analysis2.png" style="width: 100%;"></div>
                    </div>
                </div>
                <hr>

                <button id="Browse" style="width:500px" class="btn btn-success collapsed" type="button" data-toggle="collapse" data-target="#browse" aria-expanded="false" aria-controls="browse">
                    <font size="3">Browse</font>
                </button>
                <div class="collapse" id="browse" aria-expanded="false" style="height: 0px;">
                    <br>
                    <div style="border-style: double;padding: 20px;font-size: 120%;text-align:justify ">
                        The "Browse" page is organized as an interactive table for quickly searching for genome region sets and customizing filters using "Class" and "Sub Class". GREAP provides both input box and drop - down menu, users can manually input or pull down the pulley to search Class and SubClass. To view details of a given reference set of genome region, users click on "Set".
                        <div style="text-align:center;"><img src="${base.contextPath}/static/img/help_browse.png" class="" width="100%"></div>
                    </div>
                    <hr>

                </div>
                <hr>

                <button id="Search" style="width:500px" class="btn btn-success" type="button" data-toggle="collapse" data-target="#search" aria-expanded="false" aria-controls="search">
                    <font size="3">Search</font>
                </button>
                <div class="collapse" id="search" aria-expanded="false" style="height: 0px;">
                    <br>
                    <div style="border-style: double;padding: 20px;font-size: 120%;text-align:justify ">GREAP supports three different searching modes: "Search by data class", "Search by genome region" and "Search by gene name". Brief search results are presented as a table in the result page. As an example, users can click Search→ Search by data class → ChromHMM, TssA, GREAP will automatically display genome region sets essential information and Collection categories queried. Users can click the "set" to view details about the set. The other two search methods Search→ Search by genome region; Search→ Search by gene name are the same as the first.
                        <div style="text-align:center;"><img src="${base.contextPath}/static/img/help_search.png" class="" style="width: 100%;"></div>
                        <div style="text-align:center;"><img src="${base.contextPath}/static/img/help_search2.png" class="" style="width: 100%;"></div>
                        <hr>
                    </div>
                </div>
                <hr>
                <button id="Download" style="width:500px" class="btn btn-success collapsed" type="button" data-toggle="collapse" data-target="#download" aria-expanded="false" aria-controls="download">
                    <font size="3">Download</font>
                </button>
                <div class="collapse" id="download" aria-expanded="false" style="height: 0px;">
                    <br>
                    <div style="border-style: double;padding: 20px;font-size: 120%;text-align:justify ">
                        GREAP supports the download of all of reference sets. Users can click the download icon to download data from the download page.
                        <div style="text-align:center;"><img src="${base.contextPath}/static/img/help_download.png" width="100%"></div>
                    </div>
                </div>
                <hr>

            </div>

            <a id="Docker_image" style="position: relative;top: -100px;"></a>
            <div class="panel-heading"><h2><font style="color: white">Docker image</font></h2></div>
            <p style="text-align:justify;padding:10px 10px;">
                If you want to download the docker image of GREAP, please enter the following code in the console of Linux server to install it
                <span style="display: block;width: 100%;height: 50px;padding: 14px;margin-top: 16px;background-color: #f0eded;word-break: normal;white-space: pre;border: 1px solid rgba(0,0,0,.125);">docker pull greap</span>
            </p>

            <a name="GREAP Sets" style="position: relative;top: -70px;"></a>
            <div class="panel-heading"><h2><font style="color:white">GREAP Sets</font></h2></div>
            <div style="text-align:center;"></div>

            <p style="text-align:justify;padding:10px 10px;">
                <b><a name="ChromHMM" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C1: ChromHMM </b>
                ChromHMM is an automated computational system for learning chromatin states, and for characterizing their biological functions and correlations with large-scale functional datasets. From multiple chromatin marks, Roadmap uses ChromHMM v1.10, a multivariate Hidden Markov method, to calculate chromatin states across 127 epigenomes. We added the ChromHMM core 15 states of five chromatin marks (H3K4me3, H3K4me1, H3K36me3, H3K27me3, and H3K9me3) to GREAP. The core 15 states are TssA (Active TSS), TssAFlnk (Flanking Active TSS), TxFlnk (Transcr. at gene 5' and 3'), Tx (Strong transcription), TxWk (Weak transcription), EnhG (Genic enhancers), Enh (Enhancers), ZNF/Rpts (ZNF genes & repeats), Het (Heterochromatin), TssBiv (Bivalent/Poised TSS), BivFlnk (Flanking Bivalent TSS/Enh), EnhBiv (Bivalent Enhancer), ReprPC (Repressed PolyComb), ReprPCWk (Weak Repressed PolyComb) and Quies (Quiescent/Low).                <br>
                <br>
                <b><a name="TF" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C2: TF </b>
                As a transcriptional regulator, protein activities of TFs and TcoFs can affect expression of downstream target genes by indirectly occupying DNA regulatory elements, such as promoter regions, enhancer regions, and super enhancer regions. Given the development of high-throughput techniques, ChIP-seq has become an important strategy for identifying the target genes and functions of TFs and TcoFs. We downloaded the TF and TcoF ChIP-seq data from Cistrome and Remap. The TF binding regions information covered over 9,000 samples, including 57 tissue types and 3,328 TFs. The TcoF binding regions information covered more than 3,900 samples, including 41 tissue types and 1,166 TcoFs.                <br>
                <br>
                <b><a name="TcoF" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C3: TcoF </b>
                As a transcriptional regulator, protein activities of TFs and TcoFs can affect expression of downstream target genes by indirectly occupying DNA regulatory elements, such as promoter regions, enhancer regions, and super enhancer regions. Given the development of high-throughput techniques, ChIP-seq has become an important strategy for identifying the target genes and functions of TFs and TcoFs. We downloaded the TF and TcoF ChIP-seq data from Cistrome and Remap. The TF binding regions information covered over 9,000 samples, including 57 tissue types and 3,328 TFs. The TcoF binding regions information covered more than 3,900 samples, including 41 tissue types and 1,166 TcoFs.                <br>
                <br>
                <b><a name="Histone" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C4: Histone </b>
                Histone modification is one of the main means of epigenetic control in biological processes, which is usually located in the free amino terminal tails of four common histones (H2A, H2B, H3, and H4; especially H3 and H4). An increasing number of studies have found that many important biological regulatory elements were generally marked by enrichment of H3K4me1 and H3K27ac, such as poised and active enhancers. Considering that the enrichment of these histone modifications in the genome can be determined by ChIP-seq, we further downloaded histone modifications ChIP-seq data from ENCODE to provide researchers with a means of user-friendly analysis. This ChIP-seq data contains over 1,400 samples covering 33 histone modifications, such as H3K27ac, H3K27me3, and H3K4me1.                <br>
                <b><a name="ATAC" style="position: relative;top: -100px;"></a></b>
                <br>
                <b style="color: orange;">C5: ATAC-seq </b>
                Accessible chromatin is a highly informative structural feature for identifying regulatory elements, which provides information about transcriptional activities and gene regulatory mechanisms. To characterize accessible chromatin regions in different samples, we downloaded 1,493 ATAC-seq data covering numerous cell/tissue types from NCBI GEO/SRA. We used streamlined pipeline of Bowtie2, SAMtools, and MACS2 to identify accessible chromatin regions and developed ATACdb database. These accessible chromatin regions from different samples are also stored in GREAP as one of reference sets.                <br>
                <br>
                <b><a name="Enhancer" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C6: Enhancer </b>
                Enhancers and super enhancers (SEs) are classes of cis-regulatory elements that can increase gene transcription by forming loops in intergenic regions, introns and exons. SEs have a higher density of core TF and active chromatin markers such as H3K27ac than enhancers. To construct enhancer and SE categories, we integrated H3K27ac ChIP-seq data from NCBI GEO/SRA, ENCODE, Roadmap and GGR (Genomics of Gene Regulation Project), and identified enhancers and SEs involving 542 cells/tissues by the streamlined pipeline of Bowtie, MACS, and ROSE. Detailed SE annotation and regulatory information can be viewed in the SEdb database and SEanalysis web server developed by our team. These enhancer and SE sets serves as "SEdb" sub-category in the "Enhancer" and "Super enhancer" categories, respectively. In addition, we also downloaded more than 65,000 SE regions from dbSUPER, which included 99 cells/tissues. Importantly, many studies have confirmed some enhancers that play an important role in the development of diseases through low-throughput experiments. To obtain such enhancers, we manually reviewed 1,590 published studies and obtained 425 enhancers, stored in ENdb database, simultaneously and took as "ENdb" sub-category of "Enhancer".
                <br>
                <br>
                <b><a name="Super_Enhancer" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C7: Super_Enhancer </b>
                Enhancers and super enhancers (SEs) are classes of cis-regulatory elements that can increase gene transcription by forming loops in intergenic regions, introns and exons. SEs have a higher density of core TF and active chromatin markers such as H3K27ac than enhancers. To construct enhancer and SE categories, we integrated H3K27ac ChIP-seq data from NCBI GEO/SRA, ENCODE, Roadmap and GGR (Genomics of Gene Regulation Project), and identified enhancers and SEs involving 542 cells/tissues by the streamlined pipeline of Bowtie, MACS, and ROSE. Detailed SE annotation and regulatory information can be viewed in the SEdb database and SEanalysis web server developed by our team. These enhancer and SE sets serves as "SEdb" sub-category in the "Enhancer" and "Super enhancer" categories, respectively. In addition, we also downloaded more than 65,000 SE regions from dbSUPER, which included 99 cells/tissues. Importantly, many studies have confirmed some enhancers that play an important role in the development of diseases through low-throughput experiments. To obtain such enhancers, we manually reviewed 1,590 published studies and obtained 425 enhancers, stored in ENdb database, simultaneously and took as "ENdb" sub-category of "Enhancer".
                <br>
                <br>
                <b><a name="SNP" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C8&C9: SNP&eQTL </b>
                GWAS have provided a large amount of data of associating genetic variants with common phenotypes. We collected SNPs from the NHGRI GWAS Catalog. Human eQTL datasets were downloaded and merged from PancanQTL and GTEx. For each eQTL pair, we annotated eQTL with ‘rsID’. Finally, we obtained 1,515,001 SNPs associated with diseases, traits, and phenotypes. From the SNPs affecting the gene expressions, we expanded the SNP sites by 10 kb, 15 kb, and 20 kb upstream and downstream respectively.                <br>
                <br>
                <b><a name="Methylation" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C10: Methylation </b>
                A total of 198,468,712 methylation sites were collected across 60 samples, including the 450 K array from ENCODE. We divided these sites into hypermethylation and hypomethylation according to beta values. Sites with a beta value over 0.6 were assumed to involve hypermethylation, while sites were considered hypomethylation with a beta value greater than 0.2 and less than 0.6. We expanded the methylation sites by 10 kb, 15 kb, and 20 kb upstream and downstream respectively.                <br>
                <br>
                <b><a name="LncRNA" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C11: LncRNA </b>
                We collected data for multiple categories of lncRNA sets including Disease, Drug, Subcellular Localization, Cancer Hallmark, SmORF, Exosome and Cell Marker from LncSEA, which is a comprehensive human lncRNA sets resource and enrichment analysis platform developed by our team. For mRNA sets, we collected from CellMarker, GOterm and GTEx. Finally, we obtained regions between 2 kb, 5 kb, and 10 kb upstream and 1 kb downstream of the gene transcription start sites as promoter regions based on annotation file from GENCODE.                <br>
                <br>
                <b><a name="mRNA" style="position: relative;top: -100px;"></a></b>
                <b style="color: orange;">C12: mRNA </b>
                We collected data for multiple categories of lncRNA sets including Disease, Drug, Subcellular Localization, Cancer Hallmark, SmORF, Exosome and Cell Marker from LncSEA, which is a comprehensive human lncRNA sets resource and enrichment analysis platform developed by our team. For mRNA sets, we collected from CellMarker, GOterm and GTEx. Finally, we obtained regions between 2 kb, 5 kb, and 10 kb upstream and 1 kb downstream of the gene transcription start sites as promoter regions based on annotation file from GENCODE.                <br>
                <br>
            </p>
        </div>
    </div>

</div>
</div>
</div>
<#include "nav/footer.ftl" />
</body>
</html>