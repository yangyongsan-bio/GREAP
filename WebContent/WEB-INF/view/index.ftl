
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>GREAP</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link rel="icon" type="image/x-icon" href="${base.contextPath}/static/img/favicon.ico"/>

    <!-- Bootstrap CSS File -->
    <link href="${base.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Main Stylesheet File -->
    <link rel="stylesheet" href="${base.contextPath}/static/css/header.css">
    <link rel="stylesheet" href="${base.contextPath}/static/css/footer.css"/>
    <link href="${base.contextPath}/static/css/home.css" rel="stylesheet">
    <link href="${base.contextPath}/static/css/style.css" rel="stylesheet">

    <script src="${base.contextPath}/static/js/jquery.min.js"></script>
    <script src="${base.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${base.contextPath}/static/js/echarts.js"></script>
</head>

<body id="body">
<#include "nav/navbar.ftl" />
<main id="main" style="margin-top: 100px">

    <h4 class="container"><font style="font-weight:800;font-size: 35px">Welcome to GREAP </font>a comprehensive human <span style="font-style: italic;font-weight: bolder;">g</span>enomic <span style="font-style: italic;font-weight: bolder;">r</span>egion sets <span style="font-style: italic;font-weight: bolder;">e</span>nrichment <span style="font-style: italic;font-weight: bolder;">a</span>nalysis <span style="font-style: italic;font-weight: bolder;">p</span>latform.</h4>
    <!--==========================
      About Section

    ============================-->
    <section id="about" class="wow fadeInUp" style="padding-top: 0px;">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 content"   style="padding-top: 13px;">
                    <ul><font style="font-size: 20px;color: #14307B">  <b>Introduction   </b></font> <br>
                        <li style="font-size: 17px;text-align: justify;">
                            <i class="fa fa-cog fa-spin"></i>
                            <img src="${base.contextPath}/static/img/index.png" style="width: 4%;margin-right: 5px"><span style="font-style: italic;font-weight: bolder;">GREAP</span> aims to document a large number of available resources of human genomic region sets and to provide annotation and enrichment analysis of region lists submitted by users.<span style="font-style: italic;font-weight: bolder;">GREAP</span> supports <span style="color: #0c2db3;">85,370 genomic region sets</span> involved in <span style="color: #0c2db3;">11 categories</span> (ChromHMM state, TF, TcoF, Histone modification, Accessible Chromatin, Enhancer, Super Enhancer, SNP, Methylation, LncRNA, mRNA) and <span style="color: #0c2db3;">454 sub-categories</span>, which covered over <span style="color: #0c2db3;">634,681,107 regions</span>. Moreover, we provide <span style="color: #0c2db3;">two region enrichment analysis methods</span>: (i) Hypergeometric test, a common enrichment analysis method which is frequently cited, (ii) Locus Overlap Analysis (LOLA) uses Fisher's exact test with false discovery rate correction to assess the significance of overlap in each pairwise comparison.
                            </br>
                            <i class="fa fa-cog fa-spin"></i>
                            <img src="${base.contextPath}/static/img/index.png" style="width: 4%;margin-right: 5px">Importantly, <span style="font-style: italic;font-weight: bolder;">GREAP</span> provides <span style="color: #0c2db3;">annotation and enrichment analysis</span> functions of genomic region sets. In addition, <span style="font-style: italic;font-weight: bolder;">GREAP</span> provides a user-friendly interface to <span style="color: #0c2db3;">search, browse and visualize detailed information</span> about these genomic region sets. In summary, <span style="font-style: italic;font-weight: bolder;">GREAP</span> is a powerful platform that provides a variety of types of <span style="color: #0c2db3;">genomic region sets</span> for users and supports genomic region annotation and enrichment analysis functions. 
                            </li>
                    </ul>
                </div>

                <div class="col-lg-6 about-img" style="padding-top: 42px;">
                    <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="2000" style="margin-top: 10px;">
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                            <li data-target="#myCarousel" data-slide-to="3"></li>
                            <li data-target="#myCarousel" data-slide-to="4"></li>
                        </ol>
                        <!-- 杞挱锛圕arousel锛夐」鐩� -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="${base.contextPath}/static/img/lunbo1.png" alt="First slide" style="height: 400px;margin-left: 90px;">
                            </div>
                            <div class="item">
                                <img src="${base.contextPath}/static/img/lunbo2.png" alt="Second slide" style="width: 547.5px;height: 400px;">
                            </div>
                            <div class="item">
                                <img src="${base.contextPath}/static/img/lunbo3.png" alt="Third slide"  style="width: 547.5px;height: 400px;">
                            </div>
                            <div class="item">
                                <img src="${base.contextPath}/static/img/lunbo4.png" alt="Four slide"  style="width: 547.5px;height: 400px;">
                            </div>
                            <div class="item">
                                <img src="${base.contextPath}/static/img/lunbo5.png" alt="Five slide"  style="width: 547.5px;height: 400px;">
                            </div>
                           
                        </div>
                        <!-- 杞挱锛圕arousel锛夊鑸� -->
                        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>

            </div>

        </div>
    </section><!-- #about -->

    <!--==========================
      Services Section
    ============================-->
    <section id="services" style="padding-top: 0px">
        <div class="container">
           <div class="panel panel-default" style="border: none">
	            <!--
	            <div class="panel-heading" >
	                <font style="font-size: 25px;"><span class="glyphicon glyphicon-list"></span> Main functions and operational guidelines</font>
	            </div>
	            -->
	            <hr style="height: 1px;background-color: #034786;border-color: #034786;">
                <div class="col-md-12" style="text-align: center;">
                    <img src="${base.contextPath}/static/img/Home.png">
                </div>
            </div>
        </div>
    </section>

    <!--==========================
      Clients Section
    ============================-->
    <section id="clients" class="wow fadeInUp">
        <div class="container">
            <div class="row" >

                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <font style="font-size: 25px;"><span class="glyphicon glyphicon-list"></span> Data statistical table</font>
                        </div>

                        <div style="overflow-y: auto;height: 330px;">
                            <table class="table table-hover" style="text-align: center;">
                                <tr>
                                    <td >Data class</td>
                                    <td>Set</td>
                                    <td>Number of region</td>
                                </tr>
                                <tr>
                                    <td>ChromHMM</td>
                                    <td>1,900</td>
                                    <td style="color: red;">55,502,957</td>
                                </tr>
                                <tr>
                                    <td>TF</td>
                                    <td>5,056</td>
                                    <td style="color: red;">54,910,288</td>
                                </tr>
                                <tr>
                                    <td>TcoF</td>
                                    <td>1,946</td>
                                    <td style="color: red;">20,200,723</td>
                                </tr>
                                <tr>
                                    <td>Histone</td>
                                    <td>1,449</td>
                                    <td style="color: red;">175,953,536</td>
                                </tr>
                                <tr>
                                    <td>ATAC</td>
                                    <td>2,986</td>
                                    <td style="color: red;">51,964,468</td>
                                </tr>
                                <tr>
                                    <td>Enhancer</td>
                                    <td>543</td>
                                    <td style="color: red;">6,621,919</td>
                                </tr>
                                <tr>
                                    <td>Super Enhancer</td>
                                    <td>641</td>
                                    <td style="color: red;">396,631</td>
                                </tr>
                                <tr>
                                    <td>SNP</td>
                                    <td>11,652</td>
                                    <td style="color: red;">34,986,456</td>
                                </tr>
                                <tr>
                                    <td>Methylation</td>
                                    <td>360</td>
                                    <td style="color: red;">52,300,833</td>
                                </tr>
                                <tr>
                                    <td>LncRNA</td>
                                    <td>1,956</td>
                                    <td style="color: red;">73,119</td>
                                </tr>
                                <tr>
                                    <td>mRNA</td>
                                    <td>5,6881</td>
                                    <td style="color: red;">1,420,785</td>
                                </tr>
                            </table>
                        </div>
                    </div>



                </div>

                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">

                            <font style="font-size: 25px;"><span class="glyphicon glyphicon-envelope"></span> Contact us</font>

                        </div>
                        <div class="contact" style="margin-left: 5%;height:320px">
                            <p style="margin-top: 10px;">Principal Investigator:Chunquan Li, Ph.D.</p>
                            <p>Phone: 86-459-8153035</p>
                            <p>Fax: 86-459-8153035</p>
                            <p>Email: lcqbio@163.com</p>
                            <p>School of Medical Informatics,</p>
                            <p>Daqing Campus Harbin Medical University</p>
                            <p>39 Xinyang Road, Daqing 163319, China</p>
                        </div>
                    </div>


                </div>


                <div class="col-lg-4">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <font style="font-size: 25px;"><span class="glyphicon glyphicon-user"></span> Visitors</font>
                        </div>

                    </div>
                    <script type="text/javascript" src="//rf.revolvermaps.com/0/0/6.js?i=53u0mhrck6a&amp;m=7&amp;c=e63100&amp;cr1=ffffff&amp;f=arial&amp;l=0&amp;bv=90&amp;lx=-420&amp;ly=420&amp;hi=20&amp;he=7&amp;hc=a8ddff&amp;rs=80" async="async"></script>
            </div>




        </div>
        <div class="row" >

            <div class="col-lg-12" style="margin-top: 5px;">
                <div class="panel panel-default">
                    <div class="panel-heading">

                        <font style="font-size: 25px;"><span class="glyphicon glyphicon-list"></span> Sister  Projects</font>
                    </div>
                    <div class="col-lg-6" style="margin-top: 5px;">
                        <p>
                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;<a
                                href="http://www.licpathway.net/sedb">SEdb</a><br>
                            SEdb: the comprehensive human Super-Enhancer database
                        </p>
                        <p>
                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;<a
                                href="http://www.licpathway.net/ENdb/index.php">ENdb</a><br>
                            ENdb: an experimentally supported enhancer database for human and mouse
                        </p>

                        <p>
                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;<a
                                href="http://www.licpathway.net/SEanalysis/?tdsourcetag=s_pctim_aiomsg">SEanalysis</a><br>
                            SEanalysis: a web tool for super-enhancer associated regulatory analysis
                        </p>
                        <p>
                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;<a
                                href="http://www.licpathway.net/ATACdb/">ATACdb</a><br>
                            ATACdb: a comprehensive human chromatin accessibility database
                        </p>

                    </div>
                    <div class="col-lg-6" style="margin-top: 5px;">
                        <p>
                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;<a
                                href="http://www.licpathway.net/KnockTF/">KnockTF</a><br>
                            KnockTF: a comprehensive human gene expression profile database with knockdown/knockout of transcription
                            factors
                        </p>
                        <p>
                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;<a
                                href="http://www.licpathway.net/TRCirc/view/index">TRCirc</a><br>
                            TRCirc: a resource for transcriptional regulation information of circRNAs
                        </p>
                        <p>
                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;<a
                                href="http://bio.licpathway.net/TRlnc/view/index">TRlnc</a><br>
                            TRlnc: a comprehensive database of human transcriptional regulation of lncRNAs
                        </p>
                        <p>
                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;<a
                                href="http://www.licpathway.net/VARAdb/">VARAdb</a><br>
                            VARAdb: a variation annotation database for human

                        </p>
                    </div>
                </div>
            </div>
            
    </section><!-- #clients -->
    <div class="tlinks">Collect from <a href="http://www.cssmoban.com/" >    </a></div>



</main>

<#include "nav/footer.ftl" />
<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>


<SCRIPT language = "javascript">
    $('.carousel').carousel({
        interval: 2000
    })
</SCRIPT>



</body>
</html>
