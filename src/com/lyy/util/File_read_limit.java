package com.lyy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class File_read_limit {
	public static List<String> read(String datatype,String set,String filePath) {
		List<String> result = new ArrayList<String>();
		try {
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			while ((s = br.readLine()) != null) {
				String[] arr = s.split("\t");
				
				if(datatype.equals("ChromHMM")){
					if(arr[5].equals(set)){
						result.add(s);
					}
				}
				if(datatype.equals("TF") || datatype.equals("TcoF") || datatype.equals("Histone") || datatype.equals("ATAC")){
					if(arr[3].equals(set)){
						result.add(s);
					}
				}
				if(datatype.equals("Enhancer") || datatype.equals("Super_Enhancer") || datatype.equals("SNP") || datatype.equals("Methylation")){
					if(arr[4].equals(set)){
						result.add(s);
					}
				}
				if(datatype.equals("LncRNA") || datatype.equals("mRNA")){
					if(arr[6].equals(set)){
						result.add(s);
					}
				}
				
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
