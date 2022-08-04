package com.lyy.util;


import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class File_form {
	public static String formation(String path, String name, String suffix, String textarea) {
		Date date = new Date();
//		SimpleDateFormat ft = new SimpleDateFormat("yyyy=MM=dd");
//		String fileName = ft.format(date) + "-" + name + suffix;
		String fileName =  name + suffix;
		try {
			File input_file = new File(path + fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(input_file);
			if (!input_file.exists()) {
				input_file.createNewFile();
			}
			byte[] contentInBytes = textarea.getBytes();
			fileOutputStream.write(contentInBytes);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
