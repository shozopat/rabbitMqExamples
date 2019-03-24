package com.shozo.microservice.department.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shozo.microservice.department.dao.DepartmentDao;
import com.shozo.microservice.department.entity.Department;
import com.shozo.microservice.department.entity.Employee;

@Service
public class DepartmentService {

	@Autowired
	DepartmentDao dao;
	
	
	public void createDepartment(Department d) {
		dao.save(d);
	}


	public List<Department> getDepartment() {
		return dao.findAll();
	}


	public void incrementEmployeeCount(Employee employee) {
		Integer did = employee.getdId();
		Department department = dao.findById(did).get();
		Integer employeeCount = department.getEmployeeCount()+1;
		department.setEmployeeCount(employeeCount);
		dao.save(department);
	}
	
	public void decrementEmployeeCount(Employee employee) {
		Integer did = employee.getdId();
		Department department = dao.findById(did).get();
		Integer employeeCount = department.getEmployeeCount()-1;
		department.setEmployeeCount(employeeCount);
		dao.save(department);
	}

}
