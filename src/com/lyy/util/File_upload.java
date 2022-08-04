package com.lyy.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class File_upload {
	public static String upload(String path, String name, String suffix, MultipartFile file) {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy=MM=dd");
		String filePath = path;
//		String newFileName = ft.format(date) + "-" + name + suffix;
		String newFileName = name + suffix;
		try {
			File uploadFile = new File(filePath + newFileName);
			file.transferTo(uploadFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFileName;
	}
}
