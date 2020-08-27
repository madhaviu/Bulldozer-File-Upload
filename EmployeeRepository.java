package com.fup.base;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fup.entities.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

}
 