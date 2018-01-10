package kr.oraclejava.spring.sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class UploadController {
	
	// Single file upload
	@RequestMapping(value="/upload/single", method=RequestMethod.GET)
	public String singleUpload() {
		return "upload/single";
	}
	
	@RequestMapping(value="/upload/single", method=RequestMethod.POST)
	public String singleUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		if(file.isEmpty() == false &&
			file.getOriginalFilename().isEmpty() == false) { 
			
			File uploadFile = new File("c:/upload/", file.getOriginalFilename());
			file.transferTo(uploadFile);
			return "upload/singleSuccess";
		} else {
			return "redirect:/upload/single";
		}
	}
	
	// Multi file upload
	@RequestMapping(value="/upload/multi", method=RequestMethod.GET)
	public String multiUpload(Map<String, Object> model) {
		model.put("formUpload", new FileUpload());
		return "upload/multi";
	}
	
	@RequestMapping(value="/upload/multi", method=RequestMethod.POST)
	public String multiUpload(FileUpload fileUpload,
			Map<String, Object> model) throws IllegalStateException, IOException {
		CommonsMultipartFile[] files = fileUpload.getFiles();
		
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		for(CommonsMultipartFile file : files) {
			
			File uploadFile = new File("c:/upload/" + file.getOriginalFilename());
			file.transferTo(uploadFile);
			
			if(file.isEmpty() || file.getOriginalFilename().isEmpty()) 
				return "redirect:/upload/multi";
		
			FileInfo fi = new FileInfo();
			fi.setFileName(file.getOriginalFilename());
			fi.setFileType(file.getContentType());
			fi.setSize(file.getSize());
			
			fileInfos.add(fi);
		} 
		
		for(FileInfo fi : fileInfos) {
			System.out.println(fi);
		}
		
		model.put("fileInfos", fileInfos);
		
		return "upload/multiSuccess";
	}
}
