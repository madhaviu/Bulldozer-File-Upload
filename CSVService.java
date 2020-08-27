package com.fup.services;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fup.base.CSVHelper;
import com.fup.base.EmployeeRepository;
import com.fup.base.ResponseMessage;
import com.fup.entities.Employee;


@Service
@Transactional
public class CSVService {

	@Autowired
	  EmployeeRepository repository;

	  public void save(MultipartFile file) {
	    try {
	      List<Employee> emps = CSVHelper.csvToEmployees(file.getInputStream());
	      repository.saveAll(emps);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }

	  public ResponseMessage getAllEmployees(Integer pageNo, Integer pageSize)
		 {
		        Pageable paging = PageRequest.of(pageNo, pageSize);
		 
		        Slice<Employee> slicedResult = repository.findAll(paging); 
		         
		        
		        ResponseMessage response = new ResponseMessage();
				response.setData(slicedResult.getContent());
				
				return response;
		  }	  
}
