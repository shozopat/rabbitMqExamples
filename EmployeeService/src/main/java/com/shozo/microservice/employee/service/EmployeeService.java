package com.shozo.microservice.employee.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shozo.microservice.employee.EmployeeServiceApplication;
import com.shozo.microservice.employee.dao.EmployeeDao;
import com.shozo.microservice.employee.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao dao;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	ObjectMapper mapper; 
	
	public void createEmployee(Employee e) {
		dao.save(e);
		rabbitTemplate.convertAndSend(EmployeeServiceApplication.topicExchangeName, "add.employee", e);
		System.out.println("send firstQueue successfull");
	}

	public List<Employee> getEmployees() {
		
		return dao.findAll();
	}
	
	public Employee deleteEmployee(Integer eid) {
		System.out.println("send SecondQueue successfull");
		Employee e = dao.findById(eid).get();
		dao.deleteById(eid);
		rabbitTemplate.convertAndSend(EmployeeServiceApplication.topicExchangeName, "delete.employee", e);
		
		return e;
	}

}
