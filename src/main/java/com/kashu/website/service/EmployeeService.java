package com.kashu.website.service;

import com.kashu.website.model.Employee;

public interface EmployeeService {

	public String save(Employee e);

	public String getOne(long id);
	
	public String getAll();

	public String search(String name);

	public String delete(long id);
	
	public String update(Employee e);
}
