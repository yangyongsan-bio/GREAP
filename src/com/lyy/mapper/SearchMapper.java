package com.lyy.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lyy.model.UserEmail;

@Repository
public interface SearchMapper {
	
	List<String> queryData_Type();
	List<String> queryTissue_Name(String Data_Type);
	
	List<String> queryBysearch_region(@Param("setnew")  String setnew);
	List<String> queryBysearch_gene(@Param("gene")  String gene);
	
	List<String> querylisttable(@Param("set")  String set);
	
	List<String> querydownload(@Param("set")  String set);
	
	List<HashMap<String,String>> queryByDatatype(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("table")  String table);
	List<HashMap<String,String>> queryByDataset(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("table")  String table);
	List<HashMap<String,String>> queryByDatatype_1(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("table")  String table);
	List<HashMap<String,String>> queryByDataset_1(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("table")  String table);
	List<HashMap<String,String>> queryByDatatype_2(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("table")  String table);
	List<HashMap<String,String>> queryByDataset_2(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("table")  String table);
	List<HashMap<String,String>> queryByDatatype_3(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("table")  String table);
	List<HashMap<String,String>> queryByDataset_3(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("table")  String table);
	
	List<HashMap<HashMap<String,String>,String>> queryBybrowselist(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("searchValue")  String searchValue,@Param("table")  String table);
	List<HashMap<HashMap<String,String>,String>> queryBybrowselist_1(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("searchValue")  String searchValue,@Param("table")  String table);
	List<HashMap<HashMap<String,String>,String>> queryBybrowselist_2(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("searchValue")  String searchValue,@Param("table")  String table);
	List<HashMap<HashMap<String,String>,String>> queryBybrowselist_3(@Param("datatype")  String datatype,@Param("dataset")  String dataset,@Param("searchValue")  String searchValue,@Param("table")  String table);
	
	List<HashMap<String,String>> queryBydownlist1(@Param("searchValue")  String searchValue);
	List<HashMap<String,String>> queryBydownlist2(@Param("searchValue")  String searchValue);
	
	List<String> querySubclass(@Param("datatype") String datatype);
	
	List<HashMap<HashMap<String,String>,String>> querylistchr(@Param("datatype") String datatype, @Param("subclass") String subclass,@Param("set") String set);

	List<HashMap<String,String>> querylistfile(@Param("subclass") String subclass,@Param("set")  String set);
	//##################################### search by lncnames ####################################################	
	List<String> queryRegulation();
	
	List<HashMap<String,HashMap<String,String>>> queryByLncName(@Param("source") String source,@Param("regulation") String regulation,@Param("list")  String [] list);
	List<HashMap<String,HashMap<String,String>>> queryByLncName_G(@Param("source") String source,@Param("regulation") String regulation,@Param("list")  String [] list);
	List<HashMap<String,HashMap<String,String>>> queryByLncName_N(@Param("source") String source,@Param("regulation") String regulation,@Param("list")  String [] list);
	
	List<HashMap<String,HashMap<String,String>>> queryByLncName_chr(@Param("source") String source,@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,HashMap<String,String>>> queryByLncName_start(@Param("source") String source,@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,HashMap<String,String>>> queryByLncName_end(@Param("source") String source,@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	
	
	List<HashMap<String,HashMap<String,String>>> queryBypromoter_A(@Param("source") String source,@Param("regulation") String regulation,@Param("list")  String [] list);
	List<HashMap<String,String>> queryByse_A(@Param("source") String source,@Param("list")  String [] list);
	List<HashMap<String,String>> queryBychromatin_A(@Param("source") String source,@Param("list")  String [] list);
	
	List<HashMap<String,HashMap<String,String>>> queryBypromoter_G(@Param("source") String source,@Param("regulation") String regulation,@Param("list")  String [] list);
	List<HashMap<String,HashMap<String,String>>> queryBypromoter_N(@Param("source") String source,@Param("regulation") String regulation,@Param("list")  String [] list);
	
	List<HashMap<String,String>> queryByse_G(@Param("source") String source,@Param("list")  String [] list);
	List<HashMap<String,String>> queryByse_N(@Param("source") String source,@Param("list")  String [] list);
	
	List<HashMap<String,String>> queryBychromatin_G(@Param("source") String source,@Param("list")  String [] list);
	List<HashMap<String,String>> queryBychromatin_N(@Param("source") String source,@Param("list")  String [] list);
	
	List<HashMap<String,HashMap<String,String>>> queryByresult_left(@Param("source") String source,@Param("regulation") String regulation,@Param("list")  String [] list);
	List<HashMap<String,String>> queryByresult_left_A(@Param("regulation") String regulation,@Param("list")  String [] list);
	
	List<HashMap<String,String>> queryByresult_right(@Param("source") String source,@Param("list")  String [] list);
	List<HashMap<String,String>> queryByresult_right_A(@Param("list")  String [] list);
	//##################################### search by lncnames ���� ####################################################	
	//##################################### search by tf ####################################################	
	
	List<String> querytfname();
	List<String> querytype(@Param("tfname") String tfname);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_promoter_G(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_se_G(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_chromatin_G(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_promoter_N(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_se_N(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_chromatin_N(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type);
	
	List<HashMap<String,String>> queryByTF_promoter_A(@Param("regulation") String regulation,@Param("tfnames")  String tfnames);
	List<HashMap<String,String>> queryByTF_se_A(@Param("regulation") String regulation,@Param("tfnames")  String tfnames);
	List<HashMap<String,String>> queryByTF_chromatin_A(@Param("regulation") String regulation,@Param("tfnames")  String tfnames);
	
	List<HashMap<String,String>> queryByTF_left(@Param("tfnames")  String tfnames);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_right_node(@Param("pro_tablename") String pro_tablename,@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type,@Param("other_tablename")  String other_tablename);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByTF_right_node_A(@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type,@Param("chr")  String chr);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_right_edge(@Param("pro_tablename") String pro_tablename,@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type,@Param("other_tablename")  String other_tablename);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByTF_right_edge_A(@Param("regulation") String regulation,@Param("tfnames")  String tfnames,@Param("type")  String type,@Param("chr")  String chr);
	//##################################### search by tf ����####################################################
	//##################################### search by SNPID ####################################################	
	
	List<HashMap<String,String>> queryBySNP_promoter_G(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,String>> queryBySNP_se_G(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,String>> queryBySNP_chromatin_G(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	
	List<HashMap<String,String>> queryBySNP_promoter_N(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,String>> queryBySNP_se_N(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,String>> queryBySNP_chromatin_N(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	
	List<HashMap<String,String>> queryBySNP_promoter_A(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,String>> queryBySNP_se_A(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,String>> queryBySNP_chromatin_A(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	
	List<HashMap<String,String>> queryBySNP_left(@Param("snpid")  String snpid);
	List<HashMap<String,String>> queryBySNP_right_edge_A(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,HashMap<String,String>>> queryBySNP_right_edge(@Param("source") String source,@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,String>> queryBySNP_right_node_A(@Param("regulation") String regulation,@Param("snpid")  String snpid);
	List<HashMap<String,HashMap<String,String>>> queryBySNP_right_node(@Param("source") String source,@Param("regulation") String regulation,@Param("snpid")  String snpid);
	
	//##################################### search by SNPID ���####################################################
	//##################################### search by enhancer ####################################################
	List<String> queryEnhancerBiosample_Type();
	List<String> queryEnhancerBiosample_Name(String Biosample_Type);
	
	List<HashMap<String,String>> queryByenhancer_promoter_G(@Param("lnc") String lnc,@Param("regulation")  String regulation);
	List<HashMap<String,String>> queryByenhancer_se_G(@Param("lnc") String lnc);
	List<HashMap<String,String>> queryByenhancer_chromatin_G(@Param("lnc") String lnc);
	
	List<HashMap<String,String>> queryByenhancer_promoter_N(@Param("lnc") String lnc,@Param("regulation")  String regulation);
	List<HashMap<String,String>> queryByenhancer_se_N(@Param("lnc") String lnc);
	List<HashMap<String,String>> queryByenhancer_chromatin_N(@Param("lnc") String lnc);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_promoter_A(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end,@Param("regulation")  String regulation);
	List<HashMap<String,HashMap<String,String>>> queryByenhancer_se_A(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end);
	List<HashMap<String,HashMap<String,String>>> queryByenhancer_chromatin_A(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_left_moren(@Param("source") String source,@Param("chr") String chr,@Param("start") String start,@Param("end")  String end);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_right_node_moren(@Param("source") String source,@Param("chr") String chr,@Param("start") String start,@Param("end")  String end);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_right_edge_moren(@Param("source") String source,@Param("chr") String chr,@Param("start") String start,@Param("end")  String end);
	
	
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_left(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end,@Param("se_tablename") String se_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_right_node(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end,@Param("se_tablename") String se_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_right_edge(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end,@Param("se_tablename") String se_tablename);

	//##################################### search by enhancer ��� ####################################################
	//##################################### search by chromatin ####################################################
	List<String> queryChromatinBiosample_Type();
	List<String> queryChromatinBiosample_Name(String Biosample_Type);
	
	List<HashMap<String,HashMap<String,String>>> queryBych_promoter(@Param("lnc") String lnc,@Param("source") String source,@Param("regulation")  String regulation);
	List<HashMap<String,String>> queryBych_se(@Param("lnc") String lnc,@Param("source") String source);
	List<HashMap<String,String>> queryBych_chromatin(@Param("lnc") String lnc,@Param("source") String source);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_left(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end,@Param("source")  String source,@Param("D_tablename")  String D_tablename,@Param("A_tablename")  String A_tablename);

	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_right_node(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end,@Param("source")  String source,@Param("D_tablename")  String D_tablename,@Param("A_tablename")  String A_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_right_edge(@Param("chr") String chr,@Param("start") String start,@Param("end")  String end,@Param("source")  String source,@Param("D_tablename")  String D_tablename,@Param("A_tablename")  String A_tablename);
	
	//##################################### search by chromatin ���####################################################
	
	List<HashMap<String,String>> queryByPro_number_G(@Param("regulation") String regulation,@Param("lncnames") String lncnames);
	List<HashMap<String,String>> queryByPro_number_N(@Param("regulation") String regulation,@Param("lncnames") String lncnames);
	
	List<HashMap<String,String>> queryBySE_number_G(@Param("lncnames") String lncnames);
	List<HashMap<String,String>> queryBySE_number_N(@Param("lncnames") String lncnames);
	
	List<HashMap<String,String>> queryByCh_number_G(@Param("lncnames") String lncnames);
	List<HashMap<String,String>> queryByCh_number_N(@Param("lncnames") String lncnames);
	
	List<String> queryBySearchDiseasedis();
	
	List<HashMap<String,String>> queryBySearchDisease_G(@Param("disease") String disease);
	List<HashMap<String,String>> queryBySearchDisease_N(@Param("disease") String disease);
	
	List<HashMap<String,String>> queryBydisease_G(@Param("lncnames") String lncnames);
	List<HashMap<String,String>> queryBydisease_N(@Param("lncnames") String lncnames);
	
	List<HashMap<String,String>> queryBymirna_G(@Param("lncnames") String lncnames);
	List<HashMap<String,String>> queryBymirna_N(@Param("lncnames") String lncnames);
	
	List<HashMap<String,String>> queryBylocalization(@Param("lncnames") String lncnames,@Param("source") String source);
	
	List<HashMap<String,String>> queryByrbp(@Param("lncnames") String lncnames,@Param("source") String source);
	
	List<String> queryBiosample_Type();
	List<String> querytiss(String Biosample_Type);
	List<String> queryBiosample_Name(@Param("Biosample_Type") String Biosample_Type,@Param("tiss") String tiss);
	List<HashMap<String,HashMap<String,String>>> queryByDNaseI_G(@Param("source") String source,@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,HashMap<String,String>>> queryByDNaseI_N(@Param("source") String source,@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDNaseI_GG(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("Biosample_Type")  String Biosample_Type,@Param("list")  String [] list);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDNaseI_NN(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("Biosample_Type")  String Biosample_Type,@Param("list")  String [] list);
	
	List<String> queryBiosample_Type_TF();
	List<String> querytfid(String Biosample_Name);
	List<String> querytfidmap(String Biosample_Name);
	List<HashMap<String, String>> queryBiosample_Name_TF(String Biosample_Type_TF);
	List<HashMap<String,HashMap<String,String>>> queryByTF_GG(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("tf_tablename")  String tf_tablename);
	List<HashMap<String,HashMap<String,String>>> queryByTF_NN(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("tf_tablename")  String tf_tablename);
	
	List<String> queryBiosample_Type_450();
	List<HashMap<String, String>> queryBiosample_Name_450(String Biosample_Type_450);
	
	List<HashMap<String, String>> queryBytablename_450k(@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name")  String Biosample_Name);
	List<HashMap<String, String>> queryBytablename_WGBS(@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name")  String Biosample_Name);
	
	List<HashMap<HashMap<String,String>,String>> queryByMethylation_450k_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("tablename_450k")  String tablename_450k);
	List<HashMap<HashMap<String,String>,String>> queryByMethylation_450k_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("tablename_450k")  String tablename_450k);
	
	List<String> queryBiosample_Type_wgbs();
	List<HashMap<String, String>> queryBiosample_Name_wgbs(String Biosample_Type_wgbs);
	List<HashMap<HashMap<String,String>,String>> queryByMethylation_WGBS_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("tablename_WGBS")  String tablename_WGBS);
	List<HashMap<HashMap<String,String>,String>> queryByMethylation_WGBS_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("tablename_WGBS")  String tablename_WGBS);
	
	List<String> queryBiosample_Name_3D();
	List<HashMap<HashMap<String,String>,String>> queryBy3D_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("list")  String [] list);
	List<HashMap<HashMap<String,String>,String>> queryBy3D_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("list")  String [] list);

	
	List<String> queryBiosample_Type_EpiTensor();
	List<HashMap<String, String>> queryBiosample_Name_EpiTensor(String Biosample_Type_EpiTensor);
	
	List<HashMap<String, String>> queryBytablename_inter(@Param("tiss") String tiss);
	
	List<HashMap<HashMap<String,String>,String>> queryByEpiTensor_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("inter_tablename")  String inter_tablename);
	List<HashMap<HashMap<String,String>,String>> queryByEpiTensor_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("inter_tablename")  String inter_tablename);

	List<HashMap<HashMap<String,String>,String>> queryByregionA_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("inter_tablename")  String inter_tablename);
	List<HashMap<HashMap<String,String>,String>> queryByregionA_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("inter_tablename")  String inter_tablename);

	List<HashMap<HashMap<String,String>,String>> queryByregionB_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("inter_tablename")  String inter_tablename);
	List<HashMap<HashMap<String,String>,String>> queryByregionB_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("inter_tablename")  String inter_tablename);

	List<HashMap<String,String>> queryByMotif_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByMotif_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);

	List<HashMap<String,String>> queryBySNP_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryBySNP_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
    
	List<HashMap<String,String>> queryByRiskSNP_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByRiskSNP_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);

	List<HashMap<String,String>> queryByeqtl_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByeqtl_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);

	List<HashMap<String,String>> queryByafr_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByafr_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);

	List<HashMap<String,String>> queryByamr_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByamr_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);

	List<HashMap<String,String>> queryByeas_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByeas_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);

	List<HashMap<String,String>> queryByeur_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByeur_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);

	List<HashMap<String,String>> queryBysas_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryBysas_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames);

	
	List<HashMap<String,String>> queryByAFR_G(@Param("ID") String ID);
	List<HashMap<String,String>> queryByAFR_N(@Param("ID") String ID);
    
	List<HashMap<String,String>> queryByAMR_G(@Param("ID") String ID);
	List<HashMap<String,String>> queryByAMR_N(@Param("ID") String ID);
	
	List<HashMap<String,String>> queryByEAS_G(@Param("ID") String ID);
	List<HashMap<String,String>> queryByEAS_N(@Param("ID") String ID);
	
	List<HashMap<String,String>> queryByEQTL_G(@Param("ID") String ID);
	List<HashMap<String,String>> queryByEQTL_N(@Param("ID") String ID);
	
	List<HashMap<String,String>> queryByRISKSNP_G(@Param("ID") String ID);
	List<HashMap<String,String>> queryByRISKSNP_N(@Param("ID") String ID);
	
	List<HashMap<String,String>> queryByMOTIFCHANGE_G(@Param("ID") String ID);
	List<HashMap<String,String>> queryByMOTIFCHANGE_N(@Param("ID") String ID);
	
	List<HashMap<String,String>> queryByEUR_G(@Param("ID") String ID);
	List<HashMap<String,String>> queryByEUR_N(@Param("ID") String ID);
	
	List<HashMap<String,String>> queryBySAS_G(@Param("ID") String ID);
	List<HashMap<String,String>> queryBySAS_N(@Param("ID") String ID);
	
	
	List<String> queryBiosample_Type_Histone();
	List<HashMap<String, String>> queryBiosample_Name_Histone(String Biosample_Type_Histone);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByHistone_G(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name")  String Biosample_Name,@Param("histone")  String histone);
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByHistone_N(@Param("regulation") String regulation,@Param("lncnames")  String lncnames,@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name")  String Biosample_Name,@Param("histone")  String histone);

	List<HashMap<String,String>> queryBySE_moren(@Param("source")  String source,@Param("lncnames")  String lncnames);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_tablename(@Param("source")  String source,@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name")  String Biosample_Name);
	
	List<HashMap<String,String>> queryBySE(@Param("lncnames")  String lncnames,@Param("se_tablename")  String se_tablename);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_TF_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("tf_tablename")  String tf_tablename);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_SNP_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_RiskSNP_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_EqtL_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_SAS_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_EUR_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_EAS_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_AMR_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<String,HashMap<String,String>>> queryBySE_AFR_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_450K_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("tablename_450k")  String tablename_450k);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_WGBS_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("tablename_WGBS")  String tablename_WGBS);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBySE_Intersection_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("inter_tablename")  String inter_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByregionA(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("inter_tablename")  String inter_tablename);
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByregionB(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("inter_tablename")  String inter_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBySE_Histone_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name")  String Biosample_Name,@Param("histone")  String histone);
	
	List<HashMap<HashMap<String,String>,String>> queryBySE_motif(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<String,String>> queryByChromatin_tablename(@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name") String Biosample_Name);
	List<HashMap<String,String>> queryByChromatin_tablenameATAC(@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name") String Biosample_Name);
	List<HashMap<String,String>> queryByChromatin_tf_tablename(@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name") String Biosample_Name);
	
	List<HashMap<String,String>> queryByTF_name_pro_tablename(@Param("source")  String source,@Param("chr")  String chr);
	List<HashMap<String,String>> queryByTF_name_other_tablename(@Param("source")  String source,@Param("chr")  String chr);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_G(@Param("source")  String source,@Param("lncnames")  String lncnames,@Param("tablename")  String tablename,@Param("tablenameATAC")  String tablenameATAC);
	
	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_SNP_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);
	
	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_RiskSNP_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);

	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_Eqtl_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);

	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_SAS_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);

	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_EUR_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);

	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_EAS_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);

	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_AMR_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);

	List<HashMap<HashMap<String,String>,String>> queryByChromatinD_AFR_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end);


	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_TF_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("tf_tablename")  String tf_tablename);

	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_450K_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("tablename_450k")  String tablename_450k);

	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_WGBS_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("tablename_WGBS")  String tablename_WGBS);

	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByChromatinD_Intersection_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("inter_tablename")  String inter_tablename);
	
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBych_regionA(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("inter_tablename")  String inter_tablename);
	List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBych_regionB(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("inter_tablename")  String inter_tablename);
	
	
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryByChromatinD_Histone_G(@Param("chr")  String chr,@Param("start")  String start,@Param("end")  String end,@Param("Biosample_Type")  String Biosample_Type,@Param("Biosample_Name")  String Biosample_Name,@Param("histone")  String histone);

	List<HashMap<String,String>> queryByExpression_NONCODE_G(@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByExpression_N(@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByExpression_TCGA(@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByExpression_GTEX(@Param("lncnames")  String lncnames);
	List<HashMap<String,String>> queryByExpression_CCLE(@Param("lncnames")  String lncnames);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_1(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_2(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_3(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_4(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_5(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_6(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_7(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_8(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_9(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_10(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_11(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_12(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_13(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_14(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_15(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_1(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_2(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_3(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_4(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_5(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_6(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_7(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_8(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_9(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_10(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_11(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_12(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_13(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_14(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_15(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_1(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_2(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_3(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_4(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_5(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_6(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_7(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_8(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_9(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_10(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_11(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_12(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_13(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_14(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_15(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_1(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_2(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_3(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_4(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_5(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_6(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_7(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_8(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_9(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_10(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_11(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_12(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_13(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_14(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_15(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_1(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_2(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_3(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_4(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_5(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_6(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_7(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_8(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_9(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_10(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_11(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_12(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_13(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_14(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_15(@Param("source")  String source,@Param("Gene_type")  String Gene_type,@Param("region")  String region,@Param("CHR")  String CHR);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_1(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_1(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_1(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_1(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_1(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_2(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_2(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_2(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_2(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_2(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_3(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_3(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_3(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_3(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_3(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_4(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_4(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_4(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_4(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_4(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_5(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_5(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_5(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_5(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_5(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_6(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_6(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_6(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_6(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_6(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_7(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_7(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_7(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_7(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_7(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_8(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_8(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_8(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_8(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_8(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_9(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_9(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_9(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_9(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_9(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_10(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_10(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_10(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_10(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_10(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_11(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_11(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_11(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_11(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_11(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_12(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_12(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_12(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_12(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_12(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_13(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_13(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_13(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_13(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_13(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_14(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_14(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_14(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_14(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_14(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_15(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_15(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_15(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_15(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_15(@Param("Data_source")  String Data_source,@Param("Element_class")  String Element_class,@Param("Biosample_type")  String Biosample_type,@Param("Biosample_name")  String Biosample_name);
	
	
	//##################################### R ####################################################
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("promoterRegion") String promoterRegion,@Param("lncRNA")  String lncRNA,@Param("tftype")  String tftype);

	List<HashMap<String,String>> queryByRAnalyze_chr(@Param("lncRNA")  String lncRNA);
	
	List<HashMap<String,HashMap<String,String>>> queryByRresult_left(@Param("source") String source,@Param("promoterRegion") String promoterRegion,@Param("lncRNA")  String lncRNA);
	List<HashMap<String,String>> queryByRresult_left_A(@Param("promoterRegion") String promoterRegion,@Param("lncRNA")  String lncRNA);
	
	List<String> querytftype();
	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze_node(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("promoterRegion") String promoterRegion,@Param("lncRNA")  String lncRNA,@Param("tftype")  String tftype);

	List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze_edge(@Param("pro_tablename") String pro_tablename,@Param("other_tablename") String other_tablename,@Param("promoterRegion") String promoterRegion,@Param("lncRNA")  String lncRNA,@Param("tftype")  String tftype);

	void insertUserEmail(UserEmail userEmail);
	
	String selectSnpDisease(String set);

}
