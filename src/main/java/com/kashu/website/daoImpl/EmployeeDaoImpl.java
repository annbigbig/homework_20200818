package com.kashu.website.daoImpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.kashu.website.dao.EmployeeDao;
import com.kashu.website.exception.DummyException;
import com.kashu.website.exception.EmployeeExceptionEnum;
import com.kashu.website.exception.NoSuchEmployeeException;
import com.kashu.website.model.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDao<Long, Employee> implements EmployeeDao {

	@SuppressWarnings(value = { "unchecked" , "deprecation" })
	public List<Employee> getAll() {
		Criteria criteria = getSession().createCriteria(Employee.class);
		INFO.info("EmployeeDaoImpl getAll(): 取出所有員工 [");
		return criteria.list();
	}

	public List<Employee> searchByName(String name) {
		/*
		 * https://www.boraji.com/hibernate-5-criteria-query-example
		 * https://www.baeldung.com/hibernate-criteria-queries
		 */
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
        query.select(root).where(builder.like(root.get("name"), "%"+name+"%"));
        Query<Employee> q = session.createQuery(query);
        List<Employee> employees = q.getResultList();

        INFO.info("EmployeeDaoImpl searchByName(): 搜尋關鍵字 [" + name + "] + \t \n");
		return employees;
	}

	@SuppressWarnings(value = { "deprecation" })
	public Employee getOneById(Long id) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		INFO.info("EmployeeDaoImpl getOneById(): 取出 id = " + id + "的員工資料");
		return (Employee) criteria.uniqueResult();
	}
	
	
	public int saveEmployee(Employee emp) {
		try {
			getSession().save(emp);
			INFO.info("EmployeeDaoImpl saveEmployee(): 新增員工資料");
			return 200;
		} catch (Exception ex) {
			ERROR.error(ex.getMessage(),ex);
			ex.printStackTrace();
			return EmployeeExceptionEnum.SAVE_FAILED.getCode();
		}	
	}

	public int updateEmployee(Employee emp) {
		try {
			getSession().update(emp);
			INFO.info("EmployeeDaoImpl saveEmployee(): 修改員工資料 id = " + emp.getId());
			return 200;
		} catch (Exception ex) {
			ERROR.error(ex.getMessage(),ex);
			ex.printStackTrace();
			return EmployeeExceptionEnum.UPDATE_FAILED.getCode();
		}
	}

	//刪除成功：返回1 ， 刪除失敗：返回0
	public int deleteEmployee(Long id) {
		int result = 0;
		//拿掉了try ... catch 因為到Service Layer那裡才會回JSON
		//try {
			Employee emp = getOneById(id);
			if( emp==null|| (emp.getId()<=0l) ) {
				throw new NoSuchEmployeeException(
						EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getCode(),
						EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getErrorMsg()
				);
			}else {
				getSession().delete(emp);
				result = 1;
			}
			
			INFO.info("EmployeeDaoImpl deleteEmployee(): 刪除員工資料 id = " + id);
			return result;
//		} catch (Exception ex) {
//			ERROR.error(ex.getMessage(),ex);
//			ERROR.error(" ===== debug message from deleteEmpoyee() =====");
//			ex.printStackTrace();
//			return EmployeeExceptionEnum.UPDATE_FAILED.getCode();
//		}
	}

}
