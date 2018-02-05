package com.kosmo.springEx;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileuploadController {
	
	//파일 업로드 폼
	@RequestMapping("/fileUpload/uploadForm.do")
	public String uploadForm() {
		
		return "11FileUpload/uploadForm";
	}
	
	//파일의 물리적 경로 가져오기
	@RequestMapping("/fileUpload/uploadPath.do")
	public void uploadPath(HttpServletResponse resp, HttpServletRequest req) throws IOException{
		
		//컨트롤러에서 폴더의 물리적 경로 가져오기
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		//컨트롤러에서 직접 출력하는 경우
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter print = resp.getWriter();
		print.println("/upload 폴더의 물리적 경로");
		print.print(path);
	}

	//파일 업로드 처리
	@RequestMapping("/fileUpload/uploadAction.do")
	public String uploadAction(HttpServletRequest req, Model model) {
		
		System.out.println("파일업로드 진행중");
		
		//서버에 물리적 경로 가져오기
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		Map returnObj = new HashMap();
		
		try {
			//파일업로드를 위한 MultipartHttpServletRequest객체 생성
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
			
			Iterator itr = mhsr.getFileNames();
			MultipartFile mfile = null;
			String fileName = "";
			List resultList = new ArrayList();
			
			//제목
			String title = mhsr.getParameter("title");
			
			//디렉토리가 없다면 생성
			File directory = new File(path);
			if(!directory.isDirectory()) {
				//디렉토리가 없다면 생성함. mk = make / dirs = 디렉토리스
				directory.mkdirs();
			}
			
			
			while(itr.hasNext()) {
				fileName = (String)itr.next();
				mfile = mhsr.getFile(fileName);
				//한글깨짐방지
				String originalName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8");
				if("".equals(originalName)) {
					continue;
				}
				
				//파일명에서 확장자 가져오기
				String ext = originalName.substring(originalName.lastIndexOf('.'));
				
				//파일명을 UUID로 생성된 값으로 변경함
				String saveFileName = getUuid() + ext;
				
				//설정한 경로에 파일저장
				File serverFileName  = new File(path + File.separator + saveFileName);
				mfile.transferTo(serverFileName);
				Map file = new HashMap();
				file.put("originalName", originalName); //원본파일명
				file.put("serverFileName", serverFileName); //서버에 저장될 파일명
				file.put("title", title); // 타이틀
				resultList.add(file);
			}
			returnObj.put("files", resultList);
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch(IllegalStateException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("returnObj", returnObj);
		
		return "11FileUpload/uploadAction";
	}
	
	//Uuid생성할 메소드 선언
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println("생성된UUID:"+uuid);
		return uuid;
	}
}
