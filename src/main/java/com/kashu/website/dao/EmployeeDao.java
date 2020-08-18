package com.kashu.website.dao;

import java.util.List;
import com.kashu.website.model.Employee;

//此處先用介面定義function的名字、參數、回傳值等
//強制規定EmployeeDaoImpl要實作以下的function
public interface EmployeeDao {

	public List<Employee> getAll();
	
	public List<Employee> searchByName(String name);
	
	public Employee getOneById(Long id);
	
	public int saveEmployee(Employee e);
	
	public int updateEmployee(Employee e);
	
	public int deleteEmployee(Long id);
}