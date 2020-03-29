package com.jxk.sqmy.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static String transFile(MultipartFile file) throws Exception {
		String fileName=file.getOriginalFilename();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss"); 
		String data=formatter.format(new Date(System.currentTimeMillis()));
		String  prefix=UUID.randomUUID().toString();
		String substring = fileName.substring(fileName.lastIndexOf("."));
		String dFileName = data +prefix+ substring;

		String path = "E:/beji/file/";
		File bendifile = new File(path + dFileName);
		if (!bendifile.exists()) {
			bendifile.mkdirs();
		}
		file.transferTo(bendifile);
		return dFileName;
		
	}
}
