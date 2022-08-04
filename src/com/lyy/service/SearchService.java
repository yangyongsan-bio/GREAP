package com.lyy.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyy.model.UserEmail;


public interface SearchService {
	
	List<String> queryData_Type();
	List<String> queryTissue_Name(String Data_Type);
	
	List<String> queryBysearch_region(String setnew);
	List<String> queryBysearch_gene(String gene);
	
	List<String> querylisttable(String set);
	
	List<String> querydownload(String set);
	
	List<HashMap<String,String>> queryByDatatype(String datatype, String dataset, String table);
	List<HashMap<String,String>> queryByDataset(String datatype, String dataset, String table);
	List<HashMap<String,String>> queryByDatatype_1(String datatype, String dataset, String table);
	List<HashMap<String,String>> queryByDataset_1(String datatype, String dataset, String table);
	List<HashMap<String,String>> queryByDatatype_2(String datatype, String dataset, String table);
	List<HashMap<String,String>> queryByDataset_2(String datatype, String dataset, String table);
	List<HashMap<String,String>> queryByDatatype_3(String datatype, String dataset, String table);
	List<HashMap<String,String>> queryByDataset_3(String datatype, String dataset, String table);
	
	List<HashMap<HashMap<String,String>,String>> queryBybrowselist(String datatype, String dataset, String searchValue, String table);
	List<HashMap<HashMap<String,String>,String>> queryBybrowselist_1(String datatype, String dataset, String searchValue, String table);
	List<HashMap<HashMap<String,String>,String>> queryBybrowselist_2(String datatype, String dataset, String searchValue, String table);
	List<HashMap<HashMap<String,String>,String>> queryBybrowselist_3(String datatype, String dataset, String searchValue, String table);
	
	List<HashMap<String,String>> queryBydownlist1(String searchValue);
	List<HashMap<String,String>> queryBydownlist2(String searchValue);
	
	List<String> querySubclass(String datatype);
	
	List<HashMap<HashMap<String,String>,String>> querylistchr(String datatype, String subclass, String set);
	
	
	List<HashMap<String,String>> querylistfile(String subclass,String set);
	//##################################### search by lncnames ####################################################	
	List<String> queryRegulation();
	
	List<HashMap<String,HashMap<String,String>>> queryByLncName(String source, String regulation, String [] list);
	List<HashMap<String,HashMap<String,String>>> queryByLncName_G(String source, String regulation, String [] list);
	List<HashMap<String,HashMap<String,String>>> queryByLncName_N(String source, String regulation, String [] list);
	
	List<HashMap<String,HashMap<String,String>>> queryByLncName_chr(String source, String regulation, String lncnames);
	List<HashMap<String,HashMap<String,String>>> queryByLncName_start(String source, String regulation, String lncnames);
	List<HashMap<String,HashMap<String,String>>> queryByLncName_end(String source, String regulation, String lncnames);
	
	List<HashMap<String,HashMap<String,String>>> queryBypromoter_A(String source,String regulation, String [] list);
	List<HashMap<String,String>> queryByse_A(String source, String [] list);
	List<HashMap<String,String>> queryBychromatin_A(String source,String [] list);
	
	List<HashMap<String,HashMap<String,String>>> queryBypromoter_G(String source,String regulation, String [] list);
	List<HashMap<String,HashMap<String,String>>> queryBypromoter_N(String source,String regulation, String [] list);
	
	List<HashMap<String,String>> queryByse_G(String source, String [] list);
	List<HashMap<String,String>> queryByse_N(String source, String [] list);
	
	List<HashMap<String,String>> queryBychromatin_G(String source,String [] list);
	List<HashMap<String,String>> queryBychromatin_N(String source,String [] list);
	
	List<HashMap<String,HashMap<String,String>>> queryByresult_left(String source,String regulation, String [] list);
	List<HashMap<String,String>> queryByresult_left_A(String regulation, String [] list);
	List<HashMap<String,String>> queryByresult_right(String source,String [] list);
	List<HashMap<String,String>> queryByresult_right_A(String [] list);
	//##################################### search by lncnames ����####################################################	
	//##################################### search by tf ####################################################
	
	List<String> querytfname();
	List<String> querytype(String tfname);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_promoter_G(String pro_tablename, String other_tablename, String regulation, String tfnames, String type );
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_se_G(String pro_tablename, String other_tablename, String regulation, String tfnames, String type );
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_chromatin_G(String pro_tablename, String other_tablename, String regulation, String tfnames, String type );
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_promoter_N(String pro_tablename, String other_tablename, String regulation, String tfnames, String type );
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_se_N(String pro_tablename, String other_tablename, String regulation, String tfnames, String type );
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_chromatin_N(String pro_tablename, String other_tablename, String regulation, String tfnames, String type );
	
	List<HashMap<String,String>> queryByTF_promoter_A(String regulation, String tfnames);
	List<HashMap<String,String>> queryByTF_se_A(String regulation, String tfnames);
	List<HashMap<String,String>> queryByTF_chromatin_A(String regulation, String tfnames);
	
	List<HashMap<String,String>> queryByTF_left(String tfnames);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_right_node(String pro_tablename,String regulation, String tfnames,String type,String other_tablename);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByTF_right_node_A(String regulation, String tfnames,String type,String chr);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_right_edge(String pro_tablename,String regulation, String tfnames,String type,String other_tablename);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByTF_right_edge_A(String regulation, String tfnames,String type,String chr);
	//##################################### search by tf ����####################################################
	//##################################### search by SNPID ####################################################
	List<HashMap<String,String>> queryBySNP_promoter_G(String regulation,String snpid);
	List<HashMap<String,String>> queryBySNP_se_G(String regulation,String snpid);
	List<HashMap<String,String>> queryBySNP_chromatin_G(String regulation,String snpid);
	
	List<HashMap<String,String>> queryBySNP_promoter_N(String regulation,String snpid);
	List<HashMap<String,String>> queryBySNP_se_N(String regulation,String snpid);
	List<HashMap<String,String>> queryBySNP_chromatin_N(String regulation,String snpid);
	
	List<HashMap<String,String>> queryBySNP_promoter_A(String regulation,String snpid);
	List<HashMap<String,String>> queryBySNP_se_A(String regulation,String snpid);
	List<HashMap<String,String>> queryBySNP_chromatin_A(String regulation,String snpid);
	
	List<HashMap<String,String>> queryBySNP_left(String snpid);
	List<HashMap<String,String>> queryBySNP_right_edge_A(String regulation,String snpid);
	List<HashMap<String,HashMap<String,String>>> queryBySNP_right_edge(String source,String regulation,String snpid);
	List<HashMap<String,String>> queryBySNP_right_node_A(String regulation,String snpid);
	List<HashMap<String,HashMap<String,String>>> queryBySNP_right_node(String source,String regulation,String snpid);
	
	//##################################### search by SNPID ����####################################################
	//##################################### search by enhancer ####################################################
	List<String> queryEnhancerBiosample_Type();
	List<String> queryEnhancerBiosample_Name(String Biosample_Type);
	
	List<HashMap<String,String>> queryByenhancer_promoter_G(String lnc,String regulation);
	List<HashMap<String,String>> queryByenhancer_se_G(String lnc);
	List<HashMap<String,String>> queryByenhancer_chromatin_G(String lnc);
	
	List<HashMap<String,String>> queryByenhancer_promoter_N(String lnc,String regulation);
	List<HashMap<String,String>> queryByenhancer_se_N(String lnc);
	List<HashMap<String,String>> queryByenhancer_chromatin_N(String lnc);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_promoter_A(String chr,String start,String end,String regulation);
	List<HashMap<String,HashMap<String,String>>> queryByenhancer_se_A(String chr,String start,String end);
	List<HashMap<String,HashMap<String,String>>> queryByenhancer_chromatin_A(String chr,String start,String end);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_left_moren(String source,String chr,String start,String end);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_right_node_moren(String source,String chr,String start,String end);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_right_edge_moren(String source,String chr,String start,String end);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_left(String chr,String start,String end,String se_tablename);
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_right_node(String chr,String start,String end,String se_tablename);
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_right_edge(String chr,String start,String end,String se_tablename);
	
	//##################################### search by enhancer ����####################################################
	//##################################### search by chromatin ####################################################
	List<String> queryChromatinBiosample_Type();
	List<String> queryChromatinBiosample_Name(String bio_Type);
	
	List<HashMap<String,HashMap<String,String>>> queryBych_promoter(String lnc,String source,String regulation);
	List<HashMap<String,String>> queryBych_se(String lnc,String source);
	List<HashMap<String,String>> queryBych_chromatin(String lnc,String source);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_left(String chr,String start,String end,String source,String D_tablename,String A_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_right_node(String chr,String start,String end,String source,String D_tablename,String A_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_right_edge(String chr,String start,String end,String source,String D_tablename,String A_tablename);
	
	//##################################### search by chromatin ����####################################################
	List<HashMap<String,String>> queryByPro_number_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryByPro_number_N(String regulation,String lncnames);
	
	List<HashMap<String,String>> queryBySE_number_G(String lncnames);
	List<HashMap<String,String>> queryBySE_number_N(String lncnames);
	
	List<HashMap<String,String>> queryByCh_number_G(String lncnames);
	List<HashMap<String,String>> queryByCh_number_N(String lncnames);
	
	List<String> queryBySearchDiseasedis();
	
	List<HashMap<String,String>> queryBySearchDisease_G(String disease);
	List<HashMap<String,String>> queryBySearchDisease_N(String disease);
	
	List<HashMap<String,String>> queryBydisease_G(String lncnames);
	List<HashMap<String,String>> queryBydisease_N(String lncnames);
	
	List<HashMap<String,String>> queryBymirna_G(String lncnames);
	List<HashMap<String,String>> queryBymirna_N(String lncnames);
	
	List<HashMap<String,String>> queryBylocalization(String lncnames,String source);
	
	List<HashMap<String,String>> queryByrbp(String lncnames,String source);
	
	
	List<HashMap<String,HashMap<String,String>>> queryByDNaseI_G(String source, String regulation, String lncnames);
	List<HashMap<String,HashMap<String,String>>> queryByDNaseI_N(String source, String regulation, String lncnames);
	
	List<String> queryBiosample_Type();
	List<String> querytiss(String Biosample_Type);
	List<String> queryBiosample_Name(String Biosample_Type,String tiss);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDNaseI_GG(String regulation,String lncnames,String Biosample_Type,String [] list);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDNaseI_NN(String regulation,String lncnames,String Biosample_Type,String [] list);
	
	List<String> queryBiosample_Type_TF();
	List<HashMap<String,String>> queryBiosample_Name_TF(String Biosample_Type_TF);
	List<String> querytfid(String Biosample_Name);
	List<String> querytfidmap(String Biosample_Name);
	List<HashMap<String,HashMap<String,String>>> queryByTF_GG(String regulation,String lncnames,String tf_tablename);
	List<HashMap<String,HashMap<String,String>>> queryByTF_NN(String regulation,String lncnames,String tf_tablename);
	
	List<String> queryBiosample_Type_450();
	List<HashMap<String, String>> queryBiosample_Name_450(String Biosample_Type_450);
	
	List<HashMap<String, String>> queryBytablename_450k(String Biosample_Type,String Biosample_Name);
	List<HashMap<String, String>> queryBytablename_WGBS(String Biosample_Type,String Biosample_Name);
	
	List<HashMap<HashMap<String,String>,String>> queryByMethylation_450k_G(String regulation,String lncnames,String tablename_450k);
	List<HashMap<HashMap<String,String>,String>> queryByMethylation_450k_N(String regulation,String lncnames,String tablename_450k);
	
	List<String> queryBiosample_Type_wgbs();
	List<HashMap<String, String>> queryBiosample_Name_wgbs(String Biosample_Type_wgbs);
	List<HashMap<HashMap<String,String>,String>> queryByMethylation_WGBS_G(String regulation,String lncnames,String tablename_WGBS);
	List<HashMap<HashMap<String,String>,String>> queryByMethylation_WGBS_N(String regulation,String lncnames,String tablename_WGBS);
	
	List<String> queryBiosample_Name_3D();
	List<HashMap<HashMap<String,String>,String>> queryBy3D_G(String regulation,String lncnames,String [] list);
	List<HashMap<HashMap<String,String>,String>> queryBy3D_N(String regulation,String lncnames,String [] list);

	List<String> queryBiosample_Type_EpiTensor();
	List<HashMap<String, String>> queryBiosample_Name_EpiTensor(String Biosample_Type_EpiTensor);
	
	List<HashMap<String, String>> queryBytablename_inter(String tiss);
	
	List<HashMap<HashMap<String,String>,String>> queryByEpiTensor_G(String regulation,String lncnames,String inter_tablename);
	List<HashMap<HashMap<String,String>,String>> queryByEpiTensor_N(String regulation,String lncnames,String inter_tablename);

	List<HashMap<HashMap<String,String>,String>> queryByregionA_G(String regulation,String lncnames,String inter_tablename);
	List<HashMap<HashMap<String,String>,String>> queryByregionA_N(String regulation,String lncnames,String inter_tablename);

	List<HashMap<HashMap<String,String>,String>> queryByregionB_G(String regulation,String lncnames,String inter_tablename);
	List<HashMap<HashMap<String,String>,String>> queryByregionB_N(String regulation,String lncnames,String inter_tablename);

	List<HashMap<String,String>> queryByMotif_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryByMotif_N(String regulation,String lncnames);
	
	List<HashMap<String,String>> queryBySNP_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryBySNP_N(String regulation,String lncnames);

	List<HashMap<String,String>> queryByRiskSNP_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryByRiskSNP_N(String regulation,String lncnames);

	List<HashMap<String,String>> queryByeqtl_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryByeqtl_N(String regulation,String lncnames);
	
	List<HashMap<String,String>> queryByafr_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryByafr_N(String regulation,String lncnames);
	
	List<HashMap<String,String>> queryByeas_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryByeas_N(String regulation,String lncnames);
	
	List<HashMap<String,String>> queryByeur_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryByeur_N(String regulation,String lncnames);
	
	List<HashMap<String,String>> queryBysas_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryBysas_N(String regulation,String lncnames);
	

	List<HashMap<String,String>> queryByamr_G(String regulation,String lncnames);
	List<HashMap<String,String>> queryByamr_N(String regulation,String lncnames);
	
	List<HashMap<String,String>> queryByAFR_G(String ID);
	List<HashMap<String,String>> queryByAFR_N(String ID);
	
	List<HashMap<String,String>> queryByAMR_G(String ID);
	List<HashMap<String,String>> queryByAMR_N(String ID);
	
	List<HashMap<String,String>> queryByEAS_G(String ID);
	List<HashMap<String,String>> queryByEAS_N(String ID);
	
	List<HashMap<String,String>> queryByEQTL_G(String ID);
	List<HashMap<String,String>> queryByEQTL_N(String ID);
	
	List<HashMap<String,String>> queryByRISKSNP_G(String ID);
	List<HashMap<String,String>> queryByRISKSNP_N(String ID);
	
	List<HashMap<String,String>> queryByMOTIFCHANGE_G(String ID);
	List<HashMap<String,String>> queryByMOTIFCHANGE_N(String ID);
	
	List<HashMap<String,String>> queryByEUR_G(String ID);
	List<HashMap<String,String>> queryByEUR_N(String ID);
	
	List<HashMap<String,String>> queryBySAS_G(String ID);
	List<HashMap<String,String>> queryBySAS_N(String ID);
	
	List<String> queryBiosample_Type_Histone();
	List<HashMap<String, String>> queryBiosample_Name_Histone(String Biosample_Type_Histone);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByHistone_G(String regulation,String lncnames,String Biosample_Type,String Biosample_Name,String histone);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByHistone_N(String regulation,String lncnames,String Biosample_Type,String Biosample_Name,String histone);
	
	List<HashMap<String,String>> queryBySE_moren(String source,String lncnames);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_tablename(String source,String Biosample_Type,String Biosample_Name);
	
	List<HashMap<String,String>> queryBySE(String lncnames,String se_tablename);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_TF_G(String chr,String start,String end,String tf_tablename);

	List<HashMap<String,HashMap<String,String>>> queryBySE_SNP_G(String chr,String start,String end);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_RiskSNP_G(String chr,String start,String end);

	List<HashMap<String,HashMap<String,String>>> queryBySE_EqtL_G(String chr,String start,String end);

	List<HashMap<String,HashMap<String,String>>> queryBySE_SAS_G(String chr,String start,String end);

	List<HashMap<String,HashMap<String,String>>> queryBySE_EUR_G(String chr,String start,String end);

	List<HashMap<String,HashMap<String,String>>> queryBySE_EAS_G(String chr,String start,String end);

	List<HashMap<String,HashMap<String,String>>> queryBySE_AMR_G(String chr,String start,String end);

	List<HashMap<String,HashMap<String,String>>> queryBySE_AFR_G(String chr,String start,String end);
 
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_450K_G(String chr,String start,String end,String tablename_450k);

	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_WGBS_G(String chr,String start,String end,String tablename_WGBS);

	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBySE_Intersection_G(String chr,String start,String end,String inter_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByregionA(String chr,String start,String end,String inter_tablename);
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByregionB(String chr,String start,String end,String inter_tablename);

	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBySE_Histone_G(String chr,String start,String end,String Biosample_Type,String Biosample_Name,String histone);
	
	List<HashMap<HashMap<String,String>,String>> queryBySE_motif(String chr,String start,String end);

	List<HashMap<String,String>> queryByChromatin_tablename(String Biosample_Type,String Biosample_Name);
	List<HashMap<String,String>> queryByChromatin_tablenameATAC(String Biosample_Type,String Biosample_Name);
	List<HashMap<String,String>> queryByChromatin_tf_tablename(String Biosample_Type,String Biosample_Name);
	
	List<HashMap<String,String>> queryByTF_name_pro_tablename(String source,String chr);
	List<HashMap<String,String>> queryByTF_name_other_tablename(String source,String chr);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_G(String source,String lncnames,String tablename,String tablenameATAC);
	
	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_SNP_G(String chr,String start,String end);

	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_RiskSNP_G(String chr,String start,String end);

	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_Eqtl_G(String chr,String start,String end);
	
	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_SAS_G(String chr,String start,String end);
	
	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_EUR_G(String chr,String start,String end);
	
	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_EAS_G(String chr,String start,String end);
	
	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_AMR_G(String chr,String start,String end);
	
	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_AFR_G(String chr,String start,String end);

	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_TF_G(String chr,String start,String end,String tf_tablename);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_450K_G(String chr,String start,String end,String tablename_450k);

	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_WGBS_G(String chr,String start,String end,String tablename_WGBS);

	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByChromatinD_Intersection_G(String chr,String start,String end,String inter_tablename);

	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBych_regionA(String chr,String start,String end,String inter_tablename);
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBych_regionB(String chr,String start,String end,String inter_tablename);

	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryByChromatinD_Histone_G(String chr,String start,String end,String Biosample_Type,String Biosample_Name,String histone);

	List<HashMap<String,String>> queryByExpression_NONCODE_G(String lncnames);
	List<HashMap<String,String>> queryByExpression_N(String lncnames);
	List<HashMap<String,String>> queryByExpression_TCGA(String lncnames);
	List<HashMap<String,String>> queryByExpression_GTEX(String lncnames);
	List<HashMap<String,String>> queryByExpression_CCLE(String lncnames);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_1(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_2(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_3(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_4(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_5(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_6(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_7(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_8(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_9(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_10(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_11(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_12(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_13(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_14(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_15(String source,String Gene_type,String region,String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_1(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_2(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_3(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_4(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_5(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_6(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_7(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_8(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_9(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_10(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_11(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_12(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_13(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_14(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_15(String source,String Gene_type,String region,String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_1(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_2(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_3(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_4(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_5(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_6(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_7(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_8(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_9(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_10(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_11(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_12(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_13(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_14(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_15(String source,String Gene_type,String region,String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_1(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_2(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_3(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_4(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_5(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_6(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_7(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_8(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_9(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_10(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_11(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_12(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_13(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_14(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_15(String source,String Gene_type,String region,String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_1(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_2(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_3(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_4(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_5(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_6(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_7(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_8(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_9(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_10(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_11(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_12(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_13(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_14(String source,String Gene_type,String region,String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_15(String source,String Gene_type,String region,String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name);

	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name);
	
	//##################################### R ####################################################	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze(String pro_tablename,String other_tablename,String promoterRegion,String lncRNA,String tftype);
	
	List<HashMap<String,String>> queryByRAnalyze_chr(String lncRNA);
	
	List<HashMap<String,HashMap<String,String>>> queryByRresult_left(String source,String promoterRegion,String lncRNA);
	List<HashMap<String,String>> queryByRresult_left_A(String promoterRegion,String lncRNA);
	
	List<String> querytftype();
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze_node(String pro_tablename,String other_tablename,String promoterRegion,String lncRNA,String tftype);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze_edge(String pro_tablename,String other_tablename,String promoterRegion,String lncRNA,String tftype);

	void saveUserEmail(UserEmail userEmail);
	
	String getSnpDisease(String set);

}
