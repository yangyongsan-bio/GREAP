package com.lyy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyy.mapper.SearchMapper;
import com.lyy.model.UserEmail;
import com.lyy.service.SearchService;

@Service
public class SearchServiceImpl  implements  SearchService{
	@Autowired
	SearchMapper   searchMapper;
	
	@Override
	public List<String> queryData_Type() {
		List<String>list=null;
		list = searchMapper.queryData_Type();
		return list;
	}
	@Override
	public List<String> queryTissue_Name(String Data_Type) {
		List<String>list=null;
	
		list = searchMapper.queryTissue_Name(Data_Type);
		
		return list;
	}
	
	@Override
	public List<String> queryBysearch_region(String setnew) {
		List<String>list=null;
	
		list = searchMapper.queryBysearch_region(setnew);
		
		return list;
	}
	@Override
	public List<String> queryBysearch_gene(String gene) {
		List<String>list=null;
	
		list = searchMapper.queryBysearch_gene(gene);
		
		return list;
	}
	@Override
	public List<String> querylisttable(String set) {
		List<String>list=null;
	
		list = searchMapper.querylisttable(set);
		
		return list;
	}
	
	@Override
	public List<HashMap<String,String>> queryByDatatype(String datatype,String dataset,String table) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByDatatype(datatype,dataset,table);
	
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByDataset(String datatype,String dataset,String table) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByDataset(datatype,dataset,table);
	
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByDatatype_1(String datatype,String dataset,String table) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByDatatype_1(datatype,dataset,table);
	
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByDataset_1(String datatype,String dataset,String table) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByDataset_1(datatype,dataset,table);
	
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByDatatype_2(String datatype,String dataset,String table) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByDatatype_2(datatype,dataset,table);
	
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByDataset_2(String datatype,String dataset,String table) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByDataset_2(datatype,dataset,table);
	
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByDatatype_3(String datatype,String dataset,String table) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByDatatype_3(datatype,dataset,table);
	
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByDataset_3(String datatype,String dataset,String table) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByDataset_3(datatype,dataset,table);
	
		return list;
	}
	
	@Override
	public List<HashMap<HashMap<String,String>,String>> queryBybrowselist(String datatype,String dataset,String searchValue, String table) {
		List<HashMap<HashMap<String,String>,String>> list=null;
	
			list = searchMapper.queryBybrowselist(datatype,dataset,searchValue, table);
	
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,String>> queryBybrowselist_1(String datatype,String dataset,String searchValue, String table) {
		List<HashMap<HashMap<String,String>,String>> list=null;
	
			list = searchMapper.queryBybrowselist_1(datatype,dataset,searchValue, table);
	
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,String>> queryBybrowselist_2(String datatype,String dataset,String searchValue, String table) {
		List<HashMap<HashMap<String,String>,String>> list=null;
	
			list = searchMapper.queryBybrowselist_2(datatype,dataset,searchValue, table);
	
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,String>> queryBybrowselist_3(String datatype,String dataset,String searchValue, String table) {
		List<HashMap<HashMap<String,String>,String>> list=null;
	
			list = searchMapper.queryBybrowselist_3(datatype,dataset,searchValue, table);
	
		return list;
	}
	
	@Override
	public List<HashMap<String,String>> queryBydownlist1(String searchValue) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBydownlist1(searchValue);
	
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBydownlist2(String searchValue) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBydownlist2(searchValue);
	
		return list;
	}
	
	@Override
	public List<String> querySubclass(String datatype) {
		List<String>list=null;
	
		list = searchMapper.querySubclass(datatype);
		
		return list;
	}
	
	@Override
	public List<HashMap<HashMap<String,String>,String>> querylistchr(String datatype, String subclass, String set) {
		List<HashMap<HashMap<String,String>,String>>list=null;
	
		list = searchMapper.querylistchr(datatype,subclass,set);
		
		return list;
	}
	
	@Override
	public List<HashMap<String,String>> querylistfile(String subclass,String set) {
		List<HashMap<String,String>>list=null;
	
		list = searchMapper.querylistfile(subclass,set);
		
		return list;
	}
	//################################# download #################################	
	@Override
	public List<String> querydownload(String set) {
		List<String>list=null;
	
		list = searchMapper.querydownload(set);
		
		return list;
	}
//################################# Search by LncRNA names #################################
	@Override
	public List<String> queryRegulation() {
		List<String>list=null;
		list = searchMapper.queryRegulation();
		return list;
	}
	
	@Override
	public List<HashMap<String,HashMap<String,String>>> queryByLncName(String source,String regulation,String[] params) {
		List<HashMap<String,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByLncName(source,regulation,params);
	
		return list;
	}
	//######sourceΪGENCODE������######
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByLncName_G(String source,String regulation,String[] params) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByLncName_G(source,regulation,params);
			
			return list;
		}
		
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByLncName_chr(String source,String regulation,String lncnames) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByLncName_chr(source,regulation,lncnames);
			
			return list;
		}
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByLncName_start(String source,String regulation,String lncnames) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByLncName_start(source,regulation,lncnames);
			
			return list;
		}
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByLncName_end(String source,String regulation,String lncnames) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByLncName_end(source,regulation,lncnames);
			
			return list;
		}
	//######sourceΪNONCODE������######
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByLncName_N(String source,String regulation,String[] params) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByLncName_N(source,regulation,params);
			
			return list;
		}
		//######LncnameΪ�����ʱ������######
		
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBypromoter_A(String source,String regulation,String[] params) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBypromoter_A(source,regulation,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByse_A(String source,String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByse_A(source,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBychromatin_A(String source,String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBychromatin_A(source,params);
			
			return list;
		}
		
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBypromoter_G(String source,String regulation,String[] params) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBypromoter_G(source,regulation,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBypromoter_N(String source,String regulation,String[] params) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBypromoter_N(source,regulation,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByse_G(String source,String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByse_G(source,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByse_N(String source,String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByse_N(source,params);
			
			return list;
		}
		
		@Override
		public List<HashMap<String,String>> queryBychromatin_G(String source,String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBychromatin_G(source,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBychromatin_N(String source,String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBychromatin_N(source,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByresult_left(String source,String regulation,String[] params) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByresult_left(source,regulation,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByresult_left_A(String regulation, String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByresult_left_A(regulation,params);
			
			return list;
		}
		
		@Override
		public List<HashMap<String,String>> queryByresult_right(String source,String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByresult_right(source,params);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByresult_right_A(String[] params) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByresult_right_A(params);
			
			return list;
		}
//################################# Search by TF names #################################
	@Override
	public List<String> querytfname() {
		List<String>list=null;
		list = searchMapper.querytfname();
		return list;
	}
	@Override
	public List<String> querytype(String tfname) {
		List<String>list=null;
		list = searchMapper.querytype(tfname);
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByTF_name_pro_tablename(String source,String chr){
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByTF_name_pro_tablename(source,chr);

		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByTF_name_other_tablename(String source,String chr){
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByTF_name_other_tablename(source,chr);

		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_promoter_G(String pro_tablename,String other_tablename, String regulation,String tfnames, String type) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_promoter_G(pro_tablename,other_tablename,regulation,tfnames,type);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_se_G(String pro_tablename,String other_tablename, String regulation,String tfnames, String type) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_se_G(pro_tablename,other_tablename,regulation,tfnames,type);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_chromatin_G(String pro_tablename,String other_tablename, String regulation,String tfnames, String type) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_chromatin_G(pro_tablename,other_tablename,regulation,tfnames,type);
		
		return list;
	}
//######sourceΪNONCODE������######
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_promoter_N(String pro_tablename,String other_tablename, String regulation,String tfnames, String type) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_promoter_N(pro_tablename,other_tablename,regulation,tfnames,type);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_se_N(String pro_tablename,String other_tablename, String regulation,String tfnames, String type) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_se_N(pro_tablename,other_tablename,regulation,tfnames,type);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_chromatin_N(String pro_tablename,String other_tablename, String regulation,String tfnames, String type) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_chromatin_N(pro_tablename,other_tablename,regulation,tfnames,type);
		
		return list;
	}
//######sourceΪAll������######
	@Override
	public List<HashMap<String,String>> queryByTF_promoter_A(String regulation,String tfnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByTF_promoter_A(regulation,tfnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByTF_se_A(String regulation,String tfnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByTF_se_A(regulation,tfnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByTF_chromatin_A(String regulation,String tfnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByTF_chromatin_A(regulation,tfnames);
		
		return list;
	}
//############ �Ϸ��ı������ӻ�
	@Override
	public List<HashMap<String,String>> queryByTF_left(String tfnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByTF_left(tfnames);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_right_edge(String pro_tablename,String regulation,String tfnames,String type,String other_tablename) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_right_edge(pro_tablename,regulation,tfnames,type,other_tablename);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByTF_right_edge_A(String regulation,String tfnames,String type,String chr) {
		List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_right_edge_A(regulation,tfnames,type,chr);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByTF_right_node(String pro_tablename,String regulation,String tfnames,String type,String other_tablename) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_right_node(pro_tablename,regulation,tfnames,type,other_tablename);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByTF_right_node_A(String regulation,String tfnames,String type,String chr) {
		List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByTF_right_node_A(regulation,tfnames,type,chr);
		
		return list;
	}
	//################################# Search by SNPID #################################
	@Override
	public List<HashMap<String,String>> queryBySNP_promoter_G(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_promoter_G(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_se_G(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_se_G(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_chromatin_G(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_chromatin_G(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_promoter_N(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_promoter_N(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_se_N(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_se_N(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_chromatin_N(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_chromatin_N(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_promoter_A(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_promoter_A(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_se_A(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_se_A(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_chromatin_A(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_chromatin_A(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_left(String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_left(snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_right_edge_A(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_right_edge_A(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,HashMap<String,String>>> queryBySNP_right_edge(String source,String regulation,String snpid) {
		List<HashMap<String,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryBySNP_right_edge(source,regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySNP_right_node_A(String regulation,String snpid) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySNP_right_node_A(regulation,snpid);
		
		return list;
	}
	@Override
	public List<HashMap<String,HashMap<String,String>>> queryBySNP_right_node(String source,String regulation,String snpid) {
		List<HashMap<String,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryBySNP_right_node(source,regulation,snpid);
		
		return list;
	}
	//################################# Search by enhancer #################################
	@Override
	public List<String> queryEnhancerBiosample_Type() {
		List<String>list=null;
		list = searchMapper.queryEnhancerBiosample_Type();
		return list;
	}
	@Override
	public List<String> queryEnhancerBiosample_Name(String Biosample_Type) {
		List<String>list=null;
	
		list = searchMapper.queryEnhancerBiosample_Name(Biosample_Type);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByenhancer_promoter_G(String lnc,String regulation) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByenhancer_promoter_G(lnc,regulation);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByenhancer_se_G(String lnc) {
		List<HashMap<String,String>> list=null;
			list = searchMapper.queryByenhancer_se_G(lnc);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByenhancer_chromatin_G(String lnc) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByenhancer_chromatin_G(lnc);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByenhancer_promoter_N(String lnc,String regulation) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByenhancer_promoter_N(lnc,regulation);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByenhancer_se_N(String lnc) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByenhancer_se_N(lnc);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByenhancer_chromatin_N(String lnc) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByenhancer_chromatin_N(lnc);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_promoter_A(String chr,String start,String end,String regulation) {
		List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByenhancer_promoter_A(chr,start,end,regulation);
		
		return list;
	}
	@Override
	public List<HashMap<String,HashMap<String,String>>> queryByenhancer_se_A(String chr,String start,String end) {
		List<HashMap<String,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByenhancer_se_A(chr,start,end);
		
		return list;
	}
	@Override
	public List<HashMap<String,HashMap<String,String>>> queryByenhancer_chromatin_A(String chr,String start,String end) {
		List<HashMap<String,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByenhancer_chromatin_A(chr,start,end);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_left_moren(String source,String chr,String start,String end) {
		List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByenhancer_left_moren(source,chr,start,end);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_right_node_moren(String source,String chr,String start,String end) {
		List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByenhancer_right_node_moren(source,chr,start,end);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByenhancer_right_edge_moren(String source,String chr,String start,String end) {
		List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryByenhancer_right_edge_moren(source,chr,start,end);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_left(String chr,String start,String end,String se_tablename) {
		List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
	
			list = searchMapper.queryByenhancer_left(chr,start,end,se_tablename);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_right_node(String chr,String start,String end,String se_tablename) {
		List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
	
			list = searchMapper.queryByenhancer_right_node(chr,start,end,se_tablename);
		
		return list;
	}

	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByenhancer_right_edge(String chr,String start,String end,String se_tablename) {
		List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
	
			list = searchMapper.queryByenhancer_right_edge(chr,start,end,se_tablename);
		
		return list;
	}


//################################# search by chromatin ##################################
	@Override
	public List<String> queryChromatinBiosample_Type() {
		List<String>list=null;
		list = searchMapper.queryChromatinBiosample_Type();
		return list;
	}
	@Override
	public List<String> queryChromatinBiosample_Name(String bio_Type) {
		List<String>list=null;
	
		list = searchMapper.queryChromatinBiosample_Name(bio_Type);
		
		return list;
	}
	@Override
	public List<HashMap<String,HashMap<String,String>>> queryBych_promoter(String lnc,String source,String regulation) {
		List<HashMap<String,HashMap<String,String>>> list=null;
	
			list = searchMapper.queryBych_promoter(lnc,source,regulation);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBych_se(String lnc,String source) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBych_se(lnc,source);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBych_chromatin(String lnc,String source) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBych_chromatin(lnc,source);
		
		return list;
	}

	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_left(String chr,String start,String end,String source,String D_tablename,String A_tablename) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> list=null;
	
			list = searchMapper.queryBych_left(chr,start,end,source,D_tablename,A_tablename);
		
		return list;
	}
	
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_right_node(String chr,String start,String end,String source,String D_tablename,String A_tablename) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> list=null;
	
			list = searchMapper.queryBych_right_node(chr,start,end,source,D_tablename,A_tablename);
		
		return list;
	}
	@Override
	public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBych_right_edge(String chr,String start,String end,String source,String D_tablename,String A_tablename) {
		List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> list=null;
	
			list = searchMapper.queryBych_right_edge(chr,start,end,source,D_tablename,A_tablename);
		
		return list;
	}
	
	//#######################detailҳ��״ͼ����#################################
	@Override
	public List<HashMap<String,String>> queryByPro_number_G(String regulation,String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByPro_number_G(regulation,lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByPro_number_N(String regulation,String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByPro_number_N(regulation,lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySE_number_G(String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySE_number_G(lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySE_number_N(String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySE_number_N(lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByCh_number_G(String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByCh_number_G(lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByCh_number_N(String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByCh_number_N(lncnames);
		
		return list;
	}
	//#######################detailҳ��״ͼ�������#################################
	@Override
	public List<String> queryBySearchDiseasedis() {
		List<String>list=null;
		list = searchMapper.queryBySearchDiseasedis();
		return list;
	}
	
	@Override
	public List<HashMap<String,String>> queryBySearchDisease_G(String disease) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySearchDisease_G(disease);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBySearchDisease_N(String disease) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBySearchDisease_N(disease);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBydisease_G(String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBydisease_G(lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBydisease_N(String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBydisease_N(lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBymirna_G(String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBymirna_G(lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBymirna_N(String lncnames) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBymirna_N(lncnames);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryBylocalization(String lncnames,String source) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryBylocalization(lncnames,source);
		
		return list;
	}
	@Override
	public List<HashMap<String,String>> queryByrbp(String lncnames,String source) {
		List<HashMap<String,String>> list=null;
	
			list = searchMapper.queryByrbp(lncnames,source);
		
		return list;
	}
//############# DNaseI���� #############
//		sourceΪGENCODE������(�����ظ����Ѿ�����ɾ����)
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByDNaseI_G(String source,String regulation,String lncnames) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDNaseI_G(source,regulation,lncnames);
			
			return list;
		}
//		sourceΪNONCODE������(�����ظ����Ѿ�����ɾ����)
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByDNaseI_N(String source,String regulation,String lncnames) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDNaseI_N(source,regulation,lncnames);
			
			return list;
		}
//	   ############# �ܵ�type��tiss��name���� #############	
		@Override
		public List<String> queryBiosample_Type() {
			List<String>list=null;
			list = searchMapper.queryBiosample_Type();
			return list;
		}
		@Override
		public List<String> querytiss(String Biosample_Type) {
			List<String>list=null;
		
			list = searchMapper.querytiss(Biosample_Type);
			
			return list;
		}
		@Override
		public List<String> queryBiosample_Name(String Biosample_Type,String tiss) {
			List<String>list=null;
		
			list = searchMapper.queryBiosample_Name(Biosample_Type,tiss);
			
			return list;
		}
		
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDNaseI_GG(String regulation,String lncnames,String Biosample_Type,String[] params){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDNaseI_GG(regulation,lncnames,Biosample_Type,params);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDNaseI_NN(String regulation,String lncnames,String Biosample_Type,String[] params){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDNaseI_NN(regulation,lncnames,Biosample_Type,params);
			
			return list;
		}
//		   ############# TF��ҳ������ #############
		
		@Override
		public List<String> queryBiosample_Type_TF() {
			List<String>list=null;
			list = searchMapper.queryBiosample_Type_TF();
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBiosample_Name_TF(String Biosample_Type_TF) {
			List<HashMap<String,String>>list=null;
		
			list = searchMapper.queryBiosample_Name_TF(Biosample_Type_TF);
			
			return list;
		}
		
		@Override
		public List<String> querytfid(String Biosample_Name) {
			List<String>list=null;
		
			list = searchMapper.querytfid(Biosample_Name);
			
			return list;
		}
		@Override
		public List<String> querytfidmap(String Biosample_Name) {
			List<String>list=null;
		
			list = searchMapper.querytfidmap(Biosample_Name);
			
			return list;
		}
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByTF_GG(String regulation,String lncnames,String tf_tablename){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByTF_GG(regulation,lncnames,tf_tablename);

			return list;
		}
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByTF_NN(String regulation,String lncnames,String tf_tablename){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByTF_NN(regulation,lncnames,tf_tablename);
			
			return list;
		}
//		  ############# Methylation_450k��ҳ������ #############
		
		@Override
		public List<String> queryBiosample_Type_450() {
			List<String>list=null;
			list = searchMapper.queryBiosample_Type_450();
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBiosample_Name_450(String Biosample_Type_450) {
			List<HashMap<String,String>>list=null;
		
			list = searchMapper.queryBiosample_Name_450(Biosample_Type_450);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBytablename_450k(String Biosample_Type,String Biosample_Name) {
			List<HashMap<String,String>>list=null;
		
			list = searchMapper.queryBytablename_450k(Biosample_Type,Biosample_Name);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBytablename_WGBS(String Biosample_Type,String Biosample_Name) {
			List<HashMap<String,String>>list=null;
		
			list = searchMapper.queryBytablename_WGBS(Biosample_Type,Biosample_Name);
			
			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByMethylation_450k_G(String regulation,String lncnames,String tablename_450k){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByMethylation_450k_G(regulation,lncnames,tablename_450k);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByMethylation_450k_N(String regulation,String lncnames,String tablename_450k){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByMethylation_450k_N(regulation,lncnames,tablename_450k);
			
			return list;
		}
		
//		  ############# Methylation_WGBS��ҳ������ #############		
		@Override
		public List<String> queryBiosample_Type_wgbs() {
			List<String>list=null;
			list = searchMapper.queryBiosample_Type_wgbs();
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBiosample_Name_wgbs(String Biosample_Type_wgbs) {
			List<HashMap<String,String>>list=null;
		
			list = searchMapper.queryBiosample_Name_wgbs(Biosample_Type_wgbs);
			
			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByMethylation_WGBS_G(String regulation,String lncnames,String tablename_WGBS){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByMethylation_WGBS_G(regulation,lncnames,tablename_WGBS);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByMethylation_WGBS_N(String regulation,String lncnames,String tablename_WGBS){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByMethylation_WGBS_N(regulation,lncnames,tablename_WGBS);
			
			return list;
		}
		
//		  ############# 3D��ҳ������ #############
		@Override
		public List<String> queryBiosample_Name_3D() {
			List<String>list=null;
			list = searchMapper.queryBiosample_Name_3D();
			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryBy3D_G(String regulation,String lncnames,String[] params){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryBy3D_G(regulation,lncnames,params);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryBy3D_N(String regulation,String lncnames,String[] params){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryBy3D_N(regulation,lncnames,params);

			return list;
		}
//		  ############# EpiTensor��ҳ������ #############

		@Override
		public List<String> queryBiosample_Type_EpiTensor() {
			List<String>list=null;
			list = searchMapper.queryBiosample_Type_EpiTensor();
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBiosample_Name_EpiTensor(String Biosample_Type_EpiTensor) {
			List<HashMap<String,String>>list=null;
		
			list = searchMapper.queryBiosample_Name_EpiTensor(Biosample_Type_EpiTensor);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBytablename_inter(String tiss) {
			List<HashMap<String,String>>list=null;
		
			list = searchMapper.queryBytablename_inter(tiss);
			
			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByEpiTensor_G(String regulation,String lncnames,String inter_tablename){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByEpiTensor_G(regulation,lncnames,inter_tablename);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByEpiTensor_N(String regulation,String lncnames,String inter_tablename){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByEpiTensor_N(regulation,lncnames,inter_tablename);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByregionA_G(String regulation,String lncnames,String inter_tablename){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByregionA_G(regulation,lncnames,inter_tablename);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByregionA_N(String regulation,String lncnames,String inter_tablename){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByregionA_N(regulation,lncnames,inter_tablename);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByregionB_G(String regulation,String lncnames,String inter_tablename){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByregionB_G(regulation,lncnames,inter_tablename);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByregionB_N(String regulation,String lncnames,String inter_tablename){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByregionB_N(regulation,lncnames,inter_tablename);

			return list;
		}
//		  ############# Motif��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByMotif_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByMotif_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByMotif_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByMotif_N(regulation,lncnames);

			return list;
		}
		
//		  ############# SNP��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryBySNP_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBySNP_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBySNP_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBySNP_N(regulation,lncnames);

			return list;
		}
//		  ############# RiskSNP������ #############
		@Override
		public List<HashMap<String,String>> queryByRiskSNP_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByRiskSNP_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByRiskSNP_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByRiskSNP_N(regulation,lncnames);

			return list;
		}
//		  ############# eqtl������ #############
		@Override
		public List<HashMap<String,String>> queryByeqtl_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByeqtl_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByeqtl_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByeqtl_N(regulation,lncnames);

			return list;
		}
//		  ############# afr������ #############
		@Override
		public List<HashMap<String,String>> queryByafr_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByafr_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByafr_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByafr_N(regulation,lncnames);

			return list;
		}
//		  ############# amr������ #############
		@Override
		public List<HashMap<String,String>> queryByamr_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByamr_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByamr_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByamr_N(regulation,lncnames);

			return list;
		}
//		  ############# eas������ #############
		@Override
		public List<HashMap<String,String>> queryByeas_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByeas_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByeas_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByeas_N(regulation,lncnames);

			return list;
		}
//		  ############# eur������ #############
		@Override
		public List<HashMap<String,String>> queryByeur_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByeur_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByeur_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByeur_N(regulation,lncnames);

			return list;
		}
//		  ############# sas������ #############
		@Override
		public List<HashMap<String,String>> queryBysas_G(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBysas_G(regulation,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBysas_N(String regulation,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBysas_N(regulation,lncnames);

			return list;
		}
		
// #################### ������SNP���������Ӳ�ѯ ####################		
//		  ############# AFR��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByAFR_G(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByAFR_G(ID);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByAFR_N(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByAFR_N(ID);

			return list;
		}
//		  ############# AMR��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByAMR_G(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByAMR_G(ID);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByAMR_N(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByAMR_N(ID);

			return list;
		}
//		  ############# EAS��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByEAS_G(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByEAS_G(ID);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByEAS_N(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByEAS_N(ID);

			return list;
		}
//		  ############# EQTL��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByEQTL_G(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByEQTL_G(ID);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByEQTL_N(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByEQTL_N(ID);

			return list;
		}
//		  ############# RISKSNP��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByRISKSNP_G(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByRISKSNP_G(ID);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByRISKSNP_N(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByRISKSNP_N(ID);

			return list;
		}
//		  ############# MOTIFCHANGE��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByMOTIFCHANGE_G(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByMOTIFCHANGE_G(ID);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByMOTIFCHANGE_N(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByMOTIFCHANGE_N(ID);

			return list;
		}
//		  ############# EUR��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByEUR_G(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByEUR_G(ID);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByEUR_N(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByEUR_N(ID);

			return list;
		}
//		  ############# SAS��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryBySAS_G(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBySAS_G(ID);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBySAS_N(String ID){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBySAS_N(ID);

			return list;
		}
//		  ############# Histone��ҳ������ #############
		@Override
		public List<String> queryBiosample_Type_Histone() {
			List<String>list=null;
			list = searchMapper.queryBiosample_Type_Histone();
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBiosample_Name_Histone(String Biosample_Type_Histone) {
			List<HashMap<String,String>>list=null;
		
			list = searchMapper.queryBiosample_Name_Histone(Biosample_Type_Histone);
			
			return list;
		}

		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByHistone_G(String regulation,String lncnames,String Biosample_Type,String Biosample_Name,String histone){
			List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByHistone_G(regulation,lncnames,Biosample_Type,Biosample_Name,histone);

			return list;
		}
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByHistone_N(String regulation,String lncnames,String Biosample_Type,String Biosample_Name,String histone){
			List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByHistone_N(regulation,lncnames,Biosample_Type,Biosample_Name,histone);

			return list;
		}
//		  ############# SE��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryBySE_moren(String source,String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBySE_moren(source,lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_tablename(String source,String Biosample_Type,String Biosample_Name){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_tablename(source,Biosample_Type,Biosample_Name);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryBySE(String lncnames,String se_tablename){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryBySE(lncnames,se_tablename);

			return list;
		}
		
//		  ############# SE-TF��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_TF_G(String chr,String start,String end,String tf_tablename){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_TF_G(chr,start,end,tf_tablename);

			return list;
		}
		
//		  ############# SE-SNP��ҳ������ #############
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_SNP_G(String chr,String start,String end){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_SNP_G(chr,start,end);

			return list;
		}
			
//		  ############# SE-RiskSNP��ҳ������ #############
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_RiskSNP_G(String chr,String start,String end){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_RiskSNP_G(chr,start,end);

			return list;
		}
	
//		  ############# SE-EqtLP��ҳ������ #############
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_EqtL_G(String chr,String start,String end){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_EqtL_G(chr,start,end);

			return list;
		}
//		  ############# SE-SAS��ҳ������ #############
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_SAS_G(String chr,String start,String end){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_SAS_G(chr,start,end);

			return list;
		}
		
//		  ############# SE-EUR��ҳ������ #############
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_EUR_G(String chr,String start,String end){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_EUR_G(chr,start,end);

			return list;
		}
		
//		  ############# SE-EAS��ҳ������ #############
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_EAS_G(String chr,String start,String end){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_EAS_G(chr,start,end);

			return list;
		}
		
//		  ############# SE-AMR��ҳ������ #############
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_AMR_G(String chr,String start,String end){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_AMR_G(chr,start,end);

			return list;
		}
	
//		  ############# SE-AFR��ҳ������ #############
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryBySE_AFR_G(String chr,String start,String end){
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_AFR_G(chr,start,end);

			return list;
		}
	
//		  ############# SE-450K��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_450K_G(String chr,String start,String end,String tablename_450k){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_450K_G(chr,start,end,tablename_450k);

			return list;
		}
		
//		  ############# SE-WGBS��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySE_WGBS_G(String chr,String start,String end,String tablename_WGBS){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySE_WGBS_G(chr,start,end,tablename_WGBS);

			return list;
		}
	
//		  ############# SE-Intersection��ҳ������ #############
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBySE_Intersection_G(String chr,String start,String end,String inter_tablename){
			List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
		
				list = searchMapper.queryBySE_Intersection_G(chr,start,end,inter_tablename);

			return list;
		}
		
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByregionA(String chr,String start,String end,String inter_tablename){
			List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
		
				list = searchMapper.queryByregionA(chr,start,end,inter_tablename);

			return list;
		}
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByregionB(String chr,String start,String end,String inter_tablename){
			List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
		
				list = searchMapper.queryByregionB(chr,start,end,inter_tablename);

			return list;
		}
	
//		  ############# SE-Histone��ҳ������ #############
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> queryBySE_Histone_G(String chr,String start,String end,String Biosample_Type,String Biosample_Name,String histone){
			List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>> list=null;
		
				list = searchMapper.queryBySE_Histone_G(chr,start,end,Biosample_Type,Biosample_Name,histone);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryBySE_motif(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryBySE_motif(chr,start,end);

			return list;
		}
		
//		  ############# Chromatin Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByChromatin_tablename(String Biosample_Type,String Biosample_Name){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByChromatin_tablename(Biosample_Type,Biosample_Name);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByChromatin_tablenameATAC(String Biosample_Type,String Biosample_Name){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByChromatin_tablenameATAC(Biosample_Type,Biosample_Name);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByChromatin_tf_tablename(String Biosample_Type,String Biosample_Name){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByChromatin_tf_tablename(Biosample_Type,Biosample_Name);

			return list;
		}
		
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_G(String source,String lncnames,String tablename,String tablenameATAC){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByChromatinD_G(source,lncnames,tablename,tablenameATAC);

			return list;
		}
		
//		  ############# Chromatin-SNP Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByChromatinD_SNP_G(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_SNP_G(chr,start,end);

			return list;
		}
		
//		  ############# Chromatin-RiskSNP Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByChromatinD_RiskSNP_G(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_RiskSNP_G(chr,start,end);

			return list;
		}
//		  ############# Chromatin-Eqtl Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByChromatinD_Eqtl_G(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_Eqtl_G(chr,start,end);

			return list;
		}
//		  ############# Chromatin-SAS Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByChromatinD_SAS_G(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_SAS_G(chr,start,end);

			return list;
		}
		
//		  ############# Chromatin-EUR Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByChromatinD_EUR_G(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_EUR_G(chr,start,end);

			return list;
		}

//		  ############# Chromatin-EAS Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByChromatinD_EAS_G(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_EAS_G(chr,start,end);

			return list;
		}
//		  ############# Chromatin-AMR Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByChromatinD_AMR_G(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_AMR_G(chr,start,end);

			return list;
		}
//		  ############# Chromatin-AFR Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,String>> queryByChromatinD_AFR_G(String chr,String start,String end){
			List<HashMap<HashMap<String,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_AFR_G(chr,start,end);

			return list;
		}
//		  ############# Chromatin-TF Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_TF_G(String chr,String start,String end,String tf_tablename){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByChromatinD_TF_G(chr,start,end,tf_tablename);

			return list;
		}
		
//		  ############# Chromatin-450K Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_450K_G(String chr,String start,String end,String tablename_450k){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByChromatinD_450K_G(chr,start,end,tablename_450k);

			return list;
		}
//		  ############# Chromatin-WGBS Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByChromatinD_WGBS_G(String chr,String start,String end,String tablename_WGBS){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByChromatinD_WGBS_G(chr,start,end,tablename_WGBS);

			return list;
		}
//		  ############# Chromatin-Intersection Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryByChromatinD_Intersection_G(String chr,String start,String end,String inter_tablename){
			List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
		
				list = searchMapper.queryByChromatinD_Intersection_G(chr,start,end,inter_tablename);

			return list;
		}
		
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBych_regionA(String chr,String start,String end,String inter_tablename){
			List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
		
				list = searchMapper.queryBych_regionA(chr,start,end,inter_tablename);

			return list;
		}
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,String>> queryBych_regionB(String chr,String start,String end,String inter_tablename){
			List<HashMap<HashMap<HashMap<String,String>,String>,String>> list=null;
		
				list = searchMapper.queryBych_regionB(chr,start,end,inter_tablename);

			return list;
		}
//		  ############# Chromatin-Histone Ĭ�� ��ҳ������ #############
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>>  queryByChromatinD_Histone_G(String chr,String start,String end,String Biosample_Type,String Biosample_Name,String histone){
			List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<HashMap<String,String>,String>>>  list=null;
		
				list = searchMapper.queryByChromatinD_Histone_G(chr,start,end,Biosample_Type,Biosample_Name,histone);

			return list;
		}
//		############# Expression ��ҳ������ #############
		@Override
		public List<HashMap<String,String>> queryByExpression_NONCODE_G(String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByExpression_NONCODE_G(lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByExpression_N(String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByExpression_N(lncnames);

			return list;
		}	
		@Override
		public List<HashMap<String,String>> queryByExpression_TCGA(String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByExpression_TCGA(lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByExpression_GTEX(String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByExpression_GTEX(lncnames);

			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByExpression_CCLE(String lncnames){
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByExpression_CCLE(lncnames);

			return list;
		}
//		  ####################################### browse ҳ����  ############################################	
//		  ############# ���в���Ϊ��  #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# source��Ϊ��  �������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_1(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_1(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_1(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_1(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_1(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_1(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_1(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_1(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_1(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_1(source,Gene_type,region,CHR);

			return list;
		}
		
//		  ############# source��Ϊ�ա�Gene_type��Ϊ��    �������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_2(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_2(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_2(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_2(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_2(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_2(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_2(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_2(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_2(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_2(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# source��Ϊ�ա�Gene_type��Ϊ�� ��region��Ϊ��   �������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_3(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_3(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_3(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_3(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_3(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_3(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_3(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_3(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_3(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_3(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# source��Ϊ�ա�Gene_type��Ϊ�� ��region��Ϊ�� ��CHR��Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_4(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_4(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_4(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_4(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_4(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_4(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_4(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_4(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_4(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_4(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# Gene_type��Ϊ�� ���������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_5(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_5(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_5(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_5(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_5(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_5(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_5(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_5(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_5(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_5(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# Gene_type��Ϊ�� ��region��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_6(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_6(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_6(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_6(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_6(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_6(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_6(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_6(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_6(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_6(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# Gene_type��Ϊ�� ��region��Ϊ�ա�CHR��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_7(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_7(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_7(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_7(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_7(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_7(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_7(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_7(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_7(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_7(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# region��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_8(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_8(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_8(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_8(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_8(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_8(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_8(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_8(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_8(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_8(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# region��Ϊ�ա�CHR��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_9(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_9(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_9(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_9(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_9(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_9(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_9(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_9(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_9(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_9(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# CHR��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_10(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_10(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_10(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_10(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_10(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_10(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_10(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_10(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_10(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_10(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# source��Ϊ�ա�region��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_11(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_11(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_11(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_11(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_11(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_11(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_11(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_11(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_11(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_11(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# source��Ϊ�ա�CHR��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_12(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_12(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_12(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_12(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_12(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_12(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_12(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_12(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_12(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_12(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# Gene_type��Ϊ�ա�CHR��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_13(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_13(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_13(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_13(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_13(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_13(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_13(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_13(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_13(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_13(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# source��Ϊ�ա�region��Ϊ�ա�CHR��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_14(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_14(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_14(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_14(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_14(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_14(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_14(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_14(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_14(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_14(source,Gene_type,region,CHR);

			return list;
		}
//		  ############# source��Ϊ�ա�Gene_type��Ϊ�ա�CHR��Ϊ�ա��������Ϊ�� #############
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBrowse_15(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBrowse_15(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryBySource_15(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryBySource_15(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByGene_type_15(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByGene_type_15(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByregion_15(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByregion_15(source,Gene_type,region,CHR);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByCHR_15(String source,String Gene_type,String region,String CHR){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByCHR_15(source,Gene_type,region,CHR);

			return list;
		}
//		  ####################################### browse ҳ��������  #########################################			
//		  ####################################### download ҳ���� #########################################
//		   �������1
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������2
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_1(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_1(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_1(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_1(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_1(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_1(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������3
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_2(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_2(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_2(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_2(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_2(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_2(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������4
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_3(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_3(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_3(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_3(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_3(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_3(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������5
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_4(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_4(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_4(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_4(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_4(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_4(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������6
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_5(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_5(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_5(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_5(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_5(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_5(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������7
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_6(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_6(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_6(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_6(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_6(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_6(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������8
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_7(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_7(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_7(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_7(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_7(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_7(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������9
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_8(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_8(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_8(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_8(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_8(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_8(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������10
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_9(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_9(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_9(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_9(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_9(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_9(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������11
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_10(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_10(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_10(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_10(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_10(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_10(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������12
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_11(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_11(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_11(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_11(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_11(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_11(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������13
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_12(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_12(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_12(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_12(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_12(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_12(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������14
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_13(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_13(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_13(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_13(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_13(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_13(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������15
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_14(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_14(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_14(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_14(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_14(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_14(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		   �������16
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByDownload_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByDownload_15(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByData_source_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByData_source_15(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByElement_class_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByElement_class_15(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_type_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_type_15(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
		@Override
		public List<HashMap<HashMap<String,String>,HashMap<String,String>>> queryByBiosample_name_15(String Data_source,String Element_class,String Biosample_type,String Biosample_name){
			List<HashMap<HashMap<String,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByBiosample_name_15(Data_source,Element_class,Biosample_type,Biosample_name);

			return list;
		}
//		  ####################################### download ҳ��������  #########################################		
		@Override
		public List<String> querytftype() {
			List<String>list=null;
			list = searchMapper.querytftype();
			return list;
		}
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze(String pro_tablename,String other_tablename,String promoterRegion,String lncRNA,String tftype) {
			List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByRAnalyze(pro_tablename,other_tablename,promoterRegion,lncRNA,tftype);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByRAnalyze_chr(String lncRNA) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByRAnalyze_chr(lncRNA);
			
			return list;
		}
		@Override
		public List<HashMap<String,HashMap<String,String>>> queryByRresult_left(String source,String promoterRegion,String lncRNA) {
			List<HashMap<String,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByRresult_left(source,promoterRegion,lncRNA);
			
			return list;
		}
		@Override
		public List<HashMap<String,String>> queryByRresult_left_A(String promoterRegion, String lncRNA) {
			List<HashMap<String,String>> list=null;
		
				list = searchMapper.queryByRresult_left_A(promoterRegion,lncRNA);
			
			return list;
		}
		
		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze_node(String pro_tablename,String other_tablename,String promoterRegion,String lncRNA,String tftype) {
			List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByRAnalyze_node(pro_tablename,other_tablename,promoterRegion,lncRNA,tftype);
			
			return list;
		}

		@Override
		public List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> queryByRAnalyze_edge(String pro_tablename,String other_tablename,String promoterRegion,String lncRNA,String tftype) {
			List<HashMap<HashMap<HashMap<String,String>,String>,HashMap<String,String>>> list=null;
		
				list = searchMapper.queryByRAnalyze_edge(pro_tablename,other_tablename,promoterRegion,lncRNA,tftype);
			
			return list;
		}

		@Override
		public void saveUserEmail(UserEmail userEmail) {
			  searchMapper.insertUserEmail(userEmail);
		}
		
		@Override
		public String getSnpDisease(String set) {
			return searchMapper.selectSnpDisease(set);
		}

}
