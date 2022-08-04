package com.lyy.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.lyy.Ranalyse.Qipao_data;
import com.lyy.Ranalyse.RAnalyze;
import com.lyy.Ranalyse.RAnalyze_data;
import com.lyy.Ranalyse.RAnalyze_lola;
import com.lyy.datatable.datatableResult;
import com.lyy.model.UserEmail;
//import com.lyy.datatable.oloList;
import com.lyy.service.SearchService;
import com.lyy.util.FileToList;
import com.lyy.util.File_form;
import com.lyy.util.File_read;
import com.lyy.util.File_read_limit;
import com.lyy.util.File_upload;
import com.lyy.util.Linux_java;
import com.lyy.util.List_change;
import com.lyy.util.NumberUtil;
import com.lyy.util.ReadFile;
import com.lyy.util.datachuli;

@Controller
public class SearchController {
	@Autowired
	SearchService searchService;

	// ####################### Total Search #######################
	@RequestMapping("/search/Data_Type")
	@ResponseBody
	public List<String> queryData_Type() {
		List list = null;
		list = searchService.queryData_Type();
		return list;
	}

	@RequestMapping("/search/Tissue_Name")
	@ResponseBody
	public List<String> queryTissue_Name(String Data_Type) {
		List list = null;
		list = searchService.queryTissue_Name(Data_Type);
		return list;
	}

	@RequestMapping("/search/set")
	public String queryBysearch_set(String genome, String datatype, String dataset, HttpServletRequest request,
			HttpSession session, Model model) {
		session.setAttribute("genome", genome);
		List list = null;
		list = searchService.queryBybrowselist_3(datatype, dataset, "", "browselist");
		model.addAttribute("list", list);
		return "Search_set_result";
	}

	@RequestMapping("/search/region")
	public String queryBysearch_region(String genome, String datatype, String dataset, String chr, String start,
			String end, HttpServletRequest request, HttpSession session, Model model) throws IOException {
		session.setAttribute("genome", genome);
		String region = chr + "\t" + start + "\t" + end;
		String randomName = UUID.randomUUID().toString();
		String judge19or38 = StringUtils.equals(genome, "hg19") ? "19" : "";
		String file_search = File_form.formation("/mnt/data/GREAP" + judge19or38 + "/Search/", randomName, ".bed",
				region);
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP" + judge19or38
				+ "/" + datatype + "/" + dataset + ".bed -b /mnt/data/GREAP" + judge19or38 + "/Search/" + file_search
				+ " -wa -wb -F 0.1 -bed > /mnt/data/GREAP" + judge19or38 + "/Search/result/" + file_search);
		List<String> listsearch = File_read.read("/mnt/data/GREAP" + judge19or38 + "/Search/result/" + file_search);
//		List<String> listsearch = File_read.read("E:/Rtest/test.bed");
		if (listsearch.size() == 0) {
			return "Search_set_result";
		}
		ArrayList<String> String1 = new ArrayList<String>();
		for (int i = 0; i < listsearch.size(); i++) {
			String1.add(listsearch.get(i).split("\t")[listsearch.get(i).split("\t").length - 4]);
		}
		// ******** 閸樺鍣� ***********
		LinkedHashSet<String> set = new LinkedHashSet<>(String1);
		Gson gsonnew = new Gson();
		String setnew = gsonnew.toJson(set).replace("[", "").replace("]", "");
		List list = null;
		list = searchService.queryBysearch_region(setnew);
		model.addAttribute("list", list);

		return "Search_set_result";
	}

	@RequestMapping("/search/gene")
	public String queryBysearch_gene(String genome, String datatype, String dataset, String gene,
			HttpServletRequest request, HttpSession session, Model model) throws IOException {
		session.setAttribute("genome", genome);
		List list_region = null;
		list_region = searchService.queryBysearch_gene(gene);
		if (list_region.size() == 0) {
			return "Search_set_result";
		}
		String chr = list_region.get(0).toString().split(",")[2].split("=")[1].replace("}", "");
		String start = list_region.get(0).toString().split(",")[0].split("=")[1];
		String end = list_region.get(0).toString().split(",")[1].split("=")[1];
		String region = chr + "\t" + start + "\t" + end;
		String randomName = UUID.randomUUID().toString();
		String judge19or38 = StringUtils.equals(genome, "hg19") ? "19" : "";
		String file_search = File_form.formation("/mnt/data/GREAP" + judge19or38 + "/Search/", randomName, ".bed",
				region);
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP" + judge19or38
				+ "/" + datatype + "/" + dataset + ".bed -b /mnt/data/GREAP" + judge19or38 + "/Search/" + file_search
				+ " -wa -wb -F 0.1 -bed > /mnt/data/GREAP" + judge19or38 + "/Search/result/" + file_search);
		List<String> listsearch = File_read.read("/mnt/data/GREAP" + judge19or38 + "/Search/result/" + file_search);
//		List<String> listsearch = File_read.read("E:/Rtest/test.bed");
		if (listsearch.size() == 0) {
			return "Search_set_result";
		}
		ArrayList<String> String1 = new ArrayList<String>();
		for (int i = 0; i < listsearch.size(); i++) {
			String1.add(listsearch.get(i).split("\t")[listsearch.get(i).split("\t").length - 4]);
		}
		// ******** 閸樺鍣� ***********
		LinkedHashSet<String> set = new LinkedHashSet<>(String1);
		Gson gsonnew = new Gson();
		String setnew = gsonnew.toJson(set).replace("[", "").replace("]", "");
		List list = null;
		list = searchService.queryBysearch_region(setnew);
		model.addAttribute("list", list);
		return "Search_set_result";
	}

	@RequestMapping("/download")
	public String querydownload(String set, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws Exception {
		List list = null;
		list = searchService.querydownload(set);
		model.addAttribute("list", list);
		return "Download_list";
	}

	// ######################################################################################
	@RequestMapping("/analysis")
	public String queryanalysis(String olo, String source, String input, MultipartFile file, MultipartFile file3,
			MultipartFile file4, MultipartFile file5, MultipartFile file6, MultipartFile file7, String proportion,
			String methodForm, String analysisForm, String Data_Type, String Tissue_Name, String outputForm,
			String hmm_class, String tf_class, String tf_class2, String histone_class, String tcof_class,
			String tcof_class2, String atac_class, String enhancer_class, String se_class, String snp_class,
			String methylation_class, String lnc_class, String mic_class, String m_class, String eqtl_class,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, String min,
			String max, String pValue, String radiotf, String radiotcof, String grch, String lolaRadio,
			MultipartFile lolaFile, String judge_email, String userEmail, String userId) throws Exception {
//      姣忎釜鐢ㄦ埛鐨処D鍙� 
//    	ln -s /mnt/data/GREAP/User/ /home/tomcat/apache-tomcat-9.0.12/webapps/Greap_download/pdf
		String randomName = UUID.randomUUID().toString();
		System.out.println(radiotcof + "------------------------------");
		Linux_java Linux = new Linux_java();
		// 判断是 19 还是 38 的路径
		String name19or38 = StringUtils.equals("1", grch) ? "GREAP" : "GREAP19";
		String genome = StringUtils.equals("1", grch) ? "hg38" : "hg19";
		String pre_file_path = "/mnt/data/" + name19or38;
		Linux.getExecCommand("mkdir -p " + pre_file_path + "/User/" + randomName);
		String file_path = pre_file_path + "/User/" + randomName + "/";
		String file_name = "";
		String universe = "";

		System.out.println(file_path);

//      鐢ㄦ埛鎻愪氦鍖哄煙
		if (source.equals("0")) {
			file_name = File_form.formation(file_path, "user", ".bed", input);
		} else {
			file_name = File_upload.upload(file_path, "user", ".bed", file);
		}
		// LOLA 生成文件判断
		if (lolaRadio.equals("0")) {
			universe = "'" + pre_file_path + "/activeDHS_universe.bed'";
		} else {
			String lola_file_name = File_upload.upload(file_path, "user_universe", ".bed", lolaFile);
			universe = "'" + file_path + lola_file_name + "'";
		}

		List<String> content = File_read.read(file_path + file_name);
		int userLineLength = content.get(0).split("\t").length;

// 第三种方法
//        String olofile3 = "";
//        String olofile4 = "";
//        String olofile5 = "";
//        String olofile6 = "";
//        String olofile7 = "";
//        if(olo.equals("olo2")) {
//        	session.setAttribute("olo", "olo2");
//        	if(!file3.isEmpty()) {
//            	olofile3 = File_upload.upload(file_path, "olofile3", ".bed", file3);
//            	session.setAttribute("olofile3", "yes");
//            }else session.setAttribute("olofile3", "no");
//            if(!file4.isEmpty()) {
//            	olofile4 = File_upload.upload(file_path, "olofile4", ".bed", file4);
//            	session.setAttribute("olofile4", "yes");
//            }else session.setAttribute("olofile4", "no");
//            if(file5!=null) {
//            	if(!file5.isEmpty()) {
//                	olofile5 = File_upload.upload(file_path, "olofile5", ".bed", file5);
//                	session.setAttribute("olofile5", "yes");
//                }else session.setAttribute("olofile5", "no");
//            }
//            if(file6!=null) {
//            	if(!file6.isEmpty()) {
//                	olofile6 = File_upload.upload(file_path, "olofile6", ".bed", file6);
//                	session.setAttribute("olofile6", "yes");
//                }else session.setAttribute("olofile6", "no");
//            }
//            if(file7!=null) {
//            	if(!file7.isEmpty()) {
//                	olofile7 = File_upload.upload(file_path, "olofile7", ".bed", file7);
//                	session.setAttribute("olofile7", "yes");
//                }else session.setAttribute("olofile7", "no");
//            }
//            
//        }else {
//        	session.setAttribute("olo", "olo1");
//        };

		session.setAttribute("randomName", randomName);
		session.setAttribute("name19or38", name19or38);
		session.setAttribute("genome", genome);
		session.setAttribute("file_path", file_path);
		session.setAttribute("proportion", proportion);
		session.setAttribute("outputForm", outputForm);
		session.setAttribute("methodForm", methodForm);
		session.setAttribute("min", min);
		session.setAttribute("max", max);
		session.setAttribute("pValue", pValue);
		session.setAttribute("radiotf", radiotf);
		session.setAttribute("radiotcof", radiotcof);
		session.setAttribute("universe", universe);
		session.setAttribute("judge_email", judge_email);
		session.setAttribute("userEmail", userEmail);
		session.setAttribute("userId", userId);
		session.setAttribute("userLineLength", userLineLength);
		
		System.out.println(userLineLength);
		
		if (radiotf.equals("2")) {
			tf_class = tf_class2;
		}
		if (radiotcof.equals("2")) {
			tcof_class = tcof_class2;
		}

		if (analysisForm.equals("1")) {
			if (hmm_class != null) {
				ArrayList<List_change> list_hmm = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < hmm_class.split(",").length; i++) {
					String1.add(hmm_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < hmm_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_hmm.add(list_change);
				}
				model.addAttribute("list_hmm", list_hmm);
			}
			if (tf_class != null) {
				ArrayList<List_change> list_tf = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < tf_class.split(",").length; i++) {
					String1.add(tf_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < tf_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_tf.add(list_change);
				}
				model.addAttribute("list_tf", list_tf);
			}
			if (tcof_class != null) {
				ArrayList<List_change> list_tcof = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < tcof_class.split(",").length; i++) {
					String1.add(tcof_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < tcof_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_tcof.add(list_change);
				}
				model.addAttribute("list_tcof", list_tcof);
			}
			if (histone_class != null) {
				ArrayList<List_change> list_histone = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < histone_class.split(",").length; i++) {
					String1.add(histone_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < histone_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_histone.add(list_change);
				}
				model.addAttribute("list_histone", list_histone);
			}
			if (atac_class != null) {
				ArrayList<List_change> list_atac = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < atac_class.split(",").length; i++) {
					String1.add(atac_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < atac_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_atac.add(list_change);
				}
				model.addAttribute("list_atac", list_atac);
			}
			if (lnc_class != null) {
				ArrayList<List_change> list_lnc = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < lnc_class.split(",").length; i++) {
					String1.add(lnc_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < lnc_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_lnc.add(list_change);
				}
				model.addAttribute("list_lnc", list_lnc);
			}
			if (enhancer_class != null) {
				ArrayList<List_change> list_enhancer = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < enhancer_class.split(",").length; i++) {
					String1.add(enhancer_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < enhancer_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_enhancer.add(list_change);
				}
				model.addAttribute("list_enhancer", list_enhancer);
			}
			if (se_class != null) {
				ArrayList<List_change> list_se = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < se_class.split(",").length; i++) {
					String1.add(se_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < se_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_se.add(list_change);
				}
				model.addAttribute("list_se", list_se);
			}
			if (snp_class != null) {
				ArrayList<List_change> list_snp = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < snp_class.split(",").length; i++) {
					String1.add(snp_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < snp_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_snp.add(list_change);
				}
				model.addAttribute("list_snp", list_snp);
			}
			if (eqtl_class != null) {
				ArrayList<List_change> list_eqtl = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < eqtl_class.split(",").length; i++) {
					String1.add(eqtl_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < eqtl_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_eqtl.add(list_change);
				}
				model.addAttribute("list_eqtl", list_eqtl);
			}
			if (methylation_class != null) {
				ArrayList<List_change> list_methylation = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < methylation_class.split(",").length; i++) {
					String1.add(methylation_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < methylation_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_methylation.add(list_change);
				}
				model.addAttribute("list_methylation", list_methylation);
			}
			if (mic_class != null) {
				ArrayList<List_change> list_mic = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < mic_class.split(",").length; i++) {
					String1.add(mic_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < mic_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_mic.add(list_change);
				}
				model.addAttribute("list_mic", list_mic);
			}
			if (m_class != null) {
				ArrayList<List_change> list_m = new ArrayList<List_change>();
				ArrayList<String> String1 = new ArrayList<String>();
				for (int i = 0; i < m_class.split(",").length; i++) {
					String1.add(m_class.split(",")[i]);
				}
				List_change list_change = null;

				for (int i = 0; i < m_class.split(",").length; i++) {
					list_change = new List_change();
					list_change.setList_change(String1.get(i));
					list_m.add(list_change);
				}
				model.addAttribute("list_m", list_m);
			}
		} else if (analysisForm.equals("3")) {

			if (hmm_class != null) {

				model.addAttribute("hmm_class", hmm_class);
			}
			if (tf_class != null) {

				model.addAttribute("tf_class", tf_class);
			}
			if (tcof_class != null) {

				model.addAttribute("tcof_class", tcof_class);
			}
			if (histone_class != null) {

				model.addAttribute("histone_class", histone_class);
			}
			if (atac_class != null) {

				model.addAttribute("atac_class", atac_class);
			}
			if (lnc_class != null) {

				model.addAttribute("lnc_class", lnc_class);
			}
			if (enhancer_class != null) {

				model.addAttribute("enhancer_class", enhancer_class);
			}
			if (se_class != null) {

				model.addAttribute("se_class", se_class);
			}
			if (snp_class != null) {

				model.addAttribute("snp_class", snp_class);
			}
			if (eqtl_class != null) {

				model.addAttribute("eqtl_class", eqtl_class);
			}
			if (methylation_class != null) {

				model.addAttribute("methylation_class", methylation_class);
			}
			if (mic_class != null) {

				model.addAttribute("mic_class", mic_class);
			}
			if (m_class != null) {

				model.addAttribute("m_class", m_class);
			}
		} else {
			if (Data_Type.equals("ChromHMM")) {
				ArrayList<List_change> list_hmm = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_hmm.add(list_change);

				model.addAttribute("list_hmm", list_hmm);
			}
			if (Data_Type.equals("TF")) {
				ArrayList<List_change> list_tf = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_tf.add(list_change);

				model.addAttribute("list_tf", list_tf);
			}
			if (Data_Type.equals("TcoF")) {
				ArrayList<List_change> list_tcof = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_tcof.add(list_change);

				model.addAttribute("list_tcof", list_tcof);
			}
			if (Data_Type.equals("Histone")) {
				ArrayList<List_change> list_histone = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_histone.add(list_change);

				model.addAttribute("list_histone", list_histone);
			}
			if (Data_Type.equals("ATAC")) {
				ArrayList<List_change> list_atac = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_atac.add(list_change);

				model.addAttribute("list_atac", list_atac);
			}
			if (Data_Type.equals("LncRNA")) {
				ArrayList<List_change> list_lnc = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_lnc.add(list_change);

				model.addAttribute("list_lnc", list_lnc);
			}
			if (Data_Type.equals("Enhancer")) {
				ArrayList<List_change> list_enhancer = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_enhancer.add(list_change);

				model.addAttribute("list_enhancer", list_enhancer);
			}
			if (Data_Type.equals("Super_Enhancer")) {
				ArrayList<List_change> list_se = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_se.add(list_change);

				model.addAttribute("list_se", list_se);
			}
			if (Data_Type.equals("SNP")) {
				ArrayList<List_change> list_snp = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_snp.add(list_change);

				model.addAttribute("list_snp", list_snp);
			}
			if (Data_Type.equals("eQTL")) {
				ArrayList<List_change> list_eqtl = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_eqtl.add(list_change);

				model.addAttribute("list_eqtl", list_eqtl);
			}
			if (Data_Type.equals("Methylation")) {
				ArrayList<List_change> list_methylation = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_methylation.add(list_change);

				model.addAttribute("list_methylation", list_methylation);
			}
			if (Data_Type.equals("mRNA")) {
				ArrayList<List_change> list_m = new ArrayList<List_change>();

				List_change list_change = null;

				list_change = new List_change();
				list_change.setList_change(Tissue_Name);
				list_m.add(list_change);

				model.addAttribute("list_m", list_m);
			}
		}
//        
//        if (analysisForm.equals("3")) {
//        	model.addAttribute("oloselect", olo);
//        	
//			return "analysis_ologram_result";
//		}else {
//		}
		return "analysis_result";
	}

	@RequestMapping("/analysis/overlap")
	public String queryanalysis_overlap(String datatype, String subset, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {
		session.setAttribute("overlap_datatype", datatype);
		session.setAttribute("overlap_subset", subset);

		String file_path = (String) session.getAttribute("file_path");
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(file_path + "user.bed")));
//		LineNumberReader lnr = new LineNumberReader(new FileReader(new File("E:/Rtest/user.bed"))); 
		lnr.skip(Long.MAX_VALUE);
		lnr.close();

		int n = lnr.getLineNumber() + 1;
		model.addAttribute("n", n);

		model.addAttribute("class", datatype);
		model.addAttribute("subset", subset);

		return "analysis_result_list";
	}

//    @RequestMapping("/analysis/ologram")
//	public String queryanalysis_ologram(String datatype, String subset, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception {
//    	System.out.print("hello world");
//    	String file_path_bg = "/mnt/data/GREAP/";
//		String file_path = (String) session.getAttribute("file_path");
//		String olo = (String) session.getAttribute("olo");
//		
//		String input_file = file_path + "user.bed";
//		String[] strarr = subset.split(",");
//		String input_select = "";
//		for(int i=0;i<strarr.length;i++) {
//			String forselect = file_path_bg+datatype+"/"+strarr[i]+".bed ";
//			input_select += forselect;
//		}
//		String out = UUID.randomUUID().toString();
//		String common = "";
//		if(olo.equals("olo1")) {
//			common = "/usr/local/bin/gtftk ologram -z -w -q -c /mnt/data/GREAP/size.chromInfo -p "+ input_file +" --more-bed "+ input_select +"--more-bed-multiple-overlap -o "+file_path+out;
//		}else {
//			input_select = "";
//			String olofile3 = (String) session.getAttribute("olofile3");
//			String olofile4 = (String) session.getAttribute("olofile4");
//			String olofile5 = (String) session.getAttribute("olofile5");
//			String olofile6 = (String) session.getAttribute("olofile6");
//			String olofile7 = (String) session.getAttribute("olofile7");
//			if(olofile3.equals("yes")) {
//				String selectfile = file_path+"olofile3.bed ";
//				input_select += selectfile;
//			}
//			if(olofile4.equals("yes")) {
//				String selectfile = file_path+"olofile4.bed ";
//				input_select += selectfile;			
//			}
//			if(olofile5!=null) {
//				if(olofile5.equals("yes")) {
//					String selectfile = file_path+"olofile5.bed ";
//					input_select += selectfile;
//				}
//			}
//			if(olofile6!=null) {
//				if(olofile6.equals("yes")) {
//					String selectfile = file_path+"olofile6.bed ";
//					input_select += selectfile;
//				}
//			}
//			if(olofile7!=null) {
//				if(olofile7.equals("yes")) {
//					String selectfile = file_path+"olofile7.bed ";
//					input_select += selectfile;
//				}
//			}
//			
//			common = "/usr/local/bin/gtftk ologram -z -w -q -c /mnt/data/GREAP/size.chromInfo -p "+ input_file +" --more-bed "+ input_select +"--more-bed-multiple-overlap -o "+file_path+out;
//		}
//		
//		session.setAttribute("common", common);
//		session.setAttribute("class", datatype);
//		session.setAttribute("subset", subset);
//		session.setAttribute("out", out);
//		session.setAttribute("file_path", file_path);
//	
//		System.out.println("common::::::::"+common);
//		return "analysis_olo_result_list";
//	}

//    @RequestMapping("/analysis/olotable")
//    @ResponseBody
//    public void olotable(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception {
//    	String common = (String) session.getAttribute("common");
//    	String file_path = (String) session.getAttribute("file_path");
//    	String out = (String) session.getAttribute("out");
//    	String datatype = (String) session.getAttribute("class");
//    	String subset = (String) session.getAttribute("subset");
//    	String randomName = (String) session.getAttribute("randomName");
//    	System.out.println(out);
//    	Linux_java Linux = new Linux_java();
//		Linux.getExecCommand(common);
//		Linux.getExecCommand("mv "+file_path+out+"/*.tsv "+file_path+out+"/result.tsv");
//		Linux.getExecCommand("mv "+file_path+out+"/*.pdf "+file_path+out+"/result.pdf");
//		System.out.println("mv "+file_path+"ologram_output/*.tsv "+file_path+out+"/result.tsv");
//		List<String> content = File_read.read(file_path+out+"/result.tsv");
//		List<oloList> list = new ArrayList<>();
//		for(int i=1;i<content.size();i++) {
//			String[] str = content.get(i).split("\t");
//			oloList olo = new oloList();
//			olo.setStr1(str[0]);
//			olo.setStr2(str[1]);
//			olo.setStr3(str[2]);
//			olo.setStr4(str[3]);
//			olo.setStr5(str[4]);
//			olo.setStr6(str[5]);
//			olo.setStr7(str[6]);
//			olo.setStr8(str[7]);
//			olo.setStr9(str[8]);
//			olo.setStr10(str[9]);
//			olo.setStr11(str[10]);
//			olo.setStr12(str[11]);
//			olo.setStr13(str[12]);
//			olo.setStr14(str[13]);
//			olo.setStr15(str[14]);
//			olo.setStr16(str[15]);
//			olo.setStr17(str[16]);
//			list.add(olo);
//		}
//		Map map = new HashMap();
//        map.put("data", list);
//        map.put("pdf", randomName+"/"+out+"/result.pdf");
//        Gson gsonnew = new Gson();
//        String json = gsonnew.toJson(map);
//        System.out.println("json:"+json);
//        PrintWriter outw = response.getWriter();
//        outw.print(json);
//        outw.close();
//    }

	@RequestMapping("/email/result")
	public void email_result(String userId, String method, String hg, String min, String max, String intPvalue,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception {

		String filename = "1".equals(method) ? "result_hyp.txt" : "lola_result.txt";
		List<String> content = File_read.read("/mnt/data/" + hg +"/User/" + userId + "/" + filename);

//		List<String> content = File_read.read("D:/" + filename);

		int size = content.size();

		List<FileToList> listR = new ArrayList<FileToList>();

		if (method.equals("1")) {

			FileToList fileToList = null;

			for (int i = 1; i < size; i++) {
				String[] split = content.get(i).split("\t");
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, intPvalue, split[4], split[7])) {
					fileToList = new FileToList();
					fileToList.setString1(split[0]);
					fileToList.setString2(split[1]);
					fileToList.setString3(split[2]);
					fileToList.setString5(split[3]);
					// pValue
					fileToList.setString4(split[4]);
					fileToList.setString6(split[5]);
					// string7 Num
					fileToList.setString7(split[6]);
					fileToList.setString8(split[7]);
					listR.add(fileToList);
				}
			}

			// 筛选
			List<String> String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			List<String> String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			List<String> String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());

		} else if (method.equals("2")) {

			FileToList fileToList = null;
			for (int i = 1; i < size; i++) {
				String[] split = content.get(i).split("\t");
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, intPvalue, split[0], split[6])) {
					fileToList = new FileToList();
					fileToList.setString1(split[0]);
					fileToList.setString2(split[1]);
					fileToList.setString3(split[2]);
					fileToList.setString5(split[3]);
					// pValue
					fileToList.setString4(split[4]);
					fileToList.setString6(split[5]);
					// string7 Num
					fileToList.setString7(split[6]);
					fileToList.setString8(split[7]);
					fileToList.setString9(split[8]);
					listR.add(fileToList);
				}
			}
			// 筛选
			List<String> String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			List<String> String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			List<String> String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type("" + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String1.size(); i++) {
				stringname = "" + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();

	}

	@RequestMapping("/email/subTable")
	@ResponseBody
	public List<FileToList> emailSubTable(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model,
			@RequestParam String set, @RequestParam String userId, @RequestParam String datatype, @RequestParam String hg) {
		String file_path = "/mnt/data/" + hg +"/User/" + userId + "/";

		List<String> content = null;
		if (datatype.equals("ChromHMM")) {
			content = File_read.read(file_path + "hmm_bedtools.bed");
		} else if (datatype.equals("mRNA")) {
			content = File_read.read(file_path + "mRNA_bedtools.bed");
		} else if (datatype.equals("LncRNA")) {
			content = File_read.read(file_path + "lncRNA_bedtools.bed");
		} else {
			content = File_read.read(file_path + datatype.toLowerCase() + "_bedtools.bed");
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>> subTable start: content >>>>>>>>>>>>>>>>>>>>>");
		Integer size = content.size();
		if (size <= 0) {
			return Collections.emptyList();
		}

		int index = 0;
		// 判断索引
		switch (datatype) {
		case "TF":
		case "TcoF":
		case "Histone":
		case "ATAC":
			index = 3;
			break;
		case "Enhancer":
		case "Super_Enhancer":
		case "SNP":
		case "eQTL":
		case "Methylation":
			index = 4;
			break;
		case "ChromHMM":
			index = 5;
			break;
		case "mRNA":
		case "LncRNA":
			index = 6;
			break;
		default:
			throw new RuntimeException("datatype 获取失败...");
		}

		List<FileToList> info = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			String oneLine = content.get(i);
			String[] split = oneLine.split("\t");

			if (split.length == 0) {
				continue;
			}
			int new_index = index;
			
			if ("ENdb".equals(set)) {
				new_index = 5;
			}
			
			int length = split.length - new_index - 4;
			if (StringUtils.equals(set, split[index])) {
				FileToList fileToList = new FileToList();

				fileToList.setString1(split[new_index + 1] + ":" + split[new_index + 2] + "-" + split[new_index + 3]);
				
				fileToList.setString2(split[0] + ":" + split[1] + "-" + split[2]);
				
				if (length != 0) {
					if (length >= 1) {
						fileToList.setString3(split[new_index + 4]);
					}
					if (length >= 2) {
						fileToList.setString4(split[new_index + 5]);
					}
					if (length >= 3) {
						fileToList.setString5(split[new_index + 6]);
					}
					if (length >= 4) {
						fileToList.setString6(split[new_index + 7]);
					}
					if (length >= 5) {
						fileToList.setString7(split[new_index + 8]);
					}
					if (length >= 6) {
						fileToList.setString8(split[new_index + 9]);
					}
					if (length >= 7) {
						fileToList.setString9(split[new_index + 10]);
					}
					if (length >= 8) {
						fileToList.setString10(split[new_index + 11]);
					}
					if (length >= 9) {
						fileToList.setString11(split[new_index + 12]);
					}
				}
				info.add(fileToList);
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>> subTable end: info >>>>>>>>>>>>>>>>>>>>>");
		return info;
	}

	@RequestMapping("/analysis/ChromHMM")
	@ResponseBody
	public void queryanalysis_hmm(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		// 都需要传入 email 中
		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");
		// 都需要传入 email 中
		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		System.out.println(datatype);
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		System.out.println("fasfaf");
		String bedFile = "";

		System.out.println(userLineLength);
		
		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ChromHMM/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ChromHMM/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
						+ proportion + " -bed > " + file_path + "hmm_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ChromHMM/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ChromHMM/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -bed > " + file_path + "hmm_bedtools.bed");

			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ChromHMM/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ChromHMM/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -f " + proportion + " -bed > " + file_path + "hmm_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "hmm_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/ChromHMM/R/HMM_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/ChromHMM/R/ChromHMM.txt'";
			String step4 = "'" + file_path + "hmm_bedtools.bed'";
			bedFile = step4;

//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'HMM_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'ChromHMM.txt'";
//	        String step4 = "'hmm_bedtools.bed'";

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/ChromHMM/R/HMM.r\")");
//			conn.eval("source(\"E:/Rtest/HMM/HMM.r\")");
			REXP eval = conn.eval(
					"hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4 + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();

			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());

		}

		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'HMM19'" : "'HMM'";
			String overlap_path = "'" + file_path + "'";
//			String input = "'E:/Rtest/LOLA/user.bed'";
//			String universe = "'E:/Rtest/LOLA/activeDHS_universe.bed'";
//	        String dataclass = "'HMM'";
//	        String overlap_path = "'E:/Rtest/LOLA/test/'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/ChromHMM/R/LOLA.r\")");
//			conn.eval("source(\"E:/Rtest/LOLA/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String1.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/TF")
	@ResponseBody
	public void queryanalysis_tf(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");
		String radiotf = (String) session.getAttribute("radiotf");
		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				if (radiotf.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF2/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
							+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
							+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				}
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				if (radiotf.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF2/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
							+ proportion + " -bed > " + file_path + "tf_bedtools.bed");
				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
							+ proportion + " -bed > " + file_path + "tf_bedtools.bed");
				}
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/TF/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f " + proportion
						+ " -bed > " + file_path + "tf_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				if (radiotf.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF2/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
							+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
							+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				}
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				if (radiotf.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF2/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
							+ proportion + " -bed > " + file_path + "tf_bedtools.bed");
				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
							+ proportion + " -bed > " + file_path + "tf_bedtools.bed");
				}

			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				if (radiotf.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF2/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
							+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
							+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				}
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				if (radiotf.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF2/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
							+ proportion + " -f " + proportion + " -bed > " + file_path + "tf_bedtools.bed");
				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TF/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
							+ proportion + " -f " + proportion + " -bed > " + file_path + "tf_bedtools.bed");
				}

			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "tf_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String num = "";
			String set_num = "";
			if (radiotf.equals("2")) {
				num = "'/mnt/data/" + name19or38 + "/TF2/R/TF_intersect_universe_NUM.txt'";
				set_num = "'/mnt/data/" + name19or38 + "/TF2/R/TF.txt'";
			} else {
				num = "'/mnt/data/" + name19or38 + "/TF/R/TF_intersect_universe_NUM.txt'";
				set_num = "'/mnt/data/" + name19or38 + "/TF/R/TF.txt'";
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";

			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";

			String step4 = "'" + file_path + "tf_bedtools.bed'";
			bedFile = step4;

//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'TF_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'TF.txt'";
//	        String step4 = "'tf_bedtools.bed'";

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("filename", file_path + "result_hyp.txt");
			if (radiotf.equals("2")) {
				conn.eval("source(\"/mnt/data/" + name19or38 + "/TF2/R/TF.r\")");
			} else {
				conn.eval("source(\"/mnt/data/" + name19or38 + "/TF/R/TF.r\")");
			}

//			conn.eval("source(\"E:/Rtest/TF/TF.r\")");
			REXP eval = conn.eval(
					"hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4 + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();

			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));

				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String is1or2 = radiotf.equals("2") ? "2" : "";
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'TF" + is1or2 + "19'"
					: "'TF" + is1or2 + "'";
			System.out.println("dataclass: " + dataclass);
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			if (radiotf.equals("2")) {
				conn.eval("source(\"/mnt/data/" + name19or38 + "/TF2/R/LOLA.r\")");
			} else {
				conn.eval("source(\"/mnt/data/" + name19or38 + "/TF/R/LOLA.r\")");
			}

			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}

			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					System.out.println(fileToList);
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}

			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();

			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/TcoF")
	@ResponseBody
	public void queryanalysis_tcof(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");
		String radiotcof = (String) session.getAttribute("radiotcof");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				if (radiotcof.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF2/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
							+ " |sort |uniq> " + file_path + "user_intersect_set.txt");

				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
							+ " |sort |uniq> " + file_path + "user_intersect_set.txt");

				}
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				if (radiotcof.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF2/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
							+ proportion + " -bed > " + file_path + "tcof_bedtools.bed");

				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
							+ proportion + " -bed > " + file_path + "tcof_bedtools.bed");

				}
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				if (radiotcof.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF2/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
							+ " |sort |uniq> " + file_path + "user_intersect_set.txt");

				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
							+ " |sort |uniq> " + file_path + "user_intersect_set.txt");

				}
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				if (radiotcof.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF2/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
							+ proportion + " -bed > " + file_path + "tcof_bedtools.bed");

				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
							+ proportion + " -bed > " + file_path + "tcof_bedtools.bed");

				}

			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				if (radiotcof.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF2/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
							+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");

				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
							+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");

				}
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				if (radiotcof.equals("2")) {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF2/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
							+ proportion + " -f " + proportion + " -bed > " + file_path + "tcof_bedtools.bed");

				} else {
					Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
							+ name19or38 + "/TcoF/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
							+ proportion + " -f " + proportion + " -bed > " + file_path + "tcof_bedtools.bed");

				}

			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "tcof_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "";
			String set_num = "";
			if (radiotcof.equals("2")) {
				num = "'/mnt/data/" + name19or38 + "/TcoF2/R/TcoF_intersect_universe_NUM.txt'";
				set_num = "'/mnt/data/" + name19or38 + "/TcoF2/R/TcoF.txt'";
			} else {
				num = "'/mnt/data/" + name19or38 + "/TcoF/R/TcoF_intersect_universe_NUM.txt'";
				set_num = "'/mnt/data/" + name19or38 + "/TcoF/R/TcoF.txt'";
			}

			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";

			String step4 = "'" + file_path + "tcof_bedtools.bed'";
			bedFile = step4;

//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'TcoF_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'TcoF.txt'";
//	        String step4 = "'tcof_bedtools.bed'";

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("filename", file_path + "result_hyp.txt");
			if (radiotcof.equals("2")) {
				conn.eval("source(\"/mnt/data/" + name19or38 + "/TcoF2/R/TcoF.r\")");
			} else {
				conn.eval("source(\"/mnt/data/" + name19or38 + "/TcoF/R/TcoF.r\")");
			}

//			conn.eval("source(\"E:/Rtest/TcoF/TcoF.r\")");
			REXP eval = conn.eval(
					"hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4 + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));

				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());

		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String is1or2 = radiotcof.equals("2") ? "2" : "";
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'TcoF" + is1or2 + "19'"
					: "'TcoF" + is1or2 + "'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			if (radiotcof.equals("2")) {
				conn.eval("source(\"/mnt/data/" + name19or38 + "/TcoF2/R/LOLA.r\")");
			} else {
				conn.eval("source(\"/mnt/data/" + name19or38 + "/TcoF/R/LOLA.r\")");
			}

			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}

			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					System.out.println(fileToList);
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/Histone")
	@ResponseBody
	public void queryanalysis_histone(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();

		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Histone/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Histone/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
						+ proportion + " -bed > " + file_path + "histone_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Histone/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Histone/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -bed > " + file_path + "histone_bedtools.bed");

			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Histone/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");

				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Histone/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -f " + proportion + " -bed > " + file_path + "histone_bedtools.bed");

			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "histone_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/Histone/R/Histone_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/Histone/R/Histone.txt'";
			String step4 = "'" + file_path + "histone_bedtools.bed'";
			bedFile = step4;

//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'Histone_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'Histone.txt'";
//	        String step4 = "'histone_bedtools.bed'";

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/Histone/R/Histone.r\")");
//			conn.eval("source(\"E:/Rtest/Histone/Histone.r\")");
			REXP eval = conn.eval(
					"hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4 + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));

				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'Histone19'" : "'Histone'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/Histone/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					System.out.println(fileToList);
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/ATAC")
	@ResponseBody
	public void queryanalysis_atac(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();

		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ATAC/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ATAC/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f " + proportion
						+ " -bed > " + file_path + "atac_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ATAC/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ATAC/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F " + proportion
						+ " -bed > " + file_path + "atac_bedtools.bed");
			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ATAC/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion + " -f "
						+ proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/ATAC/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F " + proportion
						+ " -f " + proportion + " -bed > " + file_path + "atac_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "atac_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/ATAC/R/ATAC_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/ATAC/R/ATAC.txt'";
			String step4 = "'" + file_path + "atac_bedtools.bed'";
			bedFile = step4;

//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'ATAC_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'ATAC.txt'";
//	        String step4 = "'atac_bedtools.bed'";

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/ATAC/R/ATAC.r\")");
//			conn.eval("source(\"E:/Rtest/ATAC/ATAC.r\")");
			REXP eval = conn.eval(
					"hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4 + ",filename)");
			conn.close();

			String[] asStrings = eval.asStrings();
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));

				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'ATAC19'" : "'ATAC'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/ATAC/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					System.out.println(fileToList);
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/Enhancer")
	@ResponseBody
	public void queryanalysis_enhancer(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Enhancer/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Enhancer/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
						+ proportion + " -bed > " + file_path + "enhancer_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Enhancer/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Enhancer/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -bed > " + file_path + "enhancer_bedtools.bed");
			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Enhancer/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Enhancer/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -f " + proportion + " -bed > " + file_path + "enhancer_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "enhancer_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/Enhancer/R/Enhancer_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/Enhancer/R/Enhancer.txt'";
			String step4 = "'" + file_path + "enhancer_bedtools.bed'";
			bedFile = step4;

//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'Enhancer_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'Enhancer.txt'";
//	        String step4 = "'enhancer_bedtools.bed'";

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/Enhancer/R/Enhancer.r\")");
//			conn.eval("source(\"E:/Rtest/Enhancer/Enhancer.r\")");
			REXP eval = conn.eval(
					"hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4 + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}

			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'Enhancer19'" : "'Enhancer'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/Enhancer/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					System.out.println(fileToList);
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/Super_Enhancer")
	@ResponseBody
	public void queryanalysis_super_enhancer(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      浣跨敤bedtools
			if (outputForm.equals("0")) {
				// 绗竴姝� *** 缁撴灉鏂囦欢涓篟杈撳叆鐨勫弬鏁� ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 绗簩姝�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "" + name19or38 + "/Super_Enhancer/" + subset + ".bed -b " + file_path
						+ "user.bed -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 绗笁姝� *** 缁撴灉鏂囦欢涓篟杈撳叆鐨勫弬鏁� ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 绗洓姝� *** 缁撴灉鏂囦欢涓烘甯哥粏鑺傞〉璇诲叆 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Super_Enhancer/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
						+ proportion + " -bed > " + file_path + "super_enhancer_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 绗竴姝� *** 缁撴灉鏂囦欢涓篟杈撳叆鐨勫弬鏁� ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 绗簩姝�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Super_Enhancer/" + subset + ".bed -b " + file_path + "user.bed -F "
						+ proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 绗笁姝� *** 缁撴灉鏂囦欢涓篟杈撳叆鐨勫弬鏁� ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 绗洓姝� *** 缁撴灉鏂囦欢涓烘甯哥粏鑺傞〉璇诲叆 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Super_Enhancer/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -bed > " + file_path + "super_enhancer_bedtools.bed");
			} else {
				// 绗竴姝� *** 缁撴灉鏂囦欢涓篟杈撳叆鐨勫弬鏁� ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 绗簩姝�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Super_Enhancer/" + subset + ".bed -b " + file_path + "user.bed -F "
						+ proportion + " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 绗笁姝� *** 缁撴灉鏂囦欢涓篟杈撳叆鐨勫弬鏁� ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 绗洓姝� *** 缁撴灉鏂囦欢涓烘甯哥粏鑺傞〉璇诲叆 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Super_Enhancer/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -f " + proportion + " -bed > " + file_path + "super_enhancer_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "super_enhancer_bedtools.bed");
//		    ************ R杈撳叆涓虹┖鐩存帴璺冲嚭 ***************************
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax鎺ユ敹鍊兼牸寮忓墠闈㈡湁data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			System.out.println(name19or38);
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/Super_Enhancer/R/Super_Enhancer_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/Super_Enhancer/R/Super_Enhancer.txt'";
			String step4 = "'" + file_path + "super_enhancer_bedtools.bed'";
			bedFile = step4;

//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'Super_Enhancer_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'Super_Enhancer.txt'";
//	        String step4 = "'super_enhancer_bedtools.bed'";

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/Super_Enhancer/R/Super_Enhancer.r\")");
//			conn.eval("source(\"E:/Rtest/Super_Enhancer/Super_Enhancer.r\")");
			REXP eval = conn.eval(
					"hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4 + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			姘旀场鍥鹃渶瑕佺殑鏁版嵁鏋勫缓
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			鏌辩姸鍥鹃渶瑕佺殑鏁版嵁鏋勫缓
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {
				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double杞瑂tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'Super_Enhancer19'" : "'Super_Enhancer'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			System.out.println(">>>>>>>>>>>>>> R start >>>>>>>>>>>>>>");
			System.out.println(">>>>>>>>>>>>>> " + universe + " >>>>>>>>>>>>>>");
			RConnection conn = new RConnection();
			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/Super_Enhancer/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			System.out.println(">>>>>>>>>>>>>> R end >>>>>>>>>>>>>>");

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());

			// 排序
			datachuli.sortList(String1, String3, String7);

//			姘旀场鍥鹃渶瑕佺殑鏁版嵁鏋勫缓
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          鏌辩姸鍥鹃渶瑕佺殑鏁版嵁鏋勫缓
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String1.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}

//        List<FileToList> listR = new ArrayList<FileToList>();
//        
//        FileToList info1 = new FileToList();
//
//        // wwww
//        info1.setString1("Sample_01_027_Primary_cell_epithelial_cell_of_prostate");
//        info1.setString2("10");
//        info1.setString3(" 31902");
//        info1.setString4("0.00288435037455139");
//        info1.setString5("0.0038789539519829");
//        info1.setString6("0.112489664607504");
//        info1.setString7(" 898");
//        info1.setString8("1");
//        listR.add(info1);
//        info1 = new FileToList();
//        info1.setString1("Sample_00_005_Primary_cell_B_cell");
//        info1.setString2("17");
//        info1.setString3(" 35037");
//        info1.setString4("4.376445009358e-07");
//        info1.setString5("1.00400797273507e-06");
//        info1.setString6("1.70681355364962e-05");
//        info1.setString7(" 576");
//        info1.setString8("1");
//        listR.add(info1);
//        info1 = new FileToList();
//        info1.setString1("Sample_00_009_Primary_cell_CD8_positive_alpha_beta_T_cell");
//        info1.setString2("28");
//        info1.setString3(" 29691");
//        info1.setString4("9.14086815925825e-18");
//        info1.setString5("8.91234645527679e-17");
//        info1.setString6("3.56493858211072e-16");
//        info1.setString7(" 716");
//        info1.setString8("1");
//        listR.add(info1);
//        info1 = new FileToList();
//        info1.setString1("Sample_00_010_In_vitro_differentiated_cells_endodermal_cell");
//        info1.setString2("12");
//        info1.setString3(" 53000");
//        info1.setString4("0.0141751833969189");
//        info1.setString5("0.0178332952412851");
//        info1.setString6("0.552832152479837");
//        info1.setString7("1220");
//        info1.setString8("1");
//        listR.add(info1);

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
//        return listR;
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/subTable")
	@ResponseBody
	public List<FileToList> subTable(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model, @RequestParam String set) {
		String file_path = (String) session.getAttribute("file_path");
		String datatype = (String) session.getAttribute("overlap_datatype");
		List<String> content = null;
		if (datatype.equals("ChromHMM")) {
			content = File_read.read(file_path + "hmm_bedtools.bed");
		} else if (datatype.equals("mRNA")) {
			content = File_read.read(file_path + "mRNA_bedtools.bed");
		} else if (datatype.equals("LncRNA")) {
			content = File_read.read(file_path + "lncRNA_bedtools.bed");
		} else {
			content = File_read.read(file_path + datatype.toLowerCase() + "_bedtools.bed");
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>> subTable start: content >>>>>>>>>>>>>>>>>>>>>");
		Integer size = content.size();
		if (size <= 0) {
			return Collections.emptyList();
		}

		int index = 0;
		// 判断索引
		switch (datatype) {
		case "TF":
		case "TcoF":
		case "Histone":
		case "ATAC":
			index = 3;
			break;
		case "Enhancer":
		case "Super_Enhancer":
		case "SNP":
		case "eQTL":
		case "Methylation":
			index = 4;
			break;
		case "ChromHMM":
			index = 5;
			break;
		case "mRNA":
		case "LncRNA":
			index = 6;
			break;
		default:
			throw new RuntimeException("datatype 获取失败...");
		}

		List<FileToList> info = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			String oneLine = content.get(i);
			String[] split = oneLine.split("\t");

			if (split.length == 0) {
				continue;
			}
			int new_index = index;
			
			if ("ENdb".equals(set)) {
				new_index = 5;
			}
			
			int length = split.length - new_index - 4;
			if (StringUtils.equals(set, split[index])) {
				FileToList fileToList = new FileToList();

				fileToList.setString1(split[new_index + 1] + ":" + split[new_index + 2] + "-" + split[new_index + 3]);
				
				fileToList.setString2(split[0] + ":" + split[1] + "-" + split[2]);
				
				if (length != 0) {
					if (length >= 1) {
						fileToList.setString3(split[new_index + 4]);
					}
					if (length >= 2) {
						fileToList.setString4(split[new_index + 5]);
					}
					if (length >= 3) {
						fileToList.setString5(split[new_index + 6]);
					}
					if (length >= 4) {
						fileToList.setString6(split[new_index + 7]);
					}
					if (length >= 5) {
						fileToList.setString7(split[new_index + 8]);
					}
					if (length >= 6) {
						fileToList.setString8(split[new_index + 9]);
					}
					if (length >= 7) {
						fileToList.setString9(split[new_index + 10]);
					}
					if (length >= 8) {
						fileToList.setString10(split[new_index + 11]);
					}
					if (length >= 9) {
						fileToList.setString11(split[new_index + 12]);
					}
				}
				info.add(fileToList);
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>> subTable end: info >>>>>>>>>>>>>>>>>>>>>");
		return info;
	}

	@RequestMapping("/analysis/SNP")
	@ResponseBody
	public void queryanalysis_snp(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/SNP/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/SNP/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f " + proportion
						+ " -bed > " + file_path + "snp_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/SNP/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/SNP/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F " + proportion
						+ " -bed > " + file_path + "snp_bedtools.bed");
			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/SNP/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion + " -f "
						+ proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/SNP/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F " + proportion
						+ " -f " + proportion + " -bed > " + file_path + "snp_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "snp_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/SNP/R/SNP_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/SNP/R/SNP.txt'";
			String step4 = "'" + file_path + "snp_bedtools.bed'";
			String kb = "'" + subset.split("_")[subset.split("_").length - 1] + "'";
//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'SNP_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'SNP.txt'";
//	        String step4 = "'snp_bedtools.bed'";
			bedFile = step4;

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("kb", kb);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/SNP/R/SNP.r\")");
//			conn.eval("source(\"E:/Rtest/SNP/SNP.r\")");
			REXP eval = conn.eval("hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4
					+ "," + kb + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();

			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'SNP19'" : "'SNP'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/SNP/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/eQTL")
	@ResponseBody
	public void queryanalysis_eqtl(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/eQTL/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/eQTL/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f " + proportion
						+ " -bed > " + file_path + "eqtl_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/eQTL/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/eQTL/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F " + proportion
						+ " -bed > " + file_path + "eqtl_bedtools.bed");
			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/eQTL/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion + " -f "
						+ proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/eQTL/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F " + proportion
						+ " -f " + proportion + " -bed > " + file_path + "eqtl_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "eqtl_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/eQTL/R/eQTL_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/eQTL/R/eQTL.txt'";
			String step4 = "'" + file_path + "eqtl_bedtools.bed'";
//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'SNP_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'SNP.txt'";
//	        String step4 = "'snp_bedtools.bed'";
			bedFile = step4;

			System.out.println(">>>>>>>>>>>>>>>>>>>>> eQTL R start >>>>>>>>>>>>>>>>>>>>>");
			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/eQTL/R/eQTL.r\")");
//			conn.eval("source(\"E:/Rtest/SNP/SNP.r\")");
			REXP eval = conn.eval(
					"hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4 + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();

			System.out.println(">>>>>>>>>>>>>>>>>>>>> eQTL R finish >>>>>>>>>>>>>>>>>>>>>");
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'eQTL19'" : "'eQTL'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/eQTL/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}


	}

	@RequestMapping("/analysis/Methylation")
	@ResponseBody
	public void queryanalysis_methylation(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Methylation/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Methylation/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
						+ proportion + " -bed > " + file_path + "methylation_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Methylation/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Methylation/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -bed > " + file_path + "methylation_bedtools.bed");
			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Methylation/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/Methylation/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -f " + proportion + " -bed > " + file_path + "methylation_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "methylation_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/Methylation/R/Methylation_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/Methylation/R/Methylation.txt'";
			String step4 = "'" + file_path + "methylation_bedtools.bed'";
			String kb = "'" + subset.split("_")[subset.split("_").length - 1] + "'";
//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'Methylation_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'Methylation.txt'";
//	        String step4 = "'methylation_bedtools.bed'";
			bedFile = step4;

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("kb", kb);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/Methylation/R/Methylation.r\")");
//			conn.eval("source(\"E:/Rtest/Methylation/Methylation.r\")");
			REXP eval = conn.eval("hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4
					+ "," + kb + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'Methylation19'" : "'Methylation'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/Methylation/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}
//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/LncRNA")
	@ResponseBody
	public void queryanalysis_lnc(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/LncRNA/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/LncRNA/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f "
						+ proportion + " -bed > " + file_path + "lncRNA_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/LncRNA/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/LncRNA/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -bed > " + file_path + "lncRNA_bedtools.bed");
			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/LncRNA/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " -f " + proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/LncRNA/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F "
						+ proportion + " -f " + proportion + " -bed > " + file_path + "lncRNA_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "lncRNA_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/LncRNA/R/LncRNA_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/LncRNA/R/LncRNA.txt'";
			String step4 = "'" + file_path + "lncRNA_bedtools.bed'";
			String kb = "'" + subset.split("_")[subset.split("_").length - 1] + "'";
//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'LncRNA_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'LncRNA.txt'";
//	        String step4 = "'lncRNA_bedtools.bed'";
			bedFile = step4;

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("kb", kb);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/LncRNA/R/LncRNA.r\")");
//			conn.eval("source(\"E:/Rtest/LncRNA/LncRNA.r\")");
			REXP eval = conn.eval("hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4
					+ "," + kb + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {
				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'LncRNA19'" : "'LncRNA'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/LncRNA/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			ArrayList<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);

			model.addAttribute("listR", listR);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	@RequestMapping("/analysis/mRNA")
	@ResponseBody
	public void queryanalysis_mRNA(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws Exception {

		String name19or38 = (String) session.getAttribute("name19or38");
		String file_path = (String) session.getAttribute("file_path");
		String proportion = (String) session.getAttribute("proportion");
		String outputForm = (String) session.getAttribute("outputForm");
		String subset = (String) session.getAttribute("overlap_subset");
		String methodForm = (String) session.getAttribute("methodForm");

		String min = (String) session.getAttribute("min");
		String max = (String) session.getAttribute("max");
		String pValue = (String) session.getAttribute("pValue");

		String judge_email = (String) session.getAttribute("judge_email");
		String userEmail = (String) session.getAttribute("userEmail");
		String userId = (String) session.getAttribute("userId");
		String datatype = (String) session.getAttribute("overlap_datatype");
		String userLineLength = String.valueOf(session.getAttribute("userLineLength"));
		String bedFile = "";


		List<FileToList> listR = new ArrayList<FileToList>();
		List<String> String1 = new ArrayList<String>();
		List<String> String2 = new ArrayList<String>();
		List<String> String3 = new ArrayList<String>();
		List<String> String4 = new ArrayList<String>();
		List<String> String5 = new ArrayList<String>();
		List<String> String6 = new ArrayList<String>();
		List<String> String7 = new ArrayList<String>();
		List<String> String8 = new ArrayList<String>();
		List<String> String9 = new ArrayList<String>();
		if (methodForm.equals("1")) {
			Linux_java Linux = new Linux_java();
//		      娴ｈ法鏁edtools
			if (outputForm.equals("0")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/mRNA/" + subset + ".bed -b " + file_path + "user.bed -f " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/mRNA/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -f " + proportion
						+ " -bed > " + file_path + "mRNA_bedtools.bed");
			} else if (outputForm.equals("1")) {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/mRNA/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion
						+ " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/mRNA/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F " + proportion
						+ " -bed > " + file_path + "mRNA_bedtools.bed");
			} else {
				// 缁楊兛绔村锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user.bed -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb | awk -vOFS=\"\t\" -F \"\t\" '{print $4,$5,$6}' |sort |uniq > "
						+ file_path + "user_intersect_universe.txt");
				// 缁楊兛绨╁锟�
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/mRNA/" + subset + ".bed -b " + file_path + "user.bed -F " + proportion + " -f "
						+ proportion + " |sort |uniq> " + file_path + "user_intersect_set.txt");
				// 缁楊兛绗佸锟� *** 缂佹挻鐏夐弬鍥︽娑撶療鏉堟挸鍙嗛惃鍕棘閺侊拷 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a " + file_path
						+ "user_intersect_set.txt -b /mnt/data/" + name19or38
						+ "/activeDHS_universe.bed -wa -wb |sort |uniq> " + file_path
						+ "user_intersect_set_universe.txt");
				// 缁楊剙娲撳锟� *** 缂佹挻鐏夐弬鍥︽娑撶儤顒滅敮鍝ョ矎閼哄倿銆夌拠璇插弳 ***
				Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/"
						+ name19or38 + "/mRNA/" + subset + ".bed -b " + file_path + "user.bed -wa -wb -F " + proportion
						+ " -f " + proportion + " -bed > " + file_path + "mRNA_bedtools.bed");
			}
			List<String> file_check_one = Linux.getExecCommand("du -sh " + file_path + "user_intersect_universe.txt");
			List<String> file_check_two = Linux
					.getExecCommand("du -sh " + file_path + "user_intersect_set_universe.txt");
			List<String> file_check_three = Linux.getExecCommand("du -sh " + file_path + "mRNA_bedtools.bed");
//		    ************ R鏉堟挸鍙嗘稉铏光敄閻╁瓨甯寸捄鍐插毉 ***************************		
			if (file_check_one.get(0).split("\t")[0].equals("0") || file_check_two.get(0).split("\t")[0].equals("0")
					|| file_check_three.get(0).split("\t")[0].equals("0")) {
//			    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
				Map map = new HashMap();
				map.put("data", "");
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			String step1 = "'" + file_path + "user_intersect_universe.txt'";
			String num = "'/mnt/data/" + name19or38 + "/mRNA/R/mRNA_intersect_universe_NUM.txt'";
			String step3 = "'" + file_path + "user_intersect_set_universe.txt'";
			String set_num = "'/mnt/data/" + name19or38 + "/mRNA/R/mRNA.txt'";
			String step4 = "'" + file_path + "mRNA_bedtools.bed'";
			String kb = "'" + subset.split("_")[subset.split("_").length - 1] + "'";
//	        String step1 = "'user_intersect_universe.txt'";
//	        String num = "'mRNA_intersect_universe_NUM.txt'";
//	        String step3 = "'user_intersect_set_universe.txt'";
//	        String set_num = "'mRNA.txt'";
//	        String step4 = "'mRNA_bedtools.bed'";
			bedFile = step4;

			RConnection conn = new RConnection();

			conn.assign("step1", step1);
			conn.assign("num", num);
			conn.assign("step3", step3);
			conn.assign("set_num", set_num);
			conn.assign("step4", step4);
			conn.assign("kb", kb);
			conn.assign("filename", file_path + "result_hyp.txt");

			conn.eval("source(\"/mnt/data/" + name19or38 + "/mRNA/R/mRNA.r\")");
//			conn.eval("source(\"E:/Rtest/mRNA/mRNA.r\")");
			REXP eval = conn.eval("hypergeometric(" + step1 + "," + num + "," + step3 + "," + set_num + "," + step4
					+ "," + kb + ",filename)");
			conn.close();
			String[] asStrings = eval.asStrings();
			for (int i = 0; i < asStrings.length / 8; i++) {
				String1.add(asStrings[i]);
			}
			for (int i = asStrings.length / 8; i < (asStrings.length / 8) * 2; i++) {
				String2.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 2; i < (asStrings.length / 8) * 3; i++) {
				String3.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 3; i < (asStrings.length / 8) * 4; i++) {
				String4.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 4; i < (asStrings.length / 8) * 5; i++) {
				String5.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 5; i < (asStrings.length / 8) * 6; i++) {
				String6.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 6; i < (asStrings.length / 8) * 7; i++) {
				String7.add(asStrings[i]);
			}
			for (int i = (asStrings.length / 8) * 7; i < (asStrings.length / 8) * 8; i++) {
				String8.add(asStrings[i]);
			}

			FileToList fileToList = null;

			for (int i = 0; i < asStrings.length / 8; i++) {
				// 判断是否需要添加
				if (NumberUtil.addData(min, max, pValue, String4.get(i), String7.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					// pValue
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					// string7 Num
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					listR.add(fileToList);
				}
			}
			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String4 = listR.stream().map(FileToList::getString4).collect(Collectors.toList());
			String8 = listR.stream().map(FileToList::getString8).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String4, String1, String8);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String4.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(String1.get(i));
				qipao_data.setFreq(String8.get(i));
				qipao_data.setP_value("" + -Math.log10(Double.parseDouble(String4.get(i))));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);

//			閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			Gson gson = new Gson();
			String classname = gson.toJson(String1);
			session.setAttribute("classname", classname);

			ArrayList<String> pvalue = new ArrayList<String>();
			for (int i = 0; i < String4.size(); i++) {

				double a = -Math.log10(Double.parseDouble(String4.get(i)));
//				double鏉炵憘tring
				pvalue.add("" + Math.floor(a * 100) / 100);
			}

			session.setAttribute("pvalue", pvalue.toString());
		}
		if (methodForm.equals("2")) {
			String input = "'" + file_path + "user.bed'";
			String universe = (String) session.getAttribute("universe");
			String dataclass = StringUtils.equals(name19or38, "GREAP19") ? "'mRNA19'" : "'mRNA'";
			String overlap_path = "'" + file_path + "'";
			String subclass = "'" + subset + "'";

			RConnection conn = new RConnection();

			conn.assign("input", input);
			conn.assign("universe", universe);
			conn.assign("dataclass", dataclass);
			conn.assign("overlap_path", overlap_path);
			conn.assign("subclass", subclass);
			conn.eval("source(\"/mnt/data/" + name19or38 + "/mRNA/R/LOLA.r\")");
			REXP eval = null;
			try {
				eval = conn.eval(
						"lola(" + input + "," + universe + "," + dataclass + "," + subclass + "," + overlap_path + ")");
			} catch (Exception e) {
				Map map = new HashMap();
				map.put("data", listR);
				Gson gsonnew = new Gson();
				String json = gsonnew.toJson(map);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
				return;
			}
			List<List<String>> table = new ArrayList<>();

			RList rList = eval.asList();
			int length = rList.at(0).asStrings().length;
			for (int j = 0; j < length; j++) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < rList.size(); i++) {
					REXP at = rList.at(i);
					String[] one = at.asStrings();
					row.add(one[j]);
				}
				table.add(row);
			}
			conn.close();
			for (int i = 0; i < table.size(); i++) {
				String1.add(table.get(i).get(3));
				String2.add(table.get(i).get(4));
				String3.add(table.get(i).get(5));
				String4.add(table.get(i).get(6));
				String5.add(table.get(i).get(7));
				String6.add(table.get(i).get(8));
				String7.add(table.get(i).get(20));
				String8.add(table.get(i).get(21));
				String9.add(table.get(i).get(1));
			}
			FileToList fileToList = null;
			for (int i = 0; i < table.size(); i++) {
				// 判断是否需要添加
				if (NumberUtil.addData2(min, max, pValue, String1.get(i), String8.get(i))) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					listR.add(fileToList);
				}
			}

			// 筛选
			String1 = listR.stream().map(FileToList::getString1).collect(Collectors.toList());
			String3 = listR.stream().map(FileToList::getString3).collect(Collectors.toList());
			String7 = listR.stream().map(FileToList::getString7).collect(Collectors.toList());
			// 排序
			datachuli.sortList(String1, String3, String7);

//			濮樻梹鍦洪崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			ArrayList<Qipao_data> listQ = new ArrayList<Qipao_data>();
			Qipao_data qipao_data = null;
			for (int i = 0; i < String1.size(); i++) {
				qipao_data = new Qipao_data();
				qipao_data.setData_type(subset + "_" + String7.get(i).replace(".bed", ""));
				qipao_data.setFreq(String3.get(i));
				qipao_data.setP_value(String1.get(i));
				listQ.add(qipao_data);
			}
			String qipao = listQ.toString();
			session.setAttribute("qipao", qipao);
//          閺岃京濮搁崶楣冩付鐟曚胶娈戦弫鐗堝祦閺嬪嫬缂�
			String stringname = "";
			List<String> classname = new ArrayList<String>();
			for (int i = 0; i < String7.size(); i++) {
				stringname = subset + "_" + String7.get(i).replace(".bed", "");
				classname.add(stringname);
			}
			Gson gson = new Gson();
			session.setAttribute("classname", gson.toJson(classname));
			String pvalue = String1.toString();
			session.setAttribute("pvalue", pvalue);
			model.addAttribute("listR", listR);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", listR);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		// 保存信息
		if ("1".equals(judge_email)) {
			UserEmail newUserEmail = new UserEmail(userId, userEmail,
					methodForm.equals("1") ? file_path + "result_hyp.txt" : file_path + "lola_result.txt", new Date());
			newUserEmail.setfBedFile(bedFile);
			newUserEmail.setfHg(name19or38);
			newUserEmail.setfMin(min);
			newUserEmail.setfMax(max);
			newUserEmail.setfMethod(methodForm);
			newUserEmail.setfIntPValue(pValue);
			newUserEmail.setfDatatype(datatype);
			newUserEmail.setfSubset(subset);
			newUserEmail.setfLineLength(userLineLength);
		    System.out.println(newUserEmail.toString());
			searchService.saveUserEmail(newUserEmail);
		}

	}

	// ######################################################################################
	@RequestMapping("/seanalysis")
	public ModelAndView seanalysis(@RequestParam("sE_name") String sE_name) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sE_name", sE_name);
		mav.setViewName("seanalysis");
		return mav;
	}

	@RequestMapping("/trlnc")
	public ModelAndView trlnc(@RequestParam("lncrna_name") String lncrna_name,
			@RequestParam("regulation") String regulation) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("lncrna_name", lncrna_name);
		mav.addObject("regulation", regulation);
		mav.setViewName("trlnc");
		return mav;
	}

	@RequestMapping("/analysis/set_detail")
	public String queryanalysis_set_detail(String grch, String datatype, String set, String subclass, String count,
			String port, String overlap_num, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws Exception {
		List listchr = null;
		session.setAttribute("genome", grch);
		String file_path = (String) session.getAttribute("file_path");
		String methodForm = (String) session.getAttribute("methodForm");
		String radiotf = (String) session.getAttribute("radiotf");
		String radiotcof = (String) session.getAttribute("radiotcof");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
//        闂嗗棗鎮庨弻鎾瑰娴ｆ挸褰块惃鍒art
		ArrayList<String> x = new ArrayList<String>();
		ArrayList<String> y = new ArrayList<String>();
//	    缂佸棜濡い鍏哥瑐闂堛垹褰告潏锟� 闂嗗棗鎮庨弻鎾瑰娴ｆ挸褰块惃鍕叴閻樿泛娴�
		String datatypeR = datatype;
		if ("TF".equals(datatype) && "2".equals(radiotf)) {
			listchr = searchService.querylistchr("TF2", subclass, set);
			datatypeR = "TF2";
		} else if ("TcoF".equals(datatype) && "2".equals(radiotcof)) {
			listchr = searchService.querylistchr("TcoF2", subclass, set);
			datatypeR = "TcoF2";
		} else {
			listchr = searchService.querylistchr(datatype, subclass, set);
		}
		if ("SNP".equals(datatype)) {
			String SNP_disease = searchService.getSnpDisease(set);
			session.setAttribute("SNP_disease", SNP_disease);
		}
		System.out.println(datatypeR);
		for (int i = 0; i < listchr.get(0).toString().split(",").length; i++) {
			x.add("\"" + listchr.get(0).toString().split(",")[i].replace("{", "").replace("}", "").replace(" ", "")
					.split("=")[0] + "\"");
			y.add(listchr.get(0).toString().split(",")[i].replace("{", "").replace("}", "").split("=")[1]);
		}
		if (port.equals("0")) {
//		    婵″倹鐏夐弰鐤濷LA閺傝纭堕棁锟界憰浣瑰絹overlap閻ㄥ嫬灏崺锟�
			if (methodForm.equals("2")) {
				String input = "'" + file_path + "user.bed'";
				String overlap_path = "'" + file_path + "'";
				RConnection conn = new RConnection();

				conn.assign("input", input);
				conn.assign("datatype", datatypeR);
				conn.assign("subclass", subclass);
				conn.assign("overlap_path", overlap_path);
				conn.assign("overlap_num", overlap_num);

				conn.eval("source(\"/mnt/data/GREAP/LOLA_Overlap/overlap.r\")");

				if (datatype.equals("ChromHMM")) {
					conn.eval("lola_overlap(" + input + ",'HMM','" + subclass + "'," + overlap_path + "," + overlap_num
							+ ")");
				} else {
					conn.eval("lola_overlap(" + input + ",'" + datatypeR + "','" + subclass + "'," + overlap_path + "," + overlap_num + ")");
				}

				conn.close();
			}
		}

		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		ArrayList<String> String8 = new ArrayList<String>();
		ArrayList<String> String9 = new ArrayList<String>();
		ArrayList<String> String10 = new ArrayList<String>();
		List<String> listfile = null;
		if (datatype.equals("ChromHMM")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "hmm_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
					String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
					String9.add(listfile.get(i).replaceAll("\t", ",").split(",")[8]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));

					list.add(fileToList);
				}
				model.addAttribute("list", list);

			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}

		}
		if (datatype.equals("TF")) {
			System.out.println(methodForm);
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "tf_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				System.out.println(listfile);
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(listfile.get(i).split("\t")[0]);
					fileToList.setString2(listfile.get(i).split("\t")[1]);
					fileToList.setString3(listfile.get(i).split("\t")[2]);
					fileToList.setString4(listfile.get(i).split("\t")[3]);
					fileToList.setString5(listfile.get(i).split("\t")[4]);
					fileToList.setString6(listfile.get(i).split("\t")[5]);
					fileToList.setString7(listfile.get(i).split("\t")[6]);
					list.add(fileToList);
				}
				model.addAttribute("list", list);

			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}

		}
		if (datatype.equals("TcoF")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "tcof_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);

			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		if (datatype.equals("Histone")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "histone_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);

			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		if (datatype.equals("ATAC")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "atac_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);

			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		if (datatype.equals("Enhancer")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "enhancer_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(listfile.get(i).split("\t")[0]);
					fileToList.setString2(listfile.get(i).split("\t")[1]);
					fileToList.setString3(listfile.get(i).split("\t")[2]);
					fileToList.setString4(listfile.get(i).split("\t")[3]);
					fileToList.setString5(listfile.get(i).split("\t")[4]);
					fileToList.setString6(listfile.get(i).split("\t")[5]);
					fileToList.setString7(listfile.get(i).split("\t")[6]);
					fileToList.setString8(listfile.get(i).split("\t")[7]);
					if ("ENdb".equals(set)) {
					    fileToList.setString9(listfile.get(i).split("\t")[8]);
					}
					list.add(fileToList);
				}
				model.addAttribute("list", list);
			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}

		}
		if (datatype.equals("Super_Enhancer")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "super_enhancer_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
					String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);
			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		if (datatype.equals("SNP")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "snp_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
					String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);
			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		if (datatype.equals("eQTL")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "eqtl_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
					String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);
			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		if (datatype.equals("Methylation")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "methylation_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
					String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);
			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		if (datatype.equals("LncRNA")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "lncRNA_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
					String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
					String9.add(listfile.get(i).replaceAll("\t", ",").split(",")[8]);
					String10.add(listfile.get(i).replaceAll("\t", ",").split(",")[9]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					fileToList.setString10(String10.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);
			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		if (datatype.equals("mRNA")) {
			if (port.equals("0")) {
				if (methodForm.equals("1")) {
					listfile = File_read_limit.read(datatype, set, file_path + "mRNA_bedtools.bed");
				} else {
					listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
				}
				for (int i = 0; i < listfile.size(); i++) {
					String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
					String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
					String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
					String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
					String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
					String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
					String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
					String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
					String9.add(listfile.get(i).replaceAll("\t", ",").split(",")[8]);
					String10.add(listfile.get(i).replaceAll("\t", ",").split(",")[9]);
				}
				FileToList fileToList = null;
				for (int i = 0; i < listfile.size(); i++) {
					fileToList = new FileToList();
					fileToList.setString1(String1.get(i));
					fileToList.setString2(String2.get(i));
					fileToList.setString3(String3.get(i));
					fileToList.setString4(String4.get(i));
					fileToList.setString5(String5.get(i));
					fileToList.setString6(String6.get(i));
					fileToList.setString7(String7.get(i));
					fileToList.setString8(String8.get(i));
					fileToList.setString9(String9.get(i));
					fileToList.setString10(String10.get(i));
					list.add(fileToList);
				}
				model.addAttribute("list", list);
			} else {
				session.setAttribute("search_set_detail_datatype", datatype);
				session.setAttribute("search_set_detail_subclass", subclass);
				session.setAttribute("search_set_detail_set", set);
			}
		}
		model.addAttribute("x", x.toString());
		model.addAttribute("y", y.toString());
		model.addAttribute("set", set.replace("~.", "/").replaceAll("~", "+"));
		model.addAttribute("datatype", datatype);
		model.addAttribute("subclass", subclass);
		model.addAttribute("count", count);
		model.addAttribute("port", port);

		return "analysis_set_detail";
	}

//    @RequestMapping("/search/set_detail")
//    @ResponseBody
//    public datatableResult querysearch_set_detail(int start, int length, int draw, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        String datatype = (String) session.getAttribute("search_set_detail_datatype");
//        String subclass = (String) session.getAttribute("search_set_detail_subclass");
//        String set = (String) session.getAttribute("search_set_detail_set");
//        List listtable = null;
//        if (datatype.equals("ChromHMM")) {
//            subclass = "HMM_" + subclass;
//        }
//        if (datatype.equals("TF") || datatype.equals("TcoF") || datatype.equals("ATAC")) {
//            listtable = searchService.querylisttable(set);
//            subclass = "`" + listtable.get(0).toString() + "`";
//            System.out.println(subclass);
//            System.out.println(set);
//        }
//        if (datatype.equals("Histone")) {
//            subclass = "Histone_" + subclass;
//        }
//        if (datatype.equals("Enhancer")) {
//            subclass = "En_" + subclass;
//        }
//        if (datatype.equals("Super_Enhancer")) {
//            subclass = "SE_" + subclass;
//        }
//        if (datatype.equals("SNP")) {
//            subclass = "SNP_" + subclass;
//        }
//        if (datatype.equals("Methylation")) {
//            subclass = "Methylation_" + subclass;
//        }
//        if (datatype.equals("LncRNA")) {
//            subclass = "LncRNA_" + subclass;
//        }
//        if (datatype.equals("mRNA")) {
//            subclass = "mRNA_" + subclass;
//        }
//
//        List list = null;
//        datatableResult DatatableResult = new datatableResult();
//        PageHelper.startPage(start / length + 1, length);
//
//        list = searchService.querylistfile(subclass, set);
//        PageInfo pageInfo = new PageInfo(list);
//
//        DatatableResult.setDraw(DatatableResult.getDraw());
//        DatatableResult.setData(pageInfo.getList());
//        DatatableResult.setRecordsTotal((int) pageInfo.getTotal());
//        DatatableResult.setRecordsFiltered(DatatableResult.getRecordsTotal());
//
//        return DatatableResult;
//    }

	@RequestMapping(value = "/search/set_detail", method = RequestMethod.POST)
	@ResponseBody
	public datatableResult querysearch_set_detail(int start, int length, int draw, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
//		session.setAttribute("set_detail_grch", grch);
		String grch = (String) session.getAttribute("genome");
		String datatype = (String) session.getAttribute("search_set_detail_datatype");
		String subclass = (String) session.getAttribute("search_set_detail_subclass");
		String set = (String) session.getAttribute("search_set_detail_set");

		ReadFile readFile = new ReadFile();
		// /mnt/data/GREAP/R/HMM/Enh/Enh/regions
		System.out.println(grch);
		if (StringUtils.equals(datatype, "ChromHMM")) {
			datatype = "HMM";
		}
		System.out.println(datatype);
		String middle = StringUtils.equals(grch, "hg38") ? "R/" + datatype : "R19/" + datatype + "19";
		String filePath = "/mnt/data/GREAP/" + middle + "/" + subclass + "/" + subclass + "/regions/" + set + ".bed";
		System.out.println(filePath);
		List<String> lines = readFile.getLines(filePath, start, length);

		List<FileToList> fileToLists = new ArrayList<>();
		if (lines != null && lines.size() > 0) {
			FileToList fileToList = null;
			for (String line : lines) {
				if (line == null) {
					continue;
				}
				fileToList = new FileToList();
				String[] split = line.split("\t");
				fileToList.setString1(split[0]);
				fileToList.setString2(split[1]);
				fileToList.setString3(split[2]);
				if (split.length >= 4) {
					fileToList.setString4(split[3]);
				}
				if (split.length >= 5) {
					fileToList.setString5(split[4]);
				}
				if (split.length >= 6) {
					fileToList.setString6(split[5]);
				}
				if (split.length >= 7) {
					fileToList.setString7(split[6]);
				}
				if (split.length >= 8) {
					fileToList.setString8(split[7]);
				}
				if (split.length >= 9) {
					fileToList.setString9(split[8]);
				}
				if (split.length >= 10) {
					fileToList.setString10(split[9]);
				}
				if (split.length >= 11) {
					fileToList.setString11(split[10]);
				}
				if (split.length >= 12) {
					fileToList.setString12(split[11]);
				}
				fileToLists.add(fileToList);
			}
		}

		datatableResult<FileToList> DatatableResult = new datatableResult();
		DatatableResult.setDraw(DatatableResult.getDraw());
		DatatableResult.setData(fileToLists);
		DatatableResult.setRecordsTotal(readFile.totalNumber);
		DatatableResult.setRecordsFiltered(DatatableResult.getRecordsTotal());

		return DatatableResult;
	}

	@RequestMapping("/analysis/set_detail_chart")
	public String queryanalysis_set_detail_chart(String chr, String datatype, String set, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {
		String file_path = (String) session.getAttribute("file_path");
		String methodForm = (String) session.getAttribute("methodForm");
		List<String> Input = new ArrayList<>();
		List<String> Db = new ArrayList<>();
		List<String> Input_Start = new ArrayList<>();
		List<String> Input_End = new ArrayList<>();
		List<String> Db_Start = new ArrayList<>();
		List<String> Db_End = new ArrayList<>();
		List<String> listfile = null;
		System.out.println(chr);
		if (datatype.equals("ChromHMM")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "hmm_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//		    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[6].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[7] + "-" + listfile.get(i).split("\t")[8]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("TF")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "tf_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[4].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[5] + "-" + listfile.get(i).split("\t")[6]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("TcoF")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "tcof_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[4].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[5] + "-" + listfile.get(i).split("\t")[6]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("Histone")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "histone_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[4].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[5] + "-" + listfile.get(i).split("\t")[6]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("ATAC")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "atac_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[4].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[5] + "-" + listfile.get(i).split("\t")[6]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("Enhancer")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "enhancer_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			int index = 5;
			if ("ENdb".equals(set)) {
				index = 6;
			}
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.get(i).split("\t")[index].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[index + 1] + "-" + listfile.get(i).split("\t")[index + 2]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("Super_Enhancer")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "super_enhancer_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[5].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[6] + "-" + listfile.get(i).split("\t")[7]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("SNP")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "snp_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[5].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[6] + "-" + listfile.get(i).split("\t")[7]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("eQTL")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "eqtl_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[5].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[6] + "-" + listfile.get(i).split("\t")[7]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("Methylation")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "methylation_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[5].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[6] + "-" + listfile.get(i).split("\t")[7]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("LncRNA")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "lncRNA_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[7].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[8] + "-" + listfile.get(i).split("\t")[9]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
		if (datatype.equals("mRNA")) {
			if (methodForm.equals("1")) {
				listfile = File_read_limit.read(datatype, set, file_path + "mRNA_bedtools.bed");
			} else {
				listfile = File_read_limit.read(datatype, set, file_path + "overlap.txt");
			}
//			    ********************** 閹绘劕褰囬崠鍝勭厵 **********************
			for (int i = 0; i < listfile.size(); i++) {
				if (listfile.toString().split(",")[i].split("\t")[7].equals(chr)) {
					Input.add(listfile.get(i).split("\t")[8] + "-" + listfile.get(i).split("\t")[9]);
					Db.add(listfile.get(i).split("\t")[1] + "-" + listfile.get(i).split("\t")[2]);
				}
			}
		}
//				閺嶈宓侀崘鎺撳満缁犳纭堕幒鎺戠碍---Input閻拷
		int size = Input.size(); // 鏉╂瑤琚辨稉顏勫棘閺佹澘褰叉禒銉ュ彙閻拷
		String middle = null;

		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				String before = Input.get(i);
				String after = Input.get(j);
				int before_int = Integer.parseInt(before.split("-")[0]);
				int after_int = Integer.parseInt(after.split("-")[0]);
				if (before_int > after_int) {
					middle = before;
					Input.set(i, after);
					Input.set(j, middle);
				}
			}
//		            娴犲骸鐨崚鏉裤亣閹烘帒绨�瑰奔绔存稉顏勬皑閹绘仩tart閵嗕躬nd
			Input_Start.add(Input.get(i).split("-")[0]);
			Input_End.add(Input.get(i).split("-")[1]);
		}
//				閺嶈宓侀崘鎺撳満缁犳纭堕幒鎺戠碍---Db閻拷
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				String before = Db.get(i);
				String after = Db.get(j);
				int before_int = Integer.parseInt(before.split("-")[0]);
				int after_int = Integer.parseInt(after.split("-")[0]);
				if (before_int > after_int) {
					middle = before;
					Db.set(i, after);
					Db.set(j, middle);
				}
			}
//		            娴犲骸鐨崚鏉裤亣閹烘帒绨�瑰奔绔存稉顏勬皑閹绘仩tart閵嗕躬nd
			Db_Start.add(Db.get(i).split("-")[0]);
			Db_End.add(Db.get(i).split("-")[1]);
		}
		model.addAttribute("chr", chr);
		model.addAttribute("Input_Start", Input_Start.toString());
		model.addAttribute("Input_End", Input_End.toString());
		model.addAttribute("Db_Start", Db_Start.toString());
		model.addAttribute("Db_End", Db_End.toString());

		return "analysis_set_detail_chart_left";
	}

	// ######################################################################################
	@RequestMapping("/analysis/position")
	public String queryanalysis_position(String chr, String start, String end, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {
		String region = chr + "\t" + start + "\t" + end;
		String browser_region = chr + ":" + start + "-" + end;
		String file_path = (String) session.getAttribute("file_path");
		String judge19or38 = (String) session.getAttribute("genome");
		String is19or38 = StringUtils.equals(judge19or38, "hg19") ? "19" : "";
		if (file_path == null) {
			String randomName = UUID.randomUUID().toString();
			Linux_java Linux = new Linux_java();
			Linux.getExecCommand("mkdir -p /mnt/data/GREAP" + is19or38 + "/Position/" + randomName);
			String file_path_position = "/mnt/data/GREAP" + is19or38 + "/Position/" + randomName + "/";
			String file_name_position = File_form.formation(file_path_position, "position", ".bed", region);
			session.setAttribute("file_path_position", file_path_position);
		} else {
			Linux_java Linux = new Linux_java();
			Linux.getExecCommand("mkdir -p " + file_path + "position");
			String file_path_position = file_path + "position/";
			String file_name_position = File_form.formation(file_path_position, "position", ".bed", region);
			session.setAttribute("file_path_position", file_path_position);
		}
		session.setAttribute("browser_region", browser_region);
		return "analysis_set_detail_region";
	}

	@RequestMapping("/analysis/position/TF")
	public String queryanalysis_position_tf(String dataclass, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		session.setAttribute("tf_dataclass", dataclass);
		return "analysis_position_tf_list";
	}

	@RequestMapping("/analysis/position/TF/list")
	@ResponseBody
	public void queryanalysis_position_tf_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("tf_dataclass");
		String judge19or38 = (String) session.getAttribute("genome");
		String is19or38 = StringUtils.equals(judge19or38, "hg19") ? "19" : "";
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP" + is19or38
				+ "/TF/" + dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > "
				+ file_path_position + "position_tf.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		List<String> listfile = File_read.read(file_path_position + "position_tf.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/tf_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));

			list.add(fileToList);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/analysis/position/TcoF")
	public String queryanalysis_position_tcof(String dataclass, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {
		session.setAttribute("tcof_dataclass", dataclass);
		return "analysis_position_tcof_list";
	}

	@RequestMapping("/analysis/position/TcoF/list")
	@ResponseBody
	public void queryanalysis_position_tcof_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("tcof_dataclass");
		String judge19or38 = (String) session.getAttribute("genome");
		String is19or38 = StringUtils.equals(judge19or38, "hg19") ? "19" : "";
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP" + is19or38
				+ "/TcoF/" + dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > "
				+ file_path_position + "position_tcof.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		List<String> listfile = File_read.read(file_path_position + "position_tcof.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/tcof_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));

			list.add(fileToList);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/analysis/position/Histone")
	public String queryanalysis_position_histone(String dataclass, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {
		session.setAttribute("histone_dataclass", dataclass);
		return "analysis_position_histone_list";
	}

	@RequestMapping("/analysis/position/Histone/list")
	@ResponseBody
	public void queryanalysis_position_histone_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("histone_dataclass");
		String judge19or38 = (String) session.getAttribute("genome");
		String is19or38 = StringUtils.equals(judge19or38, "hg19") ? "19" : "";
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP" + is19or38
				+ "/Histone/" + dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > "
				+ file_path_position + "position_histone.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();

		List<String> listfile = File_read.read(file_path_position + "position_histone.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/histone_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);

		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));

			list.add(fileToList);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/analysis/position/ATAC")
	public String queryanalysis_position_atac(String dataclass, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {
		session.setAttribute("atac_dataclass", dataclass);
		return "analysis_position_atac_list";
	}

	@RequestMapping("/analysis/position/ATAC/list")
	@ResponseBody
	public void queryanalysis_position_atac_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("atac_dataclass");
		String judge19or38 = (String) session.getAttribute("genome");
		String is19or38 = StringUtils.equals(judge19or38, "hg19") ? "19" : "";
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP" + is19or38
				+ "/ATAC/" + dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > "
				+ file_path_position + "position_atac.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();

		List<String> listfile = File_read.read(file_path_position + "position_atac.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/atac_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);

		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));

			list.add(fileToList);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/analysis/position/Enhancer")
	public String queryanalysis_position_enhancer(String dataclass, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {
		session.setAttribute("enhancer_dataclass", dataclass);
		return "analysis_position_enhancer_list";
	}

	@RequestMapping("/analysis/position/Enhancer/list")
	@ResponseBody
	public void queryanalysis_position_enhancer_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("enhancer_dataclass");
		String judge19or38 = (String) session.getAttribute("genome");
		String is19or38 = StringUtils.equals(judge19or38, "hg19") ? "19" : "";
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP" + is19or38
				+ "/Enhancer/" + dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > "
				+ file_path_position + "position_enhancer.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		ArrayList<String> String8 = new ArrayList<String>();

		List<String> listfile = File_read.read(file_path_position + "position_enhancer.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/enhancer_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
			String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));
			fileToList.setString8(String8.get(i));

			list.add(fileToList);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/analysis/position/SE")
	public String queryanalysis_position_se(String dataclass, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		session.setAttribute("se_dataclass", dataclass);
		return "analysis_position_se_list";
	}

	@RequestMapping("/analysis/position/SE/list")
	@ResponseBody
	public void queryanalysis_position_se_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("se_dataclass");
		String judge19or38 = (String) session.getAttribute("genome");
		String is19or38 = StringUtils.equals(judge19or38, "hg19") ? "19" : "";
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP" + is19or38
				+ "/Super_Enhancer/" + dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > "
				+ file_path_position + "position_super_enhancer.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		ArrayList<String> String8 = new ArrayList<String>();

		List<String> listfile = File_read.read(file_path_position + "position_super_enhancer.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/se_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
			String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));
			fileToList.setString8(String8.get(i));

			list.add(fileToList);
		}

//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/analysis/position/SNP")
	public String queryanalysis_position_snp(String dataclass, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		session.setAttribute("snp_dataclass", dataclass);
		return "analysis_position_snp_list";
	}

	@RequestMapping("/analysis/position/eQTL")
	public String queryanalysis_position_eqtl(String dataclass, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {
		session.setAttribute("eqtl_dataclass", dataclass);
		return "analysis_position_eqtl_list";
	}

	@RequestMapping("/analysis/position/SNP/list")
	@ResponseBody
	public void queryanalysis_position_snp_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("snp_dataclass");
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP/SNP/"
				+ dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > " + file_path_position
				+ "position_snp.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		ArrayList<String> String8 = new ArrayList<String>();

		List<String> listfile = File_read.read(file_path_position + "position_snp.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/snp_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
			String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);

		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));
			fileToList.setString8(String8.get(i));

			list.add(fileToList);
		}
//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/analysis/position/eQTL/list")
	@ResponseBody
	public void queryanalysis_position_eqtl_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("eqtl_dataclass");
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP/eQTL/"
				+ dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > " + file_path_position
				+ "position_eqtl.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		ArrayList<String> String8 = new ArrayList<String>();

		List<String> listfile = File_read.read(file_path_position + "position_eqtl.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/snp_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
			String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);

		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));
			fileToList.setString8(String8.get(i));

			list.add(fileToList);
		}
//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/analysis/position/Methylation")
	public String queryanalysis_position_methylation(String dataclass, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {
		session.setAttribute("methylation_dataclass", dataclass);
		return "analysis_position_methylation_list";
	}

	@RequestMapping("/analysis/position/Methylation/list")
	@ResponseBody
	public void queryanalysis_position_methylation_list(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		String file_path_position = (String) session.getAttribute("file_path_position");
		String dataclass = (String) session.getAttribute("methylation_dataclass");
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/bedtools2/bedtools2-2.25.0/bin/bedtools intersect -a /mnt/data/GREAP/Methylation/"
				+ dataclass + ".bed -b " + file_path_position + "position.bed -wa -wb -bed > " + file_path_position
				+ "position_methylation.bed;");
		ArrayList<FileToList> list = new ArrayList<FileToList>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		ArrayList<String> String8 = new ArrayList<String>();
		List<String> listfile = File_read.read(file_path_position + "position_methylation.bed");
//	    List<String> listfile = File_read.read("E:/Rtest/Position/450K_result.bed");
		for (int i = 0; i < listfile.size(); i++) {
			String1.add(listfile.get(i).replaceAll("\t", ",").split(",")[0]);
			String2.add(listfile.get(i).replaceAll("\t", ",").split(",")[1]);
			String3.add(listfile.get(i).replaceAll("\t", ",").split(",")[2]);
			String4.add(listfile.get(i).replaceAll("\t", ",").split(",")[3]);
			String5.add(listfile.get(i).replaceAll("\t", ",").split(",")[4]);
			String6.add(listfile.get(i).replaceAll("\t", ",").split(",")[5]);
			String7.add(listfile.get(i).replaceAll("\t", ",").split(",")[6]);
			String8.add(listfile.get(i).replaceAll("\t", ",").split(",")[7]);
		}
		FileToList fileToList = null;
		for (int i = 0; i < listfile.size(); i++) {
			fileToList = new FileToList();
			fileToList.setString1(String1.get(i));
			fileToList.setString2(String2.get(i));
			fileToList.setString3(String3.get(i));
			fileToList.setString4(String4.get(i));
			fileToList.setString5(String5.get(i));
			fileToList.setString6(String6.get(i));
			fileToList.setString7(String7.get(i));
			fileToList.setString8(String8.get(i));

			list.add(fileToList);
		}
//	    ajax閹恒儲鏁归崐鍏肩壐瀵繐澧犻棃銏℃箒data
		Map map = new HashMap();
		map.put("data", list);
		Gson gsonnew = new Gson();
		String json = gsonnew.toJson(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	// ######################################################################################
	@RequestMapping("/analysis/veenchart")
	public String queryanalysis_veenchart(String chartset, int a, int b, int c, Model model) throws IOException {

		model.addAttribute("chartset", chartset);
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		model.addAttribute("c", c);
		return "analysis_chart_veen";
	}

	// ######################################################################################
	@RequestMapping("/analysis/browse")
	public String queryByBrowse(String grch, String datatype, String dataset, HttpServletRequest request,
			HttpSession session, Model model) {
		session.setAttribute("genome", grch);
		session.setAttribute("browse_grch", grch);
		session.setAttribute("browse_datatype", datatype);
		session.setAttribute("browse_dataset", dataset);
		
		String table = "hg38".equals(grch) ? "browselist" : "browselist19";
		
//  	闁鑵慳ctive		
		List<String> Activedatatype = new ArrayList<>();
		List<String> Activedataset = new ArrayList<>();

		List list1 = null;
		List list2 = null;

		if (datatype == "" && dataset == "") {
			list1 = searchService.queryByDatatype(datatype, dataset, table);
			list2 = searchService.queryByDataset(datatype, dataset, table);
		} else if (datatype != "" && dataset == "") {
			Activedatatype = Arrays.asList("active");
			list1 = searchService.queryByDatatype_1(datatype, dataset, table);
			list2 = searchService.queryByDataset_1(datatype, dataset, table);
		} else if (datatype == "" && dataset != "") {
			Activedataset = Arrays.asList("active");
			list1 = searchService.queryByDatatype_2(datatype, dataset, table);
			list2 = searchService.queryByDataset_2(datatype, dataset, table);

		} else if (datatype != "" && dataset != "") {
			Activedatatype = Arrays.asList("active");
			Activedataset = Arrays.asList("active");
			list1 = searchService.queryByDatatype_3(datatype, dataset, table);
			list2 = searchService.queryByDataset_3(datatype, dataset, table);
		}

		model.addAttribute("Activedatatype", Activedatatype);
		model.addAttribute("Activedataset", Activedataset);

		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		return "Browse";
	}

	@RequestMapping("/analysis/browselist")
	@ResponseBody
	public datatableResult queryByBrowselist(int start, int length, int draw, HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		String searchValue = request.getParameter("search[value]");
		System.out.println(searchValue);
		String datatype = (String) session.getAttribute("browse_datatype");
		String dataset = (String) session.getAttribute("browse_dataset");
		String grch = (String) session.getAttribute("genome");
		List list = null;

		String table = "hg38".equals(grch) ? "browselist" : "browselist19";
		
		datatableResult DatatableResult = new datatableResult();
		PageHelper.startPage(start / length + 1, length);

		if (datatype == "" && dataset == "") {
			list = searchService.queryBybrowselist(datatype, dataset, searchValue, table);

		} else if (datatype != "" && dataset == "") {
			list = searchService.queryBybrowselist_1(datatype, dataset, searchValue, table);
		} else if (datatype == "" && dataset != "") {
			list = searchService.queryBybrowselist_2(datatype, dataset, searchValue, table);

		} else if (datatype != "" && dataset != "") {
			list = searchService.queryBybrowselist_3(datatype, dataset, searchValue, table);
		}

		PageInfo pageInfo = new PageInfo(list);

		DatatableResult.setDraw(DatatableResult.getDraw());
		DatatableResult.setData(pageInfo.getList());
		DatatableResult.setRecordsTotal((int) pageInfo.getTotal());
		DatatableResult.setRecordsFiltered(DatatableResult.getRecordsTotal());

		return DatatableResult;
	}

	// ######################################################################################
	@RequestMapping("/analysis/download")
	public String queryByDownload(String grch, String datatype, String dataset, HttpServletRequest request,
			HttpSession session, Model model) {
		session.setAttribute("download_grch", grch);
//		session.setAttribute("download_datatype", datatype);
//		session.setAttribute("download_dataset", dataset);

////  	闁鑵慳ctive		
//		List<String> Activedatatype = new ArrayList<>();
//		List<String> Activedataset = new ArrayList<>();
//
//		List list1 = null;
//		List list2 = null;
//
//		if (datatype == "" && dataset == "") {
//			list1 = searchService.queryByDatatype(datatype, dataset);
//			list2 = searchService.queryByDataset(datatype, dataset);
//		} else if (datatype != "" && dataset == "") {
//			Activedatatype = Arrays.asList("active");
//			list1 = searchService.queryByDatatype_1(datatype, dataset);
//			list2 = searchService.queryByDataset_1(datatype, dataset);
//		} else if (datatype == "" && dataset != "") {
//			Activedataset = Arrays.asList("active");
//			list1 = searchService.queryByDatatype_2(datatype, dataset);
//			list2 = searchService.queryByDataset_2(datatype, dataset);
//
//		} else if (datatype != "" && dataset != "") {
//			Activedatatype = Arrays.asList("active");
//			Activedataset = Arrays.asList("active");
//			list1 = searchService.queryByDatatype_3(datatype, dataset);
//			list2 = searchService.queryByDataset_3(datatype, dataset);
//		}
//
//		model.addAttribute("Activedatatype", Activedatatype);
//		model.addAttribute("Activedataset", Activedataset);
//
//		model.addAttribute("list1", list1);
//		model.addAttribute("list2", list2);
		return "Download";
	}

	@RequestMapping("/analysis/downloadlist")
	@ResponseBody
	public datatableResult queryByDownloadlist(int start, int length, int draw, HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		String searchValue = request.getParameter("search[value]");
		String datatype = (String) session.getAttribute("download_datatype");
		String dataset = (String) session.getAttribute("download_dataset");
		List list = null;

		datatableResult DatatableResult = new datatableResult();
		PageHelper.startPage(start / length + 1, length);

		
		list = searchService.queryBybrowselist(datatype, dataset, searchValue, "browselist");

		

		PageInfo pageInfo = new PageInfo(list);

		DatatableResult.setDraw(DatatableResult.getDraw());
		DatatableResult.setData(pageInfo.getList());
		DatatableResult.setRecordsTotal((int) pageInfo.getTotal());
		DatatableResult.setRecordsFiltered(DatatableResult.getRecordsTotal());

		return DatatableResult;
	}
	@RequestMapping("/analysis/downloadlist1")
	@ResponseBody
	public datatableResult queryByDownloadlist1(int start, int length, int draw, HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		String searchValue = request.getParameter("search[value]");
		List list = null;

		datatableResult DatatableResult = new datatableResult();
		PageHelper.startPage(start / length + 1, length);

		list = searchService.queryBydownlist1(searchValue);

		PageInfo pageInfo = new PageInfo(list);

		DatatableResult.setDraw(DatatableResult.getDraw());
		DatatableResult.setData(pageInfo.getList());
		DatatableResult.setRecordsTotal((int) pageInfo.getTotal());
		DatatableResult.setRecordsFiltered(DatatableResult.getRecordsTotal());

		return DatatableResult;
	}
	@RequestMapping("/analysis/downloadlist2")
	@ResponseBody
	public datatableResult queryByDownloadlist2(int start, int length, int draw, HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		String searchValue = request.getParameter("search[value]");
		List list = null;

		datatableResult DatatableResult = new datatableResult();
		PageHelper.startPage(start / length + 1, length);

		list = searchService.queryBydownlist2(searchValue);


		PageInfo pageInfo = new PageInfo(list);

		DatatableResult.setDraw(DatatableResult.getDraw());
		DatatableResult.setData(pageInfo.getList());
		DatatableResult.setRecordsTotal((int) pageInfo.getTotal());
		DatatableResult.setRecordsFiltered(DatatableResult.getRecordsTotal());

		return DatatableResult;
	}
	@RequestMapping("/search/regulation")
	@ResponseBody
	public List<String> queryRegulation() {
		List list = null;
		list = searchService.queryRegulation();
		return list;
	}
//	@RequestMapping("/search/tfname")
//	@ResponseBody
//	public void querytfname(HttpServletResponse response) throws IOException{
//		List list=null;
//		list = searchService.querytfname();
//		
//		Gson gson = new Gson(); //杞琷son閿熸枻鎷峰紡閿熸枻鎷烽敓鑴氱鎷烽敓鏂ゆ嫹閫夐敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
//        String data = gson.toJson(list);
//        PrintWriter out = response.getWriter();
//		out.print(data);
//		out.close();
//	}

	// ######################### search by lncnames
	// #######################################
//	閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鍙鎷� regulation 閿熸枻鎷穕ncnames閿熸枻鎷穝ource
	@RequestMapping("/search/LncName")
	public String queryByLncName(String regulation, String lncnames, String source, HttpSession session, Model model) {
		session.setAttribute("regulation", regulation);
		session.setAttribute("lncnames", lncnames);
		session.setAttribute("source", source);
		List list = null;
		List list1 = null;
		List list2 = null;
		List list3 = null;
//total-result 閿熻緝鍑ゆ嫹閿熶茎鎲嬫嫹閿燂拷 閿熸枻鎷� 閿熸枻鎷烽敓鎺ヤ紮鎷�
		List list4 = null;
		List list5 = null;

		String[] params = lncnames.split("\r\n");
//		###### 閿熷彨璁规嫹閿熸枻鎷稧ENCODE閿熸枻鎷烽敓鏂ゆ嫹NONCODE ######
		if (source.equals("GENCODE")) {
//	    ###### 閿熷彨璁规嫹lncnames閿熻纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻璁规嫹閿燂拷 ######
			list = searchService.queryByLncName_G(source, regulation, params);
			list1 = searchService.queryBypromoter_G(source, regulation, params);
			list2 = searchService.queryByse_G(source, params);
			list3 = searchService.queryBychromatin_G(source, params);
			list4 = searchService.queryByresult_left(source, regulation, params);
			list5 = searchService.queryByresult_right(source, params);

		} else if (source.equals("NONCODE")) {
			list = searchService.queryByLncName_N(source, regulation, params);
			list1 = searchService.queryBypromoter_N(source, regulation, params);
			list2 = searchService.queryByse_N(source, params);
			list3 = searchService.queryBychromatin_N(source, params);
			list4 = searchService.queryByresult_left(source, regulation, params);
			list5 = searchService.queryByresult_right(source, params);
		} else {
			list1 = searchService.queryBypromoter_A(source, regulation, params);
			list2 = searchService.queryByse_A(source, params);
			list3 = searchService.queryBychromatin_A(source, params);
			list4 = searchService.queryByresult_left_A(regulation, params);
			list5 = searchService.queryByresult_right_A(params);
		}

		model.addAttribute("list", list);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);

		if (source.equals("GENCODE")) {
			if (lncnames.contains("\r\n")) {
				return "Lncnameresult";
			} else {
				return "LncNameResultG";
			}
		} else if (source.equals("NONCODE")) {
			if (lncnames.contains("\r\n")) {
				return "Lncnameresult";
			} else {
				return "LncNameResultN";
			}
		} else {
			return "Lncnameresult";
		}

	}

	// ###################### 閿熷彨鎲嬫嫹椤甸敓渚ュ尅鎷烽敓鎺ヤ紮鎷烽敓鏂ゆ嫹閿熸枻鎷� ######################
	@RequestMapping("/search/LncName/promoterV")
	public String queryBypromoterV(String regulation, String lncnames, String source, HttpSession session,
			Model model) {

		List list1 = null;

		String[] params = lncnames.split("\r\n");

		if (source.equals("GENCODE")) {
			list1 = searchService.queryBypromoter_G(source, regulation, params);
		} else if (source.equals("NONCODE")) {
			list1 = searchService.queryBypromoter_N(source, regulation, params);
		} else {
			list1 = searchService.queryBypromoter_A(source, regulation, params);
		}

		model.addAttribute("list1", list1);

		return "promoter-result";
	}

	@RequestMapping("/search/LncName/SEV")
	public String queryBySEV(String regulation, String lncnames, String source, HttpSession session, Model model) {

		List list2 = null;

		String[] params = lncnames.split("\r\n");

		if (source.equals("GENCODE")) {
			list2 = searchService.queryByse_G(source, params);
		} else if (source.equals("NONCODE")) {
			list2 = searchService.queryByse_N(source, params);
		} else {
			list2 = searchService.queryByse_A(source, params);
		}

		model.addAttribute("list2", list2);

		return "se-result";
	}

	@RequestMapping("/search/LncName/chromatinV")
	public String queryBychromatinV(String regulation, String lncnames, String source, HttpSession session,
			Model model) {

		List list3 = null;

		String[] params = lncnames.split("\r\n");

		if (source.equals("GENCODE")) {
			list3 = searchService.queryBychromatin_G(source, params);
		} else if (source.equals("NONCODE")) {
			list3 = searchService.queryBychromatin_N(source, params);
		} else {
			list3 = searchService.queryBychromatin_A(source, params);
		}

		model.addAttribute("list3", list3);

		return "Chromatin-result";
	}

	// ######################### search by TF
	// #######################################
	@RequestMapping("/search/tfname")
	@ResponseBody
	public List<String> querytfname() {
		List list = null;
		list = searchService.querytfname();
		return list;
	}

	@RequestMapping("/search/type")
	@ResponseBody
	public List<String> querytype(String tfname) {
		List list = null;
		list = searchService.querytype(tfname);
		return list;
	}

	@RequestMapping("/search/TF_name")
	public String queryByTF_name(String regulation, String tfnames, String source, String type, HttpSession session,
			Model model) {

		session.setAttribute("tfregulation", regulation);
		session.setAttribute("tfnames", tfnames);
		session.setAttribute("tfsource", source);
		session.setAttribute("tftype", type);

		String Region = regulation;
		String TFnames = tfnames;

//     閿熻緝鍑ゆ嫹閿熶茎鎲嬫嫹閿燂拷 閿熸枻鎷� 閿熸枻鎷烽敓鎺ヤ紮鎷�		
		List list3 = null;

		if (source.equals("GENCODE")) {
			list3 = searchService.queryByTF_left(tfnames);
		} else if (source.equals("NONCODE")) {
			list3 = searchService.queryByTF_left(tfnames);
		} else {
			list3 = searchService.queryByTF_left(tfnames);

		}

		model.addAttribute("Region", Region);
		model.addAttribute("TFnames", TFnames);

		model.addAttribute("list3", list3);

		return "TFnameResult";

	}

	@RequestMapping("/search/TF_name/chart")
	public String queryByTF_namechart(String chr, String pro_tablename, String other_tablename,
			HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {

		String regulation = (String) session.getAttribute("tfregulation");
		String tfnames = (String) session.getAttribute("tfnames");
		String source = (String) session.getAttribute("tfsource");
		String type = (String) session.getAttribute("tftype");

		if (chr == null || chr.equals("")) {
			chr = "chr1";
		}
		String Region = regulation;
		String TFnames = tfnames;

		List list4 = null;
		List list5 = null;

		List listpro = null;
		List listother = null;

		listpro = searchService.queryByTF_name_pro_tablename(source, chr);
		pro_tablename = listpro.toString().replace("[", "").replace("]", "");

		listother = searchService.queryByTF_name_other_tablename(source, chr);
		other_tablename = listother.toString().replace("[", "").replace("]", "");

		if (source.equals("GENCODE")) {
			list4 = searchService.queryByTF_right_edge(pro_tablename, regulation, tfnames, type, other_tablename);
			list5 = searchService.queryByTF_right_node(pro_tablename, regulation, tfnames, type, other_tablename);
		} else if (source.equals("NONCODE")) {
			list4 = searchService.queryByTF_right_edge(pro_tablename, regulation, tfnames, type, other_tablename);
			list5 = searchService.queryByTF_right_node(pro_tablename, regulation, tfnames, type, other_tablename);
		} else {
			list4 = searchService.queryByTF_right_edge_A(regulation, tfnames, type, chr);
			list5 = searchService.queryByTF_right_node_A(regulation, tfnames, type, chr);

		}
		model.addAttribute("Region", Region);
		model.addAttribute("TFnames", TFnames);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);

		return "TFnameResultchart";

	}

	@RequestMapping("/search/TF_name/list")
	public String queryByTF_namelist(String chr, String pro_tablename, String other_tablename, HttpSession session,
			Model model) {

		String regulation = (String) session.getAttribute("tfregulation");
		String tfnames = (String) session.getAttribute("tfnames");
		String source = (String) session.getAttribute("tfsource");
		String type = (String) session.getAttribute("tftype");

		if (chr == null || chr.equals("")) {
			chr = "chr1";
		}
		List list = null;
		List list1 = null;
		List list2 = null;
		List listpro = null;
		List listother = null;

		listpro = searchService.queryByTF_name_pro_tablename(source, chr);
		pro_tablename = listpro.toString().replace("[", "").replace("]", "");

		listother = searchService.queryByTF_name_other_tablename(source, chr);
		other_tablename = listother.toString().replace("[", "").replace("]", "");

		if (source.equals("GENCODE")) {
			list = searchService.queryByTF_promoter_G(pro_tablename, other_tablename, regulation, tfnames, type);
			list1 = searchService.queryByTF_se_G(pro_tablename, other_tablename, regulation, tfnames, type);
			list2 = searchService.queryByTF_chromatin_G(pro_tablename, other_tablename, regulation, tfnames, type);

		} else if (source.equals("NONCODE")) {
			list = searchService.queryByTF_promoter_N(pro_tablename, other_tablename, regulation, tfnames, type);
			list1 = searchService.queryByTF_se_N(pro_tablename, other_tablename, regulation, tfnames, type);
			list2 = searchService.queryByTF_chromatin_N(pro_tablename, other_tablename, regulation, tfnames, type);
		} else {
			list = searchService.queryByTF_promoter_A(regulation, tfnames);
			list1 = searchService.queryByTF_se_A(regulation, tfnames);
			list2 = searchService.queryByTF_chromatin_A(regulation, tfnames);

		}
		model.addAttribute("list", list);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);

		return "TFnameResultlist";

	}

	@RequestMapping("/search/SNPID")
	public String queryBySNPID(String regulation, String snpid, String source, HttpSession session, Model model) {

		String Region = regulation;
		String snpID = snpid;

		List list = null;
		List list1 = null;
		List list2 = null;
//     閿熻緝鍑ゆ嫹閿熶茎鎲嬫嫹閿燂拷 閿熸枻鎷� 閿熸枻鎷烽敓鎺ヤ紮鎷�		
		List list3 = null;
		List list4 = null;
		List list5 = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryBySNP_promoter_G(regulation, snpid);
			list1 = searchService.queryBySNP_se_G(regulation, snpid);
			list2 = searchService.queryBySNP_chromatin_G(regulation, snpid);
			list3 = searchService.queryBySNP_left(snpid);
			list4 = searchService.queryBySNP_right_edge(source, regulation, snpid);
			list5 = searchService.queryBySNP_right_node(source, regulation, snpid);
		} else if (source.equals("NONCODE")) {
			list = searchService.queryBySNP_promoter_N(regulation, snpid);
			list1 = searchService.queryBySNP_se_N(regulation, snpid);
			list2 = searchService.queryBySNP_chromatin_N(regulation, snpid);
			list3 = searchService.queryBySNP_left(snpid);
			list4 = searchService.queryBySNP_right_edge(source, regulation, snpid);
			list5 = searchService.queryBySNP_right_node(source, regulation, snpid);

		} else {
			list = searchService.queryBySNP_promoter_A(regulation, snpid);
			list1 = searchService.queryBySNP_se_A(regulation, snpid);
			list2 = searchService.queryBySNP_chromatin_A(regulation, snpid);
			list3 = searchService.queryBySNP_left(snpid);
			list4 = searchService.queryBySNP_right_edge_A(regulation, snpid);
			list5 = searchService.queryBySNP_right_node_A(regulation, snpid);

		}
		model.addAttribute("Region", Region);
		model.addAttribute("snpID", snpID);

		model.addAttribute("list", list);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);

		return "SNPidResult";

	}

	@RequestMapping("/search/Enhancer/Biosample_Type")
	@ResponseBody
	public List<String> queryEnhancerBiosample_Type() {
		List list = null;
		list = searchService.queryEnhancerBiosample_Type();
		return list;
	}

	@RequestMapping("/search/Enhancer/Biosample_Name")
	@ResponseBody
	public List<String> queryEnhancerBiosample_Name(String Biosample_Type) {
		List list = null;
		list = searchService.queryEnhancerBiosample_Name(Biosample_Type);
		return list;
	}

	@RequestMapping("/search/Enhancer")
	public String queryByenhancer(String se_tablename, String lnc, String Biosample_Type, String Biosample_Name,
			String chr, String start, String end, String source, HttpSession session, Model model) {

		session.setAttribute("enhancerBiosample_Type", Biosample_Type);
		session.setAttribute("enhancerBiosample_Name", Biosample_Name);
		session.setAttribute("enhancerchr", chr);
		session.setAttribute("enhancerstart", start);
		session.setAttribute("enhancerend", end);
		session.setAttribute("enhancersource", source);

		List list3 = null;
		List list4 = null;
		List list5 = null;
		List listSE = null;// 閿熸枻鎷稴E閿熸枻鎷烽敓鏂ゆ嫹

		listSE = searchService.queryBySE_tablename(source, Biosample_Type, Biosample_Name);
		se_tablename = listSE.toString().replace("[", "").replace("]", "");
		if (se_tablename.equals("")) {
			se_tablename = "SE_null";
		}

		list3 = searchService.queryByenhancer_left(chr, start, end, se_tablename);
		list4 = searchService.queryByenhancer_right_node(chr, start, end, se_tablename);
		list5 = searchService.queryByenhancer_right_edge(chr, start, end, se_tablename);

		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);

		if (list4.size() == 0) {
			lnc = "";
			session.setAttribute("Chromatinlnc", lnc);
			return "EnhancerResult";
		} else {
			lnc = datachuli.toStringToTremValues(0, list4.toString());
			session.setAttribute("enhancerlnc", lnc);
			return "EnhancerResult";
		}
	}

	@RequestMapping("/search/Enhancer/list")
	public String queryByenhancerlist(String regulation, HttpSession session, Model model) {

		String source = (String) session.getAttribute("enhancersource");
		String lnc = (String) session.getAttribute("enhancerlnc");

		if (regulation == null || regulation.equals("")) {
			regulation = "2kb/1kb";
		}
		if (lnc == null || lnc.equals("")) {
			lnc = "null";
		}
		String Region = regulation;

		List list = null;
		List list1 = null;
		List list2 = null;

		if (source.equals("GENCODE")) {

			list = searchService.queryByenhancer_promoter_G(lnc, regulation);
			list1 = searchService.queryByenhancer_se_G(lnc);
			list2 = searchService.queryByenhancer_chromatin_G(lnc);
		} else if (source.equals("NONCODE")) {
			list = searchService.queryByenhancer_promoter_N(lnc, regulation);
			list1 = searchService.queryByenhancer_se_N(lnc);
			list2 = searchService.queryByenhancer_chromatin_N(lnc);

		}

		model.addAttribute("Region", Region);
		model.addAttribute("list", list);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);

		return "EnhancerlistResult";
	}

	@RequestMapping("/search/Chromatin/Biosample_Type")
	@ResponseBody
	public List<String> queryChromatinBiosample_Type() {
		List list = null;
		list = searchService.queryChromatinBiosample_Type();
		return list;
	}

	@RequestMapping("/search/Chromatin/Biosample_Name")
	@ResponseBody
	public List<String> queryChromatinBiosample_Name(String bio_Type) {
		List list = null;
		list = searchService.queryChromatinBiosample_Name(bio_Type);
		return list;
	}

	@RequestMapping("/search/Chromatin")
	public String queryBych(String lnc, String A_tablename, String D_tablename, String bio_Type, String bio_Name,
			String chr, String start, String end, String method, String source, HttpSession session, Model model) {

		session.setAttribute("Chromatinchr", chr);
		session.setAttribute("Chromatinstart", start);
		session.setAttribute("Chromatinend", end);
		session.setAttribute("Chromatinmethod", method);
		session.setAttribute("Chromatinsource", source);

//     閿熻緝鍑ゆ嫹閿熶茎鎲嬫嫹閿燂拷 閿熸枻鎷� 閿熸枻鎷烽敓鎺ヤ紮鎷�		
		List list3 = null;
		List list4 = null;
		List list5 = null;
		List listA = null;
		List listD = null;
		// DNasEI閿熶茎鎲嬫嫹閿熸枻鎷�
		listD = searchService.queryByChromatin_tablename(bio_Type, bio_Name);
		D_tablename = listD.toString().replace("[", "").replace("]", "");
		if (D_tablename.equals("")) {
			D_tablename = "table_null";
		}
		// ATAC閿熶茎鎲嬫嫹閿熸枻鎷�
		listA = searchService.queryByChromatin_tablenameATAC(bio_Type, bio_Name);
		A_tablename = listA.toString().replace("[", "").replace("]", "");
		if (A_tablename.equals("")) {
			A_tablename = "table_null";
		}

		list3 = searchService.queryBych_left(chr, start, end, source, D_tablename, A_tablename);
		list4 = searchService.queryBych_right_node(chr, start, end, source, D_tablename, A_tablename);
		list5 = searchService.queryBych_right_edge(chr, start, end, source, D_tablename, A_tablename);

		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);

		if (list4.size() == 0) {
			lnc = "";
			session.setAttribute("Chromatinlnc", lnc);
			return "ChromatinResult";
		} else {
			lnc = datachuli.toStringToTremValues(0, list4.toString());
			session.setAttribute("Chromatinlnc", lnc);
			return "ChromatinResult";
		}
	}

	@RequestMapping("/search/Chromatin/list")
	public String queryBychlist(String regulation, HttpSession session, Model model) {

		String lnc = (String) session.getAttribute("Chromatinlnc");
		String source = (String) session.getAttribute("Chromatinsource");

		if (regulation == null || regulation.equals("")) {
			regulation = "2kb/1kb";
		}
		if (lnc == null || lnc.equals("")) {
			lnc = "null";
		}
		String Region = regulation;

		List list = null;
		List list1 = null;
		List list2 = null;

		list = searchService.queryBych_promoter(lnc, source, regulation);
		list1 = searchService.queryBych_se(lnc, source);
		list2 = searchService.queryBych_chromatin(lnc, source);

		model.addAttribute("Region", Region);

		model.addAttribute("list", list);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);

		return "ChromatinlistResult";

	}

	// #######################detail椤甸敓鏂ゆ嫹鐘跺浘閿熸枻鎷烽敓鏂ゆ嫹
	// ##########閿熸枻鎷烽敓鏂ゆ嫹閿熸埅闈╂嫹#################################
	@RequestMapping("/search/LncName/Pro-number")
	public void queryByPro_number(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;
		if (source.equals("GENCODE")) {
			list = searchService.queryByPro_number_G(regulation, lncnames);
		} else {
			list = searchService.queryByPro_number_N(regulation, lncnames);
		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/search/LncName/SE-number")
	public void queryBySE_number(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {

		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;
		if (source.equals("GENCODE")) {
			list = searchService.queryBySE_number_G(lncnames);
		} else {
			list = searchService.queryBySE_number_N(lncnames);
		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	@RequestMapping("/search/LncName/Ch-number")
	public void queryByCh_number(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {

		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;
		if (source.equals("GENCODE")) {
			list = searchService.queryByCh_number_G(lncnames);
		} else {
			list = searchService.queryByCh_number_N(lncnames);
		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	// #######################detail椤甸敓鏂ゆ嫹鐘跺浘閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�#################################
	@RequestMapping("/search/disease/dis")
	@ResponseBody
	public List<String> queryBySearchDiseasedis() {
		List list = null;
		list = searchService.queryBySearchDiseasedis();
		return list;
	}

	@RequestMapping("/search/disease")
	public String queryBySearchDisease(String disease, String source, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		session.setAttribute("disease", disease);
		session.setAttribute("diseasesource", source);

		return "Diseaseresult";
	}

	@RequestMapping("/search/disease/list")
	public String queryBySearchDiseaselist(String regulation, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		List list = null;
		String disease = (String) session.getAttribute("disease");
		String source = (String) session.getAttribute("diseasesource");

		if (regulation == null || regulation.equals("")) {
			regulation = "2kb/1kb";
		}
		if (source.equals("GENCODE")) {
			list = searchService.queryBySearchDisease_G(disease);
		} else {
			list = searchService.queryBySearchDisease_N(disease);
		}

		model.addAttribute("Regulation", regulation);
		model.addAttribute("list", list);

		return "Diseaseresultlist";
	}

	@RequestMapping("/search/LncName/disease")
	public String queryBydisease(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;
		if (source.equals("GENCODE")) {
			list = searchService.queryBydisease_G(lncnames);
		} else {
			list = searchService.queryBydisease_N(lncnames);
		}

		model.addAttribute("list", list);

		return "disease";
	}

	@RequestMapping("/search/LncName/mirna")
	public String queryBymirna(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;
		if (source.equals("GENCODE")) {
			list = searchService.queryBymirna_G(lncnames);
		} else {
			list = searchService.queryBymirna_N(lncnames);
		}
		model.addAttribute("list", list);

		return "mirna";

	}

	@RequestMapping("/search/LncName/localization")
	public String queryBylocalization(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");

		List list = null;

		list = searchService.queryBylocalization(lncnames, source);

		model.addAttribute("list", list);

		return "localization";

	}

	@RequestMapping("/search/LncName/rbp")
	public String queryByrbp(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		list = searchService.queryByrbp(lncnames, source);

		model.addAttribute("list", list);

		return "rbp";

	}

	// ################################# 缁嗛敓鏂ゆ嫹椤� total閿熸枻鎷烽敓鏂ゆ嫹iframe
	// #################################
//  ###### 閿熸澃纰夋嫹 Biosample_Type 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######	
	@RequestMapping("/search/Biosample_Type")
	@ResponseBody
	public List<String> queryBiosample_Type() {
		List list = null;
		list = searchService.queryBiosample_Type();
		return list;
	}

	@RequestMapping("/search/tiss")
	@ResponseBody
	public List<String> querytiss(String Biosample_Type) {
		List list = null;
		list = searchService.querytiss(Biosample_Type);
		return list;
	}

	// ###### 閿熸澃纰夋嫹 Biosample_Name 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######
	@RequestMapping("/search/Biosample_Name")
	@ResponseBody
	public List<String> queryBiosample_Name(String Biosample_Type, String tiss) {
		List list = null;
		list = searchService.queryBiosample_Name(Biosample_Type, tiss);
		return list;
	}

	// ###### 閿熸澃纰夋嫹iframe 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鍙鎷� Biosample_Name ######
	@RequestMapping("/search/LncName/total_iframe")
	public String queryBytotal_iframe(String tiss, String Biosample_Name, String Biosample_Type,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {

		session.setAttribute("Biosample_Name", Biosample_Name);
		session.setAttribute("Biosample_Type", Biosample_Type);
		session.setAttribute("tiss", tiss);

		return "total-iframe";

	}

	// ################################# Promoter閿熸枻鎷烽敓鏂ゆ嫹閿熺獤锟�
	// #################################
//    ###### DNaseI 閿熸枻鎷烽〉閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######
	@RequestMapping("/search/LncName/DNaseI")
	public void queryByDNaseI(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String Biosample_Type, Model model) throws IOException {

		String Biosample_Name = request.getParameter("Biosample_Name");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		String[] params = Biosample_Name.split(",");

		if (source.equals("GENCODE")) {
			list = searchService.queryByDNaseI_GG(regulation, lncnames, Biosample_Type, params);

		} else {
			list = searchService.queryByDNaseI_NN(regulation, lncnames, Biosample_Type, params);
		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	// ###### TF 閿熸枻鎷烽〉閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######
	@RequestMapping("/search/tfid")
	@ResponseBody
	public List<String> querytfid(HttpSession session) {
		List list = null;
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		list = searchService.querytfid(Biosample_Name);
		return list;
	}

	@RequestMapping("/search/LncName/TF")

	public String queryByTF(String tf_tablename, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
//		int number;
//		List listid=null;
//		
//        if(ID == null || ID.equals("") ){
//        	listid = searchService.querytfidmap(Biosample_Name_TF);
//        	Gson gson = new Gson();
//			String json = gson.toJson(listid);
//			String[] split = json.split(",");
//			List<String> roleList = new ArrayList<>();
//			for (int i = 0 ; i < split.length ; i++){
//				String str0 = split[i].substring(split[i].indexOf(":")+1);
//				str0 = str0.replaceAll("}]", "");
//				str0 = str0.replaceAll("}", "");
//				roleList.add(str0);
//			}
//			number = roleList.size()/2;
//			ID = roleList.get(number).toString().substring(1,roleList.get(number).toString().length()-1);
//        }
		List listTF = null;
		List list = null;
		listTF = searchService.queryByChromatin_tf_tablename(Biosample_Type, Biosample_Name);
		tf_tablename = listTF.toString().replace("[", "").replace("]", "");
		if (tf_tablename.equals("")) {
			tf_tablename = "TF_null";
		}
		if (source.equals("GENCODE")) {
			list = searchService.queryByTF_GG(regulation, lncnames, tf_tablename);
		} else {
			list = searchService.queryByTF_NN(regulation, lncnames, tf_tablename);
		}

		model.addAttribute("list", list);

		return "promoter-tftable";

	}

	// ###### 450k 閿熸枻鎷烽〉閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######
	@RequestMapping("/search/LncName/Methylation_450k")
	public String queryByMethylation_450k(String tablename_450k, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;
		List list450k = null;

		list450k = searchService.queryBytablename_450k(Biosample_Type, Biosample_Name);
		tablename_450k = list450k.toString().replace("[", "").replace("]", "");
		if (tablename_450k.equals("")) {
			tablename_450k = "450K_null";
		}
		if (source.equals("GENCODE")) {
			list = searchService.queryByMethylation_450k_G(regulation, lncnames, tablename_450k);
		} else {
			list = searchService.queryByMethylation_450k_N(regulation, lncnames, tablename_450k);
		}
		model.addAttribute("tablename_450k", tablename_450k);
		model.addAttribute("list", list);

		return "promoter-450k";
	}

	// ###### WGBS 閿熸枻鎷烽〉閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######
	@RequestMapping("/search/LncName/Methylation_WGBS")
	public String queryByMethylation_WGBS(String tablename_WGBS, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;
		List listWGBS = null;

		listWGBS = searchService.queryBytablename_WGBS(Biosample_Type, Biosample_Name);
		tablename_WGBS = listWGBS.toString().replace("[", "").replace("]", "");
		if (tablename_WGBS.equals("")) {
			tablename_WGBS = "WGBS_null";
		}
		if (source.equals("GENCODE")) {
			list = searchService.queryByMethylation_WGBS_G(regulation, lncnames, tablename_WGBS);
		} else {
			list = searchService.queryByMethylation_WGBS_N(regulation, lncnames, tablename_WGBS);
		}
		model.addAttribute("tablename_WGBS", tablename_WGBS);
		model.addAttribute("list", list);
		return "promoter-wgbs";
	}

	// ###### EpiTensor 閿熸枻鎷烽〉閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######
	@RequestMapping("/search/LncName/EpiTensor")
	public String queryByEpiTensor(String inter_tablename, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String tiss = (String) session.getAttribute("tiss");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");

		List list = null;
		List listinter = null;

		listinter = searchService.queryBytablename_inter(tiss);
		inter_tablename = listinter.toString().replace("[", "").replace("]", "");
		if (inter_tablename.equals("")) {
			inter_tablename = "inter_null";
		}

		if (source.equals("GENCODE")) {
			list = searchService.queryByEpiTensor_G(regulation, lncnames, inter_tablename);
		} else {
			list = searchService.queryByEpiTensor_N(regulation, lncnames, inter_tablename);
		}

		model.addAttribute("list", list);

		return "promoter-EpiTensor";
	}

	// ###### Motif 閿熸枻鎷烽〉閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######
	@RequestMapping("/search/LncName/Motif")
	public String queryByMotif(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByMotif_G(regulation, lncnames);
		} else {
			list = searchService.queryByMotif_N(regulation, lncnames);
		}

		model.addAttribute("list", list);
		return "promoter-Motif";
	}

	// ###### SNP 閿熸枻鎷烽〉閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######
	@RequestMapping("/search/LncName/SNP")
	public String queryBySNP(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryBySNP_G(regulation, lncnames);
		} else {
			list = searchService.queryBySNP_N(regulation, lncnames);
		}

		model.addAttribute("list", list);

		return "promoter-SNP";

	}

	@RequestMapping("/search/LncName/RiskSNP")
	public String queryByRiskSNP(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByRiskSNP_G(regulation, lncnames);
		} else {
			list = searchService.queryByRiskSNP_N(regulation, lncnames);
		}
		model.addAttribute("list", list);
		return "promoter-RiskSNP";
	}

	@RequestMapping("/search/LncName/eqtl")
	public String queryByeqtl(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByeqtl_G(regulation, lncnames);
		} else {
			list = searchService.queryByeqtl_N(regulation, lncnames);
		}

		model.addAttribute("list", list);
		return "promoter-eqtl";
	}

	@RequestMapping("/search/LncName/afr")
	public String queryByafr(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByafr_G(regulation, lncnames);
		} else {
			list = searchService.queryByafr_N(regulation, lncnames);
		}
		model.addAttribute("list", list);
		return "promoter-afr";
	}

	@RequestMapping("/search/LncName/amr")
	public String queryByamr(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByamr_G(regulation, lncnames);
		} else {
			list = searchService.queryByamr_N(regulation, lncnames);
		}

		model.addAttribute("list", list);
		return "promoter-amr";
	}

	@RequestMapping("/search/LncName/eas")
	public String queryByeas(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByeas_G(regulation, lncnames);
		} else {
			list = searchService.queryByeas_N(regulation, lncnames);
		}

		model.addAttribute("list", list);
		return "promoter-eas";
	}

	@RequestMapping("/search/LncName/eur")
	public String queryByeur(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByeur_G(regulation, lncnames);
		} else {
			list = searchService.queryByeur_N(regulation, lncnames);
		}

		model.addAttribute("list", list);
		return "promoter-eur";
	}

	@RequestMapping("/search/LncName/sas")
	public String queryBysas(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryBysas_G(regulation, lncnames);
		} else {
			list = searchService.queryBysas_N(regulation, lncnames);
		}

		model.addAttribute("list", list);
		return "promoter-sas";
	}

	// ############################閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷稴NP閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎺ヨ鎷疯###############################
	@RequestMapping("/search/LncName/SNP/AFR")
	public String queryByAFR(HttpSession session, String ID, Model model) {

		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByAFR_G(ID);
		} else {
			list = searchService.queryByAFR_N(ID);
		}
		model.addAttribute("list", list);
		return "promoter-SNP-AFR";
	}

	@RequestMapping("/search/LncName/SNP/AMR")
	public String queryByAMR(HttpSession session, String ID, Model model) {

		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByAMR_G(ID);
		} else {
			list = searchService.queryByAMR_N(ID);
		}
		model.addAttribute("list", list);
		return "promoter-SNP-AMR";
	}

	@RequestMapping("/search/LncName/SNP/EAS")
	public String queryByEAS(HttpSession session, String ID, Model model) {

		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByEAS_G(ID);
		} else {
			list = searchService.queryByEAS_N(ID);
		}
		model.addAttribute("list", list);
		return "promoter-SNP-EAS";
	}

	@RequestMapping("/search/LncName/SNP/EQTL")
	public String queryByEQTL(HttpSession session, String ID, Model model) {

		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByEQTL_G(ID);
		} else {
			list = searchService.queryByEQTL_N(ID);
		}
		model.addAttribute("list", list);
		return "promoter-SNP-EQTL";
	}

	@RequestMapping("/search/LncName/SNP/RISKSNP")
	public String queryByRISKSNP(HttpSession session, String ID, Model model) {

		String source = (String) session.getAttribute("source");
		List list = null;
		if (source.equals("GENCODE")) {
			list = searchService.queryByRISKSNP_G(ID);
		} else {
			list = searchService.queryByRISKSNP_N(ID);
		}
		model.addAttribute("list", list);
		return "promoter-SNP-RISKSNP";
	}

	@RequestMapping("/search/LncName/SNP/EUR")
	public String queryByEUR(HttpSession session, String ID, Model model) {

		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByEUR_G(ID);
		} else {
			list = searchService.queryByEUR_N(ID);
		}
		model.addAttribute("list", list);
		return "promoter-SNP-EUR";
	}

	@RequestMapping("/search/LncName/SNP/SAS")
	public String queryBySAS(HttpSession session, String ID, Model model) {

		String source = (String) session.getAttribute("source");
		List list = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryBySAS_G(ID);
		} else {
			list = searchService.queryBySAS_N(ID);
		}
		model.addAttribute("list", list);
		return "promoter-SNP-SAS";
	}

//		     ###### Histone 閿熸枻鎷烽〉閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷� ######

	@RequestMapping("/search/LncName/Histone")
	public String queryByHistone(String histone, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");

		if (histone == null || histone.equals("")) {
			histone = "H3K4me3";
		}

		List list = null;
		if (source.equals("GENCODE")) {
			list = searchService.queryByHistone_G(regulation, lncnames, Biosample_Type, Biosample_Name, histone);
		} else {
			list = searchService.queryByHistone_N(regulation, lncnames, Biosample_Type, Biosample_Name, histone);
		}

		model.addAttribute("list", list);

		return "promoter-Histonetable";
	}

	// ################################# Promoter閿熸枻鎷烽敓鏂ゆ嫹閿熺獤顖ゆ嫹閿熸枻鎷�
	// #################################
	// ###### Enhancer 閿熸枻鎷烽敓鏂ゆ嫹 閿熸枻鎷烽敓鏂ゆ嫹杩滈敓鏂ゆ嫹璇㈤敓鏂ゆ嫹涓轰竴閿熺殕璁规嫹閿熺獤锟� ######
	@RequestMapping("/search/se_position")
	public void querytest(String se_id, String chr, String start, String end, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		session.setAttribute("se_position_chr", chr);
		session.setAttribute("se_position_start", start);
		session.setAttribute("se_position_end", end);
		session.setAttribute("se_position_id", se_id);

	}

	// ################################# SE閿熸枻鎷烽敓鏂ゆ嫹閿熺獤锟�
	// #################################
	@RequestMapping("/search/LncName/SE")

	public String queryBySE(String se_tablename, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");

		List list = null;
		List listSE = null;// 閿熸枻鎷稴E閿熸枻鎷烽敓鏂ゆ嫹

		listSE = searchService.queryBySE_tablename(source, Biosample_Type, Biosample_Name);
		se_tablename = listSE.toString().replace("[", "").replace("]", "");
		if (se_tablename.equals("")) {
			se_tablename = "SE_null";
		}

		list = searchService.queryBySE(lncnames, se_tablename);

		model.addAttribute("list", list);

		return "SE-se";
	}

	@RequestMapping("/search/LncName/SE/TF")
	public String queryBySE_TF(String tf_tablename, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");

		List listTF = null;
		List list = null;
		listTF = searchService.queryByChromatin_tf_tablename(Biosample_Type, Biosample_Name);
		tf_tablename = listTF.toString().replace("[", "").replace("]", "");
		if (tf_tablename.equals("")) {
			tf_tablename = "TF_null";
		}
		list = searchService.queryBySE_TF_G(chr, start, end, tf_tablename);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-TFtable";

	}

	@RequestMapping("/search/LncName/SE/SNP")
	public String queryBySE_SNP(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");

		List list = null;

		list = searchService.queryBySE_SNP_G(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-SNP";
	}

	@RequestMapping("/search/LncName/SE/RiskSNP")
	public String queryBySE_RiskSNP(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");

		List list = null;

		list = searchService.queryBySE_RiskSNP_G(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-RiskSNP";
	}

	@RequestMapping("/search/LncName/SE/EqtL")
	public String queryBySE_EqtL(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");

		List list = null;

		list = searchService.queryBySE_EqtL_G(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-EQTL";
	}

	@RequestMapping("/search/LncName/SE/SAS")
	public String queryBySE_SAS(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");

		List list = null;

		list = searchService.queryBySE_SAS_G(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-SAS";
	}

	@RequestMapping("/search/LncName/SE/EUR")
	public String queryBySE_EUR(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");
		List list = null;

		list = searchService.queryBySE_EUR_G(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-EUR";
	}

	@RequestMapping("/search/LncName/SE/EAS")
	public String queryBySE_EAS(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");
		List list = null;

		list = searchService.queryBySE_EAS_G(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-EAS";
	}

	@RequestMapping("/search/LncName/SE/AMR")
	public String queryBySE_AMR(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");
		List list = null;

		list = searchService.queryBySE_AMR_G(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-AMR";
	}

	@RequestMapping("/search/LncName/SE/AFR")
	public String queryBySE_AFR(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");
		List list = null;

		list = searchService.queryBySE_AFR_G(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);
		return "SE-AFR";
	}

	@RequestMapping("/search/LncName/SE/450K")
	public String queryBySE_450K(String tablename_450k, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");
		List list = null;
		List list450k = null;

		list450k = searchService.queryBytablename_450k(Biosample_Type, Biosample_Name);
		tablename_450k = list450k.toString().replace("[", "").replace("]", "");
		if (tablename_450k.equals("")) {
			tablename_450k = "450K_null";
		}
		list = searchService.queryBySE_450K_G(chr, start, end, tablename_450k);

		model.addAttribute("tablename_450k", tablename_450k);
		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-450K";
	}

	@RequestMapping("/search/LncName/SE/WGBS")
	public String queryBySE_WGBS(String tablename_WGBS, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");
		List list = null;
		List listWGBS = null;
		listWGBS = searchService.queryBytablename_WGBS(Biosample_Type, Biosample_Name);
		tablename_WGBS = listWGBS.toString().replace("[", "").replace("]", "");
		if (tablename_WGBS.equals("")) {
			tablename_WGBS = "WGBS_null";
		}
		list = searchService.queryBySE_WGBS_G(chr, start, end, tablename_WGBS);

		model.addAttribute("tablename_WGBS", tablename_WGBS);
		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-WGBS";
	}

	@RequestMapping("/search/LncName/SE/Intersection")
	public String queryBySE_Intersection(String inter_tablename, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String tiss = (String) session.getAttribute("tiss");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");
		List list = null;
		List listinter = null;

		listinter = searchService.queryBytablename_inter(tiss);
		inter_tablename = listinter.toString().replace("[", "").replace("]", "");
		if (inter_tablename.equals("")) {
			inter_tablename = "inter_null";
		}

		list = searchService.queryBySE_Intersection_G(chr, start, end, inter_tablename);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-Intersection";
	}

	@RequestMapping("/search/LncName/SE/Histone")
	public String queryBySE_Histone(String histone, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");

		List list = null;
		if (histone == null || histone.equals("")) {
			histone = "H3K4me3";
		}

		list = searchService.queryBySE_Histone_G(chr, start, end, Biosample_Type, Biosample_Name, histone);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-Histonetable";

	}

	@RequestMapping("/search/LncName/SE/motif")
	public String queryBySE_motif(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");
		String se_id = (String) session.getAttribute("se_position_id");

		List list = null;

		list = searchService.queryBySE_motif(chr, start, end);

		model.addAttribute("se_id", se_id);
		model.addAttribute("list", list);

		return "SE-motif";

	}

	// ################################# SE閿熸枻鎷烽敓鏂ゆ嫹閿熺獤顖ゆ嫹閿熸枻鎷�
	// #################################
	// ###### Chromatin 閿熸枻鎷烽敓鏂ゆ嫹 閿熸枻鎷烽敓鏂ゆ嫹杩滈敓鏂ゆ嫹璇㈤敓鏂ゆ嫹涓轰竴閿熺殕璁规嫹閿熺獤锟� ######
	@RequestMapping("/search/ch_position")
	public void querych(String chr, String start, String end, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		session.setAttribute("ch_position_chr", chr);
		session.setAttribute("ch_position_start", start);
		session.setAttribute("ch_position_end", end);

	}

	// ################################# DNaseI閿熸枻鎷稟TAC閿熸枻鎷烽敓鏂ゆ嫹閿熺獤锟�
	// #################################
	@RequestMapping("/search/LncName/Chromatin")
	public String queryByChromatin(String tablename, String tablenameATAC, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");

		List list = null;
		List listD = null;
		List listA = null;
		// DNaseI閿熶茎鎲嬫嫹閿熸枻鎷�
		listD = searchService.queryByChromatin_tablename(Biosample_Type, Biosample_Name);
		tablename = listD.toString().replace("[", "").replace("]", "");
		if (tablename.equals("")) {
			tablename = "table_null";
		}
		// ATAC閿熶茎鎲嬫嫹閿熸枻鎷�
		listA = searchService.queryByChromatin_tablenameATAC(Biosample_Type, Biosample_Name);
		tablenameATAC = listA.toString().replace("[", "").replace("]", "");
		if (tablenameATAC.equals("")) {
			tablenameATAC = "table_null";
		}

		list = searchService.queryByChromatinD_G(source, lncnames, tablename, tablenameATAC);

		model.addAttribute("tablename", tablename);
		model.addAttribute("tablenameATAC", tablenameATAC);
		model.addAttribute("list", list);

		return "Chromatin";
	}

	@RequestMapping("/search/LncName/Chromatin/SNP")
	public String queryByChromatin_SNP(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");

		List list = null;

		list = searchService.queryByChromatinD_SNP_G(chr, start, end);

		model.addAttribute("list", list);

		return "Chromatin-SNP";
	}

	@RequestMapping("/search/LncName/Chromatin/RiskSNP")
	public String queryByChromatin_RiskSNP(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;

		list = searchService.queryByChromatinD_RiskSNP_G(chr, start, end);

		model.addAttribute("list", list);

		return "Chromatin-RiskSNP";
	}

	@RequestMapping("/search/LncName/Chromatin/Eqtl")
	public String queryByChromatin_Eqtl(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;

		list = searchService.queryByChromatinD_Eqtl_G(chr, start, end);

		model.addAttribute("list", list);

		return "Chromatin-Eqtl";
	}

	@RequestMapping("/search/LncName/Chromatin/SAS")
	public String queryByChromatin_SAS(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;

		list = searchService.queryByChromatinD_SAS_G(chr, start, end);

		model.addAttribute("list", list);

		return "Chromatin-SAS";
	}

	@RequestMapping("/search/LncName/Chromatin/EUR")
	public String queryByChromatin_EUR(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;

		list = searchService.queryByChromatinD_EUR_G(chr, start, end);

		model.addAttribute("list", list);

		return "Chromatin-EUR";
	}

	@RequestMapping("/search/LncName/Chromatin/EAS")
	public String queryByChromatin_EAS(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;

		list = searchService.queryByChromatinD_EAS_G(chr, start, end);

		model.addAttribute("list", list);

		return "Chromatin-EAS";
	}

	@RequestMapping("/search/LncName/Chromatin/AMR")
	public String queryByChromatin_AMR(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;
		list = searchService.queryByChromatinD_AMR_G(chr, start, end);

		model.addAttribute("list", list);

		return "Chromatin-AMR";
	}

	@RequestMapping("/search/LncName/Chromatin/AFR")
	public String queryByChromatin_AFR(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;
		list = searchService.queryByChromatinD_AFR_G(chr, start, end);

		model.addAttribute("list", list);

		return "Chromatin-AFR";
	}

	@RequestMapping("/search/LncName/Chromatin/TF")
	public String queryByChromatin_TF(String tablename, String tf_tablename, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;
		List listTF = null;// 閿熸枻鎷稵F閿熸枻鎷烽敓鏂ゆ嫹

		listTF = searchService.queryByChromatin_tf_tablename(Biosample_Type, Biosample_Name);
		tf_tablename = listTF.toString().replace("[", "").replace("]", "");
		if (tf_tablename.equals("")) {
			tf_tablename = "TF_null";
		}

		list = searchService.queryByChromatinD_TF_G(chr, start, end, tf_tablename);

		model.addAttribute("list", list);

		return "Chromatin-TFtable";
	}

	@RequestMapping("/search/LncName/Chromatin/450K")
	public String queryByChromatin_450K(String tablename_450k, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;
		List list450k = null;
		list450k = searchService.queryBytablename_450k(Biosample_Type, Biosample_Name);
		tablename_450k = list450k.toString().replace("[", "").replace("]", "");
		if (tablename_450k.equals("")) {
			tablename_450k = "450K_null";
		}

		list = searchService.queryByChromatinD_450K_G(chr, start, end, tablename_450k);

		model.addAttribute("tablename_450k", tablename_450k);
		model.addAttribute("list", list);

		return "Chromatin-450K";
	}

	@RequestMapping("/search/LncName/Chromatin/WGBS")
	public String queryByChromatin_WGBS(String tablename_WGBS, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;
		List listWGBS = null;
		listWGBS = searchService.queryBytablename_WGBS(Biosample_Type, Biosample_Name);
		tablename_WGBS = listWGBS.toString().replace("[", "").replace("]", "");
		if (tablename_WGBS.equals("")) {
			tablename_WGBS = "WGBS_null";
		}
		list = searchService.queryByChromatinD_WGBS_G(chr, start, end, tablename_WGBS);

		model.addAttribute("tablename_WGBS", tablename_WGBS);
		model.addAttribute("list", list);

		return "Chromatin-WGBS";
	}

	@RequestMapping("/search/LncName/Chromatin/Intersection")
	public String queryByChromatin_Intersection(String inter_tablename, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String tiss = (String) session.getAttribute("tiss");
		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;
		List listinter = null;

		listinter = searchService.queryBytablename_inter(tiss);
		inter_tablename = listinter.toString().replace("[", "").replace("]", "");
		if (inter_tablename.equals("")) {
			inter_tablename = "inter_null";
		}
		list = searchService.queryByChromatinD_Intersection_G(chr, start, end, inter_tablename);

		model.addAttribute("list", list);

		return "Chromatin-Intersection";
	}

	@RequestMapping("/search/LncName/Chromatin/Histone")
	public String queryByChromatin_Histone(String histone, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;
		if (histone == null || histone.equals("")) {
			histone = "H3K4me3";
		}
		list = searchService.queryByChromatinD_Histone_G(chr, start, end, Biosample_Type, Biosample_Name, histone);

		model.addAttribute("list", list);

		return "Chromatin-Histonetable";
	}

	@RequestMapping("/search/LncName/Chromatin/motif")
	public String queryByChromatin_motif(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		String Biosample_Type = (String) session.getAttribute("Biosample_Type");
		String Biosample_Name = (String) session.getAttribute("Biosample_Name");
		String chr = (String) session.getAttribute("ch_position_chr");
		String start = (String) session.getAttribute("ch_position_start");
		String end = (String) session.getAttribute("ch_position_end");
		List list = null;

		list = searchService.queryBySE_motif(chr, start, end); // 閿熸枻鎷烽敓鏂ゆ嫹涓�閿熸枻鎷穝earchservice

		model.addAttribute("list", list);

		return "Chromatin-motif";
	}

	// ################################# DNaseI閿熸枻鎷稟TAC閿熸枻鎷烽敓鏂ゆ嫹閿熺獤顖ゆ嫹閿熸枻鎷�
	// #################################
//################################# Expression 閿熸枻鎷烽敓鏂ゆ嫹閿熺獤锟� #################################		
	@RequestMapping("/search/LncName/Expression")
	public void queryByExpression(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		String lncnames = (String) session.getAttribute("lncnames");
		List list = null;
		list = searchService.queryByExpression_NONCODE_G(lncnames);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		String[] split = json.split(",");
		List<String> roleList = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String str0 = split[i].substring(split[i].indexOf(":") + 1);
			str0 = str0.replaceAll("}]", "");
			roleList.add(str0);
		}

		PrintWriter out = response.getWriter();
		out.print(roleList);
		out.close();
	}

	@RequestMapping("/search/LncName/Expression/ENCODE")
	public void queryByExpression_ENCODE(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		String lncnames = (String) session.getAttribute("lncnames");
		List list = null;
		list = searchService.queryByExpression_N(lncnames);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		String[] split = json.split(",");
		List<String> roleList = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String str0 = split[i].substring(split[i].indexOf(":") + 1);
			str0 = str0.replaceAll("}]", "");
			roleList.add(str0);
		}

		PrintWriter out = response.getWriter();
		out.print(roleList);
		out.close();
	}

	@RequestMapping("/search/LncName/Expression/TCGA")
	public void queryByExpression_TCGA(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		String lncnames = (String) session.getAttribute("lncnames");
		List list = null;
		list = searchService.queryByExpression_TCGA(lncnames);

		Gson gson = new Gson();
		String json = gson.toJson(list);
		String[] split = json.split(",");
		List<String> roleList = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String str0 = split[i].substring(split[i].indexOf(":") + 1);
			str0 = str0.replaceAll("}]", "");
			roleList.add(str0);
		}
		PrintWriter out = response.getWriter();
		out.print(roleList);
		out.close();
	}

	@RequestMapping("/search/LncName/Expression/GTEX")
	public void queryByExpression_GTEX(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		String lncnames = (String) session.getAttribute("lncnames");
		List list = null;
		list = searchService.queryByExpression_GTEX(lncnames);

		Gson gson = new Gson();
		String json = gson.toJson(list);
		String[] split = json.split(",");
		List<String> roleList = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String str0 = split[i].substring(split[i].indexOf(":") + 1);
			str0 = str0.replaceAll("}]", "");
			roleList.add(str0);
		}

		PrintWriter out = response.getWriter();
		out.print(roleList);
		out.close();
	}

	@RequestMapping("/search/LncName/Expression/CCLE")
	public void queryByExpression_CCLE(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) throws IOException {
		String lncnames = (String) session.getAttribute("lncnames");
		List list = null;
		list = searchService.queryByExpression_CCLE(lncnames);

		Gson gson = new Gson();
		String json = gson.toJson(list);
		String[] split = json.split(",");
		List<String> roleList = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String str0 = split[i].substring(split[i].indexOf(":") + 1);
			str0 = str0.replaceAll("}]", "");
			roleList.add(str0);
		}

		PrintWriter out = response.getWriter();
		out.print(roleList);
		out.close();
	}

//################################# Expression 閿熸枻鎷烽敓鏂ゆ嫹閿熺獤顖ゆ嫹閿熸枻鎷� #################################					
//################################# Browse 閿熸枻鎷烽敓鏂ゆ嫹閿熺獤锟� #################################	

	@RequestMapping("/search/browse/list")
	@ResponseBody
	public datatableResult queryByBrowselist(int start, int length, int draw, HttpServletRequest request,
			HttpSession session, Model model) {
		String source = (String) session.getAttribute("source");
		String Gene_type = (String) session.getAttribute("Gene_type");
		String region = (String) session.getAttribute("region");
		String CHR = (String) session.getAttribute("browseCHR");
//			 閿熸彮璇ф嫹閿熺但able閿熸枻鎷穕ist	
		List list = null;

		datatableResult DatatableResult = new datatableResult();
		PageHelper.startPage(start / length + 1, length);
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�1閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
		if (source == "" && Gene_type == "" && region == "" && CHR == "") {

			list = searchService.queryByBrowse(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�2閿熸枻鎷� 閿熸暀鈽呮嫹閿熸枻鎷烽敓鏂ゆ嫹 
		else if (source != "" && Gene_type == "" && region == "" && CHR == "") {

			list = searchService.queryByBrowse_1(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�3閿熸枻鎷� 閿熸暀鈭氣槄鎷烽敓鏂ゆ嫹 
		else if (source != "" && Gene_type != "" && region == "" && CHR == "") {

			list = searchService.queryByBrowse_2(source, Gene_type, region, CHR);

		}
//  		   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�4閿熸枻鎷� 閿熸暀鈭氣垰鈽呮嫹
		else if (source != "" && Gene_type != "" && region != "" && CHR == "") {

			list = searchService.queryByBrowse_3(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�5閿熸枻鎷� 閿熸暀鈭氣垰鈽呮嫹 
		else if (source != "" && Gene_type != "" && region != "" && CHR != "") {

			list = searchService.queryByBrowse_4(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�6閿熸枻鎷� 閿熸枻鎷烽敓鏁欌槄鎷烽敓鏂ゆ嫹
		else if (source == "" && Gene_type != "" && region == "" && CHR == "") {

			list = searchService.queryByBrowse_5(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�7閿熸枻鎷� 閿熸枻鎷烽敓鏁欌垰鈽呮嫹
		else if (source == "" && Gene_type != "" && region != "" && CHR == "") {

			list = searchService.queryByBrowse_6(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�8閿熸枻鎷� 閿熸枻鎷烽敓鏁欌垰鈽呮嫹
		else if (source == "" && Gene_type != "" && region != "" && CHR != "") {

			list = searchService.queryByBrowse_7(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�9閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鈽呮嫹
		else if (source == "" && Gene_type == "" && region != "" && CHR == "") {

			list = searchService.queryByBrowse_8(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�10閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鈽呮嫹
		else if (source == "" && Gene_type == "" && region != "" && CHR != "") {

			list = searchService.queryByBrowse_9(source, Gene_type, region, CHR);

		}
//			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�11閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
		else if (source == "" && Gene_type == "" && region == "" && CHR != "") {

			list = searchService.queryByBrowse_10(source, Gene_type, region, CHR);

		}
// 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�12閿熸枻鎷� 閿熸暀鈽呮嫹閿熸暀鈽呮嫹
		else if (source != "" && Gene_type == "" && region != "" && CHR == "") {

			list = searchService.queryByBrowse_11(source, Gene_type, region, CHR);

		}
// 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�13閿熸枻鎷� 閿熸暀鈽呮嫹閿熸枻鎷烽敓鏂ゆ嫹
		else if (source != "" && Gene_type == "" && region == "" && CHR != "") {

			list = searchService.queryByBrowse_12(source, Gene_type, region, CHR);

		}
// 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�14閿熸枻鎷� 閿熸枻鎷烽敓鏁欌槄鎷烽敓鏂ゆ嫹
		else if (source == "" && Gene_type != "" && region == "" && CHR != "") {

			list = searchService.queryByBrowse_13(source, Gene_type, region, CHR);

		}
// 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�15閿熸枻鎷� 閿熸暀鈽呮嫹閿熸暀鈽呮嫹
		else if (source != "" && Gene_type == "" && region != "" && CHR != "") {

			list = searchService.queryByBrowse_14(source, Gene_type, region, CHR);

		}
// 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�16閿熸枻鎷� 閿熸暀鈭氣槄鎷烽敓鏂ゆ嫹
		else if (source != "" && Gene_type != "" && region == "" && CHR != "") {

			list = searchService.queryByBrowse_15(source, Gene_type, region, CHR);

		}
		PageInfo pageInfo = new PageInfo(list);

		DatatableResult.setDraw(DatatableResult.getDraw());
		DatatableResult.setData(pageInfo.getList());
		DatatableResult.setRecordsTotal((int) pageInfo.getTotal());
		DatatableResult.setRecordsFiltered(DatatableResult.getRecordsTotal());

		return DatatableResult;
	}

	// ################################# Browse 閿熸枻鎷烽敓鏂ゆ嫹閿熺獤顖ゆ嫹閿熸枻鎷�
	// #################################
//################################# Download 閿熸枻鎷烽敓鏂ゆ嫹閿熺獤锟� ####################################	
	@RequestMapping("/search/download")

	public String queryByDownload(String Data_source, String Element_class, String Biosample_type,
			String Biosample_name, HttpSession session, Model model) {

		session.setAttribute("Data_source", Data_source);
		session.setAttribute("Element_class", Element_class);
		session.setAttribute("Biosample_type", Biosample_type);
		session.setAttribute("Biosample_name", Biosample_name);

//              閿熸枻鎷烽敓渚ラ潻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹涓洪敓鍓跨櫢鎷烽敓鏂ゆ嫹url
		String data_source = Data_source;
		String Element = Element_class;
		String type = Biosample_type;
		String name = Biosample_name;
//	                         閿熸枻鎷烽敓渚ラ潻鎷穉ctive閿熸枻鎷蜂负閿熸枻鎷烽�夐敓鏂ゆ嫹鏃堕敓鏂ゆ嫹閿熸枻鎷疯壊閿熸垝鎹�  				
		List<String> ActiveData_source = new ArrayList<>();
		List<String> ActiveElement_class = new ArrayList<>();
		List<String> ActiveBiosample_type = new ArrayList<>();
		List<String> ActiveBiosample_name = new ArrayList<>();

//					List list=null;
//				 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�4閿熸枻鎷穕ist
		List list1 = null;
		List list2 = null;
		List list3 = null;
		List list4 = null;

//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�1閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
		if (Data_source == "" && Element_class == "" && Biosample_type == "" && Biosample_name == "") {

//				    	  list  = searchService.queryByDownload(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�2閿熸枻鎷� 閿熸暀鈽呮嫹閿熸枻鎷烽敓鏂ゆ嫹 
		if (Data_source != "" && Element_class == "" && Biosample_type == "" && Biosample_name == "") {

			ActiveData_source = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_1(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_1(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_1(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_1(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_1(Data_source, Element_class, Biosample_type, Biosample_name);

		}

//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�3閿熸枻鎷� 閿熸暀鈭氣槄鎷烽敓鏂ゆ嫹 
		if (Data_source != "" && Element_class != "" && Biosample_type == "" && Biosample_name == "") {

			ActiveData_source = Arrays.asList("active");
			ActiveElement_class = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_2(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_2(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_2(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_2(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_2(Data_source, Element_class, Biosample_type, Biosample_name);

		}

//	  		           閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�4閿熸枻鎷� 閿熸暀鈭氣垰鈽呮嫹
		if (Data_source != "" && Element_class != "" && Biosample_type != "" && Biosample_name == "") {

			ActiveData_source = Arrays.asList("active");
			ActiveElement_class = Arrays.asList("active");
			ActiveBiosample_type = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_3(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_3(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_3(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_3(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_3(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�5閿熸枻鎷� 閿熸暀鈭氣垰鈽呮嫹 
		if (Data_source != "" && Element_class != "" && Biosample_type != "" && Biosample_name != "") {

			ActiveData_source = Arrays.asList("active");
			ActiveElement_class = Arrays.asList("active");
			ActiveBiosample_type = Arrays.asList("active");
			ActiveBiosample_name = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_4(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_4(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_4(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_4(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_4(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�6閿熸枻鎷� 閿熸枻鎷烽敓鏁欌槄鎷烽敓鏂ゆ嫹
		if (Data_source == "" && Element_class != "" && Biosample_type == "" && Biosample_name == "") {

			ActiveElement_class = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_5(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_5(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_5(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_5(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_5(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�7閿熸枻鎷� 閿熸枻鎷烽敓鏁欌垰鈽呮嫹
		if (Data_source == "" && Element_class != "" && Biosample_type != "" && Biosample_name == "") {

			ActiveElement_class = Arrays.asList("active");
			ActiveBiosample_type = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_6(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_6(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_6(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_6(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_6(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�8閿熸枻鎷� 閿熸枻鎷烽敓鏁欌垰鈽呮嫹
		if (Data_source == "" && Element_class != "" && Biosample_type != "" && Biosample_name != "") {

			ActiveElement_class = Arrays.asList("active");
			ActiveBiosample_type = Arrays.asList("active");
			ActiveBiosample_name = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_7(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_7(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_7(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_7(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_7(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�9閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鈽呮嫹
		if (Data_source == "" && Element_class == "" && Biosample_type != "" && Biosample_name == "") {

			ActiveBiosample_type = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_8(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_8(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_8(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_8(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_8(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�10閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鈽呮嫹
		if (Data_source == "" && Element_class == "" && Biosample_type != "" && Biosample_name != "") {

			ActiveBiosample_type = Arrays.asList("active");
			ActiveBiosample_name = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_9(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_9(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_9(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_9(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_9(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�11閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
		if (Data_source == "" && Element_class == "" && Biosample_type == "" && Biosample_name != "") {

			ActiveBiosample_name = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_10(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_10(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_10(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_10(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_10(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�12閿熸枻鎷� 閿熸暀鈽呮嫹閿熸暀鈽呮嫹
		if (Data_source != "" && Element_class == "" && Biosample_type != "" && Biosample_name == "") {

			ActiveData_source = Arrays.asList("active");
			ActiveBiosample_type = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_11(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_11(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_11(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_11(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_11(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�13閿熸枻鎷� 閿熸暀鈽呮嫹閿熸枻鎷烽敓鏂ゆ嫹
		if (Data_source != "" && Element_class == "" && Biosample_type == "" && Biosample_name != "") {

			ActiveData_source = Arrays.asList("active");
			ActiveBiosample_name = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_12(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_12(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_12(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_12(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_12(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�14閿熸枻鎷� 閿熸枻鎷烽敓鏁欌槄鎷烽敓鏂ゆ嫹
		if (Data_source == "" && Element_class != "" && Biosample_type == "" && Biosample_name != "") {

			ActiveElement_class = Arrays.asList("active");
			ActiveBiosample_name = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_13(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_13(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_13(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_13(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_13(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�15閿熸枻鎷� 閿熸暀鈽呮嫹閿熸暀鈽呮嫹
		if (Data_source != "" && Element_class == "" && Biosample_type != "" && Biosample_name != "") {

			ActiveData_source = Arrays.asList("active");
			ActiveBiosample_type = Arrays.asList("active");
			ActiveBiosample_name = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_14(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_14(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_14(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_14(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_14(Data_source, Element_class, Biosample_type, Biosample_name);

		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�16閿熸枻鎷� 閿熸暀鈭氣槄鎷烽敓鏂ゆ嫹
		if (Data_source != "" && Element_class != "" && Biosample_type == "" && Biosample_name != "") {

			ActiveData_source = Arrays.asList("active");
			ActiveElement_class = Arrays.asList("active");
			ActiveBiosample_name = Arrays.asList("active");

//				    	  list  = searchService.queryByDownload_15(Data_source,Element_class,Biosample_type,Biosample_name);  
			list1 = searchService.queryByData_source_15(Data_source, Element_class, Biosample_type, Biosample_name);
			list2 = searchService.queryByElement_class_15(Data_source, Element_class, Biosample_type, Biosample_name);
			list3 = searchService.queryByBiosample_type_15(Data_source, Element_class, Biosample_type, Biosample_name);
			list4 = searchService.queryByBiosample_name_15(Data_source, Element_class, Biosample_type, Biosample_name);

		}

		if (list1.size() > 4) {
			List list1forward = list1.subList(0, 4);
			List list1later = list1.subList(4, list1.size());
			model.addAttribute("list1forward", list1forward);
			model.addAttribute("list1later", list1later);
		}
		if (list2.size() > 4) {
			List list2forward = list2.subList(0, 4);
			List list2later = list2.subList(4, list2.size());
			model.addAttribute("list2forward", list2forward);
			model.addAttribute("list2later", list2later);
		}
		if (list3.size() > 4) {
			List list3forward = list3.subList(0, 4);
			List list3later = list3.subList(4, list3.size());
			model.addAttribute("list3forward", list3forward);
			model.addAttribute("list3later", list3later);
		}

		if (list4.size() > 4) {
			List list4forward = list4.subList(0, 4);
			List list4later = list4.subList(4, list4.size());
			model.addAttribute("list4forward", list4forward);
			model.addAttribute("list4later", list4later);
		}

//	                  閿熸枻鎷烽敓渚ラ潻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹涓洪敓鍓跨櫢鎷烽敓鏂ゆ嫹url   
		model.addAttribute("data_source", data_source);
		model.addAttribute("Element", Element);
		model.addAttribute("type", type);
		model.addAttribute("name", name);
//	                  閿熸枻鎷烽敓渚ラ潻鎷穉ctive閿熸枻鎷蜂负閿熸枻鎷烽�夐敓鏂ゆ嫹鏃堕敓鏂ゆ嫹閿熸枻鎷疯壊閿熸垝鎹�  
		model.addAttribute("ActiveData_source", ActiveData_source);
		model.addAttribute("ActiveElement_class", ActiveElement_class);
		model.addAttribute("ActiveBiosample_type", ActiveBiosample_type);
		model.addAttribute("ActiveBiosample_name", ActiveBiosample_name);
//	                  閿熸枻鎷峰彥閿熸枻鎷锋瘝閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鍐跺彥璋嬮敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹  
		model.addAttribute("list1_number", list1.size());
		model.addAttribute("list2_number", list2.size());
		model.addAttribute("list3_number", list3.size());
		model.addAttribute("list4_number", list4.size());

//                  model.addAttribute("list", list);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);

		return "download";
	}

	// #################################### download 閿熸彮杈规唻鎷烽敓绉告晠锟�
	// ############################
	@RequestMapping("/search/download/list")
	@ResponseBody
	public datatableResult queryBydownloadList(int start, int length, int draw, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		String Data_source = (String) session.getAttribute("Data_source");
		String Element_class = (String) session.getAttribute("Element_class");
		String Biosample_type = (String) session.getAttribute("Biosample_type");
		String Biosample_name = (String) session.getAttribute("Biosample_name");

		List list = null;

		datatableResult DatatableResult = new datatableResult();

		PageHelper.startPage(start / length + 1, length);

//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�1閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
		if (Data_source == "" && Element_class == "" && Biosample_type == "" && Biosample_name == "") {
			list = searchService.queryByDownload(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�2閿熸枻鎷� 閿熸暀鈽呮嫹閿熸枻鎷烽敓鏂ゆ嫹 
		if (Data_source != "" && Element_class == "" && Biosample_type == "" && Biosample_name == "") {
			list = searchService.queryByDownload_1(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�3閿熸枻鎷� 閿熸暀鈭氣槄鎷烽敓鏂ゆ嫹 
		if (Data_source != "" && Element_class != "" && Biosample_type == "" && Biosample_name == "") {
			list = searchService.queryByDownload_2(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//	  		           閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�4閿熸枻鎷� 閿熸暀鈭氣垰鈽呮嫹
		if (Data_source != "" && Element_class != "" && Biosample_type != "" && Biosample_name == "") {
			list = searchService.queryByDownload_3(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�5閿熸枻鎷� 閿熸暀鈭氣垰鈽呮嫹 
		if (Data_source != "" && Element_class != "" && Biosample_type != "" && Biosample_name != "") {
			list = searchService.queryByDownload_4(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�6閿熸枻鎷� 閿熸枻鎷烽敓鏁欌槄鎷烽敓鏂ゆ嫹
		if (Data_source == "" && Element_class != "" && Biosample_type == "" && Biosample_name == "") {
			list = searchService.queryByDownload_5(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�7閿熸枻鎷� 閿熸枻鎷烽敓鏁欌垰鈽呮嫹
		if (Data_source == "" && Element_class != "" && Biosample_type != "" && Biosample_name == "") {
			list = searchService.queryByDownload_6(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�8閿熸枻鎷� 閿熸枻鎷烽敓鏁欌垰鈽呮嫹
		if (Data_source == "" && Element_class != "" && Biosample_type != "" && Biosample_name != "") {
			list = searchService.queryByDownload_7(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�9閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鈽呮嫹
		if (Data_source == "" && Element_class == "" && Biosample_type != "" && Biosample_name == "") {
			list = searchService.queryByDownload_8(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�10閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸暀鈽呮嫹
		if (Data_source == "" && Element_class == "" && Biosample_type != "" && Biosample_name != "") {
			list = searchService.queryByDownload_9(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//				   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�11閿熸枻鎷� 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
		if (Data_source == "" && Element_class == "" && Biosample_type == "" && Biosample_name != "") {
			list = searchService.queryByDownload_10(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�12閿熸枻鎷� 閿熸暀鈽呮嫹閿熸暀鈽呮嫹
		if (Data_source != "" && Element_class == "" && Biosample_type != "" && Biosample_name == "") {
			list = searchService.queryByDownload_11(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�13閿熸枻鎷� 閿熸暀鈽呮嫹閿熸枻鎷烽敓鏂ゆ嫹
		if (Data_source != "" && Element_class == "" && Biosample_type == "" && Biosample_name != "") {
			list = searchService.queryByDownload_12(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�14閿熸枻鎷� 閿熸枻鎷烽敓鏁欌槄鎷烽敓鏂ゆ嫹
		if (Data_source == "" && Element_class != "" && Biosample_type == "" && Biosample_name != "") {
			list = searchService.queryByDownload_13(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�15閿熸枻鎷� 閿熸暀鈽呮嫹閿熸暀鈽呮嫹
		if (Data_source != "" && Element_class == "" && Biosample_type != "" && Biosample_name != "") {
			list = searchService.queryByDownload_14(Data_source, Element_class, Biosample_type, Biosample_name);
		}
//	 			   閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�16閿熸枻鎷� 閿熸暀鈭氣槄鎷烽敓鏂ゆ嫹
		if (Data_source != "" && Element_class != "" && Biosample_type == "" && Biosample_name != "") {
			list = searchService.queryByDownload_15(Data_source, Element_class, Biosample_type, Biosample_name);
		}

		PageInfo pageInfo = new PageInfo(list);
		DatatableResult.setDraw(DatatableResult.getDraw());
		DatatableResult.setData(pageInfo.getList());
		DatatableResult.setRecordsTotal((int) pageInfo.getTotal());
		DatatableResult.setRecordsFiltered(DatatableResult.getRecordsTotal());

		return DatatableResult;

	}

	// ################################# Download 閿熸枻鎷烽敓鏂ゆ嫹閿熺獤顖ゆ嫹閿熸枻鎷�
	// #################################
//################################# R 閿熸枻鎷烽敓鏂ゆ嫹 閿熸枻鎷烽敓鏂ゆ嫹閿熺獤锟�  #######################################		
	@RequestMapping("/search/Ranalyse")
	public String searchRAnalyze(String pro_tablename, String other_tablename, String chr, String tftype, String lncRNA,
			String source, String promoterRegion, String database, String threshold, String adjust,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {

		session.setAttribute("Rtftype", tftype);
		session.setAttribute("RlncRNA", lncRNA);
		session.setAttribute("Rsource", source);
		session.setAttribute("RpromoterRegion", promoterRegion);

		List list = null;
		List listchr = null;
		List listpro = null;
		List listother = null;
		List listleft = null;
		List listnode = null;
		List listedge = null;
		String lncname = lncRNA;

		listchr = searchService.queryByRAnalyze_chr(lncRNA);
		chr = listchr.toString().replace("[", "").replace("]", "");
		listpro = searchService.queryByTF_name_pro_tablename(source, chr);
		pro_tablename = listpro.toString().replace("[", "").replace("]", "");
		listother = searchService.queryByTF_name_other_tablename(source, chr);
		other_tablename = listother.toString().replace("[", "").replace("]", "");

		list = searchService.queryByRAnalyze(pro_tablename, other_tablename, promoterRegion, lncRNA, tftype);
		listleft = searchService.queryByRresult_left(source, promoterRegion, lncRNA); // 閿熸枻鎷烽敓鏂ゆ嫹lncname閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷稴ervice
		listnode = searchService.queryByRAnalyze_node(pro_tablename, other_tablename, promoterRegion, lncRNA, tftype);
		listedge = searchService.queryByRAnalyze_edge(pro_tablename, other_tablename, promoterRegion, lncRNA, tftype);

		Gson gson = new Gson();
		String json = gson.toJson(list);
		String[] split = json.split(",");
		List<String> roleList = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String str0 = split[i].substring(split[i].indexOf(":") + 1);
			str0 = str0.replaceAll("}]", "");
			str0 = str0.replaceAll("}", "");
			roleList.add(str0);
		}
		String tf = roleList.toString().substring(1, roleList.toString().length() - 1);

		// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鐩撮敓鏂ゆ嫹閿熸枻鎷� R 閿熻剼纰夋嫹
		// 閫夐敓鏂ゆ嫹閿熸枻鎷烽敓鎹峰尅鎷�
		String jion = database.replaceAll(",", ";");
		Gson gsond = new Gson();
		String databases = gsond.toJson(jion);

		// 閿熸枻鎷烽敓鏂ゆ嫹 R
		RConnection conn = new RConnection();
		// 閿熸枻鎷� R 璇嗛敓鏂ゆ嫹 閿熻鍑ゆ嫹閿熸枻鎷�
		conn.assign("databases", databases);
		conn.assign("threshold", threshold);
		conn.assign("adjust", adjust);
		conn.assign("tf", tf);

		// 閿熸枻鎷烽敓鏂ゆ嫹R 閿熸枻鎷烽敓鏂ゆ嫹
//   		    conn.eval("source(\"D:/R/Rtest/q.R\")");
		conn.eval("source(\"/usr/local/tomcat/apache-tomcat-8.5.46/webapps/TRlncRserve/q.R\")");
//			    conn.eval("source(\"/home/tomcat/apache-tomcat-8.5.8/webapps/TRlncRserve/q.R\")");
		// 閿熸枻鎷烽敓鏂ゆ嫹R 閿熸枻鎷烽敓璇強閿熸枻鎷烽敓鏂ゆ嫹鍊�
		REXP eval = conn
				.eval("enrichment(" + "c(" + tf + ")" + "," + databases + ",'" + adjust + "','" + threshold + "')");
		// 閿熸枻鎷烽敓鏂ゆ嫹鍊艰浆閿熸枻鎷蜂负閿熻鍑ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
		String[] asStrings = eval.asStrings();
//				System.out.println(asStrings.length);

		// 閿熸枻鎷烽敓鏂ゆ嫹 閿熸枻鎷峰悓閿熸枻鎷� list
		ArrayList<RAnalyze> listR = new ArrayList<RAnalyze>();
		ArrayList<String> String1 = new ArrayList<String>();
		ArrayList<String> String2 = new ArrayList<String>();
		ArrayList<String> String3 = new ArrayList<String>();
		ArrayList<String> String4 = new ArrayList<String>();
		ArrayList<String> String5 = new ArrayList<String>();
		ArrayList<String> String6 = new ArrayList<String>();
		ArrayList<String> String7 = new ArrayList<String>();
		ArrayList<String> String8 = new ArrayList<String>();
		ArrayList<String> String9 = new ArrayList<String>();

		// 閿熺煫纰夋嫹 閿熸枻鎷烽敓鏂ゆ嫹 閿熸枻鎷峰��
		// 閿熺煫纰夋嫹 pathwayID
		for (int i = 0; i < asStrings.length / 9; i++) {
			String1.add(asStrings[i]);
		}
		// 閿熺煫纰夋嫹 pathwayName
		for (int i = asStrings.length / 9; i < (asStrings.length / 9) * 2; i++) {
			String2.add(asStrings[i]);
		}
		// 閿熺煫纰夋嫹 Source
		for (int i = (asStrings.length / 9) * 2; i < (asStrings.length / 9) * 3; i++) {
			String3.add(asStrings[i]);
		}
		// 閿熺煫纰夋嫹 Species
		for (int i = (asStrings.length / 9) * 3; i < (asStrings.length / 9) * 4; i++) {
			String4.add(asStrings[i]);
		}
		// 閿熺煫纰夋嫹 GeneID_Type
		for (int i = (asStrings.length / 9) * 4; i < (asStrings.length / 9) * 5; i++) {
			String5.add(asStrings[i]);
		}
		// 閿熺煫纰夋嫹 AnnGene
		for (int i = (asStrings.length / 9) * 5; i < (asStrings.length / 9) * 6; i++) {
			String6.add(asStrings[i]);
		}
		// 閿熺煫纰夋嫹 GeneNumber
		for (int i = (asStrings.length / 9) * 6; i < (asStrings.length / 9) * 7; i++) {
			String7.add(asStrings[i]);
		}
		// 閿熺煫纰夋嫹 PValue
		for (int i = (asStrings.length / 9) * 7; i < (asStrings.length / 9) * 8; i++) {
			String8.add(asStrings[i]);
		}
		// 閿熺煫纰夋嫹 FDR
		for (int i = (asStrings.length / 9) * 8; i < asStrings.length; i++) {
			String9.add(asStrings[i]);
		}

		RAnalyze rAnalyze = null;

		// 閿熸枻鎷峰�奸敓鏂ゆ嫹閿熸枻鎷烽敓鍙鎷峰��
		for (int i = 0; i < asStrings.length / 9; i++) {
			rAnalyze = new RAnalyze();
			rAnalyze.setPathwayID(String1.get(i));
			rAnalyze.setPathwayName(String2.get(i));
			rAnalyze.setSource(String3.get(i));
			rAnalyze.setSpecies(String4.get(i));
			rAnalyze.setGeneID_Type(String5.get(i));
			rAnalyze.setAnnGene(String6.get(i));
			rAnalyze.setGeneNumber(String7.get(i));
			rAnalyze.setPValue(String8.get(i));
			rAnalyze.setFDR(String9.get(i));
			// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋嫢閿熺担ist 閿熸枻鎷�
			listR.add(rAnalyze);
//					System.out.println(rAnalyze);
		}

		// 閿熸枻鎷烽敓鏂ゆ嫹椤甸敓鏂ゆ嫹

		model.addAttribute("lncname", lncname);
		model.addAttribute("listnode", listnode);
		model.addAttribute("listedge", listedge);
		model.addAttribute("listleft", listleft);
		model.addAttribute("listR", listR);

		return "RAnalyzeResult";
	}

	@RequestMapping("/search/tftype")
	@ResponseBody
	public List<String> querytftype() {
		List list = null;
		list = searchService.querytftype();
		return list;
	}

	// ################################# R 閿熸枻鎷烽敓鏂ゆ嫹 閿熸枻鎷烽敓鏂ゆ嫹閿熺獤顖ゆ嫹閿熸枻鎷�
	// ####################################
//################################# 閿熸枻鎷峰浣嶉敓鏂ゆ嫹杞敓鏂ゆ嫹  ####################################	
	@RequestMapping("/search/transition")
	public String queryBytransition(String CHR, String Start, String End, Model model) throws Exception {

		String region = CHR + "\t" + Start + "\t" + End;
		// 閿熻娇绛规嫹閿熶茎纭锋嫹閿熸枻鎷峰彇閿熶茎纭锋嫹閿熸枻鎷�
		String filname = File_form.formation("/home/liftover/", UUID.randomUUID().toString(), ".bed", region);
		String filname_result = filname + "_result.bed";
		// 閿熸枻鎷烽敓鏂ゆ嫹linux
		Linux_java Linux = new Linux_java();
		Linux.getExecCommand("/home/liftover/liftOver.txt /home/liftover/" + filname
				+ " /home/liftover/hg19ToHg38.over.chain.gz /home/liftover/" + filname_result
				+ " /home/liftover/out_unlift.bed");
		// 閿熸枻鎷峰彇閿熶茎纭锋嫹
		List<String> region_new = File_read.read("/home/liftover/" + filname_result);

		model.addAttribute("CHR", CHR);
		model.addAttribute("Start", Start);
		model.addAttribute("End", End);

		model.addAttribute("region", CHR + ":" + Start + "-" + End);

		model.addAttribute("region_new", region_new.get(0).split("\t")[0] + ":" + region_new.get(0).split("\t")[1] + "-"
				+ region_new.get(0).split("\t")[2]);

		return "Transition";
	}

	@RequestMapping("/search/remap")
	@ResponseBody
	public List<String> queryByremap(String CHR, String Start, String End, Model model) throws Exception {

		List<String> list = new ArrayList<String>();
		String region = CHR + "\t" + Start + "\t" + End;

		// 閿熸枻鎷烽敓鏂ゆ嫹linux
		Linux_java Linux = new Linux_java();
		// 閿熻娇绛规嫹閿熶茎纭锋嫹閿熸枻鎷峰彇閿熶茎纭锋嫹閿熸枻鎷�
		String filname = UUID.randomUUID().toString();
		String filname_result = filname + "_result.bed";

		Linux.getExecCommand("echo -e \"" + region + "\" >/home/remap/" + filname + ".bed");
//				Linux.getExecCommand("perl /home/remap/remap_api.pl --mode asm-asm --from GCF_000001405.13 --dest GCF_000001405.39 --annotation /home/remap/" + filname + ".bed  --annot_out /home/remap/" + filname_result);

//				//閿熸枻鎷峰彇閿熶茎纭锋嫹
		List<String> region_new = Linux.getExecCommand("less /home/remap/" + filname_result);
//				List<String> region_new = File_read.read("/home/remap/" + filname_result);

		list.add(CHR + ":" + Start + "-" + End);
		list.add(region_new.get(0).split("\t")[0] + ":" + region_new.get(0).split("\t")[1] + "-"
				+ region_new.get(0).split("\t")[2]);

		return list;
	}

	@RequestMapping("/search/LncName/Intersection/chart")
	public String queryByLncName_Intersectionchart(String inter_tablename, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {

		String tiss = (String) session.getAttribute("tiss");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");

		List chr = null;
		List start = null;
		List end = null;

		chr = searchService.queryByLncName_chr(source, regulation, lncnames);
		start = searchService.queryByLncName_start(source, regulation, lncnames);
		end = searchService.queryByLncName_end(source, regulation, lncnames);

		String CHR = chr.toString().replace("[", "").replace("]", "");
		// 閿熸枻鎷穝tring杞敓鏂ゆ嫹涓篿nt閿熸枻鎷烽敓鏂ゆ嫹
		int Start = Integer.valueOf(start.toString().replace("[", "").replace("]", "")).intValue();
		int End = Integer.valueOf(end.toString().replace("[", "").replace("]", "")).intValue();

		int position = (End - Start);

		model.addAttribute("position", position);
		model.addAttribute("Start", Start);
		model.addAttribute("CHR", CHR + ":" + Start + "-" + End);

		List list = null;
		List listinter = null;

		listinter = searchService.queryBytablename_inter(tiss);
		inter_tablename = listinter.toString().replace("[", "").replace("]", "");
		if (inter_tablename.equals("")) {
			inter_tablename = "inter_null";
		}

		List regionA = null;
		List regionB = null;

		if (source.equals("GENCODE")) {
			list = searchService.queryByEpiTensor_G(regulation, lncnames, inter_tablename);
			regionA = searchService.queryByregionA_G(regulation, lncnames, inter_tablename);
			regionB = searchService.queryByregionB_G(regulation, lncnames, inter_tablename);
		} else {
			list = searchService.queryByEpiTensor_N(regulation, lncnames, inter_tablename);
			regionA = searchService.queryByregionA_N(regulation, lncnames, inter_tablename);
			regionB = searchService.queryByregionB_N(regulation, lncnames, inter_tablename);
		}

		String promoter = CHR + ":" + Start + "-" + End;
		String Aregion = regionA.toString().replace("[", "").replace("]", "");
		String Bregion = regionB.toString().replace("[", "").replace("]", "");

		// 閿熸枻鎷烽敓鏂ゆ嫹 R
		RConnection conn = new RConnection();
		// 閿熸枻鎷� R 璇嗛敓鏂ゆ嫹 閿熻鍑ゆ嫹閿熸枻鎷�
		conn.assign("promoter", promoter);
		conn.assign("Aregion", Aregion);
		conn.assign("Bregion", Bregion);

		// 閿熸枻鎷烽敓鏂ゆ嫹R 閿熸枻鎷烽敓鏂ゆ嫹
//				conn.eval("source(\"D:/R/Rtest/scale.R\")");
		conn.eval("source(\"/usr/local/tomcat/apache-tomcat-8.5.46/webapps/TRlncRserve/scale.R\")");
//			    conn.eval("source(\"/home/tomcat/apache-tomcat-8.5.8/webapps/TRlncRserve/scale.R\")");
		// 閿熸枻鎷烽敓鏂ゆ嫹R 閿熸枻鎷烽敓璇強閿熸枻鎷烽敓鏂ゆ嫹鍊�
		REXP eval = conn.eval(
				"Promoter_interaction(" + "c(\"" + promoter + "\"),c(\"" + Aregion + "\"),c(\"" + Bregion + "\"))");

		// 閿熸枻鎷烽敓鏂ゆ嫹鍊艰浆閿熸枻鎷蜂负int閿熸枻鎷烽敓鏂ゆ嫹
		int[] asStrings = eval.asIntegers();

		model.addAttribute("list", list);

		model.addAttribute("scle", asStrings[0]);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
		model.addAttribute("minstart", asStrings[1]);
		model.addAttribute("maxend", asStrings[2]);

		return "promoter-Intersectionchart";
	}

	@RequestMapping("/search/SE/Intersection/chart")
	public String queryBySE_Intersectionchart(String inter_tablename, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {

		String tiss = (String) session.getAttribute("tiss");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");

		List listchr = null;
		List liststart = null;
		List listend = null;

		listchr = searchService.queryByLncName_chr(source, regulation, lncnames);
		liststart = searchService.queryByLncName_start(source, regulation, lncnames);
		listend = searchService.queryByLncName_end(source, regulation, lncnames);

		String CHR = listchr.toString().replace("[", "").replace("]", "");
		// 閿熸枻鎷穝tring杞敓鏂ゆ嫹涓篿nt閿熸枻鎷烽敓鏂ゆ嫹
		int Start = Integer.valueOf(liststart.toString().replace("[", "").replace("]", "")).intValue();
		int End = Integer.valueOf(listend.toString().replace("[", "").replace("]", "")).intValue();

		int position = (End - Start);

		model.addAttribute("position", position);
		model.addAttribute("Start", Start);
		model.addAttribute("CHR", CHR + ":" + Start + "-" + End);

		List list = null;
		List listinter = null;

		listinter = searchService.queryBytablename_inter(tiss);
		inter_tablename = listinter.toString().replace("[", "").replace("]", "");
		if (inter_tablename.equals("")) {
			inter_tablename = "inter_null";
		}

		List regionA = null;
		List regionB = null;

		list = searchService.queryBySE_Intersection_G(chr, start, end, inter_tablename);

		regionA = searchService.queryByregionA(chr, start, end, inter_tablename);
		regionB = searchService.queryByregionB(chr, start, end, inter_tablename);

		String promoter = CHR + ":" + Start + "-" + End;
		String SE = chr + ":" + start + "-" + end;
		String Aregion = regionA.toString().replace("[", "").replace("]", "");
		String Bregion = regionB.toString().replace("[", "").replace("]", "");

		// 閿熸枻鎷烽敓鏂ゆ嫹 R
		RConnection conn = new RConnection();
		// 閿熸枻鎷� R 璇嗛敓鏂ゆ嫹 閿熻鍑ゆ嫹閿熸枻鎷�
		conn.assign("promoter", promoter);
		conn.assign("Aregion", Aregion);
		conn.assign("Bregion", Bregion);

		// 閿熸枻鎷烽敓鏂ゆ嫹R 閿熸枻鎷烽敓鏂ゆ嫹
//				conn.eval("source(\"D:/R/Rtest/scale_SE.R\")");
		conn.eval("source(\"/usr/local/tomcat/apache-tomcat-8.5.46/webapps/TRlncRserve/scale_SE.R\")");
//			    conn.eval("source(\"/home/tomcat/apache-tomcat-8.5.8/webapps/TRlncRserve/scale_SE.R\")");
		// 閿熸枻鎷烽敓鏂ゆ嫹R 閿熸枻鎷烽敓璇強閿熸枻鎷烽敓鏂ゆ嫹鍊�
		REXP eval = conn.eval("SE_interaction(" + "c(\"" + promoter + "\"),c(\"" + SE + "\"),c(\"" + Aregion
				+ "\"),c(\"" + Bregion + "\"))");
		// 閿熸枻鎷烽敓鏂ゆ嫹鍊艰浆閿熸枻鎷蜂负int閿熸枻鎷烽敓鏂ゆ嫹
		int[] asStrings = eval.asIntegers();

		model.addAttribute("list", list);

		model.addAttribute("scle", asStrings[0]);
		model.addAttribute("minstart", asStrings[1]);
		model.addAttribute("maxend", asStrings[2]);

		model.addAttribute("SE_CHR", chr);
		model.addAttribute("SE_Start", Integer.valueOf(start).intValue());
		model.addAttribute("SE_End", Integer.valueOf(end).intValue());

		return "SE-Intersectionchart";
	}

	@RequestMapping("/search/Ch/Intersection/chart")
	public String queryByCh_Intersectionchart(String inter_tablename, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {

		String tiss = (String) session.getAttribute("tiss");
		String regulation = (String) session.getAttribute("regulation");
		String lncnames = (String) session.getAttribute("lncnames");
		String source = (String) session.getAttribute("source");

		String chr = (String) session.getAttribute("se_position_chr");
		String start = (String) session.getAttribute("se_position_start");
		String end = (String) session.getAttribute("se_position_end");

		List listchr = null;
		List liststart = null;
		List listend = null;

		listchr = searchService.queryByLncName_chr(source, regulation, lncnames);
		liststart = searchService.queryByLncName_start(source, regulation, lncnames);
		listend = searchService.queryByLncName_end(source, regulation, lncnames);

		String CHR = listchr.toString().replace("[", "").replace("]", "");
		// 閿熸枻鎷穝tring杞敓鏂ゆ嫹涓篿nt閿熸枻鎷烽敓鏂ゆ嫹
		int Start = Integer.valueOf(liststart.toString().replace("[", "").replace("]", "")).intValue();
		int End = Integer.valueOf(listend.toString().replace("[", "").replace("]", "")).intValue();

		int position = (End - Start);

		model.addAttribute("position", position);
		model.addAttribute("Start", Start);
		model.addAttribute("CHR", CHR + ":" + Start + "-" + End);

		List list = null;
		List listinter = null;

		listinter = searchService.queryBytablename_inter(tiss);
		inter_tablename = listinter.toString().replace("[", "").replace("]", "");
		if (inter_tablename.equals("")) {
			inter_tablename = "inter_null";
		}

		List regionA = null;
		List regionB = null;

		list = searchService.queryByChromatinD_Intersection_G(chr, start, end, inter_tablename);

		regionA = searchService.queryBych_regionA(chr, start, end, inter_tablename);
		regionB = searchService.queryBych_regionB(chr, start, end, inter_tablename);

		String promoter = CHR + ":" + Start + "-" + End;
		String Ch = chr + ":" + start + "-" + end;
		String Aregion = regionA.toString().replace("[", "").replace("]", "");
		String Bregion = regionB.toString().replace("[", "").replace("]", "");

		// 閿熸枻鎷烽敓鏂ゆ嫹 R
		RConnection conn = new RConnection();
		// 閿熸枻鎷� R 璇嗛敓鏂ゆ嫹 閿熻鍑ゆ嫹閿熸枻鎷�
		conn.assign("promoter", promoter);
		conn.assign("Aregion", Aregion);
		conn.assign("Bregion", Bregion);

		// 閿熸枻鎷烽敓鏂ゆ嫹R 閿熸枻鎷烽敓鏂ゆ嫹
//				conn.eval("source(\"D:/R/Rtest/scale_SE.R\")");
		conn.eval("source(\"/usr/local/tomcat/apache-tomcat-8.5.46/webapps/TRlncRserve/scale_SE.R\")");
//			    conn.eval("source(\"/home/tomcat/apache-tomcat-8.5.8/webapps/TRlncRserve/scale_SE.R\")");
		// 閿熸枻鎷烽敓鏂ゆ嫹R 閿熸枻鎷烽敓璇強閿熸枻鎷烽敓鏂ゆ嫹鍊�
		REXP eval = conn.eval("SE_interaction(" + "c(\"" + promoter + "\"),c(\"" + Ch + "\"),c(\"" + Aregion
				+ "\"),c(\"" + Bregion + "\"))");
		// 閿熸枻鎷烽敓鏂ゆ嫹鍊艰浆閿熸枻鎷蜂负int閿熸枻鎷烽敓鏂ゆ嫹
		int[] asStrings = eval.asIntegers();

		model.addAttribute("list", list);

		model.addAttribute("scle", asStrings[0]);
		model.addAttribute("minstart", asStrings[1]);
		model.addAttribute("maxend", asStrings[2]);

		model.addAttribute("Ch_CHR", chr);
		model.addAttribute("Ch_Start", Integer.valueOf(start).intValue());
		model.addAttribute("Ch_End", Integer.valueOf(end).intValue());

		return "Ch-Intersectionchart";
	}

}
