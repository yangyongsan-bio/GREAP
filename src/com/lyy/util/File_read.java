package com.lyy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class File_read {
	public static List<String> read(String filePath) {
		List<String> result = new ArrayList<String>();
		try {
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			while ((s = br.readLine()) != null) {
				result.add(s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
