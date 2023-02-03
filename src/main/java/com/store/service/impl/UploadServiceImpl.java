package com.store.service.impl;

import java.io.File;



import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.store.service.UploadService;



@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	ServletContext app;
	
	@Override
	public File save(MultipartFile file, String folder) {
		try {
			File dir = new ClassPathResource("static/utilities/img/" + folder).getFile();
			//File dir = Paths.get(app.getRealPath("/assets/"), folder).toFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// tạo ra folder nếu chưa tồn tại
			String s = System.currentTimeMillis() + file.getOriginalFilename();
			String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
			// tiến hành luu file vào trong folder
			File saveFile = new File(dir,name);
			file.transferTo(saveFile);
			System.out.println(saveFile.getAbsolutePath());
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
