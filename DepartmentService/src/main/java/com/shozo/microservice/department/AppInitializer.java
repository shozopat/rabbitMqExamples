package com.shozo.microservice.department;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shozo.microservice.department.dao.DepartmentDao;
import com.shozo.microservice.department.entity.Department;

@Component
public class AppInitializer implements CommandLineRunner {

	@Autowired
	DepartmentDao dao;
	
	@Override
	public void run(String... args) throws Exception {
		
	List<Department> list = Arrays.asList(new Department(1, "Java", "Steve Rogers", 10),
											  new Department(2, "Big Data", "Tony Stark", 20),
											  new Department(3, "HR", "Natasha Romanoff", 30));
	dao.saveAll(list);	
		
	}

}
