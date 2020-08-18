package com.kashu.website.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kashu.website.dto.Bar;
import com.kashu.website.dto.QueryParams;
import com.kashu.website.exception.DummyException;
import com.kashu.website.model.Employee;
import com.kashu.website.model.Foo;
import com.kashu.website.service.EmployeeService;

@RestController
public class EmployeeRestController extends BaseController {

	@Autowired
	private EmployeeService employeeService;
	
	//用id取一個員工
	@GetMapping(path = "/employee/{id}", produces = "application/json;charset=utf-8")
	public String getEmployeeByID(@PathVariable("id") long id) {
		INFO.info("GET  /employee/" + id);
		return employeeService.getOne(id);
	}
	
	//取出所有員工
	@GetMapping(path = "/employees", produces = "application/json;charset=utf-8")
	public String getAllEmployees() {
		INFO.info("GET  /employees");
		return employeeService.getAll();
	}
	
	/* 以用戶名稱模糊搜尋多位員工
	 * 傳入以下格式的JSON
	   {
           "filters" : [
              { "column" : "name" , "operator" : "like" , "value" : "日向"}
           ],
           "sorts" : [
              { "column" : "name" , "type" : "desc" }
           ],
           "pageNumber" : 1,
           "pageSize" : 10
       }
       
	 * 注意目前只有姓名欄位，也就是「日向」那個地方有作用，其他參數可以預留作日後分頁功能擴充
	 */
	@PostMapping(path = "/employees/search" , produces = "application/json;charset=utf-8")
	public String getEmployeeByName(@RequestBody QueryParams params) {
		String keyword = params.getFilterByColumn("name").getValue();
		INFO.info("POST  /employees/search    |   keyword : = [ " + keyword + " ]");
		//return keyword;
		return employeeService.search(keyword);
	}
	
	/* 新增員工
	 * 傳入以下格式的JSON
	{
        "address": "木葉忍者村德行西路666號6F",
        "birthday": "1956-06-06",
        "luckyNumber": 6,
        "name": "旗木卡卡西",
        "online": true
    }
	*/
	@PostMapping(path = "/employee", produces = "application/json;charset=utf-8")
	public String createEmployee(@RequestBody Employee e) {
		INFO.info("POST  /employee  > " + e.toString());
		String resultStr = employeeService.save(e);
		INFO.info("(這一行根本不會執行到)新增員工 from EmployeeRestController : createEmployee() " + resultStr);
		return resultStr;
	}
	
	//刪除員工
	@DeleteMapping(path = "/employee/{id}", produces = "application/json;charset=utf-8")
	public String deleteEmployeeByID(@PathVariable("id") int id) {
		return employeeService.delete(id);
	}
	
	/* 更新員工
	 * 傳入以下格式的JSON
	   {
        "id": 6,
        "address": "木葉忍者村德行東路555號5F",
        "birthday": "1955-05-15",
        "luckyNumber": 5,
        "name": "旗木卡卡東",
        "online": false
       }
	 */
	@PutMapping(path = "/employee" , produces = "application/json;charset=utf-8")
	public String updateEmployeeByID(@RequestBody Employee e) {
	//public String updateEmployeeByID(@RequestBody Employee e, @PathVariable("id") int id) {
		return employeeService.update(e); 
	}
	
	/*
	 * 測試用：
	 */
	@GetMapping(path = "/employees/dummy", produces = "application/json;charset=utf-8")
	public String getDummy() {
		INFO.info("GET  /employees/dummy");
		throw new DummyException(404l,"發生魯魯魯錯誤");	
	}
	
	/*
	 * 測試用：
	 */
	@PostMapping(path = "/foo", produces = "application/json;charset=utf-8")
	public String getFoo(@RequestBody Foo foo) {
		INFO.info("POST  /foo");
		INFO.info(foo.toString());
		
		return foo.toString();
	}
	
	/*
	 * 測試用：
	 */
	@PostMapping(path = "/bar", produces = "application/json;charset=utf-8")
	public String getBar(@RequestBody Bar bar) {
		INFO.info("POST  /bar");
		INFO.info(bar.toString());
		
		return bar.toString();
	}
	
}