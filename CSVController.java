package com.fup.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fup.services.CSVService;

//@CrossOrigin("http://localhost:8081")
@Controller
//@RequestMapping("/api/csv")
public class CSVController 
{

	  @Autowired
	  CSVService fileService;
	  @GetMapping("/")
	    public String index() {
	        return "upload";
	    }
	  

	  @PostMapping("/upload")
	  public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	   // if (CSVHelper.hasCSVFormat(file)) {
	      try {
	        fileService.save(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() +e.getMessage();
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    //}

	    //message = "Please upload a csv file!";
	    //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }

	  @GetMapping("/api")
	    public ResponseMessage getAllProducts(
	                        @RequestParam(defaultValue = "0") Integer pageNo, 
	                        @RequestParam(defaultValue = "10") Integer pageSize,
	                        @RequestParam(defaultValue = "id") String sortBy) 
	    {
	        return fileService.getAllEmployees(pageNo, pageSize);
	 
	    }
	}
