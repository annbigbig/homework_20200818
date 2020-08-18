package com.kashu.website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kashu.website.dao.EmployeeDao;
import com.kashu.website.exception.EmployeeExceptionEnum;
import com.kashu.website.model.Employee;

@Service
@Transactional
public class EmployeeServiceImpl extends BaseService implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	public String getOne(long id) {
		Employee employee = employeeDaoImpl.getOneById(id);
		if(employee != null){
			return reponseBuilder(true, 200, employee).toString();
		}else{
			int status = EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getCode();
			String msg = EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getErrorMsg();
			return reponseBuilder(false, status, msg).toString();
		}
	}

	public String getAll() {
		List<Employee> employees = employeeDaoImpl.getAll();
		if(employees != null && employees.size()>0){
			return reponseBuilder(true, 200, employees).toString();
		}else{
			int status = EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getCode();
			String msg = EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getErrorMsg();
			return reponseBuilder(false, status, msg).toString();
		}
	}

	public String search(String name) {
		List<Employee> employees = employeeDaoImpl.searchByName(name);
		if(employees != null && employees.size()>0 ){
			return reponseBuilder(true, 200, employees).toString();
		}else{
			int status = EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getCode();
			String msg = EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getErrorMsg();
			return reponseBuilder(false, status, msg).toString();
		}
	}

	public String delete(long id) {
		try {
			employeeDaoImpl.deleteEmployee(id);
			return reponseBuilder(true, 200, "刪除成功").toString();
		} catch (Exception e) {
			ERROR.error(e.getMessage(),e);
			ERROR.error(" ===== debug message from 'EmployeeServiceImpl' delete() =====");
			e.printStackTrace();
			int status = EmployeeExceptionEnum.DELETE_ERROR.getCode();
			String msg = EmployeeExceptionEnum.DELETE_ERROR.getErrorMsg();
			return reponseBuilder(false, status, msg).toString();
		}
	}

	public String update(Employee emp) {
		try {
			employeeDaoImpl.updateEmployee(emp);
			return reponseBuilder(true, 200, "儲存成功").toString();
		} catch (Exception e) {
			ERROR.error(e.getMessage(),e);
			e.printStackTrace();
			int status = EmployeeExceptionEnum.UPDATE_FAILED.getCode();
			String msg = EmployeeExceptionEnum.UPDATE_FAILED.getErrorMsg();
			return reponseBuilder(false, status, msg).toString();
		}
	}
	
	public String save(Employee emp) {
		try {
			employeeDaoImpl.saveEmployee(emp);
			return reponseBuilder(true, 200, "儲存成功").toString();
		} catch (Exception e) {
			ERROR.error(e.getMessage(),e);
			ERROR.error(EmployeeExceptionEnum.SAVE_FAILED);
			e.printStackTrace();
			int status = EmployeeExceptionEnum.SAVE_FAILED.getCode();
			String msg = EmployeeExceptionEnum.SAVE_FAILED.getErrorMsg();
			return reponseBuilder(false, status, msg).toString();
		}
	}
	
}
